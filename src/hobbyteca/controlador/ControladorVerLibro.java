/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.controlador;

import hobbyteca.DAO.LibroDAO;
import hobbyteca.modelo.Libro;
import hobbyteca.vista.VerLibro;
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
public class ControladorVerLibro  {

    private VerLibro vistaVerLibro;
    private ControladorVentanaPrincipal controladorVentanaPrincipal;
    private Libro LibroSeleccionado = new Libro();
    private String tituloSeleccionado;



    public ControladorVerLibro(VerLibro vistaVerLibro) {
        this.vistaVerLibro = vistaVerLibro;
    }

    public ControladorVerLibro(ControladorVentanaPrincipal controladorVentanaPrincipal) {
        this.controladorVentanaPrincipal = controladorVentanaPrincipal;
    }

    public String getTituloSeleccionado() {
        return tituloSeleccionado;
    }

    public void setTituloSeleccionado(String tSeleccionado) {
        this.tituloSeleccionado = tSeleccionado;
    }

    /**
     * 
     */
    public void rellenarDatos() {
        //declaramos e inicializamos donde se guardara la media(lo hacemos a 10 porque es un numero que nunca saldra para poder controlar fallos
        double valoracionMedia = 10;
        //creamos un formato de 2 decimales
        DecimalFormat formateador = new DecimalFormat("#.##");

        
        try {
            //llamamos al metodo que sacara los datos de la bdd y nos devolverá la media y tambien guardara los datos del libro
            valoracionMedia = seleccionarLibro();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error con la bdd contacte con el administrador", "Error inesperado", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ControladorVerLibro.class.getName()).log(Level.SEVERE, null, ex);
        }
      
//obtenemos datos
        vistaVerLibro.getjLabelTitulo().setText(LibroSeleccionado.getTitulo());
        vistaVerLibro.getjLabelTextEscritor().setText(LibroSeleccionado.getAutor());
        vistaVerLibro.getjLabelTextEditorial().setText(LibroSeleccionado.getEditorial());
        vistaVerLibro.getjLabelTextLanzamiento().setText(LibroSeleccionado.getLanzamiento());
        vistaVerLibro.getjLabelTextGenero().setText(LibroSeleccionado.getGenero());
        vistaVerLibro.getjTextAreaDescripcion().setText(LibroSeleccionado.getDescripcion());
        //comprobamos que sea correcta la valoracion
        if (valoracionMedia == 10) {
            vistaVerLibro.getjLabelTextValoracionMedia().setText("No hay valoraciones");
            vistaVerLibro.getjLabelTextValoracionMedia().setForeground(Color.black);
        } else {
            //escribimos el texto y aplicamos el formato
            vistaVerLibro.getjLabelTextValoracionMedia().setText(formateador.format(valoracionMedia) + "/5");
            //comprobamos el valor para seleccionar rojo si es menor a 2.5
            if (valoracionMedia < 2.5) {
                vistaVerLibro.getjLabelTextValoracionMedia().setForeground(Color.red);
            }
        }
        //comprobamos si esta recomendado o no
        if (LibroSeleccionado.isRecomendacion()) {
            vistaVerLibro.getjRadioButtonRecomendarSi().setSelected(true);
        } else {
            vistaVerLibro.getjRadioButtonRecomendarNo().setSelected(true);
        }
        if (LibroSeleccionado.isVisualizado()) {
            vistaVerLibro.getjRadioButtonVisualizadoSi().setSelected(true);
        } else {
            vistaVerLibro.getjRadioButtonVisualizadoNo().setSelected(true);
        }
        //segun la valoracion seleccionaremos un boton
        switch (LibroSeleccionado.getValoracion()) {
            case 0:
                vistaVerLibro.getjRadioButtonValor0().setSelected(true);
                break;
            case 1:
                vistaVerLibro.getjRadioButtonValor1().setSelected(true);
                break;
            case 2:
                vistaVerLibro.getjRadioButtonValor2().setSelected(true);
                break;
            case 3:
                vistaVerLibro.getjRadioButtonValor3().setSelected(true);
                break;
            case 4:
                vistaVerLibro.getjRadioButtonValor4().setSelected(true);
                break;
            case 5:
                vistaVerLibro.getjRadioButtonValor5().setSelected(true);
                break;
            default:

                break;

        }

    }

    public double seleccionarLibro() throws SQLException {
        double valoracionMedia;
        //mandamos ha hacer la consulta con el titulo y el objeto libro que usaremos
        LibroSeleccionado = LibroDAO.devolverLibrosDAO().LibroVerLibro(LibroSeleccionado);
        //recogemos el valor de la valoracion media
        valoracionMedia = LibroDAO.devolverLibrosDAO().valoracionMediaLibro();
        return valoracionMedia;
    }

    /**
     * Llama a la ventana verLibro
     * @param jFrame
     * @param tituloSeleccionado 
     */
    public  void abrirVerLibro(JFrame jFrame, String tituloSeleccionado) {
       LibroDAO.devolverLibrosDAO().setTitulo(tituloSeleccionado);
        VerLibro nuevaVerLibro=new VerLibro(jFrame, true);
        nuevaVerLibro.setLocationRelativeTo(null);
        nuevaVerLibro.setVisible(true);
      
    }
    
    /**
     * Guarda lo que el usuario cambia.
     * @param valoracion
     * @param recomendado
     * @param visto
     * @return
     * @throws SQLException 
     */
    public boolean guardarActualizacion (int valoracion,int recomendado,int visto) throws SQLException{
    boolean correcto=LibroDAO.devolverLibrosDAO().actualizarLibroDeMiLista(valoracion, recomendado, visto);
    return correcto;
    }

    
    

}
