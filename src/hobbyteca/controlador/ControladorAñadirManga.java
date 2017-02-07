/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.controlador;
import hobbyteca.DAO.MangasDAO;
import hobbyteca.vista.AñadirManga;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 *
 * @author jesus
 */
public class ControladorAñadirManga {
    private AñadirManga vistaAñadirManga;

    public ControladorAñadirManga(AñadirManga añadirManga) {
        this.vistaAñadirManga = añadirManga;
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
           int numCaracteres =  vistaAñadirManga.getjTextAreaDescripcion().getText().length();
        vistaAñadirManga.getjLabelCaracteresMaximos().setText(numCaracteres+"/3000");
       }
       public static void AbrirAnnadirManga(JFrame jFrame){
          AñadirManga nuevaAñadirManga=new AñadirManga(jFrame, true);
           nuevaAñadirManga.setLocationRelativeTo(null);
        nuevaAñadirManga.setVisible(true);
       }
       public int comprobarCamposRellenos(String titulo,String director,String productora,String descripcion,String tomosTotales){
       int rellenos=0;
       
       if (!titulo.isEmpty() && !director.isEmpty() && !productora.isEmpty()&& !descripcion.isEmpty()&& !tomosTotales.isEmpty()){
           rellenos=2;
           }
           
       
       else{
       rellenos=0;
       }
       
           return rellenos;
       }
        public boolean insertarManga(String nombreManga,String director,String productora, String lanzamiento, String genero,String descripcion,String tomosTotales) throws SQLException{
    boolean guardado = MangasDAO.devolverMangasDAO().insertarManga(nombreManga,director,productora,lanzamiento,genero,descripcion,tomosTotales);
    //recargarTabla();
    return guardado;
    }
}
