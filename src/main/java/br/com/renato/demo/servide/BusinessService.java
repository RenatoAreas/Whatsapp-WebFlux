package br.com.renato.demo.servide;

import br.com.renato.demo.model.Backup;
import br.com.renato.demo.model.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BusinessService {

    @Autowired
    private WebClient webClient;

    public Business findById(String id){
        Mono<Business> monoBusiness = this.webClient
                .method(HttpMethod.GET)
                .uri("/business/{id}", id)
                .retrieve()
                .bodyToMono(Business.class);
        Business business = monoBusiness.block();
        return business;
    }

    public Flux<Business> findAll(){
        Flux<Business> fluxBusiness = this.webClient
                .method(HttpMethod.GET)
                .uri("/business")
                .retrieve()
                .bodyToFlux(Business.class);
        return fluxBusiness;
    }
}
