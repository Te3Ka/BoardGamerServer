/*
package ru.te3ka.bgd.boardgamerdiaryserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.te3ka.bgd.boardgamerdiaryserver.service.KafkaProducerService;

/**
 * Контроллер для обработки запросов, связанных с отправкой сообщений в Kafka.
 *
 * Этот контроллер предоставляет API для отправки сообщений в Kafka топик через
 * сервис KafkaProducerService. Он предназначен для взаимодействия с Kafka
 * для передачи данных или сообщений.
 */
/*
@RestController
@RequestMapping("/upload/kafka")
public class KafkaController {
    private final KafkaProducerService kafkaProducerService;

    /**
     * Конструктор для инициализации KafkaController.
     *
     * @param kafkaProducerService Сервис для отправки сообщений в Kafka.
     */
/*
    @Autowired
    public KafkaController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    /**
     * Обрабатывает POST запрос для отправки сообщения в Kafka топик.
     *
     * @param message Сообщение для отправки в Kafka топик.
     * @return Ответ, подтверждающий успешную отправку сообщения.
     */
/*
    @PostMapping("/send")
    public ResponseEntity<String> sendMessageToKafka(@RequestParam("message") String message) {
        kafkaProducerService.sendMessage("board_gamer_diary_topic", message);
        return ResponseEntity.ok("Message sent to Kafka");
    }

}
*/
