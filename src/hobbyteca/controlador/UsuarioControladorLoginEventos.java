/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 Necesitamos metodos para cuando inicie el programa se conecten los drivers UsuarioDAO.devolverUsuarioDAO.conectarDrivers 
 y también se conecte a la bbdd usuariodao.devolverusuariodao.realizarConexion.
 */
package hobbyteca.controlador;


import hobbyteca.vista.Login;
import java.awt.Color;
import javax.swing.JOptionPane;


/**
 *
 * @author jesus
 */
public class UsuarioControladorLoginEventos {

    /**/
    private final Login vistaLogin;
    
    /**
     *
     * @param vistaLogin
     */
    public UsuarioControladorLoginEventos(Login vistaLogin) {
        this.vistaLogin = vistaLogin;
    }

    /**
     *
     * @param evt
     */
    public void MostrarOcultarContraseña(java.awt.event.ActionEvent evt) {
        if (vistaLogin.getjCheckBoxVerPassword().isSelected()) {
            vistaLogin.getjPasswordFieldPassword().setEchoChar((char) 0);
        } else {
            if (vistaLogin.getFlagPassword()== true) {
                vistaLogin.getjPasswordFieldPassword().setEchoChar('*');
            }
         
        }

    }

    /**
     *
     * @param evt
     * @param seleccion
     */
    public void VaciarCampos(java.awt.event.FocusEvent evt, int seleccion) {
        switch (seleccion) {
            //Usuario
            case 0:
                if (vistaLogin.getFlagNombre() == false) {
                    vistaLogin.getjTextFieldUsuario().setText("");
                    vistaLogin.getjTextFieldUsuario().setForeground(Color.black);
                    vistaLogin.setFlagNombre(true);
                }
                break;
            

            //Contraseña    
            case 1:
                if (vistaLogin.getFlagPassword()== false) {
                    vistaLogin.getjPasswordFieldPassword().setText("");
                    vistaLogin.getjPasswordFieldPassword().setForeground(Color.black);
                    vistaLogin.getjPasswordFieldPassword().setEchoChar('*');
                    vistaLogin.setFlagPassword(true);
                }
                if (vistaLogin.getjCheckBoxVerPassword().isSelected()) {
                    vistaLogin.getjPasswordFieldPassword().setEchoChar((char) 0);
                } else {
                    vistaLogin.getjPasswordFieldPassword().setEchoChar('*');
                }
                break;
                  default:
                 JOptionPane.showMessageDialog(vistaLogin, "Si ves este mensaje es que has tocado el codigo por favor dejalo como estaba y no vuevas edites nada","Error", JOptionPane.ERROR_MESSAGE);
                 break;
        }
         }

    /**
     *
     * @param evt
     * @param seleccion
     */
    public void RellenarCampos(java.awt.event.FocusEvent evt, int seleccion) {
        switch (seleccion) {
            //Usuario
            case 0:
                 if (vistaLogin.getjTextFieldUsuario().getText().isEmpty()) {
            vistaLogin.getjTextFieldUsuario().setText("Usuario");
            vistaLogin.getjTextFieldUsuario().setForeground(Color.GRAY);
            vistaLogin.setFlagNombre(false);
        }
                break;
            

            //Contraseña    
            case 1:
              String pass = new String(vistaLogin.getjPasswordFieldPassword().getPassword());
        if (pass.equals("")) {
            vistaLogin.getjPasswordFieldPassword().setEchoChar((char) 0);
            vistaLogin.getjPasswordFieldPassword().setText("Password");
            vistaLogin.getjPasswordFieldPassword().setForeground(Color.GRAY);
            vistaLogin.setFlagPassword(false);
        }
             
                break;
                  default:
                 JOptionPane.showMessageDialog(vistaLogin, "Si ves este mensaje es que has tocado el codigo por favor dejalo como estaba y no vuevas edites nada","Error", JOptionPane.ERROR_MESSAGE);
                 break;
        }
         }

 
    
        
    
}
