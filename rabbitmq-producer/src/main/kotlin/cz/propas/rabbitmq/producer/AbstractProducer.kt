package cz.propas.rabbitmq.producer

import org.springframework.amqp.rabbit.core.RabbitTemplate

abstract class AbstractProducer(
    private val rabbitTemplate: RabbitTemplate): Producer {

    override fun sendMessage(queue: String, message: String) {
        rabbitTemplate.convertAndSend(queue, message)
    }
}