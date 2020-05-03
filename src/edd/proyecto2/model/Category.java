/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.model;

import edd.proyecto2.structure.BTreeBook;

/**
 *
 * @author Omar
 */
public class Category implements Comparable<Category>{

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public BTreeBook getBooks() {
        return books;
    }

    public void setBooks(BTreeBook books) {
        this.books = books;
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
        this.books = new BTreeBook(5);
    }
    
    public Category(){
        this.books = new BTreeBook(5);
    }
    
    private String categoryName;
    private BTreeBook books;

    @Override
    public int compareTo(Category o) {
        return this.getCategoryName().compareTo(o.getCategoryName());
    }
    
}
