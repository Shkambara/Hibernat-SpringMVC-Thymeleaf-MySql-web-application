package com.example.easynotes.controller;

import java.time.LocalDate;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.DrinkDayData;
import com.example.easynotes.model.DrinkForm;
import com.example.easynotes.model.State;
import com.example.easynotes.repository.DrinkDayDataRepository;
import com.example.easynotes.repository.DrinkFormRepository;

import com.example.easynotes.services.DrinkDayDataService;
import com.example.easynotes.services.DrinkFormService;

@Controller
public class DrinkDayDataController {

    @Autowired
    DrinkDayDataRepository drinkDayDataRepository;
    
    @Autowired
    DrinkDayDataService drinkDayDataService;
    
    @Autowired
    DrinkFormService drinkFormService;
    
    @Autowired
    DrinkFormRepository drinkFormRepository;

    @RequestMapping(value = "/addDay/{id}")
    public ModelAndView showDay(@PathVariable(value = "id")Long id,Model model) {
    	
    	Optional<DrinkForm> form = drinkFormService.getDrinkFormById(id);
    	
    	ModelAndView m = new ModelAndView();
    	m.setViewName("addDayDrink");
    	m.addObject("form",form.get());
    	m.addObject("date",LocalDate.now());
    	m.addObject("drinkDayData",new DrinkDayData());
    	
    	return m;
     }
    
    @RequestMapping(value = "/editDay/{id}")
    public ModelAndView editDay(@PathVariable(value = "id")Long id,Model model) {
    	
    	Optional<DrinkDayData> drinkDayData = drinkDayDataRepository.findById(id);
   
    	ModelAndView m = new ModelAndView();
    	m.setViewName("editDrink");
    	m.addObject("drinkDayData",drinkDayData.get());
    	
    	return m;
     }
 
    @RequestMapping(value="/editDrinkData/{id}", method = RequestMethod.POST)
    public String updateDrinkData(@PathVariable("id") Long id, @Valid DrinkDayData drinkDetails, 
      BindingResult result, Model model) {
    	
    	DrinkDayData drinkDayData = drinkDayDataRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DrinkDayData", "id", id));
    	
    	drinkDayData.setDate(drinkDayData.getDate().plusDays(1));
    	drinkDayData.setJuiceSold(drinkDetails.getJuiceSold());
    	drinkDayData.setCoctailsSold(drinkDetails.getCoctailsSold());
    	
    	drinkDayDataRepository.save(drinkDayData);
      
        return "redirect:/showDay/" + drinkDayData.getDrinkForm().getId();
    }
    
    
    @RequestMapping(value="/addDay", method = RequestMethod.POST)
    public String addDay(@Valid @ModelAttribute("drinkDayData") DrinkDayData day, BindingResult result, Model model) {
    	
    	
    	Optional<DrinkForm> form = drinkFormRepository.findByState(State.NOTSENT);
         day.setDate(LocalDate.now().plusDays(1));
         
        drinkDayDataService.createDrinkDayData(form.get().getId(), day);
        
        return "redirect:/showDay/" + form.get().getId();
    }
    
    @RequestMapping(value = "/delete_Day/{id}", method = RequestMethod.POST)
    public String handleDeleteDay(@ModelAttribute("day") DrinkDayData day) {
    	DrinkDayData drinkDayData = drinkDayDataRepository.findById(day.getId())
                .orElseThrow(() -> new ResourceNotFoundException("DrinkDayData", "id", day.getId()));
        Long id = drinkDayData.getDrinkForm().getId();
        
        drinkDayDataRepository.delete(drinkDayData);
        
        return "redirect:/showDay/" + id;
    }
}
