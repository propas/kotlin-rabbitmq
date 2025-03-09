package cz.propas.rabbitmq.producer

import cz.propas.rabbitmq.producer.producers.Producer
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

fun main(args: Array<String>) {
    runApplication<RabbitmqProducer>(*args)
}

@SpringBootApplication
class RabbitmqProducer (
    private val producer: Producer
): CommandLineRunner {

    override fun run(vararg args: String?) {
        producer.sendMessage()
    }
}

