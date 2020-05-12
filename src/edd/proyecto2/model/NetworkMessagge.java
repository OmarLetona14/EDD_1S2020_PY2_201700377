/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.model;

import java.util.List;

/**
 *
 * @author Omar
 */
public class NetworkMessagge {
    
    
    private String ip_origin;
    private int port_origin;
    private boolean closeCon;
    private List<JsonCategory> categories;
    private List<Peer> peers;
    private List<Block> chain;

    public NetworkMessagge(String ip_origin, int port_origin, boolean closeCon, List<JsonCategory> categories, List<Peer> peers, List<Block> chain) {
        this.ip_origin = ip_origin;
        this.port_origin = port_origin;
        this.closeCon = closeCon;
        this.categories = categories;
        this.peers = peers;
        this.chain = chain;
    }
    
    public NetworkMessagge(){}

    public String getIp_origin() {
        return ip_origin;
    }

    public void setIp_origin(String ip_origin) {
        this.ip_origin = ip_origin;
    }

    public int getPort_origin() {
        return port_origin;
    }

    public void setPort_origin(int port_origin) {
        this.port_origin = port_origin;
    }

    public boolean isCloseCon() {
        return closeCon;
    }

    public void setCloseCon(boolean closeCon) {
        this.closeCon = closeCon;
    }

    public List<JsonCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<JsonCategory> categories) {
        this.categories = categories;
    }

    public List<Peer> getPeers() {
        return peers;
    }

    public void setPeers(List<Peer> peers) {
        this.peers = peers;
    }

    public List<Block> getChain() {
        return chain;
    }

    public void setChain(List<Block> chain) {
        this.chain = chain;
    }
    
    
}
