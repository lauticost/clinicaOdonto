package com.dh.clinica_odontologica.service.impl;

import com.dh.clinica_odontologica.exception.BadRequestException;
import com.dh.clinica_odontologica.model.Domicilio;
import com.dh.clinica_odontologica.repository.IDomicilioRepository;
import com.dh.clinica_odontologica.service.IDomicilioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DomicilioService implements IDomicilioService {

    //Instancia de Interfaz Repository
    @Autowired
    IDomicilioRepository domicilioRepository;

    //Instancia de Mapper
    @Autowired
    ObjectMapper mapper;

    //Instancia de logger
    private static final Logger logger = Logger.getLogger(DomicilioService.class);

    //Metodos
    @Override
    public void registrarDomicilio(Domicilio domicilio) throws BadRequestException {
        //Excepción personalizada BadRequest en caso de crear id existente
        if(domicilioRepository.findById(domicilio.getId()).isPresent()){
            String mensajeError = "El id "+ domicilio.getId() + " del domicilio que se desea registrar ya existe";
            //Loggeo de errores
            logger.error(mensajeError);
            throw new BadRequestException(mensajeError);
        }
        logger.info("Se registró un nuevo Domicilio: " + domicilio.toString());
        domicilioRepository.save(domicilio);
    }

    @Override
    public List<Domicilio> listarTodosDomicilios() {
        List<Domicilio> listaDomicilios = domicilioRepository.findAll();
        return listaDomicilios;
    }

    @Override
    public Domicilio buscarDomicilio(Long id) throws Exception {
        Optional<Domicilio> domicilioEncontrado = domicilioRepository.findById(id);
        //Consulta si domicilioEncontrado es distinto de null
        if(domicilioEncontrado.isPresent())
            return mapper.convertValue(domicilioEncontrado, Domicilio.class);
        else
            throw new Exception("El ID " + id + " indicado no corresponde a un domicilio registrado.");
    }

    @Override
    public void eliminarDomicilio(Long id) {
        logger.info("Se eliminó el Domicilio de ID: " + id);
        domicilioRepository.deleteById(id);
    }

    @Override
    public void modificarDomicilio(Domicilio domicilio) {
        logger.info("Se modificó el Domicilio: " + domicilio.toString());
        //El método save de repository sirve para guardar registro nuevo y actualizar existente
        domicilioRepository.save(domicilio);
    }
}
