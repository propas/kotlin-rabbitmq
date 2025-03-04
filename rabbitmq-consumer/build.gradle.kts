plugins {
    id("buildsrc.convention.kotlin-jvm")
    id("buildsrc.convention.spring-boot")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-amqp")
}

application {
    mainClass = "cz.propas.rabbitmq.consumer.RabbitmqConsumerApplicationKt"
}
