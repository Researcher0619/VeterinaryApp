package dev.patika.VeterinaryApp.core.exception;


import dev.patika.VeterinaryApp.core.result.Result;
import dev.patika.VeterinaryApp.core.result.ResultHelper;
import dev.patika.VeterinaryApp.core.result.ResultData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DoctorAppointmentException.class)
    public ResponseEntity<Result> doctorAppointmentException(DoctorAppointmentException e) {
        return new ResponseEntity<>(ResultHelper.doctorAppointmentError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HourTypeException.class)
    public ResponseEntity<Result> hourTypeException(HourTypeException e) {
        return new ResponseEntity<>(ResultHelper.hourTypeError(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VaccinationException.class)
    public ResponseEntity<Result> vaccinationException(VaccinationException e) {
        return new ResponseEntity<>(ResultHelper.vaccinationError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DuplicationException.class)
    public ResponseEntity<Result> duplicationException(DuplicationException e) {
        return new ResponseEntity<>(ResultHelper.duplicationError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Result> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<>(ResultHelper.notFoundError(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultData<List<String>>> handleValidationErrors(MethodArgumentNotValidException e) {
        List<String> validationErrorList = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(ResultHelper.validateError(validationErrorList), HttpStatus.BAD_REQUEST);
    }


}
