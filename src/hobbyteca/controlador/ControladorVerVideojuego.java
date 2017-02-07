/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.controlador;

import hobbyteca.DAO.VideojuegoDAO;
import hobbyteca.modelo.Videojuego;
import hobbyteca.vista.Principal;
import hobbyteca.vista.VerVideojuego;
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
public class ControladorVerVideojuego  {

    private VerVideojuego vistaVerVideojuego;
    private ControladorVentanaPrincipal controladorVentanaPrincipal;
    private Videojuego videojuegoSeleccionado = new Videojuego();
    private String tituloSeleccionado;



    public ControladorVerVideojuego(VerVideojuego vistaVerVideojuego) {
        this.vistaVerVideojuego = vistaVerVideojuego;
    }

    public ControladorVerVideojuego(ControladorVentanaPrincipal controladorVentanaPrincipal) {
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
            valoracionMedia = seleccionarVideojuego();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error con la bdd contacte con el administrador", "Error inesperado", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
//obtenemos datos
        vistaVerVideojuego.getjLabelTitulo().setText(videojuegoSeleccionado.getTitulo());
        vistaVerVideojuego.getjLabelTextDesarrolladora().setText(videojuegoSeleccionado.getDesarrolladora());
        vistaVerVideojuego.getjLabelTextProductora().setText(videojuegoSeleccionado.getDistribuidora());
        vistaVerVideojuego.getjLabelTextLanzamiento().setText(videojuegoSeleccionado.getLanzamiento());
        vistaVerVideojuego.getjLabelTextGenero().setText(videojuegoSeleccionado.getGenero());
        vistaVerVideojuego.getjTextAreaDescripcion().setText(videojuegoSeleccionado.getDescripcion());
        vistaVerVideojuego.getjLabelTextPlataforma().setText(videojuegoSeleccionado.getPlataforma());
        //comprobamos que sea correcta la valoracion
        if (valoracionMedia == 10) {
            vistaVerVideojuego.getjLabelTextValoracionMedia().setText("No hay valoraciones");
            vistaVerVideojuego.getjLabelTextValoracionMedia().setForeground(Color.black);
        } else {
            //escribimos el texto y aplicamos el formato
            vistaVerVideojuego.getjLabelTextValoracionMedia().setText(formateador.format(valoracionMedia) + "/5");
            //comprobamos el valor para seleccionar rojo si es menor a 2.5
            if (valoracionMedia < 2.5) {
                vistaVerVideojuego.getjLabelTextValoracionMedia().setForeground(Color.red);
            }
        }
        //comprobamos si esta recomendado o no
        if (videojuegoSeleccionado.isRecomendacion()) {
            vistaVerVideojuego.getjRadioButtonRecomendarSi().setSelected(true);
        } else {
            vistaVerVideojuego.getjRadioButtonRecomendarNo().setSelected(true);
        }
        if (videojuegoSeleccionado.getJugado()) {
            vistaVerVideojuego.getjRadioButtonVisualizadoSi().setSelected(true);
        } else {
            vistaVerVideojuego.getjRadioButtonVisualizadoNo().setSelected(true);
        }
        //segun la valoracion seleccionaremos un boton
        switch (videojuegoSeleccionado.getValoracion()) {
            case 0:
                vistaVerVideojuego.getjRadioButtonValor0().setSelected(true);
                break;
            case 1:
                vistaVerVideojuego.getjRadioButtonValor1().setSelected(true);
                break;
            case 2:
                vistaVerVideojuego.getjRadioButtonValor2().setSelected(true);
                break;
            case 3:
                vistaVerVideojuego.getjRadioButtonValor3().setSelected(true);
                break;
            case 4:
                vistaVerVideojuego.getjRadioButtonValor4().setSelected(true);
                break;
            case 5:
                vistaVerVideojuego.getjRadioButtonValor5().setSelected(true);
                break;
            default:

                break;

        }

        vistaVerVideojuego.getjTextAreaTrailer().setText(videojuegoSeleccionado.getTrailer());
    }

    public double seleccionarVideojuego() throws SQLException {
        double valoracionMedia;
        //mandamos ha hacer la consulta con el titulo y el objeto pelicula que usaremos
        videojuegoSeleccionado = VideojuegoDAO.devolverVideojuegosDAO().VideojuegoVerVideojuego(videojuegoSeleccionado);
        //recogemos el valor de la valoracion media
        valoracionMedia = VideojuegoDAO.devolverVideojuegosDAO().valoracionMediaVideojuego();
        return valoracionMedia;
    }

    public  void abrirVerVideojuego(JFrame jFrame, String tituloSeleccionado) {
       VideojuegoDAO.devolverVideojuegosDAO().setTitulo(tituloSeleccionado);
        VerVideojuego nuevoVerVideojuego=new VerVideojuego(jFrame, true);
           nuevoVerVideojuego.setLocationRelativeTo(null);
        nuevoVerVideojuego.setVisible(true);
      
    }
    public boolean guardarActualizacion (int valoracion,int recomendado,int visto) throws SQLException{
    boolean correcto= VideojuegoDAO.devolverVideojuegosDAO().actualizarVideojuegoDeMiLista(valoracion,recomendado, visto);
    return correcto;
    
    }

    
    

}
