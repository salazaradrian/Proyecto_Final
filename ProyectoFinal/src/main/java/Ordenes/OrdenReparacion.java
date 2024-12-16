/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ordenes;
import Vehiculos.Vehiculo;
import java.util.ArrayList;

/**
 *
 * @author rperez
 */
public class OrdenReparacion extends Orden{
    
    protected ArrayList<Piezas> piezas;
    protected Vehiculo vehiculo;
    protected String descripcion_problema;

    public OrdenReparacion(Vehiculo vehiculo, String descripcion_problema) {
        super(Estado.Pendiente, 0.0f); 
        this.piezas = new ArrayList<>();
        this.vehiculo = vehiculo;
    }
    
    
    public ArrayList<Piezas> getPiezas() {
        return piezas;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setPiezas(ArrayList<Piezas> piezas) {
        this.piezas = piezas;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public String toString() {
        return "OrdenReparacion{" + "piezas=" + piezas + ", vehiculo=" + vehiculo + '}';
    }
    
}
