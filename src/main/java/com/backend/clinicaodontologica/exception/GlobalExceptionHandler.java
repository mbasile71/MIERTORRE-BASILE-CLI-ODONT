package com.backend.clinicaodontologica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ResourseNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> manejoResourseNotFound(ResourseNotFoundException exception) {
        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("mensaje", "Recurso no encontrado: " + exception.getMessage());
        return mensaje;
    }

    //Este metodo va a validad los @Valid de los Dto´s de entrada
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> procesarValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> exceptionMessage = new HashMap<>();

        //Aqui va a recorrer todos los FielName (Ej: nombre, apellido, etc...) y va a poner el FildName con el error correspondiente
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            exceptionMessage.put(fieldName, errorMessage);
        });
        return exceptionMessage;
    }

    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> procesarBadRequestException(BadRequestException exception){
        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("mensaje", "Recurso no encontrado: " + exception.getMessage());
        return mensaje;
    }
}
