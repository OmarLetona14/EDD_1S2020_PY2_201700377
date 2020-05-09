/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.network;

import com.google.gson.Gson;
import edd.proyecto2.files.GenerateFile;
import edd.proyecto2.model.LocalData;
import edd.proyecto2.model.Message;
import edd.proyecto2.model.Peer;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Omar
 */
public class ServerThread extends Thread{
    private ServerSocket serverSocket;
    private Socket incomingSocket;
    private DataInputStream inputData;
    private Gson gson;
    private GenerateFile generateFile;
    
    public ServerThread(int port){
        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println("Inicializando un servidor en el puerto " + port);
            this.generateFile = new GenerateFile();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Peer reciveData(){
        gson = new Gson();
        Peer peer = null;
        try {
            inputData = new DataInputStream(incomingSocket.getInputStream());
            String inputJSON = inputData.readUTF();
            peer = gson.fromJson(inputJSON, Peer.class);
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        return peer;
    }
    
    @Override
    public void run() {
        Peer peer;
        while(true){
            try {
                System.out.println("Servidor escuchando... ");
                incomingSocket = serverSocket.accept();
                peer= reciveData();
                LocalData.peers.addToFinal(peer);
                String peerString = gson.toJson(peer);
                generateFile.writeFile("Peers.json", peerString);
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
