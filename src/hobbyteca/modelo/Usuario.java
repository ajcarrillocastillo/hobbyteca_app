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
public class Usuario {

    private String nombreCompleto;
    private String nombreUsuario;
    private String email;
    private String contrasena;
    private boolean esAdministrador;
    private String fechaNacimiento;

    /*
    Constructores
    
    */
    public Usuario() {
        this.nombreCompleto = "";
        this.nombreUsuario = "";
        this.email = "";
        this.contrasena = "";
        this.esAdministrador = false;
        this.fechaNacimiento = "";
    }

    public Usuario(String nombreCompleto, String nombreUsuario, String email, String contrasena, boolean esAdministrador, String fechaNacimiento) {
        nombreCompleto = this.nombreCompleto;
        nombreUsuario = this.nombreUsuario;
        email = this.email;
        contrasena = this.contrasena;
        esAdministrador = this.esAdministrador;
        fechaNacimiento = this.fechaNacimiento;
    }

    /**
     * @return the nombreCompleto
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * @param nombreCompleto the nombreCompleto to set
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * @return the esAdministrador
     */
    public boolean EsAdministrador() {
        return esAdministrador;
    }

    /**
     * @param esAdministrador the esAdministrador to set
     */
    public void setEsAdministrador(boolean esAdministrador) {
        this.esAdministrador = esAdministrador;
    }

    /**
     * @return the fechaNacimiento
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

}
