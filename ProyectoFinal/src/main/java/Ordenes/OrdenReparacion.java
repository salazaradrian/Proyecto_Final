/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ordenes;

import Database.Conexion;
import Vehiculos.Vehiculo;
import java.util.ArrayList;
import Vehiculos.Tipo;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rperez
 */
public class OrdenReparacion extends Orden {

    protected ArrayList<Piezas> piezas;
    protected int id;
    protected Vehiculo vehiculo;
    protected int vehiculo_id;
    protected String descripcion_problema;
    protected float precio;

    public OrdenReparacion(int id) {
        super(Estado.EnProceso, 0.0f);
        this.id = id;
    }

    public OrdenReparacion(String marca, String modelo, String placa, Tipo tipo, String descripcion_problema) {
        super(Estado.Pendiente, 0.0f);
        Vehiculo vehiculo = new Vehiculo(marca, modelo, placa, tipo);
        this.vehiculo_id = vehiculo.agregar();

        this.vehiculo = vehiculo;
        this.descripcion_problema = descripcion_problema;

    }

    public OrdenReparacion(int id, int cantidadMotor, int cantidadChasis, int cantidadFrenos, int cantidadCaja, int cantidadArrancador, int cantidadLlantas, int cantidadBateria, Estado estado) {
        super(Estado.EnProceso, 0.0f);
        this.id = id;

        ArrayList<Piezas> piezas = new ArrayList<>();

        agregarPiezas(Piezas.Motor, cantidadMotor, piezas);
        agregarPiezas(Piezas.Chasis, cantidadChasis, piezas);
        agregarPiezas(Piezas.Frenos, cantidadFrenos, piezas);
        agregarPiezas(Piezas.Caja, cantidadCaja, piezas);
        agregarPiezas(Piezas.Arrancador, cantidadArrancador, piezas);
        agregarPiezas(Piezas.Llantas, cantidadLlantas, piezas);
        agregarPiezas(Piezas.Bateria, cantidadBateria, piezas);

        this.piezas = piezas;
        this.precio = CalcularPrecio(cantidadMotor, cantidadChasis, cantidadFrenos, cantidadCaja, cantidadArrancador, cantidadLlantas, cantidadBateria);
        this.estado = estado;
    }

    private float CalcularPrecio(int cantidadMotor, int cantidadChasis, int cantidadFrenos, int cantidadCaja, int cantidadArrancador, int cantidadLlantas, int cantidadBateria) {
        float precioMotor = 100.0f;
        float precioChasis = 150.0f;
        float precioFrenos = 50.0f;
        float precioCaja = 80.0f;
        float precioArrancador = 30.0f;
        float precioLlantas = 60.0f;
        float precioBateria = 40.0f;

        return (cantidadMotor * precioMotor)
                + (cantidadChasis * precioChasis)
                + (cantidadFrenos * precioFrenos)
                + (cantidadCaja * precioCaja)
                + (cantidadArrancador * precioArrancador)
                + (cantidadLlantas * precioLlantas)
                + (cantidadBateria * precioBateria);
    }

    private void agregarPiezas(Piezas pieza, int cantidad, ArrayList<Piezas> piezas) {
        for (int i = 0; i < cantidad; i++) {
            piezas.add(pieza);
        }
    }

    public void agregar() {
        String sql = "INSERT INTO orden_reparacion (vehiculo_id, descripcion_problema, estado) VALUES (?, ?, ?)";
        Conexion conexion = new Conexion();
        int generatedId = -1;

        try {

            PreparedStatement ps = conexion.conectar().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, this.vehiculo_id); // Foreign key to vehiculo
            ps.setString(2, this.descripcion_problema);
            ps.setString(3, this.estado.toString());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "El registro se ha guardado de manera exitosa");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar el registro");
            System.out.println("Error Consulta: " + ex.toString());
        } finally {
            conexion.desconectar();
        }
    }

    public void eliminar() {
        Conexion conexion = new Conexion();

        String sql = "DELETE FROM orden_reparacion WHERE id = ?";

        try {
            
            CallableStatement cs = conexion.conectar().prepareCall(sql);

            
            cs.setInt(1, this.id);

            
            cs.execute();

            
            JOptionPane.showMessageDialog(null, "El registro se ha eliminado de manera exitosa");

        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, "Error al eliminar el registro");
            System.out.println("Error Consulta: " + ex.toString());
        } finally {
            
            conexion.desconectar();
        }
    }

    public void editar() {
        Conexion conexion = new Conexion();

        String sql = "UPDATE orden_reparacion SET precio = ?, piezas = ?, estado = ? WHERE id = ?";

        try {
            
            CallableStatement cs = conexion.conectar().prepareCall(sql);

            
            cs.setFloat(1, this.precio); 
            cs.setString(2, this.piezas.toString()); 
            cs.setString(3, this.estado.toString()); 
            cs.setInt(4, this.id); 

           
            cs.execute();

            
            JOptionPane.showMessageDialog(null, "El registro se ha editado de manera exitosa");

        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, "Error al editar el registro");
            System.out.println("Error Consulta: " + ex.toString());
        } finally {
            
            conexion.desconectar();
        }
    }

    public static DefaultTableModel consultar() {
        Conexion conexion = new Conexion();

        
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        // Add columns to the model
        modelo.addColumn("Orden");
        modelo.addColumn("Marca");
        modelo.addColumn("Modelo");
        modelo.addColumn("Placa");
        modelo.addColumn("Tipo");
        modelo.addColumn("Piezas");
        modelo.addColumn("Estado");
        modelo.addColumn("Precio");
        modelo.addColumn("Des. Problema");

        String datos[] = new String[9]; // Array to store row data
        String sql = "SELECT o.id, v.marca, v.modelo, v.placa, v.tipoVehiculo, o.piezas, o.estado, o.precio, o.descripcion_problema "
                + "FROM orden_reparacion o "
                + "JOIN vehiculo v ON o.vehiculo_id = v.id";

        try {
            Statement stmt = conexion.conectar().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                datos[0] = String.valueOf(rs.getInt("id")); 
                datos[1] = rs.getString("marca");
                datos[2] = rs.getString("modelo");
                datos[3] = rs.getString("placa");
                datos[4] = rs.getString("tipoVehiculo");
                datos[5] = rs.getString("piezas");
                datos[6] = rs.getString("estado");
                datos[7] = String.valueOf(rs.getFloat("precio"));
                datos[8] = rs.getString("descripcion_problema");

                modelo.addRow(datos); // Add the row to the table model
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al consultar los registros");
            System.out.println("Error Consulta: " + ex.toString());
        } finally {
            conexion.desconectar();
        }

        return modelo;
    }
    
    
    public void compararInventario(int motor, int chasis, int llantas, int frenos, int caja, int bateria, int arrancador) {
        Conexion conexion = new Conexion();
        ArrayList<Integer> inventario = new ArrayList<>();

        try {
            String sqlSelect = "SELECT totalMotor, totalChasis, totalFrenos, totalCaja, totalArrancador, totalLlantas, totalBateria "
                    + "FROM inventario_piezas WHERE id = 1"; // Assuming a single row for inventory
            PreparedStatement psSelect = conexion.conectar().prepareStatement(sqlSelect);
            ResultSet rs = psSelect.executeQuery();

            if (rs.next()) {

                int currentMotor = rs.getInt("totalMotor");
                int currentChasis = rs.getInt("totalChasis");
                int currentFrenos = rs.getInt("totalFrenos");
                int currentCaja = rs.getInt("totalCaja");
                int currentArrancador = rs.getInt("totalArrancador");
                int currentLlantas = rs.getInt("totalLlantas");
                int currentBateria = rs.getInt("totalBateria");

                if(motor <= currentMotor & chasis <= currentChasis & frenos <= currentFrenos & caja <= currentCaja & arrancador <= currentArrancador & llantas <= currentLlantas & bateria <=currentBateria){
                    
                    OrdenReparacion.restarInventario(motor, chasis, llantas, frenos, caja, bateria, arrancador);
                    this.editar();
                }else{
                    JOptionPane.showMessageDialog(null, "No es posible actualizar la orden");
                }

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al editar el registro");
            System.out.println("Error Consulta: " + ex.toString());
        } finally {
            conexion.desconectar();
        }

    }

    public static void restarInventario(int motor, int chasis, int llantas, int frenos, int caja, int bateria, int arrancador) {
        Conexion conexion = new Conexion();

        try {
            String sqlSelect = "SELECT totalMotor, totalChasis, totalFrenos, totalCaja, totalFocos, totalLlantas, totalBateria "
                    + "FROM inventario_piezas WHERE id = 1"; // Assuming a single row for inventory
            PreparedStatement psSelect = conexion.conectar().prepareStatement(sqlSelect);
            ResultSet rs = psSelect.executeQuery();

            if (rs.next()) {

                int currentMotor = rs.getInt("totalMotor");
                int currentChasis = rs.getInt("totalChasis");
                int currentFrenos = rs.getInt("totalFrenos");
                int currentCaja = rs.getInt("totalCaja");
                int currentArrancador = rs.getInt("totalArrancador");
                int currentLlantas = rs.getInt("totalLlantas");
                int currentBateria = rs.getInt("totalBateria");

                int updatedMotor = currentMotor - motor;
                int updatedChasis = currentChasis - chasis;
                int updatedFrenos = currentFrenos - frenos;
                int updatedCaja = currentCaja - caja;
                int updatedArrancador = currentArrancador - arrancador;
                int updatedLlantas = currentLlantas - llantas;
                int updatedBateria = currentBateria - bateria;

                String sqlUpdate = "UPDATE inventario_piezas SET totalMotor = ?, totalChasis = ?, totalFrenos = ?, "
                        + "totalCaja = ?, totalArrancador = ?, totalLlantas = ?, totalBateria = ? WHERE id = 1";

                PreparedStatement psUpdate = conexion.conectar().prepareStatement(sqlUpdate);

                psUpdate.setInt(1, updatedMotor);
                psUpdate.setInt(2, updatedChasis);
                psUpdate.setInt(3, updatedFrenos);
                psUpdate.setInt(4, updatedCaja);
                psUpdate.setInt(5, updatedArrancador);
                psUpdate.setInt(6, updatedLlantas);
                psUpdate.setInt(7, updatedBateria);

                int rowsAffected = psUpdate.executeUpdate();
                JOptionPane.showMessageDialog(null, "Inventario actualizado correctamente.");

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al editar el registro");
            System.out.println("Error Consulta: " + ex.toString());
        } finally {
            conexion.desconectar();
        }
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
