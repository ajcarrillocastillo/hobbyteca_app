/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.controlador;

import hobbyteca.DAO.ComicsDAO;
import hobbyteca.vista.AgregarMiListaComics;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;

/**
 *
 * @author jesus
 */
public class ControladorAgregarComic {

    private final AgregarMiListaComics vistaAgregarComics;

    public ControladorAgregarComic(AgregarMiListaComics vistaComics) {
        this.vistaAgregarComics = vistaComics;
    }

    public DefaultListModel LLenarLista(JList jlistComics) throws SQLException {
        DefaultListModel lis = new DefaultListModel();
        jlistComics.setModel(lis);
        String columna;
        int numRegistros = ComicsDAO.devolverComicsDAO().anadirListaComic().size();
        for (int i = 0; i < numRegistros; i++) {
            columna = ComicsDAO.devolverComicsDAO().anadirListaComic().get(i).getTitulo();

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
        String letras = "" + vistaAgregarComics.getjTextFieldBuscador().getText();
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
        vistaAgregarComics.getjListBuscador().setModel(jList);
    }

    public boolean insertarComicMiLista(String nombreComic) throws SQLException {
        boolean guardado = ComicsDAO.devolverComicsDAO().insertarAMiListaComic(nombreComic);
        //recargarTabla();
        return guardado;
    }

    public static void AbrirAgregarComicMiLista(JFrame jFrame) {
        AgregarMiListaComics nuevaAgregarMiListaComics=new AgregarMiListaComics(jFrame, true);
           nuevaAgregarMiListaComics.setLocationRelativeTo(null);
        nuevaAgregarMiListaComics.setVisible(true);
    }

}
