/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.files;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import edd.proyecto2.model.Book;
import edd.proyecto2.model.User;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Omar
 */
public class JSONFileUpload {
    Gson gson;
    private String read(String filename){
        Gson gson = new Gson();
        String fileContent = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                fileContent += line;
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return fileContent;
    }
    
    public Object[] uploadBookDocument(String filename){
        FileReader fr = null;
        Book[] books = null;
        try {
            gson = new Gson();
            JsonParser parser = new JsonParser();
            fr = new FileReader(filename);
            JsonElement datos = parser.parse(fr);
            JsonElement usuarios = datos.getAsJsonObject().get("libros");
            books = gson.fromJson(usuarios.getAsString(), Book[].class);
            return books;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JSONFileUpload.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(JSONFileUpload.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return books;
    }
    public List<User> uploadUserDocument(String filename){
        FileReader fr = null;
        List<User> users = new ArrayList();
        try{
            gson = new Gson();
            JsonParser parser = new JsonParser();
            fr = new FileReader(filename);
            JsonElement datos = parser.parse(fr);
            JsonElement usuariosJSON = datos.getAsJsonObject().get("Usuarios");
            JsonArray usuarios = usuariosJSON.getAsJsonArray();
            for(JsonElement e: usuarios){
                User user = new User();
                user.setCarnet(e.getAsJsonObject().get("Carnet").getAsInt());
                user.setNombre(e.getAsJsonObject().get("Nombre").getAsString());
                user.setApellido(e.getAsJsonObject().get("Apellido").getAsString());
                user.setCarrera(e.getAsJsonObject().get("Carrera").getAsString());
                user.setPassword(e.getAsJsonObject().get("Password").getAsString());
                users.add(user);
                System.out.println("Usuario agregado correctamente");
            }
        }catch(FileNotFoundException ex){
            Logger.getLogger(JSONFileUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    
    
}
