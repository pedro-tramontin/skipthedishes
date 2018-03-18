package br.com.skipthedishes.controller;

import br.com.skipthedishes.entity.CustomerEntity;
import br.com.skipthedishes.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/Customer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerResource {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Long> create(@RequestBody CustomerEntity customerEntity) {
        return ResponseEntity.ok(customerRepository.save(customerEntity).getId());
    }


}
