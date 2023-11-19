package com.backend.clinicaodontologica.repository;

import com.backend.clinicaodontologica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //no es necesario ponerlo ya que Jpa sabe que es un respository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Paciente findByDni(int dni);
    //seria lo mismo que hacer una query asi:
    //@Query(value = "SELECT * FROM PACIENTES WHERE DNI = ?1", nativeQuery = true)

    //Aca se harian tambien consultas mas complejas: Querys, HQL o Store Procedure!!!
}
