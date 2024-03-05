package dev.patika.VeterinaryApp.core.mapper;

import dev.patika.VeterinaryApp.dto.request.VaccineRequest;
import dev.patika.VeterinaryApp.dto.response.VaccineResponse;
import dev.patika.VeterinaryApp.entity.Vaccine;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface VaccineMapper {

    Vaccine asEntity(VaccineRequest vaccineRequest);
    VaccineResponse asOutput (Vaccine vaccine);
    List<VaccineResponse> asOutput(List<Vaccine> vaccineList);
    void update(@MappingTarget Vaccine entity, VaccineRequest request);
}
