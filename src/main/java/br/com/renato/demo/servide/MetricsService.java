package br.com.renato.demo.servide;

import br.com.renato.demo.model.Metrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MetricsService {

    @Autowired
    private WebClient webClient;

    public Metrics findById(String id) {
        Mono<Metrics> monoMetrics = this.webClient
                .method(HttpMethod.GET)
                .uri("/metrics/{id}", id)
                .retrieve()
                .bodyToMono(Metrics.class);
        Metrics metrics = monoMetrics.block();
        return metrics;
    }

    public Flux<Metrics> findAll() {
        Flux<Metrics> fluxMetrics = this.webClient
                .method(HttpMethod.GET)
                .uri("/metrics")
                .retrieve()
                .bodyToFlux(Metrics.class);
        return fluxMetrics;
    }
}
