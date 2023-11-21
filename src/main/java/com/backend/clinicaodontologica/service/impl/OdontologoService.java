package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.Modificacion.OdontologoModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.Odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.dto.salida.Paciente.PacinteSalidaDto;
import com.backend.clinicaodontologica.entity.Odontologo;
import com.backend.clinicaodontologica.repository.OdontologoRepository;
import com.backend.clinicaodontologica.service.IOdontologoService;
import com.backend.clinicaodontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private OdontologoRepository odontologoRepository;

    private ModelMapper modelMapper;

    //Constructor
    public OdontologoService(OdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologo) {
        LOGGER.info("OdontologoEntradaDto: " + JsonPrinter.toString(odontologo));
        //convertimos mediante el mapper de dtoEntrada a entidad
        Odontologo OdontologoEntidad = modelMapper.map(odontologo, Odontologo.class);

        //mandamos a persistir a la capa Repository y obtenemos una entidad
        Odontologo OdontologoAPersistir = odontologoRepository.save(OdontologoEntidad);

        //transformamos la entidad obtenida en salidaDto
        OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(OdontologoAPersistir, OdontologoSalidaDto.class);
        LOGGER.info("OdontologoSalidaDto: " + JsonPrinter.toString(odontologoSalidaDto));

        return odontologoSalidaDto;
    }

    @Override
    public List<OdontologoSalidaDto> listarOdontologos() {
        List<OdontologoSalidaDto> pacienteSalidaDto = odontologoRepository.findAll().stream()
                .map(odontologo -> modelMapper.map(odontologo, OdontologoSalidaDto.class)).toList();
        return pacienteSalidaDto;
    }

    @Override
    public OdontologoSalidaDto buscarOdontologoPorId(Long id) {
        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);
        OdontologoSalidaDto odontologoEncontrado = null;

        if(odontologoBuscado != null){
            odontologoEncontrado = modelMapper.map(odontologoBuscado, OdontologoSalidaDto.class);
            LOGGER.info("Odontologo encontrado: {}" , JsonPrinter.toString(odontologoEncontrado));
        }else
            LOGGER.error("El odontologo con ese ID no fue encontrado en la DB");

        return odontologoEncontrado;
    }

    @Override
    public OdontologoSalidaDto actualizarOdontologo(OdontologoModificacionEntradaDto odontologo) {
        //debo pasar el Odontologo (odontologo) que recibo a entidad Odontologo.class para enviarlo a persistencia
        Odontologo odontologoRecibido = modelMapper.map(odontologo, Odontologo.class);  //EL OBJETO TRAE EL ID PARA BUCARLO Y ACTUALIZARLO
        //Busco el odontologo a actualizar por el ID
        Odontologo odontologoAPersistir = odontologoRepository.findById(odontologoRecibido.getId()).orElse(null);  //ACA TENGO EL ID

        OdontologoSalidaDto odontologoSalidaDto = null;

        if(odontologoAPersistir != null){
            odontologoAPersistir = odontologoRecibido;
            odontologoRepository.save(odontologoAPersistir);

            //Debo devolver un Odontologo "OdontologoSalidaDto"
            odontologoSalidaDto = modelMapper.map(odontologoAPersistir, OdontologoSalidaDto.class);
            LOGGER.warn("Odontologo actualizado: {}", JsonPrinter.toString(odontologoSalidaDto));
        }else {
            LOGGER.error("No se encontro al odontologo en nuestra DB");
        }

        return odontologoSalidaDto;
    }

    @Override
    public void eliminarOdontologo(Long id) {
        Odontologo odontologoABorrar = odontologoRepository.findById(id).orElse(null);
        if(odontologoABorrar != null){
            odontologoRepository.delete(odontologoABorrar);
            LOGGER.warn("Se elimino al odontologo con ID: {}", id);
        }else {
            LOGGER.error("No se encontro al odontologo con ID: {}", id);
        }
    }

    private void configureMapping(){
        modelMapper.typeMap(OdontologoEntradaDto.class, Odontologo.class);
        modelMapper.typeMap(Odontologo.class, OdontologoSalidaDto.class);
    }
}
