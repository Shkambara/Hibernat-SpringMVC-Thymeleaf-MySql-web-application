package com.example.easynotes.services;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.IceCream;
import com.example.easynotes.model.IceCreamDayData;
import com.example.easynotes.repository.IceCreamRepository;
import com.example.easynotes.repository.IceCreamDayDataRepository;

@Service
public class IceCreamService {
	
	
	@Autowired
	IceCreamDayDataRepository dayRepository;
	
	@Autowired
	IceCreamRepository iceRepository;
	
	public List<IceCream> getAllDays(){
		return iceRepository.findAll();
	}
	
	public Optional<IceCream> getDayById(Long id){
		if(!iceRepository.existsById(id)) {
			throw new ResourceNotFoundException("IceCream", "id", id);

		}
		return iceRepository.findById(id);
	}
// NOT FINISHED **	
	public IceCream createIceCream(Long IceCreamDayDataId, IceCream IceCream) {
		Set<IceCream> iceCreams = new HashSet<>();
		Set<IceCreamDayData> form1 = new HashSet<>();
		
		Optional<IceCreamDayData> form_id = dayRepository.findById(IceCreamDayDataId);
		if(!form_id.isPresent()) {
			throw new ResourceAccessException("IceCreamDayData with id " + form_id + " not found");
		}
		IceCreamDayData form = form_id.get();
		//IceCream.(form);
		
		IceCream day = iceRepository.save(IceCream);
		
		//days.add(day);
		//form1.setDayData(days);
		
		return day;
		
		}
	
}
