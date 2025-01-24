package com.demo.mhm.kafkaProducerService;

import java.util.Collections;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.Config;
import org.apache.kafka.clients.admin.ConfigEntry;
import org.apache.kafka.common.config.ConfigResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

@Service
public class KafkaTopicConfigImp implements KafkaTopicConfig {

    @Autowired
    private KafkaAdmin kafkaAdmin;

    @Override
    public void updateRetentionPolicy(String topicName, String retentionMs) {
        // TODO Auto-generated method stub
        try(AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties())){
            //speficy the topic resource
            ConfigResource configResource = new ConfigResource(ConfigResource.Type.TOPIC, topicName);

            //set retention
            ConfigEntry retentionEntry = new ConfigEntry("retention.ms", retentionMs);
            Config config = new Config(Collections.singletonList(retentionEntry));


            //updating topic configuration
            adminClient.alterConfigs(Collections.singletonMap(configResource, config)).all().get();
            System.out.println("Retention policy changed successfully <------------");
        }catch(Exception ex){
            System.err.println("Kafka rentention policy failed to change!");
        }
    }
    
}
