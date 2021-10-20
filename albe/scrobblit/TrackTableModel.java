/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package albe.scrobblit;
import javax.swing.table.AbstractTableModel;
import java.util.Hashtable;
import java.awt.Point;

/**
 *
 * @author Alberto
 */
public class TrackTableModel extends AbstractTableModel {
  private Hashtable lookup;

  private final int rows;

  private final int columns;

  private final String headers[];

  public TrackTableModel(int rows, String columnHeaders[]) {
    if ((rows < 0) || (columnHeaders == null)) {
      throw new IllegalArgumentException(
          "Invalid row count/columnHeaders");
    }
    this.rows = rows;
    this.columns = columnHeaders.length;
    headers = columnHeaders;
    lookup = new Hashtable();
  }

  public int getColumnCount() {
    return columns;
  }

  public int getRowCount() {
    return rows;
  }

  public String getColumnName(int column) {
    return headers[column];
  }

  public Object getValueAt(int row, int column) {
    return lookup.get(new Point(row, column));
  }

  public void setValueAt(Object value, int row, int column) {
    if ((rows < 0) || (columns < 0)) {
      throw new IllegalArgumentException("Invalid row/column setting");
    }
    if ((row < rows) && (column < columns)) {
      lookup.put(new Point(row, column), value);
    }
  }
}
