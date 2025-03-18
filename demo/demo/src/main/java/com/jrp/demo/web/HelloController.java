package com.jrp.demo.web;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting")
public class HelloController {
    @RequestMapping("/basic")
    public String sayHello(){
        return "<h1>Hello Anthony</h1>";
    }

    @RequestMapping("/proper")
    public String sayProperHello(){
        return "<h1>Hello there, how are you?</h1>";
    }

    @RequestMapping("/user_entry")
    public String userForm(){
       return "<form action=\"/greeting/user_greeting\" method=\"GET\">\n" +
             "  First name:<br>\n" +
                "  <input type=\"text\" name=\"firstname\" value=\"Anthony\">\n" +
                "  <br>\n" +
                "  Last name:<br>\n" +
                "  <input type=\"text\" name=\"lastname\" value=\"Ward\">\n" +
                "  <br><br>\n" +
                "  <input type=\"submit\" value=\"Submit\">\n" +
                "</form> ";
    }

    @RequestMapping(value="/user_greeting", method = RequestMethod.GET)
    public String printUserGreeting(@RequestParam String firstname, @RequestParam String lastname){
        return"Hello there, "+firstname +" "+ lastname;
    }

    @RequestMapping("/orders/{id}")
    public String getOrder(@PathVariable String id){
        return "Order ID: " + id;

    }
}
