/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ordenes;
import Vehiculos.Vehiculo;
import java.util.ArrayList;

/**
 *
 * @author rperez
 */
public class OrdenReparacion {
    
    protected int orden;
    protected String estado;
    protected Vehiculo vehiculo;
    protected ArrayList<String> piezas;

    public OrdenReparacion(int orden, String estado, Vehiculo vehiculo) {
        this.orden = orden;
        this.estado = estado;
        this.vehiculo = vehiculo;
    }

    public int getOrden() {
        return orden;
    }

    public String getEstado() {
        return estado;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public ArrayList<String> getPiezas() {
        return piezas;
    }

    public void setPiezas(ArrayList<String> piezas) {
        this.piezas = piezas;
    }
    
    
    public void actualiazarestado(){
        
    }
    
    public void agregarPieza(){
        
    }
    
    public void removerPieza(){
        
    }
    
    
}
