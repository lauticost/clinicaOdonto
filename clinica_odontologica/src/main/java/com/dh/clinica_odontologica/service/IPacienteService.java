package com.dh.clinica_odontologica.service;


import com.dh.clinica_odontologica.exception.BadRequestException;
import com.dh.clinica_odontologica.model.Paciente;
import java.util.List;


public interface IPacienteService {
    public void registrarPaciente(Paciente paciente) throws BadRequestException;
    public List<Paciente> listarTodosPacientes();
    public Paciente buscarPaciente(Long id) throws Exception;
    public void eliminarPaciente(Long id);
    public void modificarPaciente(Paciente paciente);
}
