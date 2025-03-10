package cz.propas.rabbitmq.consumer.consumers

import org.springframework.amqp.core.Message

interface Consumer {
    fun receiveMessage(message: Message)
    fun receiveMessage(from: String, message: String)
}