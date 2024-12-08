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

    public OrdenPieza(int id) {
        super(Estado.Pendiente, 0.0f);
        this.id = id;
    }

    public OrdenPieza(int cantidadMotor, int cantidadChasis, int cantidadFrenos, int cantidadCaja, int cantidadFocos, int cantidadLlantas, int cantidadBateria, float precio) {
        super(Estado.Pendiente, 0.0f);
        this.cantidadMotor = cantidadMotor;
        this.cantidadChasis = cantidadChasis;
        this.cantidadFrenos = cantidadFrenos;
        this.cantidadCaja = cantidadCaja;
        this.cantidadFocos = cantidadFocos;
        this.cantidadLlantas = cantidadLlantas;
        this.cantidadBateria = cantidadBateria;
    }

    public OrdenPieza(int id, int cantidadMotor, int cantidadChasis, int cantidadFrenos, int cantidadCaja, int cantidadFocos, int cantidadLlantas, int cantidadBateria, float precio) {
        super(Estado.Pendiente, 0.0f);
        this.cantidadMotor = cantidadMotor;
        this.cantidadChasis = cantidadChasis;
        this.cantidadFrenos = cantidadFrenos;
        this.cantidadCaja = cantidadCaja;
        this.cantidadFocos = cantidadFocos;
        this.cantidadLlantas = cantidadLlantas;
        this.cantidadBateria = cantidadBateria;
        this.id = id;
    }

    
    public void agregar() {
        Conexion conexion = new Conexion();

        String sql = "INSERT INTO orden_de_piezas (estado, precio, cantidadMotor, cantidadChasis, cantidadFrenos, "
                + "cantidadCaja, cantidadFocos, cantidadLlantas, cantidadBateria) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

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

            cs.execute();

            JOptionPane.showMessageDialog(null, "El registro se ha guardado de manera exitosa");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar el registro");
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

        String datos[] = new String[10];
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

}
