package com.backend.clinicaodontologica.service;

import com.backend.clinicaodontologica.dto.Modificacion.PacienteModificacionentradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.salida.Paciente.PacinteSalidaDto;
import com.backend.clinicaodontologica.entity.Paciente;

import java.util.List;

public interface IPacienteService {

    PacinteSalidaDto registrarPaciente(PacienteEntradaDto paciente);

    List<PacinteSalidaDto> listarPacientes();

    PacinteSalidaDto buscarPacientePorId(Long id);

    //aqui cambiar luego por PacinteSalidaDto
    PacinteSalidaDto actualizarPaciente(PacienteModificacionentradaDto paciente);

    PacinteSalidaDto buscarPacientePorDni(int dni);

    void eliminarPaciente(Long id);

}
