package com.dh.clinica_odontologica.service.impl;

import com.dh.clinica_odontologica.exception.BadRequestException;
import com.dh.clinica_odontologica.model.Odontologo;
import com.dh.clinica_odontologica.repository.IOdontologoRepository;
import com.dh.clinica_odontologica.service.IOdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {

    //Instancia de Interfaz Repository
    @Autowired
    IOdontologoRepository odontologoRepository;

    //Instancia de Mapper
    @Autowired
    ObjectMapper mapper;

    //Instancia de logger
    private static final Logger logger = Logger.getLogger(OdontologoService.class);

    //Metodos

    @Override
    public void registrarOdontologo(Odontologo odontologo) throws BadRequestException {
        //Excepción personalizada BadRequest en caso de crear id existente
        if(odontologoRepository.findById(odontologo.getId()).isPresent()){
            String mensajeError = "El id "+ odontologo.getId() + " del odontólogo que se desea registrar ya existe";
            //Loggeo de errores
            logger.error(mensajeError);
            throw new BadRequestException(mensajeError);
        }
        logger.info("Se registró un nuevo Odontólogo: " + odontologo.toString());
        odontologoRepository.save(odontologo);
    }

    @Override
    public List<Odontologo> listarTodosOdontologos() {
        List<Odontologo> listaOdontologos = odontologoRepository.findAll();
        return listaOdontologos;
    }

    @Override
    public Odontologo buscarOdontologo(Long id) throws Exception {
        Optional<Odontologo> odontologoEncontrado = odontologoRepository.findById(id);
        //Consulta si odontologoEncontrado es distinto de null
        if(odontologoEncontrado.isPresent())
            return mapper.convertValue(odontologoEncontrado, Odontologo.class);
        else
            throw new Exception("El ID " + id + " indicado no corresponde a un odontólogo registrado.");
    }

    @Override
    public void eliminarOdontologo(Long id) {
        logger.info("Se eliminó el Odontólogo de ID: " + id);
        odontologoRepository.deleteById(id);
    }

    @Override
    public void modificarOdontologo(Odontologo odontologo) {
        logger.info("Se modificó el Odontólogo: " + odontologo.toString());
        //El método save de repository sirve para guardar registro nuevo y actualizar existente
        odontologoRepository.save(odontologo);
    }

}
