/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal;

/**
 *
 * @author rperez
 */
public class Ruta {
    
    protected String destino;
    protected Vehiculo vehiculo;
    protected Usuario usuario;
    protected String fecheasignada;
    protected String fechafinalizada;
    protected int estado;
    protected int carga;
    protected TipoCarga tipocarga;

    public Ruta(TipoCarga tipocarga, String destino, String fecheasignada, int estado, int carga) {
        this.tipocarga = tipocarga;
        this.destino = destino;
        this.fecheasignada = fecheasignada;
        this.estado = estado;
        this.carga = carga;
    }

    public String getDestino() {
        return destino;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getFecheasignada() {
        return fecheasignada;
    }

    public int getEstado() {
        return estado;
    }

    public int getCarga() {
        return carga;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setFecheasignada(String fecheasignada) {
        this.fecheasignada = fecheasignada;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setCarga(int carga) {
        this.carga = carga;
    }
    
    public void actualizarestado(){
        
    }
    
    public void actualizardireccion(){
        
    }
    
    public void actualizarvehiculo(){
        
    }
    
    
    
    
}
