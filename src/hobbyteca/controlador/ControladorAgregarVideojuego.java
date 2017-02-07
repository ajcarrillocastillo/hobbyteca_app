/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.controlador;

import hobbyteca.DAO.VideojuegoDAO;
import hobbyteca.vista.AgregarMiListaVideojuego;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;

/**
 *
 * @author jesus
 */
public class ControladorAgregarVideojuego {

    private final AgregarMiListaVideojuego vistaAgregarMiListaVideojuego;

    public ControladorAgregarVideojuego(AgregarMiListaVideojuego vistaVideojuegos) {
        this.vistaAgregarMiListaVideojuego = vistaVideojuegos;
    }

    public DefaultListModel LLenarLista(JList jListVideojuegos) throws SQLException {
        DefaultListModel lis = new DefaultListModel();
        jListVideojuegos.setModel(lis);
        String columna;
        int numRegistros = VideojuegoDAO.devolverVideojuegosDAO().anadirListaVideojuego().size();
        for (int i = 0; i < numRegistros; i++) {
            columna = VideojuegoDAO.devolverVideojuegosDAO().anadirListaVideojuego().get(i).getTitulo();

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
        String letras = "" + vistaAgregarMiListaVideojuego.getjTextFieldBuscador().getText();
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
        vistaAgregarMiListaVideojuego.getjListBuscador().setModel(jList);
    }

    public boolean insertarVideojuegoMiLista(String nombreVideojuego) throws SQLException {
        boolean guardado = VideojuegoDAO.devolverVideojuegosDAO().insertarAMiListaVideojuegos(nombreVideojuego);
        //recargarTabla();
        return guardado;
    }

    public static void AbrirAgregarVideojuegoMiLista(JFrame jFrame) {
        AgregarMiListaVideojuego nuevaAgregarMiListaVideojuegos=new AgregarMiListaVideojuego(jFrame, true);
           nuevaAgregarMiListaVideojuegos.setLocationRelativeTo(null);
        nuevaAgregarMiListaVideojuegos.setVisible(true);
    }

}
