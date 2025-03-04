package cz.propas.rabbitmq.producer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RabbitmqProducer

fun main(args: Array<String>) {
    runApplication<RabbitmqProducer>(*args)
}