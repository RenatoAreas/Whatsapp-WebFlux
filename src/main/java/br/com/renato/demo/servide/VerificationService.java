package br.com.renato.demo.servide;

import br.com.renato.demo.model.User;
import br.com.renato.demo.model.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VerificationService {

    @Autowired
    private WebClient webClient;

    public Verification findById(String id) {
        Mono<Verification> monoVerification = this.webClient
                .method(HttpMethod.GET)
                .uri("/verification/{id}", id)
                .retrieve()
                .bodyToMono(Verification.class);
        Verification verification = monoVerification.block();
        return verification;
    }

    public Flux<Verification> findAll() {
        Flux<Verification> fluxVerification = this.webClient
                .method(HttpMethod.GET)
                .uri("/verification")
                .retrieve()
                .bodyToFlux(Verification.class);
        return fluxVerification;
    }
}
