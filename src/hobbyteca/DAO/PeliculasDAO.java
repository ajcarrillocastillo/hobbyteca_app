/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.DAO;

import java.util.ArrayList;
import hobbyteca.modelo.Pelicula;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author xRafa
 */
public class PeliculasDAO extends RepositorioEstableceConexion {
//creamos la variable del usuario para recogerla luego

    private String usuario;
    private String titulo;

    private static PeliculasDAO PeliculasDAO = null;

    public static PeliculasDAO devolverPeliculasDAO() {

        if (PeliculasDAO != null) {
            return PeliculasDAO;
        } else {
            PeliculasDAO = new PeliculasDAO();
            return PeliculasDAO;
        }
    }

    /**
     * Consulta para la lista de Peliculas. lista todos los titulos de los comics y
     * filtra aquellos que ya están agregados a registra_comic para no
     * agregarlos.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Pelicula> anadirListaPelicula() throws SQLException {
        ArrayList listaPelicula = new ArrayList();
        Pelicula peliculas;
        ResultSet recogePeliculas;
        recogePeliculas = ejecutaConsulta("SELECT p.Titulo FROM pelicula p "
                + "WHERE p.titulo not in(Select titulo_pelicula FROM registra_pelicula where nombre_usuario='" + usuario + "') ");

        while (recogePeliculas.next()) {
            peliculas = new Pelicula(); //Instanciamos
            peliculas.setTitulo(recogePeliculas.getString(1));
            listaPelicula.add(peliculas);
        }

        return listaPelicula;
    } //Cierra ArrayList //Cierra ArrayList

    /**
     * Método en el que hacemos una consulta a la vista de la base de datos en
     * la que obtenemos todos los atributos de pelicula y los almacenamos en un
     * arraylist. Primero creamos el arrayList, hacemos consulta y luego
     * mediante un while vamos recogiendo todos los datos.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Pelicula> listPeliculaMiLista() throws SQLException {
        ArrayList listaPelicula = new ArrayList();
        Pelicula peliculas;
        ResultSet recogePeliculas;
        recogePeliculas = ejecutaConsulta("SELECT p.Titulo, p.director, p.productora, p.lanzamiento, p.genero, p.descripcion, p.administrado, p.trailer, rp.valoracion, "
                + " rp.recomendacion, rp.visualizado, rp.comentario FROM usuario u, registra_pelicula rp, pelicula p "
                + "WHERE u.nombre_usuario = rp.nombre_usuario AND rp.titulo_pelicula = p.titulo AND u.nombre_usuario LIKE" + "'" + usuario + "'");

        while (recogePeliculas.next()) {
            peliculas = new Pelicula(); //Instanciamos

            peliculas.setTitulo(recogePeliculas.getString(1));
            peliculas.setAutor(recogePeliculas.getString(2));
            peliculas.setProductora(recogePeliculas.getString(3));

            peliculas.setLanzamiento(recogePeliculas.getString(4));
            peliculas.setGenero(recogePeliculas.getString(5));
            peliculas.setDescripcion(recogePeliculas.getString(6));

            peliculas.setAdministrada(recogePeliculas.getBoolean(7));
            peliculas.setTrailer(recogePeliculas.getString(8));
            peliculas.setValoracion(recogePeliculas.getInt(9));

            peliculas.setRecomendacion(recogePeliculas.getBoolean(10));
            peliculas.setVisualizado(recogePeliculas.getBoolean(11));
            peliculas.setComentario(recogePeliculas.getString(12));

            listaPelicula.add(peliculas);
        }

        return listaPelicula;
    } //Cierra ArrayList

    /**
     * Insert into para añadir la pelicula seleccionada a la lista del usuario.
     *
     * @param tituloPelicula
     * @return
     * @throws SQLException
     */
    public boolean insertarAMiListaPelicula(String tituloPelicula) throws SQLException {
        String inserccion = "Insert into registra_pelicula (nombre_usuario,titulo_pelicula,recomendacion,visualizado) VALUES(" + "'" + usuario + "'," + "'" + tituloPelicula + "',0,0)";
        boolean resul = insertarOActualizarCampos(inserccion);
        return resul;
    }

    /**
     *
     * Inserta pelicula en la base de datos pasandole parametros desde el
     * usuario.
     *
     * @param nombrePelicula
     * @param director
     * @param productora
     * @param lanzamiento
     * @param genero
     * @param descripcion
     * @param trailer
     * @return
     * @throws SQLException
     */
    public boolean insertarPelicula(String nombrePelicula, String director, String productora, String lanzamiento, String genero, String descripcion, String trailer) throws SQLException {
        String inserccion = "Insert into pelicula VALUES(" + "'" + nombrePelicula + "','" + director + "','" + productora + "','" + lanzamiento + "','" + genero + "','" + descripcion + "',0,'" + trailer + "'" + ")";
        boolean resul = insertarOActualizarCampos(inserccion);
        return resul;
    }

    /**
     * Consulta para ver pelicula seleccionada.
     *
     * @param peliculas
     * @return
     * @throws SQLException
     */
    public Pelicula PeliculaVerPelicula(Pelicula peliculas) throws SQLException {

        ResultSet recogePeliculas;
        recogePeliculas = ejecutaConsulta("SELECT p.Titulo, p.director, p.productora, p.lanzamiento, p.genero, p.descripcion, p.administrado, p.trailer, rp.valoracion, "
                + " rp.recomendacion, rp.visualizado, rp.comentario FROM usuario u, registra_pelicula rp, pelicula p "
                + "WHERE u.nombre_usuario = rp.nombre_usuario AND rp.titulo_pelicula = p.titulo AND rp.titulo_pelicula= '" + titulo + "' AND rp.nombre_usuario LIKE" + "'" + usuario + "'");

        while (recogePeliculas.next()) {
            peliculas = new Pelicula(); //Instanciamos

            peliculas.setTitulo(recogePeliculas.getString(1));
            peliculas.setAutor(recogePeliculas.getString(2));
            peliculas.setProductora(recogePeliculas.getString(3));

            peliculas.setLanzamiento(recogePeliculas.getString(4));
            peliculas.setGenero(recogePeliculas.getString(5));
            peliculas.setDescripcion(recogePeliculas.getString(6));

            peliculas.setAdministrada(recogePeliculas.getBoolean(7));
            peliculas.setTrailer(recogePeliculas.getString(8));
            peliculas.setValoracion(recogePeliculas.getInt(9));

            peliculas.setRecomendacion(recogePeliculas.getBoolean(10));
            peliculas.setVisualizado(recogePeliculas.getBoolean(11));
            peliculas.setComentario(recogePeliculas.getString(12));
        }

        return peliculas;
    }

    public double valoracionMediaPelicula() throws SQLException {
        double valoracionMedia = 10;
        ResultSet recogePeliculas;
        recogePeliculas = ejecutaConsulta("SELECT avg(valoracion) "
                + "  FROM registra_pelicula rp "
                + "WHERE rp.titulo_pelicula= '" + titulo + "'");

        while (recogePeliculas.next()) {
            valoracionMedia = recogePeliculas.getDouble(1);
        }

        return valoracionMedia;
    }
    
    public boolean borrarPeliculaDeMiLista() throws SQLException{
        String inserccion = "DELETE FROM registra_pelicula WHERE titulo_pelicula LIKE '"
                            + titulo +"' AND nombre_usuario LIKE '"+usuario+"'";
        boolean resul = insertarOActualizarCampos(inserccion);
        return resul;
    }
    
    public boolean actualizarPeliculaDeMiLista(int valoracionPersona, int recomendacion,int visto) throws SQLException{
       String inserccion = "UPDATE registra_pelicula SET valoracion = "+valoracionPersona+" , recomendacion = "+recomendacion+",visualizado="+visto+"  WHERE titulo_pelicula LIKE '"
                            + titulo +"' AND nombre_usuario LIKE '"+usuario+"'";
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
