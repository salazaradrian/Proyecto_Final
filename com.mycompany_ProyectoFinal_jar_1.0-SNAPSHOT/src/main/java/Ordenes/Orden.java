/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ordenes;

/**
 *
 * @author rperez
 */
public abstract class Orden {
    
    protected Estado estado;
    protected float precio;

    public Orden(Estado estado, float precio) {
        this.estado = estado;
        this.precio = precio;
    }

    public Estado getEstado() {
        return estado;
    }

    public float getPrecio() {
        return precio;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    
    
}
