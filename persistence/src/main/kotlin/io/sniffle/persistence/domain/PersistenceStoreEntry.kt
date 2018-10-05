package io.sniffle.persistence.domain

import java.io.Serializable

data class PersistenceStoreEntry(val key: ByteArray, val value: ByteArray) : Serializable {
    override fun equals(other: Any?): Boolean {
        other as PersistenceStoreEntry
        return when {
            this === other -> true
            !key.contentEquals(other.key) -> false
            !value.contentEquals(other.value) -> false
            else -> true
        }
    }

    override fun hashCode(): Int = 31 * key.contentHashCode() + value.contentHashCode()
}