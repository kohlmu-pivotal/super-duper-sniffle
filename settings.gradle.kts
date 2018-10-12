rootProject.name = "super-duper-sniffle"

include(
        "serialization",
        "persistence",
        "communication",
        "membership",
        "datastore",
        "common-api",
        "io-services-api"
)

pluginManagement {
    repositories {
        mavenCentral()
        maven(url = "http://dl.bintray.com/kotlin/kotlin-eap")
        maven(url = "https://plugins.gradle.org/m2/")
        maven(url = "https://kotlin.bintray.com/kotlinx")
        maven(url = "https://bintray.com/kotlin/kotlin-eap")
    }
}