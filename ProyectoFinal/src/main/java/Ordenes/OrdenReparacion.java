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

    public OrdenReparacion(int id, int cantidadMotor, int cantidadChasis, int cantidadFrenos, int cantidadCaja, int cantidadFocos, int cantidadLlantas, int cantidadBateria, Estado estado) {
        super(Estado.EnProceso, 0.0f);
        this.id = id;

        ArrayList<Piezas> piezas = new ArrayList<>();

        agregarPiezas(Piezas.Motor, cantidadMotor, piezas);
        agregarPiezas(Piezas.Chasis, cantidadChasis, piezas);
        agregarPiezas(Piezas.Frenos, cantidadFrenos, piezas);
        agregarPiezas(Piezas.Caja, cantidadCaja, piezas);
        agregarPiezas(Piezas.Arrancador, cantidadFocos, piezas);
        agregarPiezas(Piezas.Llantas, cantidadLlantas, piezas);
        agregarPiezas(Piezas.Bateria, cantidadBateria, piezas);

        this.piezas = piezas;
        this.precio = CalcularPrecio(cantidadMotor, cantidadChasis, cantidadFrenos, cantidadCaja, cantidadFocos, cantidadLlantas, cantidadBateria);
        this.estado = estado;
    }

    private float CalcularPrecio(int cantidadMotor, int cantidadChasis, int cantidadFrenos, int cantidadCaja, int cantidadFocos, int cantidadLlantas, int cantidadBateria) {
        float precioMotor = 100.0f;
        float precioChasis = 150.0f;
        float precioFrenos = 50.0f;
        float precioCaja = 80.0f;
        float precioFocos = 30.0f;
        float precioLlantas = 60.0f;
        float precioBateria = 40.0f;

        return (cantidadMotor * precioMotor)
                + (cantidadChasis * precioChasis)
                + (cantidadFrenos * precioFrenos)
                + (cantidadCaja * precioCaja)
                + (cantidadFocos * precioFocos)
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
            // Prepare the callable statement
            CallableStatement cs = conexion.conectar().prepareCall(sql);

            // Set the parameter: the ID of the order to delete
            cs.setInt(1, this.id);

            // Execute the delete operation
            cs.execute();

            // Notify the user of success
            JOptionPane.showMessageDialog(null, "El registro se ha eliminado de manera exitosa");

        } catch (SQLException ex) {
            // Handle SQL errors
            JOptionPane.showMessageDialog(null, "Error al eliminar el registro");
            System.out.println("Error Consulta: " + ex.toString());
        } finally {
            // Disconnect from the database
            conexion.desconectar();
        }
    }

    public void editar() {
    
    Conexion conexion = new Conexion();

    // Consulta para actualizar la tabla orden_reparacion
    String sqlOrdenReparacion = "UPDATE orden_reparacion SET precio = ?, piezas = ?, estado = ? WHERE id = ?";
    
    // Consulta para actualizar la tabla inventario_piezas
    String sqlInventarioPiezas = "UPDATE inventario_piezas SET totalMotor = ?, totalChasis = ?, totalFrenos = ?, totalCaja = ?, totalFocos = ?, totalLlantas = ?, totalBateria = ? WHERE id = ?";

    try {
        // Preparar el statement para actualizar orden_reparacion
        PreparedStatement psOrdenReparacion = conexion.conectar().prepareStatement(sqlOrdenReparacion);

        // Establecer los parámetros para la tabla orden_reparacion
        psOrdenReparacion.setFloat(1, this.precio); // Actualizar precio
        psOrdenReparacion.setString(2, this.piezas.toString()); // Asegúrate de que piezas sea un objeto que pueda convertirse a String
        psOrdenReparacion.setString(3, this.estado.toString()); // Asegúrate de que estado sea un objeto que pueda convertirse a String
        psOrdenReparacion.setInt(4, this.id); // Especificar el ID del registro a actualizar en orden_reparacion

        // Ejecutar la actualización de orden_reparacion
        int rowsAffectedOrdenReparacion = psOrdenReparacion.executeUpdate();

        // Verificar cuántas filas fueron afectadas
        if (rowsAffectedOrdenReparacion > 0) {
            JOptionPane.showMessageDialog(null, "El registro de orden de reparación se ha editado de manera exitosa");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron registros de orden de reparación para actualizar.");
        }

        // Preparar el statement para actualizar inventario_piezas
        PreparedStatement psInventarioPiezas = conexion.conectar().prepareStatement(sqlInventarioPiezas);

        // Establecer los parámetros para la tabla inventario_piezas
        psInventarioPiezas.setInt(1, this.totalMotor); // Actualizar totalMotor
        psInventarioPiezas.setInt(2, this.chasis); // Actualizar totalChasis
        psInventarioPiezas.setInt(3, this.frenos); // Actualizar totalFrenos
        psInventarioPiezas.setInt(4, this.caja); // Actualizar totalCaja
        psInventarioPiezas.setInt(5, this.focos); // Actualizar totalFocos
        psInventarioPiezas.setInt(6, this.llantas); // Actualizar totalLlantas
        psInventarioPiezas.setInt(7, this.bateria); // Actualizar totalBateria
        psInventarioPiezas.setInt(8, this.id); // Especificar el ID de inventario_piezas a actualizar

        // Ejecutar la actualización de inventario_piezas
        int rowsAffectedInventarioPiezas = psInventarioPiezas.executeUpdate();

        // Verificar cuántas filas fueron afectadas
        if (rowsAffectedInventarioPiezas > 0) {
            JOptionPane.showMessageDialog(null, "El inventario de piezas se ha actualizado correctamente");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron registros de inventario de piezas para actualizar.");
        }

    } catch (SQLException ex) {
        // Manejar errores de SQL
        JOptionPane.showMessageDialog(null, "Error al editar el registro");
        System.out.println("Error Consulta: " + ex.toString());
    } finally {
        // Cerrar la conexión de manera segura
        conexion.desconectar();
    }
}


    public static DefaultTableModel consultar() {
        Conexion conexion = new Conexion();

        // Define the table model and column structure
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table cells non-editable
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
                datos[0] = String.valueOf(rs.getInt("id")); // Orden ID
                datos[1] = rs.getString("marca"); // Marca from vehiculo
                datos[2] = rs.getString("modelo"); // Modelo from vehiculo
                datos[3] = rs.getString("placa"); // Placa from vehiculo
                datos[4] = rs.getString("tipoVehiculo"); // Tipo from vehiculo (enum)
                datos[5] = rs.getString("piezas"); // Piezas as a String
                datos[6] = rs.getString("estado"); // Estado
                datos[7] = String.valueOf(rs.getFloat("precio")); // Precio
                datos[8] = rs.getString("descripcion_problema"); // Descripcion problema

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
