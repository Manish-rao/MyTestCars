package com.auto.controller;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.auto.dto.CarDetailsDTO;
import com.auto.entity.CarDetails;
import com.auto.entity.MoviesEntity;
import com.auto.service.CarDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/cars")
public class CarDetailsController {
	
	@Autowired
	CarDetailsService carDetailsService;
	
	private String serviceUrl = "https://ghibliapi.herokuapp.com/films";
	
	@PostMapping("/createCarDetails")
	@Operation(summary = "Add new car")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "201", description = "Added car", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema (implementation = CarDetailsDTO.class)) }),
			  @ApiResponse(responseCode = "404", description = "ManufacturerNotFound", 
			    content = @Content)})
	public ResponseEntity<CarDetails> createCarEntity(@Valid @RequestBody CarDetailsDTO carDetailsDto){
		return new ResponseEntity<CarDetails>(carDetailsService.createCarDetails(carDetailsDto), HttpStatus.CREATED);
	}
	
	@GetMapping("/findByManufacturere/{manufacturer}")
	public List<CarDetails> findByManufacturer(@PathVariable String manufacturer){
		return carDetailsService.findCarsByManufacturer(manufacturer);
	}
	
	@GetMapping("/findAll")
	public Page<CarDetails> findAll( @RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "carId") String sortBy){
		return carDetailsService.findAllCars(pageNo, pageSize, sortBy);
	}
	
	@GetMapping("/searchCars/criteria")
	public List<CarDetails> searchMultiple(@RequestParam(name="cc", required = false) Integer cc
			,
					@RequestParam(defaultValue = "0") Integer pageNo, 
		            @RequestParam(defaultValue = "10") Integer pageSize,
		            @RequestParam(defaultValue = "id") String sortBy ,@RequestParam(name="year", required = false) Year year){
	Page<CarDetails> pagedResult =  carDetailsService.findAllCars(pageNo, pageSize, sortBy);	
	List<CarDetails> myList;
    if(pagedResult.hasContent()) {
    	myList =  pagedResult.getContent();
    } else {
    	myList =  new ArrayList<CarDetails>();
    }
	if(cc!=null) {
		myList.removeIf(x->x.getCc()!=cc);
	}
	if(year!=null) {
		myList.removeIf(x->!x.getManufactureYear().equals(year));
	}
	return myList;
	}
	
	@GetMapping("/findMovies")
	public List<MoviesEntity> getMovieDetails() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(serviceUrl, Object[].class);
		Object[] moviesArray = responseEntity.getBody();
		ObjectMapper mapper = new ObjectMapper();
		return Arrays.stream(moviesArray)
				.map(object -> mapper.convertValue(object, MoviesEntity.class))
				.collect(Collectors.toList());
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable long id){
		carDetailsService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}
