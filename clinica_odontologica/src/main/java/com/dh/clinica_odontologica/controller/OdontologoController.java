package com.dh.clinica_odontologica.controller;

import com.dh.clinica_odontologica.dto.OdontologoDTO;
import com.dh.clinica_odontologica.exception.BadRequestException;
import com.dh.clinica_odontologica.model.Odontologo;
import com.dh.clinica_odontologica.service.IOdontologoService;
import com.dh.clinica_odontologica.service.impl.OdontologoService;
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
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    IOdontologoService odontologoService;

    //Instancia de Mapper
    @Autowired
    ObjectMapper mapper;

    //Instancia de logger
    private static final Logger logger = Logger.getLogger(OdontologoService.class);

    //Método POST para registrar un nuevo odontólogo (registrarOdontologo)
    @PostMapping()
    public ResponseEntity<?> agregarOdontologo(@RequestBody Odontologo odontologo) throws BadRequestException {
        ResponseEntity<String> response = null;
        odontologoService.registrarOdontologo(odontologo);
        //Código de estado de respuesta y mensaje
        response = ResponseEntity.status(HttpStatus.OK).body("Odontólogo de id(" + odontologo.getId() + ") registrado con éxito.");
        return response;
    }


    //Método GET para listar todos los odontólogos (listarTodosOdontologos)
    @GetMapping("/lista")
    public Set<OdontologoDTO> listarOdontologos() {
        List<Odontologo> listaOdontologos = odontologoService.listarTodosOdontologos();
        Set<OdontologoDTO> listaOdontologosDTO = new HashSet<>();
        //Conversión Odontologo a OdontologoDTO de cada elemento de la lista
        for(Odontologo odontologo: listaOdontologos)
            listaOdontologosDTO.add(mapper.convertValue(odontologo,OdontologoDTO.class));

        return listaOdontologosDTO;
    }


    //Método GET para buscar un odontólogo por id (buscarOdontologo)
    @GetMapping("/{id}")
    public OdontologoDTO obtenerOdontologo(@PathVariable Long id) throws Exception {
        //Conversión Odontologo a OdontologoDTO
        OdontologoDTO odontologoDTOEncontrado =mapper.convertValue(odontologoService.buscarOdontologo(id), OdontologoDTO.class);
        return odontologoDTOEncontrado;
    }


    //Método DELETE para eliminar un odontólogo por id (eliminarOdontologo)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id) {

        ResponseEntity<String> response = null;
        odontologoService.eliminarOdontologo(id);
        //Código de estado de respuesta
        response = ResponseEntity.status(HttpStatus.OK).body("Odontólogo de id(" + id + ") eliminado con éxito.");
        return response;
    }


    //Método PUT para actualizar datos de un odontólogo (modificarOdontologo)
    @PutMapping()
    public ResponseEntity<?> actualizarOdontologo(@RequestBody Odontologo odontologo)  {
        ResponseEntity<String> response = null;
        odontologoService.modificarOdontologo(odontologo);
        //Código de estado de respuesta y mensaje
        response = ResponseEntity.status(HttpStatus.OK).body("Odontólogo de id(" + odontologo.getId() + ") actualizado con éxito.");
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
