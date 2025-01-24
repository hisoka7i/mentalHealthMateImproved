package com.demo.mhm.kafkaProducerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.demo.mhm.dto.UserTrackingDTO;
@Service
public class UserTrackingImpl implements UserTracking {
    @Value(value = "${kafka.topic.userActivity}")
    private String userActivitytopic;

    @Autowired
    private KafkaTemplate<Object,Object> template;
    @Override
    public boolean trackUser(UserTrackingDTO userData) {
        // TODO Auto-generated method stub
        //Need to publish the data from here into kakfa;
        try {
            this.template.send(userActivitytopic, userData);
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
        return true;
    }
    
}
