package com.backend.clinicaodontologica.controller;

import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.model.Paciente;
import com.backend.clinicaodontologica.service.IPacienteService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private IPacienteService pacienteService;

    //Constructor
    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    //Post
    @PostMapping("/registrar")
    public Paciente registrarPaciente(@RequestBody @Valid PacienteEntradaDto paciente){
        return pacienteService.registrarPaciente(paciente);
    }

    //Put
    @PutMapping("/actualizar")
    public Paciente actualizarPaciente(@RequestBody Paciente paciente){
        return pacienteService.actualizarPaciente(paciente);
    }
}
