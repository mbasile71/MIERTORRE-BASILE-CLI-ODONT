package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.Modificacion.TurnoModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.Odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.dto.salida.Paciente.PacinteSalidaDto;
import com.backend.clinicaodontologica.dto.salida.Turno.TurnoSalidaDto;
import com.backend.clinicaodontologica.entity.Turno;
import com.backend.clinicaodontologica.exception.BadRequestException;
import com.backend.clinicaodontologica.exception.ResourseNotFoundException;
import com.backend.clinicaodontologica.repository.TurnoRepository;
import com.backend.clinicaodontologica.service.ITurnoService;
import com.backend.clinicaodontologica.utils.JsonPrinter;
import net.bytebuddy.asm.Advice;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {

    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private final TurnoRepository turnoRepository;
    private final ModelMapper modelMapper;

    //Aqui para verificar que existan un odontologo y un paciente debo inyectar los servicios de los mismos
    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;

    public TurnoService(TurnoRepository turnoRepository, ModelMapper modelMapper, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @Override
    public TurnoSalidaDto registrarTurno(TurnoEntradaDto turno) throws BadRequestException {

        OdontologoSalidaDto odontologo = odontologoService.buscarOdontologoPorId(turno.getOdontologoId());
        PacinteSalidaDto paciente = pacienteService.buscarPacientePorId(turno.getPacienteId());

        if (odontologo == null && paciente == null) {
            throw new BadRequestException("No se encuentra ni un odontólogo ni un paciente con los ID proporcionados.");
        } else if (odontologo == null) {
            throw new BadRequestException("No se encuentra un odontólogo con el ID proporcionado.");
        } else if (paciente == null) {
            throw new BadRequestException("No se encuentra un paciente con el ID proporcionado.");
        }

        LOGGER.info("TurnoEntradaDto: {}", JsonPrinter.toString(turno));
        Turno turnoEntidad = modelMapper.map(turno, Turno.class);
        Turno turnoAPersistir = turnoRepository.save(turnoEntidad);
        TurnoSalidaDto turnoSalidaDto = modelMapper.map(turnoAPersistir, TurnoSalidaDto.class);
        LOGGER.info("turnoSalidaDto: {}", JsonPrinter.toString(turnoSalidaDto));

        return turnoSalidaDto;

        }

    @Override
    public List<TurnoSalidaDto> listarTurnos() {
        List<TurnoSalidaDto> turnoSalidaDto = turnoRepository.findAll()
                .stream()
                .map(turno -> modelMapper.map(turno, TurnoSalidaDto.class))
                .toList();
        LOGGER.info("Listado de todos los turnos: {}", JsonPrinter.toString(turnoSalidaDto) );
        return turnoSalidaDto;
    }

    @Override
    public TurnoSalidaDto buscarTurnoPorId(Long id) {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDto turnoEncontrado = null;

        if(turnoBuscado != null){
            turnoEncontrado = modelMapper.map(turnoBuscado, TurnoSalidaDto.class);
            LOGGER.info("Turno encontrado: {}", JsonPrinter.toString(turnoEncontrado));
        }else {
            LOGGER.info("No pudo encontrase en la DB el turno con ID: {}", id);
        }

        return turnoEncontrado;
    }

    @Override
    public TurnoSalidaDto ActualizarTurno(TurnoModificacionEntradaDto turno) {
        //debo pasar el Turno (turno) que recibo a entidad turno.class para enviarlo a persistencia
        Turno turnoRecibido = modelMapper.map(turno, Turno.class);
        //Busco el turno a actualizar por el ID
        Turno turnoAActualizar = turnoRepository.findById(turnoRecibido.getId()).orElse(null);

        TurnoSalidaDto turnoSalidaDto = null;

        if(turnoAActualizar != null){
            turnoAActualizar = turnoRecibido;
            turnoRepository.save(turnoAActualizar);
            turnoSalidaDto = modelMapper.map(turnoAActualizar, TurnoSalidaDto.class);
            LOGGER.warn("Turno actualizado: {},", JsonPrinter.toString(turnoSalidaDto));
        }else {
            LOGGER.error("El turno no fue posible actuializarlo por no encontrarse en la DB");
            //lanzar excepcion aqui
        }

        return turnoSalidaDto;
    }

    @Override
    public void eliminarTurno(Long id) throws ResourseNotFoundException {
        if(turnoRepository.findById(id).orElse(null) != null){
            turnoRepository.deleteById(id);
            LOGGER.warn("Se elimino el turno con ID: {}", id);
        }else {
            LOGGER.error("No se encontro el turno en la DB con ID: {}", id);
            throw new ResourseNotFoundException("No se ha podido encontrar el Turno con ID: " + id);
        }
    }


    private void configureMapping(){
            modelMapper.typeMap(TurnoEntradaDto.class, Turno.class)
                    .addMappings(modelMapper -> modelMapper.map(TurnoEntradaDto::getOdontologoId, Turno::setOdontologo))
                    .addMappings(modelMapper -> modelMapper.map(TurnoEntradaDto::getPacienteId, Turno::setPaciente));
            modelMapper.typeMap(Turno.class, TurnoSalidaDto.class)
                    .addMappings(modelMapper -> modelMapper.map(Turno::getOdontologo, TurnoSalidaDto::setOdontologoId))
                    .addMappings(modelMapper -> modelMapper.map(Turno::getPaciente, TurnoSalidaDto::setPacienteId));

            modelMapper.typeMap(TurnoModificacionEntradaDto.class, Turno.class)
                    .addMappings(modelMapper -> modelMapper.map(TurnoModificacionEntradaDto::getOdontologoEntradaDto, Turno::setOdontologo))
                    .addMappings(modelMapper -> modelMapper.map(TurnoModificacionEntradaDto::getPacienteEntradaDto, Turno::setPaciente));

        }
}