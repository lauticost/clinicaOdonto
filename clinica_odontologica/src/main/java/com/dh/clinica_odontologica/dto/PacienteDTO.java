package com.dh.clinica_odontologica.dto;

import com.dh.clinica_odontologica.model.Domicilio;


public class PacienteDTO {
    //Atributos
    private String nombre;
    private String apellido;
    private Domicilio domicilio;
    private String dni;



    //Getters y Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }


    @Override
    public String toString() {
        return "PacienteDTO{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", domicilio=" + domicilio +
                ", dni='" + dni + '\'' +
                '}';
    }
}
