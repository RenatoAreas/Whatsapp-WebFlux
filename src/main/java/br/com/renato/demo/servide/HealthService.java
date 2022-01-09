package br.com.renato.demo.servide;

import br.com.renato.demo.model.Contacts;
import br.com.renato.demo.model.Health;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HealthService {

    @Autowired
    private WebClient webClient;

    public Health findById(String id) {
        Mono<Health> monoHealth = this.webClient
                .method(HttpMethod.GET)
                .uri("/health/{id}", id)
                .retrieve()
                .bodyToMono(Health.class);
        Health health = monoHealth.block();
        return health;
    }

    public Flux<Health> findAll() {
        Flux<Health> fluxHealth = this.webClient
                .method(HttpMethod.GET)
                .uri("/health")
                .retrieve()
                .bodyToFlux(Health.class);
        return fluxHealth;
    }
}
