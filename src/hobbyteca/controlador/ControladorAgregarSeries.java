/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.controlador;

import hobbyteca.DAO.SeriesDAO;
import hobbyteca.vista.AgregarMiListaSeries;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;

/**
 *
 * @author jesus
 */
public class ControladorAgregarSeries {

    private final AgregarMiListaSeries vistaAgregarSeries;

    public ControladorAgregarSeries(AgregarMiListaSeries vistaSeries) {
        this.vistaAgregarSeries = vistaSeries;
    }

    public DefaultListModel LLenarLista(JList jlistSeries) throws SQLException {
        DefaultListModel lis = new DefaultListModel();
        jlistSeries.setModel(lis);
        String columna;
        int numRegistros = SeriesDAO.devolverSeriesDAO().anadirListaSerie().size();
        for (int i = 0; i < numRegistros; i++) {
            columna = SeriesDAO.devolverSeriesDAO().anadirListaSerie().get(i).getTitulo();

            lis.addElement(columna); //Agregamos la fila del registro
        }
        return lis;
    }

    
    /**
     * 
     * Filtro para filtrar los nombres 
     * 
     * @param jList
     * @param datosList
     * @param tamannoLista 
     */
    public void filtrarLista(DefaultListModel jList, String[] datosList, int tamannoLista) {

//Capturamos el valor de la caja de texto en la variable letras 
        String letras = "" + vistaAgregarSeries.getjTextFieldBuscador().getText();
        //creamos un listModel
        jList = new DefaultListModel();
//recorremos nuestro arreglo 

//evaluamos si las letras ingresadas estan contendidas 
//dentro de nuestro arreglo de palabras 
        for (int i = 0; i < tamannoLista; i++) {
            if (datosList[i].contains(letras)) {
// si SI estan esas letras almacena la palabra en lis 
                jList.addElement(datosList[i]);
            }
        }
//coloca dentro de la Jlist lo que haya encontrado sino encontó aparecerá vacio 
        vistaAgregarSeries.getjListBuscador().setModel(jList);
    }

    public boolean insertarSerieMiLista(String nombreSerie) throws SQLException {
        boolean guardado = SeriesDAO.devolverSeriesDAO().insertarAMiListaSerie(nombreSerie);
        //recargarTabla();
        return guardado;
    }

    public static void AbrirAgregarSerieMiLista(JFrame jFrame) {
        AgregarMiListaSeries nuevaAgregarMiListaSeries=new AgregarMiListaSeries(jFrame, true);
           nuevaAgregarMiListaSeries.setLocationRelativeTo(null);
        nuevaAgregarMiListaSeries.setVisible(true);
    }

}
