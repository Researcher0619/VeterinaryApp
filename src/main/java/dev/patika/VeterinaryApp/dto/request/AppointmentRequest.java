package dev.patika.VeterinaryApp.dto.request;

import dev.patika.VeterinaryApp.entity.Animal;
import dev.patika.VeterinaryApp.entity.Doctor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequest {
    private LocalDateTime appointmentDate;
    private Doctor doctor;
    private Animal animal;
}
