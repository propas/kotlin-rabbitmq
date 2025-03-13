package cz.propas.rabbitmq.consumer.consumers.hr

import cz.propas.rabbitmq.constants.HUMAN_RESOURCES_ACCOUNTING_QUEUE
import com.fasterxml.jackson.databind.ObjectMapper
import cz.propas.rabbitmq.constants.HUMAN_RESOURCES_PROFILE
import cz.propas.rabbitmq.consumer.consumers.AbstractConsumer
import cz.propas.rabbitmq.entity.Employee
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile(HUMAN_RESOURCES_PROFILE)
class AccountingConsumer(
    private val objectMapper: ObjectMapper) : AbstractConsumer() {

    @RabbitListener(queues = [HUMAN_RESOURCES_ACCOUNTING_QUEUE])
    fun receiveMessage(message: Message) {
        val employee = objectMapper.readValue(message.body, Employee::class.java)
        logMessage("Accounting", employee.toString())
    }
}