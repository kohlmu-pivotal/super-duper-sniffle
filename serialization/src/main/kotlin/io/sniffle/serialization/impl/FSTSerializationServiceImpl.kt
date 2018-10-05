package io.sniffle.serialization.impl

import io.sniffle.serialization.SerializationService
import org.nustaq.serialization.FSTConfiguration

class FSTSerializationServiceImpl : SerializationService {

    private val fstConfiguration = FSTConfiguration.createDefaultConfiguration()

    override fun deserialize(binary: ByteArray): Any = fstConfiguration.asObject(binary)

    override fun serialize(sourceObject: Any): ByteArray = fstConfiguration.asByteArray(sourceObject)
}