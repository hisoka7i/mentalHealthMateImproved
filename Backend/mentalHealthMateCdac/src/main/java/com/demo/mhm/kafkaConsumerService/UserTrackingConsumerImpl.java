package com.demo.mhm.kafkaConsumerService;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.demo.mhm.dto.UserTrackingDTO;

@Service
public class UserTrackingConsumerImpl implements UserTrackingConsumer{

    @Override
    @KafkaListener(topics = "${kafka.topic.userActivity}", groupId = "user-tracking-group")
    public void consumeUserTracking(UserTrackingDTO userData) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'consumeUserTracking'");
        System.out.println("UserTrackingData"+userData);
    }
    
}
