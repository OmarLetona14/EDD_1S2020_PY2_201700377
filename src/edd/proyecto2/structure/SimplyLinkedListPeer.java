/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.structure;

import edd.proyecto2.model.Peer;
import edd.proyecto2.node.NodePeer;

/**
 *
 * @author Omar
 */
public class SimplyLinkedListPeer {
    
    public NodePeer first;
    private NodePeer last;
    private int size;
    
    public SimplyLinkedListPeer(){
        first = null;
    }
    
    public int listSize(){
        return size;
    }
    
    public boolean empty(){
        return(first==null);
    }
    
    public void addToFinal(Peer peer){
        NodePeer newNodo = new NodePeer(peer);
        if(empty()){   
            first = newNodo;
            last = newNodo;
        }else{
            first.setNext(newNodo);
            last = newNodo;
        }
       size++;
    }
    

    public boolean serch(int idPeer){
        NodePeer aux = first;
        boolean found = false;
        while(aux != null && found!=true){
            if(idPeer==aux.getInfo().getIdPeer()){
                found = true;
            }else{
                aux = aux.getNext();
            }
        }
        return found;
    }

    public void delete(int idPeer){
        if(serch(idPeer)){
            if(first.getInfo().getIdPeer()==idPeer){
                first = first.getNext();
            }else{
                NodePeer aux = first;
                while(aux.getNext().getInfo().getIdPeer()!=idPeer){
                    aux = aux.getNext();
                }
                NodePeer next = aux.getNext().getNext();
                aux.setNext(next);
            }
            size--;
        }
    }
    
    public String print(){
        String listInfo = "";
        NodePeer aux = first;
        while(aux!=null){
            if(aux.getInfo()!=null){
                listInfo += "\"node" + aux.hashCode() + "\"[ label = \" " +  " IP: " + aux.getInfo().getRemoteConfig().getIp() + " active port: " + 
                aux.getInfo().getRemoteConfig().getPort() +" \" ]; \n";
            }
            aux = aux.getNext(); 
        }
        NodePeer aux2 = first;
        while(aux2!=null){
            if(aux2.getInfo()!=null){
                if(aux2!=last){
                listInfo += "\"node" + aux2.hashCode() + "\" -> ";
                }else{
                    listInfo += "\"node" + aux2.hashCode() + "\" ; ";
                }
            }
            aux2 = aux2.getNext();
        }
        return listInfo;
    }
    
    
    
}
