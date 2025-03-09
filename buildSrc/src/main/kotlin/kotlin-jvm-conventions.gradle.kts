import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("org.jetbrains.kotlin.jvm")
}

//https://github.com/gradle/gradle/issues/15383#issuecomment-779893192
val libs = extensions.getByType<LibrariesForLibs>()

kotlin {
    // Use a specific Java version to make it easier to work in different environments.
    jvmToolchain(libs.versions.java.get().toInt())
}

tasks.withType<Test>().configureEach {
    // Configure all test Gradle tasks to use JUnitPlatform.
    useJUnitPlatform()

    // Log information about all test results, not only the failed ones.
    testLogging {
        events(
            TestLogEvent.FAILED,
            TestLogEvent.PASSED,
            TestLogEvent.SKIPPED
        )
    }
}
