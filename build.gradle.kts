plugins {
    base
    java
    jacoco
    checkstyle
    id("com.github.spotbugs") version "1.6.9"
    id("org.sonarqube") version "2.6.2"
}

allprojects {
    group = "org.ulink"
    version = "1.0-SNAPSHOT"

    repositories {
        jcenter()
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

jacoco {
    toolVersion = "0.8.2"
    reportsDir = file("$buildDir/customJacocoReportDir")
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = false
        csv.isEnabled = false
        html.destination = file("${buildDir}/jacocoHtml")
    }
}

//tasks.withType<JacocoReport> {
//    reports {
//        xml.isEnabled = false
//        csv.isEnabled = false
//        html.apply {
//            isEnabled = true
//            destination = file("$buildDir/jacocoHtml")
//        }
//        executionData(tasks.withType<Test>())
//    }
//}

checkstyle {
    toolVersion = "8.16"
}

spotbugs {
    toolVersion = "3.1.10"
}

sonarqube {
    properties {
        System.setProperty("sonar.login", System.getenv("SONAR_KEY"))
    }
}

tasks.withType<Wrapper> {
    gradleVersion = "5.1.1"
    distributionType = Wrapper.DistributionType.ALL
}
