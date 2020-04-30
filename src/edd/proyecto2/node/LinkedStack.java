/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.node;

import java.util.Stack;

/**
 *
 * @author Omar
 */
public class LinkedStack extends Stack {
    public void addLast(Object o){
        this.push(o);
    }
    
    public Object getLast(){
        return this.peek();
    }
    
    public void removeLast(){
        this.pop();
    }
}
