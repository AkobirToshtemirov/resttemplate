package com.akobir.resttemplate.service;

import com.akobir.resttemplate.criteria.ProductCriteria;
import com.akobir.resttemplate.dto.ProductDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RestTemplateConsumerService {

    private final String apiUrl = "http://localhost:8080/v1/product";
    private final RestTemplate restTemplate;

    public RestTemplateConsumerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void createProduct(ProductDTO productDTO) {
        restTemplate.postForObject(apiUrl + "/create", productDTO, Void.class);
    }

    public void updateProduct(ProductDTO productDTO) {
        restTemplate.put(apiUrl + "/update", productDTO);
    }

    public void deleteProduct(Integer id) {
        restTemplate.delete(apiUrl + "/delete/{id}", id);
    }

    public ProductDTO getProduct(Integer id) {
        return restTemplate.getForObject(apiUrl + "/get/{id}", ProductDTO.class, id);
    }

    public List<ProductDTO> listProducts(ProductCriteria criteria) {
        return Arrays.asList(restTemplate.getForObject(apiUrl + "/list?page={page}&size={size}", ProductDTO[].class, criteria.getPage(), criteria.getSize()));
    }
}
