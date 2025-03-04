package buildsrc.convention

import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    id("application")
    id("org.springframework.boot")
    id("org.jetbrains.kotlin.plugin.spring")
}

dependencies {
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))
}