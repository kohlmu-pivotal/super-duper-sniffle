package io.sniffle.serialization.impl

import io.sniffle.serialization.SerializationService
import io.vavr.control.Try
import org.nustaq.serialization.FSTConfiguration

class FSTSerializationServiceImpl : SerializationService {

    private val fstConfiguration = FSTConfiguration.createDefaultConfiguration()

    override fun deserialize(binary: ByteArray): Any =
            Try.of { fstConfiguration.asObject(binary) }.get()

    override fun serialize(sourceObject: Any): ByteArray =
            Try.of { fstConfiguration.asByteArray(sourceObject) }.get()
}