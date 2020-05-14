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
public class CREAR_LIBRO {
    
    private int ISBN;
    private int Anio;
    private String Idioma;
    private String Titulo;
    private String Editorial;
    private String Autor;
    private int Edicion; 
    private String Categoria;

    public CREAR_LIBRO(int ISBN, int Anio, String Idioma, String Titulo, String Editorial, String Autor, int Edicion, String Categoria) {
        this.ISBN = ISBN;
        this.Anio = Anio;
        this.Idioma = Idioma;
        this.Titulo = Titulo;
        this.Editorial = Editorial;
        this.Autor = Autor;
        this.Edicion = Edicion;
        this.Categoria = Categoria;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public int getAnio() {
        return Anio;
    }

    public void setAnio(int Anio) {
        this.Anio = Anio;
    }

    public String getIdioma() {
        return Idioma;
    }

    public void setIdioma(String Idioma) {
        this.Idioma = Idioma;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getEditorial() {
        return Editorial;
    }

    public void setEditorial(String Editorial) {
        this.Editorial = Editorial;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String Autor) {
        this.Autor = Autor;
    }

    public int getEdicion() {
        return Edicion;
    }

    public void setEdicion(int Edicion) {
        this.Edicion = Edicion;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }
    
    
    
    
}
