import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    java
    jacoco
    id("org.springframework.boot") version "2.1.3.RELEASE"
    id("io.spring.dependency-management") version "1.0.6.RELEASE"
    id("com.bmuschko.docker-spring-boot-application") version "4.5.0"
}

val moduleName = "org.ulink.rest.api"

repositories {
    jcenter()
}

val junitVersion = "5.3.2"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    testImplementation("org.assertj:assertj-core:3.11.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

springBoot {
    mainClassName = "org.ulink.UlinkApplication"
    buildInfo()
}

docker {
    springBootApplication {
        baseImage.set("openjdk:11-jre-slim")
        ports.set(listOf(9090, 8080))
        tag.set("ulink:0.0.1")
    }
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = false
        csv.isEnabled = false
        html.destination = file("$buildDir/jacocoHtml")
    }
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