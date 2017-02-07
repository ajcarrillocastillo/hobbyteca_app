/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.DAO;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jesus
 */
public class UsuarioDAOTest {
    
    public UsuarioDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of devolverUsuarioDAO method, of class UsuarioDAO.
     */
    @Test
    public void testDevolverUsuarioDAO() {
        System.out.println("devolverUsuarioDAO");
        UsuarioDAO expResult = null;
        UsuarioDAO result = UsuarioDAO.devolverUsuarioDAO();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compruebaUsuario method, of class UsuarioDAO.
     */
    @Test
    public void testCompruebaUsuario() throws Exception {
        System.out.println("compruebaUsuario");
        String nombreUsuario = "";
        UsuarioDAO instance = new UsuarioDAO();
        boolean expResult = false;
        boolean result = instance.compruebaUsuario(nombreUsuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of comparaPasswordConUsuario method, of class UsuarioDAO.
     */
    @Test
    public void testComparaPasswordConUsuario() throws Exception {
        System.out.println("comparaPasswordConUsuario");
        String nombreUsuario = "";
        String password = "";
        UsuarioDAO instance = new UsuarioDAO();
        boolean expResult = false;
        boolean result = instance.comparaPasswordConUsuario(nombreUsuario, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compruebaEmail method, of class UsuarioDAO.
     */
    @Test
    public void testCompruebaEmail() throws Exception {
        System.out.println("compruebaEmail");
        String email = "";
        UsuarioDAO instance = new UsuarioDAO();
        boolean expResult = false;
        boolean result = instance.compruebaEmail(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of guardarDatos method, of class UsuarioDAO.
     */
    @Test
    public void testGuardarDatos() throws Exception {
        System.out.println("guardarDatos");
        String nombre = "";
        String nombreUsuario = "";
        String password = "";
        String email = "";
        String fecha_nac = "";
        UsuarioDAO instance = new UsuarioDAO();
        boolean expResult = false;
        boolean result = instance.guardarDatos(nombre, nombreUsuario, password, email, fecha_nac);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
