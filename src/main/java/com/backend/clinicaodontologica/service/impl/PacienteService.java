package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dao.IDao;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.salida.Paciente.PacinteSalidaDto;
import com.backend.clinicaodontologica.model.Paciente;
import com.backend.clinicaodontologica.service.IPacienteService;
import com.backend.clinicaodontologica.utils.JsonPrinter;
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
    public PacinteSalidaDto registrarPaciente(PacienteEntradaDto paciente) {
        //convertimos mediante el mapper de dto a entidad
        LOGGER.info("PacienteEntadaDto: " + JsonPrinter.toString(paciente));
        Paciente pacienteEntidad = modelMapper.map(paciente, Paciente.class);
        //mandamos a persistir "llamamos a la capa de persistencia"
        Paciente pacienteAPersistir = pacienteIDao.registrar(pacienteEntidad);
        //transformamos el paciente persistido en pacienteSalidaDto para enviar a esa capa
        PacinteSalidaDto pacinteSalidaDto = modelMapper.map(pacienteAPersistir, PacinteSalidaDto.class);
        LOGGER.info("PacienteSalidaDto: " + JsonPrinter.toString(pacinteSalidaDto));
        return pacinteSalidaDto;
    }

    @Override
    public List<PacinteSalidaDto> listarPacientes() {
        List<PacinteSalidaDto> pacinteSalidaDto = pacienteIDao.listarTodos()
                .stream()
                //aca con el map iteramos la lista de pacientes y con el strim lo transformamos stream y
                //luego con el toList lo convertimos en una lista
                .map(paciente -> modelMapper.map(paciente, PacinteSalidaDto.class))
                .toList();

        //esto seria la forma tradicional con un forEach
        //List<Paciente> pacientes = pacienteIDao.listarTodos();
        //List<PacienteSalidaDto> pacienteSalidaDtos = new ArrayList<>();
        //for (Paciente paciente : pacientes){
        //    PacienteSalidaDto pacienteSalidaDto = modelMapper.map(paciente, PacienteSalidaDto.class);
        //    pacienteSalidaDtos.add(pacienteSalidaDto);
        //}

        LOGGER.info("Listado de pacientes: {}" , pacinteSalidaDto);
        return pacinteSalidaDto;
    }

    @Override
    public PacinteSalidaDto buscarPacientePorId(int id) {
        Paciente pacienteBuscado = pacienteIDao.buscarPorId(id);
        PacinteSalidaDto pacienteEncontrado = null;
        if(pacienteBuscado != null){
            pacienteEncontrado = modelMapper.map(pacienteBuscado, PacinteSalidaDto.class);
            LOGGER.info("Paciente encontrado: {} ", pacienteEncontrado);
        }else {
            LOGGER.error("No se encuentra el ID en la base de datos");
        }

        return pacienteEncontrado;
    }

    @Override
    //aqui cambiar luego por PacinteSalidaDto (pendiente de hacer nosotros)
    public Paciente actualizarPaciente(Paciente paciente) {
        return pacienteIDao.actualizar(paciente);
    }

    //Creo un metodo para decirle al mapper de que clase y a que clase los voy a configurar
    private void configureMapping(){
        modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
                .addMappings(modelMapper -> modelMapper.map(PacienteEntradaDto::getDomicilioEntradaDto,
                Paciente::setDomicilio));
        modelMapper.typeMap(Paciente.class, PacinteSalidaDto.class)
                .addMappings(modelMapper -> modelMapper.map(Paciente::getDomicilio, PacinteSalidaDto::setDomicilioSalidaDto));
    }

}
