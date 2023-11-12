package com.experis.springlamiapizzeriacrud.controller;

import com.experis.springlamiapizzeriacrud.model.Pizza;
import com.experis.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {


    private PizzaRepository PizzaRepository;

    @Autowired
    public PizzaController(com.experis.springlamiapizzeriacrud.repository.PizzaRepository pizzaRepository) {
        PizzaRepository = pizzaRepository;
    }

    @GetMapping
    public String index(Model model) {
        List<Pizza> pizzaList = PizzaRepository.findAll();
        model.addAttribute( "pizzaList", pizzaList );
        return "pizzas/pizzas-list";
    }
}
