package br.com.renato.demo.servide;

import br.com.renato.demo.model.Profile;
import br.com.renato.demo.model.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RegistrationService {

    @Autowired
    private WebClient webClient;

    public Registration findById(String id) {
        Mono<Registration> monoRegistration = this.webClient
                .method(HttpMethod.GET)
                .uri("/registration/{id}", id)
                .retrieve()
                .bodyToMono(Registration.class);
        Registration registration = monoRegistration.block();
        return registration;
    }

    public Flux<Registration> findAll() {
        Flux<Registration> fluxRegistration = this.webClient
                .method(HttpMethod.GET)
                .uri("/registration")
                .retrieve()
                .bodyToFlux(Registration.class);
        return fluxRegistration;
    }
}