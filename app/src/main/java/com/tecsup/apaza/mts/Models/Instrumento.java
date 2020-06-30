package com.tecsup.apaza.mts.Models;

public class Instrumento {

    private Integer id;
    private String descripcion;
    private String nombre;
    private String modelo;
    private String serie;
    private Integer imagen;

    public Instrumento(Integer id, String nombre, String modelo, Integer imagen) {
        this.id = id;
        this.nombre = nombre;
        this.modelo = modelo;
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Integer getImagen() {
        return imagen;
    }

    public void setImagen(Integer imagen) {
        this.imagen = imagen;
    }
}
