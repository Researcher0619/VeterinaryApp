package dev.patika.VeterinaryApp.core.mapper;

import dev.patika.VeterinaryApp.dto.request.CustomerRequest;
import dev.patika.VeterinaryApp.dto.request.DoctorRequest;
import dev.patika.VeterinaryApp.dto.response.CustomerResponse;
import dev.patika.VeterinaryApp.dto.response.DoctorResponse;
import dev.patika.VeterinaryApp.entity.Customer;
import dev.patika.VeterinaryApp.entity.Doctor;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface DoctorMapper {

    Doctor asEntity(DoctorRequest doctorRequest);
    DoctorResponse asOutput(Doctor doctor);
    List<DoctorResponse> asOutput(List<Doctor> doctorList);
    void update(@MappingTarget Doctor entity, DoctorRequest request);
}
