package com.auto.entity;

import java.time.Year;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "CAR_DETAILS")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int carId;
	
	@ManyToOne
	private Manufacturer manufacturer;
	
	@Column(name="year")
	private Year manufactureYear;
	
	@Column(name="CC")
	private int cc;
	
	@Enumerated(EnumType.STRING)
	@Column(name="car_type")
	private CarType carType;

	
	
	
}
