/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.controlador;

import hobbyteca.DAO.UsuarioDAO;
import java.awt.Color;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import hobbyteca.vista.Registro;

/**
 *
 * @author xRafa
 */
public class UsuarioControladorRegistro {

    private Registro vistaRegistro;
    private Validadores validadores;

    /**
     *
     * @param vistaRegistro
     */
    public UsuarioControladorRegistro(Registro vistaRegistro) {
        this.vistaRegistro = vistaRegistro;
    }

    //método compruebaEmailExistente

    /**
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void compruebaEmail() throws SQLException, ClassNotFoundException {
        String Email;
        boolean compruebaEmail;
        Email = vistaRegistro.getjTextFieldEmail().getText();
        if (!Email.isEmpty()) {
            //creo que esto sobra
            compruebaEmail = UsuarioDAO.devolverUsuarioDAO().compruebaEmail(Email);
            if (!compruebaEmail) {
                //enviar mensaje el usuario no existe.
                vistaRegistro.getjLabelExisteEmail().setText("este email se puede usar");
                vistaRegistro.getjLabelExisteEmail().setForeground(Color.green);
            } else {
                //entrar en la ventana principal.
                vistaRegistro.getjLabelExisteEmail().setText("este email ya está en uso");
                vistaRegistro.getjLabelExisteEmail().setForeground(Color.red);
            }
        } else {
            vistaRegistro.getjLabelExisteEmail().setText("");
        }
    }

    /**
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void compruebaUsuarioRegistro() throws SQLException, ClassNotFoundException {
        String nombreUsuario;
        boolean compruebaUsuario;
        nombreUsuario = vistaRegistro.getjTextFieldNombreUsuario().getText();
        //creo que esto sobra
        compruebaUsuario = UsuarioDAO.devolverUsuarioDAO().compruebaUsuario(nombreUsuario);
        if (!compruebaUsuario) {
            //enviar mensaje el usuario no existe.
            vistaRegistro.getjLabelExisteUsuario().setText("este usuario se puede usar");
            vistaRegistro.getjLabelExisteUsuario().setForeground(Color.green);
        } else {
            vistaRegistro.getjLabelExisteUsuario().setText("este usuario ya esta en uso");
            vistaRegistro.getjLabelExisteUsuario().setForeground(Color.red);
        }
    }


    /**
     *
     * @throws SQLException
     */
    public void guardarUsuario() throws SQLException {
        Validadores nuevoValidador = new Validadores();
        String nombreCompleto = vistaRegistro.getjTextFieldNombreCompleto().getText();
        String nombreUsuario = vistaRegistro.getjTextFieldNombreUsuario().getText();
        String password= new String(vistaRegistro.getjPasswordFieldPassword().getPassword());
        String passwordEncriptada = nuevoValidador.encriptaPassword(password);
        
        String email = vistaRegistro.getjTextFieldEmail().getText();
        String fecha_nac = vistaRegistro.consigueFecha();
        // comprobadores de email y usuario.

        if (UsuarioDAO.devolverUsuarioDAO().compruebaUsuario(nombreUsuario) || UsuarioDAO.devolverUsuarioDAO().compruebaEmail(email)) {
            JOptionPane.showMessageDialog(vistaRegistro, "El usuario o el email ya estan en uso", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (!comparaPassword()) {
                JOptionPane.showMessageDialog(vistaRegistro, "La contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if(UsuarioDAO.devolverUsuarioDAO().guardarDatos(nombreCompleto, nombreUsuario, passwordEncriptada, email, fecha_nac))
                {    JOptionPane.showMessageDialog(vistaRegistro, "Se ha guardado correctamente ", "Bien", JOptionPane.INFORMATION_MESSAGE);
                    vistaRegistro.dispose();}
                
            }

        }

    }

    /**
     *
     * @return
     */
    public boolean comparaPassword() {
        boolean sonIguales = false;
        String password = new String(vistaRegistro.getjPasswordFieldVerificarPassword().getPassword());
        String verificarPassword = new String(vistaRegistro.getjPasswordFieldPassword().getPassword());
        if (verificarPassword.matches(password)) {
            sonIguales = true;
        }
        return sonIguales;
    }

    
    /**
     *
     */
    public void AbrirRegistro() { 
        Registro nuevaVistaRegistro = new Registro();
        nuevaVistaRegistro.setLocationRelativeTo(null);
        nuevaVistaRegistro.setVisible(true);
    }
    /**
     *
     */
    public void cerrarRegistro(){
        vistaRegistro.dispose();
    
    }

    //método compruebaInformacionValida
    //metodo enviarInformacion. 
}
