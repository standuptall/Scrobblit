package albe.scrobblit;
import org.jdom.*; 
import org.jdom.input.*;
import java.io.*;
import java.io.File.*;
import java.util.*;
import java.io.InputStreamReader.*;
import java.io.BufferedReader.* ;

public class CreaTrackList {
    public  CreaTrackList (String path) {
        ArrayList<Track> ListaTracce = new  ArrayList<Track>();
        String osName = System.getProperty("os.name"); 
        /*****************************************ROUTINE PER ITUNES***********************************************/
        if (osName.contains("Windows"))
            try {
                success=true;
                SAXBuilder builder = new SAXBuilder();
                builder.setEntityResolver(new albe.scrobblit.XMLResolver());
                Document document = builder.build(new File (path));
                Element rootElement = document.getRootElement();
                Element dict = rootElement.getChild(null); 
                List children = dict.getChildren();
                Iterator iterator = children.iterator(); 
                while (iterator.hasNext()){ 
                    Element element = (Element)iterator.next();
                    if (element.toString().contains("dict")){
                        dictTwoElement=element;
                        break;
                    }
                 }
                children = dictTwoElement.getChildren();          
                dictTwoList=children;            
                iterator = dictTwoList.iterator(); 
                while (iterator.hasNext()){ 
                    Element element = (Element)iterator.next();
                    children = element.getChildren();
                    Iterator iteratortwo = children.iterator();
                    while (iteratortwo.hasNext()){   
                        Element elementtwo = (Element)iteratortwo.next();

                        if (elementtwo.getText().contentEquals("Name")) {
                            elementtwo = (Element)iteratortwo.next();
                            ListaTracce.add(new Track());
                            ListaTracce.get(ListaTracce.size()-1).setNome(elementtwo.getText());
                            ListaTracce.get(ListaTracce.size()-1).setArtista(""); 
                            ListaTracce.get(ListaTracce.size()-1).setContatore("0");
                            ListaTracce.get(ListaTracce.size()-1).setDataUltimoAscolto("");
                        }
                        if (elementtwo.getText().contentEquals("Artist")) {
                            elementtwo = (Element)iteratortwo.next();                   
                            ListaTracce.get(ListaTracce.size()-1).setArtista(elementtwo.getText()); 

                        }
                        if (elementtwo.getText().contentEquals("Play Count")) {
                            elementtwo = (Element)iteratortwo.next();
                            ListaTracce.get(ListaTracce.size()-1).setContatore(elementtwo.getText());     
                        }    
                        if (elementtwo.getText().contentEquals("Play Date UTC")) {
                            elementtwo = (Element)iteratortwo.next();
                            ListaTracce.get(ListaTracce.size()-1).setDataUltimoAscolto(elementtwo.getText());     
                        }  
                        if (elementtwo.getText().contentEquals("Album")) {
                            elementtwo = (Element)iteratortwo.next();
                            ListaTracce.get(ListaTracce.size()-1).setAlbum(elementtwo.getText());     
                        } 
                        if (elementtwo.getText().contentEquals("Total Time")) {
                            elementtwo = (Element)iteratortwo.next();
                            ListaTracce.get(ListaTracce.size()-1).setDuration(Integer.parseInt(elementtwo.getText()));     
                        } 
                    }
                }  

            }
            catch (org.jdom.JDOMException e) {
                success=false;
                } 
            catch (IOException e) { 
                success=false;
                }
        /***************************************ROUTINE PER RHYTHMBOX***********************************************/
        else 
            try {
                success=true;
                SAXBuilder builder = new SAXBuilder();
                builder.setEntityResolver(new albe.scrobblit.XMLResolver());
                Document document = builder.build(new File (path));
                Element rootElement = document.getRootElement();
                List entries = rootElement.getChildren();
                Iterator iterator = entries.iterator();
                while (iterator.hasNext()) {
                    Element entry = (Element)iterator.next();
                    if (entry.getAttributeValue("type").equals("song")) {
                        entry.getChild("title").getValue();
                        ListaTracce.add(new Track(entry.getChild("artist").getValue(),
                                                  entry.getChild("title").getValue(),  
                                                  entry.getChild("album").getValue(),  
                                                  entry.getChild("last-played").getValue(),
                                                  entry.getChild("play-count").getValue()));
                    }
                        
                }
            }
            catch (Exception e) {
                success=false;
            }
        TrackList = ListaTracce;
    }
    public ArrayList<Track> getTrackList(){
        return TrackList;
    }
    ArrayList<Track> TrackList;
    Element dictTwoElement;
    List dictTwoList;
    public boolean success;
}
