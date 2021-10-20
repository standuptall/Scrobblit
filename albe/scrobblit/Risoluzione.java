/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Risoluzione.java
 *
 * Created on 18-ott-2011, 16.16.07
 */
package albe.scrobblit;
import albe.scrobblit.Scrobblit;
import java.io.*;

/**
 *
 * @author Alberto
 */
public class Risoluzione extends javax.swing.JFrame
    implements java.beans.PropertyChangeListener{

    /** Creates new form Risoluzione */
    public Risoluzione() {
        initComponents();
        controlProcess.addPropertyChangeListener(this);
    }
    
    @Override
    public void propertyChange(java.beans.PropertyChangeEvent evt) {
        if ("progress" == evt.getPropertyName()) {
            this.IOlabel.setText(evt.getNewValue().toString());
        } 
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internetLabel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        libreriaLabel = new javax.swing.JLabel();
        cartellaLabel = new javax.swing.JLabel();
        copiaLabel = new javax.swing.JLabel();
        IOlabel = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Attendere finché la procedura non finisca.");

        jLabel2.setText("Libreria di iTunes: ");

        jLabel3.setText("Cartella principale di Scrobblit: ");

        jLabel4.setText("Esistenza della copia della libreria di iTunes:");

        jLabel5.setText("Prova I/O:");

        jLabel6.setText("Connessione a internet:");

        libreriaLabel.setText(" ");

        cartellaLabel.setText(" ");

        copiaLabel.setText(" ");

        IOlabel.setText(" ");

        jLabel11.setText(" ");

        jButton1.setText("OK");

        jButton2.setText(Scrobblit.dizionario.BTannulla);

        javax.swing.GroupLayout internetLabelLayout = new javax.swing.GroupLayout(internetLabel);
        internetLabel.setLayout(internetLabelLayout);
        internetLabelLayout.setHorizontalGroup(
            internetLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(internetLabelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(internetLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(internetLabelLayout.createSequentialGroup()
                        .addGroup(internetLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(internetLabelLayout.createSequentialGroup()
                        .addGroup(internetLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addGroup(internetLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(libreriaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                            .addComponent(cartellaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(copiaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(IOlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11))))
                .addContainerGap())
        );
        internetLabelLayout.setVerticalGroup(
            internetLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(internetLabelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(54, 54, 54)
                .addGroup(internetLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(internetLabelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))
                    .addGroup(internetLabelLayout.createSequentialGroup()
                        .addComponent(libreriaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cartellaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(copiaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(IOlabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addGroup(internetLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(internetLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(internetLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Risoluzione().setVisible(true);
            }
        });
    }
    public ControlProcess controlProcess = new ControlProcess();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IOlabel;
    private javax.swing.JLabel cartellaLabel;
    private javax.swing.JLabel copiaLabel;
    private javax.swing.JPanel internetLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel libreriaLabel;
    // End of variables declaration//GEN-END:variables
    class ControlProcess 
        extends javax.swing.SwingWorker<Void,Object> {
        
        @Override
        public Void doInBackground() {
            /*---------CONTROLLO SE ESISTE IL FILE DI ITUNES---------------*/
            File libreria = new File(Scrobblit.iTunesPath);
            if (libreria.exists())
                publish(true);
            else publish (false);
            setProgress(20);
            /*----------CONTROLLO CARTELLA PRINCIPALE----------------------*/
            File cartellaPrincipale = new File(System.getProperty("user.home")+File.separator+"Scrobblit");
            if (cartellaPrincipale.exists())
                publish(true);
            else publish (false);
            setProgress(40);
            /*-------------CONTROLLO SE ESISTE LA COPIA DEL FILE-----------------------*/
            cartellaPrincipale = new File(System.getProperty("user.home")+File.separator+"Scrobblit"+File.separator+Scrobblit.versione+File.separator+"iTunes Music Library.xml");
            if (cartellaPrincipale.exists())
                publish(true);
            else publish (false);
            setProgress(60);
            return null;
        }
        @Override
        public void done() {

        }
    }
}

