package cz.propas.rabbitmq.producer.config

import cz.propas.rabbitmq.constants.HUMAN_RESOURCES_ACCOUNTING_QUEUE
import cz.propas.rabbitmq.constants.HUMAN_RESOURCES_EXCHANGE
import cz.propas.rabbitmq.constants.HUMAN_RESOURCES_PROFILE
import cz.propas.rabbitmq.constants.HUMAN_RESOURCES_MARKETING_QUEUE
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.FanoutExchange
import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile(HUMAN_RESOURCES_PROFILE)
class HumanResourcesConfig {

    @Bean
    fun humanResourcesExchange(): FanoutExchange =
        FanoutExchange(HUMAN_RESOURCES_EXCHANGE, false, true)

    @Bean
    fun accountingQueue(): Queue =
        Queue(HUMAN_RESOURCES_ACCOUNTING_QUEUE, false, false, true)

    @Bean
    fun marketingQueue(): Queue =
        Queue(HUMAN_RESOURCES_MARKETING_QUEUE, false, false, true)

    @Bean
    fun accountingBinding(accountingQueue: Queue, hrExchange: FanoutExchange): Binding =
        BindingBuilder.bind(accountingQueue).to(hrExchange)

    @Bean
    fun marketingBinding(marketingQueue: Queue, hrExchange: FanoutExchange): Binding =
        BindingBuilder.bind(marketingQueue).to(hrExchange)
}