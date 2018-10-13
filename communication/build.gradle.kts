import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    idea
}

dependencies {
    api(project(":common-api"))
    api(project(":io-services-api"))

    compile(kotlin("stdlib-jdk8"))
    compile(kotlin("reflect"))

    compile("io.rsocket.kotlin:rsocket-core:0.9-SNAPSHOT")
    compile("io.rsocket.kotlin:rsocket-transport-netty:0.9-SNAPSHOT")
    compile("io.rsocket:rsocket-transport-aeron:0.11.8")
    compile("io.rsocket:rsocket-transport-local:0.11.8")

    testImplementation("io.mockk:mockk:1.8.8")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.2.0")

}

repositories {
    maven(url = "https://oss.jfrog.org/libs-snapshot")
}