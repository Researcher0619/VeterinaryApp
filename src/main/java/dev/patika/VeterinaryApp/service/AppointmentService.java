package dev.patika.VeterinaryApp.service;

import dev.patika.VeterinaryApp.core.exception.DoctorAppointmentException;
import dev.patika.VeterinaryApp.core.exception.DuplicationException;
import dev.patika.VeterinaryApp.core.exception.NotFoundException;
import dev.patika.VeterinaryApp.core.utilities.Message;
import dev.patika.VeterinaryApp.dto.request.AppointmentRequest;
import dev.patika.VeterinaryApp.dto.response.AppointmentResponse;
import dev.patika.VeterinaryApp.entity.Appointment;
import dev.patika.VeterinaryApp.core.mapper.AppointmentMapper;
import dev.patika.VeterinaryApp.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final AvailableDateService availableDateService;

    public List<AppointmentResponse> findAll() {
        return appointmentMapper.asOutput((appointmentRepository.findAll()));
    }

    public AppointmentResponse getById(Long id) {
        return appointmentMapper.asOutput(appointmentRepository.findById(id).orElseThrow(() ->
                new NotFoundException(Message.NOT_FOUND + " The appointment with ID " + id + " could not be found!")));
    }

    public AppointmentResponse create(AppointmentRequest request) {
        LocalDateTime dateTime = request.getAppointmentDate();
        Long doctorId = request.getDoctor().getId();

        // Checks whether a specific doctor is available for the specified day and time.
        if (availableDateService.isDoctorAvailable(doctorId, dateTime)) {
            throw new DoctorAppointmentException("The doctor is not available for today. Please choose a new date.");
        }
        if (isAppointmentDateExistOnDate(doctorId, dateTime)) {
            throw new RuntimeException("There is another appointment at the entered time.");
            // Makes an appointment.
        } else {
            Appointment appointmentSaved = appointmentRepository.save(appointmentMapper.asEntity(request));
            return appointmentMapper.asOutput(appointmentSaved);
        }
    }

    // Checks if a specific doctor has another appointment on the specified date and time.
    private boolean isAppointmentDateExistOnDate(Long doctorId, LocalDateTime appointmentDate) {
        return appointmentRepository.existsByDoctorIdAndAppointmentDate(doctorId, appointmentDate);
    }

    public AppointmentResponse update(Long id, AppointmentRequest request) {
        Optional<Appointment> appointmentFromDb = appointmentRepository.findById(id);
        Optional<Appointment> isAppointmentExist = appointmentRepository.findByAppointmentDate(request.getAppointmentDate());

        if (appointmentFromDb.isEmpty()) {
            throw new NotFoundException(Message.NOT_FOUND + " The appointment with ID " + id + " could not be found for the update!");
        }
        if (isAppointmentExist.isPresent()) {
            throw new DuplicationException("An appointment has already been scheduled for this date!");
        }
        Appointment appointment = appointmentFromDb.get();
        appointmentMapper.update(appointment, request);
        return appointmentMapper.asOutput((appointmentRepository.save(appointment)));
    }

    public void deleteById(Long id) {
        Optional<Appointment> appointmentFromDb = appointmentRepository.findById(id);
        if (appointmentFromDb.isPresent()) {
            appointmentRepository.delete(appointmentFromDb.get());
        } else {
            throw new NotFoundException(Message.NOT_FOUND + " The appointment with ID " + id + " could not be found in the system!");
        }
    }

    public List<AppointmentResponse> findByAppointmentDateBetweenAndAnimalId(LocalDateTime startDate, LocalDateTime endDate, Long animalId) {
        return appointmentMapper.asOutput(appointmentRepository.findByAppointmentDateBetweenAndAnimalId(startDate, endDate, animalId));
    }

    public List<AppointmentResponse> findByAppointmentDateBetweenAndDoctorId(LocalDateTime startDate, LocalDateTime endDate, Long doctorId) {
        return appointmentMapper.asOutput(appointmentRepository.findByAppointmentDateBetweenAndDoctorId(startDate, endDate, doctorId));
    }
}






