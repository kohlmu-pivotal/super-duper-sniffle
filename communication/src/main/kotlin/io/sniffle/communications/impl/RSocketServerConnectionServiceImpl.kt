package io.sniffle.communications.impl

import io.rsocket.Closeable
import io.rsocket.RSocketFactory
import io.rsocket.transport.ServerTransport
import io.sniffle.communications.ServerConnectionService
import io.sniffle.communications.connections.RSocketServerConnection
import io.sniffle.communications.sockets.RSocketServerSocketAcceptor
import io.sniffle.io.Reader
import io.sniffle.io.Writer
import io.sniffle.io.connection.Connection
import java.net.InetAddress

abstract class RSocketServerConnectionServiceImpl : ServerConnectionService<RSocketServerSocketAcceptor> {
    private lateinit var rSocketServerConnection: RSocketServerConnection

    override fun connect(inetAddress: InetAddress, port: Int, socketAcceptor: RSocketServerSocketAcceptor): Connection {
        rSocketServerConnection = createRSocketServerConnection(socketAcceptor, getServerTransport(inetAddress, port))
        return rSocketServerConnection
    }

    override fun connect(hostName: String, port: Int, socketAcceptor: RSocketServerSocketAcceptor): Connection {
        rSocketServerConnection = createRSocketServerConnection(socketAcceptor, getServerTransport(hostName, port))
        return rSocketServerConnection
    }

    override fun connect(port: Int, socketAcceptor: RSocketServerSocketAcceptor): Connection {
        rSocketServerConnection = createRSocketServerConnection(socketAcceptor, getServerTransport(port))
        return rSocketServerConnection

    }

    private fun createRSocketServerConnection(
        socketAcceptor: RSocketServerSocketAcceptor, transport: ServerTransport<out Closeable>
    ): RSocketServerConnection {
        RSocketFactory
            .receive()
            .acceptor(socketAcceptor)
            .transport(transport)
            .start().subscribe()
        return RSocketServerConnection(socketAcceptor.rSocket)
    }

    protected abstract fun getServerTransport(port: Int): ServerTransport<out Closeable>
    protected abstract fun getServerTransport(inetAddress: InetAddress, port: Int): ServerTransport<out Closeable>
    protected abstract fun getServerTransport(hostName: String, port: Int): ServerTransport<out Closeable>

    override fun getReader(connection: Connection): Reader {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getWriter(connection: Connection): Writer {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun close(connection: Connection): Boolean {
        rSocketServerConnection.close()
        return true
    }


}