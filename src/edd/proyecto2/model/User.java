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
public class User {

    public User(int carnet, String nombre, String apellido, String carrera, String password, ROL rol) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carrera = carrera;
        this.password = password;
        this.rol = rol;
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

    public ROL getRol() {
        return rol;
    }

    public void setRol(ROL rol) {
        this.rol = rol;
    }
    private int carnet;
    private String nombre;
    private String apellido;
    private String carrera;
    private String password;
    private ROL rol;
            
    enum ROL{
        ADMINISTRADOR,
        USUARIO
    }
    
    
    
    
    
}
