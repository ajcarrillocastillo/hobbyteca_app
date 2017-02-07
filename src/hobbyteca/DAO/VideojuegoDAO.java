/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.DAO;

import hobbyteca.modelo.Videojuego;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author xRafa
 */
public class VideojuegoDAO  extends RepositorioEstableceConexion {
    
    private String usuario;
    private String titulo;
    
    private static VideojuegoDAO VideojuegosDAO = null;

    public static VideojuegoDAO devolverVideojuegosDAO() {

        if (VideojuegosDAO != null) {
            return VideojuegosDAO;
        } else {
            VideojuegosDAO = new VideojuegoDAO();
            return VideojuegosDAO;
        }
    }
    
    
    /**
     * 
     * Lista todos los titulos de los video juegos.
     * @return
     * @throws SQLException 
     */
 public ArrayList<Videojuego> anadirListaVideojuego() throws SQLException {
       ArrayList listaVideojuegos = new ArrayList();
        Videojuego Videojuegos;
        ResultSet recogeVideojuegos;
         recogeVideojuegos = ejecutaConsulta("SELECT v.Titulo FROM videojuego v "
                + "WHERE v.titulo not in(Select titulo_videojuego FROM registra_videojuego where nombre_usuario='"+usuario+"') ");
       
            while (recogeVideojuegos.next()) {
                Videojuegos = new Videojuego(); //Instanciamos
                Videojuegos.setTitulo(recogeVideojuegos.getString(1));
                listaVideojuegos.add(Videojuegos);
            }
            
        return listaVideojuegos;
    } //Cierra ArrayList //Cierra ArrayList


 /**
  * Método en el que hacemos una consulta a la vista de la base de datos en la que obtenemos todos los atributos de videojuego y los almacenamos en un arraylist.
  * Primero creamos el arrayList, hacemos consulta y luego mediante un while vamos recogiendo todos los datos.
  * @return
  * @throws SQLException 
  */
    public ArrayList<Videojuego> listVideojuegoMiLista() throws SQLException {
        ArrayList listaVideojuego = new ArrayList();
        Videojuego videojuegos;
        ResultSet recogevideojuegos;
        recogevideojuegos = ejecutaConsulta("SELECT v.Titulo, v.desarrolladora, v.distribuidora, v.lanzamiento, v.genero, v.descripcion, v.administrado "
                + ",v.plataforma, v.trailer," +
"rv.valoracion, rv.recomendacion, rv.jugado, rv.comentario FROM usuario u, registra_videojuego rv, videojuego v "
                + "WHERE u.nombre_usuario = rv.nombre_usuario AND rv.titulo_videojuego = v.titulo AND u.nombre_usuario LIKE"+"'"+usuario+"'");
       
            while (recogevideojuegos.next()) {
                videojuegos = new Videojuego(); //Instanciamos
                
                videojuegos.setTitulo(recogevideojuegos.getString(1));
                videojuegos.setDesarrolladora(recogevideojuegos.getString(2));
                videojuegos.setDistribuidora(recogevideojuegos.getString(3));
                
                videojuegos.setLanzamiento(recogevideojuegos.getString(4));
                videojuegos.setGenero(recogevideojuegos.getString(5));
                videojuegos.setDescripcion(recogevideojuegos.getString(6));
                
                videojuegos.setAdministrada(recogevideojuegos.getBoolean(7));
                videojuegos.setPlataforma(recogevideojuegos.getString(8));
                videojuegos.setTrailer(recogevideojuegos.getString(9));
                videojuegos.setValoracion(recogevideojuegos.getInt(10));
             
                
                videojuegos.setRecomendacion(recogevideojuegos.getBoolean(11));
                videojuegos.setJugado(recogevideojuegos.getBoolean(12));
                videojuegos.setComentario(recogevideojuegos.getString(13));
                
                listaVideojuego.add(videojuegos);
            }
            
        return listaVideojuego;
    } //Cierra ArrayList

    /**
     * Insert into para añadir el videojuego seleccionado a la lista del usuario. 
     * @param tituloVideojuego
     * @return
     * @throws SQLException 
     */
    public boolean insertarAMiListaVideojuegos(String tituloVideojuego) throws SQLException{
       String inserccion = "Insert into registra_videojuego (nombre_usuario, titulo_videojuego ,recomendacion, jugado) VALUES(" + "'" + usuario + "'," + "'" + tituloVideojuego + "',0,0)";
        boolean resul = insertarOActualizarCampos(inserccion);
        return resul;
    }
    
   /**
    * Inserta videojuego en la base de datos pasandole parametros desde el usuario.
    * @param nombreVideojuego
    * @param desarrolladora
    * @param distribuidora
    * @param lanzamiento
    * @param genero
    * @param descripcion
    * @param plataforma
    * @param trailer
    * @return
    * @throws SQLException 
    */
    public boolean insertarVideojuego(String nombreVideojuego, String desarrolladora, String distribuidora, String lanzamiento, String genero, String descripcion ,String plataforma, String trailer) throws SQLException{
       String inserccion = "Insert into videojuego (\n" +
"`titulo` ,\n" +
"`desarrolladora` ,\n" +
"`distribuidora` ,\n" +
"`lanzamiento` ,\n" +
"`genero` ,\n" +
"`descripcion` ,\n" +
"`administrado` ,\n" +
"`plataforma` ,\n" +
"`trailer`\n" +
") VALUES('"+ nombreVideojuego+ "', '"+ desarrolladora+"', '"+distribuidora+"', '"+lanzamiento+"', '"+ genero+"', '"+descripcion+"',  '1', '"+ plataforma+"', '"+trailer + "'" + ")";
       
       boolean resul = insertarOActualizarCampos(inserccion);
        return resul;
    }
    
    public Videojuego VideojuegoVerVideojuego(Videojuego videojuegos) throws SQLException {
        ResultSet recogeVideojuegos;
        recogeVideojuegos = ejecutaConsulta("SELECT v.Titulo, v.desarrolladora, v.distribuidora, v.lanzamiento, v.genero, v.descripcion, v.administrado "
                + ",v.plataforma, v.trailer, rv.valoracion, rv.recomendacion, rv.jugado, rv.comentario FROM usuario u, registra_videojuego rv, videojuego v "
                + "WHERE u.nombre_usuario = rv.nombre_usuario AND rv.titulo_videojuego = v.titulo "
                + "AND rv.titulo_videojuego LIKE"+" '" + titulo +"' "+"AND rv.nombre_usuario LIKE " + "'" + usuario + "'");

        while (recogeVideojuegos.next()) {
               videojuegos = new Videojuego(); //Instanciamos
                
                videojuegos.setTitulo(recogeVideojuegos.getString(1));
                videojuegos.setDesarrolladora(recogeVideojuegos.getString(2));
                videojuegos.setDistribuidora(recogeVideojuegos.getString(3));
                
                videojuegos.setLanzamiento(recogeVideojuegos.getString(4));
                videojuegos.setGenero(recogeVideojuegos.getString(5));
                videojuegos.setDescripcion(recogeVideojuegos.getString(6));
                
                videojuegos.setAdministrada(recogeVideojuegos.getBoolean(7));
                videojuegos.setPlataforma(recogeVideojuegos.getString(8));
                videojuegos.setTrailer(recogeVideojuegos.getString(9));
                videojuegos.setValoracion(recogeVideojuegos.getInt(10));
             
                
                videojuegos.setRecomendacion(recogeVideojuegos.getBoolean(11));
                videojuegos.setJugado(recogeVideojuegos.getBoolean(12));
                videojuegos.setComentario(recogeVideojuegos.getString(13));
        }

        return videojuegos;
    }

    /**
     * Consulta que calcula la valoración media de los videojuegos para mostrarla en la vista.
     * @return
     * @throws SQLException 
     */
    public double valoracionMediaVideojuego() throws SQLException {
        double valoracionMedia = 10;
        ResultSet recogeVideojuego;
        recogeVideojuego = ejecutaConsulta("SELECT avg(valoracion) "
                + "  FROM registra_videojuego rv "
                + "WHERE rv.titulo_videojuego= '" + titulo + "'");

        while (recogeVideojuego.next()) {
            valoracionMedia = recogeVideojuego.getDouble(1);
        }

        return valoracionMedia;
    }

    /**
     * Borra la consulta que queramos al clicar en la vista
     * @return
     * @throws SQLException 
     */
    public boolean borrarVideojuegoDeMiLista() throws SQLException {
        String inserccion = "DELETE FROM registra_videojuego WHERE titulo_videojuego LIKE '"
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
    public boolean actualizarVideojuegoDeMiLista(int valoracionPersona, int recomendacion, int jugado) throws SQLException {
        String inserccion = "UPDATE registra_videojuego SET valoracion = " + valoracionPersona + " , recomendacion = " + recomendacion + ",jugado=" + jugado + "  WHERE titulo_videojuego LIKE '"
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
