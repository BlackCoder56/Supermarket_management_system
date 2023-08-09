
package cc;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


public class salePro extends javax.swing.JFrame implements Runnable {

    
    public salePro() {
        setUndecorated(true);
        initComponents();
        
        // start of code for time thread
        
        Thread tume = new Thread(this);
        tume.start();
            
        // End of code for time thread
        
        
        connection();
        code.requestFocus();
        ptble();
        btt1.setEnabled(false);
       
    }
     
    // time strings
     int hour,second,minute;
    String currentTime;
    String storeDay;
    String storeDate;
    //end of time strings
    Connection conn;
    PreparedStatement pst;
    PreparedStatement pst1;
    PreparedStatement pst2;
    PreparedStatement pst3,pst4,pst5;
    ResultSet rs,rss,rs1,rs2;
    
    DefaultTableModel dtf;
    
    public void connection(){
        
        try {
            conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-Q9EIF80\\SQLEXPRESS;databaseName=epsmk;integratedSecurity=true;encrypt=false;");
        } catch (SQLException ex) {
            Logger.getLogger(createProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
     Double repo,newR,curentR;
     
    public void sales(){
        
        String ch = "shs."+cashh.getText()+".0";
        String ball = bal.getText();
        String ggt = "shs."+gt.toString();
        
        int lastid = 2000;
        
       //date declaration
       String cdat = storeDate;//cdate.getText();
       String ttime = tclock.getText();
       
        try {
             String s1 = "insert into oosolo(sutt,pay,bal,date,time) values(?,?,?,?,?)";
            pst1 = conn.prepareStatement(s1,Statement.RETURN_GENERATED_KEYS);
            pst1.setString(1, ggt);
            pst1.setString(2, ch);
            pst1.setString(3, ball);
            pst1.setString(4, cdat);
            pst1.setString(5, ttime);
            pst1.executeUpdate();
            rs = pst1.getGeneratedKeys();
            
            if(rs.next()){
                lastid = rs.getInt(1);
            }
            
            //code report total start
            
            
            
            
            //code report total start
            
          int rows = stbl.getRowCount();
            
             String s2 = "insert into salesProduct(soloid,name,price,qty,tota) values(?,?,?,?,?)";
             String s3 = "insert into rptTable(prodName,price,qty,toto) values(?,?,?,?)";
             pst2 = conn.prepareStatement(s2);
             pst4 = conn.prepareStatement(s3);
             
             
             
             String ppname = "" ;
             String priice;
             String qqty;
             Double tot;
             
             
             for(int i=0;i<stbl.getRowCount();i++){
                 
                 ppname = (String)stbl.getValueAt(i,0);
                 priice = (String)stbl.getValueAt(i, 1);
                 qqty = (String)stbl.getValueAt(i,2);
                 tot = (Double)stbl.getValueAt(i, 3);
                 
                 
                 
                 //insert for salesProduct
                 pst2.setInt(1, lastid);
                 pst2.setString(2, ppname);
                 pst2.setString(3, priice);
                 pst2.setString(4, qqty);
                 pst2.setDouble(5, tot);
                 
                 pst2.executeUpdate();
                 
                 
                 //insert for reporttbl1
                
                 pst4.setString(1, ppname);
                 pst4.setString(2, priice);
                 pst4.setString(3, qqty);
                 pst4.setDouble(4, tot);
                 
                 pst4.executeUpdate();
                                  
             }
             
             JOptionPane.showMessageDialog(this, "Sales completed");
             
             
             HashMap a = new HashMap();
             a.put("invo", lastid);
            try {
                JasperDesign jD = JRXmlLoader.load("C:\\Users\\Dell\\Desktop\\java work\\Projects\\G&G Supermarket - Copy\\src\\cc\\spk.jrxml");
                JasperReport jre = JasperCompileManager.compileReport(jD);
                
                JasperPrint jprint = JasperFillManager.fillReport(jre, a, conn);
                
                
                
                
                
                
                
                JasperViewer.viewReport(jprint);
            } catch (JRException ex) {
                Logger.getLogger(salePro.class.getName()).log(Level.SEVERE, null, ex);
            }
             
            
        } catch (SQLException ex) {
            Logger.getLogger(salePro.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
    
    
    public void stockMana(){
        newqua = avqua - qua;
        
        String bac = code.getText();
       
        
        connection();
        
        try {
            pst = conn.prepareStatement("Update producttt set total="+newqua+" where barcode=?");
           // pst.setString(1, nquantity);
            pst.setString(1, bac);
            pst.executeUpdate();
            
            
            
         
            
        } catch (SQLException ex) {
            Logger.getLogger(stockTaking.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    public void ptble(){
       
        try {
            pst = conn.prepareStatement("Select * from producttt");
            rs = pst.executeQuery();
            ResultSetMetaData Rsm = rs.getMetaData();
            
            int c;
            c = Rsm.getColumnCount();
            
            DefaultTableModel df = (DefaultTableModel)protBL.getModel();
            df.setRowCount(0);
            
            while(rs.next()){
                
                Vector v2 = new Vector();
                
                for(int i = 1; i <= c; i++)
                {
                v2.add(rs.getString("pID"));
                v2.add(rs.getString("name"));
                v2.add(rs.getString("dec"));
                v2.add(rs.getString("total"));
                
                }
                
                df.addRow(v2);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(createProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
             String bc = code.getText();

    }
    
   
  /*  public void stble(){
        try {
            pst = conn.prepareStatement("Select * from productt where barcode=?");
            pst.setString(1, bc);
            rs = pst.executeQuery();
            ResultSetMetaData Rsm = rs.getMetaData();
            
            int c;
            c = Rsm.getColumnCount();
            
            DefaultTableModel df = (DefaultTableModel)protBL.getModel();
            df.setRowCount(0);
            
            while(rs.next()){
                
                Vector v2 = new Vector();
                
                for(int i = 1; i <= c; i++)
                {
                v2.add(rs.getString("pID"));
                v2.add(rs.getString("name"));
                v2.add(rs.getString("dec"));
                v2.add(rs.getString("total"));
                
                }
                
                df.addRow(v2);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(createProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
    */
    

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        dwk = new javax.swing.JLabel();
        cdate = new javax.swing.JLabel();
        tclock = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        code = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        qt = new javax.swing.JTextField();
        ppp = new javax.swing.JTextField();
        txttt = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cashh = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        bal = new javax.swing.JTextField();
        grand = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        addd = new javax.swing.JButton();
        btt1 = new javax.swing.JButton();
        btt2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        stbl = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        protBL = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 204));
        jPanel1.setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cc/ep supermarket sm copy.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(-20, 30, 260, 160);

        jButton4.setBackground(new java.awt.Color(102, 102, 102));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("BACK");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4);
        jButton4.setBounds(80, 613, 110, 50);

        jPanel8.setBackground(new java.awt.Color(0, 0, 0));

        dwk.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        dwk.setForeground(new java.awt.Color(255, 255, 255));
        dwk.setText("Curent date");

        cdate.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cdate.setForeground(new java.awt.Color(255, 255, 255));
        cdate.setText("Curent date");

        tclock.setBackground(new java.awt.Color(0, 0, 0));
        tclock.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        tclock.setForeground(new java.awt.Color(0, 153, 0));
        tclock.setText("Curent time");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cdate)
                            .addComponent(tclock, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(dwk)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tclock)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dwk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cdate)
                .addGap(65, 65, 65))
        );

        jPanel1.add(jPanel8);
        jPanel8.setBounds(0, 310, 270, 110);

        jPanel2.setBackground(new java.awt.Color(244, 130, 38));
        jPanel2.setPreferredSize(new java.awt.Dimension(1099, 689));

        jPanel3.setBackground(new java.awt.Color(0, 0, 204));

        jPanel4.setBackground(new java.awt.Color(0, 153, 0));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 0, 153));
        jLabel5.setText("Total");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 0, 153));
        jLabel6.setText("Unit Price");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Quantity");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("BarCode");

        code.setBackground(new java.awt.Color(0, 102, 153));
        code.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        code.setForeground(new java.awt.Color(255, 255, 255));
        code.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codeKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                codeKeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Name");

        txtname.setBackground(new java.awt.Color(0, 102, 153));
        txtname.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtname.setForeground(new java.awt.Color(255, 255, 255));
        txtname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnameKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnameKeyTyped(evt);
            }
        });

        qt.setBackground(new java.awt.Color(0, 102, 153));
        qt.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        qt.setForeground(new java.awt.Color(255, 255, 255));
        qt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                qtKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                qtKeyTyped(evt);
            }
        });

        ppp.setBackground(new java.awt.Color(0, 102, 153));
        ppp.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        ppp.setForeground(new java.awt.Color(255, 255, 255));
        ppp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pppKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pppKeyTyped(evt);
            }
        });

        txttt.setBackground(new java.awt.Color(0, 102, 153));
        txttt.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txttt.setForeground(new java.awt.Color(255, 255, 255));
        txttt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtttKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtttKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel14))
                                .addGap(23, 23, 23)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtname)
                                    .addComponent(code)
                                    .addComponent(qt, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txttt, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ppp, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 22, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(qt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(ppp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txttt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        jPanel5.setBackground(new java.awt.Color(244, 130, 38));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 0, 153));
        jLabel2.setText("Cash");

        cashh.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 0, 153));
        jLabel10.setText("Change");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(153, 0, 153));
        jLabel11.setText("TOTAL");

        bal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        bal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                balActionPerformed(evt);
            }
        });

        grand.setBackground(new java.awt.Color(0, 153, 0));
        grand.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        grand.setForeground(new java.awt.Color(255, 255, 255));

        jButton3.setBackground(new java.awt.Color(204, 0, 0));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("PRINT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel2)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(grand, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cashh, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                                .addComponent(bal))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cashh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(bal, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(grand, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 130, 38), 3));

        addd.setBackground(new java.awt.Color(0, 204, 0));
        addd.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        addd.setForeground(new java.awt.Color(255, 255, 255));
        addd.setText("ADD");
        addd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adddActionPerformed(evt);
            }
        });
        addd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                adddKeyPressed(evt);
            }
        });

        btt1.setBackground(new java.awt.Color(153, 0, 153));
        btt1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        btt1.setForeground(new java.awt.Color(255, 255, 255));
        btt1.setText("REMOVE");

        btt2.setBackground(new java.awt.Color(153, 0, 153));
        btt2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        btt2.setForeground(new java.awt.Color(255, 255, 255));
        btt2.setText("REFRESH");
        btt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btt2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btt1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btt2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btt1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btt2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addd, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("SELLING POINT");

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator2.setPreferredSize(new java.awt.Dimension(50, 30));

        jPanel7.setBackground(new java.awt.Color(0, 0, 204));

        stbl.setBackground(new java.awt.Color(204, 0, 0));
        stbl.setFont(new java.awt.Font("Century", 0, 16)); // NOI18N
        stbl.setForeground(new java.awt.Color(255, 255, 255));
        stbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Name", "Price", "Quantity", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(stbl);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("SALES TABLE");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        protBL.setBackground(new java.awt.Color(0, 153, 102));
        protBL.setFont(new java.awt.Font("Century", 0, 16)); // NOI18N
        protBL.setForeground(new java.awt.Color(255, 255, 255));
        protBL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Product Name", "Description", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        protBL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                protBLMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                protBLMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(protBL);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("PRODUCT TABLE");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(259, 259, 259)
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(74, 74, 74)
                                                .addComponent(jLabel15)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(42, 42, 42)))
                                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 35, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1083, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       
        new clogin().setVisible(true);
        this.dispose();
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void codeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codeKeyTyped
       
        
        
        
        
    }//GEN-LAST:event_codeKeyTyped

   
    Double chan,avqua,newqua,balla,tot,qua,rice,gt=0.0;
   
    
    private void codeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codeKeyPressed
       String bac = code.getText();
      
       connection();
     
       if(evt.getKeyCode() == KeyEvent.VK_ENTER){
           
        
           
          /////  SELECTING TO TABLE 
           try {
            pst = conn.prepareStatement("Select * from producttt where barcode=?");
            pst.setString(1, bac);
            rs = pst.executeQuery();
            
           
            ResultSetMetaData Rsm = rs.getMetaData();
            int c;
            c = Rsm.getColumnCount();
            
            DefaultTableModel df = (DefaultTableModel)protBL.getModel();
            df.setRowCount(0);
            
            while(rs.next()){
                
                Vector v2 = new Vector();
                
                for(int i = 1; i <= c; i++)
                {
                v2.add(rs.getString("pID"));
                v2.add(rs.getString("name"));
                v2.add(rs.getString("dec"));
                v2.add(rs.getString("total"));
                txtname.setText(rs.getString("name"));
                ppp.setText(rs.getString("price"));
                rice = Double.parseDouble(ppp.getText().toString());
                avqua = Double.valueOf( rs.getString("total"));
                }
                
                df.addRow(v2);
                
            }
            String total = JOptionPane.showInputDialog(this, "Enter Product Quantity..");
        qt.setText(total);
        
      qua = Double.parseDouble(total);
      tot = qua*rice;
      txttt.setText("sh."+tot);
      gt +=tot;
            
        } catch (SQLException ex) {
            Logger.getLogger(createProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
           
           
       }
       
       
        
        
        
        
    }//GEN-LAST:event_codeKeyPressed

    private void btt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btt2ActionPerformed
        
        code.setText("");
        qt.setText("");
        code.requestFocus();
        txtname.setText("");
        ppp.setText("");
        ptble();
        txttt.setText("sh. ");
        
    }//GEN-LAST:event_btt2ActionPerformed

    private void txtnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnameKeyPressed

    private void txtnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnameKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnameKeyTyped

    private void adddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adddActionPerformed
        
        
        
        dtf = (DefaultTableModel)stbl.getModel();
        dtf.addRow(new Object[]{
        
        txtname.getText(),
        ppp.getText(),
        qt.getText(),
        tot
        
        
        
        
        
        
        });
        
        
        
        
        
        
        stockMana();
        
        
        grand.setText("shs. "+gt);
         
         code.setText("");
        qt.setText("");
        code.requestFocus();
        txtname.setText("");
        ppp.setText("");
        ptble();
        txttt.setText("sh. ");
    }//GEN-LAST:event_adddActionPerformed

    private void qtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_qtKeyPressed

    private void qtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_qtKeyTyped

    private void pppKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pppKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pppKeyPressed

    private void pppKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pppKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_pppKeyTyped

    private void txtttKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtttKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtttKeyPressed

    private void txtttKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtttKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtttKeyTyped

    private void adddKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adddKeyPressed
  
        
    }//GEN-LAST:event_adddKeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
     
        if(cashh.getText().isEmpty() || Integer.parseInt(cashh.getText()) < gt){
            JOptionPane.showMessageDialog(this, "CASH ERROR!!!");
            cashh.setText("");
            cashh.requestFocus();
        }
        else{
            
       
        
        String cc = cashh.getText();
        chan = Double.parseDouble(cc);
      balla = chan - gt;
      bal.setText("shs."+balla);
     // reprtTotal();
      sales();
      
      
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void protBLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_protBLMouseClicked
       
        try {
            DefaultTableModel d = (DefaultTableModel)protBL.getModel();
            int selectedIndex = protBL.getSelectedRow();
            String ppt = d.getValueAt(selectedIndex, 1).toString();
            String dsc = d.getValueAt(selectedIndex, 2).toString();
            txtname.setText(d.getValueAt(selectedIndex, 1).toString());
            
           
            pst = conn.prepareStatement("Select * from producttt where name = ? and dec = ? ");
            pst.setString(1, ppt);
             pst.setString(2, dsc);
            rs  = pst.executeQuery();
            rss = pst.executeQuery();
            
          /*  while(rss.next()){
              // String xc = rs.getString("dec");
            }
            if(rss.next()){
                JOptionPane.showMessageDialog(this, "Product is OUT OF STOCK!!!");
            }
            */
            while(rs.next()){
                /*
                Double xc = Double.parseDouble(rs.getString("total"));
                if(xc == 0){
                     JOptionPane.showMessageDialog(this, "Product is OUT OF STOCK!!!");
                }*/
                
               txtname.setText(rs.getString("name"));
                ppp.setText(rs.getString("price"));
                rice = Double.parseDouble(ppp.getText().toString());
                avqua = Double.valueOf( rs.getString("total")); 
                
                
                
            }
            
            
            
            
            
            String total = JOptionPane.showInputDialog(this, "Enter Product Quantity..");
            
            qt.setText(total);
            qua = Double.parseDouble(total);
            tot = qua*rice;
            txttt.setText("sh "+tot);
            gt +=tot;
            
            
            
            
           
        } catch (SQLException ex) {
            Logger.getLogger(salePro.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_protBLMouseClicked

    private void protBLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_protBLMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_protBLMouseEntered

    private void balActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_balActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_balActionPerformed

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(salePro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(salePro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(salePro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(salePro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new salePro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addd;
    private javax.swing.JTextField bal;
    private javax.swing.JButton btt1;
    private javax.swing.JButton btt2;
    private javax.swing.JTextField cashh;
    private javax.swing.JLabel cdate;
    private javax.swing.JTextField code;
    private javax.swing.JLabel dwk;
    private javax.swing.JTextField grand;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField ppp;
    private javax.swing.JTable protBL;
    private javax.swing.JTextField qt;
    private javax.swing.JTable stbl;
    private javax.swing.JLabel tclock;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txttt;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        
        while(true){
            
            Calendar cal = Calendar.getInstance();
            hour = cal.get(Calendar.HOUR_OF_DAY);
            minute = cal.get(Calendar.MINUTE);
            second = cal.get(Calendar.SECOND);
            
            
            SimpleDateFormat sdf12 = new SimpleDateFormat("hh:mm:ss a");
            Date dat = cal.getTime();
            currentTime = sdf12.format(dat);
            tclock.setText(currentTime);
            
            SimpleDateFormat day = new SimpleDateFormat("EEEE");// single capital E for shortform of the day name
            storeDay = day.format(dat);
            dwk.setText(storeDay);
            
            SimpleDateFormat datee = new SimpleDateFormat("MMMMM dd, yyyy");//for full name of month use MMMMM. for digit month use MM
            storeDate = datee.format(dat);
            cdate.setText(storeDate);
            
        }
        
    }
}
