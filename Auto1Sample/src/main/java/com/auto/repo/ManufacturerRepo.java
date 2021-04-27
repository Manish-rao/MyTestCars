package com.auto.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auto.entity.Manufacturer;

@Repository
public interface ManufacturerRepo extends JpaRepository<Manufacturer, Long>{
	
	Optional<Manufacturer> findByCompanyName(String companyName);
}
