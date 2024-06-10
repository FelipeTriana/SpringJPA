package com.personal.pizzeria.persistence.projection;

import java.time.LocalDateTime;

public interface OrderSummary {
    Integer getIdOrder(); //Ojo: Asi mismo se pone el alias de las columnas en el SELECT pero iniciando en minuscula
    String getCustomerName();
    LocalDateTime getOrderDate();
    Double getOrderTotal();
    String getPizzaNames();
}
