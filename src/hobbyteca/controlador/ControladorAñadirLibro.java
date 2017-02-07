/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.controlador;
import hobbyteca.DAO.LibroDAO;
import hobbyteca.vista.AñadirLibro;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 *
 * @author jesus
 */
public class ControladorAñadirLibro {
    private AñadirLibro vistaAñadirLibro;

    public ControladorAñadirLibro(AñadirLibro añadirLibro) {
        this.vistaAñadirLibro = añadirLibro;
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
           int numCaracteres =  vistaAñadirLibro.getjTextAreaDescripcion().getText().length();
        vistaAñadirLibro.getjLabelCaracteresMaximos().setText(numCaracteres+"/3000");
       }
       public static void AbrirAnnadirLibro(JFrame jFrame){
          AñadirLibro nuevaAñadirLibro=new AñadirLibro(jFrame, true);
           nuevaAñadirLibro.setLocationRelativeTo(null);
        nuevaAñadirLibro.setVisible(true);
       }
       public int comprobarCamposRellenos(String titulo,String escritor,String editorial,String descripcion){
       int rellenos=0;
       
       if (!titulo.isEmpty() && !escritor.isEmpty() && !editorial.isEmpty()&& !descripcion.isEmpty()){
           rellenos=1;
           rellenos=2;  
       }
       else{
       rellenos=0;
       }
       
           return rellenos;
       }
       
       
    public boolean insertarLibro(String nombreLibro,String director,String productora, String lanzamiento, String genero,String descripcion) throws SQLException{
    boolean guardado = LibroDAO.devolverLibrosDAO().insertarLibro(nombreLibro, director, productora, lanzamiento, genero, descripcion);
    //recargarTabla();
    return guardado;
    }
}
