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

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

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

    public Category(int idCategory, String categoryName, BTreeBook books) {
        this.idCategory = idCategory;
        this.categoryName = categoryName;
        this.books = books;
    }
    
    private int idCategory;
    private String categoryName;
    private BTreeBook books;

    @Override
    public int compareTo(Category o) {
        return this.getCategoryName().compareTo(o.getCategoryName());
    }
    
}
