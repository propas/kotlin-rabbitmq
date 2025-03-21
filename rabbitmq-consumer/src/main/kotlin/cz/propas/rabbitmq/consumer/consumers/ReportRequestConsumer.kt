package cz.propas.rabbitmq.consumer.consumers

import com.fasterxml.jackson.databind.ObjectMapper
import cz.propas.rabbitmq.constants.DELAYED_MESSAGE_EXCHANGE_PROFILE
import cz.propas.rabbitmq.constants.DELAYED_MESSAGE_QUEUE
import cz.propas.rabbitmq.entity.ReportRequest
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile(DELAYED_MESSAGE_EXCHANGE_PROFILE)
class ReportRequestConsumer(
    private val objectMapper: ObjectMapper) : AbstractConsumer() {

    @RabbitListener(queues = [DELAYED_MESSAGE_QUEUE])
    fun receiveMessage(message: Message) {
        val reportRequest = objectMapper.readValue(message.body, ReportRequest::class.java)
        logMessage("Report", reportRequest.toString())
    }
}