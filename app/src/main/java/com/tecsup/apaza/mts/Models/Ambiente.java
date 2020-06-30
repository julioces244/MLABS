package com.tecsup.apaza.mts.Models;

public class Ambiente {
    private Integer idambiente;
    private String codigo;
    private String nombre;
    private Integer ncomputadoras;
    private Integer colegios_idcolegios;

    public Integer getIdambiente() {
        return idambiente;
    }

    public void setIdambiente(Integer idambiente) {
        this.idambiente = idambiente;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNcomputadoras() {
        return ncomputadoras;
    }

    public void setNcomputadoras(Integer ncomputadoras) {
        this.ncomputadoras = ncomputadoras;
    }

    public Integer getColegios_idcolegios() {
        return colegios_idcolegios;
    }

    public void setColegios_idcolegios(Integer colegios_idcolegios) {
        this.colegios_idcolegios = colegios_idcolegios;
    }
}
