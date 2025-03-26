package com.hubspot.assessment.service;

import com.hubspot.assessment.model.CallRecordWrapper;
import com.hubspot.assessment.util.CalculateConcurrentCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hubspot.assessment.model.CallEvent;
import com.hubspot.assessment.model.CallRecord;
import com.hubspot.assessment.model.ResultPayload;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class CallService {

    @Autowired
    private Environment env;

    private final RestTemplate restTemplate;
    @Value("${hubspot.api.get.call.records.url}")
    private String getCallRecordsUrl;

    @Value("hubspot.api.post.concurrent.calls.url")
    private String postConcurrentCallsUrl;

    @Autowired
    public CallService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean processCallData() {
        try {
            // Fetch data
            CallRecordWrapper response = restTemplate.getForObject(getCallRecordsUrl, CallRecordWrapper.class);
            if (response == null) {
                System.out.println("Failed to fetch data");
                return false;
            }

            List<CallRecord> calls = response.getCallRecords();

            // Group calls by customer
            Map<Integer,List<CallRecord>> callsByCustomer =new HashMap<>();
            for (CallRecord callRecord : calls){
                int customerId = callRecord.getCustomerId();
                if(!callsByCustomer.containsKey(customerId)){
                    callsByCustomer.put(customerId,new ArrayList<>());
                }
                callsByCustomer.get(customerId).add(callRecord);
            }

            // Process each customer's calls
            ResultPayload resultPayload = new ResultPayload();

            for (Map.Entry<Integer, List<CallRecord>> entry : callsByCustomer.entrySet()) {
                int customerId = entry.getKey();
                List<CallRecord> customerCalls = entry.getValue();

                // Calculate the results for this customer
                List<JSONObject> customerResults = CalculateConcurrentCall.calculateConcurrentCalls(customerId, customerCalls);
                resultPayload.addAllResults(customerResults);
            }

            // Submit results
            return submitResults(resultPayload);

        } catch (Exception e) {
            System.err.println("Error processing call data: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private boolean submitResults(ResultPayload payload) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Convert to exact format required in the specification
            String jsonPayload = payload.toString();

            HttpEntity<String> request = new HttpEntity<>(jsonPayload, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(Objects.requireNonNull(env.getProperty(postConcurrentCallsUrl)), request, String.class);
            System.out.println("Response status: " + response.getStatusCode());
            System.out.println("Response body: " + response.getBody());

            // Log the actual payload sent for verification
            System.out.println("Payload sent: " + jsonPayload);

            return response.getStatusCode().is2xxSuccessful();
            //return true;
        } catch (Exception e) {
            System.err.println("Error submitting results: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
