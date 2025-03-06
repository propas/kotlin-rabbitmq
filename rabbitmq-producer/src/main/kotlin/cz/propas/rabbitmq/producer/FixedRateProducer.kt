package cz.propas.rabbitmq.producer

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
@Profile("fixed-rate")
class FixedRateProducer(
    rabbitTemplate: RabbitTemplate
) : AbstractProducer(rabbitTemplate) {
    private var i = 0

    @Scheduled(fixedRate = 500)
    override fun sendMessage() {
        i++
        log.info("Counter is : $i")
        sendMessage("course.fixedrate", "Fix rate: $i")
    }
}