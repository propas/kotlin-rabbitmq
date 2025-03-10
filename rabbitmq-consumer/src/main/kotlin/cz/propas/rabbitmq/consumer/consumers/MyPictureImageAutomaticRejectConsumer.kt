package cz.propas.rabbitmq.consumer.consumers

import com.fasterxml.jackson.databind.ObjectMapper
import cz.propas.rabbitmq.entity.Picture
import org.springframework.amqp.AmqpRejectAndDontRequeueException
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener

//@Service
//@Profile("picture-dlx")
class MyPictureImageAutomaticRejectConsumer(
    private val objectMapper: ObjectMapper) : AbstractConsumer() {

    @RabbitListener(queues = ["q.mypicture.image"])
    override fun receiveMessage(message: Message) {
        val picture = objectMapper.readValue(message.body, Picture::class.java)

        if (picture.size > 9000) throw AmqpRejectAndDontRequeueException("Picture size is too big! $picture")
        receiveMessage("Picture", picture.toString())
    }
}