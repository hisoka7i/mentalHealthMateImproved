package com.demo.mhm.kafkaProducerService;

import com.demo.mhm.dto.UserTrackingDTO;

public interface UserTracking {
    public boolean trackUser(UserTrackingDTO userData);
}
