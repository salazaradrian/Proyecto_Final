/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos;

import Usuarios.Usuario;

/**
 *
 * @author rperez
 */
public abstract class Vehiculo {
    
    protected String marca;
    protected String modelo;
    protected int año;
    protected String placa;
    protected String ubicacion;
    protected Usuario usuario;
    protected int cargamaxima;
    protected int cargaactual;

    public Vehiculo(int cargamaxima, String marca, String modelo, int año, String placa) {
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.placa = placa;
        this.cargamaxima = cargamaxima;
    }

    public int getCargaactual() {
        return cargaactual;
    }

    public void setCargaactual(int cargaactual) {
        this.cargaactual = cargaactual;
    }
    
    

    public void setCargamaxima(int cargamaxima) {
        this.cargamaxima = cargamaxima;
    }

    public int getCargamaxima() {
        return cargamaxima;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAño() {
        return año;
    }

    public String getPlaca() {
        return placa;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    public abstract void actualizarUsuario();
    public abstract void actualzarubicacion();
    public abstract void obtenerubicacion();
    
    
}
