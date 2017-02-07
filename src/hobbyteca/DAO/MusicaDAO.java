/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.DAO;

import hobbyteca.modelo.Libro;
import hobbyteca.modelo.Musica;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author xRafa
 */
public class MusicaDAO extends RepositorioEstableceConexion {
       private String usuario;
       private String ASIN;
   
    private static MusicaDAO MusicaDAO = null;

    public static MusicaDAO devolverMusicaDAO() {

        if (MusicaDAO != null) {
            return MusicaDAO;
        } else {
            MusicaDAO = new MusicaDAO();
            return MusicaDAO;
        }
    }
    
    /**
     * Consulta que lista los titulos de la musica. Filtra y no muestra aquellos ya agregados.
     * @return
     * @throws SQLException 
     */
    public ArrayList<Musica> AgregarMusicaMilista() throws SQLException {
        ArrayList listaMusica = new ArrayList();
        Musica Musica;
        ResultSet recogeMusica;
        recogeMusica = ejecutaConsulta("SELECT m.ASIN, m.titulo "
                + "FROM musica m WHERE m.ASIN not in(Select ASIN_musica FROM registra_musica where nombre_usuario='" + usuario + "') ");
       
            while (recogeMusica.next()) {
                Musica = new Musica(); //Instanciamos
                
                Musica.setASIN(recogeMusica.getString(1));
                Musica.setTitulo(recogeMusica.getString(2));

                
                listaMusica.add(Musica);
            }
            
        return listaMusica;
    } //Cierra ArrayList


    
/**
 * Método en el que hacemos una consulta a la vista de la base de datos en la que obtenemos todos los atributos de pelicula y los almacenamos en un arraylist.
 * Primero creamos el arrayList, hacemos consulta y luego mediante un while vamos recogiendo todos los datos.
 * @return
 * @throws SQLException 
 */
    public ArrayList<Musica> listMusicaMiLista() throws SQLException {
        ArrayList listaMusica = new ArrayList();
        Musica Musica;
        ResultSet recogeMusica;
        recogeMusica = ejecutaConsulta("SELECT m.ASIN, m.titulo, m.autor, m.discografica, m.lanzamiento, m.genero, m.administrado, m.album, m.trailer,"
                + "rm.valoracion, rm.recomendacion, rm.escuchado, rm.comentario "
                + "FROM usuario u, registra_musica rm, musica m WHERE u.nombre_usuario = rm.nombre_usuario AND rm.ASIN_MUSICA = m.ASIN AND u.nombre_usuario LIKE"+"'"+usuario+"'");
       
            while (recogeMusica.next()) {
                Musica = new Musica(); //Instanciamos
                
                Musica.setASIN(recogeMusica.getString(1));
                Musica.setTitulo(recogeMusica.getString(2));
                Musica.setAutor(recogeMusica.getString(3));
                
                Musica.setDiscografica(recogeMusica.getString(4));
                Musica.setLanzamiento(recogeMusica.getString(5));
                Musica.setGenero(recogeMusica.getString(6));
                
                Musica.setAdministrada(recogeMusica.getBoolean(7));
                Musica.setAlbum(recogeMusica.getString(8));
                Musica.setTrailer(recogeMusica.getString(9));
                Musica.setValoracion(recogeMusica.getInt(10));
                
                Musica.setRecomendacion(recogeMusica.getBoolean(11));
                Musica.setEscuchado(recogeMusica.getBoolean(12));
                Musica.setComentario(recogeMusica.getString(13));
                
                listaMusica.add(Musica);
            }
            
        return listaMusica;
    } //Cierra ArrayList
    
    /**
     * Insert into para añadir la pelicula seleccionada a la lista del usuario. 
     * @param tituloMusica
     * @return
     * @throws SQLException 
     */
    public boolean insertarAMiListaMusica(String ASINMusica) throws SQLException{
       String inserccion = "Insert into registra_musica (nombre_usuario, ASIN_musica, recomendacion, escuchado) VALUES(" + "'" + usuario + "'," + "'" + ASINMusica + "',0,0)";
       boolean resul = insertarOActualizarCampos(inserccion);
       return resul;
    }
    
    /**
     * 
     * Inserta musica en la base de datos pasandole parametros desde el usuario.
   */
       public boolean insertarMusica(String ASIN,String titulo,String autor,String discografica, String lanzamiento, String genero, String album, String enlace) throws SQLException{
       String inserccion = "INSERT INTO musica (`ASIN`, `titulo`, `autor`, `discografica`, `lanzamiento`, `genero`, `administrado`, `album`, `trailer`) "
               + "VALUES ('"+ASIN+"', '"+titulo+"', '"+autor+"', ' "+discografica+"', '"+lanzamiento+"', '"+genero+"', '0', '"+album+"', '"+enlace+"')";
        boolean resul = insertarOActualizarCampos(inserccion);
        return resul;
    }
    public Musica MusicaVerMusica(Musica musica) throws SQLException {
        ResultSet recogeMusica;
        recogeMusica = ejecutaConsulta("SELECT m.ASIN, m.titulo, m.autor, m.discografica, m.lanzamiento, m.genero, m.administrado, m.album, m.trailer,"
                + "rm.valoracion, rm.recomendacion, rm.escuchado, rm.comentario "
                + "FROM usuario u, registra_musica rm, musica m "
                + "WHERE u.nombre_usuario = rm.nombre_usuario AND rm.ASIN_musica = m.ASIN "
                + "AND rm.ASIN_musica LIKE '" + ASIN + "' AND rm.nombre_usuario LIKE " + "'" + usuario + "'");

        while (recogeMusica.next()) {
            musica = new Musica(); //Instanciamos

             
                musica.setASIN(recogeMusica.getString(1));
                musica.setTitulo(recogeMusica.getString(2));
                musica.setAutor(recogeMusica.getString(3));
                
                musica.setDiscografica(recogeMusica.getString(4));
                musica.setLanzamiento(recogeMusica.getString(5));
                musica.setGenero(recogeMusica.getString(6));
                
                musica.setAdministrada(recogeMusica.getBoolean(7));
                musica.setAlbum(recogeMusica.getString(8));
                musica.setTrailer(recogeMusica.getString(9));
                musica.setValoracion(recogeMusica.getInt(10));
                
                musica.setRecomendacion(recogeMusica.getBoolean(11));
                musica.setEscuchado(recogeMusica.getBoolean(12));
                musica.setComentario(recogeMusica.getString(13));
        }

        return musica;
        
    }

    /**
     * Consulta que calcula la valoración media de los libros para mostrarla en la vista.
     * @return
     * @throws SQLException 
     */
    public double valoracionMediaMusica() throws SQLException {
        double valoracionMedia = 10;
        ResultSet recogeMusica;
        recogeMusica = ejecutaConsulta("SELECT avg(valoracion) "
                + "  FROM registra_musica rm "
                + "WHERE rm.ASIN_musica = '" + ASIN + "'");

        while (recogeMusica.next()) {
            valoracionMedia = recogeMusica.getDouble(1);
        }

        return valoracionMedia;
    }

    /**
     * Borra la consulta que queramos al clicar en la vista
     * @return
     * @throws SQLException 
     */
    public boolean borrarMusicaDeMiLista() throws SQLException {
        String inserccion = "DELETE FROM registra_musica WHERE ASIN_musica LIKE '"
                + ASIN + "' AND nombre_usuario LIKE '" + usuario + "'";
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
    public boolean actualizarMusicaDeMiLista(int valoracionPersona, int recomendacion, int escuchado) throws SQLException {
        String inserccion = "UPDATE registra_musica SET valoracion = " + valoracionPersona + " , recomendacion = " + recomendacion + ",escuchado=" + escuchado + "  WHERE ASIN_musica LIKE '"
                + ASIN + "' AND nombre_usuario LIKE '" + usuario + "'";
        boolean resul = insertarOActualizarCampos(inserccion);
        return resul;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getASIN() {
        return ASIN;
    }

    public void setASIN(String ASIN) {
        this.ASIN = ASIN;
    }

}
