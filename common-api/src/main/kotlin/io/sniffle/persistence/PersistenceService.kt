package io.sniffle.persistence

interface PersistenceService {
    fun read(key: Any): ByteArray
    fun write(key: Any, value: ByteArray): Int
    fun close()
}