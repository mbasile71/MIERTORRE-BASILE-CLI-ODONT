package com.backend.clinicaodontologica.dto.salida.Turno;

import com.backend.clinicaodontologica.entity.Odontologo;
import com.backend.clinicaodontologica.entity.Paciente;

import java.time.LocalDateTime;

public class TurnoSalidaDto {
    private Long id;
    private int paciente_id;
    private int odontologo_id;
    private LocalDateTime fechaYHora;

    public TurnoSalidaDto() {
    }

    public TurnoSalidaDto(Long id, int paciente_id, int odontologo_id, LocalDateTime fechaYHora) {
        this.id = id;
        this.paciente_id = paciente_id;
        this.odontologo_id = odontologo_id;
        this.fechaYHora = fechaYHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
