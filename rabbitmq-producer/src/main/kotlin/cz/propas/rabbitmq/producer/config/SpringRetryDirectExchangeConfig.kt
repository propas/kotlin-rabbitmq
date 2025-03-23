package cz.propas.rabbitmq.producer.config

import cz.propas.rabbitmq.constants.*
import org.springframework.amqp.core.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile(SPRING_RETRY_DIRECT_EXCHANGE_PROFILE)
class SpringRetryDirectExchangeConfig {

    @Bean
    fun workDeclarables(): Declarables = createDeclarables(
        SPRING_RETRY_DIRECT_EXCHANGE,
        SPRING_PICTURE_IMAGE_QUEUE,
        SPRING_PICTURE_VECTOR_QUEUE,
        SPRING_RETRY_DLX_DIRECT_EXCHANGE
    )

    @Bean
    fun deadDeclarables(): Declarables = createDeclarables(
        SPRING_RETRY_DLX_DIRECT_EXCHANGE,
        SPRING_PICTURE_IMAGE_QUEUE_DLX,
        SPRING_PICTURE_VECTOR_QUEUE_DLX,
        null
    )

    private fun createDeclarables(exchangeName: String,
                                  imageQueueName: String,
                                  vectorQueueName: String,
                                  deadLetterExchange: String?): Declarables {

        val exchange = DirectExchange(exchangeName, false, true)

        val queueArgs = deadLetterExchange?.let {
            mapOf(DEAD_LETTER_EXCHANGE_QUEUE_FEATURE to it)
        } ?: emptyMap()

        val imageQueue = Queue(imageQueueName, false, false, true, queueArgs)
        val vectorQueue = Queue(vectorQueueName, false, false, true, queueArgs)

        return Declarables(
            exchange,
            imageQueue,
            vectorQueue,
            BindingBuilder.bind(imageQueue).to(exchange).with("png"),
            BindingBuilder.bind(imageQueue).to(exchange).with("jpg"),
            BindingBuilder.bind(vectorQueue).to(exchange).with("svg")
        )
    }
}
