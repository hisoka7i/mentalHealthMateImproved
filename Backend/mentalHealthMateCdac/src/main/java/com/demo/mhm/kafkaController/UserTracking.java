package com.demo.mhm.kafkaController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.demo.mhm.dto.UserTrackingDTO;
import com.demo.mhm.kafkaConsumerService.ConsumerMonitoring;
/*
 * This controller which track user visits to every url
 * Then may be find the time he spent on it.
 * Keep track of every click made by him.
*/
@RestController
@RequestMapping("/tracking")
public class UserTracking {
    /*
     * This should not have jwtToken for tracing
    */
    @Autowired
    private com.demo.mhm.kafkaProducerService.UserTracking userTrackingService;
    @Autowired
    private ConsumerMonitoring consumerMonitoring;
    @Value(value = "${kafka.topic.userActivity}")
    private String topicName;

    @PostMapping("/current")
    public ResponseEntity<?> trackUser(@RequestBody UserTrackingDTO userData){
        if(userTrackingService.trackUser(userData)){
            return ResponseEntity.ok("Data added to Consumer!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/monitor")
    public ResponseEntity<?> monitorLag(){
        try {
            List<String> value = consumerMonitoring.monitorLag(topicName);
            return ResponseEntity.status(HttpStatus.OK).body(value);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        
    }
}
