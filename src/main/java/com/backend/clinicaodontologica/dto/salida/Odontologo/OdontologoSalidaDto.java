package com.backend.clinicaodontologica.dto.salida.Odontologo;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public class OdontologoSalidaDto {

    private Long id;
    private String numeroMatricula;
    private String nombre;
    private String apellido;

    public OdontologoSalidaDto() {
    }

    public OdontologoSalidaDto(Long id, String numeroMatricula, String nombre, String apellido) {
        this.id = id;
        this.numeroMatricula = numeroMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
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
