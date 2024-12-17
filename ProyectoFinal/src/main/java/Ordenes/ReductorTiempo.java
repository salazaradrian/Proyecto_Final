/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ordenes;

import java.io.*;
import java.net.Socket;
import Database.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JFrame;

/**
 *
 * @author rperez
 */
public class ReductorTiempo extends Thread {

    private int ordenId;
    private int tiempoRestante;
    private OrdenPieza ordenPieza;

    public ReductorTiempo(int ordenId, int tiempoRestante, OrdenPieza ordenPieza) {
        this.ordenId = ordenId;
        this.tiempoRestante = tiempoRestante;
        this.ordenPieza = ordenPieza;
    }

    @Override
    public void run() {
        final String HOST = "localhost";
        final int PUERTO = 5800;

        try (Socket socket = new Socket(HOST, PUERTO); PrintWriter salida = new PrintWriter(socket.getOutputStream(), true); BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Consultar el tiempo restante al iniciar
            salida.println("CONSULTAR," + ordenId);
            String respuesta = entrada.readLine();
            System.out.println("Servidor: " + respuesta);

            // Extraer tiempo restante del servidor
            if (respuesta.contains("Tiempo restante")) {
                String[] partes = respuesta.split(": ");
                tiempoRestante = Integer.parseInt(partes[1].split(" ")[0]);
            }

            // Hacer temporizador
            while (tiempoRestante > 0) {
                Thread.sleep(1000);
                tiempoRestante--;

                // Actualizar la base de datos local
                actualizarTiempoRestanteEnDB();

                // Enviar tiempo restante al servidor
                salida.println("ACTUALIZAR," + ordenId + "," + tiempoRestante);

                // Verificar si el tiempo ha terminado
                if (tiempoRestante == 0) {
                    System.out.println("Orden ID: " + ordenId + " completada. Actualizando inventario...");
                    ordenPieza.actualizarInventario();
                    salida.println("ORDEN_COMPLETADA," + ordenId);
                }
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error en reductor de tiempo: " + e.getMessage());
        }
    }

    // Actualiza el tiempo restante en la base de datos
    private void actualizarTiempoRestanteEnDB() {
        Conexion conexion = new Conexion();
        String sql = "UPDATE orden_de_piezas SET tiempo_restante = ? WHERE id = ?";

        try (java.sql.Connection con = conexion.conectar(); java.sql.PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, tiempoRestante);
            ps.setInt(2, ordenId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error actualizando tiempo_restante en la DB: " + e.getMessage());
        } finally {
            conexion.desconectar();
        }
    }
}
