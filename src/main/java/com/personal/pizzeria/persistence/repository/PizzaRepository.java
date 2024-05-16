package com.personal.pizzeria.persistence.repository;

import com.personal.pizzeria.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer>{
     List<PizzaEntity> findAllByAvailableTrueOrderByPrice();
     Optional<PizzaEntity> findFirstByAvailableTrueAndNameIgnoreCase(String name);
     List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);
     List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);
     int countAllByVeganTrue();



}
