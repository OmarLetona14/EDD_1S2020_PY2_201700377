/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.process;

import edd.proyecto2.helper.CryptoMD5;
import edd.proyecto2.model.LocalData;
import edd.proyecto2.model.User;
import edd.proyecto2.view.UserDashboard;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Omar
 */
public class LoginProcess {
    
    User userLogger;
    public void doLogin(String carnet, String password, JFrame window){
        userLogger = LocalData.users.searchUser(carnet);
        if(userLogger!=null){
            try {
                String descryptPassword = CryptoMD5.desencriptar(userLogger.getPassword());
                if(password.equals(descryptPassword)){
                    LocalData.currentUser = userLogger;
                    JOptionPane.showMessageDialog(window, "Bienvenido " + userLogger.getNombre() + " " +userLogger.getApellido(),
                        "Login exitoso", JOptionPane.INFORMATION_MESSAGE);
                    LocalData.localEdit = true;
                    LocalData.operations = new ArrayList();
                    window.dispose();
                    UserDashboard userDashboard = new UserDashboard();
                    userDashboard.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(window, "Password incorrecto",
                        "Error de credenciales", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) { 
                Logger.getLogger(LoginProcess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(window, "No existe ningun usuario con este numero de carnet",
                    "Error de credenciales", JOptionPane.ERROR_MESSAGE);
        }
    }
}
