/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal;

/**
 *
 * @author rperez
 */
public class Poliza {
    
    protected int numeropoliza;
    protected int vencimiento;
    protected Usuario usuario;
    protected Vehiculo vehiculo;
    protected int estado;
    
    public Poliza(int numeropoliza, int vencimiento, Usuario usuario, Vehiculo vehiculo) {
        this.numeropoliza = numeropoliza;
        this.vencimiento = vencimiento;
        this.usuario = usuario;
        this.vehiculo = vehiculo;
    }
    
    public void setNumeropoliza(int numeropoliza) {
        this.numeropoliza = numeropoliza;
    }

    public void setVencimiento(int vencimiento) {
        this.vencimiento = vencimiento;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public int getNumeropoliza() {
        return numeropoliza;
    }

    public int getVencimiento() {
        return vencimiento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void consultarestado(){
        
    }
    
    public void renovarPoliza(){
        
    }
    
    public void notificacion(){
        
    }

    @Override
    public String toString() {
        return "Poliza{" + "numeropoliza=" + numeropoliza + ", vencimiento=" + vencimiento + ", usuario=" + usuario + ", vehiculo=" + vehiculo + '}';
    }
    
    
    
}
