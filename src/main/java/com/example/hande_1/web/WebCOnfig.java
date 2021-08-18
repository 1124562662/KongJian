package com.example.hande_1.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebCOnfig  implements WebMvcConfigurer {
      @Override
       public  void addViewControllers (ViewControllerRegistry vcr){
          vcr.addViewController("/login").setViewName("login");
      }
}
