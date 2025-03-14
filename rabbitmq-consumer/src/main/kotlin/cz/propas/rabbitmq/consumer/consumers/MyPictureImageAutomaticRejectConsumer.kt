package cz.propas.rabbitmq.consumer.consumers

import com.fasterxml.jackson.databind.ObjectMapper
import cz.propas.rabbitmq.constants.MY_PICTURE_IMAGE_QUEUE
import cz.propas.rabbitmq.entity.Picture
import org.springframework.amqp.AmqpRejectAndDontRequeueException
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener

//@Service
//@Profile(MY_PICTURE_DLX_PROFILE)
class MyPictureImageAutomaticRejectConsumer(
    private val objectMapper: ObjectMapper) : AbstractConsumer() {

    @RabbitListener(queues = [MY_PICTURE_IMAGE_QUEUE])
    fun receiveMessage(message: Message) {
        val picture = objectMapper.readValue(message.body, Picture::class.java)

        if (picture.size > 9000) throw AmqpRejectAndDontRequeueException("Picture size is too big! $picture")
        logMessage("Picture", picture.toString())
    }
}