package cz.propas.rabbitmq.producer.producers

import com.fasterxml.jackson.databind.ObjectMapper
import cz.propas.rabbitmq.constants.FIXED_RATE_PROFILE
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.annotation.Profile
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

private val log: Logger = LoggerFactory.getLogger(FixedRateProducer::class.java)

@Service
@EnableScheduling
@Profile(FIXED_RATE_PROFILE)
class FixedRateProducer(
    rabbitTemplate: RabbitTemplate,
    objectMapper: ObjectMapper
) : AbstractProducer(rabbitTemplate, objectMapper) {
    private var i = 0

    @Scheduled(fixedRate = 500)
    override fun sendMessage() {
        i++
        log.info("Counter is : $i")
        sendMessage("fixedrate", "Fix rate: $i")
    }
}