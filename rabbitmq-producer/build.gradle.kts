plugins {
    id("application-conventions")
}

dependencies {
    implementation(project(":rabbitmq-common"))
    implementation(libs.spring.boot.starter.amqp)
    implementation(libs.spring.boot.starter.webflux)
    implementation(libs.bundles.jacksons)
}

application {
    mainClass = "cz.propas.rabbitmq.producer.RabbitmqProducerApplicationKt"
}
