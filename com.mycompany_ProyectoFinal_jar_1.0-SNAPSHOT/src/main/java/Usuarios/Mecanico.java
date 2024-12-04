/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Usuarios;

import Ordenes.OrdenPieza;
import Ordenes.OrdenReparacion;
import Usuarios.Usuario;
import java.util.ArrayList;

/**
 *
 * @author rperez
 */
public class Mecanico extends Usuario{
    
    private ArrayList<OrdenReparacion> ordenes_reparacion;
    private ArrayList<OrdenPieza> ordenes_piezas;
    
    
    
    public Mecanico(String ubicacion,String nombre, String usuario, String contraseña, Rol rol) {
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

    public ArrayList<OrdenReparacion> getOrdenes_reparacion() {
        return ordenes_reparacion;
    }

    public ArrayList<OrdenPieza> getOrdenes_piezas() {
        return ordenes_piezas;
    }

    public void setOrdenes_reparacion(ArrayList<OrdenReparacion> ordenes_reparacion) {
        this.ordenes_reparacion = ordenes_reparacion;
    }

    public void setOrdenes_piezas(ArrayList<OrdenPieza> ordenes_piezas) {
        this.ordenes_piezas = ordenes_piezas;
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
