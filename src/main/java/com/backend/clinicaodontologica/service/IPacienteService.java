package com.backend.clinicaodontologica.service;

import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.model.Paciente;
import com.backend.clinicaodontologica.service.impl.PacienteService;

import java.util.List;

public interface IPacienteService {

    Paciente registrarPaciente(PacienteEntradaDto paciente);

    List<Paciente> listarPacientes();

    Paciente buscarPaciente(int id);

    Paciente actualizarPaciente(Paciente paciente);

}
