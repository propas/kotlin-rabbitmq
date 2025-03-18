package cz.propas.rabbitmq.producer.config

import cz.propas.rabbitmq.constants.*
import org.springframework.amqp.core.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile(MY_PICTURE_DLX_PROFILE)
class MyPictureConfig {

    @Bean
    fun rabbitDeclarables(): Declarables {
        val mainExchange = FanoutExchange(MY_PICTURE_FANOUT_EXCHANGE, false, true)
        val dlxExchange = FanoutExchange(MY_PICTURE_DLX_FANOUT_EXCHANGE, false, true)

        val dlxQueue = Queue(MY_PICTURE_DLX_QUEUE, false, false, true)

        val imageQueue = QueueBuilder.durable(MY_PICTURE_IMAGE_QUEUE)
            .withArgument(DEAD_LETTER_EXCHANGE_QUEUE_FEATURE, MY_PICTURE_DLX_FANOUT_EXCHANGE)
            .build()

        val imageTtlQueue = QueueBuilder.durable(MY_PICTURE_IMAGE_TTL_QUEUE)
            .withArgument(DEAD_LETTER_EXCHANGE_QUEUE_FEATURE, MY_PICTURE_DLX_FANOUT_EXCHANGE)
            .withArgument(MESSAGE_TTL_QUEUE_FEATURE, 5000)
            .build()

        val bindings = listOf(
            BindingBuilder.bind(imageQueue).to(mainExchange),
            BindingBuilder.bind(imageTtlQueue).to(mainExchange),
            BindingBuilder.bind(dlxQueue).to(dlxExchange)
        )

        return Declarables(listOf(mainExchange, dlxExchange, dlxQueue, imageQueue, imageTtlQueue) + bindings)
    }
}