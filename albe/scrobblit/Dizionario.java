/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package albe.scrobblit;
import org.jdom.input.*;
import org.jdom.*;
import java.io.*;

/**
 *
 * @author Alberto
 */
public class Dizionario {
    Element messaggi;
    Element labels;
    Element tasti;
    String language;
    public Dizionario () {
        try {
            File file= new File(java.lang.System.getProperty("user.home")+File.separator+"Scrobblit"+File.separator+albe.scrobblit.Scrobblit.versione+File.separator+"lan.txt");
            FileReader lettore= new FileReader(file);
            char[] cbuf = new char[2]; 
            lettore.read(cbuf);
            language = String.copyValueOf(cbuf);
            if (language.equals("en"))
                language = "INGLESE";
            if (language.equals("it"))
                language = "ITALIANO";
        }
        catch (IOException e) {
            if (System.getProperty("user.language").contentEquals("it"))
                language="ITALIANO";
            else language="INGLESE";
            String s="";
            if (language.equals("ITALIANO"))
                s="it";
            if (language.equals("INGLESE"))
                s="en";
            try {
                    File file= new File(java.lang.System.getProperty("user.home")+File.separator+"Scrobblit"+File.separator+albe.scrobblit.Scrobblit.versione+File.separator+"lan.txt");
                    FileWriter scrittore= new FileWriter(file);
                    scrittore.write(s);
                    scrittore.close();
              }
              catch (IOException ex) {
                    
              }
        }
        
        java.net.URL path = (getClass().getResource("/albe/scrobblit/dizionario.xml"));
        System.out.print(path);
        try {
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(path);
            Element elemento = document.getRootElement();
            messaggi = elemento.getChild("MESSAGGI");
            labels = elemento.getChild("LABELS");
            tasti = elemento.getChild("TASTI");
            
        }
        catch (Exception e) {
            
        }
        caricaLingua();
    }
    
    public void aggiornaLingua () {
        caricaLingua();
    }
    private void caricaLingua () {
        BTrileva = getElementByAttribute(tasti,"rileva").getChild(language).getValue();
        BTselezionaTutto = getElementByAttribute(tasti,"selezionaTutto").getChild(language).getValue();
        BTdeselezionaTutto = getElementByAttribute(tasti,"deselezionaTutto").getChild(language).getValue();
        BTscrobbla = getElementByAttribute(tasti,"scrobbla").getChild(language).getValue();
        BTabout = getElementByAttribute(tasti,"about").getChild(language).getValue();
        BTcopiaDatabase = getElementByAttribute(tasti,"copiaDatabase").getChild(language).getValue();
        BTesci = getElementByAttribute(tasti,"esci").getChild(language).getValue();
        BTcambiaLingua = getElementByAttribute(tasti,"cambiaLingua").getChild(language).getValue();
        BTdisconnetti = getElementByAttribute(tasti,"disconnetti").getChild(language).getValue();
        BTaiuto = getElementByAttribute(tasti,"aiuto").getChild(language).getValue();
        BTannulla = getElementByAttribute(tasti,"annulla").getChild(language).getValue();
        
        LBselectedTracks = getElementByAttribute(labels,"selectedTracks").getChild(language).getValue();
        LBloggedAs = getElementByAttribute(labels,"loggedAs").getChild(language).getValue();
        LBimmettiCredenziali = getElementByAttribute(labels,"immettiCredenziali").getChild(language).getValue();
        LBnomeUtente = getElementByAttribute(labels,"nomeUtente").getChild(language).getValue();
        LBmemorizzaPass = getElementByAttribute(labels,"memorizzaPass").getChild(language).getValue();
        LBartista = getElementByAttribute(labels,"artista").getChild(language).getValue();
        LBtitolo = getElementByAttribute(labels,"titolo").getChild(language).getValue();
        LBdataUltimoAscolto = getElementByAttribute(labels,"dataUltimoAscolto").getChild(language).getValue();
        LBnome = getElementByAttribute(labels,"nome").getChild(language).getValue();
        LBindirizzo = getElementByAttribute(labels,"indirizzo").getChild(language).getValue();
        LBtesto = getElementByAttribute(labels,"testo").getChild(language).getValue();
        LBcontact = getElementByAttribute(labels,"contact").getChild(language).getValue();
        
        MSauth=getElementByAttribute(messaggi,"auth").getChild(language).getValue();
        MSerror=getElementByAttribute(messaggi,"error").getChild(language).getValue();
        MSsuccess=getElementByAttribute(messaggi,"success").getChild(language).getValue();
        MSrejected=getElementByAttribute(messaggi,"rejected").getChild(language).getValue();
        MSauthConfirm=getElementByAttribute(messaggi,"authConfirm").getChild(language).getValue();
        MSnoTracks=getElementByAttribute(messaggi,"noTracks").getChild(language).getValue();
        MSconfirmTrack1=getElementByAttribute(messaggi,"confirmTrack1").getChild(language).getValue();
        MSconfirmTrack2=getElementByAttribute(messaggi,"confirmTrack2").getChild(language).getValue();
        MSabout=getElementByAttribute(messaggi,"about").getChild(language).getValue();
        MSdatabaseQuest=getElementByAttribute(messaggi,"databaseQuest").getChild(language).getValue();
        MSdatabaseSucc=getElementByAttribute(messaggi,"databaseSucc").getChild(language).getValue();
        MSnuovaVersione=getElementByAttribute(messaggi,"nuovaVersione").getChild(language).getValue();
        MSriavvia=getElementByAttribute(messaggi,"riavvia").getChild(language).getValue();
        MSuserPassErrati = getElementByAttribute(messaggi,"userPassErrati").getChild(language).getValue();
        MScampiVuoti = getElementByAttribute(messaggi,"campiVuoti").getChild(language).getValue();
        MSemailNonValida = getElementByAttribute(messaggi,"emailNonValida").getChild(language).getValue();
    }
    private Element getElementByAttribute(Element e,String name) {
        java.util.List lista = e.getChildren();
        java.util.Iterator iteratore = lista.iterator();
        int i=0;
        while (iteratore.hasNext()) {
            Element element = (Element)lista.get(i);
            if (element.getAttribute("ID").getValue().equals(name)) {
                return element;
            }
            i++;
        }
        return null;
    }
    

        public String BTrileva;
        public String BTselezionaTutto;
        public String BTdeselezionaTutto;
        public String BTscrobbla;
        public String BTabout;
        public String BTcopiaDatabase;
        public String BTesci;
        public String BTcambiaLingua;
        public String BTdisconnetti;
        public String BTaiuto;
        public String BTannulla;

    
        public String MSauth;
        public String MSerror;
        public String MSsuccess;
        public String MSrejected;
        public String MSauthConfirm;
        public String MSnoTracks;
        public String MSconfirmTrack1;
        public String MSconfirmTrack2;
        public String MSabout;
        public String MSdatabaseQuest;
        public String MSdatabaseSucc;
        public String MSnuovaVersione;
        public String MSriavvia;
        public String MSuserPassErrati;
        public String MScampiVuoti;
        public String MSemailNonValida;
        
        public String LBselectedTracks;
        public String LBloggedAs; 
        public String LBimmettiCredenziali;
        public String LBnomeUtente;
        public String LBmemorizzaPass;
        public String LBartista;
        public String LBtitolo;
        public String LBdataUltimoAscolto;
        public String LBnome;
        public String LBindirizzo;
        public String LBtesto;    
        public String LBcontact;
}
