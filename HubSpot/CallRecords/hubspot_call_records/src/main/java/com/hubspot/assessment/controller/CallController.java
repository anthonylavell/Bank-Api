package com.hubspot.assessment.controller;

import com.hubspot.assessment.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// Controller for exposing REST endpoints
@Controller
@RequestMapping("/process")
public class CallController {

    private final CallService callService;

    @Autowired
    public CallController(CallService callService) {

        this.callService = callService;
    }

    @GetMapping("/call")
    @ResponseBody
    public String processCallRecordData() {
        boolean success = callService.processCallRecord();
        return success ? "Processing call records successfully" : "Processing call records failed";
    }
}
