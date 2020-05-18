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
import edd.proyecto2.model.Block;
import edd.proyecto2.model.Book;
import edd.proyecto2.model.Category;
import edd.proyecto2.model.JsonCategory;
import edd.proyecto2.model.LocalData;
import edd.proyecto2.model.Message;
import edd.proyecto2.model.NetworkMessagge;
import edd.proyecto2.model.Operation;
import edd.proyecto2.model.Options;
import edd.proyecto2.model.Peer;
import edd.proyecto2.model.RemoteConfig;
import edd.proyecto2.model.User;
import edd.proyecto2.node.NodeBlock;
import edd.proyecto2.node.NodePeer;
import edd.proyecto2.structure.AVLTreeCategory;
import edd.proyecto2.structure.DoubleLinkedListBlock;
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
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    public static boolean flag;
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
    
    private void reciveOriginData(JsonElement e){
        String ipOrigin = e.getAsJsonObject().get("ip_origin").getAsString();
        int portOrigin = e.getAsJsonObject().get("port_origin").getAsInt();
        boolean closeCon = e.getAsJsonObject().get("closeCon").getAsBoolean();
        port = portOrigin;
        ip = ipOrigin;
        closeConnection = closeCon;
    }
    
    private void reciveChain(JsonElement e){
        if(e!=null){
                JsonArray chainArray = e.getAsJsonArray();
                Block block =null;
                if(chainArray!=null){
                    for(JsonElement c: chainArray){
                        block = new Block();
                        List<Operation> op=null;
                        block.setHash(c.getAsJsonObject().get("hash").getAsString());
                        block.setIndex(c.getAsJsonObject().get("index").getAsInt());
                        block.setNONCE(c.getAsJsonObject().get("NONCE").getAsInt());
                        block.setPreviousHash(c.getAsJsonObject().get("previousHash").getAsInt());
                        block.setTimestamp(Timestamp.valueOf(c.getAsJsonObject().get("timestamp").getAsString()));
                        JsonElement operationsJson = c.getAsJsonObject().get("data");
                        JsonArray operations = operationsJson.getAsJsonArray();
                        if(operations!=null){
                            op = new ArrayList();
                            for(JsonElement k: operations){
                                if(k!=null){
                                    Operation o = new Operation();
                                    o.setObject(k.getAsJsonObject().get("object").getAsJsonObject());
                                    switch(k.getAsJsonObject().get("type").getAsString()){
                                        case "crear_usuario":
                                            o.setType(Operation.operationType.crear_usuario);
                                            break;
                                        case "editar_usuario":
                                            o.setType(Operation.operationType.editar_usuario);
                                            break;
                                        case "crear_libro":
                                            o.setType(Operation.operationType.crear_libro);
                                            break;
                                        case "eliminar_libro":
                                            o.setType(Operation.operationType.eliminar_libro);
                                            break;
                                        case "crear_categoria":
                                            o.setType(Operation.operationType.crear_categoria);
                                            break;
                                        case "eliminar_categoria":
                                            o.setType(Operation.operationType.eliminar_categoria);
                                            break;

                                    }
                                    op.add(o);
                                }
                            }
                        }
                        block.setData(op);
                    }
                    if(LocalData.blockchain!=null){
                        LocalData.blockchain.addToFinal(block);
                    }else{
                        LocalData.blockchain = new DoubleLinkedListBlock();
                        LocalData.blockchain.addToFinal(block);
                    }

                }
            }
    }
    
    private void recivePeers(JsonElement element){
        if(element!=null){
                JsonArray peers = element.getAsJsonArray();
                if(peers!=null){
                    for(JsonElement e: peers){
                        if(e!=null){
                             JsonElement jsonRemote = e.getAsJsonObject().get("remote");
                            RemoteConfig remote = new RemoteConfig();
                            remote.setIp(jsonRemote.getAsJsonObject().get("ip").getAsString());
                            remote.setPort(jsonRemote.getAsJsonObject().get("port").getAsInt());
                            Peer newPeer = new Peer();
                            newPeer.setRemoteConfig(remote);
                            newPeer.setIdPeer(LocalData.peers.listSize());
                            LocalData.peers.addToFinal(newPeer);
                        }
                    }
                }
            }
    }
    
    private void reciveCategories(JsonElement element){
        User newUser=null;
        JsonArray array = element.getAsJsonArray();
        if(array!=null){
                for(JsonElement j : array){
                    Category cat = LocalData.virtualLibrary.searchByCategoryName(LocalData.virtualRoot, j.getAsJsonObject().get("categoryName").getAsString());
                    if(cat==null){
                        cat = new Category();
                        newUser = new User();
                        newUser.setCarnet(j.getAsJsonObject().get("carnet").getAsInt());
                        cat.setCategoryName(j.getAsJsonObject().get("categoryName").getAsString());
                        cat.setUser(newUser);
                        LocalData.virtualRoot = LocalData.virtualLibrary.insert(LocalData.virtualRoot, cat);
                        LocalData.virtualLibrary.syncRoot(LocalData.virtualRoot);
                        LocalData.virtualLibrary.print(LocalData.virtualRoot);
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
                                currentBook.setUsuario(new User(e.getAsJsonObject().get("carnet").getAsInt(),"","","",""));
                            }else{
                                currentBook.setUsuario(newUser);
                            }
                            currentBook.setCategory(cat);
                        }
                      
                        cat.getBooks().insert(currentBook);
                    }
                }
               
            }
    
    }
    
    private Object reciveData(){
        gson = new Gson();
        Peer peer = null;
        try {
            inputData = new DataInputStream(incomingSocket.getInputStream());
            String inputJson = inputData.readUTF();
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(inputJson);
            reciveOriginData(jsonElement);
            JsonElement chain = jsonElement.getAsJsonObject().get("chain");
            reciveChain(chain);
            JsonElement jsonPeers = jsonElement.getAsJsonObject().get("peers");
            recivePeers(jsonPeers);
            JsonElement categories = jsonElement.getAsJsonObject().get("categories");
            reciveCategories(categories);
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 5;
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
                if(LocalData.blockchain!=null && !LocalData.blockchain.empty()){
                    messagge.setChain(getChain());
                }
                messagge.setIp_origin(getIPV4Address(incomingSocket));
                messagge.setPort_origin(incomingSocket.getLocalPort());
                messagge.setCloseCon(true);
                if(LocalData.peers!=null && !LocalData.peers.empty()){
                    messagge.setPeers(getPeers());
                }
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
    
    private List<Peer> getPeers(){
        List<Peer> peers = new ArrayList();
        NodePeer aux = LocalData.peers.first;
        while(aux!=null){
            if(aux.getInfo()!=null){
                peers.add(aux.getInfo());
            }
        }
        return peers;
    }
    
    private List<Block> getChain(){
        List<Block> chain = new ArrayList();
        NodeBlock aux = LocalData.blockchain.first;
        while(aux!=null){
            if(aux.getInfo()!=null){
                chain.add(aux.getInfo());
            }
            aux = aux.getNext();
        }
        return chain;
    }
    
    public static void closeConnection(){
        flag = false;
        
    }
    
    @Override
    public void run() {
        try {
            Peer peer;
            System.out.println("Servidor escuchando... ");
            flag = true;
            while(flag){
                try {
                    incomingSocket = serverSocket.accept();
                    Object currentObject = reciveData();
                    if(!closeConnection){
                        if(currentObject!=null){
                            if(currentObject instanceof Peer){
                                peer= (Peer)currentObject;
                                LocalData.peers.addToFinal(peer);
                                String peerString = gson.toJson(peer);
                                generateFile.writeFile(GenerateFile.getTemp() + "Peers.json", peerString);
                            }else if(currentObject instanceof Integer){
                                answer();
                                if(LocalData.currentWindow instanceof BooksWindow){
                                    JOptionPane.showMessageDialog(null, "Se actualizo la biblioteca", "Biblioteca actualizada", JOptionPane.INFORMATION_MESSAGE);
                                    BooksWindow.closeWindow();
                                    BooksWindow booksWindow = new BooksWindow();
                                    booksWindow.setVisible(true);
                                }
                                incomingSocket.close(); 
                            }
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
           // serverSocket.close();
        } catch (Exception ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
}
