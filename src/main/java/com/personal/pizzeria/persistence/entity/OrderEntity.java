package com.personal.pizzeria.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pizza_order")
@Getter
@Setter
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order", nullable = false)
    private Integer idOrder;

    @Column(name = "id_customer", nullable = false, length = 15)
    private String idCustomer;

    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime date;

    @Column(nullable = false, columnDefinition = "DECIMAL(6,2)")
    private Double total;

    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private String method;

    @Column(name = "additional_notes", length = 200)
    private String additionalNotes;

    //LAZY: los datos de la entidad relacionada no se cargarán de la base de datos hasta que se acceda explícitamente a ellos en el código.
    @OneToOne(fetch = FetchType.LAZY) //Lazy es para que no se cargue la relacion sino hasta que se use, si no se usa no va a cargar la relacion como es en este caso
    @JoinColumn(name = "id_customer", referencedColumnName = "id_customer", insertable = false, updatable = false)
    @JsonIgnore                         //A pesar de poner el JsonIgnore, se siguen haciendo cinco consultas a la bd para obtener los clientes si no ponemos el fetchType LAZY
    private CustomerEntity customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER) //Eager es para que cuando se trate de recuperar un OrderEntity automaticamente tambien cargue esta relacion
    private List<OrderItemEntity> items;

}
