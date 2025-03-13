package cz.propas.rabbitmq.producer.config

import cz.propas.rabbitmq.constants.HUMAN_RESOURCES_EXCHANGE
import cz.propas.rabbitmq.constants.MY_PICTURE_DLX_FANOUT_EXCHANGE
import cz.propas.rabbitmq.constants.MY_PICTURE_FANOUT_EXCHANGE
import cz.propas.rabbitmq.constants.PICTURE_DIRECT_EXCHANGE
import cz.propas.rabbitmq.constants.PICTURE_TOPIC_EXCHANGE
import cz.propas.rabbitmq.constants.PROMOTION_EXCHANGE
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.FanoutExchange
import org.springframework.amqp.core.HeadersExchange
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class ExchangesConfig {

    @Bean
    @Profile("fanout-exchange")
    fun hrFanoutExchange() =
        FanoutExchange(HUMAN_RESOURCES_EXCHANGE, false, true)

    @Bean
    @Profile("direct-exchange")
    fun pictureDirectExchange() =
        DirectExchange(PICTURE_DIRECT_EXCHANGE, false, true)

    @Bean
    @Profile("topic-exchange")
    fun pictureTopicExchange() =
        TopicExchange(PICTURE_TOPIC_EXCHANGE, false, true)

    @Bean
    @Profile("headers-exchange")
    fun promotionHeadersExchange() =
        HeadersExchange(PROMOTION_EXCHANGE, false, true)

    @Bean
    @Profile("dead-letter-exchange")
    fun myPictureFanoutExchange() =
        FanoutExchange(MY_PICTURE_FANOUT_EXCHANGE, false, true)

    @Bean
    @Profile("dead-letter-exchange")
    fun myPictureDlxFanoutExchange() =
        FanoutExchange(MY_PICTURE_DLX_FANOUT_EXCHANGE, false, true)
}