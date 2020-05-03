/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Omar
 */
public class GenerateReport {
    private FileWriter fileWriter;
    private PrintWriter printw;
    private ProcessBuilder pbuilder;
    
    
    public void generate(String filename, String content){
        String dotFile = filename+".dot";
        String pngFile = filename+".png";
        try {
            File newFile = new File(dotFile);
            fileWriter = new FileWriter(newFile);
            printw = new PrintWriter(fileWriter);
            printw.append(generateDOTStructure(content, filename));
            printw.close();
        } catch (IOException ex) {
            Logger.getLogger(GenerateReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        initProcess(dotFile, pngFile);
    }
    
    private String generateDOTStructure(String content, String file_name){
        StringBuilder builder = new StringBuilder();
        String line = "digraph " + file_name + " { \n";
        builder.append(line);
        line  =  "node[shape=record]\n";
        builder.append(line);
        builder.append(content);
        builder.append("\n");
        builder.append("}");
        return builder.toString();
    }
    
    private void initProcess(String dot, String png){
        try {
            pbuilder = new ProcessBuilder("dot", "-Tpng", dot, "-o", png);
            pbuilder.redirectErrorStream(true);
            pbuilder.start();
        } catch (IOException ex) {
            Logger.getLogger(GenerateReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
