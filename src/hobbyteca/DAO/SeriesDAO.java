/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.DAO;

import hobbyteca.modelo.Serie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jesus
 */
public class SeriesDAO extends RepositorioEstableceConexion {

    private String usuario;
    private String titulo;

    private static SeriesDAO SeriesDAO = null;

    public static SeriesDAO devolverSeriesDAO() {

        if (SeriesDAO != null) {
            return SeriesDAO;
        } else {
            SeriesDAO = new SeriesDAO();
            return SeriesDAO;
        }
    }

    public ArrayList<Serie> anadirListaSerie() throws SQLException {
        ArrayList listaSerie = new ArrayList();
        Serie Series;
        ResultSet recogeSeries;
        recogeSeries = ejecutaConsulta("SELECT s.Titulo FROM serie s "
                + "WHERE s.Titulo not in(Select titulo_serie FROM registra_serie where nombre_usuario='" + usuario + "') ");

        while (recogeSeries.next()) {
            Series = new Serie(); //Instanciamos
            Series.setTitulo(recogeSeries.getString(1));
            listaSerie.add(Series);
        }

        return listaSerie;
    } //Cierra ArrayList //Cierra ArrayList

    /**
     * Primero creamos el arrayList, hacemos consulta y luego mediante un while
     * vamos recogiendo todos los datos. Método en el que hacemos una consulta a
     * la vista de la base de datos en la que obtenemos todos los atributos de
     * serie y los almacenamos en un arraylist.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Serie> listSerieMiLista() throws SQLException {
        ArrayList listaSerie = new ArrayList();
        Serie Series;
        ResultSet recogeSeries;
        recogeSeries = ejecutaConsulta("SELECT s.Titulo, s.director, s.productora, s.lanzamiento, s.genero, s.descripcion, s.administrado, s.capitulos_totales, s.trailer,"
                + " rs.valoracion, rs.recomendacion, rs.capitulos_vistos, rs.comentario FROM usuario u, registra_serie rs, serie s "
                + "WHERE u.nombre_usuario = rs.nombre_usuario AND rs.titulo_serie = s.titulo AND u.nombre_usuario LIKE" + "'" + usuario + "'");

        while (recogeSeries.next()) {
            Series = new Serie(); //Instanciamos

            Series.setTitulo(recogeSeries.getString(1));
            Series.setDirector(recogeSeries.getString(2));
            Series.setProductora(recogeSeries.getString(3));

            Series.setLanzamiento(recogeSeries.getString(4));
            Series.setGenero(recogeSeries.getString(5));
            Series.setDescripcion(recogeSeries.getString(6));

            Series.setAdministrada(recogeSeries.getBoolean(7));
            Series.setCapitulos_totales(recogeSeries.getInt(8));
            Series.setTrailer(recogeSeries.getString(9));
            Series.setValoracion(recogeSeries.getInt(10));

            Series.setRecomendacion(recogeSeries.getBoolean(11));
            Series.setCapitulos_vistos(recogeSeries.getInt(12));
            Series.setComentario(recogeSeries.getString(13));

            listaSerie.add(Series);
        }

        return listaSerie;
    } //Cierra ArrayList

    /**
     * Insert into para añadir la serie seleccionada a la lista del usuario.
     *
     * @param tituloSerie
     * @return
     * @throws SQLException
     */
    public boolean insertarAMiListaSerie(String tituloSerie) throws SQLException {
        String inserccion = "Insert into registra_serie (nombre_usuario,titulo_serie,recomendacion,capitulos_vistos) VALUES(" + "'" + usuario + "'," + "'" + tituloSerie + "',0,0)";
        boolean resul = insertarOActualizarCampos(inserccion);
        return resul;
    }

    /**
     *
     * Inserta serie en la base de datos pasandole parametros desde el
     * usuario.
     *
     * @param nombreSerie
     * @param director
     * @param productora
     * @param lanzamiento
     * @param genero
     * @param descripcion
     * @param trailer
     * @return
     * @throws SQLException
     */
    public boolean insertarSerie(String nombreSerie, String director, String productora, String lanzamiento, String genero, String descripcion,String capitulosTotales, String trailer) throws SQLException {
        String inserccion = "Insert into serie VALUES(" + "'" + nombreSerie + "','" + director + "','" + productora + "','" + lanzamiento + "','" + genero + "','" + descripcion + "',0,"+capitulosTotales+",'" + trailer + "'" + ")";
        boolean resul = insertarOActualizarCampos(inserccion);
        return resul;
    }

    /**
     * Consulta para ver serie seleccionada.
     *
     * @param series
     * @return
     * @throws SQLException
     */
    public Serie SeriesVerSerie(Serie series) throws SQLException {

        ResultSet recogeSeries;

        recogeSeries = ejecutaConsulta("SELECT s.Titulo, s.director, s.productora, s.lanzamiento, s.genero, s.descripcion, s.administrado, s.capitulos_totales, s.trailer,"
                + " rs.valoracion, rs.recomendacion, rs.capitulos_vistos, rs.comentario FROM usuario u, registra_serie rs, serie s "
                + "WHERE u.nombre_usuario = rs.nombre_usuario AND rs.titulo_serie = s.titulo AND rs.titulo_serie= '" + titulo + "' AND rs.nombre_usuario LIKE" + "'" + usuario + "'");

        while (recogeSeries.next()) {
            series = new Serie(); //Instanciamos

            series.setTitulo(recogeSeries.getString(1));
            series.setDirector(recogeSeries.getString(2));
            series.setProductora(recogeSeries.getString(3));

            series.setLanzamiento(recogeSeries.getString(4));
            series.setGenero(recogeSeries.getString(5));
            series.setDescripcion(recogeSeries.getString(6));

            series.setAdministrada(recogeSeries.getBoolean(7));
            series.setCapitulos_totales(recogeSeries.getInt(8));
            series.setTrailer(recogeSeries.getString(9));
            series.setValoracion(recogeSeries.getInt(10));

            series.setRecomendacion(recogeSeries.getBoolean(11));
            series.setCapitulos_vistos(recogeSeries.getInt(12));
            series.setComentario(recogeSeries.getString(13));
        }

        return series;
    }

    public double valoracionMediaSerie() throws SQLException {
        double valoracionMedia = 10;
        ResultSet recogePeliculas;
        recogePeliculas = ejecutaConsulta("SELECT avg(valoracion) "
                + "  FROM registra_serie "
                + "WHERE titulo_serie= '" + titulo + "'");

        while (recogePeliculas.next()) {
            valoracionMedia = recogePeliculas.getDouble(1);
        }

        return valoracionMedia;
    }

    public boolean borrarSeriesDeMiLista() throws SQLException {
        String inserccion = "DELETE FROM registra_serie WHERE titulo_serie LIKE '"
                + titulo + "' AND nombre_usuario LIKE '" + usuario + "'";
        boolean resul = insertarOActualizarCampos(inserccion);
        return resul;
    }

    public boolean actualizarSerieDeMiLista(String valoracionPersona, int recomendacion, String capitulosVistos) throws SQLException {
        String inserccion = "UPDATE registra_serie SET valoracion = " + valoracionPersona + " , recomendacion = " + recomendacion + ",capitulos_vistos=" + capitulosVistos + "  WHERE titulo_serie LIKE '"
                + titulo + "' AND nombre_usuario LIKE '" + usuario + "'";
        boolean resul = insertarOActualizarCampos(inserccion);
        return resul;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    

}
