package io.`super`.duper.sniffle.persistence

interface PersistenceService {
    fun read(key: Any): ByteArray
    fun write(key: Any, value: ByteArray)
    fun close()
}