package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.Modificacion.PacienteModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.salida.Paciente.PacinteSalidaDto;
import com.backend.clinicaodontologica.entity.Paciente;
import com.backend.clinicaodontologica.repository.PacienteRepository;
import com.backend.clinicaodontologica.service.IPacienteService;
import com.backend.clinicaodontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {

    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);

    private PacienteRepository pacienteRepository;
    //Inyecto un ModelMapper
    private ModelMapper modelMapper;

    //Constructor
    @Autowired //No es necesario ponerlo porque ya gracias a Sping lo hace!!
    public PacienteService(PacienteRepository pacienteRepository, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
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
        Paciente pacienteAPersistir = pacienteRepository.save(pacienteEntidad);
        //transformamos el paciente persistido en pacienteSalidaDto para enviar a esa capa
        PacinteSalidaDto pacinteSalidaDto = modelMapper.map(pacienteAPersistir, PacinteSalidaDto.class);
        LOGGER.info("PacienteSalidaDto: " + JsonPrinter.toString(pacinteSalidaDto));
        return pacinteSalidaDto;
    }

    @Override
    public List<PacinteSalidaDto> listarPacientes() {
        List<PacinteSalidaDto> pacinteSalidaDto = pacienteRepository.findAll()
                .stream()
                //aca con el map iteramos la lista de pacientes y con el strim lo transformamos stream y
                //luego con el toList lo convertimos en una lista
                .map(paciente -> modelMapper.map(paciente, PacinteSalidaDto.class))
                .toList();

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
    public PacinteSalidaDto buscarPacientePorId(Long id) {
        Paciente pacienteBuscado = pacienteRepository.findById(id).orElse(null);
        PacinteSalidaDto pacienteEncontrado = null;

        if (pacienteBuscado != null) {
            pacienteEncontrado = modelMapper.map(pacienteBuscado, PacinteSalidaDto.class);
            LOGGER.info("Paciente encontrado: {}", JsonPrinter.toString(pacienteEncontrado));
        } else LOGGER.error("El id no se encuentra registrado en la base de datos");

        return pacienteEncontrado;
    }



    @Override
    public PacinteSalidaDto actualizarPaciente(PacienteModificacionEntradaDto paciente) {
        //debo pasar el paciente (paciente )que recibo a entidad Paciente.class para enviarlo a persistencia
        Paciente pacienteRecibido = modelMapper.map(paciente, Paciente.class);  //TIENE EL ID
        //Busco el paciente a actualizar por el ID
        Paciente pacienteAActualizar = pacienteRepository.findById(pacienteRecibido.getId()).orElse(null);
        //Debo devolver un paciente "PacienteSalidaDto" y ya lo dejo disponible aqui
        PacinteSalidaDto pacinteSalidaDto = null;
        if(pacienteAActualizar != null){
            pacienteAActualizar = pacienteRecibido;
            pacienteRepository.save(pacienteAActualizar);
            //paso la entidad pacienteAActualizar de entidad a PacinteSalidaDto
            pacinteSalidaDto = modelMapper.map(pacienteAActualizar, PacinteSalidaDto.class);
            LOGGER.warn("Paciente actualizado correctamente: {}" , JsonPrinter.toString(pacinteSalidaDto));
        }else{
            LOGGER.error("No se pudo actualizar el paciente por no encontrarse en nuestra base de datos");
            //lanar luego una excepcion
        }

        return pacinteSalidaDto;
    }

    @Override
    public PacinteSalidaDto buscarPacientePorDni(int dni) {
        return modelMapper.map(pacienteRepository.findByDni(dni), PacinteSalidaDto.class);
    }

    @Override
    public void eliminarPaciente(Long id) {
        if(pacienteRepository.findById(id).orElse(null) != null){
            pacienteRepository.deleteById(id);
            LOGGER.warn("Se ha elimiminado al paciente con id: {}" , id);
        }else {
            LOGGER.error("No se encuentra en la base de datos el paciente con ID {}", id);
            //lanzar excepcion aqui
        }
    }


    //Creo un metodo para decirle al mapper de que clase y a que clase los voy a configurar
    private void configureMapping(){
        modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
                .addMappings(modelMapper -> modelMapper.map(PacienteEntradaDto::getDomicilioEntradaDto,
                Paciente::setDomicilio));
        modelMapper.typeMap(Paciente.class, PacinteSalidaDto.class)
                .addMappings(modelMapper -> modelMapper.map(Paciente::getDomicilio, PacinteSalidaDto::setDomicilioSalidaDto));
        modelMapper.typeMap(PacienteModificacionEntradaDto.class, Paciente.class)
                .addMappings(mapper -> mapper.map(PacienteModificacionEntradaDto::getDomicilioEntradaDto, Paciente::setDomicilio));
    }

}
