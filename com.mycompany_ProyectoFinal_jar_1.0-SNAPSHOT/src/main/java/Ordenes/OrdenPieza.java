/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ordenes;

import java.util.ArrayList;

/**
 *
 * @author rperez
 */
public class OrdenPieza extends Orden{
    protected int cantidadMotor;
    protected int candtidadChasis;
    protected int cantidadFrenos;
    protected int cantidadcaja;
    protected int cantidadFocos;
    protected int cantidadLlantas;
    protected int cantidadBateria;
    protected ArrayList<Piezas> piezas;

    public OrdenPieza(int cantidadMotor, int candtidadChasis, int cantidadFrenos, int cantidadcaja, int cantidadFocos, int cantidadLlantas, int cantidadBateria, float precio) {
        super(Estado.Pendiente, 0.0f);
        this.cantidadMotor = cantidadMotor;
        this.candtidadChasis = candtidadChasis;
        this.cantidadFrenos = cantidadFrenos;
        this.cantidadcaja = cantidadcaja;
        this.cantidadFocos = cantidadFocos;
        this.cantidadLlantas = cantidadLlantas;
        this.cantidadBateria = cantidadBateria;
    }
    
    
    
    
    
}
