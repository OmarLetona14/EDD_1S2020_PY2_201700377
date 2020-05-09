/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.node;

import edd.proyecto2.model.Peer;

/**
 *
 * @author Omar
 */
public class NodePeer {

    public Peer getInfo() {
        return info;
    }

    public void setInfo(Peer info) {
        this.info = info;
        this.next=null;
    }

    public NodePeer getNext() {
        return next;
    }

    public void setNext(NodePeer next) {
        this.next = next;
    }
    private Peer info;
    private NodePeer next;
    
    public NodePeer(Peer _info){
        this.info = _info;
    }
}
