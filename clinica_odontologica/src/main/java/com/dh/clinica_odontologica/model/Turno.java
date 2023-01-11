package com.dh.clinica_odontologica.model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "turnos")
public class Turno {
    //Atributos

    //Se indica al id como clave primaria y autogenerado
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    private Date fechaTurno;

    //Relación Muchos a 1 con Odontologo
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    //Clave foránea de odontólogos en tabla turnos, no nula
    @JoinColumn(name = "odontologo_id", nullable = false, referencedColumnName = "id")
    private Odontologo odontologo;
    //Relación Muchos a 1 con Pacientes
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    //Clave foránea de pacientes, no nula
    @JoinColumn(name = "paciente_id", nullable = false, referencedColumnName = "id")
    private Paciente paciente;


    //Constructor
    public Turno() {
    }

    //Getters y Setters
    //Para ID no se crea setter ya que es autoincremental y se busca que no pueda ser accedido para setear su valor
    public Long getId() {
        return id;
    }

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

    public Date getFechaTurno() {
        return fechaTurno;
    }

    public void setFechaTurno(Date fechaTurno) {
        this.fechaTurno = fechaTurno;
    }

    //Método Imprimir datos Turno

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", odontologo=" + odontologo +
                ", paciente=" + paciente +
                ", fechaTurno=" + fechaTurno +
                '}';
    }
}
