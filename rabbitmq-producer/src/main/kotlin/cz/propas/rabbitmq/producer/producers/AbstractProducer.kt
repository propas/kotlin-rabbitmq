package cz.propas.rabbitmq.producer.producers

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageProperties
import org.springframework.amqp.rabbit.core.RabbitTemplate

abstract class AbstractProducer(
    private val rabbitTemplate: RabbitTemplate,
    private val objectMapper: ObjectMapper): Producer {

    fun sendMessage(routingKey: String, message: String) {
        rabbitTemplate.convertAndSend(routingKey, message)
    }

    fun <T : Any> sendMessage(routingKey: String, data: T) {
        sendMessage("", routingKey, data)
    }

    fun <T : Any> sendMessage(exchange: String, routingKey: String, data: T) {
        sendMessage(exchange, routingKey, MessageProperties(), data)
    }

    fun <T : Any> sendMessage(exchange: String,
                              routingKey: String,
                              properties: MessageProperties = MessageProperties(),
                              data: T) {
        val json = try {
            objectMapper.writeValueAsString(data)
        } catch (e: JsonProcessingException) {
            throw RuntimeException("Failed to serialize message data", e)
        }
        val message = Message(json.toByteArray(Charsets.UTF_8), properties)
        rabbitTemplate.convertAndSend(exchange, routingKey, message)
    }
}