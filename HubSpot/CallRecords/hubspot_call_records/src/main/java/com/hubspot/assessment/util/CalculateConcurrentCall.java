package com.hubspot.assessment.util;

import com.hubspot.assessment.model.CallEvent;
import com.hubspot.assessment.model.CallRecord;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public final class CalculateConcurrentCall {

    public static List<JSONObject> calculateConcurrentCalls(int customerId, List<CallRecord> calls) {
        // For each call, create start and end events and group by day
        Map<LocalDate, List<CallEvent>> eventsByDay =  createEventsByDay(calls);

        // Process each day's events
        List<JSONObject> results = processDayEvents( customerId,eventsByDay);;

        return results;
    }

    private static Map<LocalDate, List<CallEvent>> createEventsByDay(List<CallRecord> calls){
        Map<LocalDate, List<CallEvent>> eventsByDay =new HashMap<>();
        for (CallRecord call : calls) {
            long start = call.getStartTimestamp();
            long end = call.getEndTimestamp();
            String callId = call.getCallId();

            LocalDate startDate = timestampToLocalDate(start);
            LocalDate endDate = timestampToLocalDate(end);

            // Handle calls that span multiple days
            LocalDate currentDate = startDate;
            while (!currentDate.isAfter(endDate)) {
                long dayStart = Math.max(start, dateToTimeStamp(currentDate));
                long dayEnd = Math.min(end, dateToTimeStamp(currentDate.plusDays(1)));

                if (dayStart < dayEnd) {
                    // Add events for this day
                    if (!eventsByDay.containsKey(currentDate)) {
                        eventsByDay.put(currentDate, new ArrayList<>());
                    }
                    eventsByDay.get(currentDate).add(new CallEvent(dayStart, true, callId));
                    eventsByDay.get(currentDate).add(new CallEvent(dayEnd, false, callId));
                }

                currentDate = currentDate.plusDays(1);
            }
        }
        return eventsByDay;
    }

    private static  List<JSONObject> processDayEvents( int customerId, Map<LocalDate, List<CallEvent>> eventsByDay){
        List<JSONObject> results = new ArrayList<>();
        for (Map.Entry<LocalDate, List<CallEvent>> entry : eventsByDay.entrySet()) {
            LocalDate date = entry.getKey();
            List<CallEvent> events = entry.getValue();

            // Sort events by timestamp (and if timestamps are equal, process end events before start events)
            events.sort((e1, e2) -> {
                if (e1.getTimestamp() != e2.getTimestamp()) {
                    return Long.compare(e1.getTimestamp(), e2.getTimestamp());
                }
                // For same timestamp, put end events first
                return Boolean.compare(e1.isStart(), e2.isStart());
            });

            int maxConcurrent = 0;
            int currentConcurrent = 0;
            long maxTimestamp = 0;
            Set<String> currentCallIds = new HashSet<>();
            Set<String> maxCallIds = new HashSet<>();

            // Process events to find maximum concurrent calls
            for (CallEvent event : events) {
                if (event.isStart()) {
                    currentCallIds.add(event.getCallId());
                    currentConcurrent++;

                    if (currentConcurrent > maxConcurrent) {
                        maxConcurrent = currentConcurrent;
                        maxTimestamp = event.getTimestamp();
                        maxCallIds = new HashSet<>(currentCallIds);
                    }
                } else {
                    currentCallIds.remove(event.getCallId());
                    currentConcurrent--;
                }
            }

            // Only add result if there were calls on that day
            if (maxConcurrent > 0) {
                JSONObject result = new JSONObject();
                result.put("customerId", customerId);
                result.put("date", date.format(DateTimeFormatter.ISO_LOCAL_DATE));
                result.put("maxConcurrentCalls", maxConcurrent);
                result.put("timestamp", maxTimestamp);

                // Add callIds as an array in exact format requested
                JSONArray callIdsArray = new JSONArray();
                for (String callId : maxCallIds) {
                    callIdsArray.put(callId);
                }
                result.put("callIds", callIdsArray);

                results.add(result);
            }
        }
        return results;
    }

    private static LocalDate timestampToLocalDate(long timestamp) {
        return Instant.ofEpochMilli(timestamp)
                .atZone(ZoneId.of("UTC"))
                .toLocalDate();
    }
    private static long dateToTimeStamp(LocalDate date) {
        return date.atStartOfDay(ZoneId.of("UTC")).toInstant().toEpochMilli();
    }
}
