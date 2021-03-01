package com.wahuu.restspringdata.service;

import com.wahuu.restspringdata.model.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    private final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @KafkaListener(topics = "hotel", groupId = "new_group_id3")
    public void consume(Hotel hotel) {
        logger.info("Consumed message: {}", hotel);
    }

}
