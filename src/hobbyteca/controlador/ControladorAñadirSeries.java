/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.controlador;
import hobbyteca.DAO.SeriesDAO;
import hobbyteca.vista.AñadirSerie;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 *
 * @author jesus
 */
public class ControladorAñadirSeries {
    private AñadirSerie vistaAñadirSerie;

    public ControladorAñadirSeries(AñadirSerie añadirSerie) {
        this.vistaAñadirSerie = añadirSerie;
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
           int numCaracteres =  vistaAñadirSerie.getjTextAreaDescripcion().getText().length();
        vistaAñadirSerie.getjLabelCaracteresMaximos().setText(numCaracteres+"/3000");
       }
       public static void AbrirAnnadirSerie(JFrame jFrame){
          AñadirSerie nuevaAñadirSerie=new AñadirSerie(jFrame, true);
           nuevaAñadirSerie.setLocationRelativeTo(null);
        nuevaAñadirSerie.setVisible(true);
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
        public boolean insertarSerie(String nombreSerie,String director,String productora, String lanzamiento, String genero,String descripcion,String capitulosTotales,String trailer) throws SQLException{
    boolean guardado = SeriesDAO.devolverSeriesDAO().insertarSerie(nombreSerie,director,productora,lanzamiento,genero,descripcion,capitulosTotales,trailer);
    //recargarTabla();
    return guardado;
    }
}
