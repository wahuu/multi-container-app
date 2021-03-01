package com.wahuu.restspringdata.service;

import com.wahuu.restspringdata.model.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class ProducerService {
    private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);
    private static final String TOPIC = "hotel";

    @Autowired
    private KafkaTemplate<String, Hotel> kafkaTemplate;

    public void sendMessage(Hotel hotel) {
        ListenableFuture<SendResult<String, Hotel>> send = this.kafkaTemplate.send(TOPIC, hotel);
        send.addCallback(new ListenableFutureCallback<SendResult<String, Hotel>>() {
            @Override
            public void onFailure(Throwable e) {
                logger.error("Unable to send message= [{}] due to {}", hotel.toString(), e.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Hotel> result) {
                logger.info("Sent message= [{}] with offset=[ {} ]", hotel.toString(), result.getRecordMetadata().offset());

            }
        });

    }
}
