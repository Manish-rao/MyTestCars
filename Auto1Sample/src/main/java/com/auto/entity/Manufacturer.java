package com.auto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;

@Entity
@Table(name="MANUFACTURER")
@Builder
public class Manufacturer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long manufacturerId;
	
	@Column(name="companyName")
	private String companyName;

	
	
	public Manufacturer() {
		super();
	}

	

	public Manufacturer(long manufacturerId, String companyName) {
		this.manufacturerId = manufacturerId;
		this.companyName = companyName;
	}



	public Manufacturer(String companyName) {
		this.companyName = companyName;
	}

	
	public long getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(long manufacturerId) {
		this.manufacturerId = manufacturerId;
	}



	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	

}
