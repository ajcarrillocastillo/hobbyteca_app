/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.controlador;

import hobbyteca.DAO.ComicsDAO;
import hobbyteca.DAO.LibroDAO;
import hobbyteca.DAO.MangasDAO;
import hobbyteca.DAO.MusicaDAO;
import hobbyteca.DAO.PeliculasDAO;
import hobbyteca.DAO.SeriesDAO;
import hobbyteca.vista.Principal;
import hobbyteca.DAO.NombreUsuarioMenu;
import hobbyteca.DAO.VideojuegoDAO;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author jesus
 */
public class ControladorVentanaPrincipal {

    private Principal vistaVentanaPrincipal;
    private NombreUsuarioMenu ventanaPrincipalDAO;
    private final ConexionControlador controladorConexion = new ConexionControlador();
    private TableRowSorter trsFiltro;
    private final NombreUsuarioMenu nuevaVentanaPrincipal = new NombreUsuarioMenu();
    private final ControladorVerPelicula controladorVerPelicula = new ControladorVerPelicula(this);
    private final ControladorVerSerie controladorVerSerie = new ControladorVerSerie(this);
    private final ControladorVerManga controladorVerManga = new ControladorVerManga(this);
    private final ControladorVerComic controladorVerComic = new ControladorVerComic(this);
    private final ControladorVerLibro controladorVerLibro = new ControladorVerLibro(this);
    private final ControladorVerVideojuego controladorVerVideojuego = new ControladorVerVideojuego(this);
    private final ControladorVerMusica controladorVerMusica = new ControladorVerMusica(this);

    public ControladorVentanaPrincipal(Principal vistaventanaPrincipal) {
        this.vistaVentanaPrincipal = vistaVentanaPrincipal;

    }

    public void recogeNombreUsuarioLogueado(String nombreUsuario) {

        nuevaVentanaPrincipal.setNombreUsuario(nombreUsuario);
        //enviamos el usuario a guardar
        PeliculasDAO.devolverPeliculasDAO().setUsuario(nombreUsuario);
        SeriesDAO.devolverSeriesDAO().setUsuario(nombreUsuario);
        VideojuegoDAO.devolverVideojuegosDAO().setUsuario(nombreUsuario);
        MusicaDAO.devolverMusicaDAO().setUsuario(nombreUsuario);
        MangasDAO.devolverMangasDAO().setUsuario(nombreUsuario);
        LibroDAO.devolverLibrosDAO().setUsuario(nombreUsuario);
        ComicsDAO.devolverComicsDAO().setUsuario(nombreUsuario);
    }

    public void abrirVetananaPrincipal() throws SQLException {
        Principal nuevaVistaVentanaPrincipal = new Principal();
        nuevaVistaVentanaPrincipal.setLocationRelativeTo(null);
        nuevaVistaVentanaPrincipal.setVisible(true);
        nuevaVistaVentanaPrincipal.getjMenuUsuario().setText(getNombreusuario());

    }

    public String getNombreusuario() {
        String usuario = nuevaVentanaPrincipal.getNombreUsuario();
        return usuario;
    }

    /**
     *
     * @throws SQLException
     */
    public void cerrarConexion() throws SQLException {
        controladorConexion.cerrarConexion();
    }

    /**
     *
     */
    public void logout() {
        int result = JOptionPane.showConfirmDialog(vistaVentanaPrincipal,
                "¿Estas seguro de que quieres salir?", "Logout", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            try {
                controladorConexion.cerrarConexion();
                // TODO add your handling code here:
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error con la bdd contacte con el administrador", "Error inesperado", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.exit(0);
        }
    }

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
    public void CrearTablaPeliculas(TableModelNoEditable modeloTabla) {

        
        modeloTabla.addColumn("Titulo");
        modeloTabla.addColumn("Lanzamiento");
        modeloTabla.addColumn("Genero");
        modeloTabla.addColumn("Valoracion");
        modeloTabla.addColumn("Visto");
        modeloTabla.addColumn("");
    }
    public void LLenarTablaPeliculas(TableModelNoEditable modeloTabla) throws SQLException {
//borra los registros de la tabla y los vuelve a rellenar
        while(modeloTabla.getRowCount()>0){
            modeloTabla.removeRow(0);
        }
        Object[] columna = new Object[6];
        int numRegistros = PeliculasDAO.devolverPeliculasDAO().listPeliculaMiLista().size();

        for (int i = 0; i < numRegistros; i++) {
            columna[0] = PeliculasDAO.devolverPeliculasDAO().listPeliculaMiLista().get(i).getTitulo();
            columna[1] = PeliculasDAO.devolverPeliculasDAO().listPeliculaMiLista().get(i).getLanzamiento();
            columna[2] = PeliculasDAO.devolverPeliculasDAO().listPeliculaMiLista().get(i).getGenero();
            columna[3] = PeliculasDAO.devolverPeliculasDAO().listPeliculaMiLista().get(i).getValoracion();
            if (PeliculasDAO.devolverPeliculasDAO().listPeliculaMiLista().get(i).getVisualizado()) {
                columna[4] = "Sí";
            } else {
                columna[4] = "No";
            }
            columna[5] = "borrar";
            modeloTabla.addRow(columna); //Agregamos la fila del registro
        }

    }
   
     public void CrearTablaSeries(TableModelNoEditable modeloTabla)  {
        modeloTabla.addColumn("Titulo");
        modeloTabla.addColumn("Lanzamiento");
        modeloTabla.addColumn("Genero");
        modeloTabla.addColumn("Valoracion");
        modeloTabla.addColumn("Capitulos Vistos");
        modeloTabla.addColumn("Capitulos Totales");
        modeloTabla.addColumn("");}
     
    public void LLenarTablaSeries(TableModelNoEditable modeloTabla) throws SQLException {
        while(modeloTabla.getRowCount()>0){
        modeloTabla.removeRow(0);
        }
        Object[] columna = new Object[7];
        int numRegistros = SeriesDAO.devolverSeriesDAO().listSerieMiLista().size();

        for (int i = 0; i < numRegistros; i++) {
            columna[0] = SeriesDAO.devolverSeriesDAO().listSerieMiLista().get(i).getTitulo();
            columna[1] = SeriesDAO.devolverSeriesDAO().listSerieMiLista().get(i).getLanzamiento();
            columna[2] = SeriesDAO.devolverSeriesDAO().listSerieMiLista().get(i).getGenero();
            columna[3] = SeriesDAO.devolverSeriesDAO().listSerieMiLista().get(i).getValoracion();
            columna[4] = SeriesDAO.devolverSeriesDAO().listSerieMiLista().get(i).getCapitulos_vistos();
            columna[5] = SeriesDAO.devolverSeriesDAO().listSerieMiLista().get(i).getCapitulos_totales();
            columna[6] = "borrar";
            modeloTabla.addRow(columna); //Agregamos la fila del registro
        }
    }
        public void CrearTablaVideojuego(TableModelNoEditable modeloTabla) {

        modeloTabla.addColumn("Titulo");
        modeloTabla.addColumn("Lanzamiento");
        modeloTabla.addColumn("Genero");
        modeloTabla.addColumn("Valoracion");
        modeloTabla.addColumn("Jugados");
        modeloTabla.addColumn("");
        }
    public void LLenarTablaVideojuego(TableModelNoEditable modeloTabla) throws SQLException {
        while(modeloTabla.getRowCount()>0){
        modeloTabla.removeRow(0);
        }

        Object[] columna = new Object[6];
        int numRegistros = VideojuegoDAO.devolverVideojuegosDAO().listVideojuegoMiLista().size();

        for (int i = 0; i < numRegistros; i++) {
            columna[0] = VideojuegoDAO.devolverVideojuegosDAO().listVideojuegoMiLista().get(i).getTitulo();
            columna[1] = VideojuegoDAO.devolverVideojuegosDAO().listVideojuegoMiLista().get(i).getLanzamiento();
            columna[2] = VideojuegoDAO.devolverVideojuegosDAO().listVideojuegoMiLista().get(i).getGenero();
            columna[3] = VideojuegoDAO.devolverVideojuegosDAO().listVideojuegoMiLista().get(i).getValoracion();
            if (VideojuegoDAO.devolverVideojuegosDAO().listVideojuegoMiLista().get(i).getJugado()) {
                columna[4] = "Sí";
            } else {
                columna[4] = "No";
            }
            columna[5] = "borrar";
            modeloTabla.addRow(columna); //Agregamos la fila del registro
        }
    }
        public void CrearTablaMusica(TableModelNoEditable modeloTabla){
        modeloTabla.addColumn("Titulo");
        modeloTabla.addColumn("ASIN");
        modeloTabla.addColumn("Lanzamiento");
        modeloTabla.addColumn("Genero");
        modeloTabla.addColumn("Valoracion");
        modeloTabla.addColumn("Escuchado");
        modeloTabla.addColumn("");
        }
    public void LLenarTablaMusica(TableModelNoEditable modeloTabla) throws SQLException {
        while(modeloTabla.getRowCount()>0){
        modeloTabla.removeRow(0);
        }

        Object[] columna = new Object[7];
        int numRegistros = MusicaDAO.devolverMusicaDAO().listMusicaMiLista().size();

        for (int i = 0; i < numRegistros; i++) {
            columna[0] = MusicaDAO.devolverMusicaDAO().listMusicaMiLista().get(i).getTitulo();
            columna[1] = MusicaDAO.devolverMusicaDAO().listMusicaMiLista().get(i).getASIN();
            columna[2] = MusicaDAO.devolverMusicaDAO().listMusicaMiLista().get(i).getLanzamiento();
            columna[3] = MusicaDAO.devolverMusicaDAO().listMusicaMiLista().get(i).getGenero();
            columna[4] = MusicaDAO.devolverMusicaDAO().listMusicaMiLista().get(i).getValoracion();
            if (MusicaDAO.devolverMusicaDAO().listMusicaMiLista().get(i).isEscuchado()) {
                columna[5] = "Sí";
            } else {
                columna[5] = "No";
            }
            columna[6] = "borrar";
            modeloTabla.addRow(columna); //Agregamos la fila del registro
        }
    }
       public void CrearTablaManga(TableModelNoEditable modeloTabla)  {
        modeloTabla.addColumn("Titulo");
        modeloTabla.addColumn("Lanzamiento");
        modeloTabla.addColumn("Genero");
        modeloTabla.addColumn("Valoracion");
        modeloTabla.addColumn("Tomos Leidos");
        modeloTabla.addColumn("Tomos Totales");
        modeloTabla.addColumn("");
       }

    public void LLenarTablaManga(TableModelNoEditable modeloTabla) throws SQLException {
               while(modeloTabla.getRowCount()>0){
        modeloTabla.removeRow(0);
        }

        Object[] columna = new Object[7];
        int numRegistros = MangasDAO.devolverMangasDAO().listMangaMiLista().size();

        for (int i = 0; i < numRegistros; i++) {
            columna[0] = MangasDAO.devolverMangasDAO().listMangaMiLista().get(i).getTitulo();
            columna[1] = MangasDAO.devolverMangasDAO().listMangaMiLista().get(i).getLanzamiento();
            columna[2] = MangasDAO.devolverMangasDAO().listMangaMiLista().get(i).getGenero();
            columna[3] = MangasDAO.devolverMangasDAO().listMangaMiLista().get(i).getValoracion();
            columna[4] = MangasDAO.devolverMangasDAO().listMangaMiLista().get(i).getTomos_vistos();
            columna[5] = MangasDAO.devolverMangasDAO().listMangaMiLista().get(i).getTomo();
            columna[6] = "borrar";
            modeloTabla.addRow(columna); //Agregamos la fila del registro
        }
    }
    public void CrearTablaLibro(TableModelNoEditable modeloTabla) throws SQLException {

        modeloTabla.addColumn("Titulo");
        modeloTabla.addColumn("Lanzamiento");
        modeloTabla.addColumn("Genero");
        modeloTabla.addColumn("Valoracion");
        modeloTabla.addColumn("Leído");
        modeloTabla.addColumn("");
    }

    public void LLenarTablaLibro(TableModelNoEditable modeloTabla) throws SQLException {
        while(modeloTabla.getRowCount()>0){
        modeloTabla.removeRow(0);
        }
        Object[] columna = new Object[6];
        int numRegistros = LibroDAO.devolverLibrosDAO().listLibroMiLista().size();

        for (int i = 0; i < numRegistros; i++) {
            columna[0] = LibroDAO.devolverLibrosDAO().listLibroMiLista().get(i).getTitulo();
            columna[1] = LibroDAO.devolverLibrosDAO().listLibroMiLista().get(i).getLanzamiento();
            columna[2] = LibroDAO.devolverLibrosDAO().listLibroMiLista().get(i).getGenero();
            columna[3] = LibroDAO.devolverLibrosDAO().listLibroMiLista().get(i).getValoracion();
            if (LibroDAO.devolverLibrosDAO().listLibroMiLista().get(i).isVisualizado()) {
                columna[4] = "Sí";
            } else {
                columna[4] = "No";
            }
            columna[5] = "borrar";
            modeloTabla.addRow(columna); //Agregamos la fila del registro
        }
    }
        public void CrearTablaComic(TableModelNoEditable modeloTabla) {
        modeloTabla.addColumn("Titulo");
        modeloTabla.addColumn("Lanzamiento");
        modeloTabla.addColumn("Genero");
        modeloTabla.addColumn("Valoracion");
        modeloTabla.addColumn("Numeros Leidos");
        modeloTabla.addColumn("Numeros Totales");
        modeloTabla.addColumn("");
        }
    public void LLenarTablaComic(TableModelNoEditable modeloTabla) throws SQLException {
        while(modeloTabla.getRowCount()>0){
        modeloTabla.removeRow(0);
        }
        Object[] columna = new Object[7];
        int numRegistros = ComicsDAO.devolverComicsDAO().listComicMiLista().size();

        for (int i = 0; i < numRegistros; i++) {
            columna[0] = ComicsDAO.devolverComicsDAO().listComicMiLista().get(i).getTitulo();
            columna[1] = ComicsDAO.devolverComicsDAO().listComicMiLista().get(i).getLanzamiento();
            columna[2] = ComicsDAO.devolverComicsDAO().listComicMiLista().get(i).getGenero();
            columna[3] = ComicsDAO.devolverComicsDAO().listComicMiLista().get(i).getValoracion();
            columna[4] = ComicsDAO.devolverComicsDAO().listComicMiLista().get(i).getNumeros_vistos();
            columna[5] = ComicsDAO.devolverComicsDAO().listComicMiLista().get(i).getNumero();
            columna[6] = "borrar";
            modeloTabla.addRow(columna); //Agregamos la fila del registro
        }
    }

    public void AbrirAñadirPelicula() {
        ControladorAñadirPelicula.AbrirAnnadirPelicula(vistaVentanaPrincipal);
    }

    /**
     * Llama a abrir ver pelicula
     *
     * @param titulo
     */
    public void AbrirVerPelicula(String titulo) {
        controladorVerPelicula.abrirVerPelicula(vistaVentanaPrincipal, titulo);
    }

    /**
     * Llama a abrir ver libro
     *
     * @param titulo
     */
    public void AbrirPeliculaMiLista() {
        ControladorAgregarPeliculas.AbrirAgregarPeliculaMiLista(vistaVentanaPrincipal);
    }

    public void borrarPeliculaMiLista(String titulo) throws SQLException {

        PeliculasDAO.devolverPeliculasDAO().setTitulo(titulo);
        PeliculasDAO.devolverPeliculasDAO().borrarPeliculaDeMiLista();
    }

    //Manga
    public void AbrirVerManga(String titulo) {
        controladorVerManga.abrirVerManga(vistaVentanaPrincipal, titulo);
    }

    public void AbrirMangaMiLista() {
        ControladorAgregarManga.AbrirAgregarMangaMiLista(vistaVentanaPrincipal);
    }

    public void borrarMangaMiLista(String titulo) throws SQLException {

        MangasDAO.devolverMangasDAO().setTitulo(titulo);
        MangasDAO.devolverMangasDAO().borrarMangasDeMiLista();
    }

    public void AbrirAñadirManga() {
        ControladorAñadirManga.AbrirAnnadirManga(vistaVentanaPrincipal);
    }

    //Comic
    public void AbrirVerComic(String titulo) {
        controladorVerComic.abrirVerComic(vistaVentanaPrincipal, titulo);
    }

    public void AbrirComicMiLista() {
        ControladorAgregarComic.AbrirAgregarComicMiLista(vistaVentanaPrincipal);
    }

    public void borrarComicMiLista(String titulo) throws SQLException {

        ComicsDAO.devolverComicsDAO().setTitulo(titulo);
        ComicsDAO.devolverComicsDAO().borrarComicsDeMiLista();
    }

    public void AbrirAñadirComic() {
        ControladorAñadirComic.AbrirAnnadirComic(vistaVentanaPrincipal);
    }

//Serie
    public void AbrirAñadirSerie() {
        ControladorAñadirSeries.AbrirAnnadirSerie(vistaVentanaPrincipal);
    }

    public void AbrirVerSerie(String titulo) {
        controladorVerSerie.abrirVerSerie(vistaVentanaPrincipal, titulo);
    }

    public void AbrirSerieMiLista() {
        ControladorAgregarSeries.AbrirAgregarSerieMiLista(vistaVentanaPrincipal);

    }

    public void borrarSerieMiLista(String titulo) throws SQLException {

        SeriesDAO.devolverSeriesDAO().setTitulo(titulo);
        SeriesDAO.devolverSeriesDAO().borrarSeriesDeMiLista();
    }

    //LIBRO 

    public void AbrirVerLibro(String titulo) {
        controladorVerLibro.abrirVerLibro(vistaVentanaPrincipal, titulo);
    }

    public void AbrirLibroMiLista() {
        ControladorAgregarLibro.AbrirAgregarLibroMiLista(vistaVentanaPrincipal);
    }

    public void borrarLibroMiLista(String titulo) throws SQLException {

        LibroDAO.devolverLibrosDAO().setTitulo(titulo);
        LibroDAO.devolverLibrosDAO().borrarLibroDeMiLista();
    }

    public void AbrirAñadirLibro() {
        ControladorAñadirLibro.AbrirAnnadirLibro(vistaVentanaPrincipal);
    }

    //Videojuegos
    public void AbrirVerVideojuego(String titulo) {
        controladorVerVideojuego.abrirVerVideojuego(vistaVentanaPrincipal, titulo);
    }

    public void borrarVideojuegoMiLista(String titulo) throws SQLException {

        VideojuegoDAO.devolverVideojuegosDAO().setTitulo(titulo);
        VideojuegoDAO.devolverVideojuegosDAO().borrarVideojuegoDeMiLista();
    }

    public void AbrirAñadirVideojuego() {
        ControladorAñadirVideojuego.AbrirAnnadirVideojuego(vistaVentanaPrincipal);
    }

    public void AbrirVideojuegoMiLista() {
        ControladorAgregarVideojuego.AbrirAgregarVideojuegoMiLista(vistaVentanaPrincipal);
    }
   //Musica

    public void AbrirVerMusica(String ASIN) {
        controladorVerMusica.abrirVerMusica(vistaVentanaPrincipal, ASIN);
    }

    public void borrarMusicaMiLista(String ASIN) throws SQLException {
        MusicaDAO.devolverMusicaDAO().setASIN(ASIN);
        MusicaDAO.devolverMusicaDAO().borrarMusicaDeMiLista();
    }

    public void AbrirMusicaMiLista() {
        ControladorAgregarMusica.AbrirAgregarMusicaMiLista(vistaVentanaPrincipal);
    }

    public void AbrirAñadirMusica() {
        ControladorAñadirMusica.AbrirAnnadirMusica(vistaVentanaPrincipal);
    }
}
