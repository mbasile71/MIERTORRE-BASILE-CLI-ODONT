package com.backend.clinicaodontologica.controller;

import com.backend.clinicaodontologica.dto.Modificacion.OdontologoModificacionEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.Odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.exception.ResourseNotFoundException;
import com.backend.clinicaodontologica.service.IOdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private IOdontologoService odontologoService;

    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    //POS
    @PostMapping("/registrar")
    public ResponseEntity<OdontologoSalidaDto> registrarOdontologo (@RequestBody @Valid OdontologoEntradaDto odontologo){
        return new ResponseEntity<>(odontologoService.registrarOdontologo(odontologo), HttpStatus.CREATED);
    }

    //GET
    @GetMapping("/buscar/{id}")
    public ResponseEntity<OdontologoSalidaDto> buscarOdontologoPorId(@PathVariable Long id){
        return new ResponseEntity<>(odontologoService.buscarOdontologoPorId(id), HttpStatus.OK);
    }

    //GET
     @GetMapping("/listar")
    public ResponseEntity<List<OdontologoSalidaDto>> listarOdontologos(){
        return new ResponseEntity<>(odontologoService.listarOdontologos(), HttpStatus.OK);
     }

    //PUT
    @PostMapping("/actualizar")
    public OdontologoSalidaDto actualizarOdontologo(@RequestBody OdontologoModificacionEntradaDto odontologo){
        return odontologoService.actualizarOdontologo(odontologo);
    }

    //DELETE
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id) throws ResourseNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return new ResponseEntity<>("Odontologo eliminado correcatamente...", HttpStatus.OK);
    }

}
