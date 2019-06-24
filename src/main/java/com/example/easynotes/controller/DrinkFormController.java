package com.example.easynotes.controller;


import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.DrinkForm;
import com.example.easynotes.model.State;
import com.example.easynotes.repository.DrinkFormRepository;
import com.example.easynotes.services.DrinkFormService;

@Controller
public class DrinkFormController {

    @Autowired
    DrinkFormRepository drinkFormRepository;
    
    @Autowired
    DrinkFormService drinkFormService;


    @PutMapping("/DrinkForm/{id}")
    public DrinkForm updateDrinkForm(@PathVariable(value = "id") Long id,
                                           @Valid @RequestBody DrinkForm drinkFormDetails) {

        DrinkForm drinkForm = drinkFormRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DrinkForm", "id", id));

        drinkForm.setState(drinkFormDetails.getState());
        drinkForm.setDate(drinkFormDetails.getDate());
        
        DrinkForm updatedDrinkForm = drinkFormRepository.save(drinkForm);
        
        return updatedDrinkForm;
    }
    
    
    
    @RequestMapping(value = "/sendDay/{id}")
    public String sendForm(@PathVariable("id") Long id,  Model model) {
    	    	
    	    	Optional<DrinkForm> drinkForm = drinkFormRepository.findById(id);
    	    	
    	    	if(!drinkForm.isPresent()) {
    	    		throw new ResourceNotFoundException("DrinkForm", "id", drinkForm);
    	    	}
    	    	
    	    	if(!(drinkForm.get().getState().equals(State.SENT))) {
    	    		drinkForm.get().setState(State.SENT);
    	        	drinkFormRepository.save(drinkForm.get());
    	    	}
    	
    	    	
    	        return "redirect:/prototype";
    	    }
    
    @RequestMapping(value = "/showDay/{id}")
    public ModelAndView showDay(@PathVariable(value = "id")Long id,Model model) {
    	
    	Optional<DrinkForm> form = drinkFormService.getDrinkFormById(id);
    	
    	ModelAndView m = new ModelAndView();
    	m.setViewName("showDay");
    	m.addObject("days",form.get().getDayData());
    	m.addObject("form", form.get());
    	
    	return m;
     }

    @DeleteMapping("/DrinkForm/{id}")
    public ResponseEntity<?> deleteDrinkForm(@PathVariable(value = "id") Long id) {
        DrinkForm drinkForm = drinkFormRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DrinkForm", "id", id));

        drinkFormRepository.delete(drinkForm);

        return ResponseEntity.ok().build();
    }
}
