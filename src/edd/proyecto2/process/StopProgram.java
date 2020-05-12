/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.process;

import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author Omar
 */
public class StopProgram {
    
    public static void stop(){
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        threadSet.stream().forEach((t) -> {
            t.interrupt();
        });
    }
}
