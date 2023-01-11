package com.dh.clinica_odontologica.service;

import com.dh.clinica_odontologica.exception.BadRequestException;
import com.dh.clinica_odontologica.model.Domicilio;
import java.util.List;


public interface IDomicilioService {
    public void registrarDomicilio(Domicilio domicilio) throws BadRequestException;
    public List<Domicilio> listarTodosDomicilios();
    public Domicilio buscarDomicilio(Long id) throws Exception;
    public void eliminarDomicilio(Long id);
    public void modificarDomicilio(Domicilio domicilio);
}
