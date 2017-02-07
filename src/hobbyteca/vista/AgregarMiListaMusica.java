/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hobbyteca.vista;

import hobbyteca.controlador.ControladorAgregarMusica;
import hobbyteca.controlador.TableModelNoEditable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author jesus
 */
public class AgregarMiListaMusica extends javax.swing.JDialog {

    private ControladorAgregarMusica controladorMusica;

    private int tamannoLista;
    private Principal vistaPrincipal;
      private TableModelNoEditable modeloTabla = new TableModelNoEditable();
        
    /**
     * Creates new form AgregarMusica
     * @param parent
     * @param modal
     */
    public AgregarMiListaMusica(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        controladorMusica = new ControladorAgregarMusica(this);
       jTableMusica.setModel(modeloTabla);

        try {
            controladorMusica.CrearTablaMusica(modeloTabla);
          controladorMusica.LLenarTablaMusica(modeloTabla);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error con la bdd contacte con el administrador", "Error inesperado", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(AgregarMiListaMusica.class.getName()).log(Level.SEVERE, null, ex);
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
        jScrollPaneMusica = new javax.swing.JScrollPane();
        jTableMusica = new javax.swing.JTable();
        jTextFieldBuscador = new javax.swing.JTextField();
        jLabelBuscador = new javax.swing.JLabel();
        jButtonAgregar = new javax.swing.JButton();
        jLabelTextoInformacion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Música");

        jTableMusica.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jScrollPaneMusica.setViewportView(jTableMusica);

        jTextFieldBuscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldBuscadorKeyTyped(evt);
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
                        .addGap(26, 26, 26)
                        .addComponent(jLabelTextoInformacion))
                    .addGroup(jPanelContenedorLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPaneMusica, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanelContenedorLayout.createSequentialGroup()
                                .addComponent(jLabelBuscador)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonAgregar)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelContenedorLayout.setVerticalGroup(
            jPanelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContenedorLayout.createSequentialGroup()
                .addComponent(jScrollPaneMusica, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelBuscador)
                    .addComponent(jButtonAgregar))
                .addGap(18, 18, 18)
                .addComponent(jLabelTextoInformacion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jPanelContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarActionPerformed

        if (jTableMusica.getSelectedRow()!= -1){
        int fila = jTableMusica.getSelectedRow();
        try {
            if (controladorMusica.insertarMusicaMiLista(jTableMusica.getValueAt(fila, 1).toString())) {
                JOptionPane.showMessageDialog(this, "Se ha guardado Correctamente", "Info", JOptionPane.INFORMATION_MESSAGE);
                try {
           controladorMusica.LLenarTablaMusica(modeloTabla);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error con la bdd contacte con el administrador", "Error inesperado", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(AgregarMiListaMusica.class.getName()).log(Level.SEVERE, null, ex);
        }

            } else {
                JOptionPane.showMessageDialog(this, "No se ha guardado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error con la bdd contacte con el administrador", "Error inesperado", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(AgregarMiListaMusica.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    }//GEN-LAST:event_jButtonAgregarActionPerformed

    private void jTextFieldBuscadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscadorKeyTyped
           controladorMusica.buscador(jTextFieldBuscador, jTableMusica);
    }//GEN-LAST:event_jTextFieldBuscadorKeyTyped

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
            java.util.logging.Logger.getLogger(AgregarMiListaMusica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarMiListaMusica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarMiListaMusica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarMiListaMusica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AgregarMiListaMusica dialog = new AgregarMiListaMusica(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel jPanelContenedor;
    private javax.swing.JScrollPane jScrollPaneMusica;
    private javax.swing.JTable jTableMusica;
    private javax.swing.JTextField jTextFieldBuscador;
    // End of variables declaration//GEN-END:variables
}
