/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Usuarios;

import Usuarios.Usuario;
import Vehiculos.Vehiculo;

/**
 *
 * @author rperez
 */
public class Usuario_General extends Usuario{
    
    protected Vehiculo vehiculo;

    public Usuario_General(String nombre, String usuario, String contraseña, int id) {
        super(nombre, usuario, contraseña, id);

    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }


    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
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
    
    public void asignarvehiculo(){
        
    }
    
    public void finalizarRuta(){
        
    }
    
    public void empezarRuta(){
        
    }
    
    public void consultarRutas(){
        
    }
    
    public void consultarVehiculo(){
        
    }
    
    public void consultarPoliza(){
        
    }
    
    
}
