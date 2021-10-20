
package albe.scrobblit;
import org.xml.sax.*;
import java.net.*;
import java.io.*;
import javax.swing.*;
public class XMLResolver implements EntityResolver {
    @Override
    public InputSource resolveEntity(String publicID, String systemID)
        throws SAXException {
        File file = new File(java.lang.System.getProperty("user.home")+File.separator+"Scrobblit"+File.separator+albe.scrobblit.Scrobblit.versione+File.separator+"PropertyList-1.0.dtd.txt");
        if (!file.exists()) {
            try {
            URL url=new URL("http://www.apple.com/DTDs/PropertyList-1.0.dtd");
            InputStream in = url.openStream();
            OutputStream out = new FileOutputStream(java.lang.System.getProperty("user.home")+File.separator+"Scrobblit"+File.separator+albe.scrobblit.Scrobblit.versione+File.separator+"PropertyList-1.0.dtd.txt");
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            }
            catch (Exception e) {
                IO.err(albe.scrobblit.Scrobblit.frame, "Si è verificato un errore: "+e.getLocalizedMessage()+"\nProbabilmente non sei connesso ad internet.");
            }
        }
        else 
        if (systemID.equals("http://www.apple.com/DTDs/PropertyList-1.0.dtd")) {
            return new InputSource(java.lang.System.getProperty("user.home")+File.separator+"Scrobblit"+File.separator+albe.scrobblit.Scrobblit.versione+File.separator+"PropertyList-1.0.dtd.txt");
        }
        return null;
    }
}
