/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.controlador;

import hobbyteca.DAO.SeriesDAO;
import hobbyteca.modelo.Serie;
import hobbyteca.vista.VerSerie;
import java.awt.Color;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author jesus
 */
public class ControladorVerSerie  {


    private VerSerie vistaVerSerie;
    private ControladorVentanaPrincipal controladorVentanaPrincipal;
    private Serie serieSeleccionada = new Serie();
    private String tituloSeleccionado;



    public ControladorVerSerie(VerSerie vistaVerSerie) {
        this.vistaVerSerie = vistaVerSerie;
    }

    public ControladorVerSerie(ControladorVentanaPrincipal controladorVentanaPrincipal) {
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
            //llamamos al metodo que sacara los datos de la bdd y nos devolverá la media y tambien guardara los datos de la serie
            valoracionMedia = seleccionarSerie();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error con la bdd contacte con el administrador", "Error inesperado", JOptionPane.ERROR_MESSAGE);
        }
          int capitulosTotales=serieSeleccionada.getCapitulos_totales();
//obtenemos datos
        DefaultComboBoxModel MiModelo= new DefaultComboBoxModel();
        vistaVerSerie.getjLabelTitulo().setText(serieSeleccionada.getTitulo());
        vistaVerSerie.getjLabelTextDirector().setText(serieSeleccionada.getDirector());
        vistaVerSerie.getjLabelTextProductora().setText(serieSeleccionada.getProductora());
        vistaVerSerie.getjLabelTextLanzamiento().setText(serieSeleccionada.getLanzamiento());
        vistaVerSerie.getjLabelTextGenero().setText(serieSeleccionada.getGenero());
        vistaVerSerie.getjTextAreaDescripcion().setText(serieSeleccionada.getDescripcion());
        vistaVerSerie.getjLabelTextCapitulosTotales().setText("/"+capitulosTotales);
        for (int i=0; i<=capitulosTotales;i++)
        { 
            String capitulo=""+i;
        MiModelo.addElement(capitulo);
        
        }
        MiModelo.setSelectedItem(serieSeleccionada.getCapitulos_vistos());
        vistaVerSerie.getjComboBoxCapitulosVistos().setModel(MiModelo);
        //comprobamos que sea correcta la valoracion
        if (valoracionMedia == 10) {
            vistaVerSerie.getjLabelTextValoracionMedia().setText("No hay valoraciones");
            vistaVerSerie.getjLabelTextValoracionMedia().setForeground(Color.black);
        } else {
            //escribimos el texto y aplicamos el formato
            vistaVerSerie.getjLabelTextValoracionMedia().setText(formateador.format(valoracionMedia) + "/5");
            //comprobamos el valor para seleccionar rojo si es menor a 2.5
            if (valoracionMedia < 2.5) {
                vistaVerSerie.getjLabelTextValoracionMedia().setForeground(Color.red);
            }
        }
        //comprobamos si esta recomendado o no
        if (serieSeleccionada.isRecomendacion()) {
            vistaVerSerie.getjRadioButtonRecomendarSi().setSelected(true);
        } else {
            vistaVerSerie.getjRadioButtonRecomendarNo().setSelected(true);
        }
        //segun la valoracion seleccionaremos un boton
        switch (serieSeleccionada.getValoracion()) {
            case 0:
                vistaVerSerie.getjRadioButtonValor0().setSelected(true);
                break;
            case 1:
                vistaVerSerie.getjRadioButtonValor1().setSelected(true);
                break;
            case 2:
                vistaVerSerie.getjRadioButtonValor2().setSelected(true);
                break;
            case 3:
                vistaVerSerie.getjRadioButtonValor3().setSelected(true);
                break;
            case 4:
                vistaVerSerie.getjRadioButtonValor4().setSelected(true);
                break;
            case 5:
                vistaVerSerie.getjRadioButtonValor5().setSelected(true);
                break;
            default:

                break;

        }

        vistaVerSerie.getjTextAreaTrailer().setText(serieSeleccionada.getTrailer());
    }

    public double seleccionarSerie() throws SQLException {
        double valoracionMedia;
        //mandamos ha hacer la consulta con el titulo y el objeto serie que usaremos
        serieSeleccionada = SeriesDAO.devolverSeriesDAO().SeriesVerSerie(serieSeleccionada);
        //recogemos el valor de la valoracion media
        valoracionMedia = SeriesDAO.devolverSeriesDAO().valoracionMediaSerie();
        return valoracionMedia;
    }

    public  void abrirVerSerie(JFrame jFrame, String tituloSeleccionado) {
        SeriesDAO.devolverSeriesDAO().setTitulo(tituloSeleccionado);
        VerSerie nuevaVerSerie=new VerSerie(jFrame, true);
           nuevaVerSerie.setLocationRelativeTo(null);
        nuevaVerSerie.setVisible(true);
      
    }
    public boolean guardarActualizacion (String valoracion,int recomendado,String capitulosVistos) throws SQLException{
    boolean correcto=SeriesDAO.devolverSeriesDAO().actualizarSerieDeMiLista(valoracion,recomendado, capitulosVistos);
    return correcto;
    
    }

    
    

}

