package com.dh.clinica_odontologica.controller;

import com.dh.clinica_odontologica.dto.DomicilioDTO;
import com.dh.clinica_odontologica.exception.BadRequestException;
import com.dh.clinica_odontologica.model.Domicilio;
import com.dh.clinica_odontologica.service.IDomicilioService;
import com.dh.clinica_odontologica.service.impl.DomicilioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/domicilios")
public class DomicilioController {
    @Autowired
    IDomicilioService domicilioService;

    //Instancia de Mapper
    @Autowired
    ObjectMapper mapper;

    //Instancia de logger
    private static final Logger logger = Logger.getLogger(DomicilioService.class);

    //Método POST para registrar un nuevo domicilio (registrarDomicilio)
    @PostMapping()
    public ResponseEntity<?> agregarDomicilio(@RequestBody Domicilio domicilio) throws BadRequestException {
        ResponseEntity<String> response = null;
        domicilioService.registrarDomicilio(domicilio);
        //Código de estado de respuesta y mensaje
        response = ResponseEntity.status(HttpStatus.OK).body("Domicilio de id(" + domicilio.getId() + ") registrado con éxito.");
        return response;
    }


    //Método GET para listar todos los domicilios (listarTodosDomicilios)
    @GetMapping("/lista")
    public Set<DomicilioDTO> listarDomicilios() {
        List<Domicilio> listaDomicilios = domicilioService.listarTodosDomicilios();
        Set<DomicilioDTO> listaDomiciliosDTO = new HashSet<>();
        //Conversión Domicilio a DomicilioDTO de cada elemento de la lista
        for(Domicilio domicilio: listaDomicilios)
            listaDomiciliosDTO.add(mapper.convertValue(domicilio,DomicilioDTO.class));

        return listaDomiciliosDTO;
    }


    //Método GET para buscar un Domicilio por id (buscarDomicilio)
    @GetMapping("/{id}")
    public DomicilioDTO obtenerDomicilio(@PathVariable Long id) throws Exception {
        //Conversión Domicilio a DomicilioDTO
        DomicilioDTO domicilioDTOEncontrado =mapper.convertValue(domicilioService.buscarDomicilio(id), DomicilioDTO.class);
        return domicilioDTOEncontrado;
    }


    //Método DELETE para eliminar un domicilio por id (eliminarDomicilio)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDomicilio(@PathVariable Long id) {

        ResponseEntity<String> response = null;
        domicilioService.eliminarDomicilio(id);
        //Código de estado de respuesta
        response = ResponseEntity.status(HttpStatus.OK).body("Domicilio de id(" + id + ") eliminado con éxito.");
        return response;
    }


    //Método PUT para actualizar datos de un domicilio (modificarDomicilio)
    @PutMapping()
    public ResponseEntity<?> actualizarDomicilio(@RequestBody Domicilio domicilio)  {
        ResponseEntity<String> response = null;
        domicilioService.modificarDomicilio(domicilio);
        //Código de estado de respuesta y mensaje
        response = ResponseEntity.status(HttpStatus.OK).body("Domicilio de id(" + domicilio.getId() + ") actualizado con éxito.");
        return response;
    }

    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> procesarErrorBadRequest(BadRequestException exception) {
        //Loggeo de errores
        logger.error(exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());

    }
}
