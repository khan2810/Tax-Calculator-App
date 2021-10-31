/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tax_calculator;

//import com.mysql.cj.xdevapi.Statement;
import java.awt.CardLayout;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Ak875
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
   Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rset=null;
    Integer nettaxable;
    ImageIcon prof = new ImageIcon("C:\\Users\\amaan\\Music\\java codes\\TAX_CALCULATOR\\src\\images\\oko.png");
    ImageIcon logout = new ImageIcon("C:\\Users\\amaan\\Music\\java codes\\TAX_CALCULATOR\\src\\images\\exit.png");
    ImageIcon icon = new ImageIcon("C:\\Users\\amaan\\Music\\java codes\\TAX_CALCULATOR\\src\\images\\icn.png");
    ImageIcon Top = new ImageIcon("C:\\Users\\amaan\\Music\\java codes\\TAX_CALCULATOR\\src\\images\\kalu.png");
    ImageIcon htop = new ImageIcon("C:\\Users\\amaan\\Music\\java codes\\TAX_CALCULATOR\\src\\images\\back.jpg");
    
    ImageIcon hometop = new ImageIcon("C:\\Users\\amaan\\Music\\java codes\\TAX_CALCULATOR\\src\\images\\icons8-home-50.png");
    ImageIcon kyttop = new ImageIcon("C:\\Users\\amaan\\Music\\java codes\\TAX_CALCULATOR\\src\\images\\icons8-information-50.png");
    ImageIcon calctop = new ImageIcon("C:\\Users\\amaan\\Music\\java codes\\TAX_CALCULATOR\\src\\images\\icons8-calculator-50.png");
    ImageIcon profiletop = new ImageIcon("C:\\Users\\amaan\\Music\\java codes\\TAX_CALCULATOR\\src\\images\\unauthorized-person (1).png");
    ImageIcon taxtop = new ImageIcon("C:\\Users\\amaan\\Music\\java codes\\TAX_CALCULATOR\\src\\images\\icons8-rupee-40.png");
    
    JTextField txtemail;
    public Home() {
        initComponents();
       /* ramu(icon,Logo);
        ramu(prof,Profile);
        ramu(logout,jLabel14);
        ramu(htop,welcomelabel);
        
        ramu(prof,pprofile);  
        homebut.setIcon(hometop);
        calcbut.setIcon(calctop);
        kytbut.setIcon(kyttop);
        profbut.setIcon(profiletop);
        stax.setIcon(taxtop);
        data();*/
    }

    public Home(JTextField txtuser) {
        
        initComponents();
        ramu(icon,Logo);
        ramu(prof,Profile);
        ramu(logout,jLabel14);
        ramu(htop,welcomelabel);
        
        ramu(prof,pprofile);  
        homebut.setIcon(hometop);
        calcbut.setIcon(calctop);
        kytbut.setIcon(kyttop);
        profbut.setIcon(profiletop);
        stax.setIcon(taxtop);
        txtemail=txtuser;
       
        data();
    }

    public void data()
    {
        
         try
         {
             Class.forName("com.mysql.cj.jdbc.Driver");
             Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","Amaan@2810");
        
            //conn=MysqlConnect.ConnectDB();
            String q = "select * from signup";
            try 
            {
              java.sql.Statement stmt=conn.createStatement();
              rset=stmt.executeQuery(q);
              while(rset.next())
              {
                if(rset.getString(3).equals(txtemail.getText()))
                {
                    pname.setText(rset.getString(1));
                    pemail.setText(rset.getString(1));
                    ppan.setText(rset.getString(2));
                    pphone.setText(rset.getString(3));
                    username.setText(rset.getString(1));
                    
                   
                
                }
              }
            }
            catch (Exception e) 
            {
            JOptionPane.showMessageDialog(null,e);
            }
          }
         
         catch (Exception e)
        {
             JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
        
        
    }
    
    

     public void ramu(ImageIcon x, JLabel y)
   {
       Image img= x.getImage();
		Image imgScale = img.getScaledInstance(y.getWidth(),y.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon logoIcon = new ImageIcon(imgScale);
		y.setIcon(logoIcon);
   }

     public void needto()
     {
         Integer sal=Integer.parseInt(salary.getText());
         Integer rent=Integer.parseInt(hra.getText());
         Integer resource=Integer.parseInt(other.getText());
         Integer charity=Integer.parseInt(ngo.getText());
         Integer investment=Integer.parseInt(invest.getText());
         if(charity>360000)
             charity=360000;
         if(investment>360000)
             investment=360000;
         nettaxable=sal+rent+resource-charity-investment;
         nettax1.setText(nettaxable.toString());
     }
     
     public void taxcalc()
     {
         String agelimit=age.getSelectedItem().toString();
         Integer netincome=Integer.parseInt(nettax1.getText());
         Integer nettax=0;
         if(agelimit.equals("18-59"))
         {
             if(netincome<=250000)
                 nettax=0;
             else if(netincome>250000 && netincome<=500000)
                 nettax=(int)Math.round(netincome*0.05);
             else if(netincome>500000 && netincome<=1000000)
                 nettax=(int)Math.round(12500+(netincome-500000)*0.2);
             else
                 nettax=(int)Math.round(112500+(netincome-1000000)*0.3);
         }
         if(agelimit.equals("60-79"))
         {
             if(netincome<=300000)
                 nettax=0;
             else if(netincome>300000 && netincome<=500000)
                 nettax=(int)Math.round(netincome*0.05);
             else if(netincome>500000 && netincome<=1000000)
                 nettax=(int)Math.round(10000+(netincome-500000)*0.2);
             else
                 nettax=(int)Math.round(110000+(netincome-1000000)*0.3);
         }
         if(agelimit.equals("80 Above"))
         {
             if(netincome<=500000)
                 nettax=0;
             else if(netincome>500000 && netincome<=1000000)
                 nettax=(int)Math.round(netincome*0.2);
             else
                 nettax=(int)Math.round(100000+(netincome-1000000)*0.3);
         }
         
         stax.setText(nettax.toString()+"-/");
         snet.setText(nettaxable.toString());
         sinvest.setText(invest.getText()+" -/");
         sage.setText((String) age.getSelectedItem());
         sresd.setText((String) res.getSelectedItem());
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        homebut = new javax.swing.JLabel();
        calcbut = new javax.swing.JLabel();
        profbut = new javax.swing.JLabel();
        kytbut = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        username = new javax.swing.JLabel();
        Profile = new javax.swing.JLabel();
        Logo = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        displaypanel = new javax.swing.JPanel();
        Home = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        welcomelabel = new javax.swing.JLabel();
        calculate = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        res = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        age = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        nettax1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        NetIncome = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        hra = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        invest = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        other = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        salary = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        ngo = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        Sucesss = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        incometax = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        stax = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        snet = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        sresd = new javax.swing.JLabel();
        sage = new javax.swing.JLabel();
        sinvest = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        KYT = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        Users = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        pprofile = new javax.swing.JLabel();
        pname = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel25 = new javax.swing.JLabel();
        pphone = new javax.swing.JLabel();
        pemail = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        ppan = new javax.swing.JLabel();
        border = new javax.swing.JPanel();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("INCOME Tax");
        setBackground(new java.awt.Color(0, 0, 0));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 0, 102));
        jPanel1.setPreferredSize(new java.awt.Dimension(250, 510));

        homebut.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        homebut.setForeground(new java.awt.Color(255, 255, 255));
        homebut.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        homebut.setText("Home");
        homebut.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        homebut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homebutMouseClicked(evt);
            }
        });

        calcbut.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        calcbut.setForeground(new java.awt.Color(255, 255, 255));
        calcbut.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        calcbut.setText("Calculate");
        calcbut.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        calcbut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                calcbutMouseClicked(evt);
            }
        });

        profbut.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        profbut.setForeground(new java.awt.Color(255, 255, 255));
        profbut.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        profbut.setText("Profile");
        profbut.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        profbut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profbutMouseClicked(evt);
            }
        });

        kytbut.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        kytbut.setForeground(new java.awt.Color(255, 255, 255));
        kytbut.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        kytbut.setText("KYT");
        kytbut.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        kytbut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kytbutMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(calcbut, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addComponent(kytbut, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addComponent(profbut, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addComponent(homebut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(homebut, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(calcbut, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(kytbut, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(profbut, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(184, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 250, 510));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        username.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        username.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        username.setText("Amaan Khan");

        jLabel14.setText("jLabel14");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(Logo, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 443, Short.MAX_VALUE)
                .addComponent(Profile, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(username, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(Logo, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(Profile, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 140));

        displaypanel.setBackground(new java.awt.Color(102, 255, 0));
        displaypanel.setLayout(new java.awt.CardLayout());

        Home.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setPreferredSize(new java.awt.Dimension(300, 307));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("TAX CALCULATOR");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(204, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("WELCOME");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("To");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(welcomelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(welcomelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout HomeLayout = new javax.swing.GroupLayout(Home);
        Home.setLayout(HomeLayout);
        HomeLayout.setHorizontalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomeLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        HomeLayout.setVerticalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
        );

        displaypanel.add(Home, "card4");

        calculate.setBackground(new java.awt.Color(0, 51, 51));
        calculate.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Assesment Year");
        calculate.add(jLabel1);
        jLabel1.setBounds(100, 120, 140, 30);

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Year", "2018-2019", "2019-2020", "2020-2021", "2021-2022" }));
        calculate.add(jComboBox1);
        jComboBox1.setBounds(320, 110, 180, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tax Payer");
        calculate.add(jLabel4);
        jLabel4.setBounds(100, 160, 110, 20);

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Individual", "HUF", "Domestic Company", "Foreign Company", "Firms", "LLP" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        calculate.add(jComboBox2);
        jComboBox2.setBounds(320, 150, 180, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 0));
        jLabel8.setText("Calculate your Income Tax ");
        calculate.add(jLabel8);
        jLabel8.setBounds(120, 10, 450, 60);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Residential status");
        calculate.add(jLabel6);
        jLabel6.setBounds(100, 190, 160, 30);

        res.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        res.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Resident", "NRI" }));
        res.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resActionPerformed(evt);
            }
        });
        calculate.add(res);
        res.setBounds(320, 190, 180, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Age -group");
        calculate.add(jLabel5);
        jLabel5.setBounds(100, 240, 110, 20);

        age.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        age.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "18-59", "60-79", "80 Above" }));
        age.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ageActionPerformed(evt);
            }
        });
        calculate.add(age);
        age.setBounds(320, 230, 180, 30);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("NetTaxable Income");
        calculate.add(jLabel9);
        jLabel9.setBounds(100, 270, 170, 30);

        nettax1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nettax1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        calculate.add(nettax1);
        nettax1.setBounds(320, 270, 180, 30);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 51));
        jLabel11.setText("Need to calculate ?");
        jLabel11.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        calculate.add(jLabel11);
        jLabel11.setBounds(470, 310, 160, 30);

        jButton1.setBackground(new java.awt.Color(51, 255, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("Procced ");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        calculate.add(jButton1);
        jButton1.setBounds(170, 350, 230, 60);

        displaypanel.add(calculate, "card4");

        NetIncome.setBackground(new java.awt.Color(153, 255, 204));
        NetIncome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel15.setText("Net Income Calculate");
        NetIncome.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 270, 40));

        jLabel16.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel16.setText("Income from Salary (Annual)");
        NetIncome.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 220, 20));

        hra.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        NetIncome.add(hra, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 160, 187, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel17.setText("House Rent Allowance(HRA)");
        NetIncome.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 229, 26));

        invest.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        NetIncome.add(invest, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 310, 187, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel18.setText("Income from other sources");
        NetIncome.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 200, 34));

        other.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        other.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otherActionPerformed(evt);
            }
        });
        NetIncome.add(other, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 210, 187, 30));

        jLabel19.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel19.setText("Total Investment");
        NetIncome.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, 135, 34));

        salary.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        NetIncome.add(salary, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, 187, 30));

        jButton2.setBackground(new java.awt.Color(204, 204, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("SUBMIT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        NetIncome.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 380, 210, 50));

        jLabel20.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel20.setText("Donation to charity/NGO");
        NetIncome.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 190, 34));

        ngo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        NetIncome.add(ngo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 260, 187, 30));

        jSeparator1.setForeground(new java.awt.Color(204, 0, 0));
        NetIncome.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 230, 30));

        displaypanel.add(NetIncome, "card3");

        Sucesss.setBackground(new java.awt.Color(0, 255, 204));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        incometax.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        incometax.setForeground(new java.awt.Color(255, 0, 51));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("TOTAL  PAYABLE TAX");

        stax.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        stax.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stax.setText("12000 -/");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("net taxable income");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Residentail status");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Total investment");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Age-group");

        snet.setBackground(new java.awt.Color(51, 255, 51));
        snet.setText("15000-/");

        sresd.setText("200 -/");

        sage.setText("500 -/");

        sinvest.setText("500 -/");

        jButton3.setBackground(new java.awt.Color(0, 255, 0));
        jButton3.setText("Recalculate");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(261, 261, 261)
                                .addComponent(stax, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(229, 229, 229)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(229, 229, 229)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(599, 599, 599)
                        .addComponent(incometax, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sage, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(snet, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sinvest, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(774, 774, 774))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(sresd, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(509, 509, 509)))))))
                .addContainerGap())
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(259, 259, 259)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(133, 133, 133)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(555, Short.MAX_VALUE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stax, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(incometax, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(snet, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sresd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(sage, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sinvest, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jButton3)
                .addGap(33, 33, 33))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                    .addContainerGap(279, Short.MAX_VALUE)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(119, 119, 119)))
        );

        javax.swing.GroupLayout SucesssLayout = new javax.swing.GroupLayout(Sucesss);
        Sucesss.setLayout(SucesssLayout);
        SucesssLayout.setHorizontalGroup(
            SucesssLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SucesssLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        SucesssLayout.setVerticalGroup(
            SucesssLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SucesssLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        displaypanel.add(Sucesss, "card7");

        KYT.setBackground(new java.awt.Color(204, 255, 255));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));

        jLabel7.setBackground(new java.awt.Color(0, 153, 153));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("KNOW YOUR TAX");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jEditorPane1.setEditable(false);
        jEditorPane1.setBackground(new java.awt.Color(102, 255, 255));
        jEditorPane1.setBorder(null);
        jEditorPane1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jEditorPane1.setText("\n1) How is income tax calculated?\nIncome tax is calculated on the basis of tax slab. Your taxable income is worked out after making relevant deductions, other taxes that you may have already paid (Advance Tax) and tax deducted at source (TDS), the resultant taxable income will be taxed at the slab rate that is applicable.\n\n->CITIZENS OF AGE BETWEEN 18-59\nIncome                                                    Tax Rate\nUpto Rs. 2,50,000             \tNil.\nRs. 2,50,001 to Rs. 5,00,000\t5%\nRs. 5,00,001 to Rs. 10,00,000\tRs. 12,500 + 20% of Income exceeding Rs. 500,000.\nAbove Rs. 10,00,000         \tRs. 1,12,500 + 30% of Income exceeding of Rs 10,00,000.\n\n->SENIOR CITIZENS(AGE BETWEEN 60-79)\nIncome                               \tTax Rate\nUpto Rs.3,00,000              \tNil.\nRs. 3,00,001 to Rs. 5,00,000\t5%\nRs. 5,00,001 to Rs. 10,00,000\tRs.10,000 + 20% of Income exceeding Rs. 500,000.\nAbove Rs. 10,00,000         \tRs. 1,10,000 + 30% of Income exceeding of Rs 10,00,000.\n\n->VERY SENIOR CITIZENS(ABOVE 80)\t\nIncome                                                          Tax Rate\nUpto Rs. 5,00,000                                          Nil.\nRs. 5,00,001 to Rs. 10,00,000                       20%\nAbove Rs. 10,00,000                                    Rs. 1,00,000 + 30% of Income exceeding of Rs 10,00,000.\n\n2)What is the maximum exemption limit on Donating for charity/NGO or investing in different investment policies?\nThe maximum exemption limit for both the two is Rs. 3,60000.\n\n3) How much tax should I pay on my salary?\nThe income tax on your salary will be calculated depending on the tax slab. The taxable income will be worked out after making applicable deductions, if any. If you invest in life insurance, you can claim deduction from taxable income of life insurance premium paid upto Rs. 1.5 lakhs. Section 80C also offers deduction from taxable income for investments in PPF (Public Provident Fund), NSC (National Savings Certificate) and other instruments along with home loan principal repayment. Additionally, if you invest in health insurance, you can get deductions up to Rs. 25,000 under Section 80D for yourself and your family and up to Rs. 25,000 (Rs. 50,000 if age of insured is 60 or above) for your parents. You can also get deduction of home loan interest up to Rs. 2 lakh under Section 24. These are ways you can consider to lower your overall tax outgo.\n\n4) Which income is not taxable in India?\nIncomes mentioned under section 10 of The Income Tax act 1961 are not taxable in India\n\n5) What is the maximum non-taxable income limit?\nIncome up to Rs. 2.5 lakh does not attract any taxes. Further, u/s 87A person gets full tax rebate if income of a person is less than Rs. 5 Lacs\n\n6) Does everyone have to file their income tax returns?\nIf your income is below the taxable threshold of Rs. 2.5 lakhs currently, it is not compulsory to file your income tax return. However, if you have a PAN (Permanent Account Number) card and an income that falls below the taxable threshold, experts advise the filing of your ITR with a NIL return. This is to show the IT department that you did not have any income that was taxable for a specific year and hence, did not pay your taxes for the same. This will help you immensely in the future. Also, if you are an Indian resident with investments/assets outside India, you have to file returns even if your overall income falls below the taxable threshold. You will have to file your tax returns if you are eligible to claim refunds on any taxes that you may have paid in advance.");
        jScrollPane1.setViewportView(jEditorPane1);

        javax.swing.GroupLayout KYTLayout = new javax.swing.GroupLayout(KYT);
        KYT.setLayout(KYTLayout);
        KYTLayout.setHorizontalGroup(
            KYTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        KYTLayout.setVerticalGroup(
            KYTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KYTLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        displaypanel.add(KYT, "card6");

        Users.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));

        pname.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        pname.setForeground(new java.awt.Color(0, 153, 153));
        pname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(pprofile, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(pprofile, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(pname, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Information ");

        jSeparator2.setForeground(new java.awt.Color(255, 0, 0));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Email");

        pphone.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pphone.setForeground(new java.awt.Color(204, 0, 204));
        pphone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pemail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pemail.setForeground(new java.awt.Color(204, 0, 204));
        pemail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Name");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Pan Number");

        ppan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ppan.setForeground(new java.awt.Color(204, 0, 204));
        ppan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout UsersLayout = new javax.swing.GroupLayout(Users);
        Users.setLayout(UsersLayout);
        UsersLayout.setHorizontalGroup(
            UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UsersLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UsersLayout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addGroup(UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(UsersLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(UsersLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addGroup(UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UsersLayout.createSequentialGroup()
                                    .addComponent(pemail, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(75, 75, 75))
                                .addGroup(UsersLayout.createSequentialGroup()
                                    .addComponent(pphone, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap()))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UsersLayout.createSequentialGroup()
                                .addComponent(ppan, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(79, 79, 79))))))
        );
        UsersLayout.setVerticalGroup(
            UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UsersLayout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addGroup(UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UsersLayout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UsersLayout.createSequentialGroup()
                        .addComponent(pemail, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pphone, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ppan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(122, Short.MAX_VALUE))
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        displaypanel.add(Users, "card6");

        getContentPane().add(displaypanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 176, 700, 510));

        border.setBackground(new java.awt.Color(153, 153, 255));

        javax.swing.GroupLayout borderLayout = new javax.swing.GroupLayout(border);
        border.setLayout(borderLayout);
        borderLayout.setHorizontalGroup(
            borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1050, Short.MAX_VALUE)
        );
        borderLayout.setVerticalGroup(
            borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        getContentPane().add(border, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 140, 1050, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        
        displaypanel.removeAll();
        displaypanel.add(NetIncome);
        displaypanel.repaint();
        displaypanel.revalidate();
        
     
        
        
                
    }//GEN-LAST:event_jLabel11MouseClicked

    private void calcbutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calcbutMouseClicked
        // TODO add your handling code here:
        displaypanel.removeAll();
        displaypanel.add(calculate);
        displaypanel.repaint();
        displaypanel.revalidate();
      
       
    }//GEN-LAST:event_calcbutMouseClicked

    private void homebutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homebutMouseClicked
       
        displaypanel.removeAll();
        displaypanel.add(Home);
        displaypanel.repaint();
        displaypanel.revalidate();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_homebutMouseClicked

    private void profbutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profbutMouseClicked
        // TODO add your handling code here:
        displaypanel.removeAll();
        displaypanel.add(Users);
        displaypanel.repaint();
        displaypanel.revalidate();
    }//GEN-LAST:event_profbutMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        displaypanel.removeAll();
        displaypanel.add(calculate);
        displaypanel.repaint();
        displaypanel.revalidate();
        needto();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
        
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel14MouseClicked

    private void kytbutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kytbutMouseClicked
        // TODO add your handling code here:
        displaypanel.removeAll();
        displaypanel.add(KYT);
        displaypanel.repaint();
        displaypanel.revalidate();
        
    }//GEN-LAST:event_kytbutMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        displaypanel.removeAll();
        displaypanel.add(Sucesss);
        displaypanel.repaint();
        displaypanel.revalidate();
        taxcalc();
    }//GEN-LAST:event_jButton1MouseClicked

    private void otherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_otherActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ageActionPerformed

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        displaypanel.removeAll();
        displaypanel.add(calculate);
        displaypanel.repaint();
        displaypanel.revalidate();
        
    }//GEN-LAST:event_jButton3MouseClicked

    private void resActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_resActionPerformed

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Home;
    private javax.swing.JPanel KYT;
    private javax.swing.JLabel Logo;
    private javax.swing.JPanel NetIncome;
    private javax.swing.JLabel Profile;
    private javax.swing.JPanel Sucesss;
    private javax.swing.JPanel Users;
    private javax.swing.JComboBox<String> age;
    private javax.swing.JPanel border;
    private javax.swing.JLabel calcbut;
    private javax.swing.JPanel calculate;
    private javax.swing.JPanel displaypanel;
    private javax.swing.JLabel homebut;
    private javax.swing.JTextField hra;
    private javax.swing.JLabel incometax;
    private javax.swing.JTextField invest;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel kytbut;
    private javax.swing.JTextField nettax1;
    private javax.swing.JTextField ngo;
    private javax.swing.JTextField other;
    private javax.swing.JLabel pemail;
    private javax.swing.JLabel pname;
    private javax.swing.JLabel ppan;
    private javax.swing.JLabel pphone;
    private javax.swing.JLabel pprofile;
    private javax.swing.JLabel profbut;
    private javax.swing.JComboBox<String> res;
    private javax.swing.JLabel sage;
    private javax.swing.JTextField salary;
    private javax.swing.JLabel sinvest;
    private javax.swing.JLabel snet;
    private javax.swing.JLabel sresd;
    private javax.swing.JLabel stax;
    private javax.swing.JLabel username;
    private javax.swing.JLabel welcomelabel;
    // End of variables declaration//GEN-END:variables
}
