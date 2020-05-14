/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.network;

import com.google.gson.Gson;
import edd.proyecto2.model.Block;
import edd.proyecto2.model.Category;
import edd.proyecto2.model.JsonCategory;
import edd.proyecto2.model.JsonUser;
import edd.proyecto2.model.LocalData;
import edd.proyecto2.model.Message;
import edd.proyecto2.model.NetworkMessagge;
import edd.proyecto2.model.Peer;
import edd.proyecto2.node.NodeBlock;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Omar
 */
public class ClientThread extends Thread {
    private Socket socket;
    private DataOutputStream outputData;
    private Gson gson;
    private NetworkMessagge messagge;
    private List<Peer> peers;
    private boolean flag;
    
    public ClientThread(String ip, int port){
        try {
            this.socket = new Socket(ip, port);
            outputData = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String getLocalIp(){
        try {
            InetAddress address = InetAddress.getLocalHost();
            return address.getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    public void sendData(){
        List<JsonCategory> jsonList = new ArrayList();
        JsonCategory category;
        messagge = new NetworkMessagge();
        peers = new ArrayList();
        if(socket!=null){
            gson = new Gson();
        try {
            for(Category c: LocalData.currentUser.getCategories().getAll( LocalData.currentUser.getRoot())){
               category = new JsonCategory(c, String.valueOf(c.getUser().getCarnet()), c.getBooks().getAllJsonBooks());
               jsonList.add(category);
            }
            peers.add(LocalData.currentPeer);
            messagge.setCategories(jsonList);
            List<Block> chain  = new ArrayList();
            if(LocalData.blockchain!=null){
                NodeBlock aux = LocalData.blockchain.first;
                while(aux!=null){
                    if(aux.getInfo()!=null){
                        chain.add(aux.getInfo());
                    }
                    aux = aux.getNext();
                }
            }
            
            messagge.setChain(chain);
            messagge.setIp_origin(LocalData.remote.getIp());
            messagge.setPort_origin(LocalData.remote.getPort());
            messagge.setPeers(peers);
            messagge.setCloseCon(false);
            String jsonString= gson.toJson(messagge);
            outputData.writeUTF(jsonString);
            outputData.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    private void closeConnection(){
        flag = false;
    }
    
    public void sendBroadcastMessagge(Object peer){
        if(outputData!=null){
            try {
            gson = new Gson();
            String peerJSON = gson.toJson(peer);
            outputData.writeUTF(peerJSON);
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    @Override
    public void run(){
        flag = true;
        while(flag){}

    }
}
