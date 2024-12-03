/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ordenes;

/**
 *
 * @author rperez
 */
public class SolicitudPieza {
    
    protected String pieza;
    protected int id;
    protected String fechaingreso;
    protected String fechacompletada;
    protected int estado;
    protected int cantidad;

    public SolicitudPieza(String pieza, int id, String fechaingreso, int cantidad) {
        this.pieza = pieza;
        this.id = id;
        this.fechaingreso = fechaingreso;
        this.cantidad = cantidad;
    }

    public String getPieza() {
        return pieza;
    }

    public int getId() {
        return id;
    }

    public String getFechaingreso() {
        return fechaingreso;
    }

    public String getFechacompletada() {
        return fechacompletada;
    }

    public int getEstado() {
        return estado;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setPieza(String pieza) {
        this.pieza = pieza;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFechaingreso(String fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public void setFechacompletada(String fechacompletada) {
        this.fechacompletada = fechacompletada;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
    
    @Override
    public String toString() {
        return "SolicitudPieza{" + "pieza=" + pieza + ", id=" + id + ", fechaingreso=" + fechaingreso + ", fechacompletada=" + fechacompletada + ", estado=" + estado + ", cantidad=" + cantidad + '}';
    }
    
    
    
    
    
}
