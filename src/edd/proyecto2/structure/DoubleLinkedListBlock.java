/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.structure;

import edd.proyecto2.model.Block;
import edd.proyecto2.node.NodeBlock;

/**
 *
 * @author Omar
 */
public class DoubleLinkedListBlock {
    
    private NodeBlock first;
    private NodeBlock last;
    private int size;
    
    public DoubleLinkedListBlock(){
        first = null;
    }
    
    public int listSize(){
        return size;
    }
    
    public boolean empty(){
        return(first==null);
    }
    
    public void addToFinal(Block block){
        NodeBlock newNodo = new NodeBlock(block);
        if(empty()){   
            first = newNodo;
            last = newNodo;
            
        }else{
            last.setNext(newNodo);
            newNodo.setPrevious(last);
            last= newNodo;
        }
       size++;
    }
    

    public boolean serch(int index){
        NodeBlock aux = first;
        boolean found = false;
        while(aux != null && found!=true){
            if(index==aux.getInfo().getIndex()){
                found = true;
            }else{
                aux = aux.getNext();
            }
        }
        return found;
    }

    public void delete(int index){
        NodeBlock aux = first;
        NodeBlock ant =null;
        if(serch(index)){
            while(aux!=null){
                if(aux.getInfo().getIndex()==index){
                    if(aux==first){
                        first.setNext(first.getNext());
                        first.setPrevious(null);
                    }else if(aux==last){
                        if(ant!=null){
                            ant.setNext(null);
                            last =ant;
                        }
                    }else{
                        if(ant!=null){
                             ant.setNext(aux.getNext());
                            aux.getNext().setPrevious(ant);
                        }     
                    }
                    size--;
                    return;
                }
                ant = aux;
                aux = aux.getNext();
            }
        }
    }
    
    public String print(){
        String listInfo = "";
        NodeBlock aux = first;
        while(aux!=null){
            listInfo += "| INDEX = " + aux.getInfo().getIndex()+ " TIMESTAMP: " + aux.getInfo().getTimestamp().toString() + " NONCE: " + 
                    aux.getInfo().getNONCE() + " PREVIOUSHASH: " + aux.getInfo().getPreviousHash() + " HASH: " + aux.getInfo().getHash() +" |";
            aux = aux.getNext();
        }
        return listInfo;
    }
    
}
