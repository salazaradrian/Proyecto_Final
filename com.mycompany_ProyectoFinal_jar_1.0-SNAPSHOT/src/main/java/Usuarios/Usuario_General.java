/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Usuarios;

import Ordenes.OrdenReparacion;
import Usuarios.Usuario;
import Vehiculos.Vehiculo;
import java.util.ArrayList;

/**
 *
 * @author rperez
 */
public class Usuario_General extends Usuario{
    
    protected ArrayList<Vehiculo> vehiculos;
    protected ArrayList<OrdenReparacion> ordenes_reparacion;

    public Usuario_General(String nombre, String usuario, String contraseña, Rol rol) {
        super(nombre, usuario, contraseña, rol);

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

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public ArrayList<OrdenReparacion> getOrdenes_reparacion() {
        return ordenes_reparacion;
    }

    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public void setOrdenes_reparacion(ArrayList<OrdenReparacion> ordenes_reparacion) {
        this.ordenes_reparacion = ordenes_reparacion;
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
