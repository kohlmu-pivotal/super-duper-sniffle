package io.sniffle.communications.impl

import io.rsocket.*
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class RSocketServerChannelAcceptor : SocketAcceptor {
    override fun accept(setup: ConnectionSetupPayload, sendingSocket: RSocket): Mono<RSocket> =
            Mono.just(object : AbstractRSocket() {
                override fun requestStream(payload: Payload?): Flux<Payload> {
                    return super.requestStream(payload)
                }

                override fun onClose(): Mono<Void> {
                    return super.onClose()
                }

                override fun metadataPush(payload: Payload?): Mono<Void> {
                    return super.metadataPush(payload)
                }

                override fun requestResponse(payload: Payload?): Mono<Payload> {
                    return super.requestResponse(payload)
                }

                override fun fireAndForget(payload: Payload?): Mono<Void> {
                    return super.fireAndForget(payload)
                }

                override fun requestChannel(payloads: Publisher<Payload>?): Flux<Payload> {
                    return super.requestChannel(payloads)
                }
            })
}