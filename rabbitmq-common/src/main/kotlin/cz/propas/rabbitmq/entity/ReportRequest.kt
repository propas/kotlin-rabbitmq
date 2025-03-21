package cz.propas.rabbitmq.entity

data class ReportRequest(
    var reportName: String,
    var isLarge: Boolean
)