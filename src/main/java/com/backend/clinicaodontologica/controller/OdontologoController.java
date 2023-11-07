package com.backend.clinicaodontologica.controller;

import com.backend.clinicaodontologica.dao.IDao;
import com.backend.clinicaodontologica.model.Odontologo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/odontologo")

public class OdontologoController {

    private IDao<Odontologo> odontologoIDaoService;

    //Constructor
    public OdontologoController(IDao<Odontologo> odontologoIDaoService) {
        this.odontologoIDaoService = odontologoIDaoService;
    }

    @GetMapping("/listar")
//    public List<Odontologo> listarOdontologos(){
//        return odontologoIDaoService.listarTodos();
//    }
    public String hola(){
        return "Hello";
    }
}
