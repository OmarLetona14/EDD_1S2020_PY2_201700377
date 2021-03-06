/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.view;

import edd.proyecto2.model.Book;
import edd.proyecto2.model.Category;
import edd.proyecto2.model.ELIMINAR_LIBRO;
import edd.proyecto2.model.LocalData;
import edd.proyecto2.model.Operation;
import java.awt.HeadlessException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Omar
 */
public class DeleteBookMenu extends javax.swing.JFrame {
    private boolean deleted;
    /**
     * Creates new form DeleteBookMenu
     * @param book
     */
    public DeleteBookMenu(){
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        LocalData.currentWindow = this;
    }
    
    private List<Category> getCategories(){
        if(LocalData.localEdit){
            return LocalData.currentUser.getCategories().getAll(LocalData.currentUser.getRoot());
        }else{
            return LocalData.virtualLibrary.getAll(LocalData.virtualRoot);
        }
    }
    
    private void deleteFromLocal(boolean showWindow){
        List<Category> categorias = LocalData.currentUser.getCategories().getAll(LocalData.currentUser.getRoot());
            for(Category c: categorias){
                if(c!=null){
                    for(Book book: c.getBooks().getAllBooks()){
                        if(book!=null){
                            int isbn = Integer.valueOf(isbnTxt.getText());
                            if(book.getISBN()==isbn){
                                if(showWindow){
                                    int answer = JOptionPane.showConfirmDialog(this,"Esta seguro que desea eliminar este libro?" , 
                                    "Eliminacion", JOptionPane.WARNING_MESSAGE);
                                    if(answer==0){
                                        c.getBooks().delete(book);
                                        if(!LocalData.localEdit){
                                            ELIMINAR_LIBRO delete = new ELIMINAR_LIBRO(book.getISBN(), book.getTitulo(), book.getCategory().getCategoryName());
                                            Operation o = new Operation(Operation.operationType.eliminar_libro, delete);
                                            LocalData.operations.add(o);
                                        }
                                        JOptionPane.showMessageDialog(this, "Libro eliminado correctamente", "Eliminado",JOptionPane.OK_OPTION);
                                        deleted=true;
                                    }else{
                                        return;
                                    }
                                }
                                
                            }
                        }
                    }
                }
            }
    
    }
    
    private void deleteFromRemote(){
        deleteFromLocal(false);
         List<Category> categorias = LocalData.virtualLibrary.getAll(LocalData.virtualRoot);
            for(Category c: categorias){
                if(c!=null){
                    for(Book book: c.getBooks().getAllBooks()){
                        if(book!=null){
                            int isbn = Integer.valueOf(isbnTxt.getText());
                            if(book.getISBN()==isbn){
                                    int answer = JOptionPane.showConfirmDialog(this,"Esta seguro que desea eliminar este libro?" , 
                                    "Eliminacion", JOptionPane.WARNING_MESSAGE);
                                    if(answer==0){
                                        c.getBooks().delete(book);
                                        if(!LocalData.localEdit){
                                            ELIMINAR_LIBRO delete = new ELIMINAR_LIBRO(book.getISBN(), book.getTitulo(), book.getCategory().getCategoryName());
                                            Operation o = new Operation(Operation.operationType.eliminar_libro, delete);
                                            LocalData.operations.add(o);
                                        }
                                        JOptionPane.showMessageDialog(this, "Libro eliminado correctamente", "Eliminado",JOptionPane.OK_OPTION);
                                        deleted=true;
                                    }else{
                                        return;
                                    }
                                }
                        }
                    }
                }
            }
    }
    
    private void deleteByISBN(){
        deleted=false;
        try{
            if(LocalData.localEdit){
                deleteFromLocal(true);
            }else{
                deleteFromRemote();
            }
            if(!deleted){
                JOptionPane.showMessageDialog(this, "No se encontro el libro especificado", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }catch(NumberFormatException | HeadlessException e){
            JOptionPane.showMessageDialog(this, "Ocurrio un error al intentar dar de baja el libro", "Error", JOptionPane.ERROR_MESSAGE);
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
        eliminarNombreBtn = new javax.swing.JButton();
        atrasBtn = new javax.swing.JButton();
        eliminarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Eliminar por ISBN");

        jLabel2.setText("Eliminar por nombre");

        eliminarNombreBtn.setText("Eliminar por nombre");
        eliminarNombreBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarNombreBtnActionPerformed(evt);
            }
        });

        atrasBtn.setText("Atras");
        atrasBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atrasBtnActionPerformed(evt);
            }
        });

        eliminarBtn.setText("Eliminar");
        eliminarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(isbnTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(eliminarBtn))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(eliminarNombreBtn))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(atrasBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(isbnTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eliminarBtn))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(eliminarNombreBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(atrasBtn)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void eliminarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarBtnActionPerformed
        try{
            deleteByISBN();
            this.dispose();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Ocurrio un error al intentar eliminar el libro", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_eliminarBtnActionPerformed

    private void atrasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atrasBtnActionPerformed
        this.dispose();
        LibraryDashboard libraryDashboard = new LibraryDashboard();
        libraryDashboard.setVisible(true);
    }//GEN-LAST:event_atrasBtnActionPerformed

    private void eliminarNombreBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarNombreBtnActionPerformed
        LocalData.onDeleteOperation = true;
        BooksWindow booksWindow = null;
        if(LocalData.localEdit){
            booksWindow = new BooksWindow(LocalData.currentUser.getCategories());
        }else{
            booksWindow = new BooksWindow(LocalData.virtualLibrary);
        }
        booksWindow.setVisible(true);
    }//GEN-LAST:event_eliminarNombreBtnActionPerformed

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
            java.util.logging.Logger.getLogger(DeleteBookMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeleteBookMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeleteBookMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeleteBookMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeleteBookMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atrasBtn;
    private javax.swing.JButton eliminarBtn;
    private javax.swing.JButton eliminarNombreBtn;
    private javax.swing.JTextField isbnTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
