/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guibuilder;

import com.leapmotion.leap.Controller;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JPanel;

/**
 *
 * @author Matthieu Blais
 */
public class Corps extends javax.swing.JFrame{
   
private Controller controller;
    private LeapListener listener;
    /**
     * Creates new customizer Corps
     */
    public Corps(Controller controller,  LeapListener listener) {
        initComponents();
        this.controller=controller;
        this.listener=listener;
    }
    

    
      /**
     * @param args the command line arguments
     */
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        jButton3.setText("jButton3");

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(582, 394));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 100, 100));

        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 70, 50));

        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 110, 90));

        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 320, 120, 30));

        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 70, 50));

        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, 70, 40));

        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, 80, 30));

        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 200, 100, 120));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guibuilder/newpackage/homme2.jpg"))); // NOI18N
        jLabel8.setText("jLabel8");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 270, -1));

        jButton4.setBackground(new java.awt.Color(0, 0, 0));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 153, 153));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guibuilder/newpackage/MENU button.jpg"))); // NOI18N
        jButton4.setOpaque(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, -1, -1));

        add(jPanel1, java.awt.BorderLayout.PAGE_START);
        jPanel1.getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        Categories1 categories = new Categories1(controller, listener,"Arm");
        //identification.setVisible(true);
        jPanel1.removeAll();
        jPanel1.setLayout(new BorderLayout());
        JPanel PanelMenu = categories.getPanel();
        jPanel1.add(PanelMenu);
        jPanel1.validate();
        jPanel1.repaint();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
         Categories1 categories = new Categories1(controller, listener,"Head");
        //identification.setVisible(true);
        jPanel1.removeAll();
        jPanel1.setLayout(new BorderLayout());
        JPanel PanelMenu = categories.getPanel();
        jPanel1.add(PanelMenu);
        jPanel1.validate();
        jPanel1.repaint();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        Categories1 categories = new Categories1(controller, listener,"Arm");
        //identification.setVisible(true);
        jPanel1.removeAll();
        jPanel1.setLayout(new BorderLayout());
        JPanel PanelMenu = categories.getPanel();
        jPanel1.add(PanelMenu);
        jPanel1.validate();
        jPanel1.repaint();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        Categories1 categories = new Categories1(controller, listener,"Lung");
        //identification.setVisible(true);
        jPanel1.removeAll();
        jPanel1.setLayout(new BorderLayout());
        JPanel PanelMenu = categories.getPanel();
        jPanel1.add(PanelMenu);
        jPanel1.validate();
        jPanel1.repaint();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        Categories1 categories = new Categories1(controller, listener,"Stomach");
        //identification.setVisible(true);
        jPanel1.removeAll();
        jPanel1.setLayout(new BorderLayout());
        JPanel PanelMenu = categories.getPanel();
        jPanel1.add(PanelMenu);
        jPanel1.validate();
        jPanel1.repaint();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        Categories1 categories = new Categories1(controller, listener,"Genital");
        //identification.setVisible(true);
        jPanel1.removeAll();
        jPanel1.setLayout(new BorderLayout());
        JPanel PanelMenu = categories.getPanel();
        jPanel1.add(PanelMenu);
        jPanel1.validate();
        jPanel1.repaint();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        Categories1 categories = new Categories1(controller, listener,"Legs");
        //identification.setVisible(true);
        jPanel1.removeAll();
        jPanel1.setLayout(new BorderLayout());
        JPanel PanelMenu = categories.getPanel();
        jPanel1.add(PanelMenu);
        jPanel1.validate();
        jPanel1.repaint();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        Categories1 categories = new Categories1(controller, listener,"Feet");
        //identification.setVisible(true);
        jPanel1.removeAll();
        jPanel1.setLayout(new BorderLayout());
        JPanel PanelMenu = categories.getPanel();
        jPanel1.add(PanelMenu);
        jPanel1.validate();
        jPanel1.repaint();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        //RETURN TO MENU
        //...
        //this.dispose();
        Interface menu = new Interface(controller, listener);
        //identification.setVisible(true);
        jPanel1.removeAll();
        jPanel1.setLayout(new BorderLayout());
        JPanel PanelMenu = menu.getPanel();
        jPanel1.add(PanelMenu);
        jPanel1.validate();
        jPanel1.repaint();
    }//GEN-LAST:event_jButton4ActionPerformed


    public JPanel getPanel() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return jPanel1;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
