rootProject.name = "super-duper-sniffle"

include(
    "serialization",
    "persistence",
    "membership",
    "datastore",
    "common",
    "persistence"
)

pluginManagement {
    repositories {
        mavenCentral()
        maven(url = "http://dl.bintray.com/kotlin/kotlin-eap")
        maven(url = "https://plugins.gradle.org/m2/")
        maven(url = "https://kotlin.bintray.com/kotlinx")
    }
}