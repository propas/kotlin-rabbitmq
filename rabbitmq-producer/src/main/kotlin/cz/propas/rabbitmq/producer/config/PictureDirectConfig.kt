package cz.propas.rabbitmq.producer.config

import cz.propas.rabbitmq.constants.*
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile(PICTURE_DIRECT_EXCHANGE_PROFILE)
class PictureDirectConfig {

    @Bean
    fun pictureDirectExchange(): DirectExchange =
        DirectExchange(PICTURE_DIRECT_EXCHANGE, false, true)

    @Bean
    fun pictureImageQueue(): Queue =
        Queue(PICTURE_IMAGE_QUEUE, false, false, true)

    @Bean
    fun pictureVectorQueue(): Queue =
        Queue(PICTURE_VECTOR_QUEUE, false, false, true)

    @Bean
    fun pictureImageBindingPng(pictureImageQueue: Queue, pictureDirectExchange: DirectExchange): Binding =
        BindingBuilder.bind(pictureImageQueue).to(pictureDirectExchange).with("png")

    @Bean
    fun pictureImageBindingJpg(pictureImageQueue: Queue, pictureDirectExchange: DirectExchange): Binding =
        BindingBuilder.bind(pictureImageQueue).to(pictureDirectExchange).with("jpg")

    @Bean
    fun pictureVectorBinding(pictureVectorQueue: Queue, pictureDirectExchange: DirectExchange): Binding =
        BindingBuilder.bind(pictureVectorQueue).to(pictureDirectExchange).with("svg")
}