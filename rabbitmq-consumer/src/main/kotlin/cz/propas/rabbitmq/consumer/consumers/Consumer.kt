package cz.propas.rabbitmq.consumer.consumers

interface Consumer {
    fun receiveMessage(message: String)
    fun receiveMessage(name: String, message: String)
}