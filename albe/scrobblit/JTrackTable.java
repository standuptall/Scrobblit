/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package albe.scrobblit;
import javax.swing.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.DefaultCellEditor;
import albe.scrobblit.Scrobblit.*;
import java.awt.Color;

/**
 *
 * @author Alberto
 */
public class JTrackTable extends JTable {
    
    public void setLista(ArrayList<Track> listaInput) {
        for (int i=0;i<listaInput.size();i++)
            totalTime += listaInput.get(i).getDuration();
            
        this.lista = this.raggruppa(listaInput);
        String[] columnNames = {" ",
                                Scrobblit.dizionario.LBartista,
                                Scrobblit.dizionario.LBtitolo,
                                "Album",
                                Scrobblit.dizionario.LBdataUltimoAscolto,
                                "n"};    
        Object[][] data = new Object[lista.size()][columnNames.length]; 

        Iterator iterator = lista.iterator();
        int i=0;
        while (iterator.hasNext()) {            
            Track track = (Track)iterator.next();
            data[i][0] = true;
            data[i][1] = track.getArtista();
            data[i][2] = track.getNome();
            data[i][3] = track.getAlbum();
            data[i][4] = getData(track.getDataUltimoAscolto());
            data[i][5] = String.valueOf(track.getCount());
            i++;
        } 
        javax.swing.table.DefaultTableModel tableModel = new javax.swing.table.DefaultTableModel(data,columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column>0)        //se è la prima colonna allora deve essere editabile
                    return false;
                else return true;
            }
        };
        this.setModel(tableModel);
        TableColumnModel tcm = this.getColumnModel();
        TableColumn tc = tcm.getColumn(0);
        tc.setCellRenderer(new CheckBoxTableCellRenderer());
        tc.setHeaderRenderer(new CheckBoxTableCellRenderer());
        tc.setHeaderValue(true);
        tc.setCellEditor(new DefaultCellEditor(new JCheckBox()));
        this.setCellSelectionEnabled(false);
        this.setShowVerticalLines(false);
        this.setShowHorizontalLines(false);
        this.getTableHeader().setReorderingAllowed(false);
        this.setBackground(new Color(240,240,240,255));
    }
    public ArrayList<Track> getLista(){
        return lista;
    }
    private String getData(String s) {
        int anno, mese, giorno,ore,minuti,secondi;
        anno=Integer.parseInt(s.substring(0, 4));
        mese=Integer.parseInt(s.substring(5, 7));
        giorno=Integer.parseInt(s.substring(8,10));
        ore=Integer.parseInt(s.substring(11,13))+1;  // GMT+1
        minuti=Integer.parseInt(s.substring(14,16));
        secondi=Integer.parseInt(s.substring(17,19));
        Calendar data = Calendar.getInstance(TimeZone.getTimeZone("GMT+1"));
        data.set(anno, mese, giorno, ore, minuti, secondi);
        String dataRappresentazione = String.valueOf(data.get(Calendar.MONTH))+ "/" +
                                      String.valueOf(data.get(Calendar.DAY_OF_MONTH))+ " " +
                                      String.valueOf(data.get(Calendar.HOUR))+ ":" +
                                      String.valueOf(data.get(Calendar.MINUTE))+ ":" +
                                      String.valueOf(data.get(Calendar.SECOND))  ;
        dataRappresentazione = String.format("%02d/%02d, %02d:%02d:%02d",data.get(Calendar.MONTH),data.get(Calendar.DAY_OF_MONTH),
                                                         data.get(Calendar.HOUR_OF_DAY),data.get(Calendar.MINUTE),  
                                                         data.get(Calendar.SECOND));
        return dataRappresentazione;
    }
    public int getSelectedIndices() {
        int count=0;
       
        for (int i=0;i<lista.size();i++){
                boolean c = (Boolean)this.getModel().getValueAt(i, 0); 
                if (c)
                     count=count+Integer.parseInt((String)this.getModel().getValueAt(i, 5));
            }
            
        
            
        return count;
    }
    
    public int getSingleSelectedIndices() {
        int count=0;
        for (int i=0;i<lista.size();i++){
                boolean c = (Boolean)this.getModel().getValueAt(i, 0); 
                if (c)
                     count++;
            }
        return count;
    }
    public ArrayList<Track> getSelectedTracks() {
        ArrayList<Track> listaReturn = new ArrayList<Track>();          
        for (int i=0;i<this.getLista().size();i++) {
            boolean c = (Boolean)this.getModel().getValueAt(i, 0);     
                if (c) {                                                //Se la riga è selezionata
                    for (int k=0;k<Integer.parseInt((String)this.getModel().getValueAt(i, 5));k++)    
                        listaReturn.add(this.getTrackAt(i));            //aggiungi count volte la stessa traccia     
                }
        }
        return listaReturn;    
    }
    @Override
    public void selectAll() {
       
        for (int i=0;i<lista.size();i++){
            
            this.getModel().setValueAt(true,i, 0);   
        }
    }
    public void deselectAll() {
       
        for (int i=0;i<lista.size();i++){
            
            this.getModel().setValueAt(false,i, 0);   
        }
    }
    
    public boolean isSelectionEmpty(){
        if (getSelectedIndices()==0)
            return true;
        else return false;
    }
    
    public boolean isSelectedIndex(int row){
        boolean c = (Boolean)this.getModel().getValueAt(row, 0);
        if (c) return true;
        else return false;
    }
    
    public long getTotalTime(){
        return totalTime;
    }
    
    public Track getTrackAt(int row){
        //String nome, artista, dataUltimoAscolto,album;
        //Track track = new Track(artista, nome, album, dataUltimoAscolto); 
        return lista.get(row);
    }
    
    public void azzera() {
        DefaultTableModel model=(DefaultTableModel) this.getModel();
        model.setRowCount(0);
        
    }
    
    public ArrayList<Track> raggruppa(ArrayList<Track> lista) {
        ArrayList<Track> listaYeah = new ArrayList<Track>();
        String artista,titolo,album,dataUltimoAscolto;
        String newArtista,newTitolo,newAlbum;
        for (int i=0;i<lista.size();i++) {
            int k=1;   //numero di volte ascoltate
            artista = (lista.get(i).getArtista()==null) ? "" : lista.get(i).getArtista();
            titolo = (lista.get(i).getNome()==null) ? "" : lista.get(i).getNome();
            album = (lista.get(i).getAlbum()==null) ? "" : lista.get(i).getAlbum();
            dataUltimoAscolto = (lista.get(i).getDataUltimoAscolto()==null) ? "" : lista.get(i).getDataUltimoAscolto();
            try {
                newArtista = (lista.get(i+1).getArtista()==null) ? "" : lista.get(i+1).getArtista();
                newTitolo = (lista.get(i+1).getNome()==null) ? "" : lista.get(i+1).getNome();
                newAlbum = (lista.get(i+1).getAlbum()==null) ? "" : lista.get(i+1).getAlbum();
            }
            catch (Exception e) {
                newArtista = "";
                newTitolo="";
                newAlbum="";
            }
            if ((artista.equals(newArtista))&(titolo.equals(newTitolo))&(album.equals(newAlbum))) {
                
                while ((artista.equals(newArtista))&(titolo.equals(newTitolo))&(album.equals(newAlbum))) {                    
                    k++;
                    try {
                        newArtista = (lista.get(i+k).getArtista()==null) ? "" : lista.get(i+k).getArtista();
                        newTitolo = (lista.get(i+k).getNome()==null) ? "" : lista.get(i+k).getNome();
                        newAlbum = (lista.get(i+k).getAlbum()==null) ? "" : lista.get(i+k).getAlbum();
                    }
                    catch (IndexOutOfBoundsException e) {
                        break;
                    }
                    
                }
                i = i+k-1;
            }
            listaYeah.add(new Track(artista,titolo,album,dataUltimoAscolto,k));
        }
        return listaYeah;
    }
        
    private ArrayList<Track> lista;
    private long totalTime=0x0;
}
