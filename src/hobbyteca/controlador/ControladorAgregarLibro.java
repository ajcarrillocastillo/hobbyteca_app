/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.controlador;

import hobbyteca.DAO.LibroDAO;
import hobbyteca.vista.AgregarMiListaLibro;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;

/**
 *
 * @author jesus
 */
public class ControladorAgregarLibro {

    private final AgregarMiListaLibro vistaAgregarLibro;

    public ControladorAgregarLibro(AgregarMiListaLibro vistaLibro) {
        this.vistaAgregarLibro = vistaLibro;
    }

    public DefaultListModel LLenarLista(JList jlistLibros) throws SQLException {
        DefaultListModel lis = new DefaultListModel();
        jlistLibros.setModel(lis);
        String columna;
        int numRegistros = LibroDAO.devolverLibrosDAO().anadirListaLibro().size();
        for (int i = 0; i < numRegistros; i++) {
            columna = LibroDAO.devolverLibrosDAO().anadirListaLibro().get(i).getTitulo();
                    
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
        String letras = "" + vistaAgregarLibro.getjTextFieldBuscador().getText();
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
        vistaAgregarLibro.getjListBuscador().setModel(jList);
    }

    public boolean insertarLibroMiLista(String nombreLibro) throws SQLException {
        boolean guardado = LibroDAO.devolverLibrosDAO().insertarAMiListaLibro(nombreLibro);
        //recargarTabla();
        return guardado;
    }

    public static void AbrirAgregarLibroMiLista(JFrame jFrame) {
        AgregarMiListaLibro nuevaAgregarMiListaLibro = new AgregarMiListaLibro(jFrame, true);
        nuevaAgregarMiListaLibro.setLocationRelativeTo(null);
        nuevaAgregarMiListaLibro.setVisible(true);
    }

}
