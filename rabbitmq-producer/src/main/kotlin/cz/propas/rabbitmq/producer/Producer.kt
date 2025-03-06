package cz.propas.rabbitmq.producer

interface Producer {
    fun sendMessage()
    fun sendMessage(queue: String, message: String)
}