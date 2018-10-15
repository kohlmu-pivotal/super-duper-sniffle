package io.sniffle.communications.impl

import io.rsocket.kotlin.transport.netty.server.TcpServerTransport
import io.rsocket.transport.Transport
import io.sniffle.io.connection.Connection

class RSocketConnection(private val transport:Transport) : Connection {
    override fun close() {

    }
}