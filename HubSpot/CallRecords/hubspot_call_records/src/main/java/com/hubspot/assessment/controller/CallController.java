package com.hubspot.assessment.controller;

import com.hubspot.assessment.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// Controller for exposing REST endpoints
@Controller
public class CallController {

    private final CallService callService;

    @Autowired
    public CallController(CallService callService) {

        this.callService = callService;
    }

    @GetMapping("/process")
    @ResponseBody
    public String processData() {
        boolean success = callService.processCallData();
        return success ? "Processing completed successfully" : "Processing failed";
    }
}
