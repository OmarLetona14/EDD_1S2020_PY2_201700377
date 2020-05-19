/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.view;

import edd.proyecto2.model.Book;
import edd.proyecto2.model.LocalData;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Omar
 */
public class BookDetails extends javax.swing.JFrame {
    
    //private Book detailsBook;
    private static Book staticBook;
    private Book currentBook;
    /**
     * Creates new form BookDetails
     * @param book
     */
    public BookDetails(Book book) {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        staticBook = book;
        initWindow();
    }
    
    private void initWindow(){
        if(staticBook!=null){
            isbnTxt.setText(String.valueOf(staticBook.getISBN()));
            tituloTxt.setText(staticBook.getTitulo());
            autorTxt.setText(staticBook.getAutor());
            editorialTxt.setText(staticBook.getEditorial());
            edicionTxt.setText(String.valueOf(staticBook.getEdicion()));
            idiomaTxt.setText(staticBook.getIdioma());
            anioTxt.setText(String.valueOf(staticBook.getAnio()));
            usuarioTxt.setText(staticBook.getUsuario().getNombre() + " " + staticBook.getUsuario().getApellido() + " - " +
                    staticBook.getUsuario().getCarnet());
            categoriaTxt.setText(staticBook.getCategory().getCategoryName());
        }
        
    }
    
    private void edit(){
        try{
            if(LocalData.localEdit){
            currentBook = LocalData.currentUser.getCategories().
                    searchByCategoryName(LocalData.currentUser.getRoot(), staticBook.getCategory().getCategoryName()).getBooks().searchBookTree(staticBook);
            }else{
                currentBook = LocalData.virtualLibrary.searchByCategoryName(LocalData.virtualRoot, staticBook.getCategory().getCategoryName()).getBooks().searchBookTree(staticBook);
            }
            if(currentBook!=null){
                currentBook.setISBN(Integer.valueOf(isbnTxt.getText()));
                currentBook.setTitulo(tituloTxt.getText());
                currentBook.setAutor(autorTxt.getText());
                currentBook.setEditorial(editorialTxt.getText());
                currentBook.setAnio(Integer.valueOf(anioTxt.getText()));
                currentBook.setEdicion(Integer.valueOf(edicionTxt.getText()));
                currentBook.setIdioma(idiomaTxt.getText());
            }
            if(LocalData.currentWindow instanceof BooksWindow){
                LocalData.currentWindow.dispose();
            }
            JOptionPane.showMessageDialog(this, "Libro editado correctamente", "Editado correctamente", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            BooksWindow books = null;
            if(LocalData.localEdit){
                books = new BooksWindow(LocalData.currentUser.getCategories());
            }else{
                books = new BooksWindow(LocalData.virtualLibrary);
            }
           
           books.setVisible(true);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Ocurrio un error al editar el libro", "Error de edicion", JOptionPane.ERROR_MESSAGE);
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

        jLabel1 = new javax.swing.JLabel();
        isbnTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tituloTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        autorTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        editorialTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        edicionTxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        idiomaTxt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        usuarioTxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        categoriaTxt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        anioTxt = new javax.swing.JTextField();
        editBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ISBN");

        isbnTxt.setEditable(false);

        jLabel2.setText("Titulo");

        tituloTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setText("Autor");
        jLabel3.setToolTipText("");

        jLabel4.setText("Editorial");

        jLabel5.setText("Edicion");

        jLabel6.setText("Idioma");

        jLabel7.setText("Usuario");

        usuarioTxt.setEditable(false);

        jLabel8.setText("Categoria");

        jLabel9.setText("Año");

        editBtn.setText("Editar");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel1))
                            .addGap(41, 41, 41)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(isbnTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                .addComponent(autorTxt)
                                .addComponent(tituloTxt)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6)
                                .addComponent(jLabel8)
                                .addComponent(jLabel9))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(usuarioTxt)
                                    .addComponent(editorialTxt)
                                    .addComponent(edicionTxt)
                                    .addComponent(idiomaTxt)
                                    .addComponent(categoriaTxt, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(anioTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)))))
                    .addComponent(jLabel7))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(isbnTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(tituloTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(autorTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(editorialTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(edicionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(idiomaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(anioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(usuarioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(categoriaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(editBtn)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
       edit();
    }//GEN-LAST:event_editBtnActionPerformed

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
            java.util.logging.Logger.getLogger(BookDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new BookDetails(staticBook).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField anioTxt;
    private javax.swing.JTextField autorTxt;
    private javax.swing.JTextField categoriaTxt;
    private javax.swing.JTextField edicionTxt;
    private javax.swing.JButton editBtn;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField tituloTxt;
    private javax.swing.JTextField usuarioTxt;
    // End of variables declaration//GEN-END:variables
}
