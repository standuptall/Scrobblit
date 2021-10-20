/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package albe.scrobblit;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.table.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.UIManager;

class CheckBoxTableCellRenderer
 extends JCheckBox
 implements TableCellRenderer {
 
 Border noFocusBorder;
 Border focusBorder;

 public CheckBoxTableCellRenderer() {
   super();
   //setOpaque(true);
   setContentAreaFilled(true);  // use this instead of setOpaque()
   setBorderPainted(true);
   setHorizontalAlignment(SwingConstants.CENTER);
   setVerticalAlignment(SwingConstants.CENTER);
 }

    @Override
 public Component getTableCellRendererComponent(JTable table, Object value,
                                                boolean isSelected,
                                                boolean hasFocus,
                                                int row, int column) {
   
   if(table == null) {
     // ???
   }
   else {
     if (isSelected) {
         
         setForeground(table.getSelectionForeground());
         setBackground(table.getSelectionBackground());
     }
     else {
         
         setForeground(table.getForeground());
         setBackground(table.getBackground());
     }

     setEnabled(table.isEnabled());
     setComponentOrientation(table.getComponentOrientation());

     if (hasFocus) {
       if (focusBorder == null) {
         focusBorder = UIManager.getBorder("Table.focusCellHighlightBorder");
       }
       setBorder(focusBorder);
     }
     else {
       if (noFocusBorder == null) {
         if (focusBorder == null) {
           focusBorder = UIManager.getBorder("Table.focusCellHighlightBorder");
         }
         if (focusBorder != null) {
           Insets n = focusBorder.getBorderInsets(this);
           noFocusBorder = new EmptyBorder(n);
         }
       }
       setBorder(noFocusBorder);
     }
 

     setSelected(Boolean.TRUE.equals(value));
   }
   return this;
 }
}
