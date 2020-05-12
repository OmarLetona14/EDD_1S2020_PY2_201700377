/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.files;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import edd.proyecto2.model.Peer;
import edd.proyecto2.model.RemoteConfig;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Omar
 */
public class GenerateFile {
    
    private FileReader reader;
    private FileWriter fileWriter;
    private PrintWriter printw;
    private Gson gson;
    public static String getTemp(){
        String property = "java.io.tmpdir";
        String tempDir = System.getProperty(property);
        return tempDir;
    }
    
    public void writeFile(String filename, String content){
        try {
            File newFile = new File(filename);
            fileWriter = new FileWriter(newFile);
            printw = new PrintWriter(fileWriter);
            printw.append(content);
            printw.close();
        } catch (IOException ex) {
            Logger.getLogger(GenerateReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void writePeersFile(String filename, String content){
        try {
            File newFile = new File(filename);
            fileWriter = new FileWriter(newFile);
            printw = new PrintWriter(fileWriter);
            printw.println(content);
            printw.close();
        } catch (IOException ex) {
            Logger.getLogger(GenerateReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String readFile(String filename){
        String data="";
        try {
            File myObj = new File(filename);
            try (Scanner myReader = new Scanner(myObj)) {
                while (myReader.hasNextLine()) {
                    data += "\n" + myReader.nextLine();
                }
            }
        } catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
        }
        return data;
    }
    
    public List<Peer> getPeers(String filename){
        List<Peer> peers = new ArrayList();
        try {
            File testFile = new File(filename);
            JsonParser parser = new JsonParser();
            if(testFile.exists()){
                 reader = new FileReader(filename);
                JsonElement datos = parser.parse(reader);
                if(!datos.toString().equals("")){
                    JsonArray peersArray = datos.getAsJsonArray();
                    for(JsonElement e: peersArray){
                        Peer peer = new Peer();
                        RemoteConfig remote = new RemoteConfig();
                        JsonElement remotejson = e.getAsJsonObject().get("remoteConfig");
                        remote.setPort(remotejson.getAsJsonObject().get("port").getAsInt());
                        remote.setIp(remotejson.getAsJsonObject().get("ip").getAsString());
                        peer.setIdPeer(e.getAsJsonObject().get("idPeer").getAsInt());
                        peer.setRemoteConfig(remote);
                        peers.add(peer);
                    }
                }
            }
        } catch (Exception  ex) {
            Logger.getLogger(GenerateFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return peers;
    }
    
}
