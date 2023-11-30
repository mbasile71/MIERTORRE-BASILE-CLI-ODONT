package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.salida.Paciente.PacinteSalidaDto;
import com.backend.clinicaodontologica.exception.ResourseNotFoundException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    void deberiaRegistrarUnPacienteDeNombreMarceloYRetornarSuId(){
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Marcelo", "Basile", 22355512, LocalDate.of(2023, 12, 15
        ), new DomicilioEntradaDto("R Pena", 1563, "Martinez", "Baires"));

        PacinteSalidaDto pacinteSalidaDto = pacienteService.registrarPaciente(pacienteEntradaDto);

        assertEquals("Marcelo", pacinteSalidaDto.getNombre());
        assertNotNull(pacinteSalidaDto.getId());
    }

    @Test
    @Order(2)
    void alIntentarEliminarUnPacienteConId1YaEliminado_deberiaLanzarUnaResourceNotFoundException(){
        try {
            pacienteService.eliminarPaciente(1L);
        }catch (Exception exception){
            exception.printStackTrace();
        }

        assertThrows(ResourseNotFoundException.class, () -> pacienteService.eliminarPaciente(1L));
    }

    @Test
    @Order(3)
    void deberiDevolverUnaListaDePacientesVacia(){
        List<PacinteSalidaDto> pacinteSalidaDto1 = pacienteService.listarPacientes();

        assertTrue(pacinteSalidaDto1.isEmpty());
    }

}