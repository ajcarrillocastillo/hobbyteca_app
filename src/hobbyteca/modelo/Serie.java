/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.modelo;

/**
 *
 * @author David
 */
public class Serie {
    
    private String titulo;
    private String director;
    private String productora;
    
    private String lanzamiento;
    private String genero;
    private String descripcion;
    private boolean administrada;
    
    private int capitulos_totales;
    private String trailer;
    private int valoracion;
    
    private boolean recomendacion;
    private int capitulos_vistos;
    private String comentario;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProductora() {
        return productora;
    }

    public void setProductora(String productora) {
        this.productora = productora;
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

    public boolean isAdministrada() {
        return administrada;
    }

    public void setAdministrada(boolean administrada) {
        this.administrada = administrada;
    }

    public int getCapitulos_totales() {
        return capitulos_totales;
    }

    public void setCapitulos_totales(int capitulos_totales) {
        this.capitulos_totales = capitulos_totales;
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

    public boolean isRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(boolean recomendacion) {
        this.recomendacion = recomendacion;
    }

    public int getCapitulos_vistos() {
        return capitulos_vistos;
    }

    public void setCapitulos_vistos(int capitulos_vistos) {
        this.capitulos_vistos = capitulos_vistos;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
}
