package com.dh.clinica_odontologica.model;

import javax.persistence.*;

@Entity
@Table(name = "domicilios")
public class Domicilio {
    //Atributos

    //Se indica al id como clave primaria y autogenerado
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    private String calle;
    private int altura;
    private String codigoPostal;
    private String ciudad;

    //Constructores
    public Domicilio(){};

    public Domicilio(String calle, int altura, String codigoPostal, String ciudad) {
        this.calle = calle;
        this.altura = altura;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
    }

    //Getters y Setters
    //Para ID no se crea setter ya que es autoincremental y se busca que no pueda ser accedido para setear su valor

    public Long getId() {
        return id;
    }


    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }


    //Método Imprimir datos Domicilio
    @Override
    public String toString() {
        return "Domicilio{" +
                "id=" + id +
                ", calle='" + calle + '\'' +
                ", altura=" + altura +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", ciudad='" + ciudad + '\'' +
                '}';
    }
}
