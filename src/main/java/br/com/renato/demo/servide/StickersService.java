package br.com.renato.demo.servide;

import br.com.renato.demo.model.Services;
import br.com.renato.demo.model.Stickers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StickersService {

    @Autowired
    private WebClient webClient;

    public Stickers findById(String id) {
        Mono<Stickers> monoStickers = this.webClient
                .method(HttpMethod.GET)
                .uri("/stickers/{id}", id)
                .retrieve()
                .bodyToMono(Stickers.class);
        Stickers stickers = monoStickers.block();
        return stickers;
    }

    public Flux<Stickers> findAll() {
        Flux<Stickers> fluxStickers = this.webClient
                .method(HttpMethod.GET)
                .uri("/stickers")
                .retrieve()
                .bodyToFlux(Stickers.class);
        return fluxStickers;
    }
}
