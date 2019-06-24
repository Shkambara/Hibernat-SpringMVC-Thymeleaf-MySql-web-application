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
import com.example.easynotes.model.WorkingShift;
import com.example.easynotes.model.DrinkDayData;
import com.example.easynotes.model.EndOfTheDay;
import com.example.easynotes.model.IceCreamDayData;
import com.example.easynotes.model.State;
import com.example.easynotes.repository.WorkingShiftRepository;
import com.example.easynotes.repository.DrinkDayDataRepository;
import com.example.easynotes.repository.EndOfTheDayRepository;
import com.example.easynotes.repository.IceCreamDayDataRepository;
import com.example.easynotes.services.WorkingShiftService;
import com.example.easynotes.services.EndOfTheDayService;

@Controller
public class WorkingShiftController {

    @Autowired
    WorkingShiftRepository workingShiftRepository;
    
    @Autowired
    WorkingShiftService workingShiftService;
    
    @Autowired
    IceCreamDayDataRepository iceCreamDayDataRepository;
    
    @Autowired
    DrinkDayDataRepository drinkDayDataRepository;
    
    
    @Autowired
    EndOfTheDayRepository endOfTheDayRepository;
    
    @Autowired
    EndOfTheDayService endOfTheDayService;

    @RequestMapping(value = "/addProfit", method = RequestMethod.POST)
    public String createWorking(@Valid @ModelAttribute("workingShift") WorkingShift workingShift,BindingResult result,Model model ) {   	
    	
    	Optional<EndOfTheDay> end = endOfTheDayRepository.findByState(State.NOTSENT);
    	
    	workingShift.setDate(LocalDate.now().plusDays(1));
    	
    	workingShiftService.createWorkingShift(end.get().getId(), workingShift);
    	model.addAttribute("users");
    	
    	return "redirect:/profitForm/" + end.get().getId();
    }
    @RequestMapping(value="/editShift/{id}", method = RequestMethod.POST)
    public String updateUser(@PathVariable("id") Long id, @Valid WorkingShift shiftch, 
      BindingResult result, Model model) {
    	
    	WorkingShift workingShift = workingShiftRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DrinkDayData", "id", id));
    	workingShift.setDate(workingShift.getDate().plusDays(1));
    	workingShift.setProfitFromCash(shiftch.getProfitFromCash());
    	
      
        workingShiftRepository.save(workingShift);
      
        return "redirect:/profitForm/"+ workingShift.getEndOfTheDay().getId();
    }
    
    @RequestMapping(value = "/delete_profitDay/{id}", method = RequestMethod.POST)
    public String handleDeleteUser(@ModelAttribute("user") WorkingShift user) {
    	WorkingShift workingShift = workingShiftRepository.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("DrinkDayData", "id", user.getId()));
    	
        Long id = workingShift.getEndOfTheDay().getId();
     
        workingShiftRepository.delete(workingShift);
        
        return "redirect:/profitForm/"+id;
    }

    
    @RequestMapping(value = "/addProfitDay/{id}")
    public ModelAndView addDay(@PathVariable(value = "id")Long ssId,Model model) {
    	
    	Optional<EndOfTheDay> form = endOfTheDayService.getEndOfTheDayById(ssId); 	

    	ModelAndView m = new ModelAndView();
    	m.setViewName("addDayProfit");
    	m.addObject("iceDayProfit", 0);
    	m.addObject("drinkDayProfit",0);
    	
    	Optional<IceCreamDayData> dayData = iceCreamDayDataRepository.findByDate(LocalDate.now());
   
    	Optional<DrinkDayData> drinkDay = drinkDayDataRepository.findByDate(LocalDate.now());
    	
    	m.addObject("iceDayProfit",dayData.get().getProfit());
    	m.addObject("drinkDayProfit",drinkDay.get().getProfit());
    	m.addObject("form",form.get());
    	m.addObject("date",LocalDate.now());
    	m.addObject("workingShift",new WorkingShift());
    	
    	return m;
     }
    
    
    @RequestMapping(value = "/editProfitDay/{id}")
    public ModelAndView editDay(@PathVariable(value = "id")Long ssId,Model model) {
    	
    	Optional<WorkingShift> shift = workingShiftService.getDayById(ssId);
    	
    	WorkingShift shif = shift.get();
    	
    	ModelAndView m = new ModelAndView();
    	m.setViewName("editDayProf");
    	m.addObject("iceProf", 0);
    	m.addObject("drinkProf",0);
    	
    	Optional<IceCreamDayData> dayData = iceCreamDayDataRepository.findByDate(shift.get().getDate());
    	
    	if(!dayData.isPresent()) {
    		throw new ResourceNotFoundException("IceCreamDayData", "date", shift.get().getDate());
    	}
    	
    	Optional<DrinkDayData> drinkDay = drinkDayDataRepository.findByDate(shift.get().getDate());
    	
    	if(!drinkDay.isPresent()) {
    		throw new ResourceNotFoundException("DrinkDayData", "date", shift.get().getDate());
    	}
    	
    	m.addObject("icePro",dayData.get().getProfit());
    	m.addObject("drinkProf",drinkDay.get().getProfit());
    	m.addObject("shift",shif);
    	m.addObject("date",LocalDate.now());
    	
    	return m;
     }
    
   

    
}
