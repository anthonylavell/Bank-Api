package com.hubspot.assessment.model;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.List;

public class ResultPayload {
    private JSONArray results;

    public ResultPayload() {
        this.results = new JSONArray();
    }

    public void addResult(JSONObject result) {
        this.results.put(result);
    }

    public void addAllResults(List<JSONObject> listOfResults) {
        for (JSONObject result : listOfResults) {
            this.results.put(result);
        }
    }

    public JSONObject toJson() {
        JSONObject payload = new JSONObject();
        payload.put("results", this.results);
        return payload;
    }

    @Override
    public String toString() {
        return toJson().toString();
    }
}
