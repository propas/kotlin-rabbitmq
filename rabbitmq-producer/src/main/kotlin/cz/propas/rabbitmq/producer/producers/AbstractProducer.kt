package cz.propas.rabbitmq.producer.producers

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.rabbit.core.RabbitTemplate

abstract class AbstractProducer(
    private val rabbitTemplate: RabbitTemplate,
    private val objectMapper: ObjectMapper): Producer {

    override fun sendMessage(queue: String, message: String) {
        rabbitTemplate.convertAndSend(queue, message)
    }

    override fun <T : Any> sendMessage(routingKey: String, data: T) {
        val json = objectMapper.writeValueAsString(data)
        rabbitTemplate.convertAndSend(routingKey, json)
    }

    override fun <T : Any> sendMessage(exchange: String, routingKey: String, data: T) {
        val json = objectMapper.writeValueAsString(data)
        rabbitTemplate.convertAndSend(exchange, routingKey, json)
    }
}