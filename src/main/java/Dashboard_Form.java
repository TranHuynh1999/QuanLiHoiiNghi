


import DAO.*;
import POJOs.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TranHuynh
 */
public final class Dashboard_Form extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard_Form
     */
    
    // default border for the menu items
    Border default_border = BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(46,49,49));
        
    // yellow border for the menu items
    Border yellow_border = BorderFactory.createMatteBorder(1, 0, 1, 0, Color.YELLOW);
    
    // create an array of jlabels
    JLabel[] menuLabels = new JLabel[8];
    
    // create an array of jpanels
    JPanel[] panels = new JPanel[8];
    private int vaitro=-1;
    private int keyMember=-1;

    public int getKeyMember() {
        return keyMember;
    }

    public void setKeyMember(int keyMember) {
        this.keyMember = keyMember;
    }
    
    public void setVaitro(int vaitro) {
        this.vaitro = vaitro;
    }

    public int getVaitro() {
        return vaitro;
    }
    public Dashboard_Form() {
        initComponents();
        
        // center this form
        this.setLocationRelativeTo(null);
        
        // set icons
        jLabel_appLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/appLogo.png")));
        jLabel_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/x.png")));
        
        
        // set borders
        // panel logo border
        Border panelBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.lightGray);
        jPanel_logoANDname.setBorder(panelBorder);
        // panel container border
        Border containerBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(46,49,49));
        jPanel_container.setBorder(containerBorder);
        
        // populate the menuLabels array
        // you can use a for loop to do that
        menuLabels[0] = jLabel_menuItemHome;
        menuLabels[1] = jLabel_menuItemListConferece;
        menuLabels[2] = jLabel_menuItemStatistics;
        menuLabels[3] = jLabel_menuItemConfereceMa;
        menuLabels[4] = jLabel_menuItemUserMa;
        menuLabels[5] = jLabel_menuItemProfile;
        menuLabels[6] = jLabel_menuItemSignIn;
        menuLabels[7] = jLabel_menuItemSignout;
        // populate the panels array
        panels[0] = jPanel_Home;
        panels[1] = jPanel_Statistics;
        panels[2] = jPanel_ListConference;
        panels[3] = jPanel_ConManager;
        panels[4] = jPanel_UserManager;
        panels[5] = jPanel_Profile;
        panels[6] = jPanel_SignIn;
        panels[7] = jPanel_SignUp;
        addActionToMenuLabels();
        showMenu();
        showPanel(jPanel_Home);
        showListConference();
        
        //showProfile();
       
    }
    public void showProfile(){
        
      
        Member mb=MemberDao.findInforMember(keyMember);

        if(mb!=null)
         {
                jTextField_ProfileName.setText(mb.getTen());
                jLabel_ProfileUsername.setText(mb.getUserName());
                jTextField_EmailProfile.setText(mb.getEmail());
                jPasswordField_Profile.setText(mb.getPassword());
                }
    }
    public void showListConference()
    {
        DefaultTableModel tb = (DefaultTableModel) jTable1.getModel();
        
        List<Hoinghi> hn = HoiNghiDAO.findAll();
        
        for (int i = 0; i < hn.size(); i++) {
            Hoinghi temp = hn.get(i);
            List<String> list=new ArrayList<>();
            list.add(temp.getTen());
            list.add(temp.getMoTaNgangon()); 
            list.add(String.valueOf(temp.getSoNguoiThamDu()));
            list.add(String.valueOf(temp.getThoiGian()));
            tb.addRow(list.toArray());
            
        }
    }
    public void showListConferenceStatistics()
    {
        DefaultTableModel tbStatistics=(DefaultTableModel) jTable_ConferenceStatistics.getModel();
        Member mb=MemberDao.findInforMember(keyMember);
        Iterator<Hoinghi> hoinghis=mb.getHoinghis().iterator();
        while(hoinghis.hasNext()){
            Hoinghi temp=hoinghis.next();
            List<String> list=new ArrayList<>();
            list.add(temp.getTen());
            list.add(temp.getMoTaNgangon());
            list.add(String.valueOf(temp.getThoiGian()));
            Diadiemtochuc dd=temp.getDiadiemtochuc();
            list.add(String.valueOf(dd.getDiaChi()));
            list.add(String.valueOf(temp.getSoNguoiThamDu()));
            tbStatistics.addRow(list.toArray());
        }
    }

    
    // create a function to set the label background color
    public void setLabelBackround(JLabel label)
    {
        // reset labels to their default design
        for (JLabel menuItem : menuLabels)
        {
           // change the jlabel background color to white
           menuItem.setBackground(new Color(46,49,49));
           // change the jlabel Foreground color to blue
           menuItem.setForeground(Color.white); 
        }
        
        // change the jlabel background color to white
        label.setBackground(Color.white);
        // change the jlabel Foreground color to blue
        label.setForeground(Color.blue);
    }
    
    // create a function to show the selected panel
    public void showPanel(JPanel panel)
    {
        // hide panels
        for (JPanel pnl : panels) 
        {
            pnl.setVisible(false);
        }
        
        // and show only this panel
        panel.setVisible(true);
    }
    
    
    
    public void addActionToMenuLabels()
    {
        // get labels in the jpanel menu
        Component[] components = jPanel_menu.getComponents();
        
        for (Component component : components) {
            if(component instanceof JLabel)
            {
                JLabel label = (JLabel) component;
                
                label.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        
                        // change the jlabel background and Foreground
                        setLabelBackround(label);
                        
                        // displa the selected panel
                        switch (label.getText().trim()){

                                   
                            case "Home":
                                   showPanel(jPanel_Home);
                                   // jPanel_users.setBackground(Color.red);
                                   break;
                             case "List Conference":
                                   showPanel(jPanel_ListConference);
                                   break;
                                   
                            case "Conference statistics":
                                   showPanel(jPanel_Statistics);
                                   
                                   // jPanel_products.setBackground(Color.BLUE);
                                   break;
                                   
                                   case "Conference Manager":
                                   showPanel(jPanel_ConManager);
                                   // jPanel_settings.setBackground(Color.GRAY);
                                   break;
                                   
                                   case "User Manager":
                                   showPanel(jPanel_UserManager);
                                   // jPanel_contact.setBackground(Color.GREEN);
                                   break;
                                   
                                   case "Profile":
                                   showPanel(jPanel_Profile);
                                   // jPanel_calendar.setBackground(Color.yellow);
                                   break;
                                   
                                   case "Sign In":
                                   showPanel(jPanel_SignIn);
                                   // jPanel_test.setBackground(Color.orange);
                                   break;
                                   case "Sign Out":
                                    setVaitro(-1);
                                    showMenu();
                                    showPanel(jPanel_Home);
                                   break;
                                   
                        }
                        
                      }

                    @Override
                    public void mousePressed(MouseEvent e) {
                     }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                      }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        
                        // set the border to yellow
                        label.setBorder(yellow_border);
                        
                      }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        
                        // reset to the default border
                        label.setBorder(default_border);
                        
                      }
                });
                
            }
        }
    }
    
    private void showMenu()
    {
        switch (getVaitro()) {
            case 1:
                //jLabel_menuItemSignIn.setVisible(false);
                jLabel_menuItemHome.setVisible(true);
                jLabel_menuItemListConferece.setVisible(true);
                jLabel_menuItemSignIn.setVisible(false);
                jLabel_menuItemConfereceMa.setVisible(true);
                jLabel_menuItemSignout.setVisible(true);
                jLabel_menuItemProfile.setVisible(true);
                jLabel_menuItemStatistics.setVisible(true);
                jLabel_menuItemUserMa.setVisible(true);
                break;
            case 0:
//                jLabel_menuItemSignIn.setVisible(false);
//                jLabel_menuItemUserMa.setVisible(false);
//                jLabel_menuItemConfereceMa.setVisible(false);
                jLabel_menuItemHome.setVisible(true);
                jLabel_menuItemListConferece.setVisible(true);
                jLabel_menuItemSignIn.setVisible(false);
                jLabel_menuItemConfereceMa.setVisible(false);
                jLabel_menuItemSignout.setVisible(true);
                jLabel_menuItemProfile.setVisible(true);
                jLabel_menuItemStatistics.setVisible(true);
                jLabel_menuItemUserMa.setVisible(false);
                break;
            case -1:
                jLabel_menuItemHome.setVisible(true);
                jLabel_menuItemListConferece.setVisible(true);
                jLabel_menuItemSignIn.setVisible(true);
                jLabel_menuItemConfereceMa.setVisible(false);
                jLabel_menuItemSignout.setVisible(false);
                jLabel_menuItemProfile.setVisible(false);
                jLabel_menuItemStatistics.setVisible(false);
                jLabel_menuItemUserMa.setVisible(false);
                break;
            default:
                break;
        }
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel_container = new javax.swing.JPanel();
        jPanel_menu = new javax.swing.JPanel();
        jPanel_logoANDname = new javax.swing.JPanel();
        jLabel_appLogo = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel_menuItemHome = new javax.swing.JLabel();
        jLabel_menuItemListConferece = new javax.swing.JLabel();
        jLabel_menuItemStatistics = new javax.swing.JLabel();
        jLabel_menuItemConfereceMa = new javax.swing.JLabel();
        jLabel_menuItemUserMa = new javax.swing.JLabel();
        jLabel_menuItemProfile = new javax.swing.JLabel();
        jLabel_menuItemSignIn = new javax.swing.JLabel();
        jLabel_menuItemSignout = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel_close = new javax.swing.JLabel();
        jPanel_Home = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel_ListConference = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jPanel_Statistics = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_ConferenceStatistics = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel_ConManager = new javax.swing.JPanel();
        jPanel_UserManager = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel_Profile = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextField_EmailProfile = new javax.swing.JTextField();
        jTextField_ProfileName = new javax.swing.JTextField();
        jPasswordField_Profile = new javax.swing.JPasswordField();
        jButton_Signin1 = new javax.swing.JButton();
        jLabel_ProfileUsername = new javax.swing.JLabel();
        jPanel_SignIn = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField_Signin = new javax.swing.JTextField();
        jPasswordField_Signin = new javax.swing.JPasswordField();
        jButton_Signin = new javax.swing.JButton();
        jLabel_Signup = new javax.swing.JLabel();
        jPanel_SignUp = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jTextField_UsernameSignup = new javax.swing.JTextField();
        jPassword_SignUp = new javax.swing.JPasswordField();
        jButton_Signup = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jTextField_NameSignup = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextField_EmailSignup = new javax.swing.JTextField();
        jPassword_RetypeSignup = new javax.swing.JPasswordField();
        jLabel_SignIn = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel_container.setBackground(new java.awt.Color(255, 255, 255));

        jPanel_menu.setBackground(new java.awt.Color(46, 49, 49));

        jPanel_logoANDname.setBackground(new java.awt.Color(46, 49, 49));

        jLabel_appLogo.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_appLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_appLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/appLogo.png"))); // NOI18N
        jLabel_appLogo.setOpaque(true);

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 0));
        jLabel8.setText("Conference");

        javax.swing.GroupLayout jPanel_logoANDnameLayout = new javax.swing.GroupLayout(jPanel_logoANDname);
        jPanel_logoANDname.setLayout(jPanel_logoANDnameLayout);
        jPanel_logoANDnameLayout.setHorizontalGroup(
            jPanel_logoANDnameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_logoANDnameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_appLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_logoANDnameLayout.setVerticalGroup(
            jPanel_logoANDnameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_logoANDnameLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel_logoANDnameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel_appLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jLabel_menuItemHome.setBackground(new java.awt.Color(46, 49, 49));
        jLabel_menuItemHome.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel_menuItemHome.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_menuItemHome.setText("Home");
        jLabel_menuItemHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_menuItemHome.setOpaque(true);

        jLabel_menuItemListConferece.setBackground(new java.awt.Color(46, 49, 49));
        jLabel_menuItemListConferece.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel_menuItemListConferece.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_menuItemListConferece.setText("List Conference");
        jLabel_menuItemListConferece.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_menuItemListConferece.setOpaque(true);

        jLabel_menuItemStatistics.setBackground(new java.awt.Color(46, 49, 49));
        jLabel_menuItemStatistics.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel_menuItemStatistics.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_menuItemStatistics.setText("Conference statistics ");
        jLabel_menuItemStatistics.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_menuItemStatistics.setOpaque(true);

        jLabel_menuItemConfereceMa.setBackground(new java.awt.Color(46, 49, 49));
        jLabel_menuItemConfereceMa.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel_menuItemConfereceMa.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_menuItemConfereceMa.setText("Conference Manager ");
        jLabel_menuItemConfereceMa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_menuItemConfereceMa.setOpaque(true);

        jLabel_menuItemUserMa.setBackground(new java.awt.Color(46, 49, 49));
        jLabel_menuItemUserMa.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel_menuItemUserMa.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_menuItemUserMa.setText("User Manager");
        jLabel_menuItemUserMa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_menuItemUserMa.setOpaque(true);

        jLabel_menuItemProfile.setBackground(new java.awt.Color(46, 49, 49));
        jLabel_menuItemProfile.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel_menuItemProfile.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_menuItemProfile.setText("Profile");
        jLabel_menuItemProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_menuItemProfile.setOpaque(true);

        jLabel_menuItemSignIn.setBackground(new java.awt.Color(46, 49, 49));
        jLabel_menuItemSignIn.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel_menuItemSignIn.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_menuItemSignIn.setText("Sign In");
        jLabel_menuItemSignIn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_menuItemSignIn.setOpaque(true);

        jLabel_menuItemSignout.setBackground(new java.awt.Color(0, 0, 0));
        jLabel_menuItemSignout.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel_menuItemSignout.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_menuItemSignout.setText("Sign Out");
        jLabel_menuItemSignout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_menuItemSignout.setOpaque(true);

        javax.swing.GroupLayout jPanel_menuLayout = new javax.swing.GroupLayout(jPanel_menu);
        jPanel_menu.setLayout(jPanel_menuLayout);
        jPanel_menuLayout.setHorizontalGroup(
            jPanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_logoANDname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel_menuItemHome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel_menuItemConfereceMa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel_menuItemListConferece, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel_menuItemStatistics, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel_menuItemUserMa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel_menuItemProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel_menuItemSignIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel_menuItemSignout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel_menuLayout.setVerticalGroup(
            jPanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_menuLayout.createSequentialGroup()
                .addComponent(jPanel_logoANDname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jLabel_menuItemHome, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_menuItemListConferece, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel_menuItemStatistics, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel_menuItemConfereceMa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_menuItemUserMa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_menuItemProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_menuItemSignIn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_menuItemSignout, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(46, 49, 49));

        jLabel_close.setBackground(new java.awt.Color(46, 49, 49));
        jLabel_close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_close.setOpaque(true);
        jLabel_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_closeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 874, Short.MAX_VALUE)
                .addComponent(jLabel_close, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_close, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        jPanel_Home.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Home.setMaximumSize(new java.awt.Dimension(922, 488));
        jPanel_Home.setMinimumSize(new java.awt.Dimension(922, 488));
        jPanel_Home.setPreferredSize(new java.awt.Dimension(922, 488));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("General Introduction");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Dell\\Documents\\NetBeansProjects\\QuanLiHoiiNghi\\target\\classes\\IMAGES\\conference.jpg")); // NOI18N
        jLabel2.setText("jLabel2");

        jPanel10.setBackground(new java.awt.Color(248, 148, 6));

        jLabel3.setBackground(new java.awt.Color(248, 148, 6));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Welcome to the conference management application.");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(248, 148, 6)));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 922, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel_HomeLayout = new javax.swing.GroupLayout(jPanel_Home);
        jPanel_Home.setLayout(jPanel_HomeLayout);
        jPanel_HomeLayout.setHorizontalGroup(
            jPanel_HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel_HomeLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel_HomeLayout.setVerticalGroup(
            jPanel_HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_HomeLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel_HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel_ListConference.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_ListConference.setMaximumSize(new java.awt.Dimension(922, 488));
        jPanel_ListConference.setMinimumSize(new java.awt.Dimension(922, 488));
        jPanel_ListConference.setPreferredSize(new java.awt.Dimension(922, 488));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên", "Mô Tả Ngắn", "Số người tham dự", "Thời gian"
            }
        ));
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(jTable1);

        jLabel5.setBackground(new java.awt.Color(255, 204, 204));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 51));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Conference List");
        jLabel5.setToolTipText("Conference List");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel_ListConferenceLayout = new javax.swing.GroupLayout(jPanel_ListConference);
        jPanel_ListConference.setLayout(jPanel_ListConferenceLayout);
        jPanel_ListConferenceLayout.setHorizontalGroup(
            jPanel_ListConferenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ListConferenceLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );
        jPanel_ListConferenceLayout.setVerticalGroup(
            jPanel_ListConferenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ListConferenceLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel_Statistics.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Statistics.setMaximumSize(new java.awt.Dimension(922, 488));
        jPanel_Statistics.setMinimumSize(new java.awt.Dimension(922, 488));
        jPanel_Statistics.setPreferredSize(new java.awt.Dimension(922, 488));

        jPanel6.setBackground(new java.awt.Color(248, 148, 6));
        jPanel6.setMaximumSize(new java.awt.Dimension(922, 83));
        jPanel6.setMinimumSize(new java.awt.Dimension(922, 83));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Conference Statistics");
        jLabel1.setMaximumSize(new java.awt.Dimension(922, 83));
        jLabel1.setMinimumSize(new java.awt.Dimension(922, 83));
        jLabel1.setPreferredSize(new java.awt.Dimension(922, 83));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel12.setBackground(new java.awt.Color(44, 62, 60));
        jPanel12.setMaximumSize(new java.awt.Dimension(922, 397));
        jPanel12.setMinimumSize(new java.awt.Dimension(922, 397));

        jTable_ConferenceStatistics.setBackground(new java.awt.Color(108, 122, 137));
        jTable_ConferenceStatistics.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTable_ConferenceStatistics.setForeground(new java.awt.Color(228, 241, 254));
        jTable_ConferenceStatistics.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Brief Description", "Time", "Venue Location", "Number of Attendees"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_ConferenceStatistics.setGridColor(new java.awt.Color(255, 51, 51));
        jTable_ConferenceStatistics.setSelectionBackground(new java.awt.Color(0, 0, 153));
        jScrollPane2.setViewportView(jTable_ConferenceStatistics);
        if (jTable_ConferenceStatistics.getColumnModel().getColumnCount() > 0) {
            jTable_ConferenceStatistics.getColumnModel().getColumn(0).setResizable(false);
            jTable_ConferenceStatistics.getColumnModel().getColumn(1).setResizable(false);
            jTable_ConferenceStatistics.getColumnModel().getColumn(2).setResizable(false);
            jTable_ConferenceStatistics.getColumnModel().getColumn(3).setResizable(false);
            jTable_ConferenceStatistics.getColumnModel().getColumn(4).setResizable(false);
        }

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sort by...", "Name", "Time", "Number of Attendees" }));
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jTextField1.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        jTextField1.setText("Search...");
        jTextField1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jLabel11.setIcon(new javax.swing.ImageIcon("C:\\Users\\Dell\\Documents\\NetBeansProjects\\QuanLiHoiiNghi\\src\\main\\resources\\IMAGES\\search.png")); // NOI18N
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 902, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel_StatisticsLayout = new javax.swing.GroupLayout(jPanel_Statistics);
        jPanel_Statistics.setLayout(jPanel_StatisticsLayout);
        jPanel_StatisticsLayout.setHorizontalGroup(
            jPanel_StatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel_StatisticsLayout.setVerticalGroup(
            jPanel_StatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_StatisticsLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel_ConManager.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_ConManager.setMaximumSize(new java.awt.Dimension(922, 488));
        jPanel_ConManager.setMinimumSize(new java.awt.Dimension(922, 488));

        javax.swing.GroupLayout jPanel_ConManagerLayout = new javax.swing.GroupLayout(jPanel_ConManager);
        jPanel_ConManager.setLayout(jPanel_ConManagerLayout);
        jPanel_ConManagerLayout.setHorizontalGroup(
            jPanel_ConManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 922, Short.MAX_VALUE)
        );
        jPanel_ConManagerLayout.setVerticalGroup(
            jPanel_ConManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 488, Short.MAX_VALUE)
        );

        jPanel_UserManager.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_UserManager.setMaximumSize(new java.awt.Dimension(922, 488));
        jPanel_UserManager.setMinimumSize(new java.awt.Dimension(922, 488));

        jPanel5.setBackground(new java.awt.Color(255, 204, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 922, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 488, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel_UserManagerLayout = new javax.swing.GroupLayout(jPanel_UserManager);
        jPanel_UserManager.setLayout(jPanel_UserManagerLayout);
        jPanel_UserManagerLayout.setHorizontalGroup(
            jPanel_UserManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel_UserManagerLayout.setVerticalGroup(
            jPanel_UserManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel_Profile.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Profile.setMaximumSize(new java.awt.Dimension(922, 488));
        jPanel_Profile.setMinimumSize(new java.awt.Dimension(922, 488));
        jPanel_Profile.setPreferredSize(new java.awt.Dimension(922, 488));

        jPanel11.setBackground(new java.awt.Color(248, 148, 6));
        jPanel11.setMaximumSize(new java.awt.Dimension(922, 83));
        jPanel11.setMinimumSize(new java.awt.Dimension(922, 83));
        jPanel11.setPreferredSize(new java.awt.Dimension(922, 83));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Profile");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(44, 62, 60));
        jPanel7.setMaximumSize(new java.awt.Dimension(922, 397));
        jPanel7.setMinimumSize(new java.awt.Dimension(922, 397));
        jPanel7.setPreferredSize(new java.awt.Dimension(922, 397));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 26)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(236, 240, 241));
        jLabel17.setText("Username:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 26)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(236, 240, 241));
        jLabel18.setText("Name:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 26)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(236, 240, 241));
        jLabel19.setText("Email:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 26)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(236, 240, 241));
        jLabel20.setText("Password:");

        jTextField_EmailProfile.setBackground(new java.awt.Color(108, 122, 137));
        jTextField_EmailProfile.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField_EmailProfile.setForeground(new java.awt.Color(228, 241, 254));
        jTextField_EmailProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_EmailProfileActionPerformed(evt);
            }
        });

        jTextField_ProfileName.setBackground(new java.awt.Color(108, 122, 137));
        jTextField_ProfileName.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField_ProfileName.setForeground(new java.awt.Color(228, 241, 254));
        jTextField_ProfileName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_ProfileNameActionPerformed(evt);
            }
        });

        jPasswordField_Profile.setBackground(new java.awt.Color(108, 122, 137));
        jPasswordField_Profile.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPasswordField_Profile.setForeground(new java.awt.Color(228, 241, 254));
        jPasswordField_Profile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField_ProfileActionPerformed(evt);
            }
        });

        jButton_Signin1.setBackground(new java.awt.Color(34, 167, 240));
        jButton_Signin1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton_Signin1.setForeground(new java.awt.Color(228, 241, 254));
        jButton_Signin1.setText("Save");
        jButton_Signin1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton_Signin1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Signin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Signin1ActionPerformed(evt);
            }
        });

        jLabel_ProfileUsername.setBackground(new java.awt.Color(108, 122, 137));
        jLabel_ProfileUsername.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel_ProfileUsername.setForeground(new java.awt.Color(228, 241, 254));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel20)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField_EmailProfile)
                    .addComponent(jTextField_ProfileName)
                    .addComponent(jPasswordField_Profile, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                    .addComponent(jLabel_ProfileUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(420, Short.MAX_VALUE)
                .addComponent(jButton_Signin1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(380, 380, 380))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_ProfileName, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_ProfileUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordField_Profile, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_EmailProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton_Signin1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel_ProfileLayout = new javax.swing.GroupLayout(jPanel_Profile);
        jPanel_Profile.setLayout(jPanel_ProfileLayout);
        jPanel_ProfileLayout.setHorizontalGroup(
            jPanel_ProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_ProfileLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel_ProfileLayout.setVerticalGroup(
            jPanel_ProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ProfileLayout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel_SignIn.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_SignIn.setMaximumSize(new java.awt.Dimension(922, 488));
        jPanel_SignIn.setMinimumSize(new java.awt.Dimension(922, 488));
        jPanel_SignIn.setPreferredSize(new java.awt.Dimension(922, 488));

        jPanel3.setBackground(new java.awt.Color(248, 148, 6));
        jPanel3.setMaximumSize(new java.awt.Dimension(922, 83));
        jPanel3.setMinimumSize(new java.awt.Dimension(922, 83));
        jPanel3.setPreferredSize(new java.awt.Dimension(922, 83));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel6.setText("Sign In");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(22, 22, 22))
        );

        jPanel1.setBackground(new java.awt.Color(44, 62, 60));
        jPanel1.setMaximumSize(new java.awt.Dimension(922, 397));
        jPanel1.setMinimumSize(new java.awt.Dimension(922, 397));
        jPanel1.setPreferredSize(new java.awt.Dimension(922, 397));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 26)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(236, 240, 241));
        jLabel7.setText("Password:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 26)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(236, 240, 241));
        jLabel9.setText("Username: ");

        jTextField_Signin.setBackground(new java.awt.Color(108, 122, 137));
        jTextField_Signin.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField_Signin.setForeground(new java.awt.Color(228, 241, 254));
        jTextField_Signin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_SigninActionPerformed(evt);
            }
        });

        jPasswordField_Signin.setBackground(new java.awt.Color(108, 122, 137));
        jPasswordField_Signin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPasswordField_Signin.setForeground(new java.awt.Color(228, 241, 254));
        jPasswordField_Signin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField_SigninActionPerformed(evt);
            }
        });

        jButton_Signin.setBackground(new java.awt.Color(34, 167, 240));
        jButton_Signin.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton_Signin.setForeground(new java.awt.Color(228, 241, 254));
        jButton_Signin.setText("Sign In");
        jButton_Signin.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton_Signin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Signin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SigninActionPerformed(evt);
            }
        });

        jLabel_Signup.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_Signup.setForeground(new java.awt.Color(228, 241, 254));
        jLabel_Signup.setText("Click here to create a new account!");
        jLabel_Signup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_Signup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_SignupMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_Signin, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(328, 328, 328))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel7))
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel_Signup)
                        .addContainerGap(360, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPasswordField_Signin)
                            .addComponent(jTextField_Signin))
                        .addGap(178, 178, 178))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_Signin, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordField_Signin, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jButton_Signin, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(jLabel_Signup, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout jPanel_SignInLayout = new javax.swing.GroupLayout(jPanel_SignIn);
        jPanel_SignIn.setLayout(jPanel_SignInLayout);
        jPanel_SignInLayout.setHorizontalGroup(
            jPanel_SignInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_SignInLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel_SignInLayout.setVerticalGroup(
            jPanel_SignInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SignInLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel_SignUp.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_SignUp.setMaximumSize(new java.awt.Dimension(922, 488));
        jPanel_SignUp.setMinimumSize(new java.awt.Dimension(922, 488));
        jPanel_SignUp.setPreferredSize(new java.awt.Dimension(922, 488));

        jPanel8.setBackground(new java.awt.Color(248, 148, 6));
        jPanel8.setMaximumSize(new java.awt.Dimension(922, 83));
        jPanel8.setMinimumSize(new java.awt.Dimension(922, 83));
        jPanel8.setPreferredSize(new java.awt.Dimension(922, 83));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel10.setText("Sign Up");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(22, 22, 22))
        );

        jPanel9.setBackground(new java.awt.Color(44, 62, 60));
        jPanel9.setMaximumSize(new java.awt.Dimension(922, 397));
        jPanel9.setMinimumSize(new java.awt.Dimension(922, 397));
        jPanel9.setPreferredSize(new java.awt.Dimension(922, 397));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(236, 240, 241));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Username: ");

        jTextField_UsernameSignup.setBackground(new java.awt.Color(108, 122, 137));
        jTextField_UsernameSignup.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField_UsernameSignup.setForeground(new java.awt.Color(228, 241, 254));
        jTextField_UsernameSignup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_UsernameSignupActionPerformed(evt);
            }
        });

        jPassword_SignUp.setBackground(new java.awt.Color(108, 122, 137));
        jPassword_SignUp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPassword_SignUp.setForeground(new java.awt.Color(228, 241, 254));
        jPassword_SignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPassword_SignUpActionPerformed(evt);
            }
        });

        jButton_Signup.setBackground(new java.awt.Color(34, 167, 240));
        jButton_Signup.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton_Signup.setForeground(new java.awt.Color(228, 241, 254));
        jButton_Signup.setText("Sign Up");
        jButton_Signup.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton_Signup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Signup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SignupActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(236, 240, 241));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Name:");

        jTextField_NameSignup.setBackground(new java.awt.Color(108, 122, 137));
        jTextField_NameSignup.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField_NameSignup.setForeground(new java.awt.Color(228, 241, 254));
        jTextField_NameSignup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_NameSignupActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(236, 240, 241));
        jLabel14.setText("Retype Password:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(236, 240, 241));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Password:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(236, 240, 241));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Email:");

        jTextField_EmailSignup.setBackground(new java.awt.Color(108, 122, 137));
        jTextField_EmailSignup.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField_EmailSignup.setForeground(new java.awt.Color(228, 241, 254));
        jTextField_EmailSignup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_EmailSignupActionPerformed(evt);
            }
        });

        jPassword_RetypeSignup.setBackground(new java.awt.Color(108, 122, 137));
        jPassword_RetypeSignup.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPassword_RetypeSignup.setForeground(new java.awt.Color(228, 241, 254));
        jPassword_RetypeSignup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPassword_RetypeSignupActionPerformed(evt);
            }
        });

        jLabel_SignIn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_SignIn.setForeground(new java.awt.Color(228, 241, 254));
        jLabel_SignIn.setText("Click here to Sign In!");
        jLabel_SignIn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_SignIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_SignInMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(228, 228, 228)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jTextField_UsernameSignup, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPassword_SignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField_NameSignup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPassword_RetypeSignup)
                            .addComponent(jTextField_EmailSignup))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_Signup, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_SignIn))
                .addGap(353, 353, 353))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_NameSignup, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_UsernameSignup, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPassword_SignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPassword_RetypeSignup, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_EmailSignup, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton_Signup, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jLabel_SignIn)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel_SignUpLayout = new javax.swing.GroupLayout(jPanel_SignUp);
        jPanel_SignUp.setLayout(jPanel_SignUpLayout);
        jPanel_SignUpLayout.setHorizontalGroup(
            jPanel_SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel_SignUpLayout.setVerticalGroup(
            jPanel_SignUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SignUpLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel_containerLayout = new javax.swing.GroupLayout(jPanel_container);
        jPanel_container.setLayout(jPanel_containerLayout);
        jPanel_containerLayout.setHorizontalGroup(
            jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_containerLayout.createSequentialGroup()
                .addComponent(jPanel_menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_Home, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel_containerLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addGap(0, 240, Short.MAX_VALUE)
                    .addComponent(jPanel_Statistics, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addGap(0, 241, Short.MAX_VALUE)
                    .addComponent(jPanel_ListConference, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addGap(0, 241, Short.MAX_VALUE)
                    .addComponent(jPanel_ConManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addGap(0, 241, Short.MAX_VALUE)
                    .addComponent(jPanel_UserManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addGap(0, 241, Short.MAX_VALUE)
                    .addComponent(jPanel_Profile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addGap(0, 241, Short.MAX_VALUE)
                    .addComponent(jPanel_SignIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addGap(0, 241, Short.MAX_VALUE)
                    .addComponent(jPanel_SignUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel_containerLayout.setVerticalGroup(
            jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_containerLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel_Home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addGap(0, 55, Short.MAX_VALUE)
                    .addComponent(jPanel_Statistics, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addGap(0, 52, Short.MAX_VALUE)
                    .addComponent(jPanel_ListConference, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addGap(0, 55, Short.MAX_VALUE)
                    .addComponent(jPanel_ConManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addGap(0, 55, Short.MAX_VALUE)
                    .addComponent(jPanel_UserManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addGap(0, 55, Short.MAX_VALUE)
                    .addComponent(jPanel_Profile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addGap(0, 49, Short.MAX_VALUE)
                    .addComponent(jPanel_SignIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addGap(0, 55, Short.MAX_VALUE)
                    .addComponent(jPanel_SignUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_closeMouseClicked
        // close this form
        this.dispose();
    }//GEN-LAST:event_jLabel_closeMouseClicked

    private void jPasswordField_SigninActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField_SigninActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField_SigninActionPerformed

    private void jTextField_SigninActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_SigninActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_SigninActionPerformed

    private void jLabel_SignupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_SignupMouseClicked
        showPanel(jPanel_SignUp);
    }//GEN-LAST:event_jLabel_SignupMouseClicked

    private void jButton_SigninActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SigninActionPerformed
        // TODO add your handling code here:
        String password=String.valueOf(jPasswordField_Signin.getPassword());
        String username=jTextField_Signin.getText();
        boolean kq = false;
        try {
            kq = MemberDao.checkSignin(username, password);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Dashboard_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
      if(kq==true)
      {     
          setKeyMember(MemberDao.findidMember(username));
          setVaitro(MemberDao.findVaitro(username));
          showPanel(jPanel_Home);
          showMenu();
          showProfile();
          jTextField_Signin.setText(null);
          jPasswordField_Signin.setText(null);
          showListConferenceStatistics();
      }else JOptionPane.showMessageDialog(null, "Incorrect Username or Password!");
        
    }//GEN-LAST:event_jButton_SigninActionPerformed

 
    private void jTextField_UsernameSignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_UsernameSignupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_UsernameSignupActionPerformed

    private void jPassword_SignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPassword_SignUpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPassword_SignUpActionPerformed
    public static String hashPassword(String password) throws NoSuchAlgorithmException
    {
        MessageDigest md=MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[]b=md.digest();
        StringBuffer sb=new StringBuffer();
        for(byte b1:b)
        {
            sb.append(Integer.toHexString(b1&0xff).toString());
            
        }
        return sb.toString();
    }
    private void jButton_SignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SignupActionPerformed
        // TODO add your handling code here:
        Member member=new Member();
        member.setTen(jTextField_NameSignup.getText());
        //Kiểm tra username đã có hay chưa
        String userName=jTextField_UsernameSignup.getText();
        boolean a=MemberDao.findMemberUser(userName);
        if(a)
        {
            JOptionPane.showMessageDialog(null, "Sorry.Username already taken!");
            return;
        }     
        member.setUserName(jTextField_UsernameSignup.getText());
        //Kiểm tra nhập lại password có khớp hay không
        String password=String.valueOf(jPassword_SignUp.getPassword());
        String retypepassword=String.valueOf(jPassword_RetypeSignup.getPassword());
         if(password.equals(retypepassword)==false)
        {
            JOptionPane.showMessageDialog(null, "Please your make sure password match!");
            return;
        }
        try {
            member.setPassword(hashPassword(password));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Dashboard_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        member.setEmail(jTextField_EmailSignup.getText());
        //Thêm thành viên
        boolean kq=MemberDao.themMember(member);
        if(kq) {
            JOptionPane.showMessageDialog(null,"Sign Up Success!");
            jTextField_NameSignup.setText(null);
            jTextField_UsernameSignup.setText(null);
            jPassword_RetypeSignup.setText(null);
            jPassword_SignUp.setText(null);
            jTextField_EmailSignup.setText(null);
            
        }
        else System.out.println("Sign Up Failed!");
    }//GEN-LAST:event_jButton_SignupActionPerformed

    private void jTextField_NameSignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_NameSignupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_NameSignupActionPerformed

    private void jTextField_EmailSignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_EmailSignupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_EmailSignupActionPerformed

    private void jPassword_RetypeSignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPassword_RetypeSignupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPassword_RetypeSignupActionPerformed

    private void jLabel_SignInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_SignInMouseClicked
        // TODO add your handling code here:
        showPanel(jPanel_SignIn);
    }//GEN-LAST:event_jLabel_SignInMouseClicked

    private void jTextField_EmailProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_EmailProfileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_EmailProfileActionPerformed

    private void jTextField_ProfileNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_ProfileNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_ProfileNameActionPerformed

    private void jPasswordField_ProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField_ProfileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField_ProfileActionPerformed

    private void jButton_Signin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Signin1ActionPerformed
        try {
            // TODO add your handling code here:
            Member mb=MemberDao.findInforMember(keyMember);
            mb.setTen(jTextField_ProfileName.getText());
            String password=String.valueOf(jPasswordField_Profile.getPassword());
            mb.setPassword(hashPassword(password));
            mb.setEmail(jTextField_EmailProfile.getText());
            boolean kq=MemberDao.updateProfile(mb);
            if(kq) JOptionPane.showMessageDialog(null, "Update Success!");
            else  JOptionPane.showMessageDialog(null, "Update Fail!");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Dashboard_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_Signin1ActionPerformed

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
            java.util.logging.Logger.getLogger(Dashboard_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Dashboard_Form().setVisible(true);
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Dashboard_Form.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Dashboard_Form.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Dashboard_Form.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Dashboard_Form.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Signin;
    private javax.swing.JButton jButton_Signin1;
    private javax.swing.JButton jButton_Signup;
    private javax.swing.JComboBox<String> jComboBox1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_ProfileUsername;
    private javax.swing.JLabel jLabel_SignIn;
    private javax.swing.JLabel jLabel_Signup;
    private javax.swing.JLabel jLabel_appLogo;
    private javax.swing.JLabel jLabel_close;
    private javax.swing.JLabel jLabel_menuItemConfereceMa;
    private javax.swing.JLabel jLabel_menuItemHome;
    private javax.swing.JLabel jLabel_menuItemListConferece;
    private javax.swing.JLabel jLabel_menuItemProfile;
    private javax.swing.JLabel jLabel_menuItemSignIn;
    private javax.swing.JLabel jLabel_menuItemSignout;
    private javax.swing.JLabel jLabel_menuItemStatistics;
    private javax.swing.JLabel jLabel_menuItemUserMa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel_ConManager;
    private javax.swing.JPanel jPanel_Home;
    private javax.swing.JPanel jPanel_ListConference;
    private javax.swing.JPanel jPanel_Profile;
    private javax.swing.JPanel jPanel_SignIn;
    private javax.swing.JPanel jPanel_SignUp;
    private javax.swing.JPanel jPanel_Statistics;
    private javax.swing.JPanel jPanel_UserManager;
    private javax.swing.JPanel jPanel_container;
    private javax.swing.JPanel jPanel_logoANDname;
    private javax.swing.JPanel jPanel_menu;
    private javax.swing.JPasswordField jPasswordField_Profile;
    private javax.swing.JPasswordField jPasswordField_Signin;
    private javax.swing.JPasswordField jPassword_RetypeSignup;
    private javax.swing.JPasswordField jPassword_SignUp;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable_ConferenceStatistics;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField_EmailProfile;
    private javax.swing.JTextField jTextField_EmailSignup;
    private javax.swing.JTextField jTextField_NameSignup;
    private javax.swing.JTextField jTextField_ProfileName;
    private javax.swing.JTextField jTextField_Signin;
    private javax.swing.JTextField jTextField_UsernameSignup;
    // End of variables declaration//GEN-END:variables
}
