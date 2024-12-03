/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Usuarios;

/**
 *
 * @author rperez
 */
public abstract class Usuario {
    protected String nombre;
    protected String usuario;
    protected String contraseña;
    protected Rol rol;

    public Usuario(String nombre, String usuario, String contraseña, Rol rol) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public Rol getRol() {
        return rol;
    }
    


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    

//    
    
    public abstract void IniciarSesion();
    public abstract void CerrarSesion();
    public abstract void ConsultarPerfil();
    public abstract void ActualizarPerfil();
    
    
}
