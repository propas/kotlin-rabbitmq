package cz.propas.rabbitmq.consumer.consumers

import cz.propas.rabbitmq.constants.FIXED_RATE_PROFILE
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.TimeUnit

@Service
@Profile(FIXED_RATE_PROFILE)
class FixedRateConsumer : AbstractConsumer() {

    @RabbitListener(queues = ["fixedrate"], concurrency = "3-7")
    fun receiveMessage(message: String) {
        logMessage("FixedRate", message)
        TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextLong(2000))
    }
}