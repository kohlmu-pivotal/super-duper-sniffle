package io.sniffle.io

import io.vavr.control.Either

interface Writer {
    fun write(byteArray: ByteArray): Either<Int, Error>
}