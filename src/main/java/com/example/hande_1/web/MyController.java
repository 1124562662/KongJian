package com.example.hande_1.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/MyAccount")
public class MyController {
     @GetMapping
    public  String myaccount(Principal principal){

         return "MyAccount";
     }
}
