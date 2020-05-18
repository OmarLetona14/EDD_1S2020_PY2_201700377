/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edd.proyecto2.files.GenerateFile;
import edd.proyecto2.model.Block;
import edd.proyecto2.model.LocalData;
import edd.proyecto2.model.Peer;
import edd.proyecto2.node.NodePeer;
import edd.proyecto2.structure.SimplyLinkedListPeer;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Omar
 */
public class Blockchain {
    private Block block;
    private GenerateFile generateFile;
    private Gson gson;
    private ClientThread client;
    
    public void deletePeer(){
       NodePeer aux = LocalData.peers.first;
        while(aux!=null){
            if(aux.getInfo()!=null){
                client = new ClientThread(aux.getInfo().getRemoteConfig().getIp(), aux.getInfo().getRemoteConfig().getPort());
                client.sendBroadcastMessagge("DELETE_PEER");
                aux = aux.getNext();
            }
        }
    }
    
    
    public void registerPeer(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gson = gsonBuilder.create();
        try{
            List<Peer> peers = new ArrayList();
            Peer peer = new Peer(LocalData.peers.listSize()+1, LocalData.remote);
            peers.add(peer);
            NodePeer aux = LocalData.peers.first;
            while(aux!=null){
                peers.add(aux.getInfo());
                aux = aux.getNext();
            }
            String jsonString= gson.toJson(peers);
            generateFile = new GenerateFile();
            generateFile.writePeersFile(GenerateFile.getTemp() + "Peers.json", jsonString);
        }catch(Exception e){
            System.out.println("Ocurrio un error al registrar el nuevo nodo a la red");
        }
    }
    
    
    public void sync(){
        generateFile = new GenerateFile();
        LocalData.peers = new SimplyLinkedListPeer();
        List<Peer> nodePeers = generateFile.getPeers(GenerateFile.getTemp() + "Peers.json");
        if(nodePeers!=null){
            nodePeers.stream().filter((p) -> (p!=null)).forEach((p) -> {
                LocalData.peers.addToFinal(p);
            });
        }
        addLibrary();
    }
    private void addLibrary(){
       NodePeer aux = LocalData.peers.first;
        while(aux!=null){
            if(aux.getInfo()!=null && aux.getInfo()!=LocalData.currentPeer){
                client = new ClientThread(aux.getInfo().getRemoteConfig().getIp(), aux.getInfo().getRemoteConfig().getPort());
                client.sendData();
                aux = aux.getNext();
            }
        } 
    }
   
}
