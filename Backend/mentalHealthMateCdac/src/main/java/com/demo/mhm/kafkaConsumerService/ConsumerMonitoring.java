package com.demo.mhm.kafkaConsumerService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.ListOffsetsResult;
import org.apache.kafka.clients.admin.OffsetSpec;
import org.apache.kafka.clients.admin.ListOffsetsResult.ListOffsetsResultInfo;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConsumerMonitoring {
    /*
     * What we are doing here is that, offset - last commit -> This will get us lag.
    */
    private final AdminClient adminClient;
    @Value(value = "${KAFKA_PORT}")
    private String bootStrapServer;
    @Value(value = "${spring.kafka.consumer.group-id}")
    private String consumerGroupId;

    public ConsumerMonitoring(){
        this.adminClient = createAdminClient();
    }

    private AdminClient createAdminClient(){
        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        return AdminClient.create(config);
    }

    public List<String> monitorLag(String topicName) throws Exception{
        //get partitions for the topic
        List<TopicPartition> topicPartitions = getTopicPartitions(topicName);

        //fetch the latest offsets
        Map<TopicPartition, Long> logEndOffSets = getLongEndOffSets(topicPartitions);

        //get the consumer group committed offsets
        Map<TopicPartition, OffsetAndMetadata> commitedOffSets = getCommitedOffsets(topicPartitions);

        // Calculate and log the lag for each partition
        return logConsumerLag(logEndOffSets, commitedOffSets);
    }

    private List<String> logConsumerLag(Map<TopicPartition, Long> logEndOffsets, Map<TopicPartition, OffsetAndMetadata> committedOffsets) {
        List<String> outcome = new ArrayList<>();
        logEndOffsets.forEach((partition, logEndOffset) -> {
            long committedOffset = committedOffsets.getOrDefault(partition, new OffsetAndMetadata(0L)).offset();
            long lag = logEndOffset - committedOffset;
            outcome.add("Partition:"+partition.partition()+", Log End Offset: "+ logEndOffset+", Committed Offset: "+committedOffset+", Lag: "+lag
                );
        });
        return outcome;
    }
    /*
     * Function to get all the partitions from a topic
    */
    private List<TopicPartition> getTopicPartitions(String topicName) throws ExecutionException, InterruptedException {
        List<TopicPartition> topicPartitions = new ArrayList<>();
        adminClient.describeTopics(Collections.singletonList(topicName)).all()
        .get().forEach((topic, description) -> description.partitions()
        .forEach(partition -> topicPartitions.add(new TopicPartition(topicName, partition.partition()))));
        return topicPartitions;
    } 

    //A function to get longEndOffset
    private Map<TopicPartition, Long> getLongEndOffSets(List<TopicPartition> topicPartitions) throws ExecutionException, InterruptedException{

        //a map for partition and getting the current offset
        Map<TopicPartition, OffsetSpec> offsetSpecMap = new HashMap<>();
        for(TopicPartition currentTopic: topicPartitions){
            offsetSpecMap.put(currentTopic, OffsetSpec.latest());
        }

        //fetch for the latest offsets for each partition
        ListOffsetsResult result = adminClient.listOffsets(offsetSpecMap);
        Map<TopicPartition, Long> logEndOffSets = new HashMap<>();
        result.all().get().forEach((topic, offsetResult)->{
            ListOffsetsResultInfo offsetInfo = offsetResult;
            logEndOffSets.put(topic, offsetInfo.offset());
        });

        return logEndOffSets;
    }
    //A function to get committed offset
    private Map<TopicPartition, OffsetAndMetadata> getCommitedOffsets(List<TopicPartition> topicPartitions){
        Map<TopicPartition, OffsetAndMetadata> committedOffsets = new HashMap<>();
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(getConsumerProps());
        topicPartitions.forEach(tp -> committedOffsets.put(tp, consumer.committed(tp)));
        return committedOffsets;
    }

    private Properties getConsumerProps() {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootStrapServer);
        props.put("group.id", consumerGroupId);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return props;
    }
}
