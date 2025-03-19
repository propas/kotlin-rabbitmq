package cz.propas.rabbitmq.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class RabbitmqQueue(
    var messages: Long = 0,
    var name: String = "",
    var vhost: String = "/"
) {
    val isDirty: Boolean
        get() = messages > 0
}
