package hobbyteca.controlador;
import hobbyteca.DAO.RepositorioEstableceConexion;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xRafa
 */
public class ConexionControlador {
    

    //Este controlador es el encargado de establecer conexión al iniciar el programa.
    //Cada vez que cambiemos de ventana se abrirán y cerrarán los Statement.
    //También se encargará de Cerrar la conexión al cerrar al programa.

    /**
     *
     * @throws SQLException
     */
    
    public void estableceConexion() throws SQLException{
        RepositorioEstableceConexion.establecerRepositorioConexion().realizarConexion();
    }
    
    /**
     *
     * @return
     * @throws SQLException
     */
    public int cerrarConexion() throws SQLException {
        RepositorioEstableceConexion.establecerRepositorioConexion().cerrarConexion();
        return 1;
    }
    
    /**
     *
     * @throws ClassNotFoundException
     */
    public void conectarDrivers() throws ClassNotFoundException{
        RepositorioEstableceConexion.establecerRepositorioConexion().conectarDrivers();
    }
}
