/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.vista;

import hobbyteca.controlador.ControladorAgregarLibro;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author jesus
 */
public class AgregarMiListaLibro extends javax.swing.JDialog {

    private ControladorAgregarLibro controladorAgregarLibro;
    private DefaultListModel jList;
    private String datosLis[];
    private int tamannoLista;
    private Principal vistaPrincipal;
    /**
     * Creates new form AgregarLibros
     * @param parent
     * @param modal
     */
    public AgregarMiListaLibro(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        controladorAgregarLibro = new ControladorAgregarLibro(this);

        try {
            jList = controladorAgregarLibro.LLenarLista(jListBuscador);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error con la bdd contacte con el administrador", "Error inesperado", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(AgregarMiListaLibro.class.getName()).log(Level.SEVERE, null, ex);
        }
        tamannoLista = jList.getSize();
        datosLis = new String[tamannoLista];
//recorremos nuestro arreglo 
        for (int i = 0; i < tamannoLista; i++) {
            datosLis[i] = jList.get(i).toString();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelContenedor = new javax.swing.JPanel();
        jScrollPaneBuscador = new javax.swing.JScrollPane();
        jListBuscador = new javax.swing.JList();
        jTextFieldBuscador = new javax.swing.JTextField();
        jLabelBuscador = new javax.swing.JLabel();
        jButtonAgregar = new javax.swing.JButton();
        jLabelTextoInformacion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Libro");

        jScrollPaneBuscador.setViewportView(jListBuscador);

        jTextFieldBuscador.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextFieldBuscadorCaretUpdate(evt);
            }
        });

        jLabelBuscador.setText("Buscador");

        jButtonAgregar.setText("Agregar");
        jButtonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarActionPerformed(evt);
            }
        });

        jLabelTextoInformacion.setForeground(new java.awt.Color(126, 126, 126));
        jLabelTextoInformacion.setText("¡Ojo! ¡El buscador distingue entre mayúsculas y minúsculas!");

        javax.swing.GroupLayout jPanelContenedorLayout = new javax.swing.GroupLayout(jPanelContenedor);
        jPanelContenedor.setLayout(jPanelContenedorLayout);
        jPanelContenedorLayout.setHorizontalGroup(
            jPanelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContenedorLayout.createSequentialGroup()
                .addGroup(jPanelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelContenedorLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanelContenedorLayout.createSequentialGroup()
                                .addComponent(jLabelBuscador)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldBuscador)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonAgregar))
                            .addComponent(jScrollPaneBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelContenedorLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabelTextoInformacion)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanelContenedorLayout.setVerticalGroup(
            jPanelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContenedorLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jScrollPaneBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelBuscador)
                    .addComponent(jButtonAgregar))
                .addGap(18, 18, 18)
                .addComponent(jLabelTextoInformacion)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldBuscadorCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextFieldBuscadorCaretUpdate
        controladorAgregarLibro.filtrarLista(jList, datosLis, tamannoLista);


    }//GEN-LAST:event_jTextFieldBuscadorCaretUpdate

    private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarActionPerformed
if (jListBuscador.getSelectedIndex()!= -1){
        String valorSeleccionado = new String((String) jListBuscador.getSelectedValue());
        try {
            if (controladorAgregarLibro.insertarLibroMiLista(valorSeleccionado)) {
                JOptionPane.showMessageDialog(this, "Se ha guardado Correctamente", "Info", JOptionPane.INFORMATION_MESSAGE);
                try {
            jList = controladorAgregarLibro.LLenarLista(jListBuscador);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error con la bdd contacte con el administrador", "Error inesperado", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(AgregarMiListaLibro.class.getName()).log(Level.SEVERE, null, ex);
        }
        tamannoLista = jList.getSize();
        datosLis = new String[tamannoLista];
//recorremos nuestro arreglo 
        for (int i = 0; i < tamannoLista; i++) {
            datosLis[i] = jList.get(i).toString();
        }
            } else {
                JOptionPane.showMessageDialog(this, "No se ha guardado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error con la bdd contacte con el administrador", "Error inesperado", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(AgregarMiListaLibro.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    }//GEN-LAST:event_jButtonAgregarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AgregarMiListaLibro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarMiListaLibro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarMiListaLibro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarMiListaLibro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AgregarMiListaLibro dialog = new AgregarMiListaLibro(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    public JList getjListBuscador() {
        return jListBuscador;
    }

    public void setjListBuscador(JList jListBuscador) {
        this.jListBuscador = jListBuscador;
    }

    public JTextField getjTextFieldBuscador() {
        return jTextFieldBuscador;
    }

    public void setjTextFieldBuscador(JTextField jTextFieldBuscador) {
        this.jTextFieldBuscador = jTextFieldBuscador;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JLabel jLabelBuscador;
    private javax.swing.JLabel jLabelTextoInformacion;
    private javax.swing.JList jListBuscador;
    private javax.swing.JPanel jPanelContenedor;
    private javax.swing.JScrollPane jScrollPaneBuscador;
    private javax.swing.JTextField jTextFieldBuscador;
    // End of variables declaration//GEN-END:variables
}
