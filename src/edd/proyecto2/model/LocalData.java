/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.model;

import edd.proyecto2.node.NodeCategory;
import edd.proyecto2.structure.AVLTreeCategory;
import edd.proyecto2.structure.UserHashTable;

/**
 *
 * @author Omar
 */
public class LocalData {
    
    public static UserHashTable users = new UserHashTable(45);

    public static NodeCategory root = null;
    public static AVLTreeCategory categories = new AVLTreeCategory();
    public static User currentUser;
    
 
}
