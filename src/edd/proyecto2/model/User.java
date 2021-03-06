/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.model;

import edd.proyecto2.node.NodeCategory;
import edd.proyecto2.structure.AVLTreeCategory;
import edd.proyecto2.structure.UserHashTable;

/**
 *
 * @author Omar
 */
public class User implements Comparable<User>{

    public AVLTreeCategory getCategories() {
        return categories;
    }

    public void setCategories(AVLTreeCategory categories) {
        this.categories = categories;
    }
    
    private AVLTreeCategory categories;

    public NodeCategory getRoot() {
        return root;
    }

    public void setRoot(NodeCategory root) {
        this.root = root;
    }
    private NodeCategory root;
    
    
    public User(int carnet, String nombre, String apellido, String carrera, String password) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carrera = carrera;
        this.password = password;
        this.categories = new AVLTreeCategory();
        this.root = null;
    }
    
    public User(){
        this.categories = new AVLTreeCategory();
        this.root = null;
    }

    public int getCarnet() {
        return carnet;
    }

    public void setCarnet(int carnet) {
        this.carnet = carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private int carnet;
    private String nombre;
    private String apellido;
    private String carrera;
    private String password;

    @Override
    public int compareTo(User o) {
        return this.getNombre().compareTo(o.getNombre());
    }
            
}
