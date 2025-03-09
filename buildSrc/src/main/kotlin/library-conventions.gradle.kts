import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    id("java-library")
    id("kotlin-jvm-conventions")
    id("org.jetbrains.kotlin.plugin.spring")
}

dependencies {
    api(platform(SpringBootPlugin.BOM_COORDINATES))
}