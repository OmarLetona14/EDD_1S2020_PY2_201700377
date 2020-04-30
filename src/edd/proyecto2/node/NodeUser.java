/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.node;

import edd.proyecto2.model.User;

/**
 *
 * @author Omar
 */
public class NodeUser {
    
    public User info;
    public NodeUser next;
    
    public NodeUser(User data){
        this.info = data;
        this.next = null;
    }
    
}
