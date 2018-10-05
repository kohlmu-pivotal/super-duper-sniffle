import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "io.sniffle"
version = "1.0-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.3.0-rc-146"
    idea
}

allprojects {
    repositories {
        mavenCentral()
        maven(url = "http://dl.bintray.com/kotlin/kotlin-eap")
        maven(url = "https://plugins.gradle.org/m2/")
        maven(url = "https://kotlin.bintray.com/kotlinx")
        maven(url = "https://bintray.com/kotlin/kotlin-eap")
    }
    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions.languageVersion = "1.3"
    }
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile(kotlin("reflect"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.languageVersion = "1.3"
}