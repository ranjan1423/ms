package com.ms.card.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UtilController {

    @GetMapping("/ping")
    public String ping() {
        return "ping : card-service";
    }

    @Value("${build-info.version}")
    private String version;

    private final Environment environment;

    public UtilController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping(path = "/build-version")
    public ResponseEntity<String> fetchBuildInfo() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(environment.getProperty("build-info.environment")+ " " + version);
    }

    @GetMapping(path = "/build-features")
    public ResponseEntity<String> fetchFeatures() {
        return ResponseEntity.status(HttpStatus.OK)
                .body("Features in : " + environment.getProperty("build-info.environment") +" "+ environment.getProperty("build-info.features"));
    }
}