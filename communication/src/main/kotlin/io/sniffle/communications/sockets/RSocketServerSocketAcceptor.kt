package io.sniffle.communications.sockets

import io.rsocket.AbstractRSocket
import io.rsocket.ConnectionSetupPayload
import io.rsocket.RSocket
import io.rsocket.SocketAcceptor
import reactor.core.publisher.Mono

class RSocketServerSocketAcceptor(internal val rSocket: AbstractRSocket) : SocketAcceptor {
    override fun accept(setup: ConnectionSetupPayload, sendingSocket: RSocket): Mono<RSocket> =
        Mono.just(rSocket)
}