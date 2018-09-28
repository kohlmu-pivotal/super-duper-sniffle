package io.`super`.duper.sniffle.persistence.impl

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class SerializationUtil {
    companion object {
        fun serializeObject(objectToSerialize: Any): ByteArray {
            return ByteArrayOutputStream().use {
                val objectOutputStream = ObjectOutputStream(it)
                objectOutputStream.writeObject(objectToSerialize)
                it.toByteArray()
            }
        }

        fun deserializeObject(byteArrayToDeserialize: ByteArray): Any =
            ObjectInputStream(ByteArrayInputStream(byteArrayToDeserialize)).use { it.readObject() }
    }
}