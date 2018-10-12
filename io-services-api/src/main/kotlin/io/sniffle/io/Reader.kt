package io.sniffle.io

import io.vavr.control.Either

interface Reader {
    fun read(byteArray: ByteArray): Either<ByteArray, Error>
}