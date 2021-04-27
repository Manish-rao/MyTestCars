package com.auto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.auto.dto.CarDetailsDTO;
import com.auto.entity.CarDetails;
import com.auto.entity.Manufacturer;
import com.auto.exception.ManufacturerNotFoundException;
import com.auto.exception.ResourceNotFoundException;
import com.auto.repo.CarDetailsRepo;
import com.auto.repo.ManufacturerRepo;

@Service
public class CarDetailsService {
	
	@Autowired
	CarDetailsRepo carDetailsRepo;
	
	private final ManufacturerRepo manuRepo;
	
	
	Log logger = LogFactory.getLog(CarDetailsService.class);
	
	public CarDetailsService(ManufacturerRepo manuRepo) {
		this.manuRepo = manuRepo;
		logger.info("Damn it!!!!!!!!!I am called");
		manuRepo.save(new Manufacturer("BMW"));
		manuRepo.save(new Manufacturer("AUDI"));
		manuRepo.save(new Manufacturer("JAGUAR"));
	}
	
	public CarDetails createCarDetails(CarDetailsDTO carDetailsDTO) {		
		String carCompany = carDetailsDTO.getManufacturer().getCompanyName();
		Optional<Manufacturer> manuOptional = manuRepo.findByCompanyName(carCompany);
		if(manuOptional.isPresent()) {
			carDetailsDTO.setManufacturer(manuOptional.get());
		}else {
			throw new ManufacturerNotFoundException("No manufacturer found with this name :"+carCompany);
		}
		CarDetails carDetails = new CarDetails();
		BeanUtils.copyProperties(carDetailsDTO, carDetails);
		carDetails = carDetailsRepo.save(carDetails);
		return carDetails;
	}
	
	public List<CarDetails> findCarsByManufacturer(String companyName){
		List<CarDetails> carDetailsList = new ArrayList<>();
		carDetailsList.addAll(carDetailsRepo.findByManufacturer(companyName));
		if(carDetailsList.isEmpty())
			throw new ManufacturerNotFoundException("No car found with manufacturer:"+companyName);
		return carDetailsList;
	}

	public Page<CarDetails> findAllCars(int pageNo, int pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<CarDetails> pagedResult = carDetailsRepo.findAll(paging);
        return pagedResult;
	}
	
	public void deleteById(long id) {
		CarDetails carDetails = carDetailsRepo.findById(id).orElseThrow(() -> new ManufacturerNotFoundException("No manufacturer found with this name :"+id));
		carDetailsRepo.delete(carDetails);
	}
}
