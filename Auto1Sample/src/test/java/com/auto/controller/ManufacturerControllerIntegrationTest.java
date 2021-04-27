package com.auto.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.auto.dto.ManufacturerDTO;
import com.auto.service.ManufacturerMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc

public class ManufacturerControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	
	
	@MockBean(name ="manufacturerMapper")
	private ManufacturerMapper manufacturerMapper;
	
	@Test
	@WithMockUser(username = "user", password = "password", roles = {"ADMIN"})
	void addManufacturer() throws IOException, Exception {
		
		ManufacturerDTO manufacturerDTO = new ManufacturerDTO();
		manufacturerDTO.setCompanyName("BMW1");
				
		mockMvc.perform(MockMvcRequestBuilders.post("/company/createComp")
												.contentType(MediaType.APPLICATION_JSON)
												.accept(MediaType.APPLICATION_JSON)
												.content(toJson(manufacturerDTO)))
		.andExpect(MockMvcResultMatchers.status().isCreated())
		.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(MockMvcResultMatchers.jsonPath("$.companyName").value("BMW1"));
		
	}
	
	@Test
	public void getAllManufacturers_throwUnauthorized() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/findAllCars")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnauthorized());
	}
	
	@Test
	@WithMockUser(username = "user", password = "password", roles = {"ADMIN"})
	public void getAllManufacturers() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/company/findAllCars")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.[*].companyName").isNotEmpty());
	}
	 
	@Test
	@WithMockUser(username = "user", password = "password", roles = {"ADMIN"})
	public void getAllManufacturerByMake() throws Exception 
	{
		mockMvc.perform( MockMvcRequestBuilders
	      .get("/company/findByCarByMake/{manufacturer}", "BMW")
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "user", password = "password", roles = {"ADMIN"})
	public void throwExceptionForInvalidCarMake() throws Exception{
		mockMvc.perform( MockMvcRequestBuilders
			      .get("/company/findByCarByMake/{manufacturer}", "BMW324234")
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isNotFound())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Manufacturuer not found"));
	}
	
	public static byte[] toJson(final Object object) throws IOException {

		final ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}
	
		
}
