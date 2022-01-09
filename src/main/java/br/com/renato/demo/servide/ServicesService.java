package br.com.renato.demo.servide;

import br.com.renato.demo.model.Registration;
import br.com.renato.demo.model.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServicesService {

    @Autowired
    private WebClient webClient;

    public Services findById(String id) {
        Mono<Services> monoServices = this.webClient
                .method(HttpMethod.GET)
                .uri("/services/{id}", id)
                .retrieve()
                .bodyToMono(Services.class);
        Services services = monoServices.block();
        return services;
    }

    public Flux<Services> findAll() {
        Flux<Services> fluxServices = this.webClient
                .method(HttpMethod.GET)
                .uri("/services")
                .retrieve()
                .bodyToFlux(Services.class);
        return fluxServices;
    }
}
