package cz.propas.rabbitmq.consumer.consumers

import com.fasterxml.jackson.databind.ObjectMapper
import cz.propas.rabbitmq.constants.SPRING_PICTURE_IMAGE_QUEUE
import cz.propas.rabbitmq.constants.SPRING_PICTURE_VECTOR_QUEUE
import cz.propas.rabbitmq.constants.SPRING_RETRY_DIRECT_EXCHANGE_PROFILE
import cz.propas.rabbitmq.entity.Picture
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import java.io.IOException

@Service
@Profile(SPRING_RETRY_DIRECT_EXCHANGE_PROFILE)
class SpringRetryDirectPictureConsumer(
    private val objectMapper: ObjectMapper) : AbstractConsumer() {

    @RabbitListener(queues = [SPRING_PICTURE_IMAGE_QUEUE])
    fun receiveImageMessage(message: Message) {
        val picture = objectMapper.readValue(message.body, Picture::class.java)
        logMessage("Consuming image", picture.toString())

        if (picture.size > 9000) {
            throw IOException("Image " + picture.name + " size too large : " + picture.size)
        }
        logMessage("Processing image", picture.toString())
    }

    @RabbitListener(queues = [SPRING_PICTURE_VECTOR_QUEUE])
    fun receiveVectorMessage(message: Message) {
        val picture = objectMapper.readValue(message.body, Picture::class.java)
        logMessage("Consuming vector", picture.toString())
        logMessage("Processing vector", picture.toString())
    }
}