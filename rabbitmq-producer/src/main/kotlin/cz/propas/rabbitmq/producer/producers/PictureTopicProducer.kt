package cz.propas.rabbitmq.producer.producers

import com.fasterxml.jackson.databind.ObjectMapper
import cz.propas.rabbitmq.entity.Picture
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import java.util.concurrent.ThreadLocalRandom

@Service
@Profile("picture-topic")
class PictureTopicProducer(
    rabbitTemplate: RabbitTemplate,
    objectMapper: ObjectMapper
) : AbstractProducer(rabbitTemplate, objectMapper) {

    private val log: Logger = LoggerFactory.getLogger(PictureTopicProducer::class.java)

    private val sources = listOf("mobile", "web", "desktop")
    private val types = listOf("jpg", "png", "svg")

    override fun sendMessage() {
        for (i in 1..10) {
            val picture = Picture(
                "Picture $i",
                types[i % types.size],
                sources[i % sources.size],
                ThreadLocalRandom.current().nextLong(1, 100000))

            val routingKey = listOf(
                picture.source,
                if (picture.size > 4000) "large" else "small",
                picture.type
            ).joinToString(".")

            log.info("Sending: $picture")
            sendMessage("x.picture2", routingKey, picture)
        }
    }
}