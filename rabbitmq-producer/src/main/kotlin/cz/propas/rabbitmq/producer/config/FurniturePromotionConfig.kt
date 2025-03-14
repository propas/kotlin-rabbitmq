package cz.propas.rabbitmq.producer.config

import cz.propas.rabbitmq.constants.*
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.HeadersExchange
import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

const val HEADER_COLOR = "color"
const val HEADER_MATERIAL = "material"

@Configuration
@Profile(FURNITURE_PROMOTION_PROFILE)
class FurniturePromotionConfig {

    @Bean
    fun promotionExchange(): HeadersExchange =
        HeadersExchange(PROMOTION_EXCHANGE, false, true)

    @Bean
    fun discountQueue(): Queue =
        Queue(PROMOTION_DISCOUNT_QUEUE, false, false, true)

    @Bean
    fun freeDeliveryQueue(): Queue =
        Queue(PROMOTION_FREE_DELIVERY_QUEUE, false, false, true)

    @Bean
    fun discountBindingRedSteel(discountQueue: Queue, exchange: HeadersExchange): Binding =
        BindingBuilder.bind(discountQueue)
            .to(exchange)
            .whereAll(
                mapOf(
                    HEADER_COLOR to "red",
                    HEADER_MATERIAL to "steel"
                )
            ).match()

    @Bean
    fun discountBindingWhiteWood(discountQueue: Queue, exchange: HeadersExchange): Binding =
        BindingBuilder.bind(discountQueue)
            .to(exchange)
            .whereAll(
                mapOf(
                    HEADER_COLOR to "white",
                    HEADER_MATERIAL to "wood"
                )
            ).match()

    @Bean
    fun freeDeliveryBinding(freeDeliveryQueue: Queue, exchange: HeadersExchange): Binding =
        BindingBuilder.bind(freeDeliveryQueue)
            .to(exchange)
            .whereAny(
                mapOf(
                    HEADER_COLOR to "red",
                    HEADER_MATERIAL to "steel"
                )
            ).match()
}