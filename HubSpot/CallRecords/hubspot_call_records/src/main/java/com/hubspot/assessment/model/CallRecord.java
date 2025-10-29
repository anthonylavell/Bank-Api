package com.hubspot.assessment.model;

// Models
public class CallRecord {
    private int customerId;
    private String callId;
    private long startTimestamp;
    private long endTimestamp;

    public CallRecord(){

    }
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
