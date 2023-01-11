package com.dh.clinica_odontologica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "odontologos")
public class Odontologo {
    //Atributos

    //Se indica al id como clave primaria y autogenerado
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    private String nombre;
    private String apellido;
    private int matricula;

    //Relación 1 a Muchos con Turno
    @OneToMany(mappedBy = "odontologo", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Turno> turnos;



    //Constructores

    public Odontologo(){};

    public Odontologo(String nombre, String apellido, int matricula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }

    //Getters y Setters
    //Para ID no se crea setter ya que es autoincremental y se busca que no pueda ser accedido para setear su valor
    public Long getId() {
        return id;
    }



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

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public Set<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(Set<Turno> turnos) {
        this.turnos = turnos;
    }

    //Método Imprimir datos Odontologo
    @Override
    public String toString() {
        return "Odontologo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", matricula='" + matricula + '\'' +
                '}';
    }
}
