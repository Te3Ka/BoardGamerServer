/*
package ru.te3ka.bgd.boardgamerdiaryserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

*/
/**
 * Сервис для потребления сообщений из Kafka.
 *
 * Этот сервис использует аннотацию {@link KafkaListener} для прослушивания сообщений из указанной топики Kafka.
 * Поступающие сообщения обрабатываются методом {@link #consume(String)}, который логирует полученные данные.
 *//*

@Service
public class KafkaConsumerService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    */
/**
     * Обрабатывает сообщения, полученные из топика Kafka.
     *
     * Этот метод вызывается при поступлении сообщений в топик "board_gamer_diary_topic".
     * Сообщение выводится в лог для дальнейшего анализа.
     *
     * @param message Сообщение, полученное из Kafka топика.
    *//*

    @KafkaListener(topics = "board_gamer_diary_topic", groupId = "group_id")
    public void consume(String message) {
        logger.info("Consumed message: {}", message);
    }
}
*/
