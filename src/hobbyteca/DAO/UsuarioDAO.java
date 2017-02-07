/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;



/**
 *
 * @author xRafa
 */
public class UsuarioDAO extends RepositorioEstableceConexion {

    /**
     *
     * @param nombreUsuario
     * @return
     * @throws SQLException Consultamos que haya existencias sobre el usuario,
     * sino existe devuelve false, si existe true.
     */
    /*Singleton*/
    private static UsuarioDAO UsuarioDAO = null;

    /**
     *
     * @return
     */
    public static UsuarioDAO devolverUsuarioDAO() {

        if (UsuarioDAO != null) {
            return UsuarioDAO;
        } else {
            UsuarioDAO = new UsuarioDAO();
            return UsuarioDAO;
        }
    }
    /*Singleton*/

    /*Comprueba Usuario Existente */

    /**
     *
     * @param nombreUsuario
     * @return
     * @throws SQLException
     */

    public boolean compruebaUsuario(String nombreUsuario) throws SQLException {
        boolean existeUsuario = false;
        ResultSet compruebaUsuario;
        int recorreResultSet = 0;
        compruebaUsuario = ejecutaConsulta("SELECT nombre_usuario FROM usuario WHERE nombre_usuario LIKE" + "'" + nombreUsuario + "'");
        while (compruebaUsuario.next()) {
            recorreResultSet++;
        }
        if (recorreResultSet > 0) {
            existeUsuario = true;
        }
       
        cerrarResulSet(compruebaUsuario);
        return existeUsuario;
    }

    /**
     * @param nombreUsuario
     * @param password
     * @return
     * @throws SQLException comparamos el nombre de user y la contraseña enviada
     * por el usuario con las tablas, si existe 1 consulta devolverá true, sino
     * devolverá false. sino hay coincidencias devuelve false, si la hay, true.
     */
    public boolean comparaPasswordConUsuario(String nombreUsuario, String password) throws SQLException {
        boolean coincideUsuarioPassword = false;
        ResultSet compruebaExistencia;
        int recorreResultSet = 0;
        compruebaExistencia = ejecutaConsulta("SELECT nombre_usuario, password FROM usuario WHERE nombre_usuario LIKE" + "'" + nombreUsuario + "'" + "AND password LIKE" + "'" + password + "'");
        while (compruebaExistencia.next()) {
            recorreResultSet++;
        }
        if (recorreResultSet == 1) {
            coincideUsuarioPassword = true;
        }
        cerrarResulSet(compruebaExistencia);
        return coincideUsuarioPassword;
    }

    /**
     *
     * @param email
     * @return
     * @throws SQLException
     */
    public boolean compruebaEmail(String email) throws SQLException {
        boolean existeEmail = false;
        ResultSet compruebaEmail;
        int recorreResultSet = 0;
        compruebaEmail = ejecutaConsulta("SELECT email FROM usuario WHERE email LIKE" + "'" + email + "'");
        while (compruebaEmail.next()) {
            recorreResultSet++;
        }
        if (recorreResultSet > 0) {
            existeEmail = true;
        }
        cerrarResulSet(compruebaEmail);
        return existeEmail;
    }

    /**
     *
     * @param nombre
     * @param nombreUsuario
     * @param password
     * @param email
     * @param fecha_nac
     * @return
     * @throws SQLException
     */
    public boolean guardarDatos(String nombre, String nombreUsuario, String password, String email, String fecha_nac) throws SQLException {
        String inserccion = "Insert into usuario VALUES(" + "'" + nombre + "'," + "'" + nombreUsuario + "'," + "'" + password + "'," + "'" + email + "'," + "'" + fecha_nac + "'," + "'0'" + ")";
        boolean resul = insertarOActualizarCampos(inserccion);
        return resul;

    }

}
