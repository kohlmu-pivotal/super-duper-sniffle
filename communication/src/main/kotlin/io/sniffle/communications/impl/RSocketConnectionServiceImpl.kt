package io.sniffle.communications.impl

import io.reactivex.Flowable
import io.reactivex.Single
import io.rsocket.kotlin.DefaultPayload
import io.rsocket.kotlin.Payload
import io.rsocket.kotlin.RSocket
import io.rsocket.kotlin.Setup
import io.rsocket.kotlin.util.AbstractRSocket
import io.sniffle.communications.ConnectionService
import io.sniffle.io.Reader
import io.sniffle.io.Writer
import io.sniffle.io.connection.Connection
import java.net.InetAddress

class RSocketConnectionServiceImpl : ConnectionService {
    private lateinit var rsocket: Single<*>

//    override fun bind(inetAddress: InetAddress): Connection {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun bind(inetAddress: InetAddress, port: Int): Connection {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun bind(hostName: String, port: Int): Connection {
//        rsocket = RSocketFactory
//            .receive()
//            .acceptor { { setup, rSocket -> handler(setup, rSocket) } } // server handler RSocket
//            .transport(TcpServerTransport.create(hostName, port))  // Netty websocket transport
//            .start()
//    }
//
//    override fun bind(port: Int): Connection {
//        rsocket = RSocketFactory
//            .receive()
//            .acceptor { { setup, rSocket -> handler(setup, rSocket) } } // server handler RSocket
//            .transport(TcpServerTransport.create(port)) // Netty websocket transport
//            .start()
//    }


    override fun connect(inetAddress: InetAddress): Connection {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun connect(inetAddress: InetAddress, port: Int): Connection {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun connect(hostName: String, port: Int): Connection {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun connect(port: Int): Connection {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getReader(connection: Connection): Reader {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getWriter(connection: Connection): Writer {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun close(connection: Connection): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun handler(setup: Setup, rSocket: RSocket): Single<RSocket> {
        return Single.just(object : AbstractRSocket() {
            override fun requestStream(payload: Payload): Flowable<Payload> {
                return Flowable.just(DefaultPayload.text("server handler response"))
            }
        })
    }
}