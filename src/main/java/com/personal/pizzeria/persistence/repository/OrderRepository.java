package com.personal.pizzeria.persistence.repository;

import com.personal.pizzeria.persistence.entity.OrderEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer>{
}
