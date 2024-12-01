/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal;

/**
 *
 * @author rperez
 */
public class Mecanico extends Usuario{
    
    protected String direccion;
    protected int estado;

    public Mecanico(String ubicacion,String nombre, String usuario, String contraseña, int id) {
        super(nombre, usuario, contraseña, id);
        this.direccion = ubicacion;
        this.estado = estado;
    }
    
    
    @Override
    public void IniciarSesion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void CerrarSesion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void ConsultarPerfil() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void ActualizarPerfil() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getUbicacion() {
        return direccion;
    }

    public int getEstado() {
        return estado;
    }

    public void setUbicacion(String ubicacion) {
        this.direccion = direccion;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
    public void crearOrdenReparacion(){
        
    }
    
    public void actualizarReparacion(){
        
    }
    
    public void consultarInventario(){
        
    }
    
    public void solicitarPieza(){
        
    }
    
    public void gestionarReparaciones(){
        
    }
    
    public void gestionarSolicitudPiezas(){
        
    }
    
    
    

}
