package br.com.renato.demo.servide;

import br.com.renato.demo.model.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ApplicationService {

    @Autowired
    private WebClient webClient;

    public Application findById(String id){
        Mono<Application> monoAplication = this.webClient
                .method(HttpMethod.GET)
                .uri("/application/{id}", id)
                .retrieve()
                .bodyToMono(Application.class);
        Application application = monoAplication.block();
                return application;
    }

    public Flux<Application> findAll(){
        Flux<Application> fluxApplication = this.webClient
                .method(HttpMethod.GET)
                .uri("/application")
                .retrieve()
                .bodyToFlux(Application.class);
        return fluxApplication;
     }
}
