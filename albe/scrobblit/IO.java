/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package albe.scrobblit;

/**
 *
 * @author Alberto
 */
public class IO {
    static boolean console = albe.scrobblit.Scrobblit.consoleEnabled;
    public static void print(javax.swing.JFrame component, String s) {
        if (console) 
            System.out.print(s);       
        else
        javax.swing.JOptionPane.showMessageDialog(component, s, "",javax.swing.JOptionPane.INFORMATION_MESSAGE);                
    }
    public static void err(javax.swing.JFrame component,String s) {
        if (console) 
            System.err.print(s);       
        else
        javax.swing.JOptionPane.showMessageDialog(component, s, "",javax.swing.JOptionPane.ERROR_MESSAGE);     
    }
    public static int confirm(javax.swing.JFrame component,String s) {
        if (console) {
            do {
                System.out.print(s+"[YES/NO]: ");
                java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
                String query = "";
                try {
                    query = br.readLine();
                }
                catch(Exception e) {

                }
                if ((query.equals("YES"))||(query.equals("yes")))
                     return 0;
                else if ((query.equals("NO"))||(query.equals("no")))
                    return 1;
                else continue;
            }
            while (true);
        }
        else 
            return javax.swing.JOptionPane.showConfirmDialog(component, s, "", javax.swing.JOptionPane.YES_NO_OPTION);
    }
}
