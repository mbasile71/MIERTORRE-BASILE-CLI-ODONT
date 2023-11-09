package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dao.IDao;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.model.Paciente;
import com.backend.clinicaodontologica.service.IPacienteService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {

    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);

    private IDao<Paciente> pacienteIDao;
    //Inyecto un ModelMapper
    private ModelMapper modelMapper;

    //Constructor
    public PacienteService(IDao<Paciente> pacienteIDao, ModelMapper modelMapper) {
        this.pacienteIDao = pacienteIDao;
        this.modelMapper = modelMapper;
        //Llamo aca al metodo creado abajo a lo ultimo "configureMapping"
        configureMapping();
    }

    @Override
    public Paciente registrarPaciente(PacienteEntradaDto paciente) {
        //convertimos mediante el mapper de dto a entidad
        Paciente pacienteEntidad = modelMapper.map(paciente, Paciente.class);
        //llamamos a la capa de persistencia
        return pacienteIDao.registrar(pacienteEntidad);
    }

    @Override
    public List<Paciente> listarPacientes() {
        return pacienteIDao.listarTodos();
    }

    @Override
    public Paciente buscarPaciente(int id) {
        return pacienteIDao.buscarPorId(id);
    }

    @Override
    public Paciente actualizarPaciente(Paciente paciente) {
        return pacienteIDao.actualizar(paciente);
    }

    //Creo un metodo para decirle al mapper de que clase y a que clase los voy a configurar
    private void configureMapping(){
        modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
                .addMappings(modelMapper -> modelMapper.map(PacienteEntradaDto::getDomicilioEntradaDto, Paciente::setDomicilio));
    }

}
