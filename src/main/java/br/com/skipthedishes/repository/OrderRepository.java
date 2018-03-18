package br.com.skipthedishes.repository;

import br.com.skipthedishes.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
    List<OrderEntity> findByCustomerId(Long customerId);
}