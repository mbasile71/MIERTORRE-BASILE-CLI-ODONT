package com.backend.clinicaodontologica.controller;

import com.backend.clinicaodontologica.dto.Modificacion.PacienteModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.salida.Paciente.PacinteSalidaDto;
import com.backend.clinicaodontologica.exception.ResourseNotFoundException;
import com.backend.clinicaodontologica.service.IPacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private IPacienteService pacienteService;

    //Constructor
    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    //POST
    @PostMapping("/registrar")
    public ResponseEntity<PacinteSalidaDto> registrarPaciente(@RequestBody @Valid PacienteEntradaDto paciente){
        return new ResponseEntity<>(pacienteService.registrarPaciente(paciente), HttpStatus.CREATED);
    }

    //GET
    @RequestMapping("{id}")
    public ResponseEntity<PacinteSalidaDto> obtenerPacientePorId(@PathVariable Long id){
        return new ResponseEntity<>(pacienteService.buscarPacientePorId(id), HttpStatus.OK);
    }

    //GET
    @RequestMapping("/listar")
    public ResponseEntity<List<PacinteSalidaDto>> listarPacientes(){
        return new ResponseEntity<>(pacienteService.listarPacientes(), HttpStatus.OK);
    }

    //PUT
    @PutMapping("/actualizar")
    public PacinteSalidaDto actualizarPaciente(@RequestBody PacienteModificacionEntradaDto paciente){
        return pacienteService.actualizarPaciente(paciente);
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id) throws ResourseNotFoundException {
        pacienteService.eliminarPaciente(id);
        return new ResponseEntity<>("paciente eliminado correctamente", HttpStatus.OK);
    }

}
