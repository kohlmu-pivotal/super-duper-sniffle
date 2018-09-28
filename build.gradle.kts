import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "io.super.duper.sniffle"
version = "1.0-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.2.71"
    idea
}

allprojects {
    repositories {
        mavenCentral()
        maven(url = "http://dl.bintray.com/kotlin/kotlin-eap")
        maven(url = "https://plugins.gradle.org/m2/")
        maven(url = "https://kotlin.bintray.com/kotlinx")
    }
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile(kotlin("reflect"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.languageVersion = "1.2"
}