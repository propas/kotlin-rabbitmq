package cz.propas.rabbitmq.producer.producers

import com.fasterxml.jackson.databind.ObjectMapper
import cz.propas.rabbitmq.constants.DELAYED_MESSAGE_EXCHANGE
import cz.propas.rabbitmq.constants.DELAYED_MESSAGE_EXCHANGE_PROFILE
import cz.propas.rabbitmq.constants.DELAYED_ROUTING_KEY
import cz.propas.rabbitmq.entity.ReportRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.MessageProperties
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile(DELAYED_MESSAGE_EXCHANGE_PROFILE)
class ReportRequestProducer(
    rabbitTemplate: RabbitTemplate,
    objectMapper: ObjectMapper
) : AbstractProducer(rabbitTemplate, objectMapper) {

    private val log: Logger = LoggerFactory.getLogger(ReportRequestProducer::class.java)

    override fun sendMessage() {
        val largeReportDelay = 2 * 60 * 1000L

        (1..4).forEach { i ->
            val isLargeReport = i % 2 == 0
            val reportRequest = ReportRequest("Report $i", isLargeReport)

            val delayInMillis = if (isLargeReport) largeReportDelay else 0L

            val messageProperties = MessageProperties().apply {
                setHeader("x-delay", delayInMillis.toString())
            }

            log.info("Sending: $reportRequest with delay $delayInMillis ms")

            sendMessage(DELAYED_MESSAGE_EXCHANGE, DELAYED_ROUTING_KEY, messageProperties, reportRequest)
        }
    }
}