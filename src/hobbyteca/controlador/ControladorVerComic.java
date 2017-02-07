/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.controlador;

import hobbyteca.DAO.ComicsDAO;
import hobbyteca.modelo.Comic;
import hobbyteca.vista.VerComic;
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
public class ControladorVerComic  {

    private VerComic vistaVerComic;
    private ControladorVentanaPrincipal controladorVentanaPrincipal;
    private Comic serieSeleccionada = new Comic();
    private String tituloSeleccionado;



    public ControladorVerComic(VerComic vistaVerComic) {
        this.vistaVerComic = vistaVerComic;
    }

    public ControladorVerComic(ControladorVentanaPrincipal controladorVentanaPrincipal) {
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
            valoracionMedia = seleccionarComic();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error con la bdd contacte con el administrador", "Error inesperado", JOptionPane.ERROR_MESSAGE);
        }
          int numerosTotales=serieSeleccionada.getNumero();
//obtenemos datos
        DefaultComboBoxModel MiModelo= new DefaultComboBoxModel();
        vistaVerComic.getjLabelTitulo().setText(serieSeleccionada.getTitulo());
        vistaVerComic.getjLabelTextDirector().setText(serieSeleccionada.getAutor());
        vistaVerComic.getjLabelTextProductora().setText(serieSeleccionada.getEditorial());
        vistaVerComic.getjLabelTextLanzamiento().setText(serieSeleccionada.getLanzamiento());
        vistaVerComic.getjLabelTextGenero().setText(serieSeleccionada.getGenero());
        vistaVerComic.getjTextAreaDescripcion().setText(serieSeleccionada.getDescripcion());
        vistaVerComic.getjLabelTextCapitulosTotales().setText("/"+numerosTotales);
        for (int i=0; i<=numerosTotales;i++)
        { 
            String numero=""+i;
        MiModelo.addElement(numero);
        
        }
        MiModelo.setSelectedItem(serieSeleccionada.getNumeros_vistos());
        vistaVerComic.getjComboBoxCapitulosVistos().setModel(MiModelo);
        //comprobamos que sea correcta la valoracion
        if (valoracionMedia == 10) {
            vistaVerComic.getjLabelTextValoracionMedia().setText("No hay valoraciones");
            vistaVerComic.getjLabelTextValoracionMedia().setForeground(Color.black);
        } else {
            //escribimos el texto y aplicamos el formato
            vistaVerComic.getjLabelTextValoracionMedia().setText(formateador.format(valoracionMedia) + "/5");
            //comprobamos el valor para seleccionar rojo si es menor a 2.5
            if (valoracionMedia < 2.5) {
                vistaVerComic.getjLabelTextValoracionMedia().setForeground(Color.red);
            }
        }
        //comprobamos si esta recomendado o no
        if (serieSeleccionada.isRecomendacion()) {
            vistaVerComic.getjRadioButtonRecomendarSi().setSelected(true);
        } else {
            vistaVerComic.getjRadioButtonRecomendarNo().setSelected(true);
        }
        //segun la valoracion seleccionaremos un boton
        switch (serieSeleccionada.getValoracion()) {
            case 0:
                vistaVerComic.getjRadioButtonValor0().setSelected(true);
                break;
            case 1:
                vistaVerComic.getjRadioButtonValor1().setSelected(true);
                break;
            case 2:
                vistaVerComic.getjRadioButtonValor2().setSelected(true);
                break;
            case 3:
                vistaVerComic.getjRadioButtonValor3().setSelected(true);
                break;
            case 4:
                vistaVerComic.getjRadioButtonValor4().setSelected(true);
                break;
            case 5:
                vistaVerComic.getjRadioButtonValor5().setSelected(true);
                break;
            default:

                break;

        }

    }

    public double seleccionarComic() throws SQLException {
        double valoracionMedia;
        //mandamos ha hacer la consulta con el titulo y el objeto serie que usaremos
        serieSeleccionada = ComicsDAO.devolverComicsDAO().ComicsVerComic(serieSeleccionada);
        //recogemos el valor de la valoracion media
        valoracionMedia = ComicsDAO.devolverComicsDAO().valoracionMediaComic();
        return valoracionMedia;
    }

    public  void abrirVerComic(JFrame jFrame, String tituloSeleccionado) {
        ComicsDAO.devolverComicsDAO().setTitulo(tituloSeleccionado);
        VerComic nuevaVerComic=new VerComic(jFrame, true);
           nuevaVerComic.setLocationRelativeTo(null);
        nuevaVerComic.setVisible(true);
      
    }
    public boolean guardarActualizacion (String valoracion,int recomendado,String numerosVistos) throws SQLException{
    boolean correcto=ComicsDAO.devolverComicsDAO().actualizarComicDeMiLista(valoracion,recomendado, numerosVistos);
    return correcto;
    
    }

    
    

}
