import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    idea
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile(kotlin("reflect"))
    api(project(":common-api"))

    testImplementation("io.mockk:mockk:1.8.8")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.2.0")
}