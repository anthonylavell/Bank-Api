package com.hubspot.assessment.model;

import org.json.JSONObject;

// Models
public class CallRecord {
    private int customerId;
    private String callId;
    private long startTimestamp;
    private long endTimestamp;

    public CallRecord(){

    }
    // Getters
    public int getCustomerId() {
        return customerId;
    }

    public String getCallId() {
        return callId;
    }

    public long getStartTimestamp() {
        return startTimestamp;
    }

    public long getEndTimestamp() {
        return endTimestamp;
    }
}
