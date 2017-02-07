/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.DAO;

import hobbyteca.modelo.Comic;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jesus
 */
public class ComicsDAO extends RepositorioEstableceConexion {

    private String usuario;
    private String titulo;

    private static ComicsDAO ComicsDAO = null;

    public static ComicsDAO devolverComicsDAO() {

        if (ComicsDAO != null) {
            return ComicsDAO;
        } else {
            ComicsDAO = new ComicsDAO();
            return ComicsDAO;
        }
    }

    public ArrayList<Comic> anadirListaComic() throws SQLException {
        ArrayList listaComic = new ArrayList();
        Comic Comics;
        ResultSet recogeComics;
        recogeComics = ejecutaConsulta("SELECT c.Titulo FROM comic c "
                + "WHERE c.Titulo not in(Select titulo_comic FROM registra_comic where nombre_usuario='" + usuario + "') ");

        while (recogeComics.next()) {
            Comics = new Comic(); //Instanciamos
            Comics.setTitulo(recogeComics.getString(1));
            listaComic.add(Comics);
        }

        return listaComic;
    } //Cierra ArrayList //Cierra ArrayList

    /**
     * Primero creamos el arrayList, hacemos consulta y luego mediante un while
     * vamos recogiendo todos los datos. Método en el que hacemos una consulta a
     * la vista de la base de datos en la que obtenemos todos los atributos de
     * comic y los almacenamos en un arraylist.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Comic> listComicMiLista() throws SQLException {
        ArrayList listaComic = new ArrayList();
        Comic Comics;
        ResultSet recogeComics;
        recogeComics = ejecutaConsulta("SELECT c.Titulo, c.autor, c.editorial, c.lanzamiento, c.genero, c.descripcion, c.administrado, c.numeros_totales,"
                + " rc.valoracion, rc.recomendacion, rc.numeros_vistos, rc.comentario FROM usuario u, registra_comic rc,  comic c "
                + "WHERE u.nombre_usuario = rc.nombre_usuario AND rc.titulo_comic = c.titulo AND u.nombre_usuario LIKE" + "'" + usuario + "'");

        while (recogeComics.next()) {
            Comics = new Comic(); //Instanciamos

            Comics.setTitulo(recogeComics.getString(1));
            Comics.setAutor(recogeComics.getString(2));
            Comics.setEditorial(recogeComics.getString(3));

            Comics.setLanzamiento(recogeComics.getString(4));
            Comics.setGenero(recogeComics.getString(5));
            Comics.setDescripcion(recogeComics.getString(6));

            Comics.setAdministrada(recogeComics.getBoolean(7));
            Comics.setNumero(recogeComics.getInt(8));
            Comics.setValoracion(recogeComics.getInt(9));

            Comics.setRecomendacion(recogeComics.getBoolean(10));
            Comics.setNumeros_vistos(recogeComics.getInt(11));
            Comics.setComentario(recogeComics.getString(12));

            listaComic.add(Comics);
        }

        return listaComic;
    } //Cierra ArrayList

    /**
     * Insert into para añadir la comic seleccionada a la lista del usuario.
     *
     * @param tituloComic
     * @return
     * @throws SQLException
     */
    public boolean insertarAMiListaComic(String tituloComic) throws SQLException {
        String inserccion = "Insert into registra_comic (nombre_usuario,titulo_comic,recomendacion,numeros_vistos) VALUES(" + "'" + usuario + "'," + "'" + tituloComic + "',0,0)";
        boolean resul = insertarOActualizarCampos(inserccion);
        return resul;
    }

    /**
     *
     * Inserta comic en la base de datos pasandole parametros desde el
     * usuario.
     *
     * @param nombreComic
     * @param autor
     * @param editorial
     * @param lanzamiento
     * @param genero
     * @param descripcion
     * @param trailer
     * @return
     * @throws SQLException
     */
    public boolean insertarComic(String nombreComic, String autor, String editorial, String lanzamiento, String genero, String descripcion, String numerosTotales) throws SQLException {
        String inserccion = "Insert into comic VALUES(" + "'" + nombreComic + "','" + autor + "','" + editorial + "','" + lanzamiento + "','" + genero + "','" + descripcion + "',0," + numerosTotales + "" + ")";
        boolean resul = insertarOActualizarCampos(inserccion);
        return resul;
    }

    /**
     * Consulta para ver comic seleccionada.
     *
     * @param comics
     * @return
     * @throws SQLException
     */
    public Comic ComicsVerComic(Comic comics) throws SQLException {

        ResultSet recogeComics;

        recogeComics = ejecutaConsulta("SELECT c.Titulo, c.autor, c.editorial, c.lanzamiento, c.genero, c.descripcion, c.administrado, c.numeros_totales,"
                + " rc.valoracion, rc.recomendacion, rc.numeros_vistos, rc.comentario FROM usuario u, registra_comic rc,  comic c "
                + "WHERE u.nombre_usuario = rc.nombre_usuario AND rc.titulo_comic = c.titulo AND rc.titulo_comic= '" + titulo + "' AND rc.nombre_usuario LIKE" + "'" + usuario + "'");

        while (recogeComics.next()) {
            comics = new Comic(); //Instanciamos

            comics.setTitulo(recogeComics.getString(1));
            comics.setAutor(recogeComics.getString(2));
            comics.setEditorial(recogeComics.getString(3));

            comics.setLanzamiento(recogeComics.getString(4));
            comics.setGenero(recogeComics.getString(5));
            comics.setDescripcion(recogeComics.getString(6));

            comics.setAdministrada(recogeComics.getBoolean(7));
            comics.setNumero(recogeComics.getInt(8));
            comics.setValoracion(recogeComics.getInt(9));

            comics.setRecomendacion(recogeComics.getBoolean(10));
            comics.setNumeros_vistos(recogeComics.getInt(11));
            comics.setComentario(recogeComics.getString(12));
        }

        return comics;
    }

    public double valoracionMediaComic() throws SQLException {
        double valoracionMedia = 10;
        ResultSet recogePeliculas;
        recogePeliculas = ejecutaConsulta("SELECT avg(valoracion) "
                + "  FROM registra_comic "
                + "WHERE titulo_comic= '" + titulo + "'");

        while (recogePeliculas.next()) {
            valoracionMedia = recogePeliculas.getDouble(1);
        }

        return valoracionMedia;
    }

    public boolean borrarComicsDeMiLista() throws SQLException {
        String inserccion = "DELETE FROM registra_comic WHERE titulo_comic LIKE '"
                + titulo + "' AND nombre_usuario LIKE '" + usuario + "'";
        boolean resul = insertarOActualizarCampos(inserccion);
        return resul;
    }

    public boolean actualizarComicDeMiLista(String valoracionPersona, int recomendacion, String numerosVistos) throws SQLException {
        String inserccion = "UPDATE registra_comic SET valoracion = " + valoracionPersona + " , recomendacion = " + recomendacion + ",numeros_vistos=" + numerosVistos + "  WHERE titulo_comic LIKE '"
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
