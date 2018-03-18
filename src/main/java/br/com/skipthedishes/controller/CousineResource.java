package br.com.skipthedishes.controller;

import br.com.skipthedishes.entity.CousineEntity;
import br.com.skipthedishes.entity.StoreEntity;
import br.com.skipthedishes.repository.CousineRepository;
import br.com.skipthedishes.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/Cousine", produces = MediaType.APPLICATION_JSON_VALUE)
public class CousineResource {

    @Autowired
    private CousineRepository cousineRepository;

    @Autowired
    private StoreRepository storeRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CousineEntity>> getCousines() {
        List<CousineEntity> cousinesList = new ArrayList<>();
        cousineRepository.findAll().forEach(cousinesList::add);

        return ResponseEntity.ok(cousinesList);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search/{searchText}")
    public ResponseEntity<List<CousineEntity>> getCousines(@PathVariable("searchText") String name) {
        List<CousineEntity> cousineEntities = cousineRepository.findByNameContainingIgnoreCase(name);

        return ResponseEntity.ok(cousineEntities);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{cousineId}/stores")
    public ResponseEntity<List<StoreEntity>> getStores(@PathVariable Long cousineId) {
        return ResponseEntity.ok(storeRepository.findByCousineId(cousineId));
    }
}
