/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.model;

/**
 *
 * @author Omar
 */
public class RemoteConfig {

    public RemoteConfig(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
    
    public RemoteConfig(){}
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    
    private String ip;
    private int port;
    
}
