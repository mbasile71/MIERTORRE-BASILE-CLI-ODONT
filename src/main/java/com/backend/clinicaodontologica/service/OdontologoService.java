package com.backend.clinicaodontologica.service;

import com.backend.clinicaodontologica.dao.IDao;
import com.backend.clinicaodontologica.model.Odontologo;
import com.backend.clinicaodontologica.model.Paciente;

import java.util.List;

public class OdontologoService {
    private IDao<Odontologo> odontologoIDao;

    //Constructor
    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo registrarOdontologo(Odontologo odontologo){
        return odontologoIDao.registrar(odontologo);
    }

    public List<Odontologo> listarOdontologos(){
        return odontologoIDao.listarTodos();
    }
}
