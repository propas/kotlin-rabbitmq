import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    id("application")
    id("kotlin-jvm-conventions")
    id("org.springframework.boot")
    id("org.jetbrains.kotlin.plugin.spring")
}

dependencies {
    api(platform(SpringBootPlugin.BOM_COORDINATES))
}