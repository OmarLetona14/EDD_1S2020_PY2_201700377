/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.view;

import edd.proyecto2.model.Book;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Omar
 */
public class BookDetailsButton extends JButton implements ActionListener {
    private Book currentBook;
    public BookDetailsButton(Book book){
        
        this.setText(book.getTitulo());
        this.setSize(150, 100);
        this.currentBook = book;
        this.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
           BookDetails bookDetails = new BookDetails(currentBook);
           bookDetails.setVisible(true);
        }catch(Exception ex){
        }
    }
}
