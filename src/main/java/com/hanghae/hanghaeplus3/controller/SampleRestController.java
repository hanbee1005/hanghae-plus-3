package com.hanghae.hanghaeplus3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleRestController {
    @GetMapping("/")
    public ResponseEntity<?> helloWorld() {
        return ResponseEntity.ok("Hello World!!");
    }
}
