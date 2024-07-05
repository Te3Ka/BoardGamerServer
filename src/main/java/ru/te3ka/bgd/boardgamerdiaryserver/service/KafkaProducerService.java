/*
package ru.te3ka.bgd.boardgamerdiaryserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

*/
/**
 * Сервис для отправки сообщений в Kafka.
 *
 * Этот сервис использует {@link KafkaTemplate} для отправки сообщений в указанный топик Kafka.
 * Сообщения логируются перед отправкой для мониторинга и отладки.
 *//*

@Service
public class KafkaProducerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    */
/**
     * Конструктор для внедрения зависимости {@link KafkaTemplate}.
     *
     * @param kafkaTemplate {@link KafkaTemplate} для отправки сообщений в Kafka.
     *//*

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    */
/**
     * Отправляет сообщение в указанный топик Kafka.
     *
     * Этот метод логирует отправляемое сообщение и топик, а затем отправляет сообщение в Kafka.
     *
     * @param topic   Топик Kafka, в который будет отправлено сообщение.
     * @param message Сообщение, которое будет отправлено в топик.
     *//*

    public void sendMessage(String topic, String message) {
        logger.info("Sending message: {} to topic{}", message, topic);
        kafkaTemplate.send(topic, message);
    }
}
*/
