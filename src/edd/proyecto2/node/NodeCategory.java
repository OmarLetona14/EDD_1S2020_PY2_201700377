/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.node;

import edd.proyecto2.model.Category;

/**
 *
 * @author Omar
 */
public class NodeCategory {
    
    public NodeCategory left, right, parent;
    public int height = 1;
    public Category value;
    
    public NodeCategory(Category _category){
        this.value = _category;
    }
    
    public Category getValue(){
        return this.value;
    }
    
    private int height(NodeCategory node){
        if(node == null){
            return 0;
        }else{
            return node.height;
        }
    }
    
    public int getHeight(){
        return this.height;
    }
    
    
}
