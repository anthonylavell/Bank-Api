package com.hubspot.assessment.model;

public class CallEvent {
    private long timestamp;
    private boolean isStart;
    private String callId;

    public CallEvent(long timestamp, boolean isStart, String callId) {
        this.timestamp = timestamp;
        this.isStart = isStart;
        this.callId = callId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public boolean isStart() {
        return isStart;
    }

    public String getCallId() {
        return callId;
    }
}
