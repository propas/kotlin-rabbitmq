package cz.propas.rabbitmq.consumer.consumers

import com.fasterxml.jackson.databind.ObjectMapper
import com.rabbitmq.client.Channel
import cz.propas.rabbitmq.constants.MY_PICTURE_DLX_PROFILE
import cz.propas.rabbitmq.constants.MY_PICTURE_IMAGE_QUEUE
import cz.propas.rabbitmq.entity.Picture
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.support.AmqpHeaders
import org.springframework.context.annotation.Profile
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Service

@Service
@Profile(MY_PICTURE_DLX_PROFILE)
class MyPictureImageManualRejectConsumer(
    private val objectMapper: ObjectMapper) : AbstractConsumer() {

    @RabbitListener(queues = [MY_PICTURE_IMAGE_QUEUE])
    fun receiveMessage(message: Message, channel: Channel, @Header(AmqpHeaders.DELIVERY_TAG) tag: Long) {
        val picture = objectMapper.readValue(message.body, Picture::class.java)

        if (picture.size > 9000) channel.basicReject(tag, false)
        logMessage("Picture", picture.toString())

        channel.basicAck(tag, false)
        TODO("Do not forget to set acknowledge-mode to manual in application-common.yaml")
    }
}