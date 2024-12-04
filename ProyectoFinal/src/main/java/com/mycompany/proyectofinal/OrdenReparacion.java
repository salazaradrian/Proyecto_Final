/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal;
import java.util.ArrayList;

/**
 *
 * @author rperez
 */
public class OrdenReparacion {
    
    private int orden;
    private String marca;
    private String placa;
    private String descripcionProblema;
    private String estado;
    private Vehiculo vehiculo;
    private ArrayList<String> piezas;

    public OrdenReparacion(int orden, String marca,String placa, String descripcionProblema, String estado,Vehiculo vehiculo) {
        
        this.orden = orden;
        this.marca = marca;
        this.placa = placa;
        this.descripcionProblema = descripcionProblema;
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
