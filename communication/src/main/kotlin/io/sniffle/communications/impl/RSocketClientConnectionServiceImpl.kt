package io.sniffle.communications.impl

import io.rsocket.RSocket
import io.rsocket.RSocketFactory
import io.rsocket.SocketAcceptor
import io.rsocket.transport.ClientTransport
import io.sniffle.communications.ClientConnectionService
import io.sniffle.io.Reader
import io.sniffle.io.Writer
import io.sniffle.io.connection.Connection
import java.net.InetAddress

abstract class RSocketClientConnectionServiceImpl : ClientConnectionService<SocketAcceptor> {
    private lateinit var rsocket: RSocket

    override fun bind(inetAddress: InetAddress, port: Int, socketAcceptor: SocketAcceptor): Connection {
        rsocket = createRSocket(getClientTransport(inetAddress, port))
    }

    override fun bind(hostName: String, port: Int, socketAcceptor: SocketAcceptor): Connection {
        rsocket = createRSocket(getClientTransport(hostName, port))
    }

    override fun bind(port: Int, socketAcceptor: SocketAcceptor): Connection {
        rsocket = createRSocket(getClientTransport(port))

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
        rsocket.dispose()
    }
}