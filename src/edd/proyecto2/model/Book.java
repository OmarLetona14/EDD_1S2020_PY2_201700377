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

    public Book(int ISBN, String titulo, String autor, String editorial, int anio, String edicion, String idioma, User usuario) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.anio = anio;
        this.edicion = edicion;
        this.idioma = idioma;
        this.usuario = usuario;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
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

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
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
    
    
    private int ISBN;
    private String titulo;
    private String autor;
    private String editorial;
    private int anio;
    private String edicion;
    private String idioma;
    private User usuario;

    @Override
    public int compareTo(Book o) {
        return this.getTitulo().compareTo(o.getTitulo());
    }
    
    
}
