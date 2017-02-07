/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.controlador;

import java.awt.Color;
import javax.swing.JOptionPane;
import hobbyteca.vista.Registro;
import java.awt.event.KeyEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author jesus
 */
public class UsuarioControladorRegistroEnventos {

    private Registro vistaRegistro;
    private UsuarioControladorRegistro cotroladorRegistro;
    private Validadores validador = new Validadores();

    /**
     *
     * @param vistaRegistro
     */
    public UsuarioControladorRegistroEnventos(Registro vistaRegistro) {
        this.vistaRegistro = vistaRegistro;
    }

    /**
     *
     * @param controladorRegistro
     */
    public UsuarioControladorRegistroEnventos(UsuarioControladorRegistro controladorRegistro) {
        this.cotroladorRegistro = controladorRegistro;
    }

    /**
     *
     * @param validador
     */
    public UsuarioControladorRegistroEnventos(Validadores validador) {
        this.validador = validador;
    }

    //Control de numeros
    /**
     *
     * @param evt
     */
    public void invalidarNumeros(KeyEvent evt) {
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            evt.consume();

        }
    }

    /**
     *
     * @param evt
     */
    public void invalidarLetras(KeyEvent evt) {
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();

        }
    }

    /*limitadores de numeros*/
    /**
     *
     * @param evt
     * @param numeroDeCarcateresMaximos
     */
    public void caracteresMaximosTexto(KeyEvent evt, int numeroDeCarcateresMaximos, JTextField jText) {
        String texto;
        String textoCortado;
        if (jText.getText().length() == numeroDeCarcateresMaximos) {
            evt.consume();
        }
        if (jText.getText().length() > numeroDeCarcateresMaximos) {
            texto = jText.getText();
            textoCortado = texto.substring(0, numeroDeCarcateresMaximos);
            jText.setText(textoCortado);
        }

    }

    /**
     *
     * @param evt
     * @param numeroDeCarcateresMaximos
     */
    public void caracteresMaximosjPassword(KeyEvent evt, int numeroDeCarcateresMaximos, JPasswordField jPassword) {
        String password = new String(jPassword.getPassword());
        if (password.length() == numeroDeCarcateresMaximos) {
            evt.consume();
        }

        String passwordCortado;
        if (jPassword.getText().length() == numeroDeCarcateresMaximos) {
            evt.consume();
        }
        if (jPassword.getText().length() > numeroDeCarcateresMaximos) {
            password = jPassword.getText();
            passwordCortado = password.substring(0, numeroDeCarcateresMaximos);
            jPassword.setText(passwordCortado);

        }
    }

    /*fin limitadores de numeros*/
    //Mostrar Contaseña
    /**
     *
     * @param evt
     */
    public void MostrarOcultarContraseña(java.awt.event.ActionEvent evt) {
        if (vistaRegistro.getjCheckBoxMostrarPassword().isSelected()) {
            vistaRegistro.getjPasswordFieldPassword().setEchoChar((char) 0);
            vistaRegistro.getjPasswordFieldVerificarPassword().setEchoChar((char) 0);
        } else {
            if (vistaRegistro.getflagPassword() == true) {
                vistaRegistro.getjPasswordFieldPassword().setEchoChar('*');
            }
            if (vistaRegistro.getflagValidarPassword() == true) {
                vistaRegistro.getjPasswordFieldVerificarPassword().setEchoChar('*');

            }
        }

    }
    //validar Email

    /**
     *
     * @return
     */
    public boolean validarEmail() {

        boolean validado = validador.validateEmail(vistaRegistro.getjTextFieldEmail().getText());
        if (!validado) {
            vistaRegistro.getjLabelExisteEmail().setText("Tiene que usar un formato valido");
            vistaRegistro.getjLabelExisteEmail().setForeground(Color.red);
        } else {
            vistaRegistro.getjLabelExisteEmail().setText("");
        }
        return validado;
    }
    //validar contraseña

    /**
     *
     * @param evt
     * @param seleccionador
     */
    public void ValidarContraseñas(java.awt.event.FocusEvent evt, boolean seleccionador) {
        String verificaPasword = new String(vistaRegistro.getjPasswordFieldVerificarPassword().getPassword());
        String password = new String(vistaRegistro.getjPasswordFieldPassword().getPassword());
        if (seleccionador) {
            if (password.isEmpty()) {
                vistaRegistro.getjPasswordFieldPassword().setEchoChar((char) 0);
                vistaRegistro.getjPasswordFieldPassword().setText("Contraseña");
                vistaRegistro.getjPasswordFieldPassword().setForeground(Color.GRAY);
                vistaRegistro.setflagPassword(false);
            } else {
                if (vistaRegistro.getflagValidarPassword()) {
                    if (!comparaPassword()) {
                        vistaRegistro.getjLabelPasswordNoCoincide().setText("Contraseñas no coinciden");
                        vistaRegistro.getjLabelPasswordNoCoincide().setForeground(Color.red);
                    } else {
                        vistaRegistro.getjLabelPasswordNoCoincide().setText("");
                    }
                }
            }
        } else {
            if (verificaPasword.isEmpty()) {
                vistaRegistro.getjPasswordFieldVerificarPassword().setEchoChar((char) 0);
                vistaRegistro.getjPasswordFieldVerificarPassword().setText("Repetir Contraseña");
                vistaRegistro.getjPasswordFieldVerificarPassword().setForeground(Color.GRAY);
                vistaRegistro.setflagValidarPassword(false);
            } else {
                if (vistaRegistro.getflagPassword() == true) {

                    if (!password.equals(verificaPasword)) {
                        vistaRegistro.getjLabelPasswordNoCoincide().setText("Contraseñas no coinciden");
                        vistaRegistro.getjLabelPasswordNoCoincide().setForeground(Color.red);
                    } else {
                        vistaRegistro.getjLabelPasswordNoCoincide().setText("");
                    }
                }
            }
        }

    }

    //vaciar campos
    /**
     *
     * @param evt
     * @param seleccion
     */
    public void VaciarCampos(java.awt.event.FocusEvent evt, int seleccion) {
        switch (seleccion) {
            //Nombre
            case 0:
                if (vistaRegistro.getflagNombre() == false) {
                    vistaRegistro.getjTextFieldNombreCompleto().setText("");
                    vistaRegistro.getjTextFieldNombreCompleto().setForeground(Color.black);
                    vistaRegistro.setflagNombre(true);
                }
                break;
            //Usuario
            case 1:
                if (vistaRegistro.getFlagUsuario() == false) {
                    vistaRegistro.getjTextFieldNombreUsuario().setText("");
                    vistaRegistro.getjTextFieldNombreUsuario().setForeground(Color.black);
                    vistaRegistro.setflagUsuario(true);
                }
                break;
            //Email
            case 2:
                if (vistaRegistro.getflagEmail() == false) {
                    vistaRegistro.getjTextFieldEmail().setText("");
                    vistaRegistro.getjTextFieldEmail().setForeground(Color.black);
                    vistaRegistro.setflagEmail(true);
                }
                break;

            //Contraseña    
            case 3:
                if (vistaRegistro.getflagPassword() == false) {
                    vistaRegistro.getjPasswordFieldPassword().setText("");
                    vistaRegistro.getjPasswordFieldPassword().setForeground(Color.black);
                    vistaRegistro.getjPasswordFieldPassword().setEchoChar('*');
                    vistaRegistro.setflagPassword(true);
                }
                if (vistaRegistro.getjCheckBoxMostrarPassword().isSelected()) {
                    vistaRegistro.getjPasswordFieldPassword().setEchoChar((char) 0);
                } else {
                    vistaRegistro.getjPasswordFieldPassword().setEchoChar('*');
                }
                break;
            //validarContraseña    
            case 4:
                if (vistaRegistro.getflagValidarPassword() == false) {
                    vistaRegistro.getjPasswordFieldVerificarPassword().setText("");
                    vistaRegistro.getjPasswordFieldVerificarPassword().setForeground(Color.black);
                    vistaRegistro.getjPasswordFieldVerificarPassword().setEchoChar('*');
                    vistaRegistro.setflagValidarPassword(true);
                }
                if (vistaRegistro.getjCheckBoxMostrarPassword().isSelected()) {
                    vistaRegistro.getjPasswordFieldVerificarPassword().setEchoChar((char) 0);
                } else {
                    vistaRegistro.getjPasswordFieldVerificarPassword().setEchoChar('*');
                }
                break;
            default:
                JOptionPane.showMessageDialog(vistaRegistro, "Si ves este mensaje es que has tocado el codigo por favor dejalo como estaba y no vuevas edites nada", "Error", JOptionPane.ERROR_MESSAGE);
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
            case 0:

                vistaRegistro.getjTextFieldNombreCompleto().setText("Nombre Completo");
                vistaRegistro.getjTextFieldNombreCompleto().setForeground(Color.GRAY);
                vistaRegistro.setflagNombre(false);

                break;
            case 1:

                vistaRegistro.getjTextFieldNombreUsuario().setText("Usuario");
                vistaRegistro.getjTextFieldNombreUsuario().setForeground(Color.GRAY);
                vistaRegistro.getjLabelExisteUsuario().setText("");
                vistaRegistro.setflagUsuario(false);

                break;
            case 2:
                vistaRegistro.getjTextFieldEmail().setText("Correo Electrónico");
                vistaRegistro.getjTextFieldEmail().setForeground(Color.GRAY);
                vistaRegistro.getjLabelExisteEmail().setText("");
                vistaRegistro.setflagEmail(false);
                break;
            default:
                JOptionPane.showMessageDialog(vistaRegistro, "Si ves este mensaje es que has tocado el codigo por favor dejalo como estaba y no vuevas edites nada", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    /**
     *
     * @param minimo
     * @return
     */
    public boolean limitaNombreCompleto(int minimo) {
        boolean superaLimite = validador.limitadorNombreCompleto(vistaRegistro.getjTextFieldNombreCompleto().getText(), minimo);
        if (!superaLimite) {
            vistaRegistro.getjLabelErrorNombre().setText("Debe tener mas de " + minimo);
            vistaRegistro.getjLabelErrorNombre().setForeground(Color.red);

        } else {
            vistaRegistro.getjLabelErrorNombre().setText("");
        }
        return superaLimite;
    }

    /**
     *
     * @param minimo
     * @return
     */
    public boolean limitaNombreUsuario(int minimo) {
        boolean superaLimite = validador.limitadorNombreUsuario(vistaRegistro.getjTextFieldNombreUsuario().getText(), minimo);
        if (!superaLimite) {
            vistaRegistro.getjLabelExisteUsuario().setText("Debe tener mas de " + minimo);
            vistaRegistro.getjLabelExisteUsuario().setForeground(Color.red);

        } else {
            vistaRegistro.getjLabelErrorNombre().setText("");
        }
        return superaLimite;
    }

    /**
     * Compara contraseñas y devuelve si son iguales o no.
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

}
