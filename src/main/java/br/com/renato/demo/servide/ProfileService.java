package br.com.renato.demo.servide;

import br.com.renato.demo.model.Metrics;
import br.com.renato.demo.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProfileService {

    @Autowired
    private WebClient webClient;

    public Profile findById(String id) {
        Mono<Profile> monoProfile = this.webClient
                .method(HttpMethod.GET)
                .uri("/profile/{id}", id)
                .retrieve()
                .bodyToMono(Profile.class);
        Profile profile = monoProfile.block();
        return profile;
    }

    public Flux<Profile> findAll() {
        Flux<Profile> fluxProfile = this.webClient
                .method(HttpMethod.GET)
                .uri("/profile")
                .retrieve()
                .bodyToFlux(Profile.class);
        return fluxProfile;
    }
}
