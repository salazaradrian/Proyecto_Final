
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
private String url;
    private String db;
    private String driver;
    private Connection connection;

    public Conexion(String user, String password) {
        this.url = "jdbc:mysql://localhost:3306/"; 
        this.db = "Proyecto_SGV"; 
        this.driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
        try {
            
            Class.forName(driver);
            
            connection = DriverManager.getConnection(url + db, user, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al cargar el driver: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getMessage());
        }
    }

    // Obtener la conexion
    public Connection getConnection() {
        return connection;
    }

  
    public void desconectar() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión finalizada");
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }
}

