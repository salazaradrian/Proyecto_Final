/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ordenes;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 *
 * @author rperez
 */
public class ProgressCellRenderer extends JProgressBar implements TableCellRenderer{
    
    public ProgressCellRenderer() {
        setStringPainted(true);
    }
    
    //@Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof Integer) {
            setValue((Integer) value);
        }
        return this;
    }
    
    
}
