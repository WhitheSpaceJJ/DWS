package pantallas;

import prueba.WebSocketClient;

import javax.swing.JOptionPane;

public class PrincipalCliente extends javax.swing.JFrame {

    private static PrincipalCliente instance;

    private PrincipalCliente() {
        initComponents();
    }
    
     
    public static PrincipalCliente getInstance() {
        if (instance == null) {
            instance = new PrincipalCliente();
        }
        return instance;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuOpciones = new javax.swing.JMenu();
        jMenuItemMensajesEspecificos = new javax.swing.JMenuItem();
        jMenuItemEnvio = new javax.swing.JMenuItem();
        jMenuItemHistorial = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Menu Principal");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 547, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );

        jMenuBar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jMenuOpciones.setText("Administracion");
        jMenuOpciones.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jMenuOpciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuOpcionesActionPerformed(evt);
            }
        });

        jMenuItemMensajesEspecificos.setText("Recepcion de Mensajes");
        jMenuItemMensajesEspecificos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMensajesEspecificosActionPerformed(evt);
            }
        });
        jMenuOpciones.add(jMenuItemMensajesEspecificos);

        jMenuItemEnvio.setText("Envio de Mensajes");
        jMenuItemEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEnvioActionPerformed(evt);
            }
        });
        jMenuOpciones.add(jMenuItemEnvio);

        jMenuItemHistorial.setText("Historial");
        jMenuItemHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemHistorialActionPerformed(evt);
            }
        });
        jMenuOpciones.add(jMenuItemHistorial);

        jMenuBar.add(jMenuOpciones);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuOpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuOpcionesActionPerformed

    }//GEN-LAST:event_jMenuOpcionesActionPerformed

    private void jMenuItemMensajesEspecificosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMensajesEspecificosActionPerformed
        this.setVisible(false);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MensajesEspecificos.getInstance().setVisible(true);
            }
        });
    }//GEN-LAST:event_jMenuItemMensajesEspecificosActionPerformed

    private void jMenuItemEnvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEnvioActionPerformed
        this.setVisible(false);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EnvioMensajes.getInstance().setVisible(true);
            }
        });
    }//GEN-LAST:event_jMenuItemEnvioActionPerformed

    private void jMenuItemHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemHistorialActionPerformed
        this.setVisible(false);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Historial.getInstance().setVisible(true);
            }
        });
    }//GEN-LAST:event_jMenuItemHistorialActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Byye bye ha dado click en cerrar");
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuItem jMenuItemEnvio;
    private javax.swing.JMenuItem jMenuItemHistorial;
    private javax.swing.JMenuItem jMenuItemMensajesEspecificos;
    private javax.swing.JMenu jMenuOpciones;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}