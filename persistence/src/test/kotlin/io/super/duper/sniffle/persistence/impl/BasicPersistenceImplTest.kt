package io.`super`.duper.sniffle.persistence.impl

import io.`super`.duper.sniffle.persistence.store.PersistenceStore
import io.`super`.duper.sniffle.persistence.util.SerializationUtil
import io.`super`.duper.sniffle.serialization.SerializationService
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.slot
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.io.File

@ExtendWith(MockKExtension::class)
class BasicPersistenceImplTest {

    @MockK(relaxed = true)
    private lateinit var persistenceStore: PersistenceStore

    @MockK
    private lateinit var serializationService: SerializationService

    @BeforeEach
    @AfterEach
    fun clear() {
        File("/tmp/.lock").delete()
        File("/tmp/somePersFile.snf").delete()
    }

    @Test
    fun tesBasicPersistence() {

        val serializationSlot = slot<Any>()
        val deserializationSlot = slot<ByteArray>()
        val persistenceValueByteArraySlot = slot<ByteArray>()

        every { serializationService.serialize(capture(serializationSlot)) } answers { SerializationUtil.serializeObject(serializationSlot.captured) }
        every { serializationService.deserialize(capture(deserializationSlot)) } answers { SerializationUtil.deserializeObject(deserializationSlot.captured) }

        every { persistenceStore.write(capture(persistenceValueByteArraySlot)) } answers { Pair(0L, persistenceValueByteArraySlot.captured.size + 4) }
        every { persistenceStore.read(any(), any()) } answers { "Some Value Entry".toByteArray() }

        val basicPersistenceImpl = BasicPersistenceImpl("/tmp", "somePersFile.snf", 8L,
            serializationService, persistenceStore)
        basicPersistenceImpl.write("Some Key Value", "Some Value Entry".toByteArray())

        val readValue = basicPersistenceImpl.read("Some Key Value")
        Assertions.assertEquals("Some Value Entry", String(readValue))
    }

}