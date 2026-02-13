val exposed_version: String by project
val h2_version: String by project
val koin_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val postgres_version: String by project
val ktor_version: String by project

plugins {
    kotlin("jvm") version "2.3.0"
    id("io.ktor.plugin") version "3.4.0"
}

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

kotlin {
    jvmToolchain(21)
}

repositories {
    mavenCentral()
}

subprojects {
    group = "com.shandy"
    version = "0.0.1"

    apply(plugin = "kotlin")
    apply(plugin = "application")
    apply(plugin = "io.ktor.plugin")

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("io.ktor:ktor-server-core")
        implementation("io.ktor:ktor-server-content-negotiation")
        implementation("io.ktor:ktor-serialization-jackson")
        implementation("io.ktor:ktor-serialization-kotlinx-json")
        implementation("io.ktor:ktor-server-di:$ktor_version")
        implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
        implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
        implementation ("org.jetbrains.exposed:exposed-dao:${exposed_version}")
        implementation("com.h2database:h2:$h2_version")
        implementation("org.postgresql:postgresql:$postgres_version")
//        implementation("io.insert-koin:koin-ktor:$koin_version")
        implementation("io.insert-koin:koin-logger-slf4j:$koin_version")
        implementation("io.ktor:ktor-server-netty")
        implementation("ch.qos.logback:logback-classic:$logback_version")
        implementation("io.ktor:ktor-server-config-yaml")
        testImplementation("io.ktor:ktor-server-test-host")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    }

}

//dependencies {
//
//}
