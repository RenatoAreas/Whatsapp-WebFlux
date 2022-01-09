package br.com.renato.demo.servide;

import br.com.renato.demo.model.Health;
import br.com.renato.demo.model.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MediaService {

    @Autowired
    private WebClient webClient;

    public Media findById(String id) {
        Mono<Media> monoMedia = this.webClient
                .method(HttpMethod.GET)
                .uri("/media/{id}", id)
                .retrieve()
                .bodyToMono(Media.class);
        Media media = monoMedia.block();
        return media;
    }

    public Flux<Media> findAll() {
        Flux<Media> fluxMedia = this.webClient
                .method(HttpMethod.GET)
                .uri("/media")
                .retrieve()
                .bodyToFlux(Media.class);
        return fluxMedia;
    }
}
