package com.auto.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.auto.entity.CarDetails;

@Repository
public interface CarDetailsRepo extends PagingAndSortingRepository<CarDetails, Long> {

	CarDetails findCarDetailsByCarId(long carId);
	
	@Query("SELECT cardetails from CarDetails as cardetails where cardetails.manufacturer.companyName = :companyName")
	List<CarDetails> findByManufacturer(String companyName);
}
