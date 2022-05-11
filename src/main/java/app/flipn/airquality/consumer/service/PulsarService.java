package app.flipn.airquality.consumer.service;

import app.flipn.airquality.consumer.model.Observation;
import org.apache.pulsar.client.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

import java.util.ArrayList;
import java.util.List;

/**
 * Not used in message listener version
 * only needed for manual consumer
 * message listener is more practical
 */
@Service
public class PulsarService {
    private static final Logger log = LoggerFactory.getLogger(PulsarService.class);
    private static final int MAX_COUNT = 5;

    @Autowired
    PulsarClient pulsarClient;

    @Autowired
    Consumer<Observation> consumer;

    @Autowired
    MessageListener messageListener;

    /**
     * consume to pulsar
     *
     * @return List Observation
     */
    public List<Observation> consume() {
        List<Observation> messages = new ArrayList<Observation>();

        int count = 0;

        do {
            // Wait until a message is available
            Message<Observation> msg = null;
            try {
                msg = consumer.receive();
            } catch (PulsarClientException e) {
                log.error("Consume Failure", e);
            }

            Observation observation = msg.getValue();
            observation.setKey(msg.getKey());

            if ( msg.getMessageId() != null ) {
                observation.setMessageId(msg.getMessageId().toString());
            }
            observation.setProducerName(msg.getProducerName());
            messages.add(observation);

            // Acknowledge processing of the message so that it can be deleted
            try {
                consumer.acknowledge(msg);
            } catch (PulsarClientException e) {
                log.error("Consume Ack Failure", e);
            }
            count++;
        } while (count < MAX_COUNT );

        return messages;
    }
}
