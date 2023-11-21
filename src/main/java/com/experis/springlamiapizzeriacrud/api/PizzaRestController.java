package com.experis.springlamiapizzeriacrud.api;


import com.experis.springlamiapizzeriacrud.model.Pizza;
import com.experis.springlamiapizzeriacrud.repository.PizzaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pizzas")
@CrossOrigin
public class PizzaRestController {

    @Autowired
    private PizzaRepository pizzaRepository;

    // Inietta il PizzaRepository nel costruttore
    public PizzaRestController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    // Endpoint per la lista di tutte le pizze
    @GetMapping
    public ResponseEntity<List<Pizza>> getPizzas(@RequestParam Optional<String> search) {
        List<Pizza> pizzaList;
        if (search.isPresent()) {
            pizzaList = pizzaRepository.findByNameContainingIgnoreCase( search.get() );
        } else {
            pizzaList = pizzaRepository.findAll();
        }

        // Restituisci la lista di pizze con una risposta HTTP 200 (OK)
        return new ResponseEntity<>( pizzaList, HttpStatus.OK );
    }


    // Endpoint per i dettagli della pizza per ID
    @GetMapping("/{id}")
    public ResponseEntity<Pizza> getPizzaDetails(@PathVariable Integer id) {
        Optional<Pizza> optionalPizza = pizzaRepository.findById( id );
        if (optionalPizza.isPresent()) {
            Pizza pizza = optionalPizza.get();
            return new ResponseEntity<>( pizza, HttpStatus.OK );
        } else {
            // Se la pizza non è trovata, restituisci una risposta HTTP 404 (Not Found)
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Pizza not found with ID: " + id );
        }

    }

    // Endpoint per la creazione di una nuova pizza
    public ResponseEntity<Pizza> create(@Valid @RequestBody Pizza pizza) {
        try {
            // Implementa la logica per la creazione della pizza
            Pizza newPizza = pizzaRepository.save( pizza );

            // Restituisci la nuova pizza con una risposta HTTP 201 (Created)
            return new ResponseEntity<>( newPizza, HttpStatus.CREATED );
        } catch (Exception e) {
            // Se si verifica un errore, restituisci una risposta HTTP 400 (Bad Request)
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Error creating pizza", e );
        }

    }

    // Endpoint per la edit di una nuova pizza
    @PutMapping("/{id}")
    public ResponseEntity<Pizza> update(@PathVariable Integer id, @Valid @RequestBody Pizza updatedPizza) {
        // Verifica se la pizza con l'id specificato esiste nel database
        Pizza existingPizza = pizzaRepository.findById( id )
                .orElseThrow( () -> new ResponseStatusException( HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found" ) );

        // Aggiorna le informazioni della pizza esistente con i nuovi dati forniti
        existingPizza.setName( updatedPizza.getName() );
        existingPizza.setDescription( updatedPizza.getDescription() );
        existingPizza.setPhoto_url( updatedPizza.getPhoto_url() );
        existingPizza.setPrice( updatedPizza.getPrice() );

        // Salva la pizza aggiornata nel database
        Pizza savedPizza = pizzaRepository.save( existingPizza );

        // Restituisci la pizza aggiornata e uno stato HTTP 200 OK
        return ResponseEntity.ok( savedPizza );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        // Verifica se la pizza con l'id specificato esiste nel database
        Pizza pizzaToDelete = pizzaRepository.findById( id )
                .orElseThrow( () -> new ResponseStatusException( HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found" ) );

        // Elimina la pizza dal database
        pizzaRepository.deleteById( id );

        // Restituisci uno stato HTTP 204 No Content
        return ResponseEntity.noContent().build();

    }
}
