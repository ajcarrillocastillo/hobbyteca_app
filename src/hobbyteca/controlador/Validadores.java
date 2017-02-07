/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.controlador;

import hobbyteca.vista.Registro;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author jesus
 */
public class Validadores {

    private Registro vistaRegistro;
  

    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

   /* private static final String PATTERN_PASSWORD = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,50})";
*/

    /**
     *
     * @param email
     * @return
     */

    public static boolean validateEmail(String email) {
        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);

        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /*public static boolean validatePassword(String password) {
        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_PASSWORD);

        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }*/

    /**
     *
     * @param nombre
     * @param minimo
     * @return
     */


    public  boolean limitadorNombreUsuario(String nombre,int minimo) {
        boolean superaMinimo = false;
        if (nombre.length() >= minimo) {
            superaMinimo = true;

        }
        return superaMinimo;
    }

    /**
     *
     * @param email
     * @param minimo
     * @return
     */
    public boolean limitadorNombreCompleto(String email, int minimo){
        boolean superaMinimo = false;
        if(email.length() >= minimo){
            superaMinimo = true;
        }
        return superaMinimo;
    }

    /**
     *
     * @param password
     * @return
     */
    public String encriptaPassword( String password) {        
        String textoEncriptadoConSHA = DigestUtils.sha1Hex(password);
        return textoEncriptadoConSHA;
    }

}
