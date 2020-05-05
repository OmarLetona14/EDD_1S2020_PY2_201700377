/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.table;

import edd.proyecto2.model.LocalData;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Omar
 */
public class BookTableModel extends DefaultTableModel {
    
    String[] titulos;
    Object[][] datos;

   //
   // Determina el modelo con el que se va a construir la tabla
   // @param datos
   // @param titulos
   //
    public BookTableModel(Object[][] datos, String[] titulos) {
     super();
     this.titulos=titulos;
     this.datos=datos;
     setDataVector(datos, titulos);
    }

    public BookTableModel() {
     // TODO Auto-generated constructor stub
    }

    @Override
    public boolean isCellEditable (int row, int column)
    {
     //Definimos si una celda puede ser o no editable
     return false;
    }
    
}
