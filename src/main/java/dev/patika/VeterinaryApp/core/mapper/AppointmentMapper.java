package dev.patika.VeterinaryApp.core.mapper;

import dev.patika.VeterinaryApp.dto.request.AppointmentRequest;
import dev.patika.VeterinaryApp.dto.response.AnimalResponse;
import dev.patika.VeterinaryApp.dto.response.AppointmentResponse;
import dev.patika.VeterinaryApp.entity.Animal;
import dev.patika.VeterinaryApp.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface AppointmentMapper {
    Appointment asEntity(AppointmentRequest appointmentRequest);
    AppointmentResponse asOutput(Appointment appointment);
    List<AppointmentResponse> asOutput(List<Appointment> appointmentList);
    void update(@MappingTarget Appointment entity, AppointmentRequest appointmentRequest);


}
