package albe.scrobblit;
import java.io.*;
import java.io.File.*;
import java.util.*;
import java.io.InputStreamReader.*;
import java.io.BufferedReader.* ;
import javax.swing.*;
import org.jdom.*; 
import org.jdom.input.*;
import java.net.*;
import java.awt.*;





public class Scrobblit {
    public static int i=1;
    public static int k=1;
    public static String versione = "2.02";
    public static boolean consoleEnabled = false;
    public static Dizionario dizionario = new Dizionario();
    public static Frame frame = new Frame();
    public static CreaTrackList LocalTrackList;
    public static CreaTrackList iTunesTrackList;
    public static ArrayList<Track> TracceDaScrobblare = new ArrayList<Track>();
    public static String username,sk, iTunesPath;
    public final static userpass userpassdialog = new userpass(frame,false);
    
    public static int CreaTimeStamp(String s) {           
        int anno, mese, giorno,ore,minuti,secondi;
        anno=Integer.parseInt(s.substring(0, 4));
        mese=Integer.parseInt(s.substring(5, 7));
        giorno=Integer.parseInt(s.substring(8,10));
        ore=Integer.parseInt(s.substring(11,13));
        minuti=Integer.parseInt(s.substring(14,16));
        secondi=Integer.parseInt(s.substring(17,19));
        int timeYear = 0;
        switch(anno) {
            case 2011: {timeYear = 1293840000;break;}
            case 2012: {timeYear = 1325376000;break;}
            case 2013: {timeYear = 1356998400;break;}
            case 2014: {timeYear = 1388534400;break;}
            case 2015: {timeYear = 1420070400;break;}
        }
        int residuo = secondi+minuti*60+ore*3600+(giorno-1)*86400;
        int residuomesi=0;
        switch (mese) {
            case 1: {residuomesi=0;break;}
            case 2: {residuomesi=2678400;break;}
            case 3: {residuomesi=5097600;break;}
            case 4: {residuomesi=7772400;break;}
            case 5: {residuomesi=10364400;break;}
            case 6: {residuomesi=13042800;break;}
            case 7: {residuomesi=15634800;break;}
            case 8: {residuomesi=18313200;break;}
            case 9: {residuomesi=20991600;break;}
            case 10: {residuomesi=23583600;break;}
            case 11: {residuomesi=26262000;break;}
            case 12: {residuomesi=28857600;break;}   

        }
        int timestamp=timeYear+residuo+residuomesi;
       
        return timestamp;
    }
    public static void ScrobblaTracce (ArrayList<Track> lista) {
        String api_key="91de4539bbb0d5b2bdc36d23d3906246";
        String secret="f0ca15d7a6bc93ab5e7219887a843456";
        frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        
        try {
                de.umass.lastfm.Session session=de.umass.lastfm.Session.createSession("91de4539bbb0d5b2bdc36d23d3906246", "f0ca15d7a6bc93ab5e7219887a843456", sk);
                
                java.util.List<de.umass.lastfm.scrobble.ScrobbleData> DaScrobblare = new ArrayList<de.umass.lastfm.scrobble.ScrobbleData>();
                for (int a=0;a<lista.size();a++) 
                    DaScrobblare.add(new de.umass.lastfm.scrobble.ScrobbleData(lista.get(a).getArtista(), 
                                                                               lista.get(a).getNome(), 
                                                                               CreaTimeStamp(lista.get(a).getDataUltimoAscolto()),
                                                                               lista.get(a).getDuration(),
                                                                               lista.get(a).getAlbum(),
                                                                               "",
                                                                               "",
                                                                               0,
                                                                               ""));                
                
                java.util.List<de.umass.lastfm.scrobble.ScrobbleResult>  scrobbleresult = new ArrayList<de.umass.lastfm.scrobble.ScrobbleResult>();
                scrobbleresult= de.umass.lastfm.Track.scrobble(DaScrobblare, session);
                int successcount=0;   //tiene conto del numero di tracce inviate
                int insuccesscount=0;
                for (int a=0;a<scrobbleresult.size();a++) { 
                    if (scrobbleresult.get(a).isSuccessful()) 
                        successcount++;
                    
                    else 
                        insuccesscount++;
                }
                                
                frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                IO.print(frame,successcount+dizionario.MSsuccess+insuccesscount+dizionario.MSrejected);
        }
        catch (Exception e) {
            frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            IO.print(frame,dizionario.MSerror+e.getMessage()+"\nProbabilmente non sei connesso a internet.");
        }
    }
    public static void StampaTraccia(Track t,int count){
            if (t.getArtista().contentEquals("")) t.setArtista("NoArtist");
            if (t.getNome().contentEquals("")) t.setNome("NoName");
            if (t.getDataUltimoAscolto().contentEquals("")) t.setDataUltimoAscolto("NoDate"); 
            System.out.print(t.getNome());
            System.out.print(" by ");
            System.out.print(t.getArtista());
            System.out.print(", listened ");
            System.out.print(count);
            System.out.print(" times, ");
            System.out.print("Last played on ");
            System.out.print(t.getDataUltimoAscolto());
            System.out.print("\n");
        }
    static void copy(File src, File dst) throws IOException {
    InputStream in = new FileInputStream(src);
    OutputStream out = new FileOutputStream(dst);

    // Transfer bytes from in to out
    byte[] buf = new byte[1024];
    int len;
    while ((len = in.read(buf)) > 0) {
        out.write(buf, 0, len);
    }
    in.close();
    out.close();
}
    public static String  FileCheck()  {
        /* METODO PER LEGGERE IL PERCORSO DI LOCALTRACKLIST
         * DA UN FILE E QUINDI VEDERE SE ESISTE LOCALTRACKLIST
         */
        String file ="";
        try   {
            FileReader pathiTunes=new FileReader(java.lang.System.getProperty("user.home")+File.separator+"Scrobblit"+File.separator+versione+File.separator+"path.txt");
            char[] buff = new char[100];
            pathiTunes.read(buff);
            iTunesPath=String.copyValueOf(buff);
            return iTunesPath;         
        }
        catch (Exception e){            
            File LocalTrackListFile=new File((java.lang.System.getProperty("user.home")+File.separator+".iTunesScrobbler"+File.separator+versione+File.separator+"iTunes Music Library.xml"));
            //devo implementare tutto questo in un metodo
            if (!LocalTrackListFile.exists()) {   //se il file non esiste...
                String[] possibiliPath = {System.getProperty("user.home")+"/Music/iTunes/iTunes Music Library.xml",
                                          System.getProperty("user.home")+"\\My Documents\\My Music\\iTunes\\iTunes Music Library.xml",
                                          System.getProperty("user.home")+"\\Music\\iTunes\\iTunes Music Library.xml",
                                          System.getProperty("user.home")+"\\My Music\\iTunes\\iTunes Music Library.xml",
                                          System.getProperty("user.home")+"\\My Documents\\My Music\\iTunes\\iTunes Music Library.xml",
                                          System.getProperty("user.home")+File.separator+".local"+File.separator+"share"+File.separator+"rhythmbox"+File.separator+"rhythmdb.xml"
                                        };
                for (int i=0;i<6;i++) {
                    File possibilePathFile;
                    possibilePathFile = new File(possibiliPath[i]);
                    if (possibilePathFile.exists()) {
                        file = possibilePathFile.getAbsolutePath();
                        break;
                    }
                }
                        try {
                            File echeche = new File(java.lang.System.getProperty("user.home")+File.separator+"Scrobblit"+File.separator+versione+File.separator+"path.txt");               
                            if (!echeche.exists()) {
                                boolean nwdnw=(new File(java.lang.System.getProperty("user.home")+File.separator+"Scrobblit"+File.separator+versione+File.separator)).mkdirs();
                                echeche.createNewFile();
                            }
                            FileWriter scrittore = new FileWriter(java.lang.System.getProperty("user.home")+File.separator+"Scrobblit"+File.separator+versione+File.separator+"path.txt");
                            
                            iTunesPath=file;
                            scrittore.write(file);
                            scrittore.close();
                            File Originale=new File(file);
                            File Copia=new File(java.lang.System.getProperty("user.home")+File.separator+"Scrobblit"+File.separator+versione+File.separator+"iTunes Music Library.xml");
                            copy(Originale,Copia);
                        }
                        catch (IOException eio) {
                            
                        }
                    }
                } 
            
            return file;    
    }
    public static void ControlloVersione() {
        try {
            frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            SAXBuilder builder = new SAXBuilder();
            URL link = new URL("http://prodigious.altervista.org/Scrobblit/controlloversione.php?ver="+versione);
            Document document = builder.build(link);
            Element rootElement = document.getRootElement();                  //ottiene il Token
            String response = rootElement.getChild("NV").getText();
            frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            if (response.contains("si"))
                if (IO.confirm(frame, dizionario.MSnuovaVersione)==JOptionPane.YES_OPTION){
                    Desktop desktop = Desktop.getDesktop();
                    URI browseURI;
                    browseURI = new URI("http://prodigious.altervista.org/Scrobblit/index.php");     //Apre pagina internet
                    desktop.browse(browseURI);                  
                }
        }
        catch (Exception e) {
            frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }
    public static void console() {
        System.out.print("\n\nSCROBBLIT version "+versione+" (c) 2011\nType \"help\" for a list of commands\n");
        byte[] querya = new byte[24];
        String query = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.print("Command: ");
                query = "";
                query = br.readLine();
            }
            catch (IOException e) {
                 
            }
            if (query.equals("exit")) 
                System.exit(1);
            else if (query.equals("help")) {
                System.out.print("    detect: Detect new tracks\n");
                System.out.print("    scrobble: Scrobble ALL tracks\n");
                System.out.print("    authorize: authorize Scrobblit by entering user and password\n");
                System.out.print("    disconnect: disconnect by the current session\n");
                System.out.print("    exit: close this program\n");
                System.out.print("    feedback: send a feedback\n");
                System.out.print("    cpdat: copy database file\n");
                System.out.print("    about: show credits\n");
            }
            else if (query.equals("about")) 
                System.out.print("Scrobblit version "+versione+", freeware (c) 2011 by Alberto Zichittella\n"
                        + "www.last.fm/user/otrebla86\n"
                        + "a.zichittella@gmail.com\n");
            else if (query.equals("detect"))
                frame.detect();
            else if (query.equals("scrobble")){
                try {
                    frame.scrobble();
                }
                catch(Exception e) {
                    System.out.print("No tracks detected. Use \"detect\" first.\n");
                }
            }
                
            else System.out.print("Cannot recognize \""+query+"\" Type \"help\" for a list of valid commands\n");
        }
    }
        public static void autorizza(){
        File filesk = new File(java.lang.System.getProperty("user.home")+File.separator+"Scrobblit"+File.separator+versione+File.separator+"sk.txt");
            File fileuser = new File(java.lang.System.getProperty("user.home")+File.separator+"Scrobblit"+File.separator+versione+File.separator+"user.txt");
            if (fileuser.exists()) {
                try{
                    FileReader lettore = new FileReader(fileuser);
                    char[] buff = new char[(int)fileuser.getAbsoluteFile().length()];
                    lettore.read(buff);
                    username=String.copyValueOf(buff);
                    frame.getjLabel2().setText(dizionario.LBloggedAs+username);
                }
                catch (IOException e) {
                    IO.err(frame, dizionario.MSerror);
                }
                    
            }
            if (filesk.exists()) {
                try{
                    FileReader lettore = new FileReader(filesk);
                    char[] buff = new char[32];
                    lettore.read(buff);
                    sk=String.copyValueOf(buff);
                    frame.getjMenuItem5().setEnabled(true);
                    lettore.close();
                }
                catch (IOException e) {
                    IO.err(frame, dizionario.MSerror);
                }
                    
            }
            else {userpassdialog.setVisible(true);
                       while (userpassdialog.isVisible())
                           {                           
                           }
                if (userpassdialog.getClickedButton().equals("OK"))                    
                    try {  
                        
                        String key = "91de4539bbb0d5b2bdc36d23d3906246";      // api key
                        String secret = "f0ca15d7a6bc93ab5e7219887a843456";   // api secret
                        String user = userpassdialog.getUser();     // user name
                        String password = userpassdialog.getPassword(); // user's password
                        de.umass.lastfm.Session session = de.umass.lastfm.Authenticator.getMobileSession(user, password, key, secret);
                        try {
                            sk=session.getKey();
                            frame.getjMenuItem5().setEnabled(true);
                        }
                        catch (Exception e) {
                            IO.err(frame,"Nome utente e/o password errati.");
                        }
                        username = session.getUsername();
                        fileuser = new File(java.lang.System.getProperty("user.home")+File.separator+"Scrobblit"+File.separator+versione+File.separator+"user.txt");
                        FileWriter scrittore = new FileWriter(fileuser);                        
                        scrittore.write(username);
                        scrittore.close();                        
                        if (userpassdialog.salvaPassword()) {
                            filesk = new File(java.lang.System.getProperty("user.home")+File.separator+"Scrobblit"+File.separator+versione+File.separator+"sk.txt");
                            scrittore = new FileWriter(filesk);
                            scrittore.write(sk);
                            scrittore.close();
                        }
                    }
                    catch (Exception e) {
                        IO.err(frame,dizionario.MSerror);
                        frame.dispose();
                    }
                else {
                    frame.dispose();
                }
            }
    } 
    public static void caricaTrackList() {
        File iTunesTrackListFile = new File (FileCheck());
        iTunesTrackList = new CreaTrackList(iTunesTrackListFile.getPath());
        LocalTrackList = new CreaTrackList(java.lang.System.getProperty("user.home")+File.separator+"Scrobblit"+File.separator+versione+File.separator+"iTunes Music Library.xml");
        if ((iTunesTrackList.success)&(LocalTrackList.success)) frame.EnableButton();   
        else {
            IO.err(frame,dizionario.MSerror);
            frame.dispose();
        }
    }
    public static void main(String[] args) {
            try {
                int i=0;
                do {
                    if (args[i].equals("-console"))
                    consoleEnabled = true;
                    i++;
                } while (true);
            }
            catch (Exception e) {}
            System.out.print("\nLoading...\n");
            ControlloVersione();
            caricaTrackList();
            autorizza();          
            if (consoleEnabled)
                console();
            frame.setVisible(true);
    } 
}
    

