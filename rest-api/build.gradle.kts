import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    java
}

val moduleName = "org.ulink.rest.api"

val junitVersion = "5.3.2"

repositories {
    jcenter()
}

dependencies {
    testImplementation("org.assertj:assertj-core:3.11.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

//tasks.withType<JavaCompile> {
//    inputs.property("moduleName", ext["moduleName"])
//    doFirst {
//        options.compilerArgs = listOf(
//                "--module-path", classpath.asPath
//        )
//        classpath = files()
//    }
//}

tasks.withType<Test> {
    println("always printed: configuration phase test")

    useJUnitPlatform()

    testLogging {
        showStandardStreams = true
        // set options for log level LIFECYCLE
        events = setOf(TestLogEvent.FAILED,
                TestLogEvent.PASSED,
                TestLogEvent.SKIPPED,
                TestLogEvent.STANDARD_OUT)
        exceptionFormat = TestExceptionFormat.FULL
        showExceptions = true
        showCauses = true
        showStackTraces = true

        // set options for log level DEBUG and INFO
        debug {
            events = setOf(TestLogEvent.STARTED,
                    TestLogEvent.FAILED,
                    TestLogEvent.PASSED,
                    TestLogEvent.SKIPPED,
                    TestLogEvent.STANDARD_ERROR,
                    TestLogEvent.STANDARD_OUT)
            exceptionFormat = TestExceptionFormat.FULL
        }
        info.events = debug.events
        info.exceptionFormat = debug.exceptionFormat
    }

    doLast {
        println("only printed if executed: execution phase test")
    }

}