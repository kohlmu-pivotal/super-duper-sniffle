package io.sniffle.communications

import io.sniffle.io.Reader
import io.sniffle.io.Writer
import io.sniffle.io.connection.Connection
import io.vavr.control.Either
import java.net.InetAddress

interface ConnectionService {
    fun connect(inetAddress: InetAddress): Either<Connection, Error>
    fun connect(inetAddress: InetAddress, port: Int): Either<Connection, Error>
    fun connect(hostName: String, port: Int): Either<Connection, Error>
    fun connect(port: Int): Either<Connection, Error>

    fun getReader(connection: Connection): Either<Reader, Error>
    fun getWriter(connection: Connection): Either<Writer, Error>

    fun close(connection: Connection): Either<Boolean, Error>
}