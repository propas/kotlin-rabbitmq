package cz.propas.rabbitmq.consumer.consumers

import com.fasterxml.jackson.databind.ObjectMapper
import cz.propas.rabbitmq.constants.*
import cz.propas.rabbitmq.entity.Employee
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
@Profile(SPRING_RETRY_FANOUT_EXCHANGE_PROFILE)
class SpringRetryFanoutEmployeeConsumer(
    private val objectMapper: ObjectMapper) : AbstractConsumer() {

    @RabbitListener(queues = [SPRING_HUMAN_RESOURCES_ACCOUNTING_QUEUE])
    fun receiveImageMessage(message: Message) {
        val employee = objectMapper.readValue(message.body, Employee::class.java)

        if (employee.name == "Employee 3") {
            throw IllegalArgumentException("Employee 3 is sick")
        }
        logMessage("Accounting", employee.toString())
    }

    @RabbitListener(queues = [SPRING_HUMAN_RESOURCES_MARKETING_QUEUE])
    fun receiveVectorMessage(message: Message) {
        val employee = objectMapper.readValue(message.body, Employee::class.java)
        logMessage("Marketing", employee.toString())
    }
}