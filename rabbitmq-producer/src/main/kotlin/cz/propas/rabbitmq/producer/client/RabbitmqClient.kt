package cz.propas.rabbitmq.producer.client

import cz.propas.rabbitmq.entity.RabbitmqQueue
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.DefaultUriBuilderFactory
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.util.*

private const val RABBITMQ_QUEUES_API = "http://localhost:15672/api/queues"
private const val RABBITMQ_USER = "guest"
private const val RABBITMQ_PASSWORD = "guest"

@Service
class RabbitmqClient {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(RabbitmqClient::class.java)
    }

    private val webClient = WebClient.create(RABBITMQ_QUEUES_API)

    fun getAllQueues(): List<RabbitmqQueue>? = webClient.get()
        .header(HttpHeaders.AUTHORIZATION, createBasicAuthHeaders())
        .retrieve()
        .bodyToMono(object : ParameterizedTypeReference<List<RabbitmqQueue>>() {})
        .block(Duration.ofSeconds(10))

    fun purgeAllQueues() = getAllQueues()
            ?.filter { it.isDirty }
            ?.forEach { queue ->
                log.info("Queue ${queue.name} has ${queue.messages} unprocessed messages which will be deleted.")
                val encodedVhost = URLEncoder.encode(queue.vhost, StandardCharsets.UTF_8)

                val uriFactory = DefaultUriBuilderFactory("$RABBITMQ_QUEUES_API/$encodedVhost/${queue.name}/contents")
                uriFactory.encodingMode = DefaultUriBuilderFactory.EncodingMode.NONE

                webClient.method(HttpMethod.DELETE)
                    .uri(uriFactory.builder().build())
                    .header(HttpHeaders.AUTHORIZATION, createBasicAuthHeaders())
                    .retrieve()
                    .toBodilessEntity()
                    .block(Duration.ofSeconds(10))
            }

    private fun createBasicAuthHeaders(): String =
        "Basic " + Base64.getEncoder().encodeToString("$RABBITMQ_USER:$RABBITMQ_PASSWORD".toByteArray())
}
