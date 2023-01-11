package com.dh.clinica_odontologica.service;

import com.dh.clinica_odontologica.model.Turno;
import java.util.List;


public interface ITurnoService {

    public void registrarTurno(Turno turno);
    public List<Turno> listarTodosTurnos();

    public Turno buscarTurno(Long id) throws Exception;
    public void eliminarTurno(Long id);
    public void modificarTurno(Turno turno);
}
