package com.backend.clinicaodontologica.dto.Modificacion;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DomicilioModificacionEntradaDto {
    @NotNull(message = "Debe proveerse el id del Domicilio que se desea modificar")
    private Long id;

    @NotNull(message = "La calle del paciente no puede ser nulo")
    @NotBlank(message = "Debe especificarse la calle del paciente")
    @Size(max = 50, message = "El nombre de la calle debe tener hasta 50 caracteres")
    private String calle;

    @NotNull(message = "Debe proveerse el numero de la calle")
    private int numero;

    @NotNull(message = "La localidad del paciente no puede ser nulo")
    @NotBlank(message = "Debe especificarse la localidad del paciente")
    @Size(max = 50, message = "El nombre de la localidad debe tener hasta 50 caracteres")
    private String localidad;

    @NotNull(message = "La provincia del paciente no puede ser nulo")
    @NotBlank(message = "Debe especificarse la provincia del paciente")
    @Size(max = 50, message = "El nombre de la provincia debe tener hasta 50 caracteres")
    private String provincia;

    public DomicilioModificacionEntradaDto() {
    }

    public DomicilioModificacionEntradaDto(Long id, String calle, int numero, String localidad, String provincia) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
