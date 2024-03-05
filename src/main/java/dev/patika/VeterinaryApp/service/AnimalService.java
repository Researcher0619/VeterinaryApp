package dev.patika.VeterinaryApp.service;

import dev.patika.VeterinaryApp.core.exception.DuplicationException;
import dev.patika.VeterinaryApp.core.exception.NotFoundException;
import dev.patika.VeterinaryApp.core.utilities.Message;
import dev.patika.VeterinaryApp.dto.request.AnimalRequest;
import dev.patika.VeterinaryApp.dto.response.AnimalResponse;
import dev.patika.VeterinaryApp.entity.Animal;
import dev.patika.VeterinaryApp.core.mapper.AnimalMapper;
import dev.patika.VeterinaryApp.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;

    public List<AnimalResponse> findAll() {
        return animalMapper.asOutput(animalRepository.findAll());
    }

    public AnimalResponse getById(Long id) {
        return animalMapper.asOutput(animalRepository.findById(id).orElseThrow(() ->
                new NotFoundException(Message.NOT_FOUND + "Animal with ID " + id)));
    }

    public AnimalResponse create(AnimalRequest request) {
        Optional<Animal> isAnimalExist = animalRepository.findByNameAndSpeciesAndBreed(
                request.getName(), request.getSpecies(), request.getBreed());

        if (isAnimalExist.isEmpty()) {
            Animal animalSaved = animalRepository.save(animalMapper.asEntity(request));
            return animalMapper.asOutput(animalSaved);
        }
        throw new DuplicationException("This animal has already been registered in the system!");
    }

    public AnimalResponse update(Long id, AnimalRequest request) {

        // Search for the animal to be updated in the database using the provided animal ID.
        Optional<Animal> animalFromDb = animalRepository.findById(id);

        // Checking if there is another animal with the same customer, name, species, and breed as the new information.
        Optional<Animal> isAnimalExist = animalRepository.findByCustomerIdAndNameAndSpeciesAndBreed(request.getCustomer().getId(), request.getName(), request.getSpecies(), request.getBreed());
        if (animalFromDb.isEmpty()) {
            throw new NotFoundException(Message.NOT_FOUND + " The animal with the ID " + id + " that you are trying to update could not be found in the system!");
        }
        if (isAnimalExist.isPresent() && !isAnimalExist.get().getId().equals(id)) {
            throw new DuplicationException(Message.ALREADY_EXIST + " This animal has already been registered in the system!");
        }
        Animal animal = animalFromDb.get();
        animalMapper.update(animal, request);
        return animalMapper.asOutput((animalRepository.save(animal)));
    }

    public void deleteById(Long id) {
        Optional<Animal> animalFromDb = animalRepository.findById(id);
        if (animalFromDb.isPresent()) {
            animalRepository.delete(animalFromDb.get());
        } else {
            throw new NotFoundException(Message.NOT_FOUND + " The animal with the ID " + id + " could not be found in the system!");
        }
    }

    public List<AnimalResponse> findByName(String name) {
        return animalMapper.asOutput(animalRepository.findByName(name));
    }

    public List<AnimalResponse> findByCustomerName(String customerName) {
        return animalMapper.asOutput(animalRepository.findByCustomerName(customerName));
    }

    public List<AnimalResponse> findByCustomerId(Long customerId) {
        return animalMapper.asOutput(animalRepository.findByCustomerId(customerId));
    }

}
