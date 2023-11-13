package com.backend.clinicaodontologica.service;

import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.salida.Paciente.PacinteSalidaDto;
import com.backend.clinicaodontologica.model.Odontologo;
import com.backend.clinicaodontologica.model.Paciente;
import com.backend.clinicaodontologica.service.impl.PacienteService;

import java.util.List;

public interface IPacienteService {

    PacinteSalidaDto registrarPaciente(PacienteEntradaDto paciente);

    List<PacinteSalidaDto> listarPacientes();

    PacinteSalidaDto buscarPacientePorId(int id);

    //aqui cambiar luego por PacinteSalidaDto
    Paciente actualizarPaciente(Paciente paciente);

}
