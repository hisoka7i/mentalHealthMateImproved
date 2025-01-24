package com.demo.mhm.kafkaProducerService;

public interface KafkaTopicConfig {
    public void updateRetentionPolicy(String topicName,String retentionMs);
}
