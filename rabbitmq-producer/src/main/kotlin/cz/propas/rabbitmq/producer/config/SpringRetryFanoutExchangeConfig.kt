package cz.propas.rabbitmq.producer.config

import cz.propas.rabbitmq.constants.*
import org.springframework.amqp.core.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

const val ACCOUNTING_ROUTING_KEY = "dead-accounting"
const val MARKETING_ROUTING_KEY = "dead-marketing"

@Configuration
@Profile(SPRING_RETRY_FANOUT_EXCHANGE_PROFILE)
class SpringRetryFanoutExchangeConfig {

    @Bean
    fun workDeclarables(): Declarables {
        val exchange = FanoutExchange(SPRING_RETRY_FANOUT_EXCHANGE, false, true)
        val accountingArgs = mapOf(
            DEAD_LETTER_EXCHANGE_QUEUE_FEATURE to SPRING_RETRY_FANOUT_DLX_DIRECT_EXCHANGE,
            DEAD_LETTER_ROUTING_KEY_FEATURE to ACCOUNTING_ROUTING_KEY
        )

        val marketingArgs = mapOf(
            DEAD_LETTER_EXCHANGE_QUEUE_FEATURE to SPRING_RETRY_FANOUT_DLX_DIRECT_EXCHANGE,
            DEAD_LETTER_ROUTING_KEY_FEATURE to MARKETING_ROUTING_KEY
        )

        val accountingQueue = Queue(SPRING_HUMAN_RESOURCES_ACCOUNTING_QUEUE, false, false, true, accountingArgs)
        val marketingQueue = Queue(SPRING_HUMAN_RESOURCES_MARKETING_QUEUE, false, false, true, marketingArgs)

        return Declarables(
            exchange,
            accountingQueue,
            marketingQueue,
            BindingBuilder.bind(accountingQueue).to(exchange),
            BindingBuilder.bind(marketingQueue).to(exchange),
        )
    }

    @Bean
    fun deadDeclarables(): Declarables {
        val exchange = DirectExchange(SPRING_RETRY_FANOUT_DLX_DIRECT_EXCHANGE, false, true)

        val accountingQueue = Queue(SPRING_HUMAN_RESOURCES_ACCOUNTING_DLX_QUEUE, false, false, true)
        val marketingQueue = Queue(SPRING_HUMAN_RESOURCES_MARKETING_DLX_QUEUE, false, false, true)

        return Declarables(
            exchange,
            accountingQueue,
            marketingQueue,
            BindingBuilder.bind(accountingQueue).to(exchange).with(ACCOUNTING_ROUTING_KEY),
            BindingBuilder.bind(marketingQueue).to(exchange).with(MARKETING_ROUTING_KEY)
        )
    }
}
