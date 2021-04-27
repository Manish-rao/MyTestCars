package com.auto.service;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mappings;

import com.auto.dto.ManufacturerDTO;
import com.auto.entity.Manufacturer;

public interface ManufacturerMapper {
	@Mappings({
		// no specific mappings
	})
	ManufacturerDTO mapEntityToDto(final Manufacturer model);

	@InheritInverseConfiguration
	Manufacturer mapDtoToEntity(final ManufacturerDTO dto);
}
