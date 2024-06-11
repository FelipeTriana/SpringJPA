package com.personal.pizzeria.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "pizza")
@EntityListeners(AuditingEntityListener.class)
@Getter //No se recomienda usar @Data porque gestiona automaticamente los metodos equals y hashcode y no es recomendable hacerlo con lombok en entidades
@Setter
@NoArgsConstructor
public class PizzaEntity extends AuditableEntity{ //Con solo decirle que herede de AuditableEntity ya tendra las columnas de auditoria (Todos los entity pueden heredar de ella), ademas esto no queda en el json de respuesta
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pizza", nullable = false)
    private Integer idPizza;

    @Column(nullable = false, length = 30, unique = true)
    private String name;

    @Column(nullable = false, length = 150)
    private String description;

    @Column(nullable = false, columnDefinition = "Decimal(5,2)")
    private Double price;

    @Column(columnDefinition = "TINYINT")
    private Boolean vegetarian;

    @Column(columnDefinition = "TINYINT")
    private Boolean vegan;

    @Column(columnDefinition = "TINYINT", nullable = false)
    private Boolean available;


}
