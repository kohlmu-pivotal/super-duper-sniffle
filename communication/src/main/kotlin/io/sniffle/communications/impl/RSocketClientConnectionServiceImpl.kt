package io.sniffle.communications.impl

import io.rsocket.RSocket
import io.rsocket.RSocketFactory
import io.rsocket.SocketAcceptor
import io.rsocket.transport.ClientTransport
import io.sniffle.communications.ClientConnectionService
import io.sniffle.communications.connections.RSocketClientConnection
import io.sniffle.io.Reader
import io.sniffle.io.Writer
import io.sniffle.io.connection.Connection
import java.net.InetAddress

abstract class RSocketClientConnectionServiceImpl : ClientConnectionService<SocketAcceptor> {
    private lateinit var rsocketClientConnection: RSocketClientConnection

    override fun bind(inetAddress: InetAddress, port: Int, socketAcceptor: SocketAcceptor): Connection {
        rsocketClientConnection = RSocketClientConnection(
            createRSocket(
                getClientTransport(
                    inetAddress,
                    port
                )
            )
        )
        return rsocketClientConnection
    }

    override fun bind(hostName: String, port: Int, socketAcceptor: SocketAcceptor): Connection {
        rsocketClientConnection = RSocketClientConnection(
            createRSocket(
                getClientTransport(
                    hostName,
                    port
                )
            )
        )
        return rsocketClientConnection
    }

    override fun bind(port: Int, socketAcceptor: SocketAcceptor): Connection {
        rsocketClientConnection =
                RSocketClientConnection(createRSocket(getClientTransport(port)))
        return rsocketClientConnection
    }

    private fun createRSocket(transport: ClientTransport): RSocket =
        RSocketFactory
            .connect()
            .transport(transport)
            .start()
            .block()!!

    protected abstract fun getClientTransport(port: Int): ClientTransport
    protected abstract fun getClientTransport(inetAddress: InetAddress, port: Int): ClientTransport
    protected abstract fun getClientTransport(hostName: String, port: Int): ClientTransport

    override fun getReader(connection: Connection): Reader {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getWriter(connection: Connection): Writer {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun close(connection: Connection): Boolean {
        rsocketClientConnection.close()
        return true
    }
}