/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 Necesitamos metodos para cuando inicie el programa se conecten los drivers UsuarioDAO.devolverUsuarioDAO.conectarDrivers 
 y también se conecte a la bbdd usuariodao.devolverusuariodao.realizarConexion.
 */
package hobbyteca.controlador;

import hobbyteca.DAO.UsuarioDAO;
import hobbyteca.vista.Login;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author jesus
 */
public class UsuarioControladorLogin {

    /**/
    private Login vistaLogin;
    private UsuarioControladorRegistro usuarioControladorRegistro;
    private Validadores validadores;
    private ControladorVentanaPrincipal ventanaPrincipalControlador;
    /**
     *
     * @param vistaLogin
     */
    public UsuarioControladorLogin(Login vistaLogin) {
        this.vistaLogin = vistaLogin;
    }
    
    /**
     *
     * @param usuarioControladorRegistro
     */
    public UsuarioControladorLogin(UsuarioControladorRegistro usuarioControladorRegistro) {
        this.usuarioControladorRegistro = usuarioControladorRegistro;
    }

    
       public UsuarioControladorLogin(ControladorVentanaPrincipal ventanaPrincipalControlador) {
        this.ventanaPrincipalControlador = ventanaPrincipalControlador;
    }
    //Métodos de Login

    /**
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void compruebaUsuarioLogin() throws SQLException, ClassNotFoundException {
        String nombreUsuario;
        boolean compruebaUsuario;
        nombreUsuario = vistaLogin.getjTextFieldUsuario().getText();
        //creo que esto sobra
        compruebaUsuario = UsuarioDAO.devolverUsuarioDAO().compruebaUsuario(nombreUsuario);
        if (!compruebaUsuario) {
            //enviar mensaje el usuario no existe.
            JOptionPane.showMessageDialog(vistaLogin, "El usuario no existe", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            //entrar en la ventana principal.
        }
    }

    /**
     *
     * @throws SQLException
     */
    public void compruebaUsuarioconPassword() throws SQLException {
        Validadores mivalidador = new Validadores();
        String nombreUsuario;
        String passwordEncriptada;
        String password=new String(vistaLogin.getjPasswordFieldPassword().getPassword());
        boolean compruebaUsuarioConPassword;
        nombreUsuario = vistaLogin.getjTextFieldUsuario().getText();
        passwordEncriptada = mivalidador.encriptaPassword(password);
        compruebaUsuarioConPassword = UsuarioDAO.devolverUsuarioDAO().comparaPasswordConUsuario(nombreUsuario, passwordEncriptada);
        if (!compruebaUsuarioConPassword) {
            JOptionPane.showMessageDialog(vistaLogin, "La contraseña o el nombre de usuario es erróneo", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(vistaLogin, "Bienvenido", "Acierto", JOptionPane.INFORMATION_MESSAGE);
            AbrirVentanaPrincipal(nombreUsuario);

        }
    }

    /**
     *
     */
    public void AbrirVistaRegistro() {
        usuarioControladorRegistro = new UsuarioControladorRegistro(null);
        usuarioControladorRegistro.AbrirRegistro();
        
    }
    
    /**
     * 
     * @param nombreUsuario 
     */
    public void AbrirVentanaPrincipal(String nombreUsuario) throws SQLException{
         ventanaPrincipalControlador = new ControladorVentanaPrincipal(null);
         ventanaPrincipalControlador.recogeNombreUsuarioLogueado(nombreUsuario);
         ventanaPrincipalControlador.abrirVetananaPrincipal();
         cerrarLogin();
    }
    /**
     * 
     */
    public void ocultarLogin(){
    vistaLogin.setVisible(true);
    }
    /**
     * 
     */
    public void cerrarLogin(){
        vistaLogin.dispose();
    }
    /**
     * 
     */
    public void mostrarLogin(){
       vistaLogin.setVisible(true);
    }
    /**
     * 
     * @throws SQLException 
     */
    public void AbrirLogin() throws SQLException{
    Login nuevoLogin= new Login();
    nuevoLogin.setVisible(true);
    }
}
