package cz.propas.rabbitmq.producer.config

import cz.propas.rabbitmq.constants.*
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile(PICTURE_TOPIC_EXCHANGE_PROFILE)
class PictureTopicConfig {

    @Bean
    fun pictureTopicExchange(): TopicExchange =
        TopicExchange(PICTURE_TOPIC_EXCHANGE, false, true)

    @Bean
    fun pictureImageQueue(): Queue =
        Queue(PICTURE_IMAGE_QUEUE, false, false, true)

    @Bean
    fun pictureVectorQueue(): Queue =
        Queue(PICTURE_VECTOR_QUEUE, false, false, true)

    @Bean
    fun pictureFilterQueue(): Queue =
        Queue(PICTURE_FILTER_QUEUE, false, false, true)

    @Bean
    fun pictureLogQueue(): Queue =
        Queue(PICTURE_LOG_QUEUE, false, false, true)

    @Bean
    fun pictureImageBindingPng(pictureImageQueue: Queue, pictureTopicExchange: TopicExchange): Binding =
        BindingBuilder.bind(pictureImageQueue).to(pictureTopicExchange).with("*.*.png")

    @Bean
    fun pictureImageBindingJpg(pictureImageQueue: Queue, pictureTopicExchange: TopicExchange): Binding =
        BindingBuilder.bind(pictureImageQueue).to(pictureTopicExchange).with("#.jpg")

    @Bean
    fun pictureVectorBinding(pictureVectorQueue: Queue, pictureTopicExchange: TopicExchange): Binding =
        BindingBuilder.bind(pictureVectorQueue).to(pictureTopicExchange).with("*.*.svg")

    @Bean
    fun pictureFilterBinding(pictureFilterQueue: Queue, pictureTopicExchange: TopicExchange): Binding =
        BindingBuilder.bind(pictureFilterQueue).to(pictureTopicExchange).with("mobile.#")

    @Bean
    fun pictureLogBinding(pictureLogQueue: Queue, pictureTopicExchange: TopicExchange): Binding =
        BindingBuilder.bind(pictureLogQueue).to(pictureTopicExchange).with("*.large.svg")
}