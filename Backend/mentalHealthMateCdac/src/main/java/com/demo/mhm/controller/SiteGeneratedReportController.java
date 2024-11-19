package com.demo.mhm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.mhm.service.SiteGenReportI;

@RequestMapping("/SiteReport")
@RestController
@CrossOrigin("http://localhost:3000")
public class SiteGeneratedReportController {

    @Autowired
    private SiteGenReportI SiteGenReportImp;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllReports(@PathVariable("id") int id, Authentication authentication) {
        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok(SiteGenReportImp.getAllReports(id));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Authentication Failed");
    }
}
