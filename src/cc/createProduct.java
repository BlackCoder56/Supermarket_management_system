
package cc;

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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class createProduct extends javax.swing.JFrame implements Runnable {

    
    public createProduct() {
        setUndecorated(true);
        initComponents();
        
         // start of code for time thread
        
        Thread tume = new Thread(this);
        tume.start();
            
        // End of code for time thread
        
        connection();
        auto.setText("PD-"+autoProId());
        LoadCategory();
        ptble();
        txtcode.requestFocus();
        btt2.setEnabled(false);
        btt3.setEnabled(false);
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
    
    
    private int autoProId(){
            int id = 0;
        
        
        try {
            String s = "Select max(pID) from producttt";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }
            id++;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(createProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
        
    }

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
    
    
    
    
    
    ///////   LOAD CATEGORY ///
    
     public class category{
        
        String id;
        String cname;
        
        public category(String id,String ccname){
            
            this.id = id;
            this.cname = ccname;
            
        }
        
        public String toString(){
         
           return cname; 
        }
        
    }
    
    public void LoadCategory(){
        
        try {
            pst = conn.prepareStatement("Select * from category");
            rs = pst.executeQuery();
            cat.removeAll();
            
            while(rs.next()){
                
                cat.addItem(new category(rs.getString(1),rs.getString(2)));
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(createProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    
    
    public void ptble(){
        
        try {
            pst = conn.prepareStatement("Select * from producttt");
            rs = pst.executeQuery();
            ResultSetMetaData Rsm = rs.getMetaData();
            
            int c;
            c = Rsm.getColumnCount();
            
            DefaultTableModel df = (DefaultTableModel)proTBL.getModel();
            df.setRowCount(0);
            
            while(rs.next()){
                
                Vector v2 = new Vector();
                
                for(int i = 1; i <= c; i++)
                {
                v2.add(rs.getString("pID"));
                v2.add(rs.getString("name"));
                v2.add(rs.getString("dec"));
                v2.add(rs.getString("price"));
                v2.add(rs.getString("cat"));
                
                }
                
                df.addRow(v2);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(createProduct.class.getName()).log(Level.SEVERE, null, ex);
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
        proTBL = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        ds = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtd = new javax.swing.JTextField();
        auto = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtname = new javax.swing.JTextField();
        btt1 = new javax.swing.JButton();
        btt2 = new javax.swing.JButton();
        btt3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cat = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        txtcode = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        ds1 = new javax.swing.JLabel();
        price = new javax.swing.JTextField();
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
        jPanel8.setBounds(0, 310, 270, 120);

        jPanel2.setBackground(new java.awt.Color(244, 130, 38));
        jPanel2.setPreferredSize(new java.awt.Dimension(1099, 689));

        proTBL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Product Name", "Description", "Price", "Category"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        proTBL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                proTBLMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(proTBL);

        jPanel3.setBackground(new java.awt.Color(0, 0, 204));

        ds.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        ds.setForeground(new java.awt.Color(255, 255, 255));
        ds.setText("Description");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Product ID");

        txtd.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        auto.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        auto.setForeground(new java.awt.Color(255, 255, 0));
        auto.setText(" ID");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Product Name");

        txtname.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        btt1.setBackground(new java.awt.Color(0, 204, 0));
        btt1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        btt1.setForeground(new java.awt.Color(255, 255, 255));
        btt1.setText("CREATE");
        btt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btt1ActionPerformed(evt);
            }
        });

        btt2.setBackground(new java.awt.Color(153, 0, 153));
        btt2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        btt2.setForeground(new java.awt.Color(255, 255, 255));
        btt2.setText("UPDATE");
        btt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btt2ActionPerformed(evt);
            }
        });

        btt3.setBackground(new java.awt.Color(204, 0, 0));
        btt3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        btt3.setForeground(new java.awt.Color(255, 255, 255));
        btt3.setText("DELETE");
        btt3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btt3ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Category");

        cat.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Product BarCode");

        txtcode.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        jButton6.setBackground(new java.awt.Color(204, 0, 0));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Refresh");

        jButton7.setBackground(new java.awt.Color(204, 0, 0));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("FILTER");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        ds1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        ds1.setForeground(new java.awt.Color(255, 255, 255));
        ds1.setText("Product price");

        price.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(ds)
                            .addComponent(ds1))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(price)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(auto)
                                    .addComponent(cat, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 123, Short.MAX_VALUE))
                            .addComponent(txtd)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txtcode, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(48, 48, 48)
                        .addComponent(txtname)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btt1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btt2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btt3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(114, 114, 114))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addGap(179, 179, 179))))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap(545, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(306, 306, 306)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btt1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btt2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btt3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(auto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ds)
                                    .addComponent(txtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ds1)
                                    .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(cat, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap(264, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(12, 12, 12)))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("CREATE PRODUCT");

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator2.setPreferredSize(new java.awt.Dimension(50, 30));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1025, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 11, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(259, 259, 259)
                                .addComponent(jLabel3)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btt1ActionPerformed
       
        
        String bc = txtcode.getText();
        String pname = txtname.getText();
        String dsc = txtd.getText();
        String pr = price.getText();
        category caat = (category) cat.getSelectedItem();
        
        connection();
        
        
        try {
            pst = conn.prepareStatement("insert into producttt(barcode,name,dec,price,cat) values(?,?,?,?,?)");
            pst.setString(1, bc);
            pst.setString(2, pname);
            pst.setString(3, dsc);
            pst.setString(4, pr);
            pst.setString(5, caat.cname);
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Poduct created Successfully!!!");
            
            
            txtcode.setText("");
            txtname.setText("");
            txtd.setText("");
            price.setText("");
            txtcode.requestFocus();
            
            ptble();
            auto.setText("PD-"+autoProId());
            //LoadCategory();
            
        } catch (SQLException ex) {
            Logger.getLogger(createProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
    }//GEN-LAST:event_btt1ActionPerformed

    private void proTBLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_proTBLMouseClicked
       
        
         DefaultTableModel d1 = (DefaultTableModel)proTBL.getModel();
        
        int SelectIndex = proTBL.getSelectedRow();
        
        
        auto.setText(d1.getValueAt(SelectIndex, 0).toString());
        
        txtname.setText(d1.getValueAt(SelectIndex, 1).toString());
        txtd.setText(d1.getValueAt(SelectIndex, 2).toString());
        price.setText(d1.getValueAt(SelectIndex, 3).toString());
       
        btt1.setEnabled(false);
        btt2.setEnabled(true);
        btt3.setEnabled(true);
        
        
    }//GEN-LAST:event_proTBLMouseClicked

    private void priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        new dashboard().setVisible(true);
        this.dispose();
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btt2ActionPerformed
       String id = auto.getText();
          String bc = txtcode.getText();
        String pname = txtname.getText();
        String dsc = txtd.getText();
        String pr = price.getText();
        category caat = (category) cat.getSelectedItem();
        
        connection();
        
        
        try {
            pst = conn.prepareStatement("Update  producttt set name = ?,dec = ?,price = ?,cat = ?  where pID = ?");
           // 
            pst.setString(1, pname);
            pst.setString(2, dsc);
            pst.setString(3, pr);
            pst.setString(4, caat.cname);
            pst.setString(5, id);
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Poduct Updated Successfully!!!");
            
            
            txtcode.setText("");
            txtname.setText("");
            txtd.setText("");
            price.setText("");
            txtcode.requestFocus();
            
            btt1.setEnabled(true);
            btt2.setEnabled(false);
            btt3.setEnabled(false);
            
            
            ptble();
            auto.setText("PD-"+autoProId());
            
            
        } catch (SQLException ex) {
            Logger.getLogger(createProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
    }//GEN-LAST:event_btt2ActionPerformed

    private void btt3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btt3ActionPerformed
       
        
        
         String id = auto.getText();
          String bc = txtcode.getText();
        String pname = txtname.getText();
        String dsc = txtd.getText();
        String pr = price.getText();
        category caat = (category) cat.getSelectedItem();
        
        connection();
        
        
        try {
            pst = conn.prepareStatement("Delete from producttt where pID = ?");
            pst.setString(1, id);
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Poduct Deleted Successfully!!!");
            
            
            txtcode.setText("");
            txtname.setText("");
            txtd.setText("");
            price.setText("");
            txtcode.requestFocus();
            
            ptble();
            auto.setText("PD-"+autoProId());
            //LoadCategory();
            btt1.setEnabled(true);
            btt2.setEnabled(false);
            btt3.setEnabled(false);
            
        } catch (SQLException ex) {
            Logger.getLogger(createProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }//GEN-LAST:event_btt3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        
        
        
        
    }//GEN-LAST:event_jButton7ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(createProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(createProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(createProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(createProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new createProduct().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel auto;
    private javax.swing.JButton btt1;
    private javax.swing.JButton btt2;
    private javax.swing.JButton btt3;
    private javax.swing.JComboBox cat;
    private javax.swing.JLabel cdate;
    private javax.swing.JLabel ds;
    private javax.swing.JLabel ds1;
    private javax.swing.JLabel dwk;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField price;
    private javax.swing.JTable proTBL;
    private javax.swing.JLabel tclock;
    private javax.swing.JTextField txtcode;
    private javax.swing.JTextField txtd;
    private javax.swing.JTextField txtname;
    // End of variables declaration//GEN-END:variables
}
