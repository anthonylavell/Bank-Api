package com.hubspot.assessment.model;

public class CallEvent {
    private long timeStamp;
    private boolean isStart;
    private String callId;

    public CallEvent(long timeStamp, boolean isStart, String callId) {
        this.timeStamp = timeStamp;
        this.isStart = isStart;
        this.callId = callId;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public boolean isStart() {
        return isStart;
    }

    public String getCallId() {
        return callId;
    }
}
