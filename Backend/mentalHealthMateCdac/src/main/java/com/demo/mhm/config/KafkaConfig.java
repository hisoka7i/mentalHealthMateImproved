package com.demo.mhm.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig; // For Kafka Admin configuration
import org.apache.kafka.clients.admin.NewTopic;         // For creating new Kafka topics
import org.springframework.beans.factory.annotation.Value; // For reading environment variables or properties
import org.springframework.context.annotation.Bean;       // For defining Spring beans
import org.springframework.context.annotation.Configuration; // For marking as a configuration class
import org.springframework.kafka.config.TopicBuilder;     // For building Kafka topics
import org.springframework.kafka.core.KafkaAdmin;         // For Kafka admin operations
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;


@Configuration
public class KafkaConfig {
    @Value(value = "${KAFKA_PORT}")
    private String kafkaPort;
    @Value(value = "${kafka.topic.userActivity}")
    private String topicName;
    /*
     * For creating topic programtically
    */
    @Bean
    public KafkaAdmin kafkaAdmin(){
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaPort);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic userActivityTopic(){
        return TopicBuilder.name(topicName)
        .partitions(1).replicas(1).build();
    }

    @Bean
    public RecordMessageConverter converter(){
        return new JsonMessageConverter(); //no need to manually serialize and de-serialize data
    }
}
