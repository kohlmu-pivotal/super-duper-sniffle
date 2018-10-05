package io.sniffle.serialization

interface SerializationService {
    fun serialize(sourceObject: Any): ByteArray
    fun deserialize(binary: ByteArray): Any
}