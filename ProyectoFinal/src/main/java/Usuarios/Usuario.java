package Usuarios;

import Database.Conexion;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public abstract class Usuario {
    protected int idUsuario;
    protected String nombre;
    protected String usuario;
    protected String contrasena;
    protected Rol rol;

    // Crear Usuario
    public Usuario(String nombre, String usuario, String contrasena, Rol rol) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    // Modificar Usuario
    public Usuario(int idUsuario, String nombre, String usuario, String contrasena, Rol rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.rol = rol;
    }
    
    // READ
    public static DefaultTableModel consultar() {

        Conexion conexion = new Conexion();
        DefaultTableModel modelo = new DefaultTableModel(){
            
            // Hacer que la tabla no sea editable
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            } 
        };

        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Usuario");
        modelo.addColumn("Contraseña");
        modelo.addColumn("Rol");

        String datos[] = new String[5];

        try {
            Statement stmt = conexion.conectar().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");

            while (rs.next()) {
                datos[0] = String.valueOf(rs.getInt("codigo"));
                datos[1] = rs.getString("nombre");
                datos[2] = rs.getString("usuario");
                datos[3] = rs.getString("contrasena");
                datos[4] = rs.getString("rol");

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
    
    // CREATE
    public void agregar() {
        Conexion conexion = new Conexion();

        String sql = "INSERT INTO usuario (usuario, nombre, contrasena, rol) VALUES (?, ?, ?, ?)";

        try {
            CallableStatement cs = conexion.conectar().prepareCall(sql);

            cs.setString(1, this.nombre);
            cs.setString(2, this.usuario);
            cs.setString(3, this.contrasena);
            cs.setString(4, this.rol.toString());

            cs.execute();

            JOptionPane.showMessageDialog(null, "El resgistro se a guardado de manera exitosa");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar el registros");
            System.out.println("Error Consulta: " + ex.toString());
        } finally {
            conexion.desconectar();
        }
    }
    
    // UPDATE
    public void editar() {
        Conexion conexion = new Conexion();

        String sql = "UPDATE usuario SET usuario = ?, nombre = ?, contrasena = ?, rol = ? WHERE idUsuario = ?";

        try {
            CallableStatement cs = conexion.conectar().prepareCall(sql);

            cs.setString(1, this.nombre);
            cs.setString(2, this.usuario);
            cs.setString(3, this.contrasena);
            cs.setString(4, this.rol.toString());
            cs.setInt(5, this.idUsuario);

            cs.execute();

            JOptionPane.showMessageDialog(null, "El resgistro se a editado de manera exitosa");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al editar el registros");
            System.out.println("Error Consulta: " + ex.toString());
        } finally {
            conexion.desconectar();
        }
    }
    
    // DELETE
    public void eliminar() {
        Conexion conexion = new Conexion();

        String sql = "DELETE FROM usuario WHERE idUsuario = ?";
        
        try {
            CallableStatement cs = conexion.conectar().prepareCall(sql);

            cs.setInt(1, this.idUsuario);

            cs.execute();

            JOptionPane.showMessageDialog(null, "El resgistro se a eliminado de manera exitosa");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el registros");
            System.out.println("Error Consulta: " + ex.toString());
        } finally {
            conexion.desconectar();
        }
    }
    
    public Usuario(int idUsuario){
        this.idUsuario = idUsuario;
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public Rol getRol() {
        return rol;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
         
    public abstract void IniciarSesion();
    public abstract void CerrarSesion();
    public abstract void ConsultarPerfil();
    public abstract void ActualizarPerfil();
}
