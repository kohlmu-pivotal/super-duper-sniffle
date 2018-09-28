package io.`super`.duper.sniffle.persistence.impl

import io.`super`.duper.sniffle.persistence.PersistenceService
import io.`super`.duper.sniffle.persistence.domain.PersistenceStoreEntry
import io.`super`.duper.sniffle.serialization.SerializationService

class BasicPersistenceImpl(private val path: String, private val name: String = "defaultFile.snf",
                           private val sizeInMB: Long = 1024, private val serializationService: SerializationService,
                           private val persistenceStore: PersistenceStore = loadOrCreateFileStore(path, name, sizeInMB)) : PersistenceService {


    //This pair is defined as <OffSet,ValueLength>
    private val keyIndexMap: HashMap<Any, Pair<Long, Int>>

    init {
        keyIndexMap = createIndexFromFileStore(persistenceStore)
    }

    private fun createIndexFromFileStore(persistenceStore: PersistenceStore): HashMap<Any, Pair<Long, Int>> {
        var currentOffset: Long = 0
        val fileStoreSize = persistenceStore.length
        val tempMap = HashMap<Any, Pair<Long, Int>>(100000)
        while (currentOffset < fileStoreSize) {
            val length = persistenceStore.readInt(currentOffset)
            val binaryObject = persistenceStore.read(currentOffset, length)
            val fileStoreEntry = serializationService.deserialize(binaryObject) as PersistenceStoreEntry
            val key = serializationService.deserialize(fileStoreEntry.key)
            tempMap[key] = Pair(currentOffset, length)
            currentOffset += length
        }
        return tempMap
    }

    override fun read(key: Any): ByteArray {
        val (offset, length) = getPairForKey(key)
        return if (length > 0) {
            persistenceStore.read(offset, length)
        } else {
            fail("No entry exists for key \"$key\"")
        }
    }

    override fun close() {
        persistenceStore.close()
    }

    private fun fail(errorMessage: String): Nothing = throw IllegalArgumentException(errorMessage)

    private fun getPairForKey(key: Any): Pair<Long, Int> = keyIndexMap[key] ?: Pair(-1L, 0)

    override fun write(key: Any, value: ByteArray) {
        val binaryKey = serializationService.serialize(key)
        val binaryEntry = serializationService.serialize(PersistenceStoreEntry(binaryKey, value))
        keyIndexMap[key] = persistenceStore.write(binaryEntry)
    }
}

internal fun loadOrCreateFileStore(path: String, name: String, sizeInMB: Long): PersistenceStore =
    PersistenceStore(path, name, sizeInMB)