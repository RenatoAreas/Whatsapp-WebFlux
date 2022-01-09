package br.com.renato.demo.servide;

import br.com.renato.demo.model.Certificates;
import br.com.renato.demo.model.Contacts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ContactsService {

    @Autowired
    private WebClient webClient;

    public Contacts findById(String id) {
        Mono<Contacts> monoContacts = this.webClient
                .method(HttpMethod.GET)
                .uri("/contacts/{id}", id)
                .retrieve()
                .bodyToMono(Contacts.class);
        Contacts contacts = monoContacts.block();
        return contacts;
    }

    public Flux<Contacts> findAll() {
        Flux<Contacts> fluxContacts = this.webClient
                .method(HttpMethod.GET)
                .uri("/contacts")
                .retrieve()
                .bodyToFlux(Contacts.class);
        return fluxContacts;
    }
}
