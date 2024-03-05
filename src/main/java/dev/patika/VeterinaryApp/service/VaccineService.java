package dev.patika.VeterinaryApp.service;

import dev.patika.VeterinaryApp.core.exception.DuplicationException;
import dev.patika.VeterinaryApp.core.exception.NotFoundException;
import dev.patika.VeterinaryApp.core.utilities.Message;
import dev.patika.VeterinaryApp.dto.request.VaccineRequest;
import dev.patika.VeterinaryApp.dto.response.VaccineResponse;
import dev.patika.VeterinaryApp.entity.Vaccine;
import dev.patika.VeterinaryApp.core.mapper.VaccineMapper;
import dev.patika.VeterinaryApp.repository.VaccineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VaccineService {
    private final VaccineRepository vaccineRepository;
    private final VaccineMapper vaccineMapper;

    public List<VaccineResponse> findAll() {
        return vaccineMapper.asOutput((vaccineRepository.findAll()));
    }

    public VaccineResponse getById(Long id) {
        return vaccineMapper.asOutput(vaccineRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Vaccine with ID " + id + " not found")));
    }

    public VaccineResponse create(VaccineRequest request) {
        List<Vaccine> isVaccineExist = vaccineRepository
                .findByAnimalIdAndCodeAndProtectionFinishDateAfter(
                        request.getAnimal().getId(),
                        request.getCode(),
                        request.getProtectionStartDate());
        if (!isVaccineExist.isEmpty()) {
            throw new RuntimeException("The vaccine protection is still active, you cannot add a new vaccine.");
        } else {
            Vaccine vaccineSaved = vaccineRepository.save(vaccineMapper.asEntity(request));
            return vaccineMapper.asOutput(vaccineSaved);
        }
    }

    public VaccineResponse update(Long id, VaccineRequest request) {

        // searches for a vaccine with the given ID, when found it is assigned to the variable vaccineFromDb.
        Optional<Vaccine> vaccineFromDb = vaccineRepository.findById(id);

        // check if it has the same animal id and the specified features.
        Optional<Vaccine> isVaccineExist = vaccineRepository.findByAnimalIdAndNameAndCode(request.getAnimal().getId(), request.getName(), request.getCode());


        if (vaccineFromDb.isEmpty()) {
            throw new NotFoundException(Message.NOT_FOUND + " The vaccine with ID " + id + " could not be found in the system for the update!");
        }

        if (isVaccineExist.isPresent() && !isVaccineExist.get().getId().equals(id)) {
            // throws an error if the id found is not equal to the id to be updated.
            throw new DuplicationException(Message.ALREADY_EXIST + " Another vaccine with the same name and code already exists in the system!");
        }

        Vaccine vaccine = vaccineFromDb.get();
        vaccineMapper.update(vaccine, request);
        return vaccineMapper.asOutput((vaccineRepository.save(vaccine)));
    }

    public void deleteById(Long id) {
        Optional<Vaccine> vaccineFromDb = vaccineRepository.findById(id);
        if (vaccineFromDb.isPresent()) {
            vaccineRepository.delete(vaccineFromDb.get());
        } else {
            throw new NotFoundException(Message.NOT_FOUND + " The vaccine with the ID " + id + " could not be found in the system!");
        }
    }

    public List<VaccineResponse> findByAnimalId(Long animalId) {
        return vaccineMapper.asOutput(vaccineRepository.findByAnimalId(animalId));
    }

    public List<VaccineResponse> findByProtectionFinishDateBetween(LocalDate protectionStartDate, LocalDate protectionFinishDate) {
        return vaccineMapper.asOutput(vaccineRepository.findByProtectionFinishDateBetween(protectionStartDate, protectionFinishDate));
    }
}






















































