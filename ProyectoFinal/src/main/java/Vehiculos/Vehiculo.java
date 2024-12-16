/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos;

import Database.Conexion;
import Usuarios.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author rperez
 */
public class Vehiculo {

    protected String marca;
    protected String modelo;
    protected String placa;
    protected Tipo tipo;
    protected int id;

    public Vehiculo(String marca, String modelo, String placa, Tipo tipo) {
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.tipo = tipo;
    }

  

    public int agregar() {
        String sql = "INSERT INTO vehiculo (marca, modelo, placa, tipoVehiculo) VALUES (?, ?, ?, ?)";
        Conexion conexion = new Conexion();
        int generatedId = -1; // Default value for failure

        try {
            PreparedStatement ps = conexion.conectar().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.marca);
            ps.setString(2, this.modelo);
            ps.setString(3, this.placa);
            ps.setString(4, this.tipo.toString()); // Convert enum to String

            ps.executeUpdate();

            // Retrieve the generated keys (ID)
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1); // Get the first column, which is the ID
                this.id = generatedId; // Set the ID for the current object
                JOptionPane.showMessageDialog(null, "Vehículo agregado exitosamente con ID: " + generatedId);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar vehículo.");
            System.out.println("Error: " + ex);
        } finally {
            conexion.desconectar();
        }

        return generatedId; // Return the generated ID
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public int getId() {
        return id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

}
