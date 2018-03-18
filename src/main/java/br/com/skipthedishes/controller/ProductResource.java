package br.com.skipthedishes.controller;

import br.com.skipthedishes.entity.ProductEntity;
import br.com.skipthedishes.entity.StoreEntity;
import br.com.skipthedishes.repository.ProductRepository;
import br.com.skipthedishes.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping(value = "/Product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductResource {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ProductEntity>> getProducts() {
        List<ProductEntity> productEntityList = new ArrayList<>();
        productRepository.findAll().forEach(productEntityList::add);

        return ResponseEntity.ok(productEntityList);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search/{searchText}")
    public ResponseEntity<List<ProductEntity>> getProducts(@PathVariable("searchText") String searchText) {
        HashSet<ProductEntity> results = new HashSet<>();
        productRepository.findByNameContainingIgnoreCase(searchText).stream().forEach(results::add);
        productRepository.findByDescriptionContainingIgnoreCase(searchText).stream().forEach(results::add);

        return ResponseEntity.ok(new ArrayList<>(results));
    }

    @RequestMapping(method = RequestMethod.GET, value = "{productId}")
    public ResponseEntity<ProductEntity> getProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(productRepository.findOne(productId));
    }

}
