package com.backend.clinicaodontologica.test;

import com.backend.clinicaodontologica.dao.impl.OdontologoDaoH2;
import com.backend.clinicaodontologica.model.Odontologo;
import com.backend.clinicaodontologica.service.OdontologoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {

    private OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());

    @BeforeAll
    static void doBefore() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/bdcodontol;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Test
    void deberiaAgregarUnOdontologo(){
        Odontologo odontologo = new Odontologo(1234, "Marcelo", "Basile");

        Odontologo odontologoRegistrado = odontologoService.registrarOdontologo(odontologo);

        assertTrue(odontologoRegistrado.getId() != 0);
    }

}