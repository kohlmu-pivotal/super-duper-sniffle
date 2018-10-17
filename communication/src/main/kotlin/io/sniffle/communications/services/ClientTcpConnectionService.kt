package io.sniffle.communications.services

import io.rsocket.transport.ClientTransport
import io.rsocket.transport.netty.client.TcpClientTransport
import io.sniffle.communications.impl.RSocketClientConnectionServiceImpl
import java.net.InetAddress
import java.net.InetSocketAddress

class ClientTcpConnectionService : RSocketClientConnectionServiceImpl() {
    override fun getClientTransport(port: Int): ClientTransport =
        TcpClientTransport.create(port)

    override fun getClientTransport(inetAddress: InetAddress, port: Int): ClientTransport =
        TcpClientTransport.create(InetSocketAddress(inetAddress, port))

    override fun getClientTransport(hostName: String, port: Int): ClientTransport =
        TcpClientTransport.create(InetSocketAddress(hostName, port))
}