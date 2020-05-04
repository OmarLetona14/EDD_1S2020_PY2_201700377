/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.view;

import edd.proyecto2.model.Book;
import edd.proyecto2.model.Category;
import edd.proyecto2.model.LocalData;
import javax.swing.JOptionPane;

/**
 *
 * @author Omar
 */
public class InsertBook extends javax.swing.JFrame {
    
    private Book book;
    private Category currentCategory;
    private boolean error;
    /**
     * Creates new form InsertBook
     */
    public InsertBook() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        initWindow();
    }
    
    private void initWindow(){
        LocalData.categories.getAll(LocalData.root).stream().filter((category) -> (category!=null)).forEach((category) -> {
            cbCategory.addItem(category.getCategoryName());
        });
    }   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cbCategory = new javax.swing.JComboBox();
        nuevaCategoriaBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        isbnTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tituloTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        autorTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        editorialTxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        anioTxt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        edicionTxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        idiomaTxt = new javax.swing.JTextField();
        agregarLibroBtn = new javax.swing.JButton();
        atrasBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Elija la categoria");

        nuevaCategoriaBtn.setText("Nueva categoria");
        nuevaCategoriaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevaCategoriaBtnActionPerformed(evt);
            }
        });

        jLabel2.setText("ISBN");

        jLabel3.setText("Titulo");

        jLabel4.setText("Autor");

        jLabel5.setText("Editorial");

        jLabel6.setText("Año");

        jLabel7.setText("Edicion");

        jLabel8.setText("Idioma");

        agregarLibroBtn.setText("Agregar Libro");
        agregarLibroBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarLibroBtnActionPerformed(evt);
            }
        });

        atrasBtn.setText("Atras");
        atrasBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atrasBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(cbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nuevaCategoriaBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tituloTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                                    .addComponent(isbnTxt)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(autorTxt)
                                    .addComponent(anioTxt)
                                    .addComponent(editorialTxt)
                                    .addComponent(edicionTxt)
                                    .addComponent(idiomaTxt))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(agregarLibroBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(atrasBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nuevaCategoriaBtn))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(isbnTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(agregarLibroBtn))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tituloTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(atrasBtn))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(autorTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(editorialTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(anioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(edicionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(idiomaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevaCategoriaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevaCategoriaBtnActionPerformed
        this.dispose();
        InsertCategory insertCategory = new InsertCategory();
        insertCategory.setVisible(true);
    }//GEN-LAST:event_nuevaCategoriaBtnActionPerformed

    private void agregarLibroBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarLibroBtnActionPerformed
        insertBook();
    }//GEN-LAST:event_agregarLibroBtnActionPerformed

    private void atrasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atrasBtnActionPerformed
        this.dispose();
        LibraryDashboard libraryDashboard = new LibraryDashboard();
        libraryDashboard.setVisible(true);
    }//GEN-LAST:event_atrasBtnActionPerformed
    
    private void insertBook(){
        error= false;
        book = new Book();
        currentCategory = LocalData.categories.searchNode(String.valueOf(cbCategory.getSelectedItem()), LocalData.root, null).getValue();
        if(currentCategory!=null){
            try{
                book.setUsuario(LocalData.currentUser);
                book.setTitulo(tituloTxt.getText());
                book.setAutor(autorTxt.getText());
                book.setISBN(Integer.valueOf(isbnTxt.getText()));
                book.setEditorial(editorialTxt.getText());
                book.setIdioma(idiomaTxt.getText());
                book.setAnio(Integer.valueOf(anioTxt.getText()));
                book.setEdicion(Integer.valueOf(edicionTxt.getText()));
                book.setCategory(currentCategory);
            }catch(Exception e ){
                JOptionPane.showMessageDialog(this, "Ocurrio un error al insertar el libro", "Error", JOptionPane.ERROR_MESSAGE);
                error= true;
            }
            if(!error){
                try{
                    currentCategory.getBooks().insert(book);
                    currentCategory.getBooks().print();
                    JOptionPane.showMessageDialog(this, "Libro insertado correctamente", "Insertado", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    InsertBook insert = new InsertBook();
                    insert.setVisible(true);
                }catch(Exception e){
                    JOptionPane.showMessageDialog(this, "Ocurrio un error al insertar el libro", "Error", JOptionPane.ERROR_MESSAGE);
                }    
            } 
        }     
    }
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
            java.util.logging.Logger.getLogger(InsertBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InsertBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InsertBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InsertBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InsertBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarLibroBtn;
    private javax.swing.JTextField anioTxt;
    private javax.swing.JButton atrasBtn;
    private javax.swing.JTextField autorTxt;
    private javax.swing.JComboBox cbCategory;
    private javax.swing.JTextField edicionTxt;
    private javax.swing.JTextField editorialTxt;
    private javax.swing.JTextField idiomaTxt;
    private javax.swing.JTextField isbnTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JButton nuevaCategoriaBtn;
    private javax.swing.JTextField tituloTxt;
    // End of variables declaration//GEN-END:variables
}
