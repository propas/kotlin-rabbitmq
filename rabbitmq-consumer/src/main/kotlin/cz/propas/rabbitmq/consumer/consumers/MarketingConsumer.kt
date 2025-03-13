package cz.propas.rabbitmq.consumer.consumers

import com.fasterxml.jackson.databind.ObjectMapper
import cz.propas.rabbitmq.entity.Employee
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile("fanout-exchange")
class MarketingConsumer(
    private val objectMapper: ObjectMapper) : AbstractConsumer() {

    @RabbitListener(queues = ["q.hr.marketing"])
    fun receiveMessage(message: Message) {
        val employee = objectMapper.readValue(message.body, Employee::class.java)
        logMessage("Marketing", employee.toString())
    }
}