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
    
    public String getReport(){
        SimplyLinkedListUser currentList;
        String content="rankdir =LR"+ "\n";
        content += "struct1 [label = \" ";
        for(int i=0; i<listOfUsers.length;i++){
            if(i!=listOfUsers.length-1){
                content += "<f" + i + "> " + i +" |";
            }else{
                content += "<f" + i + "> " + i + " ";
            }
        }
        content += " \" ];" + "\n";
        for(int x=0; x<listOfUsers.length;x++){
            currentList = listOfUsers[x];
            if(currentList!=null){
                NodeUser aux = currentList.first;
                while(aux!=null){
                    content += "\"node" + aux.info.getCarnet() + "\"[label= \" " + aux.info.getNombre() 
                            + " "+ aux.info.getApellido() + " "+ aux.info.getCarnet() +" \" ];"+"\n"; 
                    aux = aux.next;
                }
                NodeUser aux2 = currentList.first;
                content += "struct1:f" + x + " -> ";
                while(aux2!=null){
                    if(aux2!=currentList.last){
                        content += "\"node" + aux2.info.getCarnet() + "\" -> ";
                    }else{
                        content += "\"node" + aux2.info.getCarnet() + "\";" + "\n";
                    }
                    aux2 = aux2.next;
                }
            }
        }
        return content;
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
