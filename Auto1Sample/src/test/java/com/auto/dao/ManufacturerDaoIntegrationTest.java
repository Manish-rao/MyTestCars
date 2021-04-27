package com.auto.dao;

import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;

import com.auto.entity.Manufacturer;
import com.auto.repo.ManufacturerRepo;

@SpringBootTest
@Transactional
@DirtiesContext
@AutoConfigureTestEntityManager
public class ManufacturerDaoIntegrationTest {

	@Autowired
	ManufacturerRepo manufacturerRepo;
	
	@Autowired
	TestEntityManager entityManager;
	
	static String name = "Ford";
	
	@BeforeEach
	public void setup() {
		Manufacturer manufacturer = new Manufacturer(name);
		entityManager.persist(manufacturer);
		entityManager.flush();
	}
	
	@Test
	@Rollback(true)	
	public void givenName_thenReturnManufacturer() {
		final Manufacturer manufacturer = manufacturerRepo.findByCompanyName(name).get();
		System.out.println("*********"+manufacturer.getManufacturerId());
		Assertions.assertThat(manufacturer.getCompanyName())
		.isEqualTo(name);
	}
}
