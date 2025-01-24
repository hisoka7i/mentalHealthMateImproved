package com.demo.mhm.kafkaController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.demo.mhm.dto.UserTrackingDTO;
/*
 * This controller which track user visits to every url
 * Then may be find the time he spent on it.
 * Keep track of every click made by him.
*/
@RestController
public class UserTracking {
    @Autowired
    private com.demo.mhm.kafkaProducerService.UserTracking userTrackingService;
    @PostMapping("/")
    public ResponseEntity<?> trackUser(@RequestBody UserTrackingDTO userData){
        if(userTrackingService.trackUser(userData)){
            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
    }
}
