/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.controlador;

import hobbyteca.DAO.MusicaDAO;
import hobbyteca.vista.AgregarMiListaMusica;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author jesus
 */
public class ControladorAgregarMusica {

    private final AgregarMiListaMusica vistaAgregarMusica;
    private TableRowSorter trsFiltro;

    public ControladorAgregarMusica(AgregarMiListaMusica vistaMusica) {
        this.vistaAgregarMusica = vistaMusica;
    }
    public void CrearTablaMusica(TableModelNoEditable modeloTabla) throws SQLException {

        modeloTabla.addColumn("Titulo");
        modeloTabla.addColumn("ASIN");
    }

public void LLenarTablaMusica(TableModelNoEditable modeloTabla) throws SQLException {
        
      while(modeloTabla.getRowCount()>0){
        modeloTabla.removeRow(0);
        }
        Object[] columna = new Object[2];
        int numRegistros = MusicaDAO.devolverMusicaDAO().AgregarMusicaMilista().size();

        for (int i = 0; i < numRegistros; i++) {
            columna[0] = MusicaDAO.devolverMusicaDAO().AgregarMusicaMilista().get(i).getTitulo();
            columna[1] = MusicaDAO.devolverMusicaDAO().AgregarMusicaMilista().get(i).getASIN();

            modeloTabla.addRow(columna); //Agregamos la fila del registro
        }
    }
/**
 * 
 * @param cuadroBusqueda
 * @param miTabla 
 */
    public void buscador(JTextField cuadroBusqueda, JTable miTabla) {
        cuadroBusqueda.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                String cadena = (cuadroBusqueda.getText());
                cuadroBusqueda.setText(cadena);
                miTabla.repaint();
                filtro(cuadroBusqueda);
            }
        });
        trsFiltro = new TableRowSorter(miTabla.getModel());
        miTabla.setRowSorter(trsFiltro);
        trsFiltro.setRowFilter(RowFilter.regexFilter(cuadroBusqueda.getText(), 0));

    }
        public void filtro(JTextField cuadroBusqueda) {

        trsFiltro.setRowFilter(RowFilter.regexFilter(cuadroBusqueda.getText(), 0));
    }
    
   

    public boolean insertarMusicaMiLista(String ASINMusica) throws SQLException {
        boolean guardado = MusicaDAO.devolverMusicaDAO().insertarAMiListaMusica(ASINMusica);
        //recargarTabla();
        return guardado;
    }

    public static void AbrirAgregarMusicaMiLista(JFrame jFrame) {
        AgregarMiListaMusica nuevaAgregarMiListaMusica=new AgregarMiListaMusica(jFrame, true);
           nuevaAgregarMiListaMusica.setLocationRelativeTo(null);
        nuevaAgregarMiListaMusica.setVisible(true);
    }

}
