package dev.patika.VeterinaryApp.repository;

import dev.patika.VeterinaryApp.entity.Animal;
import dev.patika.VeterinaryApp.entity.Appointment;
import dev.patika.VeterinaryApp.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Optional<Appointment> findByAppointmentDate (LocalDateTime appointmentDate);

    // doctor to check if she/he has an appointment on the day he is available and at the specified time
    boolean existsByDoctorIdAndAppointmentDate(Long doctorId, LocalDateTime appointmentDate);

    // appointment search by entered date range and animal
    List<Appointment> findByAppointmentDateBetweenAndAnimalId
    (LocalDateTime startDate, LocalDateTime endDate, Long animalId);

    // appointment search by entered date range and doctor
    List<Appointment> findByAppointmentDateBetweenAndDoctorId
    (LocalDateTime startDate, LocalDateTime endDate, Long doctorId);

}

