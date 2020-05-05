/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.structure;

import edd.proyecto2.model.User;

/**
 *
 * @author Omar
 */
public class UserHashTable {
    private final int size;
    private SimplyLinkedListUser listUsers;

    public SimplyLinkedListUser[] getListOfUsers() {
        return listOfUsers;
    }

    public void setListOfUsers(SimplyLinkedListUser[] listOfUsers) {
        this.listOfUsers = listOfUsers;
    }
    private SimplyLinkedListUser[] listOfUsers;
    
    public UserHashTable(int _size){
        this.size = _size;
        listOfUsers = new SimplyLinkedListUser[size];
    }
    
    public int getSize(){
        return this.size;
    }
    
    public User searchUser(String carnet){
        try{
            SimplyLinkedListUser usersByIndex = this.getListOfUsers()[carnetHashFunction(Integer.valueOf(carnet))];
            if(usersByIndex!=null){
                return usersByIndex.serchByCarnet(Integer.valueOf(carnet));
            }else{
                return null;
            }
        }catch(Exception e){
            System.out.println("Ocurrio un error al intentar buscar al usuario");
        }
        return null;
    }
    
    
    public void deleteUser(String carnet){
        try{
            SimplyLinkedListUser usersByIndex = this.getListOfUsers()[carnetHashFunction(Integer.valueOf(carnet))];
            if(usersByIndex!=null){
                usersByIndex.delete(Integer.valueOf(carnet));
            }
        }catch(Exception e){
            System.out.println("Ocurrio un error al intentar eliminar al usuario");
        }
    }
    
    private int carnetHashFunction(int carnet){
        return carnet%getSize();
    }
    
    public boolean insert(User student){
        if(student !=null){
            if(searchUser(String.valueOf(student.getCarnet()))==null){
                int index = hashFunction(student);
                if(isEmpty(index)){
                    System.out.println("El usuario: " + 
                            student.getNombre() +" fue asignado a la posicion " + index);
                    listUsers = new SimplyLinkedListUser();
                    listUsers.addToFinal(student);
                    this.listOfUsers[hashFunction(student)] = listUsers;
                    return true;
                }else{
                    System.out.println("El usuario: " + 
                            student.getNombre() +" fue asignado a la posicion " + index);
                    getListByIndex(index).addToFinal(student);
                }
                System.out.println("Usuario agregado correctamente");
            }
        }
        return false;
    }
    
    public void edit(String carnet, User user){
        try{
            SimplyLinkedListUser usersByIndex = this.getListOfUsers()[carnetHashFunction(Integer.valueOf(carnet))];
            if(usersByIndex!=null){
                usersByIndex.edit(user, Integer.valueOf(carnet));
            }
        }catch(Exception e){
            System.out.println("Ocurrio un error al intentar editar al usuario");
        }
    }
    private SimplyLinkedListUser getListByIndex(int x){
        return this.listOfUsers[x];
    }
    
    public boolean isEmpty(int x){
        return this.listOfUsers[x] == null;
    }
    private int hashFunction(User user){
        return user.getCarnet()%getSize();
    }
    
    public void printHashTable(){
        for(int x = 0; x<this.getListOfUsers().length;x++){
            if(this.getListOfUsers()[x]!=null){
                System.out.println(x+ this.getListOfUsers()[x].print() + "\n");
            }else{
                System.out.println(x + "|  |");
            }
        }
    }
    
}
