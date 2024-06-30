/*
package ru.te3ka.bgd.boardgamerdiaryserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.te3ka.bgd.boardgamerdiaryserver.service.KafkaProducerService;

@RestController
@RequestMapping("/upload/kafka")
public class KafkaController {
    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public KafkaController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessageToKafka(@RequestParam("message") String message) {
        kafkaProducerService.sendMessage("board_gamer_diary_topic", message);
        return ResponseEntity.ok("Message sent to Kafka");
    }

}
*/
