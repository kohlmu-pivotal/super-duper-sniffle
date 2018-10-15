package io.sniffle.communications

import io.sniffle.io.Reader
import io.sniffle.io.Writer
import io.sniffle.io.connection.Connection
import io.vavr.control.Either
import java.net.InetAddress

interface ConnectionService {
    fun connect(inetAddress: InetAddress): Connection
    fun connect(inetAddress: InetAddress, port: Int): Connection
    fun connect(hostName: String, port: Int): Connection
    fun connect(port: Int): Connection

    fun getReader(connection: Connection): Reader
    fun getWriter(connection: Connection): Writer

    fun close(connection: Connection): Boolean
}