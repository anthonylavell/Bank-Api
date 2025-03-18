package org.academy.registerapplication.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @Autowired
    ObjectMapper mapper;

    @GetMapping("/")
    public ObjectNode getHomeMessage(){
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("message","Welcome to College Catalog API");
        return objectNode;
    }
}
