/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jPanel;

import DAO.HoiNghiDAO;
import POJOs.Hoinghi;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Tran Huynh
 */
public class JpanelCardConference extends javax.swing.JPanel {

    /**
     * Creates new form JpanelCardConference
     */
    public int i;
    public void showOneConference(){
        
        Hoinghi hn=HoiNghiDAO.findInforHoinghi(i);
        jLabel_Ten.setText(hn.getTen());
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(this.getClass().getResource(hn.getHinhAnh())).getImage().getScaledInstance(305, 105, Image.SCALE_SMOOTH));
        jLabel_HinhAnh.setIcon(imageIcon);
        jPanel1.setVisible(true);
    
        
    }
    public JpanelCardConference(int key) {
        initComponents();
        setI(key);
        showOneConference();
    }
    
    public void setI(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel_HinhAnh = new javax.swing.JLabel();
        jLabel_Ten = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(305, 145));
        setMinimumSize(new java.awt.Dimension(305, 145));
        setPreferredSize(new java.awt.Dimension(305, 145));

        jPanel1.setBackground(new java.awt.Color(44, 62, 60));
        jPanel1.setMaximumSize(new java.awt.Dimension(305, 145));
        jPanel1.setMinimumSize(new java.awt.Dimension(305, 145));
        jPanel1.setPreferredSize(new java.awt.Dimension(305, 145));

        jLabel_HinhAnh.setMaximumSize(new java.awt.Dimension(305, 105));
        jLabel_HinhAnh.setMinimumSize(new java.awt.Dimension(305, 105));
        jLabel_HinhAnh.setPreferredSize(new java.awt.Dimension(305, 105));

        jLabel_Ten.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel_Ten.setForeground(new java.awt.Color(228, 241, 254));
        jLabel_Ten.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Ten.setMaximumSize(new java.awt.Dimension(305, 40));
        jLabel_Ten.setMinimumSize(new java.awt.Dimension(305, 40));
        jLabel_Ten.setPreferredSize(new java.awt.Dimension(305, 40));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel_Ten, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_HinhAnh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel_Ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel_HinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel_HinhAnh;
    private javax.swing.JLabel jLabel_Ten;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
