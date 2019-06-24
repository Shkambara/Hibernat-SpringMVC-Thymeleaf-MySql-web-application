package com.example.easynotes.services;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.IceCream;
import com.example.easynotes.model.IceCreamDayData;
import com.example.easynotes.model.IceCreamForm;
import com.example.easynotes.repository.IceCreamDayDataRepository;
import com.example.easynotes.repository.IceCreamFormRepository;
import com.example.easynotes.repository.IceCreamRepository;

@Service
public class IceCreamDayDataService {
	
	
	@Autowired
	IceCreamFormRepository iceCreamFormRepository;
	
	@Autowired
	IceCreamDayDataRepository dayRepository;
	
	@Autowired
	IceCreamRepository iceCreamRepository;
	
	public List<IceCreamDayData> getAllDays(){
		return dayRepository.findAll();
	}
	
	public Optional<IceCreamDayData> getDayById(Long day_id){
		if(!dayRepository.existsById(day_id)) {
			
			throw new ResourceNotFoundException("IceCreamDayData", "id", day_id);
		}
		return dayRepository.findById(day_id);
	}
	
	public void addIce(Long iceCream_id,Long day_id) {

		Optional<IceCream> ice_id = iceCreamRepository.findById(iceCream_id);
		Optional<IceCreamDayData> dayData_id = dayRepository.findById(day_id);
		
		if(!ice_id.isPresent()) {
			throw new ResourceNotFoundException("IceCreamForm", "id", dayData_id);
		}
		
		if(!dayData_id.isPresent()) {
			throw new ResourceNotFoundException("IceCreamDayData", "id", dayData_id);
		}
		
		IceCreamDayData day = dayData_id.get();
		IceCream ic = ice_id.get();
		
		Set<IceCream> iceCreams = day.getIceCreamMade();
		iceCreams.add(ic);
		day.setIceCreamMade(iceCreams);
		
		Set<IceCreamDayData> days = ic.getIceCreamDayData();
		days.add(day);
		ic.setIceCreamDayData(days);
		
		iceCreamRepository.save(ic);
		dayRepository.save(day);
		
	}
	
	
	
	public IceCreamDayData createIceCreamDayData(Long IceCreamFormId, IceCreamDayData IceCreamDayData) {
		Set<IceCreamDayData> days = new HashSet<>();
		IceCreamForm form1 = new IceCreamForm();
		
		Optional<IceCreamForm> form_id = iceCreamFormRepository.findById(IceCreamFormId);
		
		if(!form_id.isPresent()) {
			throw new ResourceNotFoundException("IceCreamForm", "id", form_id);
		}
		
		IceCreamForm form = form_id.get();
		IceCreamDayData.setIceForm(form);
		
		IceCreamDayData day = dayRepository.save(IceCreamDayData);
		
		days.add(day);
		form1.setDayData(days);
		
		return day;
		
		}
	
}
