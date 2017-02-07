/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.DAO;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author xRafa
 */
public class RepositorioEstableceConexion {

    private static Connection conexion;
    /*Conexión a la base de datos.*/
    /*
     Nombre de la base de datos: hobbyteca_bbdd
     puerto: 3306
     */
     private static RepositorioEstableceConexion repositorioConexion = null;

    /**
     *
     * @return
     */
    public static RepositorioEstableceConexion establecerRepositorioConexion() {

        if (repositorioConexion != null) {
            return repositorioConexion;
        } else {
            repositorioConexion = new RepositorioEstableceConexion();
            return repositorioConexion;
        }
    }

    /**
     *
     * @throws ClassNotFoundException
     */
    public void conectarDrivers() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
    }

    /**
     *
     * @return
     */
    public Connection realizarConexion() {
        try{
        if (conexion == null || conexion.isClosed()) {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/hobbyteca_bbdd", "root", "");
        }
        }catch(SQLException | NullPointerException e){
          JOptionPane.showMessageDialog(null, "No Conecta con la base de datos","Error", JOptionPane.ERROR_MESSAGE);
        }
        return conexion;
    }

    /**
     *
     * @param consulta
     * @return
     * @throws SQLException
     */
    public ResultSet ejecutaConsulta(String consulta) throws SQLException {
        /*statement = declaracion*/
     
          Statement stat = realizarConexion().createStatement();
        ResultSet res = stat.executeQuery(consulta);       
        return res;   
    
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public int cerrarConexion() throws SQLException {
        realizarConexion().close();
        return 1;
    }

    /**
     *
     * @param insertInto
     * @return
     * @throws SQLException
     */
    public boolean insertarOActualizarCampos(String insertInto) throws SQLException {
        boolean devuelveInsertarCamposCorrectos = false;
        Statement stat = realizarConexion().createStatement();
        String inserccion = insertInto;
        //resultado guarda el numero de columnas numerico del executaUpdate del statement.
        int resultado = stat.executeUpdate(inserccion);

        if (resultado > 0) {
            devuelveInsertarCamposCorrectos = true;
        }
        return devuelveInsertarCamposCorrectos;
    }

    /**
     *
     * @param updateCampo
     * @return
     * @throws SQLException
     */
    public boolean actualizarCampos(String updateCampo) throws SQLException {
        boolean devuelveActualizarCamposCorrectos = false;
        Statement stat = realizarConexion().createStatement();
        String inserccion = updateCampo;
        //resultado guarda el numero de columnas numerico del executaUpdate del statement.
        int resultado = stat.executeUpdate(inserccion);
        if (resultado > 0) {
            devuelveActualizarCamposCorrectos = true;
        }
        return devuelveActualizarCamposCorrectos;
    }

    /**
     *
     * @param deleteCampo
     * @return
     * @throws SQLException
     */
    public boolean borrarCampos(String deleteCampo) throws SQLException {
        boolean devuelveBorrarCamposCorrectos = false;
        Statement stat = realizarConexion().createStatement();
        String inserccion = deleteCampo;
        //resultado guarda el numero de columnas numerico del executaUpdate del statement.
        int resultado = stat.executeUpdate(inserccion);
        if (resultado > 0) {
            devuelveBorrarCamposCorrectos = true;
        }
        stat.close();
        return devuelveBorrarCamposCorrectos;
    }
    
    /**
     *
     * @param rs
     * @throws SQLException
     */
    public void cerrarResulSet(ResultSet rs) throws SQLException{ //solucionar el null
        rs.getStatement().close();
        rs.close();
    }
    
}


//Cada vez que abramos una ventana se abrirá el statemente, y cuando cerremos la ventana se cerrará y abrirá la ventana nueva con el statement. 