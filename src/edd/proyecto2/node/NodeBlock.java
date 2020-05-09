/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.node;

import edd.proyecto2.model.Block;

/**
 *
 * @author Omar
 */
public class NodeBlock {

    public Block getInfo() {
        return info;
    }

    public void setInfo(Block info) {
        this.info = info;
    }

    public NodeBlock getNext() {
        return next;
    }

    public void setNext(NodeBlock next) {
        this.next = next;
    }

    public NodeBlock getPrevious() {
        return previous;
    }

    public void setPrevious(NodeBlock previous) {
        this.previous = previous;
    }
    
    private Block info;
    private NodeBlock next;
    private NodeBlock previous;
    
    public NodeBlock(Block _block){
        this.info = _block;
        this.next =null;
        this.previous =null;
    }
    
    
    
}
