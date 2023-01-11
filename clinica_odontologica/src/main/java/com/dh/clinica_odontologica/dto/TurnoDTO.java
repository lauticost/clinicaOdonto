package com.dh.clinica_odontologica.dto;

import com.dh.clinica_odontologica.model.Odontologo;
import com.dh.clinica_odontologica.model.Paciente;


public class TurnoDTO {

    //Atributos
    private Odontologo odontologo;
    private Paciente paciente;


    //Getters y Setters

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "TurnoDTO{" +
                "odontologo=" + odontologo +
                ", paciente=" + paciente +
                '}';
    }
}
