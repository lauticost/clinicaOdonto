package com.dh.clinica_odontologica.service.impl;

import com.dh.clinica_odontologica.exception.BadRequestException;
import com.dh.clinica_odontologica.model.Paciente;
import com.dh.clinica_odontologica.repository.IPacienteRepository;
import com.dh.clinica_odontologica.service.IPacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {

    //Instancia de Interfaz pacienteRepository
    @Autowired
    IPacienteRepository pacienteRepository;

    //Instancia de Mapper
    @Autowired
    ObjectMapper mapper;

    //Instancia de logger
    private static final Logger logger = Logger.getLogger(PacienteService.class);

    //Metodos

    //Creación de método guardarPaciente para reutilizar código
    private void guardarPaciente(Paciente paciente)throws BadRequestException {
        //Excepción personalizada BadRequest en caso de crear id existente
        if(pacienteRepository.findById(paciente.getId()) != null){
            String mensajeError = "El id "+ paciente.getId() + " del paciente que se desea registrar ya existe";
            //Loggeo de errores
            logger.error(mensajeError);
            throw new BadRequestException(mensajeError);
        }
        pacienteRepository.save(paciente);
    }

    @Override
    public void registrarPaciente(Paciente paciente) throws BadRequestException {

        //Excepción personalizada BadRequest en caso de crear id existente
        if(pacienteRepository.findById(paciente.getId()).isPresent()){
            String mensajeError = "El id "+ paciente.getId() + " del paciente que se desea registrar ya existe";
            //Loggeo de errores
            logger.error(mensajeError);
            throw new BadRequestException(mensajeError);
        }
        logger.info("Se registró un nuevo Paciente: " + paciente);
        pacienteRepository.save(paciente);
    }

    @Override
    public List<Paciente> listarTodosPacientes() {
        List<Paciente> listaPacientes = pacienteRepository.findAll();
        return  listaPacientes;

    }

    @Override
    public Paciente buscarPaciente(Long id) throws Exception {
        Optional<Paciente> pacienteEncontrado = pacienteRepository.findById(id);
        //Consulta si pacienteEncontrado es distinto de null
        if(pacienteEncontrado.isPresent())
            return mapper.convertValue(pacienteEncontrado, Paciente.class);
        else
            throw new Exception("El ID " + id + " indicado no corresponde a un paciente registrado.");
    }

    @Override
    public void eliminarPaciente(Long id) {
        logger.info("Se eliminó el Paciente de ID: " + id);
        pacienteRepository.deleteById(id);
    }

    @Override
    public void modificarPaciente(Paciente paciente)  {
        logger.info("Se modificó el Paciente: " + paciente.toString());
        //El método save de repository sirve para para guardar registro nuevo y actualizar existente
        pacienteRepository.save(paciente);
    }
}
