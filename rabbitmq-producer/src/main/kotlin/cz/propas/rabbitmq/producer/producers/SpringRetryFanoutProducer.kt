package cz.propas.rabbitmq.producer.producers

import com.fasterxml.jackson.databind.ObjectMapper
import cz.propas.rabbitmq.constants.SPRING_RETRY_FANOUT_EXCHANGE
import cz.propas.rabbitmq.constants.SPRING_RETRY_FANOUT_EXCHANGE_PROFILE
import cz.propas.rabbitmq.entity.Employee
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
@Profile(SPRING_RETRY_FANOUT_EXCHANGE_PROFILE)
class SpringRetryFanoutProducer(
    rabbitTemplate: RabbitTemplate,
    objectMapper: ObjectMapper
) : AbstractProducer(rabbitTemplate, objectMapper) {

    private val log: Logger = LoggerFactory.getLogger(SpringRetryFanoutProducer::class.java)

    override fun sendMessage() {
        for (i in 1..5) {
            val employee = Employee(i.toLong(), "Employee $i", LocalDate.now())
            log.info("Sending: $employee")
            sendMessage(SPRING_RETRY_FANOUT_EXCHANGE, "", employee)
        }
    }
}