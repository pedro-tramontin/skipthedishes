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

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping(value = "/Store", produces = MediaType.APPLICATION_JSON_VALUE)
public class StoreResource {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<StoreEntity>> getStores() {
        List<StoreEntity> storeEntityList = new ArrayList<>();
        storeRepository.findAll().forEach(storeEntityList::add);

        return ResponseEntity.ok(storeEntityList);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search/{searchText}")
    public ResponseEntity<List<StoreEntity>> getStores(@PathVariable("searchText") String searchText) {
        HashSet<StoreEntity> results = new HashSet<>();
        storeRepository.findByNameContainingIgnoreCase(searchText).stream().forEach(results::add);
        storeRepository.findByAddressContainingIgnoreCase(searchText).stream().forEach(results::add);

        return ResponseEntity.ok(new ArrayList<>(results));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{storeId}")
    public ResponseEntity<StoreEntity> getStore(@PathVariable Long storeId) {
        return ResponseEntity.ok(storeRepository.findOne(storeId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{storeId}/products")
    public ResponseEntity<List<ProductEntity>> getProducts(@PathVariable Long storeId) {
        return ResponseEntity.ok(productRepository.findByStoreId(storeId));
    }

}
