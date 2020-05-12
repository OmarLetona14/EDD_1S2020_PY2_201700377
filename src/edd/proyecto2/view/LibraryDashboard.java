/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.view;

import edd.proyecto2.files.JSONFileUpload;
import edd.proyecto2.model.LocalData;
import java.awt.FileDialog;
import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author Omar
 */
public class LibraryDashboard extends javax.swing.JFrame {
    
    JSONFileUpload uploadBooks;
    /**
     * Creates new form LibraryDashboard
     */
    public LibraryDashboard() {
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

        cargaMasivaBtn = new javax.swing.JButton();
        atrasBtn = new javax.swing.JButton();
        verLibrosBtn = new javax.swing.JButton();
        agregarLibro = new javax.swing.JButton();
        eliminarLibroBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cargaMasivaBtn.setText("Carga masiva");
        cargaMasivaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargaMasivaBtnActionPerformed(evt);
            }
        });

        atrasBtn.setText("Atras");
        atrasBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atrasBtnActionPerformed(evt);
            }
        });

        verLibrosBtn.setText("Ver libros");
        verLibrosBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verLibrosBtnActionPerformed(evt);
            }
        });

        agregarLibro.setText("Agregar libro a mi biblioteca");
        agregarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarLibroActionPerformed(evt);
            }
        });

        eliminarLibroBtn.setText("Eliminar libro");
        eliminarLibroBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarLibroBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(eliminarLibroBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cargaMasivaBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(atrasBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(verLibrosBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(agregarLibro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(verLibrosBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(agregarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(eliminarLibroBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cargaMasivaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(atrasBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargaMasivaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargaMasivaBtnActionPerformed
        uploadBooks = new JSONFileUpload();
        FileDialog openFile = new FileDialog(this, "Abrir", FileDialog.LOAD);
        openFile.setFilenameFilter((File dir, String name) -> name.endsWith(".json"));
        openFile.setVisible(true);
        if(openFile.getFile()!=null){
            String ruta = openFile.getFiles()[0].getAbsolutePath();
            try{
                System.out.println(ruta);
                uploadBooks.uploadBookDocument(ruta);
                JOptionPane.showMessageDialog(this, "Libros cargados correctamente",
                        "Libros cargados", JOptionPane.INFORMATION_MESSAGE);
                LocalData.currentUser.getCategories().print(LocalData.currentUser.getRoot());
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, "Ocurrio un error al intentar leer los datos",
                        "Error al leer los datos", JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());
            }
            
        }
    }//GEN-LAST:event_cargaMasivaBtnActionPerformed

    private void atrasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atrasBtnActionPerformed
        this.dispose();
        UserDashboard userDashboard = new UserDashboard();
        userDashboard.setVisible(true);
    }//GEN-LAST:event_atrasBtnActionPerformed

    private void agregarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarLibroActionPerformed
        this.dispose();
        InsertBook insertBook = new InsertBook();
        insertBook.setVisible(true);
    }//GEN-LAST:event_agregarLibroActionPerformed

    private void verLibrosBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verLibrosBtnActionPerformed
        this.dispose();
        BooksWindow booksWindow = new BooksWindow(LocalData.currentUser.getCategories());
        booksWindow.setVisible(true);
    }//GEN-LAST:event_verLibrosBtnActionPerformed

    private void eliminarLibroBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarLibroBtnActionPerformed
        this.dispose();
        DeleteBookMenu deleteBook = new DeleteBookMenu();
        deleteBook.setVisible(true);
    }//GEN-LAST:event_eliminarLibroBtnActionPerformed

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
            java.util.logging.Logger.getLogger(LibraryDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LibraryDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LibraryDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LibraryDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LibraryDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarLibro;
    private javax.swing.JButton atrasBtn;
    private javax.swing.JButton cargaMasivaBtn;
    private javax.swing.JButton eliminarLibroBtn;
    private javax.swing.JButton verLibrosBtn;
    // End of variables declaration//GEN-END:variables
}
