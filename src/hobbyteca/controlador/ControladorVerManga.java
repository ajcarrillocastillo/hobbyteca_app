/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.controlador;

import hobbyteca.DAO.MangasDAO;
import hobbyteca.modelo.Manga;
import hobbyteca.vista.VerManga;
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
public class ControladorVerManga  {

    private VerManga vistaVerManga;
    private ControladorVentanaPrincipal controladorVentanaPrincipal;
    private Manga serieSeleccionada = new Manga();
    private String tituloSeleccionado;



    public ControladorVerManga(VerManga vistaVerManga) {
        this.vistaVerManga = vistaVerManga;
    }

    public ControladorVerManga(ControladorVentanaPrincipal controladorVentanaPrincipal) {
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
            valoracionMedia = seleccionarManga();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error con la bdd contacte con el administrador", "Error inesperado", JOptionPane.ERROR_MESSAGE);
        }
          int tomosTotales=serieSeleccionada.getTomo();
//obtenemos datos
        DefaultComboBoxModel MiModelo= new DefaultComboBoxModel();
        vistaVerManga.getjLabelTitulo().setText(serieSeleccionada.getTitulo());
        vistaVerManga.getjLabelTextDirector().setText(serieSeleccionada.getAutor());
        vistaVerManga.getjLabelTextProductora().setText(serieSeleccionada.getEditorial());
        vistaVerManga.getjLabelTextLanzamiento().setText(serieSeleccionada.getLanzamiento());
        vistaVerManga.getjLabelTextGenero().setText(serieSeleccionada.getGenero());
        vistaVerManga.getjTextAreaDescripcion().setText(serieSeleccionada.getDescripcion());
        vistaVerManga.getjLabelTextCapitulosTotales().setText("/"+tomosTotales);
        for (int i=0; i<=tomosTotales;i++)
        { 
            String tomo=""+i;
        MiModelo.addElement(tomo);
        
        }
        MiModelo.setSelectedItem(serieSeleccionada.getTomos_vistos());
        vistaVerManga.getjComboBoxCapitulosVistos().setModel(MiModelo);
        //comprobamos que sea correcta la valoracion
        if (valoracionMedia == 10) {
            vistaVerManga.getjLabelTextValoracionMedia().setText("No hay valoraciones");
            vistaVerManga.getjLabelTextValoracionMedia().setForeground(Color.black);
        } else {
            //escribimos el texto y aplicamos el formato
            vistaVerManga.getjLabelTextValoracionMedia().setText(formateador.format(valoracionMedia) + "/5");
            //comprobamos el valor para seleccionar rojo si es menor a 2.5
            if (valoracionMedia < 2.5) {
                vistaVerManga.getjLabelTextValoracionMedia().setForeground(Color.red);
            }
        }
        //comprobamos si esta recomendado o no
        if (serieSeleccionada.isRecomendacion()) {
            vistaVerManga.getjRadioButtonRecomendarSi().setSelected(true);
        } else {
            vistaVerManga.getjRadioButtonRecomendarNo().setSelected(true);
        }
        //segun la valoracion seleccionaremos un boton
        switch (serieSeleccionada.getValoracion()) {
            case 0:
                vistaVerManga.getjRadioButtonValor0().setSelected(true);
                break;
            case 1:
                vistaVerManga.getjRadioButtonValor1().setSelected(true);
                break;
            case 2:
                vistaVerManga.getjRadioButtonValor2().setSelected(true);
                break;
            case 3:
                vistaVerManga.getjRadioButtonValor3().setSelected(true);
                break;
            case 4:
                vistaVerManga.getjRadioButtonValor4().setSelected(true);
                break;
            case 5:
                vistaVerManga.getjRadioButtonValor5().setSelected(true);
                break;
            default:

                break;

        }

    }

    public double seleccionarManga() throws SQLException {
        double valoracionMedia;
        //mandamos ha hacer la consulta con el titulo y el objeto serie que usaremos
        serieSeleccionada = MangasDAO.devolverMangasDAO().MangasVerManga(serieSeleccionada);
        //recogemos el valor de la valoracion media
        valoracionMedia = MangasDAO.devolverMangasDAO().valoracionMediaManga();
        return valoracionMedia;
    }

    public  void abrirVerManga(JFrame jFrame, String tituloSeleccionado) {
        MangasDAO.devolverMangasDAO().setTitulo(tituloSeleccionado);
        VerManga nuevaVerManga=new VerManga(jFrame, true);
           nuevaVerManga.setLocationRelativeTo(null);
        nuevaVerManga.setVisible(true);
      
    }
    public boolean guardarActualizacion (String valoracion,int recomendado,String tomosVistos) throws SQLException{
    boolean correcto=MangasDAO.devolverMangasDAO().actualizarMangaDeMiLista(valoracion,recomendado, tomosVistos);
    return correcto;
    
    }

    
    

}
