package dev.patika.VeterinaryApp.core.mapper;

import dev.patika.VeterinaryApp.dto.request.AnimalRequest;
import dev.patika.VeterinaryApp.dto.response.AnimalResponse;
import dev.patika.VeterinaryApp.entity.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface AnimalMapper {

    Animal asEntity(AnimalRequest animalRequest);
    AnimalResponse asOutput(Animal animal);
    List<AnimalResponse> asOutput(List<Animal> animalList);
    void update(@MappingTarget Animal entity, AnimalRequest animalRequest);

}
