package com.auto.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.auto.dto.ManufacturerDTO;
import com.auto.entity.Manufacturer;
import com.auto.service.ManufacturerMapper;
import com.auto.service.ManufacturerService;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping("/company")
@Secured("ADMIN")
public class ManufacturerController {

	@Autowired
	ManufacturerService manufacturerService;
	
	@PostMapping("/createComp")
	public ResponseEntity<Manufacturer> createCarEntity(@Valid @RequestBody ManufacturerDTO manufacturerDTO){
		//return new ResponseEntity<Manufacturer>(manufacturerService.saveManufacturer(manufacturerDTO), HttpStatus.CREATED);
		Manufacturer manDo = manufacturerService.saveManufacturer(manufacturerDTO);
		log.info(manDo.getCompanyName());
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.contentType(MediaType.APPLICATION_JSON)
				.body(manDo);
	}
	
	@GetMapping("/findByCarByMake/{manufacturer}")
	@ResponseStatus(code = HttpStatus.OK)
	public Manufacturer findByManufacturer(@PathVariable String manufacturer){
		return manufacturerService.getManufacturersByName(manufacturer);
	} 
	
	@GetMapping("/findAllCars")
	public List<Manufacturer> findManufacturers(){
		return manufacturerService.findAll();
	}
}
