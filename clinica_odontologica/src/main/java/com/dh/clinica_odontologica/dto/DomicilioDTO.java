package com.dh.clinica_odontologica.dto;

public class DomicilioDTO {
    //Atributos
    private String calle;
    private int altura;
    private String codigoPostal;
    private String ciudad;

    //Getters y Setters

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

    @Override
    public String toString() {
        return "DomicilioDTO{" +
                ", calle='" + calle + '\'' +
                ", altura=" + altura +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", ciudad='" + ciudad + '\'' +
                '}';
    }
}
