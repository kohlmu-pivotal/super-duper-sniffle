package io.sniffle.communications.services

import io.rsocket.transport.ServerTransport
import io.rsocket.transport.netty.server.TcpServerTransport
import io.sniffle.communications.impl.RSocketServerConnectionServiceImpl
import java.net.InetAddress
import java.net.InetSocketAddress

class ServerTcpConnectionService(port: Int) : RSocketServerConnectionServiceImpl() {
    constructor(inetSocketAddress: InetSocketAddress, port: Int) : this(port)
    constructor(hostName: String, port: Int) : this(port)

    override fun getServerTransport(port: Int): ServerTransport<*> = TcpServerTransport.create(port)
    override fun getServerTransport(inetAddress: InetAddress, port: Int): ServerTransport<*> = TcpServerTransport.create(InetSocketAddress(inetAddress, port))
    override fun getServerTransport(hostName: String, port: Int): ServerTransport<*> = TcpServerTransport.create(InetSocketAddress(hostName, port))
}