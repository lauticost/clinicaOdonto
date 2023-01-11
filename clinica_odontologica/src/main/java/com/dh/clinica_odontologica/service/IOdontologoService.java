package com.dh.clinica_odontologica.service;

import com.dh.clinica_odontologica.exception.BadRequestException;
import com.dh.clinica_odontologica.model.Odontologo;
import java.util.List;

public interface IOdontologoService {
    public void registrarOdontologo(Odontologo odontologo) throws BadRequestException;
    public List<Odontologo> listarTodosOdontologos();
    public Odontologo buscarOdontologo(Long id) throws Exception;
    public void eliminarOdontologo(Long id);
    public void modificarOdontologo(Odontologo odontologo);

}
