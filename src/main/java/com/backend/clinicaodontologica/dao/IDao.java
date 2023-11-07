package com.backend.clinicaodontologica.dao;

import org.springframework.stereotype.Service;

import java.util.List;

public interface IDao<T> {
    T registrar(T t);
    List<T> listarTodos();
    T buscarPorId(int id);
}
