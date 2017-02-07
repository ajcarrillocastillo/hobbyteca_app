/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.controlador;

import hobbyteca.DAO.MusicaDAO;
import hobbyteca.modelo.Musica;
import hobbyteca.vista.VerMusica;
import java.awt.Color;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author jesus
 */
public class ControladorVerMusica  {

    private VerMusica vistaVerMusica;
    private ControladorVentanaPrincipal controladorVentanaPrincipal;
    private Musica musicaSeleccionada = new Musica();
    private String tituloSeleccionado;



    public ControladorVerMusica(VerMusica vistaVerMusica) {
        this.vistaVerMusica = vistaVerMusica;
    }

    public ControladorVerMusica(ControladorVentanaPrincipal controladorVentanaPrincipal) {
        this.controladorVentanaPrincipal = controladorVentanaPrincipal;
    }

    public String getTituloSeleccionado() {
        return tituloSeleccionado;
    }

    public void setTituloSeleccionado(String tSeleccionado) {
        this.tituloSeleccionado = tSeleccionado;
    }

    
    
    public void rellenarDatos() {
        //declaramos e inicializamos donde se guardara la media(lo hacemos a 10 porque es un numero que nunca saldra para poder controlar fallos
        double valoracionMedia = 10;
        //creamos un formato de 2 decimales
        DecimalFormat formateador = new DecimalFormat("#.##");

        try {
            //llamamos al metodo que sacara los datos de la bdd y nos devolverá la media y tambien guardara los datos de la pelicula
            valoracionMedia = seleccionarMusica();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error con la bdd contacte con el administrador", "Error inesperado", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ControladorVerLibro.class.getName()).log(Level.SEVERE, null, ex);
        }
//obtenemos datos
        vistaVerMusica.getjLabelTitulo().setText(musicaSeleccionada.getTitulo());
        vistaVerMusica.getjLabelTextAutor().setText(musicaSeleccionada.getAutor());
        vistaVerMusica.getjLabelTextDiscografica().setText(musicaSeleccionada.getDiscografica());
        vistaVerMusica.getjLabelTextLanzamiento().setText(musicaSeleccionada.getLanzamiento());
        vistaVerMusica.getjLabelTextGenero().setText(musicaSeleccionada.getGenero());
        vistaVerMusica.getjLabelTextASIN().setText(musicaSeleccionada.getASIN());
        vistaVerMusica.getjLabelTextAlbum().setText(musicaSeleccionada.getAlbum());
        //comprobamos que sea correcta la valoracion
        if (valoracionMedia == 10) {
            vistaVerMusica.getjLabelTextValoracionMedia().setText("No hay valoraciones");
            vistaVerMusica.getjLabelTextValoracionMedia().setForeground(Color.black);
        } else {
            //escribimos el texto y aplicamos el formato
            vistaVerMusica.getjLabelTextValoracionMedia().setText(formateador.format(valoracionMedia) + "/5");
            //comprobamos el valor para seleccionar rojo si es menor a 2.5
            if (valoracionMedia < 2.5) {
                vistaVerMusica.getjLabelTextValoracionMedia().setForeground(Color.red);
            }
        }
        //comprobamos si esta recomendado o no
        if (musicaSeleccionada.isRecomendacion()) {
            vistaVerMusica.getjRadioButtonRecomendarSi().setSelected(true);
        } else {
            vistaVerMusica.getjRadioButtonRecomendarNo().setSelected(true);
        }
        if (musicaSeleccionada.isEscuchado()) {
            vistaVerMusica.getjRadioButtonVisualizadoSi().setSelected(true);
        } else {
            vistaVerMusica.getjRadioButtonVisualizadoNo().setSelected(true);
        }
        //segun la valoracion seleccionaremos un boton
        switch (musicaSeleccionada.getValoracion()) {
            case 0:
                vistaVerMusica.getjRadioButtonValor0().setSelected(true);
                break;
            case 1:
                vistaVerMusica.getjRadioButtonValor1().setSelected(true);
                break;
            case 2:
                vistaVerMusica.getjRadioButtonValor2().setSelected(true);
                break;
            case 3:
                vistaVerMusica.getjRadioButtonValor3().setSelected(true);
                break;
            case 4:
                vistaVerMusica.getjRadioButtonValor4().setSelected(true);
                break;
            case 5:
                vistaVerMusica.getjRadioButtonValor5().setSelected(true);
                break;
            default:

                break;

        }

        vistaVerMusica.getjTextAreaTrailer().setText(musicaSeleccionada.getTrailer());
    }

    public double seleccionarMusica() throws SQLException {
        double valoracionMedia;
        //mandamos ha hacer la consulta con el titulo y el objeto pelicula que usaremos
        musicaSeleccionada = MusicaDAO.devolverMusicaDAO().MusicaVerMusica(musicaSeleccionada);
        //recogemos el valor de la valoracion media
        valoracionMedia = MusicaDAO.devolverMusicaDAO().valoracionMediaMusica();
        return valoracionMedia;
    }

    public  void abrirVerMusica(JFrame jFrame, String ASIN) {
        MusicaDAO.devolverMusicaDAO().setASIN(ASIN);
        VerMusica nuevaVerPelicula=new VerMusica(jFrame, true);
           nuevaVerPelicula.setLocationRelativeTo(null);
        nuevaVerPelicula.setVisible(true);
      
    }
    public boolean guardarActualizacion (int valoracion,int recomendado,int escuchado) throws SQLException{
    boolean correcto=MusicaDAO.devolverMusicaDAO().actualizarMusicaDeMiLista(valoracion, recomendado, escuchado);
    
    return correcto;
    
    }

    
    

}
