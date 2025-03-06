package cz.propas.rabbitmq.consumer

interface Consumer {
    fun receiveMessage(message: String)
    fun receiveMessage(name: String, message: String)
}