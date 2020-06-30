package com.tecsup.apaza.mts.Models;

public class Colegio {

    private Integer idcolegio;
    private String nombre;
    private String imagen;
    private String latitud;
    private String longitud;
    private String abreviatura;

    public Integer getIdcolegio() {
        return idcolegio;
    }

    public void setIdcolegio(Integer idcolegio) {
        this.idcolegio = idcolegio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }
}
