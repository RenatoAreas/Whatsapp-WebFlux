package br.com.renato.demo.servide;

import br.com.renato.demo.model.Business;
import br.com.renato.demo.model.Certificates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CertificatesService {

    @Autowired
    private WebClient webClient;

    public Certificates findById(String id) {
        Mono<Certificates> monoBusiness = this.webClient
                .method(HttpMethod.GET)
                .uri("/certificates/{id}", id)
                .retrieve()
                .bodyToMono(Certificates.class);
        Certificates certificates = monoBusiness.block();
        return certificates;
    }

    public Flux<Certificates> findAll() {
        Flux<Certificates> fluxCertificates = this.webClient
                .method(HttpMethod.GET)
                .uri("/certificates")
                .retrieve()
                .bodyToFlux(Certificates.class);
        return fluxCertificates;
    }
}