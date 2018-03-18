package br.com.skipthedishes.repository;

import br.com.skipthedishes.entity.StoreEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StoreRepository extends CrudRepository<StoreEntity, Long> {

    List<StoreEntity> findByNameContainingIgnoreCase(String name);

    List<StoreEntity> findByAddressContainingIgnoreCase(String address);

    List<StoreEntity> findByCousineId(Long cousineId);
}