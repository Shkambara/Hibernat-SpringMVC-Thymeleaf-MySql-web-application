package com.example.easynotes.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.easynotes.model.DrinkForm;
import com.example.easynotes.model.EndOfTheDay;
import com.example.easynotes.model.IceCreamForm;
import com.example.easynotes.model.State;
import com.example.easynotes.repository.DrinkFormRepository;
import com.example.easynotes.repository.EndOfTheDayRepository;
import com.example.easynotes.repository.IceCreamFormRepository;



@Controller
public class ApplicationController {
	
	@Autowired
	IceCreamFormRepository iceCreamFormRepository;
		
	@Autowired
	DrinkFormRepository drinkFormRepository;
		
	@Autowired
	EndOfTheDayRepository endOfTheDayRepository;
			
	
	@RequestMapping(value = "/prototype", method = RequestMethod.GET)
    public ModelAndView showChoose() {
		
		Optional<IceCreamForm> iceForm = iceCreamFormRepository.findByState(State.NOTSENT);
		Optional<DrinkForm> drinkForm = drinkFormRepository.findByState(State.NOTSENT);
		Optional<EndOfTheDay> profitForm = endOfTheDayRepository.findByState(State.NOTSENT);
		
		ModelAndView m = new ModelAndView();
		m.setViewName("Choose");
		
		if(iceForm.isPresent() && drinkForm.isPresent() && profitForm.isPresent()) {	
			
			if(iceForm.isPresent()) m.addObject("iceForm",iceForm.get());
			else m.addObject("iceForm");
			
			if(drinkForm.isPresent()) m.addObject("drinkForm", drinkForm.get());
			else m.addObject("drinkForm");
			
			if(profitForm.isPresent()) m.addObject("profitForm", profitForm.get());
			else m.addObject("profitForm");
			
    	}
		
		return m;	
    }
	
	
}
