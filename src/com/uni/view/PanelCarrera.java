/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uni.view;


import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author diego
 */
public class PanelCarrera extends javax.swing.JPanel {

    /**
     * Creates new form PanelCarrera
     */
    public PanelCarrera() {
        initComponents();
        insertarSubMenu(new JPanel());
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
        bt_list = new javax.swing.JButton();
        bt_edit = new javax.swing.JButton();
        bt_agregar = new javax.swing.JButton();
        bt_eliminar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_carrera = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        bt_list.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/uni/images/bt_List_default.png"))); // NOI18N
        bt_list.setBorderPainted(false);
        bt_list.setContentAreaFilled(false);
        bt_list.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_list.setSelected(true);
        bt_list.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/uni/images/bt_List_select.png"))); // NOI18N

        bt_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/uni/images/bt_Edit_default.png"))); // NOI18N
        bt_edit.setBorderPainted(false);
        bt_edit.setContentAreaFilled(false);
        bt_edit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_edit.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/uni/images/bt_Edit_select.png"))); // NOI18N

        bt_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/uni/images/bt_Agregar_default.png"))); // NOI18N
        bt_agregar.setBorderPainted(false);
        bt_agregar.setContentAreaFilled(false);
        bt_agregar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_agregar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/uni/images/bt_Agregar_select.png"))); // NOI18N

        bt_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/uni/images/bt_Edit_default.png"))); // NOI18N
        bt_eliminar.setBorderPainted(false);
        bt_eliminar.setContentAreaFilled(false);
        bt_eliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_eliminar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/uni/images/bt_Edit_select.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bt_list, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(bt_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(bt_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(bt_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(bt_list, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(234, 234, 234));

        jSplitPane1.setBorder(null);
        jSplitPane1.setDividerLocation(0);
        jSplitPane1.setDividerSize(4);
        jSplitPane1.setForeground(new java.awt.Color(102, 255, 153));

        table_carrera.setBackground(new java.awt.Color(234, 234, 234));
        table_carrera.setForeground(new java.awt.Color(51, 51, 51));
        table_carrera.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codigo", "nombre", "duración"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_carrera.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        table_carrera.setAutoscrolls(false);
        table_carrera.setOpaque(false);
        jScrollPane1.setViewportView(table_carrera);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Carreras");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 826, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE))
        );

        jSplitPane1.setRightComponent(jPanel4);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 393, Short.MAX_VALUE)
        );

        jSplitPane1.setLeftComponent(jPanel3);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public JTable getTable_carrera() {
        return table_carrera;
    }

    public JButton getBt_edit() {
        return bt_edit;
    }

    public JButton getBt_list() {
        return bt_list;
    }

    public JButton getBt_agregar() {
        return bt_agregar;
    }

    public JButton getBt_eliminar() {
        return bt_eliminar;
    }
    
    
    
    public void insertarSubMenu(JPanel panel){
        this.jSplitPane1.setLeftComponent(panel);
    } 
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_agregar;
    private javax.swing.JButton bt_edit;
    private javax.swing.JButton bt_eliminar;
    private javax.swing.JButton bt_list;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable table_carrera;
    // End of variables declaration//GEN-END:variables
}
