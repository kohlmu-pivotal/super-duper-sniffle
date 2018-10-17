package io.sniffle.communications

import io.sniffle.io.Reader
import io.sniffle.io.Writer
import io.sniffle.io.connection.Connection
import java.net.InetAddress

interface ClientConnectionService<T> {
    fun bind(inetAddress: InetAddress, port: Int, socketAcceptor: T): Connection
    fun bind(hostName: String, port: Int, socketAcceptor: T): Connection
    fun bind(port: Int, socketAcceptor: T): Connection

    fun getReader(connection: Connection): Reader
    fun getWriter(connection: Connection): Writer

    fun close(connection: Connection): Boolean
}