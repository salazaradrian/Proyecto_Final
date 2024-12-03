/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectofinal;

import Database.Conexion;
import java.sql.Connection;

/**
 *
 * @author rperez
 */
public class Concurrente_Proyecto_Final {

    public static void main(String[] args) {
        // Usuario 1
        Conexion conexionAdmin = new Conexion("Admin1", "Admin");
        Connection con1 = conexionAdmin.getConnection();
        if (con1 != null) {
            System.out.println("Conexión establecida con Admin1");
            conexionAdmin.desconectar();
        }

        // Usuario 2
        Conexion conexionUsuario = new Conexion("Usuario", "Usuario");
        Connection con2 = conexionUsuario.getConnection();
        if (con2 != null) {
            System.out.println("Conexión establecida con Usuario1");
            conexionUsuario.desconectar();
        }

        // Usuario 3
        Conexion conexionInvitado = new Conexion("Mecanico", "Mecanico");
        Connection con3 = conexionInvitado.getConnection();
        if (con3 != null) {
            System.out.println("Conexión establecida con Invitado1");
            conexionInvitado.desconectar();
        
        
    }
}
    
}