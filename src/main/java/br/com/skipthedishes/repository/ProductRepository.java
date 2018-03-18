package br.com.skipthedishes.repository;

import br.com.skipthedishes.entity.ProductEntity;
import br.com.skipthedishes.entity.StoreEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

    List<ProductEntity> findByNameContainingIgnoreCase(String name);

    List<ProductEntity> findByDescriptionContainingIgnoreCase(String description);

    List<ProductEntity> findByStoreId(Long storeId);
}