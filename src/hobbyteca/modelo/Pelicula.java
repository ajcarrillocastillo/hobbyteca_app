/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.modelo;

/**
 *
 * @author xRafa
 */
public class Pelicula {

    private String titulo;
    private String autor;
    private String productora;
    private String director;
    
    private String lanzamiento;
    private String genero;
    private String descripcion;
    
    private boolean administrada;
    private String trailer;
    private int valoracion;
    
    private boolean recomendacion;
    private boolean visualizado;
    private String comentario;

    public Pelicula(){
    
    }

    public Pelicula(String titulo, String autor, String productora, String director, String lanzamiento, String genero, String descripcion, boolean administrada, String trailer, int valoracion, boolean recomendacion, boolean visualizado, String comentario) {
        this.titulo = titulo;
        this.autor = autor;
        this.productora = productora;
        this.director = director;
        this.lanzamiento = lanzamiento;
        this.genero = genero;
        this.descripcion = descripcion;
        this.administrada = administrada;
        this.trailer = trailer;
        this.valoracion = valoracion;
        this.recomendacion = recomendacion;
        this.visualizado = visualizado;
        this.comentario = comentario;
    }

    


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getProductora() {
        return productora;
    }

    public void setProductora(String productora) {
        this.productora = productora;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getLanzamiento() {
        return lanzamiento;
    }

    public void setLanzamiento(String lanzamiento) {
        this.lanzamiento = lanzamiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getAdministrada() {
        return administrada;
    }

    public void setAdministrada(boolean administrada) {
        this.administrada = administrada;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public boolean getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(boolean recomendacion) {
        this.recomendacion = recomendacion;
    }

    public boolean getVisualizado() {
        return visualizado;
    }

    public void setVisualizado(boolean visualizado) {
        this.visualizado = visualizado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
}
