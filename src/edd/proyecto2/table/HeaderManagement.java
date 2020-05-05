/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto2.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Omar
 */
public class HeaderManagement implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JComponent component = null;
        if(value instanceof String){
            component = new JLabel((String)value);
            ((JLabel)component).setHorizontalAlignment(SwingConstants.CENTER);
            ((JLabel)component).setSize(30, component.getWidth());
            ((JLabel)component).setPreferredSize(new Dimension(6, component.getWidth()));
        }
        component.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(255, 255, 255)));
        component.setOpaque(true);
        component.setBackground( new Color(65,65,65) );
        component.setToolTipText("Tabla Seguimiento");
        component.setForeground(Color.white);
        return component;
    }
    
}
