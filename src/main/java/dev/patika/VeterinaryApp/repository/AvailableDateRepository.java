package dev.patika.VeterinaryApp.repository;

import dev.patika.VeterinaryApp.entity.Appointment;
import dev.patika.VeterinaryApp.entity.AvailableDate;
import dev.patika.VeterinaryApp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AvailableDateRepository extends JpaRepository<AvailableDate, Long> {

    Optional<AvailableDate> findByAvailableDate (LocalDate availableDate);


    List<AvailableDate> findByDoctorId(Long doctorId);

    List<AvailableDate> findByDoctorIdAndAvailableDate(Long doctorId, LocalDate availableDate);

    boolean existsByDoctorIdAndAvailableDate(Long doctorId, LocalDate availableDate);


}
