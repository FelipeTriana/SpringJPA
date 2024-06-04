package com.personal.pizzeria.web.controller;

import com.personal.pizzeria.persistence.entity.PizzaEntity;
import com.personal.pizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<List<PizzaEntity>> getAll(){
        return ResponseEntity.ok(this.pizzaService.getAll());
    }

    @GetMapping("/getPage") //Notese que no es necesario enviarle parametros (Revisar esta consulta en la coleccion de postman)
    public ResponseEntity<Page<PizzaEntity>> getAllPaging(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "8") int elements){
        return ResponseEntity.ok(this.pizzaService.getAllPaging(page, elements));
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> get(@PathVariable int idPizza){
        return ResponseEntity.ok(this.pizzaService.get(idPizza));
    }

    @PostMapping
    public ResponseEntity<PizzaEntity> add(@RequestBody PizzaEntity pizza){
        if (pizza.getIdPizza() == null || !this.pizzaService.exists(pizza.getIdPizza())) {
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza){
        if (pizza.getIdPizza() != null || this.pizzaService.exists(pizza.getIdPizza())) {
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> delete(@PathVariable int idPizza){
        if (this.pizzaService.exists(idPizza)){
            this.pizzaService.delete(idPizza);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/available")
    public ResponseEntity<List<PizzaEntity>> getAvailable(){
        return ResponseEntity.ok(this.pizzaService.getAvailable());
    }

    //Esta funcion mostrara las pizzas disponibles paginadas y ordenadas por el parametro que se le pase (Que por defecto es el pecio)
    @GetMapping("/available/PagSort") //Notese que no es necesario enviarle parametros (Revisar esta consulta en la coleccion de postman)
    public ResponseEntity<Page<PizzaEntity>> getAvailablePagingSort(@RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "8") int elements,
                                                                    @RequestParam(defaultValue = "price") String sortBy,    //Se ordena por precio por defecto
                                                                    @RequestParam(defaultValue = "ASC") String sortDirection){  //Se ordena de forma ascendente por defecto
        return ResponseEntity.ok(this.pizzaService.getAvailablePagingSort(page, elements, sortBy, sortDirection));
    }

    @GetMapping("/vegannumber")
    public ResponseEntity<String> getVeganNumber(){
        return ResponseEntity.ok("El numero de pizzas veganas es: " + this.pizzaService.getVeganPizzas());
    }

    @GetMapping("/name/{namePizza}")
    public ResponseEntity<PizzaEntity> getByName(@PathVariable String namePizza){
        return ResponseEntity.ok(this.pizzaService.getByName(namePizza));
    }

    @GetMapping("/with/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getByIngredient(@PathVariable String ingredient){
        return ResponseEntity.ok(this.pizzaService.getByIngredient(ingredient));
    }

    @GetMapping("/without/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getWithout(@PathVariable String ingredient){
        return ResponseEntity.ok(this.pizzaService.getWithout(ingredient));
    }

}
