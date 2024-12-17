/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ordenes;

import Database.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JFrame;

/**
 *
 * @author rperez
 */
public class ReducidorTiempo extends Thread {

    private int ordenId;
    private int tiempoRestante;
    private OrdenPieza ordenPieza; // Reference to OrdenPieza
    private JFrame frame;

    public ReducidorTiempo(int ordenId, int tiempoRestante, OrdenPieza ordenPieza) {
        this.ordenId = ordenId;
        this.tiempoRestante = tiempoRestante;
        this.ordenPieza = ordenPieza; // Pass the OrdenPieza object
        this.frame = frame;
    }

    @Override
    public void run() {
        while (tiempoRestante > 0) {
            try {
                Thread.sleep(1000); // Wait 5 seconds
                tiempoRestante -= 1;

                if (tiempoRestante <= 0) {
                    tiempoRestante = 0; // Ensure it stops at 0
                }

                // Update remaining time in database
                actualizarTiempoRestanteEnDB();

                System.out.println("Orden ID: " + ordenId + ", Tiempo restante: " + tiempoRestante + "s");

            } catch (InterruptedException e) {
                System.out.println("Error in timer: " + e.getMessage());
            }
        }
        
        // Order completed - Update inventory
        ordenPieza.actualizarInventario();
        System.out.println("Orden ID: " + ordenId + " completada. Inventario actualizado.");
    }

    private void actualizarTiempoRestanteEnDB() {
        Conexion conexion = new Conexion();
        String sql = "UPDATE orden_de_piezas SET tiempo_restante = ? WHERE id = ?";

        try (Connection con = conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, tiempoRestante);
            ps.setInt(2, ordenId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error updating tiempo_restante: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
    }

}
