/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.files;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import edd.proyecto2.helper.CryptoMD5;
import edd.proyecto2.model.Book;
import edd.proyecto2.model.Category;
import edd.proyecto2.model.LocalData;
import edd.proyecto2.model.User;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Omar
 */
public class JSONFileUpload {
    private Gson gson;
    private CryptoMD5 encrypt;
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
            try{fr = new FileReader(filename);}catch(Exception e){
                JOptionPane.showMessageDialog(null, "Ocurrio un error al cargar el archivo" + e.getMessage(), 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            JsonArray libros = null;
            try{
                JsonElement datos = parser.parse(fr);
                String d = datos.toString().replace('ñ', 'n');
                datos = parser.parse(d);
                JsonElement librosJson = datos.getAsJsonObject().get("libros");
                libros = librosJson.getAsJsonArray();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Ocurrio un error al cargar el archivo" + e.getMessage(), 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            for(JsonElement e: libros){
                if(e!=null){
                    Book book = new Book();
                    book.setISBN(e.getAsJsonObject().get("ISBN").getAsInt());
                    book.setTitulo(e.getAsJsonObject().get("Titulo").getAsString());
                    book.setAutor(e.getAsJsonObject().get("Autor").getAsString());
                    if(e.getAsJsonObject().get("Ano")!=null){
                        book.setAnio(e.getAsJsonObject().get("Ano").getAsInt());
                    }else if(e.getAsJsonObject().get("Anio")!=null){
                        book.setAnio(e.getAsJsonObject().get("Anio").getAsInt());
                    }
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
               
            }
        }catch(Exception e){
            PrintWriter print = null;
            try {
                File file = new File("D:\\Users\\Omar\\Desktop\\log.txt");
                print = new PrintWriter(file);
                e.printStackTrace(print);
                JOptionPane.showMessageDialog(null, "Ocurrio un error al cargar los libros " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(JSONFileUpload.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                print.close();
            }
        }
    }
    
    private Category searchCategory(JsonElement e){
        if(LocalData.currentUser.getCategories().searchNode(e.getAsJsonObject().get("Categoria").getAsString(), LocalData.currentUser.getRoot(), null) !=null){
            return LocalData.currentUser.getCategories().searchNode(e.getAsJsonObject().get("Categoria").getAsString(), LocalData.currentUser.getRoot(), null).getValue();
        }else{
            return null;
        }
    }
    
    
    private void insertCategory(String categoryName){
        try{
            Category newCategory = new Category();
            newCategory.setCategoryName(categoryName);
            LocalData.currentUser.setRoot(LocalData.currentUser.getCategories().insert(LocalData.currentUser.getRoot(), newCategory)); 
            LocalData.currentUser.getCategories().syncRoot(LocalData.currentUser.getRoot());
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
                String userPassword = CryptoMD5.encriptar(e.getAsJsonObject().get("Password").getAsString());
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
