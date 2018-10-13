package io.sniffle.communications.impl

import io.sniffle.communications.ConnectionService
import io.vavr.control.Either
import java.net.InetAddress

class RSocketConnectionServiceImpl : ConnectionService {
    private RS
    override fun connect(inetAddress: InetAddress): Either<io.sniffle.io.connection.Connection, Error> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun connect(inetAddress: InetAddress, port: Int): Either<io.sniffle.io.connection.Connection, Error> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun connect(hostName: String, port: Int): Either<io.sniffle.io.connection.Connection, Error> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun connect(port: Int): Either<io.sniffle.io.connection.Connection, Error> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getReader(connection: io.sniffle.io.connection.Connection): Either<io.sniffle.io.Reader, Error> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getWriter(connection: io.sniffle.io.connection.Connection): Either<io.sniffle.io.Writer, Error> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun close(connection: io.sniffle.io.connection.Connection): Either<Boolean, Error> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}