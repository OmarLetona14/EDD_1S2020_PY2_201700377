/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.view;

import edd.proyecto2.files.GenerateReport;
import edd.proyecto2.model.LocalData;
import javax.swing.JOptionPane;

/**
 *
 * @author Omar
 */
public class ReportDashboard extends javax.swing.JFrame {
    
    GenerateReport report;
    /**
     * Creates new form ReportDashboard
     */
    public ReportDashboard() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        atrasBtn = new javax.swing.JButton();
        arbolALVbtn = new javax.swing.JButton();
        arbolbBtn = new javax.swing.JButton();
        usuariosBtn = new javax.swing.JButton();
        preOrdenBtn = new javax.swing.JButton();
        postOrdenBtn = new javax.swing.JButton();
        inOrdenBtn = new javax.swing.JButton();
        listaNodosBtn = new javax.swing.JButton();
        lagOperacionesBtn = new javax.swing.JButton();

        atrasBtn.setText("Atras");
        atrasBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atrasBtnActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        arbolALVbtn.setText("Categorias");
        arbolALVbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arbolALVbtnActionPerformed(evt);
            }
        });

        arbolbBtn.setText("Libros por categoria ");
        arbolbBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arbolbBtnActionPerformed(evt);
            }
        });

        usuariosBtn.setText("Usuarios");

        preOrdenBtn.setText("Preorden AVL");
        preOrdenBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preOrdenBtnActionPerformed(evt);
            }
        });

        postOrdenBtn.setText("PostOrden AVL");
        postOrdenBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postOrdenBtnActionPerformed(evt);
            }
        });

        inOrdenBtn.setText("InOrden AVL");
        inOrdenBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inOrdenBtnActionPerformed(evt);
            }
        });

        listaNodosBtn.setText("Listado Nodos ");

        lagOperacionesBtn.setText("Lag de operaciones");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lagOperacionesBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(listaNodosBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inOrdenBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(postOrdenBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(preOrdenBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(usuariosBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(arbolbBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(arbolALVbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(arbolALVbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(arbolbBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(usuariosBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(preOrdenBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(postOrdenBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(inOrdenBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(listaNodosBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lagOperacionesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void arbolALVbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arbolALVbtnActionPerformed
       report = new GenerateReport();
        try{
            String content = LocalData.currentUser.getCategories().printTree(LocalData.currentUser.getRoot());
            if(!content.equals("")){
                report.generate("Categorias", content);
                JOptionPane.showMessageDialog(this, "Reporte generado correctamente", "Reporte Generado", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this, "Arbol vacio", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Ocurrio un error al intentar generar el arbol", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_arbolALVbtnActionPerformed

    private void atrasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atrasBtnActionPerformed
        this.dispose();
        UserDashboard userDashboard = new UserDashboard();
        userDashboard.setVisible(true);
    }//GEN-LAST:event_atrasBtnActionPerformed

    private void arbolbBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arbolbBtnActionPerformed
        this.dispose();
        ReportBookPerCategory reportBooks  = new ReportBookPerCategory();
        reportBooks.setVisible(true);
    }//GEN-LAST:event_arbolbBtnActionPerformed

    private void preOrdenBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preOrdenBtnActionPerformed
        report = new GenerateReport();
        try{
            LocalData.currentUser.getCategories().setIteracion(0);
            LocalData.currentUser.getCategories().setContenido("");
            LocalData.currentUser.getCategories().ReportPre(LocalData.currentUser.getRoot());
            String content = LocalData.currentUser.getCategories().getContenido();
            report.generate("PreOrdenAVL", content); 
            JOptionPane.showMessageDialog(this, "Reporte generado correctamente", "Generado correctamente", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Ocurrio un error al generar el reporte", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_preOrdenBtnActionPerformed

    private void postOrdenBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_postOrdenBtnActionPerformed
        report = new GenerateReport();
        try{
            LocalData.currentUser.getCategories().setIteracion(0);
            LocalData.currentUser.getCategories().setContenido("");
            LocalData.currentUser.getCategories().ReportPost(LocalData.currentUser.getRoot());
            String content = LocalData.currentUser.getCategories().getContenido();
            report.generate("PostOrdenAVL", content); 
            JOptionPane.showMessageDialog(this, "Reporte generado correctamente", "Generado correctamente", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Ocurrio un error al generar el reporte", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_postOrdenBtnActionPerformed

    private void inOrdenBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inOrdenBtnActionPerformed
        report = new GenerateReport();
        try{
            LocalData.currentUser.getCategories().setIteracion(0);
            LocalData.currentUser.getCategories().setContenido("");
            LocalData.currentUser.getCategories().ReportIn(LocalData.currentUser.getRoot());
            String content = LocalData.currentUser.getCategories().getContenido();
            report.generate("InOrdenAVL", content); 
            JOptionPane.showMessageDialog(this, "Reporte generado correctamente", "Generado correctamente", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Ocurrio un error al generar el reporte", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_inOrdenBtnActionPerformed

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
            java.util.logging.Logger.getLogger(ReportDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReportDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton arbolALVbtn;
    private javax.swing.JButton arbolbBtn;
    private javax.swing.JButton atrasBtn;
    private javax.swing.JButton inOrdenBtn;
    private javax.swing.JButton lagOperacionesBtn;
    private javax.swing.JButton listaNodosBtn;
    private javax.swing.JButton postOrdenBtn;
    private javax.swing.JButton preOrdenBtn;
    private javax.swing.JButton usuariosBtn;
    // End of variables declaration//GEN-END:variables
}
