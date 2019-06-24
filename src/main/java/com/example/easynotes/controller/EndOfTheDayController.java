package com.example.easynotes.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.DrinkDayData;
import com.example.easynotes.model.EndOfTheDay;
import com.example.easynotes.model.IceCreamDayData;
import com.example.easynotes.model.State;
import com.example.easynotes.repository.DrinkDayDataRepository;
import com.example.easynotes.repository.EndOfTheDayRepository;
import com.example.easynotes.repository.IceCreamDayDataRepository;
import com.example.easynotes.services.EndOfTheDayService;

@Controller
public class EndOfTheDayController {

    @Autowired
    EndOfTheDayRepository endOfTheDayRepository;
    
    @Autowired
    EndOfTheDayService endOfTheDayService;
    
    @Autowired
    IceCreamDayDataRepository iceCreamDayDataRepository;
    
    @Autowired
    DrinkDayDataRepository drinkDayDataRepository;
    
    @RequestMapping(value = "/sendProfitForm/{id}")
    public String sendForm(@PathVariable("id") Long id,  Model model) {
    	    	
    	    	Optional<EndOfTheDay> eotd = endOfTheDayRepository.findById(id);
    	    	
    	    	if(!eotd.isPresent()) {
    	    		throw new ResourceNotFoundException("DrinkForm", "id", eotd);
    	    	}
    	    	
    	    	if(!(eotd.get().getState().equals(State.SENT))) {
    	    		eotd.get().setState(State.SENT);
    	        	endOfTheDayRepository.save(eotd.get());
    	    	}
    	
    	    	
    	        return "redirect:/profitForm/" + id;
    	    }
    
    @RequestMapping(value = "/profitForm/{id}",method=RequestMethod.GET)
    public ModelAndView showDay(@PathVariable(value = "id")Long id,Model model) {
    	
    	Optional<EndOfTheDay> form = endOfTheDayService.getEndOfTheDayById(id);

    	ModelAndView m = new ModelAndView();
    	m.setViewName("profitForm");
    	
    	Optional<IceCreamDayData> dayData = iceCreamDayDataRepository.findByDate(LocalDate.now());
    	
    	if(!dayData.isPresent()) {
    		throw new ResourceNotFoundException("IceCreamDayData", "date", LocalDate.now());
    	}
    	
    	Optional<DrinkDayData> drinkDay = drinkDayDataRepository.findByDate(LocalDate.now());
    	
    	if(!drinkDay.isPresent()) {
    		throw new ResourceNotFoundException("DrinkDayData", "date", LocalDate.now());
    	}
    	
    	m.addObject("iceDay",dayData);
    	m.addObject("drinkDay",drinkDay);
    	m.addObject("shifts",form.get().getShifts());
    	m.addObject("form", form.get());
    	return m;
     }
}
