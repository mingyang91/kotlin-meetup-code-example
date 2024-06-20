plugins {
    id("org.springframework.boot") version "3.2.5"
    kotlin("jvm") version "2.0.0"
}

group = "dev.mingyang"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter:3.3.0")
    implementation("org.springframework:spring-context:6.1.8")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}