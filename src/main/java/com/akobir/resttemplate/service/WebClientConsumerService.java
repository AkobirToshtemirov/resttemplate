package com.akobir.resttemplate.service;

import com.akobir.resttemplate.criteria.ProductCriteria;
import com.akobir.resttemplate.dto.ProductDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class WebClientConsumerService {

    private final String apiUrl = "http://localhost:8080/v1/product";
    private final WebClient webClient;

    public WebClientConsumerService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(apiUrl).build();
    }

    public Mono<Void> createProduct(ProductDTO productDTO) {
        return webClient.post()
                .uri("/create")
                .bodyValue(productDTO)
                .retrieve()
                .bodyToMono(Void.class);
    }

    public Mono<Void> updateProduct(ProductDTO productDTO) {
        return webClient.put()
                .uri("/update")
                .bodyValue(productDTO)
                .retrieve()
                .bodyToMono(Void.class);
    }

    public Mono<Void> deleteProduct(Integer id) {
        return webClient.delete()
                .uri("/delete/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }

    public Mono<ProductDTO> getProduct(Integer id) {
        return webClient.get()
                .uri("/get/{id}", id)
                .retrieve()
                .bodyToMono(ProductDTO.class);
    }

    public Mono<List<ProductDTO>> listProducts(ProductCriteria criteria) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/list")
                        .queryParam("page", criteria.getPage())
                        .queryParam("size", criteria.getSize())
                        .build())
                .retrieve()
                .bodyToFlux(ProductDTO.class)
                .collectList();
    }
}
