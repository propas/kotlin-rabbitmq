package cz.propas.rabbitmq.producer.producers

import cz.propas.rabbitmq.constants.HUMAN_RESOURCES_EXCHANGE
import com.fasterxml.jackson.databind.ObjectMapper
import cz.propas.rabbitmq.constants.HUMAN_RESOURCES_PROFILE
import cz.propas.rabbitmq.entity.Employee
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
@Profile(HUMAN_RESOURCES_PROFILE)
class HumanResourcesProducer(
    rabbitTemplate: RabbitTemplate,
    objectMapper: ObjectMapper
) : AbstractProducer(rabbitTemplate, objectMapper) {

    private val log: Logger = LoggerFactory.getLogger(HumanResourcesProducer::class.java)

    override fun sendMessage() {
        for (i in 1..5) {
            val employee = Employee(i.toLong(), "Employee $i", LocalDate.now())
            log.info("Sending: $employee")
            sendMessage(HUMAN_RESOURCES_EXCHANGE, "", employee)
        }
    }
}