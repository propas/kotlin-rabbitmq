package cz.propas.rabbitmq.entity

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

data class Employee(
    val id: Long,
    val name: String,
    @JsonFormat(pattern = "yyyy-MM-dd")
    val birthday: LocalDate
)
