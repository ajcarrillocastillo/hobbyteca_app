/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.controlador;

import hobbyteca.DAO.MangasDAO;
import hobbyteca.vista.AgregarMiListaMangas;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;

/**
 *
 * @author jesus
 */
public class ControladorAgregarManga {

    private final AgregarMiListaMangas vistaAgregarMangas;

    public ControladorAgregarManga(AgregarMiListaMangas vistaMangas) {
        this.vistaAgregarMangas = vistaMangas;
    }

    public DefaultListModel LLenarLista(JList jlistMangas) throws SQLException {
        DefaultListModel lis = new DefaultListModel();
        jlistMangas.setModel(lis);
        String columna;
        int numRegistros = MangasDAO.devolverMangasDAO().anadirListaManga().size();
        for (int i = 0; i < numRegistros; i++) {
            columna = MangasDAO.devolverMangasDAO().anadirListaManga().get(i).getTitulo();

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
        String letras = "" + vistaAgregarMangas.getjTextFieldBuscador().getText();
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
        vistaAgregarMangas.getjListBuscador().setModel(jList);
    }

    public boolean insertarMangaMiLista(String nombreManga) throws SQLException {
        boolean guardado = MangasDAO.devolverMangasDAO().insertarAMiListaManga(nombreManga);
        //recargarTabla();
        return guardado;
    }

    public static void AbrirAgregarMangaMiLista(JFrame jFrame) {
        AgregarMiListaMangas nuevaAgregarMiListaMangas=new AgregarMiListaMangas(jFrame, true);
           nuevaAgregarMiListaMangas.setLocationRelativeTo(null);
        nuevaAgregarMiListaMangas.setVisible(true);
    }

}
