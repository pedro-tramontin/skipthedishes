package br.com.skipthedishes.repository;

import br.com.skipthedishes.entity.CousineEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CousineRepository extends CrudRepository<CousineEntity, Long> {

    List<CousineEntity> findByNameContainingIgnoreCase(String name);
}