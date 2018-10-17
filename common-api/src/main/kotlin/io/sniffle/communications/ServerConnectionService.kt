package io.sniffle.communications

import io.sniffle.io.Reader
import io.sniffle.io.Writer
import io.sniffle.io.connection.Connection
import java.net.InetAddress

interface ServerConnectionService<T> {
    fun connect(inetAddress: InetAddress, port: Int, socketAcceptor: T): Connection
    fun connect(hostName: String, port: Int, socketAcceptor: T): Connection
    fun connect(port: Int, socketAcceptor: T): Connection

    fun getReader(connection: Connection): Reader
    fun getWriter(connection: Connection): Writer

    fun close(connection: Connection): Boolean
}