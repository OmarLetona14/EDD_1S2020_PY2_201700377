/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edd.proyecto2.helper.CryptoSHA256;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Omar
 */
public class Block {
    private Gson gson;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getNONCE() {
        return NONCE;
    }

    public void setNONCE(int NONCE) {
        this.NONCE = NONCE;
    }

    public int getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(int previousHash) {
        this.previousHash = previousHash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
    private int index;
    private Timestamp timestamp;
    private List<Operation> data;
    private int NONCE;
    private int previousHash;
    private String hash;

    public List<Operation> getData() {
        return data;
    }

    public void setData(List<Operation> data) {
        this.data = data;
    }
    
    
    public Block(List<Operation> data, int _previousHash){
        this.data = data;
        this.NONCE = 0;
        this.previousHash = _previousHash;
        this.hash = generateHash();
    }
    
    public Block(int _previousHash){
        this.data = new ArrayList();
        this.NONCE = 0;
        this.previousHash = _previousHash;
        this.hash = generateHash();
    }
    
    public Block(){}
    
    private String getStringData(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gson = gsonBuilder.create();
        String operationsJson="";
        operationsJson = getData().stream().filter((o) -> (o!=null)).map((o) -> gson.toJson(o)).reduce(operationsJson, String::concat);
        return operationsJson;
    }
    
    
    private String generateHash(){
        String chain = "";
        while(!chain.startsWith("0000")){
            try {
                timestamp = new Timestamp(System.currentTimeMillis());
                String keyGenerator = String.valueOf(index).trim() + timestamp.toString().trim() + String.valueOf(previousHash).trim() +
                        getStringData().trim() + String.valueOf(NONCE);
                //chain = CryptoSHA256.toHexString(CryptoSHA256.getSHA(keyGenerator));
                chain = CryptoSHA256.getSHA(keyGenerator);
            } catch (NoSuchAlgorithmException ex) {
                System.out.println("Ocurrio un error al generar el hash " + Arrays.toString(ex.getStackTrace()));
            }
            NONCE++;
            //System.out.println(NONCE);
            //System.out.println(chain);
        }
        this.setTimestamp(timestamp);
        return chain;
    }
    
    
}
