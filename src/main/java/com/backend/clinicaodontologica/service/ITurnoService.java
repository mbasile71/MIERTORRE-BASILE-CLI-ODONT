package com.backend.clinicaodontologica.service;

import com.backend.clinicaodontologica.dto.Modificacion.TurnoModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.Turno.TurnoSalidaDto;
import com.backend.clinicaodontologica.exception.BadRequestException;
import com.backend.clinicaodontologica.exception.ResourseNotFoundException;

import java.util.List;

public interface ITurnoService {

    TurnoSalidaDto registrarTurno(TurnoEntradaDto turno) throws BadRequestException;

    List<TurnoSalidaDto> listarTurnos();

    TurnoSalidaDto buscarTurnoPorId(Long id);

    TurnoSalidaDto ActualizarTurno(TurnoModificacionEntradaDto turno);

    void eliminarTurno(Long id) throws ResourseNotFoundException;

}
