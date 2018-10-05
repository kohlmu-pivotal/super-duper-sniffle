package io.sniffle.persistence.domain

import java.io.Serializable

data class PersistenceStoreEntry(val key: ByteArray, val value: ByteArray) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        other as PersistenceStoreEntry

        if (!key.contentEquals(other.key)) return false
        if (!value.contentEquals(other.value)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = key.contentHashCode()
        result = 31 * result + value.contentHashCode()
        return result
    }
}