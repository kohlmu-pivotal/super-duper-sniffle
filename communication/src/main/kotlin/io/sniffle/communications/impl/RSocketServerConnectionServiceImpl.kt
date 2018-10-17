package io.sniffle.communications.impl

import io.rsocket.Closeable
import io.rsocket.RSocket
import io.rsocket.RSocketFactory
import io.rsocket.SocketAcceptor
import io.rsocket.transport.ServerTransport
import io.sniffle.communications.ServerConnectionService
import io.sniffle.io.Reader
import io.sniffle.io.Writer
import io.sniffle.io.connection.Connection
import reactor.core.publisher.Mono
import java.net.InetAddress

abstract class RSocketServerConnectionServiceImpl : ServerConnectionService<SocketAcceptor> {
    private lateinit var rsocket: Mono<RSocket>

    override fun connect(inetAddress: InetAddress, port: Int, socketAcceptor: SocketAcceptor): Connection {
        RSocketFactory
                .receive()
                .acceptor(socketAcceptor)
                .transport(getServerTransport(inetAddress, port))
                .start().subscribe()
    }

    override fun connect(hostName: String, port: Int, socketAcceptor: SocketAcceptor): Connection {
        RSocketFactory
                .receive()
                .acceptor(socketAcceptor)
                .transport(getServerTransport(hostName, port))
                .start().subscribe()
    }

    override fun connect(port: Int, socketAcceptor: SocketAcceptor): Connection {
        RSocketFactory
                .receive()
                .acceptor(socketAcceptor)
                .transport(getServerTransport(port))
                .start().subscribe()

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}