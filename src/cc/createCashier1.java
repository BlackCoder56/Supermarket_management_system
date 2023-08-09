
package cc;

import java.awt.Image;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class createCashier1 extends javax.swing.JFrame implements Runnable {

    
    public createCashier1() {
        setUndecorated(true);
        initComponents();
        // start of code for time thread
        
        Thread tume = new Thread(this);
        tume.start();
            
        // End of code for time thread
        
        connection();
        readTable();
        img.setEnabled(false);
        bbt1.setEnabled(true);
        bbt2.setEnabled(false);
        bbt3.setEnabled(false);
        
        oot.setText(""+autoId());
    }

     // time strings
     int hour,second,minute;
    String currentTime;
    String storeDay;
    String storeDate;
    //end of time strings
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    
   public void connection(){
       
         try {
            conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-Q9EIF80\\SQLEXPRESS;databaseName=epsmk;integratedSecurity=true;encrypt=false;");
        } catch (SQLException ex) {
            Logger.getLogger(createProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
       
   }
    
     private int autoId(){
            int id = 0;
        
        
        try {
            String s = "Select max(cid) from cashierTBL";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }
            id++;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(createCashier1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
        
    }
    
     
     
      public void readTable(){
        
        try {
            pst = conn.prepareStatement("Select * from cashierTBL");
            rs = pst.executeQuery();
            ResultSetMetaData Rsm = rs.getMetaData();
            
            int c;
            c = Rsm.getColumnCount();
            
            DefaultTableModel df = (DefaultTableModel)manaT.getModel();
            df.setRowCount(0);
            
            while(rs.next()){
                
                Vector v2 = new Vector();
                
                for(int i = 1; i <= c; i++)
                {
                v2.add(rs.getString("cid"));
                v2.add(rs.getString("Name"));
                v2.add(rs.getString("uname"));
                v2.add(rs.getString("gender"));
                v2.add(rs.getString("cont"));
                v2.add(rs.getString("addres"));
                 
                
                }
                
                df.addRow(v2);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(createCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
     
     
     
     
     
    
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
        jScrollPane1 = new javax.swing.JScrollPane();
        manaT = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt2 = new javax.swing.JTextField();
        txt4 = new javax.swing.JTextField();
        oot = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt5 = new javax.swing.JTextField();
        txt3 = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        bbt1 = new javax.swing.JButton();
        bbt2 = new javax.swing.JButton();
        bbt3 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        rimage = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        img = new javax.swing.JTextField();
        oot1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();

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
        jButton4.setBounds(80, 613, 87, 50);

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

        manaT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cashier ID", "Cashier Name", "Cashier Username", "Gender", "Contact", "Address"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        manaT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                manaTMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(manaT);

        jPanel3.setBackground(new java.awt.Color(0, 0, 204));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Gender");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Cashier ID");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Username");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Contact");

        txt2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        txt4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        oot.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        oot.setForeground(new java.awt.Color(255, 255, 0));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Cashier Name");

        txt1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Address");

        txt5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        txt3.setFont(new java.awt.Font("Tahoma", 1, 26)); // NOI18N
        txt3.setForeground(new java.awt.Color(0, 0, 204));
        txt3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MALE", "FEMALE" }));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 130, 38), 3));

        bbt1.setBackground(new java.awt.Color(0, 204, 0));
        bbt1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        bbt1.setForeground(new java.awt.Color(255, 255, 255));
        bbt1.setText("CREATE");
        bbt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbt1ActionPerformed(evt);
            }
        });

        bbt2.setBackground(new java.awt.Color(153, 0, 153));
        bbt2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        bbt2.setForeground(new java.awt.Color(255, 255, 255));
        bbt2.setText("UPDATE");
        bbt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbt2ActionPerformed(evt);
            }
        });

        bbt3.setBackground(new java.awt.Color(204, 0, 0));
        bbt3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        bbt3.setForeground(new java.awt.Color(255, 255, 255));
        bbt3.setText("DELETE");
        bbt3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbt3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(bbt2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bbt3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bbt1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(bbt1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(bbt2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(bbt3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jPanel5.setBackground(new java.awt.Color(51, 51, 0));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.setLayout(null);

        rimage.setText("jLabel10");
        jPanel5.add(rimage);
        rimage.setBounds(38, 20, 170, 150);

        jButton5.setBackground(new java.awt.Color(204, 204, 0));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton5.setForeground(new java.awt.Color(244, 130, 38));
        jButton5.setText("Add Image");
        jButton5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton5.setBorderPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5);
        jButton5.setBounds(50, 180, 147, 54);

        img.setEditable(false);

        oot1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        oot1.setForeground(new java.awt.Color(255, 255, 0));
        oot1.setText("C");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(7, 7, 7))
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt4, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt5, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel4))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(oot, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(256, 256, 256)
                    .addComponent(oot1)
                    .addContainerGap(625, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(oot, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jLabel4))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(img, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(45, 45, 45)
                    .addComponent(oot1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(292, Short.MAX_VALUE)))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("CREATE CASHIER");

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator2.setPreferredSize(new java.awt.Dimension(50, 30));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(349, 349, 349))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1025, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 11, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1068, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
     
        
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        img.setText(filename);
        Image getAbsolutePath = null;
        ImageIcon icon = new ImageIcon(filename);
        Image image = icon.getImage().getScaledInstance(rimage.getWidth(), rimage.getHeight(),Image.SCALE_SMOOTH);
        rimage.setIcon(icon);
     
    }//GEN-LAST:event_jButton5ActionPerformed

    private void bbt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbt1ActionPerformed
     
        String name = txt1.getText();
        String uname = txt2.getText();
        String gnd = txt3.getSelectedItem().toString();
        String phone = txt4.getText();
        String adds = txt5.getText();
        
        
        
        String imaa = img.getText();
        
        byte[] varBinary = imaa.getBytes(StandardCharsets.UTF_8);
        
      
        connection();
        
        try {
            pst = conn.prepareStatement("insert into cashierTBL(Name,uname,gender,cont,addres,img)values(?,?,?,?,?,?)");
            pst.setString(1, name);
            pst.setString(2, uname);
            pst.setString(3, gnd);
            pst.setString(4, phone);
            pst.setString(5, adds);
            pst.setBytes(6, varBinary);
            pst.executeUpdate();
            
            
            JOptionPane.showMessageDialog(this, "Cashier created Successfully!!!!");
            
            txt1.setText("");
            txt2.setText("");
            txt3.setSelectedIndex(1);
            txt4.setText("");
            txt5.setText("");
            txt1.requestFocus();
            
            oot.setText(""+autoId());
            readTable();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(createCashier1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }//GEN-LAST:event_bbt1ActionPerformed

    private void manaTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manaTMouseClicked
      
        DefaultTableModel d = (DefaultTableModel)manaT.getModel();
        int selectedIndex = manaT.getSelectedRow();
        
        oot.setText(d.getValueAt(selectedIndex, 0).toString());
        txt1.setText(d.getValueAt(selectedIndex, 1).toString());
        txt2.setText(d.getValueAt(selectedIndex, 2).toString());
        txt3.setSelectedItem(d.getValueAt(selectedIndex, 3).toString());
        txt4.setText(d.getValueAt(selectedIndex, 4).toString());
        txt5.setText(d.getValueAt(selectedIndex, 5).toString());
        
        
                
        bbt1.setEnabled(false);
        bbt2.setEnabled(true);
        bbt3.setEnabled(true);
        
    }//GEN-LAST:event_manaTMouseClicked

    private void bbt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbt2ActionPerformed
       
        String id = oot.getText();
        String name = txt1.getText();
        String uname = txt2.getText();
        String gnd = txt3.getSelectedItem().toString();
        String phone = txt4.getText();
        String adds = txt5.getText();
        
        
        
        String imaa = img.getText();
        
        byte[] varBinary = imaa.getBytes(StandardCharsets.UTF_8);
        
      
        connection();
        
        try {
            pst = conn.prepareStatement("Update cashierTBL set Name = ?, uname = ?,gender = ?,cont = ?,addres = ?,img = ? where cid = ?");
            pst.setString(1, name);
            pst.setString(2, uname);
            pst.setString(3, gnd);
            pst.setString(4, phone);
            pst.setString(5, adds);
            pst.setBytes(6, varBinary);
            pst.setString(7, id);
            pst.executeUpdate();
            
            
            JOptionPane.showMessageDialog(this, "Cashier Information updated Successfully!!!!");
            
            txt1.setText("");
            txt2.setText("");
            txt3.setSelectedIndex(1);
            txt4.setText("");
            txt5.setText("");
            txt1.requestFocus();
            
            
            bbt1.setEnabled(true);
            bbt2.setEnabled(false);
            bbt3.setEnabled(false);
            
            
            oot.setText(""+autoId());
            readTable();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(createCashier1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }//GEN-LAST:event_bbt2ActionPerformed

    private void bbt3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbt3ActionPerformed
       
        
        String id = oot.getText();
  
        
        
        
       
      
        connection();
        
        try {
            pst = conn.prepareStatement("Delete from cashierTBL  where cid = ?");
          
            pst.setString(1, id);
            pst.executeUpdate();
            
            
            JOptionPane.showMessageDialog(this, "Cashier Information Deleted Successfully!!!!");
            
            txt1.setText("");
            txt2.setText("");
            txt3.setSelectedIndex(1);
            txt4.setText("");
            txt5.setText("");
            txt1.requestFocus();
            
            oot.setText(""+autoId());
            readTable();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(createCashier1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }//GEN-LAST:event_bbt3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
      new dashboard().setVisible(true);
      this.dispose();
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    
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
            java.util.logging.Logger.getLogger(createCashier1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(createCashier1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(createCashier1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(createCashier1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new createCashier1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bbt1;
    private javax.swing.JButton bbt2;
    private javax.swing.JButton bbt3;
    private javax.swing.JLabel cdate;
    private javax.swing.JLabel dwk;
    private javax.swing.JTextField img;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable manaT;
    private javax.swing.JLabel oot;
    private javax.swing.JLabel oot1;
    private javax.swing.JLabel rimage;
    private javax.swing.JLabel tclock;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt2;
    private javax.swing.JComboBox<String> txt3;
    private javax.swing.JTextField txt4;
    private javax.swing.JTextField txt5;
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
