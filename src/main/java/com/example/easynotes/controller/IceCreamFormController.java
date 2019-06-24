package com.example.easynotes.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.IceCreamForm;
import com.example.easynotes.model.State;
import com.example.easynotes.repository.IceCreamFormRepository;
import com.example.easynotes.services.IceCreamFormService;

@Controller
public class IceCreamFormController {

    @Autowired
    IceCreamFormRepository iceCreamFormRepository;
    
    @Autowired
    IceCreamFormService iceCreamFormService;

    @RequestMapping(value = "/sendIceCreamForm/{id}")
    public String sendForm(@PathVariable("id") Long id,  Model model) {
    	    	
    	    	Optional<IceCreamForm> iceCreamForm = iceCreamFormRepository.findById(id);
    	    	if(!iceCreamForm.isPresent()) {
    	    		throw new ResourceNotFoundException("DrinkForm", "id", iceCreamForm);
    	    	}
    	    	if(!(iceCreamForm.get().getState().equals(State.SENT))) {
    	    		iceCreamForm.get().setState(State.SENT);
    	        	iceCreamFormRepository.save(iceCreamForm.get());
    	    	}
    	   	    	
    	        return "redirect:/iceCreamForm/3";
    	    }
    
    
    @RequestMapping(value = "/iceCreamForm/{form_id}")
    public ModelAndView showDay(@PathVariable(value = "form_id")Long id,Model model) {
    	
    	Optional<IceCreamForm> form = iceCreamFormService.getIceCreamFormById(id);
    	
    	ModelAndView m = new ModelAndView();
    	m.setViewName("iceCreamForm");
    	m.addObject("days",form.get().getDayData());
    	m.addObject("form", form.get());
    	
    	return m;
     }
   
}
