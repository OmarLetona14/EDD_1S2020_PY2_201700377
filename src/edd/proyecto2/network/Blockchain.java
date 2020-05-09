/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.network;

import com.google.gson.Gson;
import edd.proyecto2.files.GenerateFile;
import edd.proyecto2.model.Block;
import edd.proyecto2.model.LocalData;
import edd.proyecto2.model.Peer;
import edd.proyecto2.node.NodePeer;
import edd.proyecto2.structure.SimplyLinkedListPeer;
import java.io.File;
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
    
    public void createBlock(String data, int previous){
        block = new Block(data, previous);
        
    }
    public void registerNode(){
        gson = new Gson();
        try{
            Peer peer = new Peer(LocalData.peers.listSize()+1, LocalData.remote);
            String jsonString= gson.toJson(peer);
            generateFile = new GenerateFile();
            generateFile.writeFile("Peers.json", jsonString);
        }catch(Exception e){
            System.out.println("Ocurrio un error al registrar el nuevo nodo a la red");
        }
    }
    
    
    public void connect(){
        LocalData.peers = new SimplyLinkedListPeer();
        List<Peer> nodePeers = generateFile.getPeers("Peers.json");
        if(nodePeers!=null){
            nodePeers.stream().filter((p) -> (p!=null)).forEach((p) -> {
                LocalData.peers.addToFinal(p);
            });
        }
        spread();
    }
    
    private void spread(){
        NodePeer aux = LocalData.peers.first;
        while(aux!=null){
            if(aux.getInfo()!=null){
                client = new ClientThread(aux.getInfo().getRemoteConfig().getIp(), aux.getInfo().getRemoteConfig().getPort());
                client.sendBroadcastMessagge(aux.getInfo());
                aux = aux.getNext();
            }
        }
    }
}
