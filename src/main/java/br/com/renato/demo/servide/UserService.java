package br.com.renato.demo.servide;

import br.com.renato.demo.model.Stickers;
import br.com.renato.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    private WebClient webClient;

    public User findById(String id) {
        Mono<User> monoUser = this.webClient
                .method(HttpMethod.GET)
                .uri("/user/{id}", id)
                .retrieve()
                .bodyToMono(User.class);
        User user = monoUser.block();
        return user;
    }

    public Flux<User> findAll() {
        Flux<User> fluxUser = this.webClient
                .method(HttpMethod.GET)
                .uri("/user")
                .retrieve()
                .bodyToFlux(User.class);
        return fluxUser;
    }
}
