/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.view;

import edd.proyecto2.model.Book;
import edd.proyecto2.model.Category;
import edd.proyecto2.model.ELIMINAR_CATEGORIA;
import edd.proyecto2.model.LocalData;
import edd.proyecto2.model.Operation;
import edd.proyecto2.structure.AVLTreeCategory;
import edd.proyecto2.table.BookTableModel;
import edd.proyecto2.table.CellManagement;
import edd.proyecto2.table.HeaderManagement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Omar
 */
public class BooksWindow extends javax.swing.JFrame implements MouseListener{
    
    private Category category;
    private BookDetailsButton bookBtn;
    private BookTableModel bookModel;
    private List<Book> libros;
    private JTable booksTable;
    private int filasTabla, columnasTabla;
    private JScrollPane scrollPane; 
    private TableRowSorter sorter = null; 
    private static AVLTreeCategory currentData;
    private static JFrame currentWindow;
    /**
     * Creates new form BooksWindow
     * @param _currentData
     */
    public BooksWindow(AVLTreeCategory _currentData) {
        initComponents();
        LocalData.currentWindow = this;
        setLocationRelativeTo(null);
        setResizable(false);
        initWindow();
        currentData = _currentData;
    }
    
    private void initWindow(){
        currentWindow =this;
        scrollPane = new JScrollPane();
        scrollPane.setSize(booksPane.getWidth(), booksPane.getHeight());
        booksPane.add(scrollPane);
        List<Category> categories = null;
        if(LocalData.localEdit){
            categories = LocalData.currentUser.getCategories().getAll(LocalData.currentUser.getRoot());
        }else{
            categories = LocalData.virtualLibrary.getAll(LocalData.virtualRoot);
        }
        if(categories!=null){
            for(Category c: categories){
                categoriaCb.addItem(c.getCategoryName());
            }
        }
    }
    
    private void fillPanel(){
        libros = new ArrayList();
        if(currentData.searchNode(categoriaCb.getSelectedItem().toString(), currentData.getRoot(), null)!=null){
            category = currentData.searchNode(categoriaCb.getSelectedItem().toString(), currentData.getRoot(), null).getValue();
            libros.addAll(category.getBooks().getAllBooks());       
        }else{
            JOptionPane.showMessageDialog(this, "No hay libros asociados a esta categoria", "Error", JOptionPane.ERROR_MESSAGE);
        }
        fillTable();
    }
    
    public static void closeWindow(){
        currentWindow.dispose();
    }
    
    private void fillTable(){
        booksTable = new JTable();
        booksTable.addMouseListener(this);
        String[] titulos_tabla = {"ISBN", "Titulo", "Autor", "Editorial", "Anio", "Edicion", "Idioma", "Usuario propietario", "Categoria"," "};
        Object[][] datos = obtainMatrix();
        bookModel = new BookTableModel(datos, titulos_tabla);
        booksTable.setModel(bookModel);
        filasTabla = booksTable.getRowCount();
        columnasTabla = booksTable.getColumnCount();
        booksTable.getColumnModel().getColumn(LocalData.ISBN).setCellRenderer(new CellManagement("numerico"));
        booksTable.getColumnModel().getColumn(LocalData.TITULO).setCellRenderer(new CellManagement("texto"));
        booksTable.getColumnModel().getColumn(LocalData.AUTOR).setCellRenderer(new CellManagement("texto"));
        booksTable.getColumnModel().getColumn(LocalData.EDITORIAL).setCellRenderer(new CellManagement("texto"));
        booksTable.getColumnModel().getColumn(LocalData.ANIO).setCellRenderer(new CellManagement("numerico"));
        booksTable.getColumnModel().getColumn(LocalData.EDICION).setCellRenderer(new CellManagement("numerico"));
        booksTable.getColumnModel().getColumn(LocalData.IDIOMA).setCellRenderer(new CellManagement("texto"));
        booksTable.getColumnModel().getColumn(LocalData.USUARIO).setCellRenderer(new CellManagement("texto"));
        booksTable.getColumnModel().getColumn(LocalData.CATEGORIA).setCellRenderer(new CellManagement("texto"));
        booksTable.getColumnModel().getColumn(LocalData.DETALLES).setCellRenderer(new CellManagement("boton"));
        booksTable.getTableHeader().setReorderingAllowed(false);
        booksTable.setRowHeight(25);//tamaño de las celdas
        booksTable.setGridColor(new java.awt.Color(0, 0, 0));
        booksTable.getColumnModel().getColumn(LocalData.ISBN).setPreferredWidth(300);
        booksTable.getColumnModel().getColumn(LocalData.TITULO).setPreferredWidth(800);
        booksTable.getColumnModel().getColumn(LocalData.AUTOR).setPreferredWidth(500);
        booksTable.getColumnModel().getColumn(LocalData.EDITORIAL).setPreferredWidth(800);
        booksTable.getColumnModel().getColumn(LocalData.ANIO).setPreferredWidth(300);
        booksTable.getColumnModel().getColumn(LocalData.EDICION).setPreferredWidth(300);
        booksTable.getColumnModel().getColumn(LocalData.IDIOMA).setPreferredWidth(800);
        booksTable.getColumnModel().getColumn(LocalData.USUARIO).setPreferredWidth(800);
        booksTable.getColumnModel().getColumn(LocalData.CATEGORIA).setPreferredWidth(800);
        booksTable.getColumnModel().getColumn(LocalData.DETALLES).setPreferredWidth(800);
        JTableHeader jtableHeader = booksTable.getTableHeader();
        jtableHeader.setDefaultRenderer(new HeaderManagement());
        booksTable.setTableHeader(jtableHeader);
        scrollPane.setViewportView(booksTable);
    }
    
    private Object[][] obtainMatrix(){
        String information[][] = new String[libros.size()][9];
        for(int i=0; i<information.length;i++){
            information[i][LocalData.ISBN] = libros.get(i).getISBN().toString();
            information[i][LocalData.TITULO] = libros.get(i).getAutor();
            information[i][LocalData.AUTOR] = libros.get(i).getAutor();
            information[i][LocalData.EDITORIAL] = libros.get(i).getEditorial();
            information[i][LocalData.ANIO] = String.valueOf(libros.get(i).getAnio());
            information[i][LocalData.EDICION] = String.valueOf(libros.get(i).getEdicion());
            information[i][LocalData.IDIOMA] = String.valueOf(libros.get(i).getIdioma());
            information[i][LocalData.USUARIO] = String.valueOf(libros.get(i).getUsuario().getCarnet());
            information[i][LocalData.CATEGORIA] = libros.get(i).getCategory().getCategoryName();
        }
        return information;
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
        categoriaCb = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        eliminarCategoriaBtn = new javax.swing.JButton();
        atrasBtn = new javax.swing.JButton();
        mostrarLibrosBtn = new javax.swing.JButton();
        booksPane = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        filtroTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setText("Seleccione la categoria");

        categoriaCb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriaCbActionPerformed(evt);
            }
        });

        jLabel3.setText("Eliminar categoria");

        eliminarCategoriaBtn.setText("Eliminar");
        eliminarCategoriaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarCategoriaBtnActionPerformed(evt);
            }
        });

        atrasBtn.setText("Atras");
        atrasBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atrasBtnActionPerformed(evt);
            }
        });

        mostrarLibrosBtn.setText("Mostrar libros");
        mostrarLibrosBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarLibrosBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout booksPaneLayout = new javax.swing.GroupLayout(booksPane);
        booksPane.setLayout(booksPaneLayout);
        booksPaneLayout.setHorizontalGroup(
            booksPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        booksPaneLayout.setVerticalGroup(
            booksPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 495, Short.MAX_VALUE)
        );

        jLabel2.setText("Filtro");

        filtroTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtroTxtActionPerformed(evt);
            }
        });
        filtroTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                filtroTxtKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(booksPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(categoriaCb, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(eliminarCategoriaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(atrasBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mostrarLibrosBtn)
                        .addGap(39, 39, 39)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(filtroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(categoriaCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(eliminarCategoriaBtn)
                    .addComponent(atrasBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mostrarLibrosBtn)
                    .addComponent(jLabel2)
                    .addComponent(filtroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(booksPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void categoriaCbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriaCbActionPerformed

    }//GEN-LAST:event_categoriaCbActionPerformed

    private void atrasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atrasBtnActionPerformed
        this.dispose();
        LibraryDashboard libraryDashboard = new LibraryDashboard();
        libraryDashboard.setVisible(true);
    }//GEN-LAST:event_atrasBtnActionPerformed

    private void mostrarLibrosBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarLibrosBtnActionPerformed
        fillPanel();
    }//GEN-LAST:event_mostrarLibrosBtnActionPerformed

    private void filtroTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtroTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filtroTxtActionPerformed

    private void filtroTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filtroTxtKeyTyped
    
        filtroTxt.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent evt){
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + filtroTxt.getText(), LocalData.TITULO));
            }  
        });   
        sorter = new TableRowSorter(bookModel);
        booksTable.setRowSorter(sorter);
    }//GEN-LAST:event_filtroTxtKeyTyped

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed

    private void eliminarCategoriaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarCategoriaBtnActionPerformed
        deleteCategory();
    }//GEN-LAST:event_eliminarCategoriaBtnActionPerformed
    
    private Category searchCategory(){
        if(LocalData.localEdit){
            if(LocalData.currentUser.getCategories().searchNode(
                String.valueOf(categoriaCb.getSelectedItem()), LocalData.currentUser.getRoot(), null)!=null){
                return LocalData.currentUser.getCategories().searchNode(
                String.valueOf(categoriaCb.getSelectedItem()), LocalData.currentUser.getRoot(), null).getValue();
            }
        }else{
            if(LocalData.virtualLibrary.searchNode(String.valueOf(categoriaCb.getSelectedItem()), LocalData.virtualRoot, null)!=null){
                return LocalData.virtualLibrary.searchNode(String.valueOf(categoriaCb.getSelectedItem()), LocalData.virtualRoot, null).getValue();
            }   
        }
        return null;
    }
    
    private void deleteFromLocal(Category category, boolean showWindow){
        if(category!=null){
            if(category.getUser()==LocalData.currentUser){
                LocalData.currentUser.setRoot(LocalData.currentUser.getCategories().deleteNode(LocalData.currentUser.getRoot(), category));
                LocalData.currentUser.getCategories().syncRoot(LocalData.currentUser.getRoot());
                if(showWindow){
                    JOptionPane.showMessageDialog(this, "Se elimino correctamente la categoria", "Eliminado correctamente", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    BooksWindow booksWindow = new BooksWindow(LocalData.currentUser.getCategories());
                    booksWindow.setVisible(true);
                } 
            }
        }
    }
    private void deleteFromRemote(Category category){
        deleteFromLocal(category, false);
        LocalData.virtualRoot = LocalData.virtualLibrary.deleteNode(LocalData.virtualRoot, category);
        LocalData.virtualLibrary.syncRoot(LocalData.virtualRoot);
        ELIMINAR_CATEGORIA eliminar = new ELIMINAR_CATEGORIA(category.getCategoryName());
        Operation operation = new Operation(Operation.operationType.eliminar_categoria, eliminar);
        LocalData.operations.add(operation);
        JOptionPane.showMessageDialog(this, "Se elimino correctamente la categoria", "Eliminado correctamente", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
        BooksWindow booksWindow = new BooksWindow(LocalData.virtualLibrary);
        booksWindow.setVisible(true);
    }
    
    private void deleteCategory(){
        Category deleteCategory = searchCategory();
        if(deleteCategory!=null){
            if(deleteCategory.getUser()==LocalData.currentUser){
                if(LocalData.localEdit){
                    try{
                        deleteFromLocal(deleteCategory, true);
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(this, "Ocurrio un error al intentar eliminar la categoria", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }else{
                    try{
                        deleteFromRemote(deleteCategory);
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(this, "Ocurrio un error al intentar eliminar la categoria", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }else{
                JOptionPane.showMessageDialog(this, "Usted no tiene permiso para eliminar esta categoria", "Error", JOptionPane.WARNING_MESSAGE);
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
            java.util.logging.Logger.getLogger(BooksWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BooksWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BooksWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BooksWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new BooksWindow(currentData).setVisible(true);
            
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atrasBtn;
    private javax.swing.JPanel booksPane;
    private javax.swing.JComboBox categoriaCb;
    private javax.swing.JButton eliminarCategoriaBtn;
    private javax.swing.JTextField filtroTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton mostrarLibrosBtn;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
        Book book = libros.get(booksTable.rowAtPoint(e.getPoint()));
        if(book!=null && !LocalData.onDeleteOperation){
            BookDetails bookDetails = new BookDetails(book);
            bookDetails.setVisible(true);
        }else if(book!=null && LocalData.onDeleteOperation){
            try{
                deleteBook(book);
                JOptionPane.showMessageDialog(this, "Se elimino correctamente el libro", "Libro eliminado", JOptionPane.INFORMATION_MESSAGE);
                BooksWindow b = null;
                this.dispose();
                if(LocalData.localEdit){
                    b = new BooksWindow(LocalData.currentUser.getCategories());
                }else{
                    b = new BooksWindow(LocalData.virtualLibrary);
                }
                b.setVisible(true);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this, "Ocurrio un error al eliminar el libro", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void deleteBook(Book _book){
        if(LocalData.localEdit){
            deleteFromLocal(_book);
        }else{
            deleteFromRemote(_book);
        }
    }
    
    private void deleteFromRemote(Book _b){
        deleteFromLocal(_b);
        LocalData.virtualLibrary.getAll(LocalData.virtualRoot).stream().filter((c) -> (c!=null)).forEach((c) -> {
            c.getBooks().getAllBooks().stream().filter((b) -> (b!=null)).filter((b) -> (b==_b)).forEach((b) -> {
                c.getBooks().delete(b);
            });
        });
    }
    
    private void deleteFromLocal(Book book){
        LocalData.currentUser.getCategories().getAll(LocalData.currentUser.getRoot()).stream().filter((c) -> (c!=null)).forEach((c) -> {
            c.getBooks().getAllBooks().stream().filter((b) -> (b!=null)).filter((b) -> (b==book)).forEach((b) -> {
                c.getBooks().delete(b);
            });
        });
    }

    @Override
    public void mousePressed(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
