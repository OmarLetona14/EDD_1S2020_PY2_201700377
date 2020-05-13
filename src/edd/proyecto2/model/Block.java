/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.model;

import edd.proyecto2.helper.CryptoSHA256;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Omar
 */
public class Block {

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
    private String data;
    private int NONCE;
    private int previousHash;
    private String hash;
    
    public Block(String _data,  int _previousHash){
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.data = _data;
        this.NONCE = 0;
        this.previousHash = _previousHash;
        this.hash = generateHash();
    }

    public Block(int index, Timestamp timestamp, String data, int NONCE, int previousHash, String hash) {
        this.index = index;
        this.timestamp = timestamp;
        this.data = data;
        this.NONCE = NONCE;
        this.previousHash = previousHash;
        this.hash = hash;
    }
    
    
    public Block(){}
    private String generateHash(){
        String chain = "";
        while(!chain.startsWith("0000")){
            try {
                String keyGenerator = String.valueOf(index).trim() + timestamp.toString().trim() + String.valueOf(previousHash).trim() +
                        data.trim() + String.valueOf(NONCE);
                chain = CryptoSHA256.toHexString(CryptoSHA256.getSHA(keyGenerator));
            } catch (NoSuchAlgorithmException ex) {
                System.out.println("Ocurrio un error al generar el hash " + Arrays.toString(ex.getStackTrace()));
            }
            NONCE++;
        }
        return chain;
    }
    
    
}
