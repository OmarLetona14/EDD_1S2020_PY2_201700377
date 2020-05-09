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
public class Peer {

    public Peer(int idPeer, RemoteConfig remoteConfig) {
        this.idPeer = idPeer;
        this.remoteConfig = remoteConfig;
    }

    public int getIdPeer() {
        return idPeer;
    }

    public void setIdPeer(int idPeer) {
        this.idPeer = idPeer;
    }

    public RemoteConfig getRemoteConfig() {
        return remoteConfig;
    }

    public void setRemoteConfig(RemoteConfig remoteConfig) {
        this.remoteConfig = remoteConfig;
    }
    private int idPeer;
    private RemoteConfig remoteConfig;
}
