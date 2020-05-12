/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.network;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import edd.proyecto2.files.GenerateFile;
import edd.proyecto2.model.Book;
import edd.proyecto2.model.Category;
import edd.proyecto2.model.JsonCategory;
import edd.proyecto2.model.LocalData;
import edd.proyecto2.model.Message;
import edd.proyecto2.model.NetworkMessagge;
import edd.proyecto2.model.Options;
import edd.proyecto2.model.Peer;
import edd.proyecto2.model.User;
import edd.proyecto2.structure.AVLTreeCategory;
import edd.proyecto2.view.BooksWindow;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Omar
 */
public class ServerThread extends Thread{
    private int port;
    private String ip;
    private boolean closeConnection;
    private ServerSocket serverSocket;
    private Socket incomingSocket;
    private Socket outcomingSocket;
    private DataInputStream inputData;
    private DataOutputStream outputData;
    private Gson gson;
    private GenerateFile generateFile;
    private NetworkMessagge messagge;
    private ClientThread clientThread;
    
    public ServerThread(int port){
        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println("Inicializando un servidor en el puerto " + port);
            this.generateFile = new GenerateFile();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private String getIPV4Address(Socket socketName){
        SocketAddress socketAddress = socketName.getRemoteSocketAddress();
        if (socketAddress instanceof InetSocketAddress) {
            InetAddress inetAddress = ((InetSocketAddress)socketAddress).getAddress();
            if (inetAddress instanceof Inet4Address)
                return inetAddress.getHostAddress();
            else
                System.err.println("Not an IP address.");
        } else { 
            System.err.println("Not an internet protocol socket.");
        }
        return "";
    }
    
    private void addPeers(){
        
    }
    
    private Object reciveData(){
        gson = new Gson();
        Peer peer = null;
        User newUser=null;
        try {
            inputData = new DataInputStream(incomingSocket.getInputStream());
            String inputJson = inputData.readUTF();
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(inputJson);
            String ipOrigin = jsonElement.getAsJsonObject().get("ip_origin").getAsString();
            int portOrigin = jsonElement.getAsJsonObject().get("port_origin").getAsInt();
            boolean closeCon = jsonElement.getAsJsonObject().get("closeCon").getAsBoolean();
            port = portOrigin;
            ip = ipOrigin;
            closeConnection = closeCon;
            JsonElement categories = jsonElement.getAsJsonObject().get("categories");
            JsonArray array = categories.getAsJsonArray();
            if(array!=null){
                for(JsonElement j : array){
                    Category cat = LocalData.virtualLibrary.searchByCategoryName(LocalData.virtualRoot, j.getAsJsonObject().get("categoryName").getAsString());
                    if(cat==null){
                        cat = new Category();
                        newUser = new User();
                        newUser.setCarnet(j.getAsJsonObject().get("carnet").getAsInt());
                        cat.setCategoryName(j.getAsJsonObject().get("categoryName").getAsString());
                        cat.setUser(newUser);
                    }
                    Book currentBook=null;
                    JsonArray libros = j.getAsJsonObject().get("books").getAsJsonArray();
                    for(JsonElement e: libros){
                        if(e!=null){
                            currentBook = new Book();
                            currentBook.setISBN(e.getAsJsonObject().get("isbn").getAsInt());
                            currentBook.setTitulo(e.getAsJsonObject().get("titulo").getAsString());
                            currentBook.setAutor(e.getAsJsonObject().get("autor").getAsString());
                            currentBook.setAnio(e.getAsJsonObject().get("anio").getAsInt());
                            currentBook.setEdicion(e.getAsJsonObject().get("edicion").getAsInt());
                            currentBook.setEditorial(e.getAsJsonObject().get("editorial").getAsString());
                            currentBook.setIdioma(e.getAsJsonObject().get("idioma").getAsString());
                            if(newUser==null){
                                currentBook.setUsuario(cat.getUser());
                            }else{
                                currentBook.setUsuario(newUser);
                            }
                            currentBook.setCategory(cat);
                        }
                        cat.getBooks().insert(currentBook);
                    }
                    LocalData.virtualRoot = LocalData.virtualLibrary.insert(LocalData.virtualRoot, cat);
                }
                Integer i =5;
                return i;
            }else{
                String text = gson.fromJson(inputJson, String.class);
                switch(text){
                    case "DELETE_PEER":
                        gson = new Gson();
                        generateFile = new GenerateFile();
                        List<Peer> peers = generateFile.getPeers(GenerateFile.getTemp() + "Peers.json");
                        peers.remove(LocalData.currentPeer);
                        String arrayJSON = gson.toJson(peers);
                        generateFile.writeFile(GenerateFile.getTemp() + "Peers.json", arrayJSON);
                        break;
                }
                return null;
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        return peer;
    }
    
    private void answer() throws IOException{
        outcomingSocket = new Socket(ip,port);
        List<JsonCategory> jsonList = new ArrayList();
        JsonCategory category;
        messagge = new NetworkMessagge();
        if(outcomingSocket!=null){
        try {
            outputData = new DataOutputStream(outcomingSocket.getOutputStream());
            gson = new Gson();
            try {
                for(Category c: LocalData.currentUser.getCategories().getAll( LocalData.currentUser.getRoot())){
                    category = new JsonCategory(c, String.valueOf(c.getUser().getCarnet()), c.getBooks().getAllJsonBooks());
                    jsonList.add(category);
                }
                messagge.setCategories(jsonList);
                messagge.setChain(null);
                messagge.setIp_origin(getIPV4Address(incomingSocket));
                messagge.setPort_origin(incomingSocket.getLocalPort());
                messagge.setCloseCon(true);
                messagge.setPeers(null);
                String jsonString= gson.toJson(messagge);
                outputData.writeUTF(jsonString);
                outputData.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    @Override
    public void run() {
        try {
            Peer peer;
            System.out.println("Servidor escuchando... ");
            incomingSocket = serverSocket.accept();
            while(true){
                try {
                    Object currentObject = reciveData();
                    if(currentObject!=null){
                        if(currentObject instanceof Peer){
                            peer= (Peer)currentObject;
                            LocalData.peers.addToFinal(peer);
                            String peerString = gson.toJson(peer);
                            generateFile.writeFile(GenerateFile.getTemp() + "Peers.json", peerString);
                        }else if(currentObject instanceof Integer){
                            answer();
                            JOptionPane.showMessageDialog(null, "Se actualizo la biblioteca", "Biblioteca actualizada", JOptionPane.INFORMATION_MESSAGE);
                            BooksWindow.closeWindow();
                            BooksWindow booksWindow = new BooksWindow(LocalData.virtualLibrary);
                            booksWindow.setVisible(true);
                            incomingSocket.close(); 
                        }
                    }
                    if(!closeConnection){
                        answer();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
}
