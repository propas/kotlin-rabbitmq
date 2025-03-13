package cz.propas.rabbitmq.producer.producers

import cz.propas.rabbitmq.constants.PROMOTION_EXCHANGE
import com.fasterxml.jackson.databind.ObjectMapper
import cz.propas.rabbitmq.constants.FURNITURE_PROMOTION_PROFILE
import cz.propas.rabbitmq.entity.Furniture
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.MessageProperties
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile(FURNITURE_PROMOTION_PROFILE)
class FurniturePromotionProducer(
    rabbitTemplate: RabbitTemplate,
    objectMapper: ObjectMapper
) : AbstractProducer(rabbitTemplate, objectMapper) {

    private val log: Logger = LoggerFactory.getLogger(FurniturePromotionProducer::class.java)

    private val colors = listOf("white", "red", "green")
    private val materials = listOf("wood", "plastic", "steel")

    override fun sendMessage() {

        for (i in 0 until 10) {
            val furniture = Furniture(
                "Furniture $i",
                colors[i % colors.size],
                materials[i % materials.size],
                i)

            val messageProperties = MessageProperties()
            messageProperties.setHeader("color", furniture.color)
            messageProperties.setHeader("material", furniture.material)

            log.info("Sending: $furniture")
            sendMessage(PROMOTION_EXCHANGE, "", messageProperties, furniture)
        }
    }
}