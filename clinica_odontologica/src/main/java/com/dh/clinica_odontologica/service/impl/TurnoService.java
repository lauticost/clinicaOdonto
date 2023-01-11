package com.dh.clinica_odontologica.service.impl;

import com.dh.clinica_odontologica.model.Turno;
import com.dh.clinica_odontologica.repository.ITurnoRepository;
import com.dh.clinica_odontologica.service.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {


    //Instancia de Interfaz turnoRepository
    @Autowired
    ITurnoRepository turnoRepository;

    //Instancia de Mapper
    @Autowired
    ObjectMapper mapper;

    //Instancia de logger
    private static final Logger logger = Logger.getLogger(TurnoService.class);

    //Metodos

    @Override
    public void registrarTurno(Turno turno) {
        logger.info("Se registró un nuevo Turno: " + turno);
        turnoRepository.save(turno);
    }

    @Override
    public List<Turno> listarTodosTurnos() {
        List<Turno> listaTurnos = turnoRepository.findAll();
        return  listaTurnos;
    }

    @Override
    public void eliminarTurno(Long id) {
        logger.info("Se eliminó el Turno de ID: " + id);
        turnoRepository.deleteById(id);
    }

    @Override
    public Turno buscarTurno(Long id) throws Exception {
        Optional<Turno> turnoEncontrado = turnoRepository.findById(id);
        //Consulta si turnoEncontrado es distinto de null
        if(turnoEncontrado.isPresent())
            return mapper.convertValue(turnoEncontrado, Turno.class);
        else
            throw new Exception("El ID " + id + " indicado no corresponde a un turno registrado.");
    }

    @Override
    public void modificarTurno(Turno turno) {
        logger.info("Se modificó el Turno: " + turno.toString());
        //El método save de repository sirve para guardar y actualizar
        turnoRepository.save(turno);
    }
}
