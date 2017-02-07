/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.controlador;
import hobbyteca.DAO.VideojuegoDAO;
import hobbyteca.vista.AñadirVideojuego;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 *
 * @author jesus
 */
public class ControladorAñadirVideojuego {
    private AñadirVideojuego vistaAñadirVideojuegos;

    public ControladorAñadirVideojuego(AñadirVideojuego añadirVideojuego) {
        this.vistaAñadirVideojuegos = añadirVideojuego;
    }
    
       public void caracteresMaximos(KeyEvent evt, int numeroDeCarcateresMaximos, JTextField jText) {
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
       public void caracteresMaximosTextArea(KeyEvent evt, int numeroDeCarcateresMaximos, JTextArea jTextArea) {
        String texto;
        String textoCortado;
        if (jTextArea.getText().length() == numeroDeCarcateresMaximos) {
            evt.consume();
        }
        if (jTextArea.getText().length() > numeroDeCarcateresMaximos) {
            texto = jTextArea.getText();
            textoCortado = texto.substring(0, numeroDeCarcateresMaximos);
            jTextArea.setText(textoCortado);
        }

    }
       public void contadorTextArea(){
           int numCaracteres =  vistaAñadirVideojuegos.getjTextAreaDescripcion().getText().length();
        vistaAñadirVideojuegos.getjLabelCaracteresMaximos().setText(numCaracteres+"/3000");
       }
       
       
       
       
       public static void AbrirAnnadirVideojuego(JFrame jFrame){
           AñadirVideojuego nuevaAñadirVideojuego=new AñadirVideojuego(jFrame, true);
           nuevaAñadirVideojuego.setLocationRelativeTo(null);
           nuevaAñadirVideojuego.setVisible(true);
       }
      
       
       public int comprobarCamposRellenos(String titulo,String desarrolladora, String distribuidora, String descripcion, String trailer){
       int rellenos=0;
       
       if (!titulo.isEmpty() && !desarrolladora.isEmpty() && !distribuidora.isEmpty()&& !descripcion.isEmpty()){
           rellenos=1;
           if (!trailer.isEmpty()){
             rellenos=2;  
           }    
       }
       else{
       rellenos=0;
       }
       
           return rellenos;
       }
      /**
     *
     * @param nombreVideojuego
     * @param desarrolladora
     * @param distribuidora
     * @param lanzamiento
     * @param genero
     * @param descripcion
     * @param plataforma
     * @param trailer
     * @return
     * @throws SQLException
     */
    public boolean insertarVideojuego(String nombreVideojuego, String desarrolladora, String distribuidora, String lanzamiento, String genero, String descripcion, String plataforma, String trailer) throws SQLException {
        boolean guardado = VideojuegoDAO.devolverVideojuegosDAO().insertarVideojuego(nombreVideojuego, desarrolladora, distribuidora, lanzamiento, genero, descripcion, plataforma, trailer);
        //recargarTabla();
        return guardado;
    }
}
