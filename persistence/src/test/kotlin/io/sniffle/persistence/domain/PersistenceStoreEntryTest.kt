package io.sniffle.persistence.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PersistenceStoreEntryTest {
    @Test
    fun testEqualsMethod() {
        val persistenceStoreEntry = PersistenceStoreEntry("This is a key".toByteArray(), "This is a value".toByteArray())
        Assertions.assertEquals(persistenceStoreEntry, persistenceStoreEntry)

        val persistenceStoreEntry1 = PersistenceStoreEntry("This is a key".toByteArray(), "This is a value1".toByteArray())
        Assertions.assertNotEquals(persistenceStoreEntry, persistenceStoreEntry1)

        val persistenceStoreEntry2 = PersistenceStoreEntry("This is a key2".toByteArray(), "This is a value".toByteArray())
        Assertions.assertNotEquals(persistenceStoreEntry, persistenceStoreEntry2)
    }
}