package br.com.skipthedishes.repository;

import br.com.skipthedishes.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

    CustomerEntity findByEmailAndPassword(String email, String password);
}