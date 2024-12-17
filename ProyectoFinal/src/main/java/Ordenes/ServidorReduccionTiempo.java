/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ordenes;

/**
 *
 * @author josed
 */

import java.io.*;
import java.net.*;
import java.util.concurrent.ConcurrentHashMap;

public class ServidorReduccionTiempo {

    // Mapa para almacenar tiempos restantes por orden (ordenId, tiempoRestante)
    private static final ConcurrentHashMap<Integer, Integer> tiemposRestantes = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        final int PUERTO = 5800;
        System.out.println("Servidor ReducidorTiempo iniciado en el puerto " + PUERTO);

        // Iniciar hilo que decrementa los tiempos
        iniciarTemporizador();

        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress());

                // Manejar cada cliente con un nuevo hilo
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        manejarCliente(cliente);
                    }
                }).start();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }

    private static void manejarCliente(Socket cliente) {
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream())); PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true)) {

            String input;
            while ((input = entrada.readLine()) != null) {
                String[] datos = input.split(",");
                String comando = datos[0];

                if (comando.equalsIgnoreCase("ACTUALIZAR")) {
                    int ordenId = Integer.parseInt(datos[1]);
                    int tiempoRestante = Integer.parseInt(datos[2]);
                    tiemposRestantes.put(ordenId, tiempoRestante);
                    salida.println("Orden ID: " + ordenId + " actualizada a " + tiempoRestante + " segundos.");

                } else if (comando.equalsIgnoreCase("CONSULTAR")) {
                    int ordenId = Integer.parseInt(datos[1]);
                    int tiempoRestante = tiemposRestantes.getOrDefault(ordenId, -1);
                    salida.println("Orden ID: " + ordenId + " -> Tiempo restante: " + tiempoRestante + " segundos.");
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al manejar cliente: " + e.getMessage());
        } finally {
            try {
                cliente.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
    }

    private static void iniciarTemporizador() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000); // Esperar 1 segundo
                        for (Integer ordenId : tiemposRestantes.keySet()) {
                            int tiempo = tiemposRestantes.get(ordenId);
                            if (tiempo > 0) {
                                tiemposRestantes.put(ordenId, tiempo - 1);
                                System.out.println("Orden ID: " + ordenId + ", Tiempo restante: " + (tiempo - 1));
                            } else {
                                System.out.println("Orden ID: " + ordenId + " completada.");
                                tiemposRestantes.remove(ordenId);
                            }
                        }
                    } catch (InterruptedException e) {
                        System.err.println("Error en temporizador: " + e.getMessage());
                    }
                }
            }
        }).start();
    }
}
