package com.auto.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.auto.dto.ManufacturerDTO;
import com.auto.entity.Manufacturer;
import com.auto.exception.ManufacturerNotFoundException;
import com.auto.repo.ManufacturerRepo;

@SpringBootTest
public class ManufacturerServiceIntegrationTest {

	@Autowired
	ManufacturerService manufacturerService;
	
	@Mock
	ManufacturerRepo manufacturerRepo;
	@Test
	public void retrieveAllBasic() {
		List<Manufacturer> manList = manufacturerService.findAll();
		assertEquals("BMW", manList.get(0).getCompanyName());
		assertEquals("AUDI", manList.get(1).getCompanyName());
	}
	
	@Test
	public void getByComapany_no_exception() {
		Manufacturer manufacturer = manufacturerService.getManufacturersByName("BMW");
		assertNotNull(manufacturer);
		assertEquals(manufacturer.getCompanyName(), "BMW");
	}
	
	@Test
	public void getByComapany_throw_exception() {
			assertThrows(ManufacturerNotFoundException.class,()-> manufacturerService.getManufacturersByName("BAJKDH"));
	}
	

	@Test
	public void createNewManufacturer() {
		/*
		 * Manufacturer manufacturer = new Manufacturer();
		 * manufacturer.setCompanyName("Ford");
		 * BDDMockito.given(manufacturerRepo.findByCompanyName(manufacturer.
		 * getCompanyName())) .willReturn(Optional.empty());
		 * BDDMockito.given(manufacturerRepo.save(manufacturer)).willAnswer(invocation->
		 * invocation.getArgument(0));
		 * verify(manufacturerRepo).save(Mockito.any(Manufacturer.class));
		 */
		ManufacturerDTO manufacturerDTO = new ManufacturerDTO();
		manufacturerDTO.setCompanyName("Ford");
		Manufacturer servManufacturer = manufacturerService.saveManufacturer(manufacturerDTO);
		assertNotNull(servManufacturer);
		assertEquals(4L, servManufacturer.getManufacturerId());
		
	}
}
