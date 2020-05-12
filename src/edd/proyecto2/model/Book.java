/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.model;

/**
 *
 * @author Omar
 */
public class Book implements Comparable <Book>{    

    public Book(int ISBN, String titulo, String autor, String editorial, int anio, int edicion, String idioma, User usuario, Category category) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.anio = anio;
        this.edicion = edicion;
        this.idioma = idioma;
        this.usuario = usuario;
        this.category = category;
    }
    public Book(){}

    public Integer getISBN() {
        return ISBN;
    }

    public Book(Integer ISBN, String titulo, String autor, String editorial, int anio, int edicion, String idioma, User usuario) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.anio = anio;
        this.edicion = edicion;
        this.idioma = idioma;
        this.usuario = usuario;
    }

    public void setISBN(Integer ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getEdicion() {
        return edicion;
    }

    public void setEdicion(int edicion) {
        this.edicion = edicion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    
    private Integer ISBN;
    private String titulo;
    private String autor;
    private String editorial;
    private int anio;
    private int edicion;
    private String idioma;
    private User usuario;
    private Category category;

    @Override
    public int compareTo(Book o) {
        return this.getISBN().compareTo(o.getISBN());
    }
    
    
}
