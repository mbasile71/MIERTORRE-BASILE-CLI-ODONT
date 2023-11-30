package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.Odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.exception.ResourseNotFoundException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void debariaRegistrarUnOdontologoConNumeroDeMatricula12345YRetormnarElId(){

        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("12345", "Agustin", "Mier Torre" );

        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);

        assertEquals("12345", odontologoSalidaDto.getNumeroMatricula());
        assertNotNull(odontologoSalidaDto.getId());
    }

    @Test
    @Order(2)
    void deberiaAgregarUnOdontologoYRetornarUnaListaConUnObjetos(){
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("12874", "Marcelo", "Basile" );
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);

        List<OdontologoSalidaDto> odontologoSalidaDtos = odontologoService.listarOdontologos();

        assertFalse(odontologoSalidaDtos.isEmpty());
    }

    @Test
    @Order(3)
    void alIntentarEliminarUnOdontologoConId1YaEliminado_deberiaLanzarUnaResourceNotFoundException(){
        try {
            odontologoService.eliminarOdontologo(1L);
        }catch (Exception exception){
            exception.printStackTrace();
        }

        assertThrows(ResourseNotFoundException.class, () -> odontologoService.eliminarOdontologo(1L));
    }

}