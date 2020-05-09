/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.network;

import com.google.gson.Gson;
import edd.proyecto2.model.LocalData;
import edd.proyecto2.model.Message;
import edd.proyecto2.model.Peer;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Omar
 */
public class ClientThread {
    private Socket socket;
    private DataOutputStream outputData;
    private Gson gson;
    
    
    public ClientThread(String ip, int port){
        try {
            this.socket = new Socket(ip, port);
            outputData = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendData(){
        gson = new Gson();
        try {
            String object = gson.toJson(LocalData.currentUser.getCategories());
            outputData.writeUTF(object);
            outputData.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendBroadcastMessagge(Peer peer){
        try {
            gson = new Gson();
            String peerJSON = gson.toJson(peer);
            outputData.writeUTF(peerJSON);
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
