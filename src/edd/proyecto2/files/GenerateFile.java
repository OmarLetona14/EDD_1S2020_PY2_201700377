/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.files;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edd.proyecto2.model.Peer;
import java.io.File;
import java.io.FileNotFoundException;
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
        gson = new Gson();
        File testFile = new File(getTemp() + filename);
        if(testFile.exists()){
            String jsonString = readFile(getTemp() + filename);
            peers = gson.fromJson(jsonString, new TypeToken<List<Peer>>() {}.getType());
        }
        return peers;
    }
    
}
