/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.DAO;

import hobbyteca.modelo.Libro;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author xRafa
 */
public class LibroDAO extends RepositorioEstableceConexion {

    private String usuario;
    private String titulo;

    private static LibroDAO LibrosDAO = null;

    public static LibroDAO devolverLibrosDAO() {

        if (LibrosDAO != null) {
            return LibrosDAO;
        } else {
            LibrosDAO = new LibroDAO();
            return LibrosDAO;
        }
    }

    /**
     * Consulta para la lista de libro. lista todos los titulos de los libros y
     * filtra aquellos que ya están agregados a registra_libros para no
     * agregarlos.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Libro> anadirListaLibro() throws SQLException {
        ArrayList listaLibro = new ArrayList();
        Libro Libros;
        ResultSet recogeLibros;
        recogeLibros = ejecutaConsulta("SELECT l.Titulo FROM libro l "
                + "WHERE l.Titulo not in(Select titulo_libro FROM registra_libro where nombre_usuario='" + usuario + "') ");

        while (recogeLibros.next()) {
            Libros = new Libro(); //Instanciamos
            Libros.setTitulo(recogeLibros.getString(1));
            listaLibro.add(Libros);
        }

        return listaLibro;
    } //Cierra ArrayList //Cierra ArrayList

    /**
     * Método en el que hacemos una consulta a la vista de la base de datos en
     * la que obtenemos todos los atributos de libro y los almacenamos en un
     * arraylist. Primero creamos el arrayList, hacemos consulta y luego
     * mediante un while vamos recogiendo todos los datos.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Libro> listLibroMiLista() throws SQLException {
        ArrayList listaLibro = new ArrayList();
        Libro Libros;
        ResultSet recogeLibros;
        recogeLibros = ejecutaConsulta("SELECT l.Titulo, l.autor, l.editorial, l.lanzamiento, l.genero, l.descripcion, l.administrado, "
                + "rl.valoracion, rl.recomendacion, rl.visualizado, rl.comentario FROM usuario u, registra_libro rl, libro l"
                + " WHERE u.nombre_usuario = rl.nombre_usuario AND rl.titulo_libro = l.titulo AND u.nombre_usuario LIKE" + "'" + usuario + "'");

        while (recogeLibros.next()) {
            Libros = new Libro(); //Instanciamos

            Libros.setTitulo(recogeLibros.getString(1));
            Libros.setAutor(recogeLibros.getString(2));
            Libros.setEditorial(recogeLibros.getString(3));

            Libros.setLanzamiento(recogeLibros.getString(4));
            Libros.setGenero(recogeLibros.getString(5));
            Libros.setDescripcion(recogeLibros.getString(6));

            Libros.setAdministrada(recogeLibros.getBoolean(7));
            Libros.setValoracion(recogeLibros.getInt(8));

            Libros.setRecomendacion(recogeLibros.getBoolean(9));
            Libros.setVisualizado(recogeLibros.getBoolean(10));
            Libros.setComentario(recogeLibros.getString(11));

            listaLibro.add(Libros);
        }

        return listaLibro;
    } //Cierra ArrayList

    /**
     * Insert into para añadir la libro seleccionada a la lista del usuario.
     *
     * @param tituloLibro
     * @return
     * @throws SQLException
     */
    public boolean insertarAMiListaLibro(String tituloLibro) throws SQLException {
        String inserccion = "Insert into registra_libro (nombre_usuario,titulo_libro,recomendacion,visualizado) VALUES(" + "'" + usuario + "'," + "'" + tituloLibro + "',0,0)";
        boolean resul = insertarOActualizarCampos(inserccion);
        return resul;
    }

    /**
     *
     * Inserta libro en la base de datos pasandole parametros desde el
     * usuario.
     *
     * @param nombreLibro
     * @param escritor
     * @param editorial
     * @param lanzamiento
     * @param genero
     * @param descripcion
     * @param trailer
     * @return
     * @throws SQLException
     */
    public boolean insertarLibro(String nombreLibro, String escritor, String editorial, String lanzamiento, String genero, String descripcion) throws SQLException {
        String inserccion = "Insert into libro VALUES(" + "'" + nombreLibro + "','" + escritor + "','" + editorial + "','" + lanzamiento + "','" + genero + "','" + descripcion + "' ,0)";
        boolean resul = insertarOActualizarCampos(inserccion);
        return resul;
    }

    /**
     * Saca todos los datos de libros para mostrarlo al picar en él.
     * @param libros
     * @return
     * @throws SQLException 
     */
    public Libro LibroVerLibro(Libro libros) throws SQLException {
        ResultSet recogeLibro;
        recogeLibro = ejecutaConsulta("SELECT l.Titulo, l.autor, l.editorial, l.lanzamiento, l.genero, l.descripcion, l.administrado, "
                + "rl.valoracion, rl.recomendacion, rl.visualizado, rl.comentario FROM usuario u, registra_libro rl, libro l "
                + "WHERE u.nombre_usuario = rl.nombre_usuario AND rl.titulo_libro = l.titulo "
                + "AND rl.titulo_libro LIKE '" + titulo + "' AND rl.nombre_usuario LIKE " + "'" + usuario + "'");

        
        while (recogeLibro.next()) {
            libros = new Libro(); //Instanciamos

            libros.setTitulo(recogeLibro.getString(1));
            libros.setAutor(recogeLibro.getString(2));
            libros.setEditorial(recogeLibro.getString(3));

            libros.setLanzamiento(recogeLibro.getString(4));
            libros.setGenero(recogeLibro.getString(5));
            libros.setDescripcion(recogeLibro.getString(6));

            libros.setAdministrada(recogeLibro.getBoolean(7));
            libros.setValoracion(recogeLibro.getInt(8));

            libros.setRecomendacion(recogeLibro.getBoolean(9));
            libros.setVisualizado(recogeLibro.getBoolean(10));
            libros.setComentario(recogeLibro.getString(11));
        }

        return libros;
    }

    /**
     * Consulta que calcula la valoración media de los libros para mostrarla en la vista.
     * @return
     * @throws SQLException 
     */
    public double valoracionMediaLibro() throws SQLException {
        double valoracionMedia = 10;
        ResultSet recogeLibro;
        recogeLibro = ejecutaConsulta("SELECT avg(valoracion) "
                + "  FROM registra_libro rl "
                + "WHERE rl.titulo_libro= '" + titulo + "'");

        while (recogeLibro.next()) {
            valoracionMedia = recogeLibro.getDouble(1);
        }

        return valoracionMedia;
    }

    /**
     * Borra la consulta que queramos al clicar en la vista
     * @return
     * @throws SQLException 
     */
    public boolean borrarLibroDeMiLista() throws SQLException {
        String inserccion = "DELETE FROM registra_libro WHERE titulo_libro LIKE '"
                + titulo + "' AND nombre_usuario LIKE '" + usuario + "'";
        boolean resul = insertarOActualizarCampos(inserccion);
        return resul;
    }

    
    /**
     * Actualizar libro (cuando guardamos si hemos editado algo)
     * @param valoracionPersona
     * @param recomendacion
     * @param leido
     * @return
     * @throws SQLException 
     */
    public boolean actualizarLibroDeMiLista(int valoracionPersona, int recomendacion, int leido) throws SQLException {
        String inserccion = "UPDATE registra_libro SET valoracion = " + valoracionPersona + " , recomendacion = " + recomendacion + ",visualizado=" + leido + "  WHERE titulo_libro LIKE '"
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
