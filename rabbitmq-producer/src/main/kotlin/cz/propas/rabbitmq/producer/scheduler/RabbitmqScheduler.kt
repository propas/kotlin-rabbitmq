package cz.propas.rabbitmq.producer.scheduler

import cz.propas.rabbitmq.producer.client.RabbitmqClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
@EnableScheduling
class RabbitmqScheduler(private val rabbitmqClient: RabbitmqClient) {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(RabbitmqScheduler::class.java)
    }

    @Scheduled(fixedDelay = 60000)
    fun sweepDirtyQueues() = runCatching {
        rabbitmqClient.purgeAllQueues()
    }.onFailure { log.warn("Cannot sweep queues: ${it.message}") }
}
