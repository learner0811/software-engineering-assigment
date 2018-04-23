/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.MenuCtr;
import Model.Mon;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ns_red
 */
public class AddDishFrm extends javax.swing.JFrame{
    ///////////////////////VARIBLE//////////////////////////////////////////////
    private ArrayList<Mon> listDishResult;
    private ArrayList<Mon> listDishQueue;
    private DefaultTableModel modelResult;
    private DefaultTableModel modelQueue;
    private AddMenuFrm parent;
    private static AddDishFrm addDishFrm;
    
    //////////////////////CONTRUCTOR////////////////////////////////////////////
    public AddDishFrm() {
        initComponents();  
        myInit();
    }
    
    //////////////////////GETTER & SETTER///////////////////////////////////////

    public ArrayList<Mon> getListDishQueue() {
        return listDishQueue;
    }

    /////////////////////FUNCTION///////////////////////////////////////////////
    public static AddDishFrm getInstace(){
        if (addDishFrm == null)
            addDishFrm = new AddDishFrm();
        return addDishFrm;
    }
    
    private void myInit(){
        modelResult = (DefaultTableModel) tblResult.getModel();
        modelQueue = (DefaultTableModel) tblQueue.getModel();
        listDishResult = new ArrayList<>();
        listDishQueue = new ArrayList<>();
        
        
    }
    
    public void passArgument(AddMenuFrm parent){
        this.parent = parent;
    }
    
    private void AddClicked(){
        //varible
        int rowIndex = tblResult.getSelectedRow();
        //add to queueList the object which is choosen
        
        //haven't selected anything yet
        if (rowIndex == -1) 
            return;
        
        //encap object
        Mon target = new Mon();
        target.setId((Integer) modelResult.getValueAt(rowIndex, 0));
        target.setTen((String) modelResult.getValueAt(rowIndex, 1));
        target.setGia((Double) modelResult.getValueAt(rowIndex, 2));
        
        //check if it contain or not, if not add to queue list
        
        //if it emty add to queue
        if (listDishQueue.isEmpty())
            listDishQueue.add(target);
        else
            if (!listDishQueue.contains(target))
                listDishQueue.add(target);
            else{
                JOptionPane.showMessageDialog(null, "Da co trong danh sach doi", "Alert", JOptionPane.WARNING_MESSAGE);
                return;
            }
        //clear the table before show new data
        modelQueue.setRowCount(0);
        
        //show the list in the table
        Object[] dataRow = new Object[3];
        for (int i = 0; i < listDishQueue.size(); i++){
            dataRow[0] = listDishQueue.get(i).getId();
            dataRow[1] = listDishQueue.get(i).getTen();
            dataRow[2] = listDishQueue.get(i).getGia();
            modelQueue.addRow(dataRow);
        }
    }
    
    private void RemoveClicked(){
        //varible
        int indexRow = tblQueue.getSelectedRow();
       
        //check if it has anything to select
        if (indexRow == -1)
            return;
        
        //remove the selected row
        listDishQueue.remove(indexRow);
       
        //show the new of list to table 
        //reset table
        modelQueue.setRowCount(0);
        
        //show again
        Object[] dataRow = new Object[3];
        for (int i = 0; i < listDishQueue.size(); i++){
            dataRow[0] = listDishQueue.get(i).getId();
            dataRow[1] = listDishQueue.get(i).getTen();
            dataRow[2] = listDishQueue.get(i).getGia();
            modelQueue.addRow(dataRow);
        }
    }
    
    private void SearchClicked(){
        //varible
        MenuCtr menuCtr = new MenuCtr();
        String keyWord = txtSearch.getText();
        
        // have no keyword
        if (keyWord.equals("")){
            JOptionPane.showMessageDialog(null, "Ban chua nhap tu khoa", "Alert", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        //clear the table if it has previous data
        modelResult.setRowCount(0);

        listDishResult = menuCtr.getDish(keyWord);
        
        //not match any dish
        if (listDishResult.isEmpty()){
            JOptionPane.showMessageDialog(null, "Khong co mon nao trung voi tu khoa", "Alert", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        //add row to table
        Object[] rowData = new Object[3];
        for (int i = 0; i < listDishResult.size(); i++){
            rowData[0] = listDishResult.get(i).getId();
            rowData[1] = listDishResult.get(i).getTen();
            rowData[2] = listDishResult.get(i).getGia();
            modelResult.addRow(rowData);
        }
    }
 
    private void ResetClicked(){
        listDishQueue.clear();
        listDishResult.clear();
        modelQueue.setRowCount(0);
        modelResult.setRowCount(0);
        txtSearch.setText("");
    }
    
    ////////////////////GENERATED CODE//////////////////////////////////////////
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblResult = new javax.swing.JTable();
        btnSearch = new javax.swing.JButton();
        btnAddDish = new javax.swing.JButton();
        btnRemoveDish = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblQueue = new javax.swing.JTable();
        btnReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("AddDish");
        setLocation(new java.awt.Point(450, 0));
        setPreferredSize(new java.awt.Dimension(590, 350));
        setResizable(false);
        setSize(new java.awt.Dimension(620, 350));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nhap ten mon");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 23, -1, -1));
        getContentPane().add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 20, 173, -1));

        tblResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Ten mon", "Gia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class
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
        jScrollPane1.setViewportView(tblResult);
        if (tblResult.getColumnModel().getColumnCount() > 0) {
            tblResult.getColumnModel().getColumn(0).setMinWidth(5);
            tblResult.getColumnModel().getColumn(0).setMaxWidth(30);
            tblResult.getColumnModel().getColumn(2).setMinWidth(10);
            tblResult.getColumnModel().getColumn(2).setMaxWidth(60);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 92, 289, 160));

        btnSearch.setText("Tim");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        getContentPane().add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(301, 19, -1, -1));

        btnAddDish.setText("Them");
        btnAddDish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDishActionPerformed(evt);
            }
        });
        getContentPane().add(btnAddDish, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, 67, -1));

        btnRemoveDish.setText("Xoa");
        btnRemoveDish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveDishActionPerformed(evt);
            }
        });
        getContentPane().add(btnRemoveDish, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 260, 60, -1));

        jLabel2.setText("Danh sach them");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, -1, -1));

        jLabel3.setText("Bang ket qua tim kiem");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(308, 60, 10, 200));

        tblQueue.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Ten mon", "Gia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class
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
        jScrollPane2.setViewportView(tblQueue);
        if (tblQueue.getColumnModel().getColumnCount() > 0) {
            tblQueue.getColumnModel().getColumn(0).setMinWidth(5);
            tblQueue.getColumnModel().getColumn(0).setMaxWidth(30);
            tblQueue.getColumnModel().getColumn(2).setMinWidth(5);
            tblQueue.getColumnModel().getColumn(2).setMaxWidth(60);
        }

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 270, 160));

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        getContentPane().add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        SearchClicked();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnAddDishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDishActionPerformed
        AddClicked();
    }//GEN-LAST:event_btnAddDishActionPerformed

    private void btnRemoveDishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveDishActionPerformed
        RemoveClicked();
    }//GEN-LAST:event_btnRemoveDishActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        ResetClicked();
    }//GEN-LAST:event_btnResetActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddDish;
    private javax.swing.JButton btnRemoveDish;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblQueue;
    private javax.swing.JTable tblResult;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

   
}
