package io.sniffle.communications.impl

import io.rsocket.Closeable
import io.rsocket.RSocketFactory
import io.rsocket.SocketAcceptor
import io.rsocket.transport.ServerTransport
import io.sniffle.communications.ServerConnectionService
import io.sniffle.io.Reader
import io.sniffle.io.Writer
import io.sniffle.io.connection.Connection
import reactor.core.Disposable
import java.net.InetAddress

abstract class RSocketServerConnectionServiceImpl : ServerConnectionService<SocketAcceptor> {
    private lateinit var disposable: Disposable

    override fun connect(inetAddress: InetAddress, port: Int, socketAcceptor: SocketAcceptor): Connection {
        disposable = createRSocketServerConnection(socketAcceptor, getServerTransport(inetAddress, port))
    }

    override fun connect(hostName: String, port: Int, socketAcceptor: SocketAcceptor): Connection {
        disposable = createRSocketServerConnection(socketAcceptor, getServerTransport(hostName, port))
    }

    override fun connect(port: Int, socketAcceptor: SocketAcceptor): Connection {
        disposable = createRSocketServerConnection(socketAcceptor, getServerTransport(port))

    }

    private fun createRSocketServerConnection(socketAcceptor: SocketAcceptor, transport: ServerTransport<out Closeable>) =
            RSocketFactory
                    .receive()
                    .acceptor(socketAcceptor)
                    .transport(transport)
                    .start().subscribe()

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
        disposable.dispose()
    }


}