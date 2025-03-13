package cz.propas.rabbitmq.consumer.consumers.picture

import com.fasterxml.jackson.databind.ObjectMapper
import cz.propas.rabbitmq.constants.*
import cz.propas.rabbitmq.consumer.consumers.AbstractConsumer
import cz.propas.rabbitmq.entity.Picture
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile(PICTURE_TOPIC_EXCHANGE_PROFILE)
class PictureTopicConsumer(
    private val objectMapper: ObjectMapper) : AbstractConsumer() {

    @RabbitListener(queues = [PICTURE_IMAGE_QUEUE, PICTURE_VECTOR_QUEUE, PICTURE_FILTER_QUEUE, PICTURE_LOG_QUEUE])
    fun receiveMessage(message: Message) {
        val picture = objectMapper.readValue(message.body, Picture::class.java)
        logMessage(message.messageProperties.receivedRoutingKey, picture.toString())
    }
}