package com.backend.clinicaodontologica.test;


import com.backend.clinicaodontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.salida.Paciente.PacinteSalidaDto;

import com.backend.clinicaodontologica.exception.ResourseNotFoundException;
import com.backend.clinicaodontologica.service.impl.PacienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class PacienteServiceTest {

    private PacienteService pacienteService;

    @Test
    void deberiaAgregarUnPaciente(){
        PacienteEntradaDto paciente = new PacienteEntradaDto("Marcelo", "Apellido", 123456, LocalDate.of(2023, 05, 02), new DomicilioEntradaDto("Calle", 13, "Localidad", "Provincia"));

        PacinteSalidaDto pacinteRegistrado = pacienteService.registrarPaciente(paciente);

        Assertions.assertTrue(pacinteRegistrado.getId() != 0);

    }

    @Test
    void deberiaRetornarUnaListaNoVacia(){
        assertFalse(pacienteService.listarPacientes().isEmpty());
    }

    @Test
    void deberiaeliminarUnPacientePorId() throws ResourseNotFoundException {
        PacienteEntradaDto paciente = new PacienteEntradaDto("Marcelo", "Apellido", 123456, LocalDate.of(2023, 05, 02), new DomicilioEntradaDto("Calle", 13, "Localidad", "Provincia"));

        PacinteSalidaDto pacinteRegistrado = pacienteService.registrarPaciente(paciente);
        Long ObtengoID = (long) pacinteRegistrado.getId();
        pacienteService.eliminarPaciente(ObtengoID);
        assertTrue(pacinteRegistrado == null);

    }

}