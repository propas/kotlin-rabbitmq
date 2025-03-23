package cz.propas.rabbitmq.producer.producers

import com.fasterxml.jackson.databind.ObjectMapper
import cz.propas.rabbitmq.constants.*
import cz.propas.rabbitmq.entity.Picture
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile(SPRING_RETRY_DIRECT_EXCHANGE_PROFILE)
class SpringRetryDirectProducer(
    rabbitTemplate: RabbitTemplate,
    objectMapper: ObjectMapper
) : AbstractProducer(rabbitTemplate, objectMapper) {

    private val log: Logger = LoggerFactory.getLogger(SpringRetryDirectProducer::class.java)

    override fun sendMessage() {
        val picture = Picture(
            "Spring Picture ",
            "jpg",
            "web",
            9500
        )

        log.info("Sending: $picture")
        sendMessage(SPRING_RETRY_DIRECT_EXCHANGE, picture.type, picture)
    }
}