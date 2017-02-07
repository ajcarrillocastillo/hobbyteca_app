/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.controlador;
import hobbyteca.DAO.PeliculasDAO;
import hobbyteca.vista.AñadirPelicula;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 *
 * @author jesus
 */
public class ControladorAñadirPelicula {
    private AñadirPelicula vistaAñadirPelicula;

    public ControladorAñadirPelicula(AñadirPelicula añadirPelicula) {
        this.vistaAñadirPelicula = añadirPelicula;
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
           int numCaracteres =  vistaAñadirPelicula.getjTextAreaDescripcion().getText().length();
        vistaAñadirPelicula.getjLabelCaracteresMaximos().setText(numCaracteres+"/3000");
       }
       public static void AbrirAnnadirPelicula(JFrame jFrame){
          AñadirPelicula nuevaAñadirPelicula=new AñadirPelicula(jFrame, true);
           nuevaAñadirPelicula.setLocationRelativeTo(null);
        nuevaAñadirPelicula.setVisible(true);
       }
       public int comprobarCamposRellenos(String titulo,String director,String productora,String descripcion,String trailer){
       int rellenos=0;
       
       if (!titulo.isEmpty() && !director.isEmpty() && !productora.isEmpty()&& !descripcion.isEmpty()){
           rellenos=1;
           if(!trailer.isEmpty()){
           rellenos=2;
           }
           
       }
       else{
       rellenos=0;
       }
       
           return rellenos;
       }
        public boolean insertarPelicula(String nombrePelicula,String director,String productora, String lanzamiento, String genero,String descripcion,String trailer) throws SQLException{
    boolean guardado = PeliculasDAO.devolverPeliculasDAO().insertarPelicula(nombrePelicula,director,productora,lanzamiento,genero,descripcion,trailer);
    //recargarTabla();
    return guardado;
    }
}
