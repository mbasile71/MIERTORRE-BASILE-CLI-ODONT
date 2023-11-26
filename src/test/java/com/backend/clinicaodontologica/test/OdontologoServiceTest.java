package com.backend.clinicaodontologica.test;


import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.Odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.service.impl.OdontologoService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class OdontologoServiceTest {

    private OdontologoService odontologoService;

    @Test
    void deberiaAgregarUnOdontologo(){
    OdontologoEntradaDto odontologo = new OdontologoEntradaDto("1234", "Marcelo", "Basile");

    OdontologoSalidaDto odontologoRegistrado = odontologoService.registrarOdontologo(odontologo);

    Assert.assertTrue(odontologoRegistrado.getNombre() != null);
    }

}