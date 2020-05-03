/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.view;

import edd.proyecto2.model.Book;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

/**
 *
 * @author Omar
 */
public class BookCard extends JPanel {
    
    private Book currentBook;
    private JLabel tituloNombreLbl;
    private JLabel isbnLbl;
    private JLabel autorLbl;
    private JLabel editorialLbl;
    private JLabel anioLbl;
    private JLabel edicionLbl;
    private JLabel idiomaLbl;
    private JLabel categoriaLbl;
    private JLabel usuarioLbl;
    
    public BookCard(Book _book, int x, int y,JScrollPane container){
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.currentBook = _book;
        this.setBounds(x, y, 250, 320);
        organizeComponents();
        container.add(this);
    }
    private void organizeComponents(){
        this.tituloNombreLbl = new JLabel("Titulo: " + currentBook.getTitulo());
        this.isbnLbl = new JLabel("ISBN: " + currentBook.getISBN());
        this.autorLbl = new JLabel("Autor: " + currentBook.getAutor());
        this.editorialLbl = new JLabel("Editorial: " + currentBook.getEditorial());
        this.anioLbl = new JLabel("Año: " + currentBook.getAnio());
        this.edicionLbl = new JLabel("Edicion: " + currentBook.getEdicion());
        this.idiomaLbl = new JLabel("Idioma: " + currentBook.getIdioma());
        this.categoriaLbl = new JLabel("Categoria: " + currentBook.getCategory().getCategoryName());
        this.add(tituloNombreLbl);
        this.add(isbnLbl);
        this.add(autorLbl);
        this.add(editorialLbl);
        this.add(anioLbl);
        this.add(edicionLbl);
        this.add(idiomaLbl);
        this.add(categoriaLbl);
    }
    
    
    
}
