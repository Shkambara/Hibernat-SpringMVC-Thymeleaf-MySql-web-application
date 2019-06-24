package com.example.easynotes.controller;

import java.time.LocalDate;
import java.util.List;
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
import com.example.easynotes.model.IceCream;
import com.example.easynotes.model.IceCreamDayData;
import com.example.easynotes.model.IceCreamForm;
import com.example.easynotes.model.State;
import com.example.easynotes.model.WorkingShift;
import com.example.easynotes.repository.IceCreamDayDataRepository;
import com.example.easynotes.repository.IceCreamFormRepository;
import com.example.easynotes.repository.IceCreamRepository;
import com.example.easynotes.services.IceCreamDayDataService;
import com.example.easynotes.services.IceCreamFormService;

@Controller
public class IceCreamDayDataController {

    @Autowired
    IceCreamDayDataRepository iceCreamDayDataRepository;
    
    @Autowired
    IceCreamDayDataService iceCreamDayDataService;
    
    @Autowired
    IceCreamFormService iceCreamFormService;
    
    @Autowired
    IceCreamFormRepository iceCreamFormRepository;
    
    @Autowired
    IceCreamRepository iceCreamRepository;
    
    @RequestMapping(value = "/addIceDay/{id}")
    public ModelAndView addDay(@PathVariable(value = "id")Long id,Model model) {
    	
    	Optional<IceCreamForm> form = iceCreamFormService.getIceCreamFormById(id);
    	
    	List<IceCream> iceCreams = iceCreamRepository.findAll();
    	
    	ModelAndView m = new ModelAndView();
    	m.setViewName("addDayIce");
    	m.addObject("form",form.get());
    	m.addObject("date",LocalDate.now());
    	m.addObject("iceData",new IceCreamDayData());
    	m.addObject("rows",new IceCream());
    	m.addObject("allVarieties",iceCreams);
    	
    	return m;
     }
    @RequestMapping(value = "/editIceData/{id}")
    public ModelAndView editDay(@PathVariable(value = "id")Long id,Model model) {
    	
    	Optional<IceCreamDayData> day = iceCreamDayDataService.getDayById(id);
    	
    	ModelAndView m = new ModelAndView();
    	m.setViewName("EditDayIce");
    	m.addObject("iceData",day.get());
    	
    	return m;
     }
    
    @RequestMapping(value = "/addIceData", method = RequestMethod.POST)
    public String createIceData(@Valid @ModelAttribute("iceData") IceCreamDayData iceData,BindingResult result,Model model ) {   	
    	
    	Optional<IceCreamForm> iceForm = iceCreamFormRepository.findByState(State.NOTSENT);
    	
    	iceData.setDate(LocalDate.now().plusDays(1));
    	
    	iceCreamDayDataService.createIceCreamDayData(iceForm.get().getId(), iceData);
    
    	return "redirect:/iceCreamForm/" + iceForm.get().getId();
    }
    @RequestMapping(value = "/delete_iceDay/{id}", method = RequestMethod.POST)
    public String handleDeleteDay(@ModelAttribute("day") WorkingShift day) {
    	IceCreamDayData iceData = iceCreamDayDataRepository.findById(day.getId())
                .orElseThrow(() -> new ResourceNotFoundException("DrinkDayData", "id", day.getId()));
        Long id = iceData.getIceForm().getId();
        
        iceCreamDayDataRepository.delete(iceData);
        
        return "redirect:/iceCreamForm/" + id;
    }
    
    @RequestMapping(value="/editDay/{id}", method = RequestMethod.POST)
    public String updateDay(@PathVariable("id") Long id, @Valid IceCreamDayData iceDataDescription, 
      BindingResult result, Model model) {
    	
    	IceCreamDayData iceData = iceCreamDayDataRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DrinkDayData", "id", id));
    	iceData.setDate(iceData.getDate().plusDays(1));
    	iceData.setScoopsSold(iceDataDescription.getScoopsSold());
    	
      
        iceCreamDayDataRepository.save(iceData);
      
        return "redirect:/iceCreamForm/"+iceData.getIceForm().getId();
    }
    
   @RequestMapping(value = "/showDetails/{id}")
    public ModelAndView showDay(@PathVariable(value = "id")Long id,Model model) {
    	
    	Optional<IceCreamDayData> iceDay = iceCreamDayDataService.getDayById(id);
    	
    	ModelAndView m = new ModelAndView();
    	m.setViewName("showDayIce");
    	m.addObject("day",iceDay.get());

    	return m;
     }
   
}
