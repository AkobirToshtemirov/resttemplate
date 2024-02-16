package com.akobir.resttemplate.controller;

import com.akobir.resttemplate.client.DemoApiClient;
import com.akobir.resttemplate.criteria.ProductCriteria;
import com.akobir.resttemplate.dto.ProductDTO;
import com.akobir.resttemplate.service.RestTemplateConsumerService;
import com.akobir.resttemplate.service.WebClientConsumerService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    private final RestTemplateConsumerService restTemplateConsumerService;
    private final WebClientConsumerService webClientConsumerService;
    private final DemoApiClient demoApiClient;

    public ConsumerController(RestTemplateConsumerService restTemplateConsumerService, WebClientConsumerService webClientConsumerService, DemoApiClient demoApiClient) {
        this.restTemplateConsumerService = restTemplateConsumerService;
        this.webClientConsumerService = webClientConsumerService;
        this.demoApiClient = demoApiClient;
    }

    @PostMapping("/rest-template/create")
    public void createProductWithRestTemplate(@RequestBody ProductDTO productDTO) {
        restTemplateConsumerService.createProduct(productDTO);
    }

    @PostMapping("/web-client/create")
    public Mono<Void> createProductWithWebClient(@RequestBody ProductDTO productDTO) {
        return webClientConsumerService.createProduct(productDTO);
    }

    @PostMapping("/open-feign/create")
    public void createProductWithOpenFeign(@RequestBody ProductDTO productDTO) {
        demoApiClient.createProduct(productDTO);
    }

    @PutMapping("/rest-template/update")
    public void updateProductWithRestTemplate(@RequestBody ProductDTO productDTO) {
        restTemplateConsumerService.updateProduct(productDTO);
    }

    @PutMapping("/web-client/update")
    public Mono<Void> updateProductWithWebClient(@RequestBody ProductDTO productDTO) {
        return webClientConsumerService.updateProduct(productDTO);
    }

    @PutMapping("/open-feign/update")
    public void updateProductWithOpenFeign(@RequestBody ProductDTO productDTO) {
        demoApiClient.updateProduct(productDTO);
    }

    @DeleteMapping("/rest-template/delete/{id}")
    public void deleteProductWithRestTemplate(@PathVariable("id") Integer id) {
        restTemplateConsumerService.deleteProduct(id);
    }

    @DeleteMapping("/web-client/delete/{id}")
    public Mono<Void> deleteProductWithWebClient(@PathVariable("id") Integer id) {
        return webClientConsumerService.deleteProduct(id);
    }

    @DeleteMapping("/open-feign/delete/{id}")
    public void deleteProductWithOpenFeign(@PathVariable("id") Integer id) {
        demoApiClient.deleteProduct(id);
    }

    @GetMapping("/rest-template/get/{id}")
    public ProductDTO getProductWithRestTemplate(@PathVariable("id") Integer id) {
        return restTemplateConsumerService.getProduct(id);
    }

    @GetMapping("/web-client/get/{id}")
    public Mono<ProductDTO> getProductWithWebClient(@PathVariable("id") Integer id) {
        return webClientConsumerService.getProduct(id);
    }

    @GetMapping("/open-feign/get/{id}")
    public ProductDTO getProductWithOpenFeign(@PathVariable("id") Integer id) {
        return demoApiClient.getProduct(id);
    }

    @GetMapping("/rest-template/list")
    public List<ProductDTO> listProductsWithRestTemplate(@RequestParam("page") Long page, @RequestParam("size") Long size) {
        return restTemplateConsumerService.listProducts(new ProductCriteria(page, size));
    }

    @GetMapping("/web-client/list")
    public Mono<List<ProductDTO>> listProductsWithWebClient(@RequestParam("page") Long page, @RequestParam("size") Long size) {
        return webClientConsumerService.listProducts(new ProductCriteria(page, size));
    }

    @GetMapping("/open-feign/list")
    public List<ProductDTO> listProductsWithOpenFeign(@RequestParam("page") Long page, @RequestParam("size") Long size) {
        return demoApiClient.listProducts(Optional.of(page), Optional.of(size));
    }
}
