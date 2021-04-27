package com.auto.dto;

import java.time.Year;

import javax.validation.constraints.Min;

import com.auto.entity.CarType;
import com.auto.entity.Manufacturer;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude
public class CarDetailsDTO {
	
	private int carId;
	
	private Manufacturer manufacturer;
	
	private Year manufactureYear;
	
	@Min(value = 100, message = "CC cannot be less than 100")
	private int cc;
	
	private CarType carType;
	
	
	public CarDetailsDTO(Manufacturer manufacturer, Year manufactureYear, int cc, CarType carType) {
		super();
		this.manufacturer = manufacturer;
		this.manufactureYear = manufactureYear;
		this.cc = cc;
		this.carType = carType;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Year getManufactureYear() {
		return manufactureYear;
	}

	public void setManufactureYear(Year manufactureYear) {
		this.manufactureYear = manufactureYear;
	}

	public int getCc() {
		return cc;
	}

	public void setCc(int cc) {
		this.cc = cc;
	}

	public CarType getCarType() {
		return carType;
	}

	public void setCarType(CarType carType) {
		this.carType = carType;
	}
	
	
}
