/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.controlador;

import hobbyteca.DAO.PeliculasDAO;
import hobbyteca.vista.AgregarMiListaPeliculas;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;

/**
 *
 * @author jesus
 */
public class ControladorAgregarPeliculas {

    private final AgregarMiListaPeliculas vistaAgregarPeliculas;

    public ControladorAgregarPeliculas(AgregarMiListaPeliculas vistaPeliculas) {
        this.vistaAgregarPeliculas = vistaPeliculas;
    }

    public DefaultListModel LLenarLista(JList jlistPeliculas) throws SQLException {
        DefaultListModel lis = new DefaultListModel();
        jlistPeliculas.setModel(lis);
        String columna;
        int numRegistros = PeliculasDAO.devolverPeliculasDAO().anadirListaPelicula().size();
        for (int i = 0; i < numRegistros; i++) {
            columna = PeliculasDAO.devolverPeliculasDAO().anadirListaPelicula().get(i).getTitulo();

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
        String letras = "" + vistaAgregarPeliculas.getjTextFieldBuscador().getText();
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
        vistaAgregarPeliculas.getjListBuscador().setModel(jList);
    }

    public boolean insertarPeliculaMiLista(String nombrePelicula) throws SQLException {
        boolean guardado = PeliculasDAO.devolverPeliculasDAO().insertarAMiListaPelicula(nombrePelicula);
        //recargarTabla();
        return guardado;
    }

    public static void AbrirAgregarPeliculaMiLista(JFrame jFrame) {
        AgregarMiListaPeliculas nuevaAgregarMiListaPeliculas=new AgregarMiListaPeliculas(jFrame, true);
           nuevaAgregarMiListaPeliculas.setLocationRelativeTo(null);
        nuevaAgregarMiListaPeliculas.setVisible(true);
    }

}
