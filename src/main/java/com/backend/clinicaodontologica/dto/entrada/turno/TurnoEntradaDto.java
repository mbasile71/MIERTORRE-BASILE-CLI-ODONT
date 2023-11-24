package com.backend.clinicaodontologica.dto.entrada.turno;

import com.backend.clinicaodontologica.entity.Odontologo;
import com.backend.clinicaodontologica.entity.Paciente;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TurnoEntradaDto {

    @NotNull(message = "El campo paciente no puede ser nulo")
    //@NotBlank(message = "Debe especificarse el nombre o id del paciente")
    private int paciente_id;
    @NotNull(message = "El campo odontologo no puede ser nulo")
    //@NotBlank(message = "Debe especificarse el nombre o id del odontologo")
    private int odontologo_id;
    @NotNull(message = "El campo fecha y hora no puede ser nulo")
    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    private LocalDateTime fechaYHora;

    public TurnoEntradaDto() {
    }

    public TurnoEntradaDto(int paciente_id, int odontologo_id, LocalDateTime fechaYHora) {
        this.paciente_id = paciente_id;
        this.odontologo_id = odontologo_id;
        this.fechaYHora = fechaYHora;
    }

    public int getPaciente_id() {
        return paciente_id;
    }

    public void setPaciente_id(int paciente_id) {
        this.paciente_id = paciente_id;
    }

    public int getOdontologo_id() {
        return odontologo_id;
    }

    public void setOdontologo_id(int odontologo_id) {
        this.odontologo_id = odontologo_id;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }
}
