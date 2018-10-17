package io.sniffle.communications.impl

import io.rsocket.transport.ClientTransport
import io.rsocket.transport.netty.server.TcpServerTransport
import java.net.InetAddress
import java.net.InetSocketAddress

class ClientTcpConnectionService : RSocketClientConnectionServiceImpl() {
    override fun getClientTransport(port: Int): ClientTransport<*> = TcpServerTransport.create(port)
    override fun getClientTransport(inetAddress: InetAddress, port: Int): ClientTransport<*> = TcpServerTransport.create(InetSocketAddress(inetAddress, port))
    override fun getClientTransport(hostName: String, port: Int): ServeClientTransportrTransport<*> = TcpServerTransport.create(InetSocketAddress(hostName, port))
}