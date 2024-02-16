package com.akobir.resttemplate.client;

import com.akobir.resttemplate.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "demo-api", url = "http://localhost:8080")
public interface DemoApiClient {

    @PostMapping("/v1/product/create")
    void createProduct(@RequestBody ProductDTO productDTO);

    @PutMapping("/v1/product/update")
    void updateProduct(@RequestBody ProductDTO productDTO);

    @DeleteMapping("/v1/product/delete/{id}")
    void deleteProduct(@PathVariable("id") Integer id);

    @GetMapping("/v1/product/get/{id}")
    ProductDTO getProduct(@PathVariable("id") Integer id);

    @GetMapping("/v1/product/list")
    List<ProductDTO> listProducts(@RequestParam("page") Optional<Long> page, @RequestParam("size") Optional<Long> size);
}
