package dev.patika.VeterinaryApp.service;

import dev.patika.VeterinaryApp.core.exception.DuplicationException;
import dev.patika.VeterinaryApp.core.exception.NotFoundException;
import dev.patika.VeterinaryApp.core.utilities.Message;
import dev.patika.VeterinaryApp.dto.request.AvailableDateRequest;
import dev.patika.VeterinaryApp.dto.response.AvailableDateResponse;
import dev.patika.VeterinaryApp.entity.AvailableDate;
import dev.patika.VeterinaryApp.core.mapper.AvailableDateMapper;
import dev.patika.VeterinaryApp.repository.AvailableDateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvailableDateService {
    private final AvailableDateRepository availableDateRepository;
    private final AvailableDateMapper availableDateMapper;


    public List<AvailableDateResponse> findAll() {
        return availableDateMapper.asOutput((availableDateRepository.findAll()));
    }

    // Find the data with the specified ID from the database, throw an exception if not found.
    public AvailableDateResponse getById(Long id) {
        return availableDateMapper.asOutput(availableDateRepository.findById(id).orElseThrow(() ->
                new RuntimeException("The available date with ID " + id + " could not be found!")));
    }
    // Convert the found date to AvailableResponse type (using availableDateMapper) and return it.


    public AvailableDateResponse create(AvailableDateRequest request) {
        Optional<AvailableDate> isAvailableDateExist = availableDateRepository.findByAvailableDate(request.getAvailableDate());
        if (isAvailableDateExist.isPresent()) {
            throw new RuntimeException("The date is not available!");
        } else {
            AvailableDate availableDateSaved = availableDateRepository.save(availableDateMapper.asEntity(request));
            return availableDateMapper.asOutput(availableDateSaved);
        }

    }

    public AvailableDateResponse update(Long id, AvailableDateRequest request) {
        Optional<AvailableDate> availableDateFromDb = availableDateRepository.findById(id);
        Optional<AvailableDate> isAvailableDateExist = availableDateRepository.findByAvailableDate(request.getAvailableDate());

        if (availableDateFromDb.isEmpty()) {
            throw new NotFoundException(Message.NOT_FOUND + " The date with the ID " + id + " that you are trying to update could not be found in the system!");
        }
        if (isAvailableDateExist.isPresent()) {
            throw new DuplicationException(Message.ALREADY_EXIST + " This date has already been registered in the system!");
        }
        AvailableDate availableDate = availableDateFromDb.get();
        availableDateMapper.update(availableDate, request);
        return availableDateMapper.asOutput((availableDateRepository.save(availableDate)));
    }

    //The object with a specific ID is deleted from the database using deleteById.
    //Since there is no need for a special conversion, the mapper class is not used.
    public void deleteById(Long id) {
        Optional<AvailableDate> availableDateFromDb = availableDateRepository.findById(id);
        if (availableDateFromDb.isPresent()) {
            availableDateRepository.delete(availableDateFromDb.get());
        } else {
            throw new RuntimeException("The date with the ID " + id + " could not be found in the system!");
        }

    }


    // Checks are made whether a particular doctor is available for the specified day and time.
    public boolean isDoctorAvailable(Long doctorId, LocalDateTime dateTime) {
        List<AvailableDate> availableDateList = availableDateRepository
                .findByDoctorIdAndAvailableDate(doctorId, dateTime.toLocalDate());
        return !availableDateRepository.existsByDoctorIdAndAvailableDate(doctorId,
                dateTime.toLocalDate());
    }

    public List<AvailableDateResponse> findByDoctorId(Long doctorId) {
        return availableDateMapper.asOutput(availableDateRepository.findByDoctorId(doctorId));
    }
}




