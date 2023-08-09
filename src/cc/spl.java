
package cc;

import java.util.logging.Level;
import java.util.logging.Logger;


public class spl extends javax.swing.JFrame {

    
    public spl() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pbar = new javax.swing.JProgressBar();
        ptxt = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setLayout(null);

        pbar.setBackground(new java.awt.Color(204, 204, 0));
        pbar.setForeground(new java.awt.Color(0, 204, 51));
        jPanel1.add(pbar);
        pbar.setBounds(20, 370, 590, 14);

        ptxt.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        ptxt.setForeground(new java.awt.Color(255, 255, 255));
        ptxt.setText("jLabel3");
        jPanel1.add(ptxt);
        ptxt.setBounds(40, 350, 220, 16);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cc/epflogo.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(-70, 30, 700, 330);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Dell\\Desktop\\java work\\Projects\\Real Life project\\bg4.jpg")); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 630, 410);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    public static void main(String args[]) {
        
       ///////////////////////////
        
        
        spl spas = new spl();
        spas.setVisible(true);
        
        
        
            
            try {
                for(int i=0;i<=100;i++){
                Thread.sleep(100);
                spas.ptxt.setText(i + "%");
                
                
                if(i == 10){
                    spas.ptxt.setText("Starting Events....");
                }
                if(i == 20){
                    spas.ptxt.setText("Loding background Modules....");
                }
                
                if(i == 40){
                    spas.ptxt.setText("Connecting to Database.....");
                }
                if(i == 65){
                    spas.ptxt.setText("Connection Succefull....");
                }
                 if(i == 70){
                    spas.ptxt.setText("Launching Application.......");
                }
                  if(i == 80){
                    spas.ptxt.setText("Welcome to EASY PRICE SUPERMARKET ........");
                }
                  
                  spas.pbar.setValue(i);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(spl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            new departmentSelect().setVisible(true);
            spas.dispose();
            
        
        
        
        
        
        /*
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new spl().setVisible(true);
            }
        });


*/
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar pbar;
    private javax.swing.JLabel ptxt;
    // End of variables declaration//GEN-END:variables
}
