/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ordenes;

import Database.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rperez
 */
public class OrdenPieza extends Orden {

    protected int id;
    protected int cantidadMotor;
    protected int cantidadChasis;
    protected int cantidadFrenos;
    protected int cantidadCaja;
    protected int cantidadFocos;
    protected int cantidadLlantas;
    protected int cantidadBateria;
    protected ArrayList<Piezas> piezas;
    protected int tiempoRestante;

    public OrdenPieza(int id) {
        super(Estado.EnProceso, 0.0f);
        this.id = id;
    }

    public OrdenPieza(int cantidadMotor, int cantidadChasis, int cantidadFrenos, int cantidadCaja, int cantidadFocos, int cantidadLlantas, int cantidadBateria, float precio) {
        super(Estado.EnProceso, 0.0f);
        this.cantidadMotor = cantidadMotor;
        this.cantidadChasis = cantidadChasis;
        this.cantidadFrenos = cantidadFrenos;
        this.cantidadCaja = cantidadCaja;
        this.cantidadFocos = cantidadFocos;
        this.cantidadLlantas = cantidadLlantas;
        this.cantidadBateria = cantidadBateria;
        this.precio = calcularPrecio();
        this.tiempoRestante = calcularTiempoTotal();

    }

    public OrdenPieza(int id, int cantidadMotor, int cantidadChasis, int cantidadFrenos, int cantidadCaja, int cantidadFocos, int cantidadLlantas, int cantidadBateria, float precio) {
        super(Estado.EnProceso, 0.0f);
        this.cantidadMotor = cantidadMotor;
        this.cantidadChasis = cantidadChasis;
        this.cantidadFrenos = cantidadFrenos;
        this.cantidadCaja = cantidadCaja;
        this.cantidadFocos = cantidadFocos;
        this.cantidadLlantas = cantidadLlantas;
        this.cantidadBateria = cantidadBateria;
        this.id = id;
        this.precio = calcularPrecio();
    }

    private float calcularPrecio() {

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

    private int calcularTiempoTotal() {
        return (cantidadMotor * 10) + (cantidadChasis * 15) + (cantidadFrenos * 5)
                + (cantidadCaja * 8) + (cantidadFocos * 2) + (cantidadLlantas * 7) + (cantidadBateria * 4);
    }

    public int agregar() {
        Conexion conexion = new Conexion();
        
        int generatedId = -1;

        String sql = "INSERT INTO orden_de_piezas (estado, precio, cantidadMotor, cantidadChasis, cantidadFrenos, "
                + "cantidadCaja, cantidadFocos, cantidadLlantas, cantidadBateria,tiempo_restante) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            CallableStatement cs = conexion.conectar().prepareCall(sql);

            cs.setString(1, this.estado.toString());
            cs.setFloat(2, this.precio);
            cs.setInt(3, this.cantidadMotor);
            cs.setInt(4, this.cantidadChasis);
            cs.setInt(5, this.cantidadFrenos);
            cs.setInt(6, this.cantidadCaja);
            cs.setInt(7, this.cantidadFocos);
            cs.setInt(8, this.cantidadLlantas);
            cs.setInt(9, this.cantidadBateria);
            cs.setInt(10, this.tiempoRestante);

            cs.executeUpdate();

            // Retrieve the generated keys (ID)
            ResultSet rs = cs.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1); // Get the first column, which is the ID
                this.id = generatedId; // Set the ID for the current object
                JOptionPane.showMessageDialog(null, "Vehículo agregado exitosamente con ID: " + generatedId);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar el registro");
            System.out.println("Error Consulta: " + ex.toString());
        } finally {
            conexion.desconectar();
        }
        
        return generatedId;
    }

    public static DefaultTableModel consultar() {
        Conexion conexion = new Conexion();
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        modelo.addColumn("ID");
        modelo.addColumn("Estado");
        modelo.addColumn("Precio");
        modelo.addColumn("Motor");
        modelo.addColumn("Chasis");
        modelo.addColumn("Frenos");
        modelo.addColumn("Caja");
        modelo.addColumn("Focos");
        modelo.addColumn("Llantas");
        modelo.addColumn("Bateria");
        modelo.addColumn("Tiempo Restante");

        String datos[] = new String[11];
        try {
            Statement stmt = conexion.conectar().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM orden_de_piezas");

            while (rs.next()) {
                datos[0] = String.valueOf(rs.getInt("id"));
                datos[1] = rs.getString("estado");
                datos[2] = String.valueOf(rs.getFloat("precio"));
                datos[3] = String.valueOf(rs.getInt("cantidadMotor"));
                datos[4] = String.valueOf(rs.getInt("cantidadChasis"));
                datos[5] = String.valueOf(rs.getInt("cantidadFrenos"));
                datos[6] = String.valueOf(rs.getInt("cantidadCaja"));
                datos[7] = String.valueOf(rs.getInt("cantidadFocos"));
                datos[8] = String.valueOf(rs.getInt("cantidadLlantas"));
                datos[9] = String.valueOf(rs.getInt("cantidadBateria"));
                datos[10] = String.valueOf(rs.getInt("tiempo_restante"));
                

                modelo.addRow(datos);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al consultar los registros");
            System.out.println("Error Consulta: " + ex.toString());
        } finally {
            conexion.desconectar();
        }

        return modelo;
    }

    public void editar() {
        Conexion conexion = new Conexion();

        String sql = "UPDATE orden_de_piezas SET estado = ?, precio = ?, cantidadMotor = ?, cantidadChasis = ?, "
                + "cantidadFrenos = ?, cantidadCaja = ?, cantidadFocos = ?, cantidadLlantas = ?, cantidadBateria = ? "
                + "WHERE id = ?";

        try {
            CallableStatement cs = conexion.conectar().prepareCall(sql);

            cs.setString(1, this.estado.toString());
            cs.setFloat(2, this.precio);
            cs.setInt(3, this.cantidadMotor);
            cs.setInt(4, this.cantidadChasis);
            cs.setInt(5, this.cantidadFrenos);
            cs.setInt(6, this.cantidadCaja);
            cs.setInt(7, this.cantidadFocos);
            cs.setInt(8, this.cantidadLlantas);
            cs.setInt(9, this.cantidadBateria);
            cs.setInt(10, this.id);

            System.out.println("Query: " + sql);
            System.out.println("Parameters:");
            System.out.println("estado: " + this.estado.toString());
            System.out.println("precio: " + this.precio);
            System.out.println("cantidadMotor: " + this.cantidadMotor);
            System.out.println("cantidadChasis: " + this.cantidadChasis);
            System.out.println("cantidadFrenos: " + this.cantidadFrenos);
            System.out.println("cantidadCaja: " + this.cantidadCaja);
            System.out.println("cantidadFocos: " + this.cantidadFocos);
            System.out.println("cantidadLlantas: " + this.cantidadLlantas);
            System.out.println("cantidadBateria: " + this.cantidadBateria);
            System.out.println("id: " + this.id);

            cs.execute();

            JOptionPane.showMessageDialog(null, "El registro se ha editado de manera exitosa");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al editar el registro");
            System.out.println("Error Consulta: " + ex.toString());
        } finally {
            conexion.desconectar();
        }
    }

    public void eliminar() {
        Conexion conexion = new Conexion();

        String sql = "DELETE FROM orden_de_piezas WHERE id = ?";

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

    public void actualizarInventario() {
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
                int currentFocos = rs.getInt("totalFocos");
                int currentLlantas = rs.getInt("totalLlantas");
                int currentBateria = rs.getInt("totalBateria");

                int updatedMotor = currentMotor + this.cantidadMotor;
                int updatedChasis = currentChasis + this.cantidadChasis;
                int updatedFrenos = currentFrenos + this.cantidadFrenos;
                int updatedCaja = currentCaja + this.cantidadCaja;
                int updatedFocos = currentFocos + this.cantidadFocos;
                int updatedLlantas = currentLlantas + this.cantidadLlantas;
                int updatedBateria = currentBateria + this.cantidadBateria;

                String sqlUpdate = "UPDATE inventario_piezas SET totalMotor = ?, totalChasis = ?, totalFrenos = ?, "
                        + "totalCaja = ?, totalFocos = ?, totalLlantas = ?, totalBateria = ? WHERE id = 1";

                PreparedStatement psUpdate = conexion.conectar().prepareStatement(sqlUpdate);

                psUpdate.setInt(1, updatedMotor);
                psUpdate.setInt(2, updatedChasis);
                psUpdate.setInt(3, updatedFrenos);
                psUpdate.setInt(4, updatedCaja);
                psUpdate.setInt(5, updatedFocos);
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

    public static DefaultTableModel consultarInventario() {
        Conexion conexion = new Conexion();
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        modelo.addColumn("Motor");
        modelo.addColumn("Chasis");
        modelo.addColumn("Frenos");
        modelo.addColumn("Caja");
        modelo.addColumn("Focos");
        modelo.addColumn("Llantas");
        modelo.addColumn("Bateria");

        String datos[] = new String[7];
        try {
            Statement stmt = conexion.conectar().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM inventario_piezas");

            while (rs.next()) {
                datos[0] = String.valueOf(rs.getInt("totalMotor"));
                datos[1] = String.valueOf(rs.getInt("totalChasis"));
                datos[2] = String.valueOf(rs.getInt("totalFrenos"));
                datos[3] = String.valueOf(rs.getInt("totalCaja"));
                datos[4] = String.valueOf(rs.getInt("totalFocos"));
                datos[5] = String.valueOf(rs.getInt("totalLlantas"));
                datos[6] = String.valueOf(rs.getInt("totalBateria"));

                modelo.addRow(datos);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al consultar los registros");
            System.out.println("Error Consulta: " + ex.toString());
        } finally {
            conexion.desconectar();
        }

        return modelo;
    }

    public int getCodigo() {
        return id;
    }

    public int getCantidadMotor() {
        return cantidadMotor;
    }

    public int getCandtidadChasis() {
        return cantidadChasis;
    }

    public int getCantidadFrenos() {
        return cantidadFrenos;
    }

    public int getCantidadcaja() {
        return cantidadCaja;
    }

    public int getCantidadFocos() {
        return cantidadFocos;
    }

    public int getCantidadLlantas() {
        return cantidadLlantas;
    }

    public int getCantidadBateria() {
        return cantidadBateria;
    }

    public ArrayList<Piezas> getPiezas() {
        return piezas;
    }

    public void setCodigo(int codigo) {
        this.id = codigo;
    }

    public void setCantidadMotor(int cantidadMotor) {
        this.cantidadMotor = cantidadMotor;
    }

    public void setCandtidadChasis(int candtidadChasis) {
        this.cantidadChasis = candtidadChasis;
    }

    public void setCantidadFrenos(int cantidadFrenos) {
        this.cantidadFrenos = cantidadFrenos;
    }

    public void setCantidadcaja(int cantidadcaja) {
        this.cantidadCaja = cantidadcaja;
    }

    public void setCantidadFocos(int cantidadFocos) {
        this.cantidadFocos = cantidadFocos;
    }

    public void setCantidadLlantas(int cantidadLlantas) {
        this.cantidadLlantas = cantidadLlantas;
    }

    public void setCantidadBateria(int cantidadBateria) {
        this.cantidadBateria = cantidadBateria;
    }

    public void setPiezas(ArrayList<Piezas> piezas) {
        this.piezas = piezas;
    }

    public int getId() {
        return id;
    }

    public int getCantidadChasis() {
        return cantidadChasis;
    }

    public int getCantidadCaja() {
        return cantidadCaja;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCantidadChasis(int cantidadChasis) {
        this.cantidadChasis = cantidadChasis;
    }

    public void setCantidadCaja(int cantidadCaja) {
        this.cantidadCaja = cantidadCaja;
    }

    public void setTiempoRestante(int tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }
    
    

}
