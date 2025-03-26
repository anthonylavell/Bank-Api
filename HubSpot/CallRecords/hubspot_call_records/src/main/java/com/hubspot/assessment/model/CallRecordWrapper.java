package com.hubspot.assessment.model;

import java.util.List;

public class CallRecordWrapper {
    private List<CallRecord> callRecords;

    public List<CallRecord> getCallRecords() {
        return callRecords;
    }

    public void setCallRecords(List<CallRecord> callRecords) {
        this.callRecords = callRecords;
    }
}
