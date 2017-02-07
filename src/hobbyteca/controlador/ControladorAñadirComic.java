/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.controlador;
import hobbyteca.DAO.ComicsDAO;
import hobbyteca.vista.AñadirComic;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 *
 * @author jesus
 */
public class ControladorAñadirComic {
    private AñadirComic vistaAñadirComic;

    public ControladorAñadirComic(AñadirComic añadirComic) {
        this.vistaAñadirComic = añadirComic;
    }
    public void limitarNumeros(KeyEvent evt){
            char letra=evt.getKeyChar();
        if(Character.isDigit(letra)){
        
        }else{evt.consume();}
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
           int numCaracteres =  vistaAñadirComic.getjTextAreaDescripcion().getText().length();
        vistaAñadirComic.getjLabelCaracteresMaximos().setText(numCaracteres+"/3000");
       }
       public static void AbrirAnnadirComic(JFrame jFrame){
          AñadirComic nuevaAñadirComic=new AñadirComic(jFrame, true);
           nuevaAñadirComic.setLocationRelativeTo(null);
        nuevaAñadirComic.setVisible(true);
       }
       public int comprobarCamposRellenos(String titulo,String director,String productora,String descripcion,String numerosTotales){
       int rellenos=0;
       
       if (!titulo.isEmpty() && !director.isEmpty() && !productora.isEmpty()&& !descripcion.isEmpty()&& !numerosTotales.isEmpty()){
           rellenos=2;
           }
           
       
       else{
       rellenos=0;
       }
       
           return rellenos;
       }
        public boolean insertarComic(String nombreComic,String director,String productora, String lanzamiento, String genero,String descripcion,String numerosTotales) throws SQLException{
    boolean guardado = ComicsDAO.devolverComicsDAO().insertarComic(nombreComic,director,productora,lanzamiento,genero,descripcion,numerosTotales);
    //recargarTabla();
    return guardado;
    }
}
