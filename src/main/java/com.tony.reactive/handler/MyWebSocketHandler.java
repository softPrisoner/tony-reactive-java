package com.tony.reactive.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author tony
 * @describe WebSocketHandler
 * @date 2019/07/01
 */
@Component
public class MyWebSocketHandler implements WebSocketHandler {


    @Override
    public List<String> getSubProtocols() {
        return null;
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.send(session.receive()
                .map(msg -> session.textMessage("服务端返回:小明->" + msg.getPayloadAsText())));
    }
}
