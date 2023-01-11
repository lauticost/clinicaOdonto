package com.dh.clinica_odontologica;

import com.dh.clinica_odontologica.exception.BadRequestException;
import com.dh.clinica_odontologica.model.Odontologo;
import com.dh.clinica_odontologica.service.IOdontologoService;
import org.apache.log4j.Logger;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;



@SpringBootTest
class OdontologoServiceTest {
    //Instancia de logger
    private static final Logger logger = Logger.getLogger(OdontologoServiceTest.class);

    //Instancia de OdontologoService
    @Autowired
    IOdontologoService odontologoService;

    //Declaración de variables globales de datos de prueba
    Odontologo odontologo1;
    Odontologo odontologo2;
    Odontologo odontologo3;


    @BeforeEach
    public void cargarDatosPrueba() throws BadRequestException {
        //Generación de datos de prueba
        odontologo1 = new Odontologo("Gerardo", "Muela", 011);
        odontologo2 = new Odontologo("Jualiana", "Implante", 012);
        odontologo3 = new Odontologo("Pedro", "Escamoso", 231);


        odontologoService.registrarOdontologo(odontologo1);
        odontologoService.registrarOdontologo(odontologo2);
        odontologoService.registrarOdontologo(odontologo3);

        //Informa datos de prueba
        logger.info("Datos de prueba para test: " + '\n' + odontologo1.toString() + '\n' + odontologo2.toString() + '\n' + odontologo3.toString());
    }


    @Test
    //Test método Registrar Odontólogo
    public void registrarOdontologo(){
        logger.info("Se realiza el test del método REGISTRAR ODONTOLOGO.");
        assertTrue(odontologo1 != null);
    }


        @Test
    //Test método Listar Odontologos
    public void listarOdontologos(){
        logger.info("Se realiza el test del método LISTAR ODONTOLOGOS.");

        List<Odontologo> odontologoList = odontologoService.listarTodosOdontologos();
        //Comprueba longitud de arreglo odontologos mayor a 0
        assertTrue(odontologoList.size() > 0);
    }



    @Test
    //Test método Modificar Odontólogo
    public void modificarOdontologo()  {
        logger.info("Se realiza el test del método MODIFICAR ODONTOLOGO.");
        odontologo2 = new Odontologo("Jualiana", "Caries", 012);
        odontologoService.modificarOdontologo(odontologo2);
        assertEquals("Caries", odontologo2.getApellido());
    }

}