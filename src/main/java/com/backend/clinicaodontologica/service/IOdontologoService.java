package com.backend.clinicaodontologica.service;

import com.backend.clinicaodontologica.dto.Modificacion.OdontologoModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.Odontologo.OdontologoSalidaDto;

import java.util.List;

public interface IOdontologoService {

    OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologo);

    List<OdontologoSalidaDto> listarOdontologos();

    OdontologoSalidaDto buscarOdontologoPorId(Long id);

    OdontologoSalidaDto actualizarOdontologo(OdontologoModificacionEntradaDto odontologo);

    void eliminarOdontologo(Long id);

}