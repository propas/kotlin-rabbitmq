package cz.propas.rabbitmq.consumer.consumers

import com.fasterxml.jackson.databind.ObjectMapper
import cz.propas.rabbitmq.entity.Employee
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile("employee-json")
class EmployeeJsonConsumer(
    private val objectMapper: ObjectMapper) : AbstractConsumer() {

    @RabbitListener(queues = ["course.employee"])
    override fun receiveMessage(message: String) {
        val employee = objectMapper.readValue(message, Employee::class.java)
        receiveMessage("Employee", employee.toString())
    }
}