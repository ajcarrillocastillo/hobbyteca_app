/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.controlador;
import hobbyteca.DAO.MusicaDAO;
import hobbyteca.vista.AñadirMusica;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 *
 * @author jesus
 */
public class ControladorAñadirMusica {
    private AñadirMusica vistaAñadirMusica;

    public ControladorAñadirMusica(AñadirMusica añadirMusica) {
        this.vistaAñadirMusica = añadirMusica;
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
       
       public static void AbrirAnnadirMusica(JFrame jFrame){
       AñadirMusica nuevaAñadirMusica=new AñadirMusica(jFrame, true);
       nuevaAñadirMusica.setLocationRelativeTo(null);
       nuevaAñadirMusica.setVisible(true);
       }
      
       public int comprobarCamposRellenos(String ASIN,String titulo,String autor,String discografica, String album, String enlace){
       int rellenos=0;
       if (!titulo.isEmpty() && !ASIN.isEmpty() && !titulo.isEmpty()&& !autor.isEmpty() && !discografica.isEmpty() && !album.isEmpty()){
           rellenos=1;
           if(!enlace.isEmpty()){
           rellenos=2;
           }
       }
       else{
       rellenos=0;
       }
       
           return rellenos;
       }
       
       
public boolean insertarMusica(String ASIN,String titulo,String autor,String discografica, String lanzamiento, String genero,String album,String trailer) throws SQLException{
    boolean guardado = MusicaDAO.devolverMusicaDAO().insertarMusica(ASIN, titulo, autor, discografica, lanzamiento, genero,album ,trailer);
    //recargarTabla();
    return guardado;
    }


}
