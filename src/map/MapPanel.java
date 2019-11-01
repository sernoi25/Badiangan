/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import beneficiary.map.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

/**
 *
 * @author John Rey Alipe
 */
public class MapPanel extends javax.swing.JPanel{

    /**
     * Creates new form MapPanel
     */
    public MapPanel() {
        initComponents();
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
        beneCHB = new javax.swing.JCheckBox();
        evacCHB = new javax.swing.JCheckBox();
        hazardCHB = new javax.swing.JCheckBox();
        disasterCHB = new javax.swing.JCheckBox();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(200, 100));

        beneCHB.setText("Beneficiaries");

        evacCHB.setText("Evacuation Sites");

        hazardCHB.setText("Hazards");

        disasterCHB.setText("Disasters");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(beneCHB)
                    .addComponent(evacCHB)
                    .addComponent(hazardCHB)
                    .addComponent(disasterCHB))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(beneCHB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(evacCHB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hazardCHB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(disasterCHB)
                .addContainerGap(408, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.LINE_END);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JCheckBox beneCHB;
    public javax.swing.JCheckBox disasterCHB;
    public javax.swing.JCheckBox evacCHB;
    public javax.swing.JCheckBox hazardCHB;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}