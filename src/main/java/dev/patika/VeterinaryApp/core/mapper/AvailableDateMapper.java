package dev.patika.VeterinaryApp.core.mapper;

import dev.patika.VeterinaryApp.dto.request.AvailableDateRequest;
import dev.patika.VeterinaryApp.dto.request.CustomerRequest;
import dev.patika.VeterinaryApp.dto.response.AvailableDateResponse;
import dev.patika.VeterinaryApp.dto.response.CustomerResponse;
import dev.patika.VeterinaryApp.entity.AvailableDate;
import dev.patika.VeterinaryApp.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface AvailableDateMapper {

    AvailableDate asEntity(AvailableDateRequest availableDateRequest);
    AvailableDateResponse asOutput(AvailableDate availableDate);
    List<AvailableDateResponse> asOutput(List<AvailableDate> availableDateList);
    void update(@MappingTarget AvailableDate entity, AvailableDateRequest request);



}
