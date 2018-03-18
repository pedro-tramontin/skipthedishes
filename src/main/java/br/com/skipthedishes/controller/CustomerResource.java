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

import java.util.Base64;

@RestController
@RequestMapping(value = "/Customer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerResource {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody CustomerEntity customerEntity) {
        CustomerEntity savedEntity = customerRepository.save(customerEntity);

        String basicAuth = savedEntity.getEmail() + ":" + savedEntity.getPassword();

        String base64auth = Base64.getEncoder().encodeToString(basicAuth.getBytes());

        return ResponseEntity.ok(base64auth);
    }


}
