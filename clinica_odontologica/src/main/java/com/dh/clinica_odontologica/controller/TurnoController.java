package com.dh.clinica_odontologica.controller;

import com.dh.clinica_odontologica.dto.PacienteDTO;
import com.dh.clinica_odontologica.dto.TurnoDTO;
import com.dh.clinica_odontologica.model.Turno;
import com.dh.clinica_odontologica.service.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    ITurnoService turnoService;

    //Instancia de Mapper
    @Autowired
    ObjectMapper mapper;

    //Método POST para registrar un nuevo turno (registrarTurno)
    @PostMapping()
    public ResponseEntity<?> crearTurno(@RequestBody Turno turno) {
        ResponseEntity<String> response = null;
        turnoService.registrarTurno(turno);
        //Código de estado de respuesta y mensaje
        response = ResponseEntity.status(HttpStatus.OK).body("Turno creado con éxito.");
        return response;
    }


    //Método GET para listar todos los turnos (listarTodosTurnos)
    @GetMapping("/lista")
    public Set<TurnoDTO> listarTurnos() {
        List<Turno> listaTurnos = turnoService.listarTodosTurnos();
        Set<TurnoDTO> listaTurnosDTO = new HashSet<>();
        //Conversión Turno a TurnoDTO de cada elemento de la lista
        for(Turno turno: listaTurnos)
            listaTurnosDTO.add(mapper.convertValue(turno,TurnoDTO.class));

        return listaTurnosDTO;
    }


    //Método GET para buscar un turno por id (buscarTurno)
    @GetMapping("/{id}")
    public TurnoDTO obtenerTurno(@PathVariable Long id) throws Exception {
        //Conversión Turno a TurnoDTO
        TurnoDTO turnoDTOEncontrado =mapper.convertValue(turnoService.buscarTurno(id), TurnoDTO.class);
        return turnoDTOEncontrado;
    }

    //Método DELETE para eliminar un turno por id (eliminarTurno)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id) {

        ResponseEntity<String> response = null;
        turnoService.eliminarTurno(id);
        //Código de estado de respuesta
        response = ResponseEntity.status(HttpStatus.OK).body("Turno de id(" + id + ") eliminado con éxito.");
        return response;
    }


    //Método PUT para actualizar datos de un turno (modificarTurno)
    @PutMapping()
    public ResponseEntity<?> actualizarTurno(@RequestBody Turno turno) {
        ResponseEntity<String> response = null;
        turnoService.modificarTurno(turno);
        //Código de estado de respuesta y mensaje
        response = ResponseEntity.status(HttpStatus.OK).body("Turno de id(" + turno.getId() + ") actualizado con éxito.");
        return response;
    }

}
