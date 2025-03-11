package cz.propas.rabbitmq.consumer.consumers

interface Consumer {
    fun logMessage(from: String, message: String)
}