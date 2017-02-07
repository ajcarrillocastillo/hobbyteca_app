/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.DAO;

import hobbyteca.modelo.Manga;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jesus
 */
public class MangasDAO extends RepositorioEstableceConexion {

    private String usuario;
    private String titulo;

    private static MangasDAO MangasDAO = null;

    public static MangasDAO devolverMangasDAO() {

        if (MangasDAO != null) {
            return MangasDAO;
        } else {
            MangasDAO = new MangasDAO();
            return MangasDAO;
        }
    }

    public ArrayList<Manga> anadirListaManga() throws SQLException {
        ArrayList listaManga = new ArrayList();
        Manga Mangas;
        ResultSet recogeMangas;
        recogeMangas = ejecutaConsulta("SELECT m.Titulo FROM manga m "
                + "WHERE m.Titulo not in(Select titulo_manga FROM registra_manga where nombre_usuario='" + usuario + "') ");

        while (recogeMangas.next()) {
            Mangas = new Manga(); //Instanciamos
            Mangas.setTitulo(recogeMangas.getString(1));
            listaManga.add(Mangas);
        }

        return listaManga;
    } //Cierra ArrayList //Cierra ArrayList

    /**
     * Primero creamos el arrayList, hacemos consulta y luego mediante un while
     * vamos recogiendo todos los datos. Método en el que hacemos una consulta a
     * la vista de la base de datos en la que obtenemos todos los atributos de
     * manga y los almacenamos en un arraylist.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Manga> listMangaMiLista() throws SQLException {
        ArrayList listaManga = new ArrayList();
        Manga Mangas;
        ResultSet recogeMangas;
        recogeMangas = ejecutaConsulta("SELECT m.Titulo, m.autor, m.editorial, m.lanzamiento, m.genero, m.descripcion, m.administrado, m.tomos_totales,"
                + " rm.valoracion, rm.recomendacion, rm.tomos_vistos, rm.comentario FROM usuario u, registra_manga rm,  manga m "
                + "WHERE u.nombre_usuario = rm.nombre_usuario AND rm.titulo_manga = m.titulo AND u.nombre_usuario LIKE" + "'" + usuario + "'");

        while (recogeMangas.next()) {
            Mangas = new Manga(); //Instanciamos

            Mangas.setTitulo(recogeMangas.getString(1));
            Mangas.setAutor(recogeMangas.getString(2));
            Mangas.setEditorial(recogeMangas.getString(3));

            Mangas.setLanzamiento(recogeMangas.getString(4));
            Mangas.setGenero(recogeMangas.getString(5));
            Mangas.setDescripcion(recogeMangas.getString(6));

            Mangas.setAdministrada(recogeMangas.getBoolean(7));
            Mangas.setTomo(recogeMangas.getInt(8));
            Mangas.setValoracion(recogeMangas.getInt(9));

            Mangas.setRecomendacion(recogeMangas.getBoolean(10));
            Mangas.setTomos_vistos(recogeMangas.getInt(11));
            Mangas.setComentario(recogeMangas.getString(12));

            listaManga.add(Mangas);
        }

        return listaManga;
    } //Cierra ArrayList

    /**
     * Insert into para añadir la manga seleccionada a la lista del usuario.
     *
     * @param tituloManga
     * @return
     * @throws SQLException
     */
    public boolean insertarAMiListaManga(String tituloManga) throws SQLException {
        String inserccion = "Insert into registra_manga (nombre_usuario,titulo_manga,recomendacion,tomos_vistos) VALUES(" + "'" + usuario + "'," + "'" + tituloManga + "',0,0)";
        boolean resul = insertarOActualizarCampos(inserccion);
        return resul;
    }

    /**
     *
     * Inserta manga en la base de datos pasandole parametros desde el
     * usuario.
     *
     * @param nombreManga
     * @param autor
     * @param editorial
     * @param lanzamiento
     * @param genero
     * @param descripcion
     * @param trailer
     * @return
     * @throws SQLException
     */
    public boolean insertarManga(String nombreManga, String autor, String editorial, String lanzamiento, String genero, String descripcion, String tomosTotales) throws SQLException {
        String inserccion = "Insert into manga VALUES(" + "'" + nombreManga + "','" + autor + "','" + editorial + "','" + lanzamiento + "','" + genero + "','" + descripcion + "',0," + tomosTotales + "" + ")";
        boolean resul = insertarOActualizarCampos(inserccion);
        return resul;
    }

    /**
     * Consulta para ver manga seleccionada.
     *
     * @param mangas
     * @return
     * @throws SQLException
     */
    public Manga MangasVerManga(Manga mangas) throws SQLException {

        ResultSet recogeMangas;

        recogeMangas = ejecutaConsulta("SELECT m.Titulo, m.autor, m.editorial, m.lanzamiento, m.genero, m.descripcion, m.administrado, m.tomos_totales,"
                + " rm.valoracion, rm.recomendacion, rm.tomos_vistos, rm.comentario FROM usuario u, registra_manga rm,  manga m "
                + "WHERE u.nombre_usuario = rm.nombre_usuario AND rm.titulo_manga = m.titulo AND rm.titulo_manga= '" + titulo + "' AND rm.nombre_usuario LIKE" + "'" + usuario + "'");

        while (recogeMangas.next()) {
            mangas = new Manga(); //Instanciamos

            mangas.setTitulo(recogeMangas.getString(1));
            mangas.setAutor(recogeMangas.getString(2));
            mangas.setEditorial(recogeMangas.getString(3));

            mangas.setLanzamiento(recogeMangas.getString(4));
            mangas.setGenero(recogeMangas.getString(5));
            mangas.setDescripcion(recogeMangas.getString(6));

            mangas.setAdministrada(recogeMangas.getBoolean(7));
            mangas.setTomo(recogeMangas.getInt(8));
            mangas.setValoracion(recogeMangas.getInt(9));

            mangas.setRecomendacion(recogeMangas.getBoolean(10));
            mangas.setTomos_vistos(recogeMangas.getInt(11));
            mangas.setComentario(recogeMangas.getString(12));
        }

        return mangas;
    }

    public double valoracionMediaManga() throws SQLException {
        double valoracionMedia = 10;
        ResultSet recogePeliculas;
        recogePeliculas = ejecutaConsulta("SELECT avg(valoracion) "
                + "  FROM registra_manga "
                + "WHERE titulo_manga= '" + titulo + "'");

        while (recogePeliculas.next()) {
            valoracionMedia = recogePeliculas.getDouble(1);
        }

        return valoracionMedia;
    }

    public boolean borrarMangasDeMiLista() throws SQLException {
        String inserccion = "DELETE FROM registra_manga WHERE titulo_manga LIKE '"
                + titulo + "' AND nombre_usuario LIKE '" + usuario + "'";
        boolean resul = insertarOActualizarCampos(inserccion);
        return resul;
    }

    public boolean actualizarMangaDeMiLista(String valoracionPersona, int recomendacion, String tomosVistos) throws SQLException {
        String inserccion = "UPDATE registra_manga SET valoracion = " + valoracionPersona + " , recomendacion = " + recomendacion + ",tomos_vistos=" + tomosVistos + "  WHERE titulo_manga LIKE '"
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
