package br.com.renato.demo.servide;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MessagesService {

    @Autowired
    private WebClient webClient;

    public Message findById(String id) {
        Mono<Message> monoMessage = this.webClient
                .method(HttpMethod.GET)
                .uri("/message/{id}", id)
                .retrieve()
                .bodyToMono(Message.class);
        Message message = monoMessage.block();
        return message;
    }

    public Flux<Message> findAll() {
        Flux<Message> fluxMessage = this.webClient
                .method(HttpMethod.GET)
                .uri("/message")
                .retrieve()
                .bodyToFlux(Message.class);
        return fluxMessage;
    }
}
