package com.dh.clinica_odontologica.controller;

import com.dh.clinica_odontologica.dto.PacienteDTO;
import com.dh.clinica_odontologica.exception.BadRequestException;
import com.dh.clinica_odontologica.model.Paciente;
import com.dh.clinica_odontologica.service.IPacienteService;
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
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    IPacienteService pacienteService;

    //Instancia de Mapper
    @Autowired
    ObjectMapper mapper;

    //Instancia de logger
    private static final Logger logger = Logger.getLogger(OdontologoService.class);


    //Método POST para registrar un nuevo paciente (registrarPaciente)
    @PostMapping()
    public ResponseEntity<?> agregarPaciente(@RequestBody Paciente paciente) throws BadRequestException {
        ResponseEntity<String> response = null;
        pacienteService.registrarPaciente(paciente);
        //Código de estado de respuesta y mensaje
        response = ResponseEntity.status(HttpStatus.OK).body("Paciente de id(" + paciente.getId() + ") registrado con éxito.");
        return response;
    }


    //Método GET para listar todos los pacientes (listarTodosPacientes)
    @GetMapping("/lista")
    public Set<PacienteDTO> listarPacientes() {
        List<Paciente> listaPacientes = pacienteService.listarTodosPacientes();
        Set<PacienteDTO> listaPacientesDTO = new HashSet<>();
        //Conversión Paciente a PacienteDTO de cada elemento de la lista
        for(Paciente paciente: listaPacientes)
            listaPacientesDTO.add(mapper.convertValue(paciente,PacienteDTO.class));

        return listaPacientesDTO;
    }


    //Método GET para buscar un paciente por id (buscarPaciente)
    @GetMapping("/{id}")
    public PacienteDTO obtenerPaciente(@PathVariable Long id) throws Exception {
        //Conversión Paciente a PacienteDTO
        PacienteDTO pacienteDTOEncontrado =mapper.convertValue(pacienteService.buscarPaciente(id), PacienteDTO.class);
        return pacienteDTOEncontrado;
    }


    //Método DELETE para eliminar un paciente por id (eliminarPaciente)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id) {

        ResponseEntity<String> response = null;
        pacienteService.eliminarPaciente(id);
        //Código de estado de respuesta
        response = ResponseEntity.status(HttpStatus.OK).body("Paciente de id(" + id + ") eliminado con éxito.");
        return response;
    }


    //Método PUT para actualizar datos de un paciente (modificarPaciente)
    @PutMapping()
    public ResponseEntity<?> actualizarPaciente(@RequestBody Paciente paciente) throws BadRequestException {
        ResponseEntity<String> response = null;
        pacienteService.modificarPaciente(paciente);
        //Código de estado de respuesta y mensaje
        response = ResponseEntity.status(HttpStatus.OK).body("Paciente de id(" + paciente.getId() + ") actualizado con éxito.");
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


