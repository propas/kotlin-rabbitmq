package cz.propas.rabbitmq.producer.producers

interface Producer {
    fun sendMessage()
    fun sendMessage(queue: String, message: String)
    fun <T : Any> sendMessage(routingKey: String, data: T)
    fun <T : Any> sendMessage(exchange: String, routingKey: String, data: T)
}