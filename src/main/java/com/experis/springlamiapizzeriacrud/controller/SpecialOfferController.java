package com.experis.springlamiapizzeriacrud.controller;

import com.experis.springlamiapizzeriacrud.model.Pizza;
import com.experis.springlamiapizzeriacrud.model.SpecialOffer;
import com.experis.springlamiapizzeriacrud.repository.PizzaRepository;
import com.experis.springlamiapizzeriacrud.repository.SpecialOfferRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/special_offer")
public class SpecialOfferController {

    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private SpecialOfferRepository specialOfferRepository;


    //CREATE
    @GetMapping("/create")
    public String create(@RequestParam Integer pizzaId, Model model) {
        Pizza pizza = pizzaRepository.findById( pizzaId ).orElseThrow( () -> new ResponseStatusException( HttpStatus.NOT_FOUND ) );
        SpecialOffer specialOffer = new SpecialOffer();
        specialOffer.setStartDate( LocalDate.now() );
        specialOffer.setPizza( pizza );
        model.addAttribute( "specialOffer", specialOffer );
        return "specialOffer/form";
    }

    //STORE

    @PostMapping("/create/store")
    public String store(@Valid @ModelAttribute("specialOffer") SpecialOffer formSpecialOffer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "specialOffer/form";
        }
        SpecialOffer savedSpecialOffer = specialOfferRepository.save( formSpecialOffer );
        return "redirect:/pizzas/show/" + savedSpecialOffer.getPizza().getId();
    }


    //EDIT

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<SpecialOffer> result = specialOfferRepository.findById( id );

        if (result.isPresent()) {
            model.addAttribute( "specialOffer", result.get() );
            return "specialOffer/edit";
        } else {
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "special offer with id " + id + " not found" );
        }


    }

    //UPDATE

    @PostMapping("/edit/update/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("specialOffer") SpecialOffer formSpecialOffer, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "specialOffer/edit";
        }

        SpecialOffer specialOfferToEdit = specialOfferRepository.findById( id )
                .orElseThrow( () -> new ResponseStatusException( HttpStatus.NOT_FOUND ) );

        specialOfferToEdit.setTitle( formSpecialOffer.getTitle() );
        specialOfferToEdit.setStartDate( formSpecialOffer.getStartDate() );
        specialOfferToEdit.setEndDate( formSpecialOffer.getEndDate() );


        SpecialOffer updatedSpecialOffer = specialOfferRepository.save( specialOfferToEdit );

        return "redirect:/pizzas/show/" + updatedSpecialOffer.getPizza().getId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        SpecialOffer specialOfferToDelete = specialOfferRepository.findById( id ).orElseThrow( () -> new ResponseStatusException( HttpStatus.NOT_FOUND ) );
        specialOfferRepository.deleteById( id );
        return "redirect:/pizzas/show/" + specialOfferToDelete.getPizza().getId();
    }
}
