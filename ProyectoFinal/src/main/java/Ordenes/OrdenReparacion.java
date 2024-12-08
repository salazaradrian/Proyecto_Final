/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ordenes;
import Database.Conexion;
import Vehiculos.Vehiculo;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rperez
 */
public class OrdenReparacion {
    
    private int orden;
    private String estado;
    private Vehiculo vehiculo;
    private String descripcionproblema;
    protected ArrayList<String> piezas;

    public OrdenReparacion(int orden, String estado, Vehiculo vehiculo,String descripcionproblema) {
        this.orden = orden;
        this.estado = estado;
        this.vehiculo = vehiculo;
        this.descripcionproblema = descripcionproblema;
    }

    
    //Metodos para el CRUD
    Conexion conexion = new Conexion();
     DefaultTableModel modelo = new DefaultTableModel(){
            
            // Hacer que la tabla no sea editable
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            } 
        };
    
    
    
     
     public void agregar(){
         Conexion conexion = new Conexion();
         
         String sql = conexion.conectar().prepareCall(sql);
         
          cs.setString(1, this.marca);
            cs.setString(2, this.tipo);
            cs.setDouble(3, this.precioPorHora);
            cs.setString(4, this.estado.toString());

            cs.execute();

            JOptionPane.showMessageDialog(null, "El resgistro se a guardado de manera exitosa");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar el registros");
            System.out.println("Error Consulta: " + ex.toString());
        } finally {
            conexion.desconectar();
        }
  
// METODO PARA ELIMINAR registros (DELETE)
    public void eliminar() {
        Conexion conexion = new Conexion();

        String sql = "DELETE FROM ordenreparacion WHERE codigo = ?";
        
        try {
            CallableStatement cs = conexion.conectar().prepareCall(sql);

            cs.setInt(1, this.codigo);

            cs.execute();

            JOptionPane.showMessageDialog(null, "El resgistro se a eliminado de manera exitosa");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el registros");
            System.out.println("Error Consulta: " + ex.toString());
        } finally {
            conexion.desconectar();
        }
         
         
         
         
     }
    
    
    
    
    
    
    
    
    
    
    public int getOrden() {
        return orden;
    }


public String getDescripcionproblema(){
    return descripcionproblema;

} 

public String setDescripcionproblema(){
    return descripcionproblema;

}  
    

    public String getEstado() {
        return estado;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public ArrayList<String> getPiezas() {
        return piezas;
    }

    public void setPiezas(ArrayList<String> piezas) {
        this.piezas = piezas;
    }
    
    
    public void actualiazarestado(){
        
    }
    
    public void agregarPieza(){
        
    }
    
    public void removerPieza(){
        
    }
    
    
}
