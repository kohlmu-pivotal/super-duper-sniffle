package io.`super`.duper.sniffle.persistence.impl

import io.`super`.duper.sniffle.persistence.domain.PersistenceStoreEntry
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

class PersistenceStoreTest {

    @BeforeEach
    @AfterEach
    fun clear() {
        File("/tmp/.lock").delete()
        File("/tmp/someFile").delete()
    }

    @Test
    fun testFileCreation() {
        val persistenceStore = PersistenceStore("/tmp", "someFile", 8L)
        persistenceStore.close()
    }

    @Test
    fun testFileCreationFailure() {
        val lockFile = File("/tmp/.lock").apply { createNewFile() }
        assertThrows(IllegalArgumentException::class.java) { PersistenceStore("/tmp", "someFile", 8L, lockFile) }
    }

    @Test
    fun testWriteToFile() {
        val (persistenceStore) = createPersistentStoreWithOneEntry()
        persistenceStore.close()
    }

    @Test
    fun testReadFromFile() {
        var (persistenceStore, expectedByteArray) = createPersistentStoreWithOneEntry()
        persistenceStore.close()

        persistenceStore = PersistenceStore("/tmp", "someFile", 8L)
        val lengthOfObject = persistenceStore.readInt(0)
        assertEquals(lengthOfObject, expectedByteArray.size)

        val readByteArray = persistenceStore.read(4, lengthOfObject)
        assertArrayEquals(expectedByteArray, readByteArray)

        persistenceStore.close()
    }

    private fun createPersistentStoreWithOneEntry(): Pair<PersistenceStore, ByteArray> {
        val persistenceStore = PersistenceStore("/tmp", "someFile", 8L)
        val expectedByteArray = SerializationUtil.serializeObject(PersistenceStoreEntry("This is a key".toByteArray(), "This is a value".toByteArray()))
        val (_, length) = persistenceStore.write(expectedByteArray)
        assertEquals(expectedByteArray.size, length)
        return Pair(persistenceStore, expectedByteArray)
    }
}