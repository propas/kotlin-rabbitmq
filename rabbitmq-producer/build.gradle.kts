plugins {
    id("buildsrc.convention.kotlin-jvm")
    id("buildsrc.convention.spring-boot")
}

dependencies {
    implementation(project(":rabbitmq-common"))
    implementation(libs.spring.boot.starter.amqp)
    implementation(libs.bundles.jackson)
}

application {
    mainClass = "cz.propas.rabbitmq.producer.RabbitmqProducerApplicationKt"
}
