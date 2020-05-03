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
import edd.proyecto2.helper.MD5Password;
import edd.proyecto2.model.Book;
import edd.proyecto2.model.Category;
import edd.proyecto2.model.LocalData;
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
    private Gson gson;
    private MD5Password encrypt;
    private Category currentCategory;
    
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
    
    public void uploadBookDocument(String filename){
        FileReader fr = null;
        try{
            gson = new Gson();
            JsonParser parser = new JsonParser();
            fr = new FileReader(filename);
            JsonElement datos = parser.parse(fr);
            JsonElement librosJson = datos.getAsJsonObject().get("libros");
            JsonArray libros = librosJson.getAsJsonArray();
            for(JsonElement e: libros){
                Book book = new Book();
                book.setISBN(e.getAsJsonObject().get("ISBN").getAsInt());
                book.setTitulo(e.getAsJsonObject().get("Titulo").getAsString());
                book.setAutor(e.getAsJsonObject().get("Autor").getAsString());
                book.setAnio(e.getAsJsonObject().get("Año").getAsInt());
                book.setEditorial(e.getAsJsonObject().get("Editorial").getAsString());
                book.setEdicion(e.getAsJsonObject().get("Edicion").getAsInt());
                book.setIdioma(e.getAsJsonObject().get("Idioma").getAsString());
                currentCategory = searchCategory(e);
                if(currentCategory==null){
                    insertCategory(e.getAsJsonObject().get("Categoria").getAsString());
                    currentCategory = searchCategory(e);
                }
                book.setCategory(currentCategory);      
                book.setUsuario(LocalData.currentUser);
                currentCategory.getBooks().insert(book);
                currentCategory.getBooks().print();
            }
        }catch(Exception e){}
    }
    
    private Category searchCategory(JsonElement e){
        return LocalData.categories.searchNode(e.getAsJsonObject().get("Categoria").getAsString(), LocalData.root, null).getValue();
    }
    
    
    private void insertCategory(String categoryName){
        try{
            Category newCategory = new Category();
            newCategory.setCategoryName(categoryName);
            LocalData.root = LocalData.categories.insert(LocalData.root, newCategory);
        }catch(Exception e){
            System.out.println("Ocurrio un error al intentar ingresar la categoria");
        }
        
    }
    public void uploadUserDocument(String filename){
        FileReader fr = null;
        try{
            gson = new Gson();
            JsonParser parser = new JsonParser();
            fr = new FileReader(filename);
            JsonElement datos = parser.parse(fr);
            JsonElement usuariosJSON = datos.getAsJsonObject().get("Usuarios");
            JsonArray usuarios = usuariosJSON.getAsJsonArray();
            for(JsonElement e: usuarios){
                User user = new User();
                String userPassword = MD5Password.encriptar(e.getAsJsonObject().get("Password").getAsString());
                user.setCarnet(e.getAsJsonObject().get("Carnet").getAsInt());
                user.setNombre(e.getAsJsonObject().get("Nombre").getAsString());
                user.setApellido(e.getAsJsonObject().get("Apellido").getAsString());
                user.setCarrera(e.getAsJsonObject().get("Carrera").getAsString());
                user.setPassword(userPassword);
                LocalData.users.insert(user);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
}
