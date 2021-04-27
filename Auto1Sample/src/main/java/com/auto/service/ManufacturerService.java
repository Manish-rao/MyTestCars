package com.auto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auto.dto.ManufacturerDTO;
import com.auto.entity.Manufacturer;
import com.auto.exception.ManufacturerNotFoundException;
import com.auto.repo.ManufacturerRepo;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class ManufacturerService {
	
	@Autowired
	private ManufacturerRepo manufacturerRepo;
	
	
	public Manufacturer saveManufacturer(ManufacturerDTO manufacturerDTO) {
		Manufacturer manufacturer = new Manufacturer();
		BeanUtils.copyProperties(manufacturerDTO, manufacturer);
		return manufacturerRepo.save(manufacturer);
	}
	
	public Manufacturer saveManufacturer(Manufacturer manufacturer) {
		return manufacturerRepo.save(manufacturer);
	}

	public Manufacturer getManufacturersByName(String companyName){
		Optional<Manufacturer> manufacturer = manufacturerRepo.findByCompanyName(companyName);
		if(manufacturer.isEmpty())
			throw new ManufacturerNotFoundException("No manufacturer with this name was found");
		return manufacturer.get();
	}
	
	public List<Manufacturer> findAll(){
		log.info(manufacturerRepo.findAll()+"SIZEEEEEEEEEEEE");
		return manufacturerRepo.findAll();
	}
}
