


import BUS.HoiNghiBus;
import BUS.MemberBus;
import DAO.*;
import POJOs.*;
import jPanel.JpanelCardConference;
import jPanel.jPanelConference;
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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.StreamSupport;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import jPanel.jPanelConference;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;


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

    public void setCountMember(int countMember) {
        this.countMember = countMember;
    }

    public int getCountMember() {
        return countMember;
    }
    
    // create an array of jpanels
    JPanel[] panels = new JPanel[9];
    public int vaitro=-1;
    public int keyMember=-1;
    public int keyConference=-1;
    public int countMember=0;
    public int back=-1;

    public void setBack(int back) {
        this.back = back;
    }

    public int getBack() {
        return back;
    }
    public void setKeyConference(int keyConference) {
        this.keyConference = keyConference;
    }

    public int getKeyConference() {
        return keyConference;
    }
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
        panels[8] = jPanel_DetailConference;
        addActionToMenuLabels();
        showMenu();
        showPanel(jPanel_Home);
        showListConference();
        showComboboxDiadiem();
        showCardConference();
       // aa.addActionListener(e->switchPanes());
        //showProfile();
       
    }
    public void showListConference(){
             
//       
//       jPanel_ListCon.add(a);
       List<Hoinghi> hoinghis=HoiNghiDAO.findAll();
       jPanel_ListCon.removeAll();
       for(int i=0;i<hoinghis.size();i++)
       {
           Hoinghi hn=hoinghis.get(i);
           JPanel a=new jPanelConference(hn.getIdHoiNghi());
           jPanel_ListCon.add(a);
           a.addMouseListener(new MouseAdapter() {
               @Override
               public void mouseClicked(MouseEvent e)
               {
                   setKeyConference(hn.getIdHoiNghi());
                   jLabel_DetailTen.setText(hn.getTen());
                   jLabel_DetailMotangangon.setText(hn.getMoTaNgangon());
                   jLabel_DetailNumber.setText(String.valueOf(hn.getSoNguoiThamDu()));
                   jLabel_DetailPalace.setText(hn.getDiadiemtochuc().getTen());
                   DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss"); 
                   String strDate = dateFormat.format(hn.getThoiGian());  
                   jLabel_DetailTime.setText(strDate);
                   jTextPane_DetailMotachitiet.setText(hn.getMoTaChitiet());
                   ImageIcon imageIcon = new ImageIcon(new ImageIcon(this.getClass().getResource(hn.getHinhAnh())).getImage().getScaledInstance(661, 232, Image.SCALE_SMOOTH));
                   jLabel_DetailImage.setIcon(imageIcon);
                   MemberList temp=MemberListsDAO.findMemberListtoID(getKeyMember(),getKeyConference());
                   if(temp==null)
                        jButton_signupConfer.setText("Register");
                   else jButton_signupConfer.setText("Cancel Register");
                   showMemberAttendConference(hn.getIdHoiNghi());
                   showPanel(jPanel_DetailConference);
               }
           });
       
           
       }
       
    }
       public void showCardConference(){
             
//       
//       jPanel_ListCon.add(a);
       List<Hoinghi> hoinghis=HoiNghiDAO.findAll();
       jPanel_ListCon1.removeAll();
       for(int i=0;i<hoinghis.size();i++)
       {
           Hoinghi hn=hoinghis.get(i);
           JPanel a=new JpanelCardConference(hn.getIdHoiNghi());
           jPanel_ListCon1.add(a);
           a.addMouseListener(new MouseAdapter() {
               @Override
               public void mouseClicked(MouseEvent e)
               {  
                   setKeyConference(hn.getIdHoiNghi());
                   jLabel_DetailTen.setText(hn.getTen());
                   jLabel_DetailMotangangon.setText(hn.getMoTaNgangon());
                   jLabel_DetailNumber.setText(String.valueOf(hn.getSoNguoiThamDu()));
                   jLabel_DetailPalace.setText(hn.getDiadiemtochuc().getTen());
                   DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss"); 
                   String strDate = dateFormat.format(hn.getThoiGian());  
                   jLabel_DetailTime.setText(strDate);
                   jTextPane_DetailMotachitiet.setText(hn.getMoTaChitiet());
                   ImageIcon imageIcon = new ImageIcon(new ImageIcon(this.getClass().getResource(hn.getHinhAnh())).getImage().getScaledInstance(661, 232, Image.SCALE_SMOOTH));
                   jLabel_DetailImage.setIcon(imageIcon);
                   MemberList temp=MemberListsDAO.findMemberListtoID(getKeyMember(),getKeyConference());
                   if(temp==null)
                        jButton_signupConfer.setText("Register");
                   else jButton_signupConfer.setText("Cancel Register");
                   showMemberAttendConference(hn.getIdHoiNghi());
                   showPanel(jPanel_DetailConference);
                   
               }
           });
       }
       
    }
    public void showListConferenceManager()
    {
   
        DefaultTableModel defaultTableModel=(DefaultTableModel) jTable_ListConManager.getModel();
        defaultTableModel.setRowCount(0);
        List<Hoinghi> hoinghis=HoiNghiDAO.findAll();
        for(int i=0;i<hoinghis.size();i++)
        {
            Hoinghi temp=hoinghis.get(i);
            List<String> list=new ArrayList<>();
            list.add(String.valueOf(temp.getIdHoiNghi()));
            list.add(temp.getTen());
            list.add(temp.getMoTaNgangon());
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss"); 
            String strDate = dateFormat.format(temp.getThoiGian());         
            list.add(strDate);
            list.add(temp.getDiadiemtochuc().getTen());
            list.add(String.valueOf(temp.getSoNguoiThamDu()));
            defaultTableModel.addRow(list.toArray());
            
        }
    }
    public void showMemberAttendConference(int idHn)
    {
       DefaultTableModel tableModel=(DefaultTableModel) jTable_MemberAttendConfer.getModel();
       if(tableModel.getRowCount()!=0)
           tableModel.setRowCount(0);
       Hoinghi hn=HoiNghiDAO.findInforHoinghi(idHn);
       Iterator<MemberList> memberLists= hn.getMemberLists().iterator();
       setCountMember(0);
       while(memberLists.hasNext())
       {
           MemberList temp=memberLists.next();
           if(temp.getConfirm()==1)
           {
           setCountMember(getCountMember()+1);
           List<String> list=new ArrayList<>();
           list.add(temp.getMember().getTen());
           list.add(temp.getMember().getEmail()); 
           tableModel.addRow(list.toArray());
           }
       }
        jTextCountNumberDetail.setText(String.valueOf(getCountMember()));
       
    }
    public void showMemberAttendConferManagement(int idHn)
    {
        DefaultTableModel defaultTableModel=(DefaultTableModel) jTable_MemberAttendManage.getModel();
        if(defaultTableModel.getRowCount()!=0)
            defaultTableModel.setRowCount(0);
        Hoinghi hn=HoiNghiDAO.findInforHoinghi(idHn);
        Iterator<MemberList> memberlisIt=hn.getMemberLists().iterator();
        while(memberlisIt.hasNext())
        {
            MemberList temp=memberlisIt.next();
            List<String> list=new ArrayList<>();
            
            list.add(String.valueOf(temp.getMember().getIdMember()));
            list.add(temp.getMember().getTen());
            list.add(temp.getMember().getUserName());
            if(temp.getConfirm()==1)
            {
                list.add("Confirm");
               
            }else if(temp.getConfirm()==0)
                list.add("N/A");
            defaultTableModel.addRow(list.toArray());
//            if(temp.getConfirm()==1)
//            {
//                defaultTableModel.addColumn(new Boolean(true));
//               
//            }else  defaultTableModel.addColumn(new Boolean(false));
            
        }
    }       
    public void showMemberManage()
    {
        DefaultTableModel defaultTableModel=(DefaultTableModel) jTable_UserManage.getModel();
        defaultTableModel.setRowCount(0);
        List<Member> members=MemberDao.findAll();
        for(int i=0;i<members.size();i++)
        {
            Member temp=members.get(i);
            if(temp.getVaiTro()==0)
            {
            List<String> list=new ArrayList<>();
            list.add(String.valueOf(temp.getIdMember()));
            list.add(temp.getTen());
            list.add(temp.getUserName());
            list.add(temp.getEmail());
            if(temp.getActive()==1)
            {
                list.add("active");
            }
            else if(temp.getActive()==0)
            {
                list.add("inactive");
            }
            defaultTableModel.addRow(list.toArray());
            }
        }
    }
    
    public void showProfile(){
        
        
      
        Member mb=MemberDao.findInforMember(getKeyMember());

        if(mb!=null)
         {
                jTextField_ProfileName.setText(mb.getTen());
                jLabel_ProfileUsername.setText(mb.getUserName());
                jTextField_EmailProfile.setText(mb.getEmail());
                jLabel_ProfileName.setText("Hi "+mb.getTen()+"!");
         }
    }
   
    public void showListConferenceStatistics()
    {
        DefaultTableModel tbStatistics=(DefaultTableModel) jTable_ConferenceStatistics.getModel();
        tbStatistics.setRowCount(0);
        Member mb=MemberDao.findInforMember(getKeyMember());
        Iterator<MemberList> memberlists=mb.getMemberLists().iterator();
        while(memberlists.hasNext()){
            MemberList temp=memberlists.next();
            Hoinghi hn=HoiNghiDAO.findInforHoinghi(temp.getHoinghi().getIdHoiNghi());
            List<String> list=new ArrayList<>();
            list.add(String.valueOf(hn.getIdHoiNghi()));
            list.add(hn.getTen());
            list.add(hn.getMoTaNgangon());
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss"); 
            String strDate = dateFormat.format(hn.getThoiGian());         
            list.add(strDate);
            Diadiemtochuc dd=hn.getDiadiemtochuc();
            list.add(String.valueOf(dd.getDiaChi()));
            list.add(String.valueOf(hn.getSoNguoiThamDu()));
            if(temp.getConfirm()==1)
                list.add("Confirm");
            else if(temp.getConfirm()==0)
                list.add("N/A");
            tbStatistics.addRow(list.toArray());
        }
    }
    public void resetConferManage(){
        jTextField_NameManage.setText(null);
        jTextField_NganGonManage.setText(null);
        jTextPane_ChitietManage.setText(null);
        jTextField_PathImage.setText(null);
        jTextField_TimeManage.setText(null);
        jTextField_number.setText(null);
        DefaultTableModel defaultTableModel=(DefaultTableModel) jTable_MemberAttendManage.getModel();
        defaultTableModel.setRowCount(0);
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
                                   resetConferManage();
                                   // jPanel_users.setBackground(Color.red);
                                   break;
                             case "List Conference":
                                   showPanel(jPanel_ListConference);
                                   setBack(1);
                                   resetConferManage();
                                   jButton_signupConfer.setVisible(true);
                                   break;
                                   
                            case "Conference statistics":
                                   showPanel(jPanel_Statistics);
                                   setBack(2);
                                   resetConferManage();
                                   showListConferenceStatistics();
                                   jButton_signupConfer.setVisible(true);
                                   // jPanel_products.setBackground(Color.BLUE);
                                   break;
                                   
                                   case "Conference Manager":
                                   showPanel(jPanel_ConManager);
                                   showListConferenceManager();
                                   setBack(3);
                                   // jPanel_settings.setBackground(Color.GRAY);
                                   break;
                                   
                                   case "User Manager":
                                   showPanel(jPanel_UserManager);
                                   showMemberManage();
                                   resetConferManage();
                                   // jPanel_contact.setBackground(Color.GREEN);
                                   break;
                                   
                                   case "Profile":
                                   showPanel(jPanel_Profile);
                                   resetConferManage();
                                   // jPanel_calendar.setBackground(Color.yellow);
                                   break;
                                   
                                   case "Sign In":
                                   resetConferManage();
                                   showPanel(jPanel_SignIn);
                                   // jPanel_test.setBackground(Color.orange);
                                   break;
                                   case "Sign Out":
                                    setVaitro(-1);
                                    setKeyMember(-1);
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
        jLabel2 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel_DetailConference = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel_DetailMotangangon = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_MemberAttendConfer = new javax.swing.JTable();
        jLabel_DetailImage = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane_DetailMotachitiet = new javax.swing.JTextPane();
        jPanel13 = new javax.swing.JPanel();
        jLabel_DetailTen = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel_DetailTime = new javax.swing.JLabel();
        jLabel_DetailPalace = new javax.swing.JLabel();
        jLabel_DetailNumber = new javax.swing.JLabel();
        jButton_signupConfer = new javax.swing.JButton();
        jTextCountNumberDetail = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jPanel_ListConference = new javax.swing.JPanel();
        jScrollPane_List = new javax.swing.JScrollPane();
        jPanel_ListCon = new javax.swing.JPanel();
        jScrollPane_Card = new javax.swing.JScrollPane();
        jPanel_ListCon1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel_Switch = new javax.swing.JLabel();
        jPanel_Statistics = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_ConferenceStatistics = new javax.swing.JTable();
        jComboBox_Sort = new javax.swing.JComboBox<>();
        jTextField_SearchStatistics = new javax.swing.JTextField();
        jLabel_Search = new javax.swing.JLabel();
        jPanel_ConManager = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_ListConManager = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable_MemberAttendManage = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jTextField_NameManage = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextField_NganGonManage = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextPane_ChitietManage = new javax.swing.JTextPane();
        jLabel27 = new javax.swing.JLabel();
        jTextField_PathImage = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jTextField_TimeManage = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton_ThemHoinghi = new javax.swing.JButton();
        jButton_DuyetMemberAttendConfer = new javax.swing.JButton();
        jComboBox_Chondiadiem = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        jTextField_number = new javax.swing.JTextField();
        jPanel_UserManager = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable_UserManage = new javax.swing.JTable();
        jButton_NganChanMember = new javax.swing.JButton();
        jComboBox_SortMember = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jComboBox_Filter = new javax.swing.JComboBox<>();
        jPanel_Profile = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextField_EmailProfile = new javax.swing.JTextField();
        jTextField_ProfileName = new javax.swing.JTextField();
        jPasswordField_ConNewPass = new javax.swing.JPasswordField();
        jButton_Signin1 = new javax.swing.JButton();
        jLabel_ProfileUsername = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPasswordField_CurrentPass = new javax.swing.JPasswordField();
        jPasswordField_NewPass = new javax.swing.JPasswordField();
        jLabel_ProfileName = new javax.swing.JLabel();
        jButton_ChangePass = new javax.swing.JButton();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel_container.setBackground(new java.awt.Color(255, 255, 255));

        jPanel_menu.setBackground(new java.awt.Color(46, 49, 49));

        jPanel_logoANDname.setBackground(new java.awt.Color(46, 49, 49));

        jLabel_appLogo.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_appLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_appLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/appLogo.png"))); // NOI18N
        jLabel_appLogo.setOpaque(true);

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 30)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("CMA");

        javax.swing.GroupLayout jPanel_logoANDnameLayout = new javax.swing.GroupLayout(jPanel_logoANDname);
        jPanel_logoANDname.setLayout(jPanel_logoANDnameLayout);
        jPanel_logoANDnameLayout.setHorizontalGroup(
            jPanel_logoANDnameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_logoANDnameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_appLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        jLabel_menuItemHome.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel_menuItemHome.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_menuItemHome.setText("Home");
        jLabel_menuItemHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_menuItemHome.setOpaque(true);

        jLabel_menuItemListConferece.setBackground(new java.awt.Color(46, 49, 49));
        jLabel_menuItemListConferece.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel_menuItemListConferece.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_menuItemListConferece.setText("List Conference");
        jLabel_menuItemListConferece.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_menuItemListConferece.setOpaque(true);

        jLabel_menuItemStatistics.setBackground(new java.awt.Color(46, 49, 49));
        jLabel_menuItemStatistics.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel_menuItemStatistics.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_menuItemStatistics.setText("Conference statistics ");
        jLabel_menuItemStatistics.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_menuItemStatistics.setOpaque(true);

        jLabel_menuItemConfereceMa.setBackground(new java.awt.Color(46, 49, 49));
        jLabel_menuItemConfereceMa.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel_menuItemConfereceMa.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_menuItemConfereceMa.setText("Conference Manager ");
        jLabel_menuItemConfereceMa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_menuItemConfereceMa.setOpaque(true);

        jLabel_menuItemUserMa.setBackground(new java.awt.Color(46, 49, 49));
        jLabel_menuItemUserMa.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel_menuItemUserMa.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_menuItemUserMa.setText("User Manager");
        jLabel_menuItemUserMa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_menuItemUserMa.setOpaque(true);

        jLabel_menuItemProfile.setBackground(new java.awt.Color(46, 49, 49));
        jLabel_menuItemProfile.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel_menuItemProfile.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_menuItemProfile.setText("Profile");
        jLabel_menuItemProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_menuItemProfile.setOpaque(true);

        jLabel_menuItemSignIn.setBackground(new java.awt.Color(46, 49, 49));
        jLabel_menuItemSignIn.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel_menuItemSignIn.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_menuItemSignIn.setText("Sign In");
        jLabel_menuItemSignIn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_menuItemSignIn.setOpaque(true);

        jLabel_menuItemSignout.setBackground(new java.awt.Color(0, 0, 0));
        jLabel_menuItemSignout.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_menuItemStatistics, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_menuItemConfereceMa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_menuItemUserMa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_menuItemProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_menuItemSignIn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jTextPane1.setEditable(false);
        jTextPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextPane1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextPane1.setText("Welcome to the CMA application. CMA will help you track and register the conferences that interest you.");
        jScrollPane8.setViewportView(jTextPane1);

        javax.swing.GroupLayout jPanel_HomeLayout = new javax.swing.GroupLayout(jPanel_Home);
        jPanel_Home.setLayout(jPanel_HomeLayout);
        jPanel_HomeLayout.setHorizontalGroup(
            jPanel_HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_HomeLayout.createSequentialGroup()
                .addGroup(jPanel_HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_HomeLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel_HomeLayout.setVerticalGroup(
            jPanel_HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_HomeLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel_HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_HomeLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_HomeLayout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel_DetailConference.setMaximumSize(new java.awt.Dimension(922, 488));
        jPanel_DetailConference.setMinimumSize(new java.awt.Dimension(922, 488));
        jPanel_DetailConference.setPreferredSize(new java.awt.Dimension(922, 488));

        jPanel11.setBackground(new java.awt.Color(44, 62, 60));
        jPanel11.setMaximumSize(new java.awt.Dimension(922, 488));
        jPanel11.setMinimumSize(new java.awt.Dimension(922, 488));

        jLabel_DetailMotangangon.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_DetailMotangangon.setForeground(new java.awt.Color(228, 241, 254));

        jTable_MemberAttendConfer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Email"
            }
        ));
        jScrollPane3.setViewportView(jTable_MemberAttendConfer);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTextPane_DetailMotachitiet.setEditable(false);
        jTextPane_DetailMotachitiet.setBackground(new java.awt.Color(204, 204, 204));
        jTextPane_DetailMotachitiet.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jScrollPane1.setViewportView(jTextPane_DetailMotachitiet);

        jPanel13.setBackground(new java.awt.Color(248, 148, 6));

        jLabel_DetailTen.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel_DetailTen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/back.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_DetailTen, javax.swing.GroupLayout.PREFERRED_SIZE, 825, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_DetailTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(228, 241, 254));
        jLabel1.setText("Palace:");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(228, 241, 254));
        jLabel25.setText("Time:");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(228, 241, 254));
        jLabel26.setText("Number:");

        jLabel_DetailTime.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel_DetailTime.setForeground(new java.awt.Color(228, 241, 254));

        jLabel_DetailPalace.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel_DetailPalace.setForeground(new java.awt.Color(228, 241, 254));

        jLabel_DetailNumber.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel_DetailNumber.setForeground(new java.awt.Color(228, 241, 254));

        jButton_signupConfer.setBackground(new java.awt.Color(34, 167, 240));
        jButton_signupConfer.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_signupConfer.setForeground(new java.awt.Color(228, 241, 254));
        jButton_signupConfer.setText("Đăng ký");
        jButton_signupConfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_signupConferActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(228, 241, 254));
        jLabel30.setText("Total:");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_signupConfer, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGap(50, 50, 50)
                                        .addComponent(jTextCountNumberDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(33, 33, 33))))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jLabel_DetailImage, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_DetailTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel_DetailPalace, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(jLabel26)
                                        .addGap(5, 5, 5)
                                        .addComponent(jLabel_DetailNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel_DetailMotangangon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_DetailMotangangon, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(jLabel_DetailTime))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel_DetailPalace))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(jLabel_DetailNumber))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(jLabel_DetailImage, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextCountNumberDetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_signupConfer, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout jPanel_DetailConferenceLayout = new javax.swing.GroupLayout(jPanel_DetailConference);
        jPanel_DetailConference.setLayout(jPanel_DetailConferenceLayout);
        jPanel_DetailConferenceLayout.setHorizontalGroup(
            jPanel_DetailConferenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel_DetailConferenceLayout.setVerticalGroup(
            jPanel_DetailConferenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel_ListConference.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_ListConference.setMaximumSize(new java.awt.Dimension(922, 488));
        jPanel_ListConference.setMinimumSize(new java.awt.Dimension(922, 488));
        jPanel_ListConference.setPreferredSize(new java.awt.Dimension(922, 488));

        jScrollPane_List.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane_List.setHorizontalScrollBar(null);
        jScrollPane_List.setMaximumSize(new java.awt.Dimension(922, 456));
        jScrollPane_List.setMinimumSize(new java.awt.Dimension(922, 456));
        jScrollPane_List.setPreferredSize(new java.awt.Dimension(922, 456));

        jPanel_ListCon.setBackground(new java.awt.Color(228, 241, 254));
        jPanel_ListCon.setLayout(new java.awt.GridLayout(0, 1, 10, 10));
        jScrollPane_List.setViewportView(jPanel_ListCon);

        jScrollPane_Card.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane_Card.setHorizontalScrollBar(null);
        jScrollPane_Card.setMaximumSize(new java.awt.Dimension(922, 456));
        jScrollPane_Card.setMinimumSize(new java.awt.Dimension(922, 456));
        jScrollPane_Card.setPreferredSize(new java.awt.Dimension(922, 456));

        jPanel_ListCon1.setBackground(new java.awt.Color(228, 241, 254));
        jPanel_ListCon1.setLayout(new java.awt.GridLayout(0, 3, 2, 10));
        jScrollPane_Card.setViewportView(jPanel_ListCon1);

        jPanel6.setBackground(new java.awt.Color(44, 62, 60));
        jPanel6.setMaximumSize(new java.awt.Dimension(922, 36));
        jPanel6.setMinimumSize(new java.awt.Dimension(922, 36));
        jPanel6.setPreferredSize(new java.awt.Dimension(922, 36));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
        });

        jLabel_Switch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Switch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/grid.png"))); // NOI18N
        jLabel_Switch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_Switch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_SwitchMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_Switch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(872, 872, 872))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_Switch, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel_ListConferenceLayout = new javax.swing.GroupLayout(jPanel_ListConference);
        jPanel_ListConference.setLayout(jPanel_ListConferenceLayout);
        jPanel_ListConferenceLayout.setHorizontalGroup(
            jPanel_ListConferenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane_List, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_ListConferenceLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane_Card, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel_ListConferenceLayout.setVerticalGroup(
            jPanel_ListConferenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ListConferenceLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane_List, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane_Card, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jPanel_Statistics.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Statistics.setMaximumSize(new java.awt.Dimension(922, 488));
        jPanel_Statistics.setMinimumSize(new java.awt.Dimension(922, 488));
        jPanel_Statistics.setPreferredSize(new java.awt.Dimension(922, 488));

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
                "ID", "Name", "Brief Description", "Time", "Venue Location", "Number of Attendees", "Confirm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_ConferenceStatistics.setGridColor(new java.awt.Color(255, 51, 51));
        jTable_ConferenceStatistics.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_ConferenceStatisticsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable_ConferenceStatistics);

        jComboBox_Sort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sort by...", "Name", "Time", "Number of Attendees" }));
        jComboBox_Sort.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jComboBox_Sort.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox_Sort.setMaximumSize(new java.awt.Dimension(92, 32));
        jComboBox_Sort.setMinimumSize(new java.awt.Dimension(92, 32));
        jComboBox_Sort.setPreferredSize(new java.awt.Dimension(92, 32));
        jComboBox_Sort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_SortActionPerformed(evt);
            }
        });

        jTextField_SearchStatistics.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
        jTextField_SearchStatistics.setText("Search ID, Name");
        jTextField_SearchStatistics.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField_SearchStatistics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_SearchStatisticsActionPerformed(evt);
            }
        });

        jLabel_Search.setIcon(new javax.swing.ImageIcon("C:\\Users\\Dell\\Documents\\NetBeansProjects\\QuanLiHoiiNghi\\src\\main\\resources\\IMAGES\\search.png")); // NOI18N
        jLabel_Search.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_Search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_SearchMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel_Search)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField_SearchStatistics, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jComboBox_Sort, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 902, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_Search)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField_SearchStatistics, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox_Sort, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel_StatisticsLayout = new javax.swing.GroupLayout(jPanel_Statistics);
        jPanel_Statistics.setLayout(jPanel_StatisticsLayout);
        jPanel_StatisticsLayout.setHorizontalGroup(
            jPanel_StatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_StatisticsLayout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_StatisticsLayout.setVerticalGroup(
            jPanel_StatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel_ConManager.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_ConManager.setMaximumSize(new java.awt.Dimension(922, 488));
        jPanel_ConManager.setMinimumSize(new java.awt.Dimension(922, 488));

        jPanel4.setBackground(new java.awt.Color(44, 62, 60));
        jPanel4.setMaximumSize(new java.awt.Dimension(922, 488));
        jPanel4.setMinimumSize(new java.awt.Dimension(922, 488));

        jTable_ListConManager.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Brief Description", "Time", "Venue Location", "Number of Attendees"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_ListConManager.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_ListConManagerMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable_ListConManager);

        jTable_MemberAttendManage.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Username", "Confiirm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable_MemberAttendManage);
        if (jTable_MemberAttendManage.getColumnModel().getColumnCount() > 0) {
            jTable_MemberAttendManage.getColumnModel().getColumn(0).setResizable(false);
            jTable_MemberAttendManage.getColumnModel().getColumn(1).setResizable(false);
            jTable_MemberAttendManage.getColumnModel().getColumn(2).setResizable(false);
            jTable_MemberAttendManage.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(228, 241, 254));
        jLabel11.setText("Name:");

        jTextField_NameManage.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(228, 241, 254));
        jLabel21.setText("Brief Description:");

        jTextField_NganGonManage.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jTextField_NganGonManage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_NganGonManageActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(228, 241, 254));
        jLabel24.setText("Detailed Description:");

        jTextPane_ChitietManage.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jScrollPane6.setViewportView(jTextPane_ChitietManage);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(228, 241, 254));
        jLabel27.setText("Image Path:");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(228, 241, 254));
        jLabel28.setText("Time:");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Change");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton_ThemHoinghi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_ThemHoinghi.setText("Add");
        jButton_ThemHoinghi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton_ThemHoinghi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ThemHoinghiActionPerformed(evt);
            }
        });

        jButton_DuyetMemberAttendConfer.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_DuyetMemberAttendConfer.setText("Save");
        jButton_DuyetMemberAttendConfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DuyetMemberAttendConferActionPerformed(evt);
            }
        });

        jComboBox_Chondiadiem.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(228, 241, 254));
        jLabel29.setText("Number:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_NganGonManage)
                    .addComponent(jScrollPane6)
                    .addComponent(jTextField_PathImage)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel21)
                                .addComponent(jLabel24)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextField_NameManage, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel27)
                                .addComponent(jComboBox_Chondiadiem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addGap(198, 198, 198)
                                .addComponent(jLabel29)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField_TimeManage, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField_number, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
                    .addComponent(jScrollPane4)))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(jButton_ThemHoinghi, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_DuyetMemberAttendConfer, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(197, 197, 197))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_NameManage, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_NganGonManage, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField_PathImage, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_TimeManage, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            .addComponent(jTextField_number))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox_Chondiadiem))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_ThemHoinghi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton_DuyetMemberAttendConfer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel_ConManagerLayout = new javax.swing.GroupLayout(jPanel_ConManager);
        jPanel_ConManager.setLayout(jPanel_ConManagerLayout);
        jPanel_ConManagerLayout.setHorizontalGroup(
            jPanel_ConManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel_ConManagerLayout.setVerticalGroup(
            jPanel_ConManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel_UserManager.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_UserManager.setMaximumSize(new java.awt.Dimension(922, 488));
        jPanel_UserManager.setMinimumSize(new java.awt.Dimension(922, 488));

        jPanel5.setBackground(new java.awt.Color(44, 62, 60));
        jPanel5.setForeground(new java.awt.Color(255, 255, 255));

        jTable_UserManage.setBackground(new java.awt.Color(108, 122, 137));
        jTable_UserManage.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTable_UserManage.setForeground(new java.awt.Color(228, 241, 254));
        jTable_UserManage.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Username", "Email", "Active"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_UserManage.setGridColor(new java.awt.Color(102, 102, 255));
        jScrollPane7.setViewportView(jTable_UserManage);
        if (jTable_UserManage.getColumnModel().getColumnCount() > 0) {
            jTable_UserManage.getColumnModel().getColumn(0).setResizable(false);
            jTable_UserManage.getColumnModel().getColumn(1).setResizable(false);
            jTable_UserManage.getColumnModel().getColumn(2).setResizable(false);
            jTable_UserManage.getColumnModel().getColumn(3).setResizable(false);
            jTable_UserManage.getColumnModel().getColumn(4).setResizable(false);
        }

        jButton_NganChanMember.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton_NganChanMember.setText("SAVE");
        jButton_NganChanMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_NganChanMemberActionPerformed(evt);
            }
        });

        jComboBox_SortMember.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sort by...", "ID", "Name" }));
        jComboBox_SortMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_SortMemberActionPerformed(evt);
            }
        });

        jComboBox_Filter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Filter...", "Name", "Active", "Inactive" }));
        jComboBox_Filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_FilterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jComboBox_Filter, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox_SortMember, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(420, Short.MAX_VALUE)
                .addComponent(jButton_NganChanMember, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(402, 402, 402))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_SortMember, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jComboBox_Filter, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_NganChanMember, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

        jPanel7.setBackground(new java.awt.Color(44, 62, 60));
        jPanel7.setMaximumSize(new java.awt.Dimension(922, 397));
        jPanel7.setMinimumSize(new java.awt.Dimension(922, 397));
        jPanel7.setPreferredSize(new java.awt.Dimension(922, 397));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(236, 240, 241));
        jLabel17.setText("Username:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(236, 240, 241));
        jLabel18.setText("Name:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(236, 240, 241));
        jLabel19.setText("Email:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(236, 240, 241));
        jLabel20.setText("Current Password:");

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

        jPasswordField_ConNewPass.setBackground(new java.awt.Color(108, 122, 137));
        jPasswordField_ConNewPass.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPasswordField_ConNewPass.setForeground(new java.awt.Color(228, 241, 254));
        jPasswordField_ConNewPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField_ConNewPassActionPerformed(evt);
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

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(236, 240, 241));
        jLabel22.setText("Confirm New Password:");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(236, 240, 241));
        jLabel23.setText("New Password:");

        jPasswordField_CurrentPass.setBackground(new java.awt.Color(108, 122, 137));
        jPasswordField_CurrentPass.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPasswordField_CurrentPass.setForeground(new java.awt.Color(228, 241, 254));
        jPasswordField_CurrentPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField_CurrentPassActionPerformed(evt);
            }
        });

        jPasswordField_NewPass.setBackground(new java.awt.Color(108, 122, 137));
        jPasswordField_NewPass.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPasswordField_NewPass.setForeground(new java.awt.Color(228, 241, 254));
        jPasswordField_NewPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField_NewPassActionPerformed(evt);
            }
        });

        jLabel_ProfileName.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel_ProfileName.setForeground(new java.awt.Color(236, 240, 241));
        jLabel_ProfileName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jButton_ChangePass.setBackground(new java.awt.Color(34, 167, 240));
        jButton_ChangePass.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton_ChangePass.setForeground(new java.awt.Color(228, 241, 254));
        jButton_ChangePass.setText("Change Password");
        jButton_ChangePass.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton_ChangePass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_ChangePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ChangePassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(186, 186, 186)
                                .addComponent(jLabel17))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPasswordField_ConNewPass, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextField_EmailProfile, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                                .addComponent(jLabel_ProfileUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPasswordField_CurrentPass, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPasswordField_NewPass, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_ProfileName, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                            .addComponent(jLabel_ProfileName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(257, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jButton_Signin1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(381, 381, 381))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jButton_ChangePass, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(315, 315, 315))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_ProfileName, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_ProfileName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_ProfileUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_EmailProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_Signin1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField_CurrentPass, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField_NewPass, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField_ConNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_ChangePass, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel_ProfileLayout = new javax.swing.GroupLayout(jPanel_Profile);
        jPanel_Profile.setLayout(jPanel_ProfileLayout);
        jPanel_ProfileLayout.setHorizontalGroup(
            jPanel_ProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ProfileLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel_ProfileLayout.setVerticalGroup(
            jPanel_ProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
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
                .addContainerGap(283, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel_Home, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addGap(0, 253, Short.MAX_VALUE)
                    .addComponent(jPanel_Statistics, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addGap(0, 253, Short.MAX_VALUE)
                    .addComponent(jPanel_ListConference, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addGap(0, 253, Short.MAX_VALUE)
                    .addComponent(jPanel_ConManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addGap(0, 253, Short.MAX_VALUE)
                    .addComponent(jPanel_UserManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addGap(0, 253, Short.MAX_VALUE)
                    .addComponent(jPanel_Profile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addGap(0, 253, Short.MAX_VALUE)
                    .addComponent(jPanel_SignIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addGap(0, 253, Short.MAX_VALUE)
                    .addComponent(jPanel_SignUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addGap(0, 253, Short.MAX_VALUE)
                    .addComponent(jPanel_DetailConference, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel_containerLayout.setVerticalGroup(
            jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_containerLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel_Home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
            .addGroup(jPanel_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_containerLayout.createSequentialGroup()
                    .addGap(0, 55, Short.MAX_VALUE)
                    .addComponent(jPanel_DetailConference, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        int kq = 0;
        try {
           kq = MemberDao.checkSignin(username, password);
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Dashboard_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
      if(kq==1)
      {     
          setKeyMember(MemberDao.findidMember(username));
          setVaitro(MemberDao.findVaitro(username));
          showPanel(jPanel_Home);
          showMenu();
          showProfile();
          jTextField_Signin.setText(null);
          jPasswordField_Signin.setText(null);
          
      }else if(kq==0)
          JOptionPane.showMessageDialog(null, "Account Locked.Your account has been locked by the administrator!");
      else if(kq==2) 
          JOptionPane.showMessageDialog(null, "Incorrect Username or Password!");
        
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
        member.setActive(1);//da active
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

    private void jPasswordField_ConNewPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField_ConNewPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField_ConNewPassActionPerformed

    private void jButton_Signin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Signin1ActionPerformed

            // TODO add your handling code here:
            Member mb=MemberDao.findInforMember(getKeyMember());
            mb.setTen(jTextField_ProfileName.getText());
//            String password=String.valueOf(jPasswordField_ConNewPass.getPassword());
//            mb.setPassword(hashPassword(password));
            mb.setEmail(jTextField_EmailProfile.getText());
             jLabel_ProfileName.setText("Hi "+mb.getTen()+"!");
            boolean kq=MemberDao.updateProfile(mb);
            if(kq) JOptionPane.showMessageDialog(null, "Update  Account Success!");
            else  JOptionPane.showMessageDialog(null, "Update Account Fail!");
       
    }//GEN-LAST:event_jButton_Signin1ActionPerformed
    
    private void jComboBox_SortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_SortActionPerformed
        int index=jComboBox_Sort.getSelectedIndex();
               if(index>0)
                {
                    List<Hoinghi>kq=new ArrayList<>();
            switch (index){
                    case 1:
                         {
                              kq=HoiNghiBus.sortNameListConferenceMember(getKeyMember()); 
                              break;
                        }
                    case 2:
                    {
                        kq=HoiNghiBus.sortTimeListConferenceMember(getKeyMember());
                        break;
                    }
                    case 3:
                    {
                        kq=HoiNghiBus.sortAttendeesListConferenceMember(getKeyMember());
                        break;
                    }
                   }
            DefaultTableModel tbStatistics=(DefaultTableModel) jTable_ConferenceStatistics.getModel();
            tbStatistics.setRowCount(0);
            for(int i=0;i<kq.size();i++)
            {
             Hoinghi temp=kq.get(i);
             List<String> list=new ArrayList<>();
             list.add(String.valueOf(temp.getIdHoiNghi()));
             list.add(temp.getTen());
             list.add(temp.getMoTaNgangon());
             DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss"); 
             String strDate = dateFormat.format(temp.getThoiGian());          
             list.add(String.valueOf(strDate));
             Diadiemtochuc dd=temp.getDiadiemtochuc();
             list.add(String.valueOf(dd.getDiaChi()));
             list.add(String.valueOf(temp.getSoNguoiThamDu()));
             MemberList mbl=MemberListsDAO.findMemberListtoID(getKeyMember(), temp.getIdHoiNghi());
             if(mbl.getConfirm()==0)
                 list.add("N/A");
             else if(mbl.getConfirm()==1)
                 list.add("Confirm");
             tbStatistics.addRow(list.toArray());
             }
         }
    }//GEN-LAST:event_jComboBox_SortActionPerformed

    private void jLabel_SwitchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_SwitchMouseClicked
        // TODO add your handling code here:
        
         switchPanes();
         
        
    }//GEN-LAST:event_jLabel_SwitchMouseClicked
    boolean isListCon;
    void switchPanes() {
        if (isListCon) {showListPane();}
        else { showCardPane();}
    }

    void showListPane() {
        jScrollPane_List.setVisible(true);       
        jScrollPane_Card.setVisible(false);
        Image imageIcon = new ImageIcon(this.getClass().getResource("/IMAGES/grid.png")).getImage();
        jLabel_Switch.setIcon(new ImageIcon(imageIcon));
        isListCon=false;
        showPanel(jPanel_ListConference);
    }

    void showCardPane() {       
        jScrollPane_Card.setVisible(true);
        jScrollPane_List.setVisible(false);
        Image imageIcon = new ImageIcon(this.getClass().getResource("/IMAGES/list.png")).getImage();
        jLabel_Switch.setIcon(new ImageIcon(imageIcon));
        isListCon=true;
        showPanel(jPanel_ListConference);
    }
    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel6MouseClicked

    private void jPasswordField_CurrentPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField_CurrentPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField_CurrentPassActionPerformed

    private void jPasswordField_NewPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField_NewPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField_NewPassActionPerformed

    private void jButton_ChangePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ChangePassActionPerformed
        // TODO add your handling code here
        Member mb=MemberDao.findInforMember(getKeyMember());
        String curPass=String.valueOf(jPasswordField_CurrentPass.getPassword());
        String newPass=String.valueOf(jPasswordField_NewPass.getPassword());
        String confirmNewPass=String.valueOf(jPasswordField_ConNewPass.getPassword());
        try {
            if(mb.getPassword().equals(hashPassword(curPass)))
            {
                if(newPass.equals(confirmNewPass))
                {
                    mb.setPassword(hashPassword(newPass));
                    boolean kq=MemberDao.updateProfile(mb);
                    if(kq)
                    {
                        JOptionPane.showMessageDialog(null, "Change Password Success!");
                        jPasswordField_CurrentPass.setText(null);
                        jPasswordField_NewPass.setText(null);
                        jPasswordField_ConNewPass.setText(null);
                    }
                    else JOptionPane.showMessageDialog(null, "Change Password Failed!");
                }else JOptionPane.showMessageDialog(null, "Confirm Password should be equal to Password!");
            }else JOptionPane.showMessageDialog(null, "Current Password Not Accepted!");
                } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Dashboard_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_ChangePassActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        switch (getBack()) {
            case 1:
                showPanel(jPanel_ListConference);
                break;
            case 2:
                showPanel(jPanel_Statistics);
                showListConferenceStatistics();
                break;
            case 3:
                showPanel(jPanel_ConManager);
                break;
            default:
                break;
        }
        
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jButton_signupConferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_signupConferActionPerformed
        // TODO add your handling code here:
       Hoinghi hoinghi=HoiNghiDAO.findInforHoinghi(getKeyConference());
     //   if(getCountMember()<hoinghi.getSoNguoiThamDu()) {
        int demMember=0;
        Iterator<MemberList> iterator=hoinghi.getMemberLists().iterator();
        while(iterator.hasNext())
        {
            iterator.next();
            demMember++;
        }
        if(getKeyMember()==-1)
        {
            JOptionPane.showMessageDialog(null, "Please sign in to use this function!");
            setLabelBackround(jLabel_menuItemSignIn);
            showPanel(jPanel_SignIn);
        }else
        {
            MemberList mbl=new MemberList();
            MemberListId idmbl=new MemberListId(getKeyConference(),getKeyMember());
            mbl.setId(idmbl);
            Hoinghi hn=HoiNghiDAO.findInforHoinghi(getKeyConference());
            mbl.setHoinghi(hn);
            Member mb=MemberDao.findInforMember(getKeyMember());
            mbl.setMember(mb);
            mbl.setConfirm(0);
            if(MemberListsDAO.findMemberList(mbl)==true)
            {
                 if(demMember<hoinghi.getSoNguoiThamDu())
                 {
                    MemberListsDAO.addMemberList(mbl);
                    showListConferenceStatistics();
                    JOptionPane.showMessageDialog(null, "Sign up for the conference successfully!");
                    jButton_signupConfer.setText("Cancel Register");
                 }else JOptionPane.showMessageDialog(null, "The conference is full of people");
            }else if(MemberListsDAO.findMemberList(mbl)==false)
            {
//                MemberList temp=MemberListsDAO.findMemberListtoID(getKeyMember(),getKeyConference());
//                if(temp.getConfirm()==0)
//                {
//                    JOptionPane.showMessageDialog(null, "Bạn đã đăng ký hội nghị này và đnag chờ duyệt");
//                }
//                else if(temp.getConfirm()==1)
//                {
//                    JOptionPane.showMessageDialog(null, "Bạn đã đăng ký hội nghị này");
//                }
 //               jButton_signupConfer.setText("Đăng ký");
                  
                  Date date=new Date();
                 if(date.compareTo(hoinghi.getThoiGian())<0)
                     { boolean kq=MemberListsDAO.xoaMemberList(mbl);
                        if(kq)
                        {
                               JOptionPane.showMessageDialog(null, "Cancel register successfully!");
                               jButton_signupConfer.setText("Register");
                        }
                    }   else JOptionPane.showMessageDialog(null, "The conference that took place cannot be cancel register.");
                  
                  showMemberAttendConference(hn.getIdHoiNghi());
            }
           
        }
    }//GEN-LAST:event_jButton_signupConferActionPerformed

    private void jTable_ConferenceStatisticsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ConferenceStatisticsMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==2)
        {
        int index=jTable_ConferenceStatistics.getSelectedRow();
         DefaultTableModel defaultTableModel=(DefaultTableModel) jTable_ConferenceStatistics.getModel();
         int a=Integer.parseInt(defaultTableModel.getValueAt(index, 0).toString());
         Hoinghi hn=HoiNghiDAO.findInforHoinghi(a);
         setKeyConference(hn.getIdHoiNghi());
         jLabel_DetailTen.setText(hn.getTen());
         jLabel_DetailMotangangon.setText(hn.getMoTaNgangon());
         jLabel_DetailNumber.setText(String.valueOf(hn.getSoNguoiThamDu()));
         jLabel_DetailPalace.setText(hn.getDiadiemtochuc().getTen());
         DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss"); 
         String strDate = dateFormat.format(hn.getThoiGian()); 
         jLabel_DetailTime.setText(strDate);
         jTextPane_DetailMotachitiet.setText(hn.getMoTaChitiet());
         ImageIcon imageIcon = new ImageIcon(new ImageIcon(this.getClass().getResource(hn.getHinhAnh())).getImage().getScaledInstance(661, 232, Image.SCALE_SMOOTH));
         jLabel_DetailImage.setIcon(imageIcon);
//         MemberList temp=MemberListsDAO.findMemberListtoID(getKeyMember(),getKeyConference());
//         if(temp==null)
//             jButton_signupConfer.setText("Đăng ký");
//         else 
          jButton_signupConfer.setText("Cancel Register");
         showMemberAttendConference(hn.getIdHoiNghi());
         
         showPanel(jPanel_DetailConference);
        }
         
    }//GEN-LAST:event_jTable_ConferenceStatisticsMouseClicked

    private void jLabel_SearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_SearchMouseClicked
        // TODO add your handling code here:
        List<Hoinghi> kq=new ArrayList<>();
        String search=jTextField_SearchStatistics.getText().trim();
        kq=HoiNghiBus.seachAttendeesListConferenceMember(getKeyMember(),search);
        DefaultTableModel tbStatistics=(DefaultTableModel) jTable_ConferenceStatistics.getModel();
            tbStatistics.setRowCount(0);
            for(int i=0;i<kq.size();i++)
            {
             Hoinghi temp=kq.get(i);
             List<String> list=new ArrayList<>();
             list.add(String.valueOf(temp.getIdHoiNghi()));
             list.add(temp.getTen());
             list.add(temp.getMoTaNgangon());
             DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss"); 
             String strDate = dateFormat.format(temp.getThoiGian());          
             list.add(String.valueOf(strDate));
             Diadiemtochuc dd=temp.getDiadiemtochuc();
             list.add(String.valueOf(dd.getDiaChi()));
             list.add(String.valueOf(temp.getSoNguoiThamDu()));
             MemberList mbl=MemberListsDAO.findMemberListtoID(getKeyMember(), temp.getIdHoiNghi());
             if(mbl.getConfirm()==0)
                 list.add("N/A");
             else if(mbl.getConfirm()==1)
                 list.add("Confirm");
             tbStatistics.addRow(list.toArray());
             }
         
    }//GEN-LAST:event_jLabel_SearchMouseClicked

    private void jTextField_SearchStatisticsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_SearchStatisticsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_SearchStatisticsActionPerformed
    void showComboboxDiadiem(){
         List<Diadiemtochuc> dd=DiaDiemToChucDAO.findAll();
         for(int i=0;i<dd.size();i++)
         {
             Diadiemtochuc temp=dd.get(i);
             jComboBox_Chondiadiem.addItem(temp.getTen());
         }
    }
    private void jTable_ListConManagerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ListConManagerMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==1)
        {
         int index=jTable_ListConManager.getSelectedRow();
         DefaultTableModel defaultTableModel=(DefaultTableModel) jTable_ListConManager.getModel();
         int a=Integer.parseInt(defaultTableModel.getValueAt(index, 0).toString());
         Hoinghi hn=HoiNghiDAO.findInforHoinghi(a);
         setKeyConference(hn.getIdHoiNghi());
         jTextField_NameManage.setText(hn.getTen());
         jTextField_NganGonManage.setText(hn.getMoTaNgangon());
         jTextPane_ChitietManage.setText(hn.getMoTaChitiet());
         jTextField_PathImage.setText(hn.getHinhAnh());
         jTextField_TimeManage.setText(String.valueOf(hn.getThoiGian()));
         jTextField_number.setText(String.valueOf(hn.getSoNguoiThamDu()));
//         List<Diadiemtochuc> dd=DiaDiemToChucDAO.findAll();
//         for(int i=0;i<dd.size();i++)
//         {
//             Diadiemtochuc temp=dd.get(i);
//             jComboBox_Chondiadiem.addItem(temp.getTen());
//         }
         
         jComboBox_Chondiadiem.setSelectedItem(hn.getDiadiemtochuc().getTen());
         showMemberAttendConferManagement(a);
        }
        if(evt.getClickCount()==2)
        {
        int index=jTable_ListConManager.getSelectedRow();
         DefaultTableModel defaultTableModel=(DefaultTableModel) jTable_ListConManager.getModel();
         int a=Integer.parseInt(defaultTableModel.getValueAt(index, 0).toString());
         Hoinghi hn=HoiNghiDAO.findInforHoinghi(a);
         setKeyConference(hn.getIdHoiNghi());
         jLabel_DetailTen.setText(hn.getTen());
         jLabel_DetailMotangangon.setText(hn.getMoTaNgangon());
         jLabel_DetailNumber.setText(String.valueOf(hn.getSoNguoiThamDu()));
         jLabel_DetailPalace.setText(hn.getDiadiemtochuc().getTen());
         DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss"); 
         String strDate = dateFormat.format(hn.getThoiGian()); 
         jLabel_DetailTime.setText(strDate);
         jTextPane_DetailMotachitiet.setText(hn.getMoTaChitiet());
         ImageIcon imageIcon = new ImageIcon(new ImageIcon(this.getClass().getResource(hn.getHinhAnh())).getImage().getScaledInstance(661, 232, Image.SCALE_SMOOTH));
         jLabel_DetailImage.setIcon(imageIcon);
//         MemberList temp=MemberListsDAO.findMemberListtoID(getKeyMember(),getKeyConference());
//         if(temp==null)
//             jButton_signupConfer.setText("Đăng ký");
//         else 
         jButton_signupConfer.setVisible(false);
         showMemberAttendConference(hn.getIdHoiNghi());
         
         showPanel(jPanel_DetailConference);
        }
    }//GEN-LAST:event_jTable_ListConManagerMouseClicked

    private void jButton_ThemHoinghiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ThemHoinghiActionPerformed
        // TODO add your handling code here:
        Hoinghi hn=new Hoinghi();
        hn.setTen(jTextField_NameManage.getText());
        hn.setMoTaChitiet(jTextPane_ChitietManage.getText());
        hn.setMoTaNgangon(jTextField_NganGonManage.getText());
        hn.setHinhAnh(jTextField_PathImage.getText());
        String chonDd=(String)jComboBox_Chondiadiem.getSelectedItem();
 
        Diadiemtochuc dd=DiaDiemToChucDAO.findDiadiem(chonDd);
        hn.setDiadiemtochuc(dd);
        hn.setSoNguoiThamDu(Integer.valueOf(jTextField_number.getText()));
        String thoigian=jTextField_TimeManage.getText();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(thoigian);
        } catch (ParseException ex) {
            Logger.getLogger(Dashboard_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        hn.setThoiGian(date);
        if(HoiNghiBus.tochucHoinghi(hn))
        {
        boolean kq=HoiNghiDAO.themHoiNghi(hn);
        if(kq)
        {
            JOptionPane.showMessageDialog(null, "Add conference successfully!");
            showListConferenceManager();
            showListConference();
            showCardConference();
            
        }
        else    System.out.println("Fail");
        }else JOptionPane.showMessageDialog(null, "The conference you want to add has the same time and place.");
        
    }//GEN-LAST:event_jButton_ThemHoinghiActionPerformed

    private void jTextField_NganGonManageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_NganGonManageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_NganGonManageActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       int index=jTable_ListConManager.getSelectedRow();
       if(index>-1)
       {
        Hoinghi hn=HoiNghiDAO.findInforHoinghi(getKeyConference());
        hn.setTen(jTextField_NameManage.getText());
        hn.setMoTaChitiet(jTextPane_ChitietManage.getText());
        hn.setMoTaNgangon(jTextField_NganGonManage.getText());
        hn.setHinhAnh(jTextField_PathImage.getText());
        String chonDd=(String)jComboBox_Chondiadiem.getSelectedItem();
 
        Diadiemtochuc dd=DiaDiemToChucDAO.findDiadiem(chonDd);
        hn.setDiadiemtochuc(dd);
        hn.setSoNguoiThamDu(Integer.valueOf(jTextField_number.getText()));
        String thoigian=jTextField_TimeManage.getText();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(thoigian);
        } catch (ParseException ex) {
            Logger.getLogger(Dashboard_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        hn.setThoiGian(date);
        if(HoiNghiBus.tochucHoinghi(hn))
        {
        boolean kq=HoiNghiDAO.updateConference(hn);
        if(kq)
        {
            JOptionPane.showMessageDialog(null, "Update conference successfully!");
            showListConferenceManager();
            showListConference();
            showCardConference();
        
        }
        else    System.out.println("false");
        }else JOptionPane.showMessageDialog(null, "The conference you want to update has the same time and place.");
       }else JOptionPane.showMessageDialog(null, "Please select the conference to update!");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton_DuyetMemberAttendConferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DuyetMemberAttendConferActionPerformed
        // TODO add your handling code here:
       int index=jTable_ListConManager.getSelectedRow();
        List<Integer> fail=new ArrayList<>();
       if(index>-1)
       {
//        boolean kq = false;   
        DefaultTableModel defaultTableModel=(DefaultTableModel) jTable_MemberAttendManage.getModel();
        for(int i=0;i<defaultTableModel.getRowCount();i++){
                    int a=Integer.parseInt(defaultTableModel.getValueAt(i, 0).toString());
                    MemberList mbl=MemberListsDAO.findMemberListtoID(a, getKeyConference());
                      
                    String confirm=(defaultTableModel.getValueAt(i, 3).toString());
                   
                    String b="N/A";
                    String c="Confirm";
                    if( confirm.equals(b))
                    {           
                                mbl.setConfirm(0);
                                
                    }
                    else if(confirm.equals(c))
                    {
                                mbl.setConfirm(1);
                              
                    }else
                    {
                        fail.add(mbl.getMember().getIdMember());
                    }
                    boolean temp=MemberListsDAO.updateMemberlist(mbl);
        }
                JOptionPane.showMessageDialog(null, "Approve the conference registrant successfully!");
                if(fail.isEmpty()==false)
                {
                JOptionPane.showMessageDialog(null, "But failed in idmember: "+fail);
                }
                showMemberAttendConferManagement(getKeyConference());
                      
//                if(kq) 
//                     {
//                        JOptionPane.showMessageDialog(null, "Update Success");
//                        showListConferenceStatistics();
//                    }
//                    else JOptionPane.showMessageDialog(null, "Update Failed");
       }else JOptionPane.showMessageDialog(null, "Please select the member to approve!");
        
    }//GEN-LAST:event_jButton_DuyetMemberAttendConferActionPerformed

    private void jButton_NganChanMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_NganChanMemberActionPerformed
        // TODO add your handling code here:
       int index=jTable_UserManage.getSelectedRow();
       List<Integer> fail=new ArrayList<>();
       if(index>-1)
       {
        
        DefaultTableModel defaultTableModel=(DefaultTableModel) jTable_UserManage.getModel();
        for(int i=0;i<defaultTableModel.getRowCount();i++){
                    int a=Integer.parseInt(defaultTableModel.getValueAt(i, 0).toString());
                    Member mb=MemberDao.findInforMember(a);
                    String active=(defaultTableModel.getValueAt(i, 4).toString());
                    String b="active";
                    String c="inactive";
                    
                    if( active.equals(b))
                    {           
                                mb.setActive(1);
                          
                    }
                    else if(active.equals(c))
                    {
                                mb.setActive(0);
                              
                              
                    }else
                    {
                        fail.add(mb.getIdMember());
                        
                    }
                    boolean temp=MemberDao.updateMember(mb);
                    
                    
                }
               
                JOptionPane.showMessageDialog(null, "Update member successfully!");
                if(fail.isEmpty()==false)
                {
                JOptionPane.showMessageDialog(null, "But failed idmember: "+fail);
                }
                showMemberManage();
//                if(kq) 
//                     {
//                        JOptionPane.showMessageDialog(null, "Update Success");
//                        showListConferenceStatistics();
//                    }
//                    else JOptionPane.showMessageDialog(null, "Update Failed");
       }else JOptionPane.showMessageDialog(null, "Please select the member to update!");
      
    }//GEN-LAST:event_jButton_NganChanMemberActionPerformed

    private void jComboBox_SortMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_SortMemberActionPerformed
        // TODO add your handling code here:
       int index=jComboBox_SortMember.getSelectedIndex();
       if(index>0)
       {
           List<Member> members=new ArrayList<>();
           if(index==1)
           {
               members=MemberBus.sortIdMembers();
           }else if(index==2)
           {
               members=MemberBus.sortNameMembers();
           }
       
        DefaultTableModel defaultTableModel=(DefaultTableModel) jTable_UserManage.getModel();
        defaultTableModel.setRowCount(0);
        for(int i=0;i<members.size();i++)
        {
            Member temp=members.get(i);
            if(temp.getVaiTro()==0)
            {
            List<String> list=new ArrayList<>();
            list.add(String.valueOf(temp.getIdMember()));
            list.add(temp.getTen());
            list.add(temp.getUserName());
            list.add(temp.getEmail());
            if(temp.getActive()==1)
            {
                list.add("active");
            }
            else if(temp.getActive()==0)
            {
                list.add("inactive");
            }
            defaultTableModel.addRow(list.toArray());
            }
        }
       }
    }//GEN-LAST:event_jComboBox_SortMemberActionPerformed

    private void jComboBox_FilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_FilterActionPerformed
        // TODO add your handling code here:
        int index=jComboBox_Filter.getSelectedIndex();
        if(index>0)
        {
            
        List<Member> members=new ArrayList<>();
            switch (index) {
                case 1:
                    String name=jTextField1.getText();
                    members=MemberBus.filterNameMembers(name);
                    break;
                case 2:
                    members=MemberBus.filterActiveMembers();
                    break;
                case 3:
                    members=MemberBus.filterInactiveMembers();
                    break;
                default:
                    break;
            }
       
        DefaultTableModel defaultTableModel=(DefaultTableModel) jTable_UserManage.getModel();
        defaultTableModel.setRowCount(0);
        for(int i=0;i<members.size();i++)
        {
            Member temp=members.get(i);
            if(temp.getVaiTro()==0)
            {
            List<String> list=new ArrayList<>();
            list.add(String.valueOf(temp.getIdMember()));
            list.add(temp.getTen());
            list.add(temp.getUserName());
            list.add(temp.getEmail());
            if(temp.getActive()==1)
            {
                list.add("active");
            }
            else if(temp.getActive()==0)
            {
                list.add("inactive");
            }
            defaultTableModel.addRow(list.toArray());
            }
        }
       }
    }//GEN-LAST:event_jComboBox_FilterActionPerformed

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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton_ChangePass;
    private javax.swing.JButton jButton_DuyetMemberAttendConfer;
    private javax.swing.JButton jButton_NganChanMember;
    private javax.swing.JButton jButton_Signin;
    private javax.swing.JButton jButton_Signin1;
    private javax.swing.JButton jButton_Signup;
    private javax.swing.JButton jButton_ThemHoinghi;
    private javax.swing.JButton jButton_signupConfer;
    private javax.swing.JComboBox<String> jComboBox_Chondiadiem;
    private javax.swing.JComboBox<String> jComboBox_Filter;
    private javax.swing.JComboBox<String> jComboBox_Sort;
    private javax.swing.JComboBox<String> jComboBox_SortMember;
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
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_DetailImage;
    private javax.swing.JLabel jLabel_DetailMotangangon;
    private javax.swing.JLabel jLabel_DetailNumber;
    private javax.swing.JLabel jLabel_DetailPalace;
    private javax.swing.JLabel jLabel_DetailTen;
    private javax.swing.JLabel jLabel_DetailTime;
    private javax.swing.JLabel jLabel_ProfileName;
    private javax.swing.JLabel jLabel_ProfileUsername;
    private javax.swing.JLabel jLabel_Search;
    private javax.swing.JLabel jLabel_SignIn;
    private javax.swing.JLabel jLabel_Signup;
    private javax.swing.JLabel jLabel_Switch;
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
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel_ConManager;
    private javax.swing.JPanel jPanel_DetailConference;
    private javax.swing.JPanel jPanel_Home;
    private javax.swing.JPanel jPanel_ListCon;
    private javax.swing.JPanel jPanel_ListCon1;
    private javax.swing.JPanel jPanel_ListConference;
    private javax.swing.JPanel jPanel_Profile;
    private javax.swing.JPanel jPanel_SignIn;
    private javax.swing.JPanel jPanel_SignUp;
    private javax.swing.JPanel jPanel_Statistics;
    private javax.swing.JPanel jPanel_UserManager;
    private javax.swing.JPanel jPanel_container;
    private javax.swing.JPanel jPanel_logoANDname;
    private javax.swing.JPanel jPanel_menu;
    private javax.swing.JPasswordField jPasswordField_ConNewPass;
    private javax.swing.JPasswordField jPasswordField_CurrentPass;
    private javax.swing.JPasswordField jPasswordField_NewPass;
    private javax.swing.JPasswordField jPasswordField_Signin;
    private javax.swing.JPasswordField jPassword_RetypeSignup;
    private javax.swing.JPasswordField jPassword_SignUp;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane_Card;
    private javax.swing.JScrollPane jScrollPane_List;
    private javax.swing.JTable jTable_ConferenceStatistics;
    private javax.swing.JTable jTable_ListConManager;
    private javax.swing.JTable jTable_MemberAttendConfer;
    private javax.swing.JTable jTable_MemberAttendManage;
    private javax.swing.JTable jTable_UserManage;
    private javax.swing.JTextField jTextCountNumberDetail;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField_EmailProfile;
    private javax.swing.JTextField jTextField_EmailSignup;
    private javax.swing.JTextField jTextField_NameManage;
    private javax.swing.JTextField jTextField_NameSignup;
    private javax.swing.JTextField jTextField_NganGonManage;
    private javax.swing.JTextField jTextField_PathImage;
    private javax.swing.JTextField jTextField_ProfileName;
    private javax.swing.JTextField jTextField_SearchStatistics;
    private javax.swing.JTextField jTextField_Signin;
    private javax.swing.JTextField jTextField_TimeManage;
    private javax.swing.JTextField jTextField_UsernameSignup;
    private javax.swing.JTextField jTextField_number;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane_ChitietManage;
    private javax.swing.JTextPane jTextPane_DetailMotachitiet;
    // End of variables declaration//GEN-END:variables
}
