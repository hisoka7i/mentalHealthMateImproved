package com.demo.mhm.kafkaConsumerService;

import com.demo.mhm.dto.UserTrackingDTO;

public interface UserTrackingConsumer {
    public void consumeUserTracking(UserTrackingDTO userData);
}
