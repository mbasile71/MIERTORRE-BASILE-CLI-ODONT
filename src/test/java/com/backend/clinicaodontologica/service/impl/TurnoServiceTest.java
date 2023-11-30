package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.Odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.dto.salida.Turno.TurnoSalidaDto;
import com.backend.clinicaodontologica.exception.BadRequestException;
import com.backend.clinicaodontologica.exception.ResourseNotFoundException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;

    @Test
    @Order(1)
    void deberiaRegistrarUnTurnoYDevolverUnId() throws BadRequestException{
        try {
            TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto(LocalDateTime.of(2013,12,20 , 20,30), 1L, 1L);
            TurnoSalidaDto turnoSalidaDto = turnoService.registrarTurno(turnoEntradaDto);
            assertNotNull(turnoSalidaDto.getId());
        }catch (Exception exception){
            exception.printStackTrace();
        }


    }

    @Test
    @Order(2)
    void alIntentarEliminarUnTurnoConId1Inexistente_deberiaLanzarUnaResourceNotFoundException() throws ResourseNotFoundException{
        try {
            turnoService.eliminarTurno(100L);
        }catch (Exception exception){
            exception.printStackTrace();
        }

        assertThrows(ResourseNotFoundException.class, () -> turnoService.eliminarTurno(100L));
    }

}