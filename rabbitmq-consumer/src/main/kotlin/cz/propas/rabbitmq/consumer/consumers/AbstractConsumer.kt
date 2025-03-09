package cz.propas.rabbitmq.consumer.consumers

import org.slf4j.Logger
import org.slf4j.LoggerFactory

private val log: Logger = LoggerFactory.getLogger(FixedRateConsumer::class.java)

abstract class AbstractConsumer : Consumer {

    override fun receiveMessage(name: String, message: String) {
        log.info("Receive message: $message from consumer $name")
    }
}