package cz.propas.rabbitmq.consumer.consumers

import com.fasterxml.jackson.databind.ObjectMapper
import cz.propas.rabbitmq.entity.Picture
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile("picture-direct")
class PictureVectorConsumer(
    private val objectMapper: ObjectMapper) : AbstractConsumer() {

    @RabbitListener(queues = ["q.picture.vector"])
    override fun receiveMessage(message: Message) {
        val picture = objectMapper.readValue(message.body, Picture::class.java)
        receiveMessage("Vector", picture.toString())
    }
}