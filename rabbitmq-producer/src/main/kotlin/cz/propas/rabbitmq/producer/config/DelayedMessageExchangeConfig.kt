package cz.propas.rabbitmq.producer.config

import cz.propas.rabbitmq.constants.DELAYED_MESSAGE_EXCHANGE
import cz.propas.rabbitmq.constants.DELAYED_MESSAGE_EXCHANGE_PROFILE
import cz.propas.rabbitmq.constants.DELAYED_MESSAGE_QUEUE
import cz.propas.rabbitmq.constants.DELAYED_ROUTING_KEY
import org.springframework.amqp.core.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile(DELAYED_MESSAGE_EXCHANGE_PROFILE)
class DelayedMessageExchangeConfig {

    @Bean
    fun delayedExchange(): Exchange =
        ExchangeBuilder(DELAYED_MESSAGE_EXCHANGE, "x-delayed-message")
            .durable(false)
            .autoDelete()
            .withArgument("x-delayed-type", "direct")
            .build()

    @Bean
    fun queue(): Queue = Queue(DELAYED_MESSAGE_QUEUE, false, false, true)

    @Bean
    fun binding(queue: Queue, delayedExchange: Exchange): Binding =
        BindingBuilder
            .bind(queue)
            .to(delayedExchange)
            .with(DELAYED_ROUTING_KEY)
            .noargs()
}