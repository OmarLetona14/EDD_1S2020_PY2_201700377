/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.model;

import java.util.List;

/**
 *
 * @author Omar
 */
public class JsonCategory {

    public List<JsonBook> getJsonBooks() {
        return books;
    }

    public void setBooks(List<JsonBook> books) {
        this.books = books;
    }
    private List<JsonBook> books;
    private String categoryName;
    private String carnet;

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    public JsonCategory(Category c, String carnet, List<JsonBook> jsonBook){
        this.categoryName = c.getCategoryName();
        this.books = jsonBook;
        this.carnet = carnet;
    }
}
