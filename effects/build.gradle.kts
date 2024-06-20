plugins {
    kotlin("jvm") version "2.0.0"
}

group = "dev.mingyang"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:3.0.0-beta-1")
    implementation("io.ktor:ktor-server-cio:3.0.0-beta-1")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    compilerOptions {
        freeCompilerArgs = listOf("-Xcontext-receivers")
    }
    jvmToolchain(21)
}
