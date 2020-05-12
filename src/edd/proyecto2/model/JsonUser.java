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
public class JsonUser extends User{
    public JsonUser(String carnet, String nombre, String apellido, String carrera, String password) {
        super(Integer.valueOf(carnet), nombre, apellido, carrera, password);
    }
}
