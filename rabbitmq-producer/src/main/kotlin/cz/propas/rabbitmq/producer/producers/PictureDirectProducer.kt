package cz.propas.rabbitmq.producer.producers

import cz.propas.rabbitmq.constants.PICTURE_DIRECT_EXCHANGE
import com.fasterxml.jackson.databind.ObjectMapper
import cz.propas.rabbitmq.constants.PICTURE_DIRECT_EXCHANGE_PROFILE
import cz.propas.rabbitmq.entity.Picture
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import java.util.concurrent.ThreadLocalRandom

@Service
@Profile(PICTURE_DIRECT_EXCHANGE_PROFILE)
class PictureDirectProducer(
    rabbitTemplate: RabbitTemplate,
    objectMapper: ObjectMapper
) : AbstractProducer(rabbitTemplate, objectMapper) {

    private val log: Logger = LoggerFactory.getLogger(PictureDirectProducer::class.java)

    private val sources = listOf("mobile", "web", "desktop")
    private val types = listOf("jpg", "png", "svg")

    override fun sendMessage() {
        for (i in 1..10) {
            val picture = Picture(
                "Picture $i",
                types[i % types.size],
                sources[i % sources.size],
                ThreadLocalRandom.current().nextLong(1, 100000))

            log.info("Sending: $picture")
            sendMessage(PICTURE_DIRECT_EXCHANGE, picture.type, picture)
        }
    }
}