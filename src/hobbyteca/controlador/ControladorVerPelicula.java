/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.controlador;

import hobbyteca.DAO.PeliculasDAO;
import hobbyteca.modelo.Pelicula;
import hobbyteca.vista.VerPelicula;
import java.awt.Color;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author jesus
 */
public class ControladorVerPelicula  {

    private VerPelicula vistaVerPelicula;
    private ControladorVentanaPrincipal controladorVentanaPrincipal;
    private Pelicula peliculaSeleccionada = new Pelicula();
    private String tituloSeleccionado;



    public ControladorVerPelicula(VerPelicula vistaVerPelicula) {
        this.vistaVerPelicula = vistaVerPelicula;
    }

    public ControladorVerPelicula(ControladorVentanaPrincipal controladorVentanaPrincipal) {
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
            valoracionMedia = seleccionarPelicula();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error con la bdd contacte con el administrador", "Error inesperado", JOptionPane.ERROR_MESSAGE);
        }
//obtenemos datos
        vistaVerPelicula.getjLabelTitulo().setText(peliculaSeleccionada.getTitulo());
        vistaVerPelicula.getjLabelTextDirector().setText(peliculaSeleccionada.getAutor());
        vistaVerPelicula.getjLabelTextProductora().setText(peliculaSeleccionada.getProductora());
        vistaVerPelicula.getjLabelTextLanzamiento().setText(peliculaSeleccionada.getLanzamiento());
        vistaVerPelicula.getjLabelTextGenero().setText(peliculaSeleccionada.getGenero());
        vistaVerPelicula.getjTextAreaDescripcion().setText(peliculaSeleccionada.getDescripcion());
        //comprobamos que sea correcta la valoracion
        if (valoracionMedia == 10) {
            vistaVerPelicula.getjLabelTextValoracionMedia().setText("No hay valoraciones");
            vistaVerPelicula.getjLabelTextValoracionMedia().setForeground(Color.black);
        } else {
            //escribimos el texto y aplicamos el formato
            vistaVerPelicula.getjLabelTextValoracionMedia().setText(formateador.format(valoracionMedia) + "/5");
            //comprobamos el valor para seleccionar rojo si es menor a 2.5
            if (valoracionMedia < 2.5) {
                vistaVerPelicula.getjLabelTextValoracionMedia().setForeground(Color.red);
            }
        }
        //comprobamos si esta recomendado o no
        if (peliculaSeleccionada.getRecomendacion()) {
            vistaVerPelicula.getjRadioButtonRecomendarSi().setSelected(true);
        } else {
            vistaVerPelicula.getjRadioButtonRecomendarNo().setSelected(true);
        }
        if (peliculaSeleccionada.getVisualizado()) {
            vistaVerPelicula.getjRadioButtonVisualizadoSi().setSelected(true);
        } else {
            vistaVerPelicula.getjRadioButtonVisualizadoNo().setSelected(true);
        }
        //segun la valoracion seleccionaremos un boton
        switch (peliculaSeleccionada.getValoracion()) {
            case 0:
                vistaVerPelicula.getjRadioButtonValor0().setSelected(true);
                break;
            case 1:
                vistaVerPelicula.getjRadioButtonValor1().setSelected(true);
                break;
            case 2:
                vistaVerPelicula.getjRadioButtonValor2().setSelected(true);
                break;
            case 3:
                vistaVerPelicula.getjRadioButtonValor3().setSelected(true);
                break;
            case 4:
                vistaVerPelicula.getjRadioButtonValor4().setSelected(true);
                break;
            case 5:
                vistaVerPelicula.getjRadioButtonValor5().setSelected(true);
                break;
            default:

                break;

        }

        vistaVerPelicula.getjTextAreaTrailer().setText(peliculaSeleccionada.getTrailer());
    }

    public double seleccionarPelicula() throws SQLException {
        double valoracionMedia;
        //mandamos ha hacer la consulta con el titulo y el objeto pelicula que usaremos
        peliculaSeleccionada = PeliculasDAO.devolverPeliculasDAO().PeliculaVerPelicula(peliculaSeleccionada);
        //recogemos el valor de la valoracion media
        valoracionMedia = PeliculasDAO.devolverPeliculasDAO().valoracionMediaPelicula();
        return valoracionMedia;
    }

    public  void abrirVerPelicula(JFrame jFrame, String tituloSeleccionado) {
        PeliculasDAO.devolverPeliculasDAO().setTitulo(tituloSeleccionado);
        VerPelicula nuevaVerPelicula=new VerPelicula(jFrame, true);
           nuevaVerPelicula.setLocationRelativeTo(null);
        nuevaVerPelicula.setVisible(true);
      
    }
    public boolean guardarActualizacion (int valoracion,int recomendado,int visto) throws SQLException{
    boolean correcto=PeliculasDAO.devolverPeliculasDAO().actualizarPeliculaDeMiLista(valoracion,recomendado, visto);
    return correcto;
    
    }

    
    

}
