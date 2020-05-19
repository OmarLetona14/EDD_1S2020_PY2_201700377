/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.model;

import edd.proyecto2.node.NodeCategory;
import edd.proyecto2.structure.AVLTreeCategory;
import edd.proyecto2.structure.DoubleLinkedListBlock;
import edd.proyecto2.structure.SimplyLinkedListPeer;
import edd.proyecto2.structure.UserHashTable;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author Omar
 */
public class LocalData {
    
    public static JFrame currentWindow;
    public static boolean localEdit;
    public static Block currentBlock;
    public static List<Operation> operations;
    public static boolean sendingChain;
    public static boolean onDeleteOperation;
    public static UserHashTable users = new UserHashTable(45); 
    public static SimplyLinkedListPeer peers;
    public static AVLTreeCategory virtualLibrary;
    public static NodeCategory virtualRoot;
    public static Peer currentPeer;
    public static User currentUser;
    public static DoubleLinkedListBlock blockchain;
    public static String data;
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
