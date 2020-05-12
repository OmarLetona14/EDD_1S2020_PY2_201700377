/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.model;

import edd.proyecto2.node.NodeCategory;
import edd.proyecto2.structure.AVLTreeCategory;
import edd.proyecto2.structure.SimplyLinkedListPeer;
import edd.proyecto2.structure.UserHashTable;

/**
 *
 * @author Omar
 */
public class LocalData {
    
    public static UserHashTable users = new UserHashTable(45); 
    public static SimplyLinkedListPeer peers;
    public static AVLTreeCategory virtualLibrary;
    public static NodeCategory virtualRoot;
    public static Peer currentPeer;
    public static User currentUser;
    public static RemoteConfig remote;
    public static final int ISBN = 0;
    public static final int TITULO = 1;
    public static final int AUTOR = 2; 
    public static final int EDITORIAL = 3;
    public static final int ANIO = 4;
    public static final int EDICION =5;
    public static final int IDIOMA=6;
    public static final int USUARIO = 7;
    public static final int CATEGORIA = 8;
    public static final int DETALLES = 9;
    public static int filaSeleccionada;
    
 
}
