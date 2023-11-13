package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dao.IDao;
import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.Odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.model.Odontologo;
import com.backend.clinicaodontologica.service.IOdontologoService;
import com.backend.clinicaodontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OdontologoService implements IOdontologoService {

    private IDao<Odontologo> odontologoIDao;
    private ModelMapper modelMapper;

    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);

    public OdontologoService(IDao<Odontologo> odontologoIDao, ModelMapper modelMapper) {
        this.odontologoIDao = odontologoIDao;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologo) {
        LOGGER.info("Odontologo para registrar en formato Dto: " + JsonPrinter.toString(odontologo));
        //convertimos mediante el mapper de dtoEntrada a entidad
        Odontologo odontologoEntidad = modelMapper.map(odontologo, Odontologo.class);

        //mandamos a persistir a la capa dao y obtenemos una entidad
        Odontologo odontologoAPersistir = odontologoIDao.registrar(odontologoEntidad);

        //convertimos la entidad devuelta en salidaDto
        OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(odontologoAPersistir, OdontologoSalidaDto.class);
        LOGGER.info("Odontologo devuelto en Dto: " + JsonPrinter.toString(odontologoSalidaDto));

        return odontologoSalidaDto;
    }

    @Override
    public List<OdontologoSalidaDto> listarOdontologos() {
        List<OdontologoSalidaDto> odontologoSalidaDtoList = odontologoIDao.listarTodos()
                .stream()
                .map(o -> modelMapper.map(o, OdontologoSalidaDto.class))
                .toList();
        LOGGER.info("Listado de odontologos: " + JsonPrinter.toString(odontologoSalidaDtoList));
        return odontologoSalidaDtoList;
    }

    private void configureMapping(){
        modelMapper.typeMap(OdontologoEntradaDto.class, Odontologo.class);
        modelMapper.typeMap(Odontologo.class, OdontologoSalidaDto.class);
    }

}
