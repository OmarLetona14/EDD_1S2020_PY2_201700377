/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.model;

/**
 *
 * @author Omar
 */
public class Operation {
    
    private operationType type;
    private Object object;
            
    public enum operationType{
        crear_usuario,
        editar_usuario,
        crear_libro,
        eliminar_libro, 
        crear_categoria, 
        eliminar_categoria
    }
    
    public Operation(){}

    public Operation(operationType type, Object object) {
        this.type = type;
        this.object = object;
    }
    
    public operationType getType() {
        return type;
    }

    public void setType(operationType type) {
        this.type = type;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
    
    
    
}
