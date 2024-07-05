/*
package ru.te3ka.bgd.boardgamerdiaryserver.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;


/**
 * Конфигурация для настройки Kafka Producer и Consumer.
 *
 * В этом классе задаются настройки для отправки и получения сообщений через Kafka.
 */
/*
@Configuration
@EnableKafka
public class KafkaConfig {

    @Value("{spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    */
/**
     * Конфигурация фабрики продюсера для Kafka.
     *
     * @return Конфигурация для создания продюсера Kafka.
     *//*

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    */
/**
     * Конфигурация шаблона Kafka для отправки сообщений.
     *
     * @return Шаблон Kafka для продюсеров.
     *//*

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    */
/**
     * Конфигурация фабрики консюмера для Kafka.
     *
     * @return Конфигурация для создания консюмера Kafka.
     *//*

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    */
/**
     * Конфигурация контейнера для прослушивания сообщений из Kafka.
     *
     * @return Контейнер для прослушивания сообщений Kafka с использованием конфигурации консюмера.
     *//*

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
*/
