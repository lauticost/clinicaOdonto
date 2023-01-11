package com.dh.clinica_odontologica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "pacientes")
public class Paciente {
    //Atributos

    //Se indica al id como clave primaria y autogenerado
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private Date fechaAlta;

    //Relación 1 a 1 con Domicilio
    @OneToOne(cascade = CascadeType.ALL)
    //Clave foránea de domicilios en tabla pacientes, no nula
    @JoinColumn(name = "domicilio_id", nullable = false, referencedColumnName = "id")
    private Domicilio domicilio;

    //Relación 1 a Muchos con Turno
    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Turno> turnos;

    //Constructores
    public Paciente(){};

    public Paciente(String nombre, String apellido, Domicilio domicilio, String dni, Date fechaAlta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.dni = dni;
        this.fechaAlta = fechaAlta;
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

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Set<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(Set<Turno> turnos) {
        this.turnos = turnos;
    }

    //Método Imprimir datos Paciente
    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", domicilio=" + domicilio +
                ", dni='" + dni + '\'' +
                ", fechaAlta=" + fechaAlta +
                '}';
    }
}
