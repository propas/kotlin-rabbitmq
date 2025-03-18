package cz.propas.rabbitmq.producer.config

import cz.propas.rabbitmq.constants.HUMAN_RESOURCES_ACCOUNTING_QUEUE
import cz.propas.rabbitmq.constants.HUMAN_RESOURCES_EXCHANGE
import cz.propas.rabbitmq.constants.HUMAN_RESOURCES_PROFILE
import cz.propas.rabbitmq.constants.HUMAN_RESOURCES_MARKETING_QUEUE
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Declarables
import org.springframework.amqp.core.FanoutExchange
import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile(HUMAN_RESOURCES_PROFILE)
class HumanResourcesConfig {

    @Bean
    fun humanResourcesDeclarables(): Declarables {

        val exchange = FanoutExchange(HUMAN_RESOURCES_EXCHANGE, false, true)
        val queues = listOf(
            Queue(HUMAN_RESOURCES_ACCOUNTING_QUEUE, false, false, true),
            Queue(HUMAN_RESOURCES_MARKETING_QUEUE, false, false, true)
        )

        val bindings = queues.map { queue -> BindingBuilder.bind(queue).to(exchange) }

        return Declarables(listOf(exchange) + queues + bindings)
    }
}