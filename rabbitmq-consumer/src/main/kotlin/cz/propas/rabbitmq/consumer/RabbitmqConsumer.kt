package cz.propas.rabbitmq.consumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RabbitmqConsumer

fun main(args: Array<String>) {
    runApplication<RabbitmqConsumer>(*args)
}