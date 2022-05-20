package app.flipn.airquality.consumer.config;

import app.flipn.airquality.consumer.model.Observation;
import org.apache.pulsar.client.api.*;
import org.apache.pulsar.client.impl.schema.JSONSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.TimeUnit;

@Configuration
public class PulsarConsumerConfig {
    private static final Logger log = LoggerFactory.getLogger(PulsarConsumerConfig.class);

    @Autowired
    PulsarClient pulsarClient;

    @Value("${consumer.name:consumerName}")
    String consumerName;

    @Value("${topic.name:airquality}")
    String topicName;

    @Value("${subscription.name:airqualitysubscription}")
    String subscriptionName;

    @Bean
    public MessageListener getMessageListener() {
        MessageListener<Observation> myMessageListener = (consumer, msg) -> {
            try {
                log.info("Topic Name: {}", msg.getTopicName());
                log.info("Key: {}", msg.getKey());
                log.info("Producer Name: {}", msg.getProducerName());
                log.info("Publish Time: {}", String.valueOf(msg.getPublishTime()));
                Observation observation = msg.getValue();
                log.info("Observation: {}",observation.toString());
                consumer.acknowledge(msg);
            } catch (Exception e) {
                log.error("Failed receiver", e);
                consumer.negativeAcknowledge(msg);
            }
        };
        
        return myMessageListener;
    }

    @Bean
    public Consumer<Observation> getConsumer(MessageListener myMessageListener)
            throws PulsarClientException {
        return pulsarClient.newConsumer(JSONSchema.of(Observation.class))
                .topic(topicName)
                .subscriptionName(subscriptionName)
                .subscriptionType(SubscriptionType.Key_Shared)
                .subscriptionInitialPosition(SubscriptionInitialPosition.Earliest)
                .receiverQueueSize(5)
                .messageListener(myMessageListener)
                .ackTimeout(10, TimeUnit.SECONDS)
                .subscribe();
    }

    @Bean
    public Consumer<Observation> getConsumer() {
        Consumer<Observation> pulsarConsumer = null;
        ConsumerBuilder<Observation> consumerBuilder =
                pulsarClient.newConsumer(JSONSchema.of(Observation.class))
                        .topic(topicName)
                        .subscriptionName(subscriptionName)
                        .subscriptionType(SubscriptionType.Shared)
                        .ackTimeout(10, TimeUnit.SECONDS)
                        .receiverQueueSize(5)
                        .subscriptionInitialPosition(SubscriptionInitialPosition.Earliest)
                        .consumerName(consumerName);
        try {
            pulsarConsumer = consumerBuilder.subscribe();
        } catch (PulsarClientException e) {
            log.error("Consumer Builder Error", e);
        }
        return pulsarConsumer;
    }
}