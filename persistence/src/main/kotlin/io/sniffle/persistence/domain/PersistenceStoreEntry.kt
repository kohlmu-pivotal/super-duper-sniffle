package io.sniffle.persistence.domain

import java.io.Serializable

data class PersistenceStoreEntry(val key: ByteArray, val value: ByteArray) : Serializable {
    override fun equals(other: Any?): Boolean {
        other as PersistenceStoreEntry
        return when {
            this === other -> true
            key.byteArraysEquals(other.key) -> value.byteArraysEquals(other.value)
            else -> false
        }
    }

    override fun hashCode(): Int = 31 * key.contentHashCode() + value.contentHashCode()
}

internal fun ByteArray.byteArraysEquals(otherByteArray: ByteArray): Boolean {
    val arraySize = this.size
    if (arraySize == otherByteArray.size) {
        var isEqual = true
        if (arraySize > 25) {
            for (i in 0..5) {
                isEqual = isEqual and (this[i] == otherByteArray[i])
            }
            if (isEqual) {
                for (i in arraySize..(arraySize - 5)) {
                    isEqual = isEqual and (this[i] == otherByteArray[i])
                }
            }
        } else {
            for (i in 0..(arraySize - 1)) {
                isEqual = isEqual and (this[i] == otherByteArray[i])
            }
        }
        return isEqual
    } else {
        return false
    }
}