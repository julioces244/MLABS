package com.tecsup.apaza.mts.Models;

import java.util.Date;
import java.util.List;

public class ResponseMessage {

    private List<Mantenimiento> mantenimientos;
    private Date fecha;

    public List<Mantenimiento> getMantenimientos() {
        return mantenimientos;
    }

    public void setMantenimientos(List<Mantenimiento> mantenimientos) {
        this.mantenimientos = mantenimientos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
