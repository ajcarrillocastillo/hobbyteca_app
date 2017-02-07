/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.DAO;

/**
 *
 * @author xRafa
 */

/*Hacer singleton*/

public class NombreUsuarioMenu {
 //Crear el método recoger Nombre Usuario para poder darselo a los multimedia.
    private String nombreUsuario;    
    private static NombreUsuarioMenu ventanaPrincipal = null;

    /**
     *
     * @return
     */
    public static NombreUsuarioMenu devolverVentanaPrincipal() {

        if (ventanaPrincipal != null) {
            return ventanaPrincipal;
        } else {
            ventanaPrincipal = new NombreUsuarioMenu();
            return ventanaPrincipal;
        }
    }
    
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

}
