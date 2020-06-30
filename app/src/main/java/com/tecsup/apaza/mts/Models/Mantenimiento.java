package com.tecsup.apaza.mts.Models;

import java.util.Date;

public class Mantenimiento {

    private Integer idmantenimiento;
    private String descripcion;
    private Date flimite;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdmantenimiento() {
        return idmantenimiento;
    }

    public void setIdmantenimiento(Integer idmantenimiento) {
        this.idmantenimiento = idmantenimiento;
    }

    public Date getFlimite() {
        return flimite;
    }

    public void setFlimite(Date flimite) {
        this.flimite = flimite;
    }
}
