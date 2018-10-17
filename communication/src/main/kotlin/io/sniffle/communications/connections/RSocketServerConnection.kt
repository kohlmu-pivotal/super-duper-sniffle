package io.sniffle.communications.connections

import io.rsocket.RSocket
import io.sniffle.io.connection.Connection

class RSocketServerConnection(private val rsocket: RSocket) : Connection {
    override fun close() {
        rsocket.dispose()
    }
}