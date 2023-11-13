package com.backend.clinicaodontologica.dto.entrada.odontologo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OdontologoEntradaDto {

    @NotNull(message = "El numero de matricula del odontologo no puede ser nulo")
    @Size(max = 20, message = "El nombre debe tener hasta 20 digitos")
    private int numeroMatricula;

    @NotNull(message = "El nombre del Odontologo no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre del Odontologo")
    @Size(max = 50, message = "El nombre del Odontologo debe tener hasta 50 caracteres")
    private String nombre;

    @Size(max = 50, message = "El apellido debe tener hasta 50 caracteres")
    @NotNull(message = "El apellido del Odontologo no puede ser nulo")
    @NotBlank(message = "Debe especificarse el apellido del Odontologo")
    private String apellido;

    public OdontologoEntradaDto() {
    }

    public OdontologoEntradaDto(int numeroMatricula, String nombre, String apellido) {
        this.numeroMatricula = numeroMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(int numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
