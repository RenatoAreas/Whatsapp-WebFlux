package br.com.renato.demo.servide;

import br.com.renato.demo.model.Application;
import br.com.renato.demo.model.Backup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BackupService {

    @Autowired
    private WebClient webClient;

    public Backup findById(String id){
        Mono<Backup> monoBackup = this.webClient
                .method(HttpMethod.GET)
                .uri("/backup/{id}", id)
                .retrieve()
                .bodyToMono(Backup.class);
        Backup backup = monoBackup.block();
        return backup;
    }

    public Flux<Backup> findAll(){
        Flux<Backup> fluxBackup = this.webClient
                .method(HttpMethod.GET)
                .uri("/backup")
                .retrieve()
                .bodyToFlux(Backup.class);
        return fluxBackup;
    }
}
