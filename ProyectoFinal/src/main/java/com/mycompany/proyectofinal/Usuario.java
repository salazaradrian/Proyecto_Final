/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal;

/**
 *
 * @author rperez
 */
public abstract class Usuario {
    protected String nombre;
    protected String usuario;
    protected String contraseña;
    protected int id;

    public Usuario(String nombre, String usuario, String contraseña, int id) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.id = id;
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

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }
//    
    
    public abstract void IniciarSesion();
    public abstract void CerrarSesion();
    public abstract void ConsultarPerfil();
    public abstract void ActualizarPerfil();
    
    
}
