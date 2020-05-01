/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.structure;

import edd.proyecto2.model.User;
import edd.proyecto2.node.NodeUser;

/**
 *
 * @author Omar
 */
public class SimplyLinkedListUser {
    
    private NodeUser first;
    private NodeUser last;
    int size=1;
    
    public SimplyLinkedListUser(){
        first = null;
    }
    
    public int listSize(){
        return size;
    }
    
    public boolean empty(){
        return(first==null);
    }
    
    public void addToFinal(User user){
        NodeUser newNodo = new NodeUser(user);
        if(empty()){   
            first = newNodo;
            last = newNodo;
        }else{
            first.next = newNodo; 
            last = newNodo;
        }
       size++;
    }
    

    public boolean serch(int carnet){
        NodeUser aux = first;
        boolean found = false;
        while(aux != null && found!=true){
            if(carnet==aux.info.getCarnet()){
                found = true;
            }else{
                aux = aux.next;
            }
        }
        return found;
    }
    
    public void edit(User user, int carnet){
        if(serch(carnet)){
            NodeUser aux = first;
            while(aux.info.getCarnet() != carnet){
                aux = aux.next;
            }
            aux.info.setNombre(user.getNombre());
            aux.info.setApellido(user.getApellido());
            aux.info.setCarrera(user.getCarrera());
            aux.info.setPassword(user.getPassword());
        }
    
    }
   
    public void delete(int carnet){
        if(serch(carnet)){
            if(first.info.getCarnet()==carnet){
                first = first.next;
            }else{
                NodeUser aux = first;
                while(aux.next.info.getCarnet()!=carnet){
                    aux = aux.next;
                }
                NodeUser next = aux.next.next;
                aux.next = next;
            }
            size--;
        }
    }
    
    public String print(){
        String listInfo = "";
        NodeUser aux = first;
        while(aux!=null){
            listInfo += "| Estudiante = " + aux.info.getNombre()+ " " + aux.info.getApellido() + " |";
            aux = aux.next;
        }
        return listInfo;
    }
    
}
