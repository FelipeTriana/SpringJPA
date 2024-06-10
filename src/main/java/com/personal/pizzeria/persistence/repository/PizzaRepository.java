package com.personal.pizzeria.persistence.repository;

import com.personal.pizzeria.persistence.entity.PizzaEntity;
import com.personal.pizzeria.service.dto.UpdatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer>{
     List<PizzaEntity> findAllByAvailableTrueOrderByPrice();
     Optional<PizzaEntity> findFirstByAvailableTrueAndNameIgnoreCase(String name);
     List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);
     List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);
     int countAllByVeganTrue();

     //La notacion #{# se llama Spring Expresion Language (SpEL) y sirve para obtener las propiedades de un objeto desde el script
     @Query(value =
             "UPDATE pizza " +
             "SET price = :#{#newPizzaPrice.newPrice} " +
             "WHERE id_pizza = :#{#newPizzaPrice.pizzaId}", nativeQuery = true)
     @Modifying                                                                      //OJO: UN @Query sin @Modifying SOLO PODRA SER UN SELECT
     void updatePrice(@Param("newPizzaPrice") UpdatePizzaPriceDto newPizzaPrice);

}
