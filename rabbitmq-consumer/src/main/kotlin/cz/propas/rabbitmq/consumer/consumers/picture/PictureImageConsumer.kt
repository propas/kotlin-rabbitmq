package cz.propas.rabbitmq.consumer.consumers.picture

import cz.propas.rabbitmq.constants.PICTURE_IMAGE_QUEUE
import com.fasterxml.jackson.databind.ObjectMapper
import cz.propas.rabbitmq.constants.PICTURE_DIRECT_EXCHANGE_PROFILE
import cz.propas.rabbitmq.consumer.consumers.AbstractConsumer
import cz.propas.rabbitmq.entity.Picture
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile(PICTURE_DIRECT_EXCHANGE_PROFILE)
class PictureImageConsumer(
    private val objectMapper: ObjectMapper) : AbstractConsumer() {

    @RabbitListener(queues = [PICTURE_IMAGE_QUEUE])
    fun receiveMessage(message: Message) {
        val picture = objectMapper.readValue(message.body, Picture::class.java)
        logMessage("Picture", picture.toString())
    }
}