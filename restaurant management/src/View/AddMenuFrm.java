/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.MenuCtr;
import java.util.ArrayList;
import Model.Mon;
import Model.ThucDon;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ns_red
 */
public class AddMenuFrm extends javax.swing.JFrame implements WindowListener{
    //varible
    private ThucDon menu;
    private ArrayList<Mon> listDish;
    private static AddMenuFrm addMenuFrm;
    private DefaultTableModel model;
    private AddDishFrm addDishFrm;
    
    public AddMenuFrm() {
        initComponents();
        mySetting();
    }
    
    public static AddMenuFrm getInstance(){
        if (addMenuFrm == null)
            addMenuFrm = new AddMenuFrm();
        return addMenuFrm;
    }
    
    public void mySetting(){
        model = (DefaultTableModel) tblListDish.getModel();
        menu = new ThucDon();
        listDish = new ArrayList<>();
        this.addWindowListener(this);
    }
    
    //My function
    private void btnSaveMenuClicked(){
        //varible
        String name = txtMenuName.getText();
        menu = new ThucDon();
        double totalMoney = 0;
        MenuCtr menuCtr = new MenuCtr();
        
        //textField is blank
        if (name.equals("")){
            JOptionPane.showMessageDialog(this, "Ban chua nhap ten thuc don", "Alert", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        //listDish is empty
        if (listDish.isEmpty()){
            JOptionPane.showMessageDialog(this, "Danh sach mon trong", "Alert", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        
        //calculate the total money
        if (txtTotalMoney.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Tong tien trong", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try{
            totalMoney = Double.parseDouble(txtTotalMoney.getText());
        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "Tong tien khong hop le", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        
        //encap object
        menu.setTen(name);
        menu.setTongTien(totalMoney);
        menu.setDanhSachMon(listDish);
        
        //save to database
        boolean flag = menuCtr.SaveMenu(menu);
        
        if (flag == false){
            JOptionPane.showMessageDialog(this, "Ten thuc don bi trung", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
            
        JOptionPane.showMessageDialog(this, "Luu thanh cong");
    }
    private void btnNewDishClicked(){
        addDishFrm = AddDishFrm.getInstace();
        addDishFrm.passArgument(this);
        addDishFrm.setVisible(true);
    }
    private void btnRemoveDishClicked(){
         //varible
        int indexRow = tblListDish.getSelectedRow();
       
        //check if it has anything to select
        if (indexRow == -1)
            return;
        
        //remove the selected row
        listDish.remove(indexRow);
       
        //show the new of list to table 
        //reset table
        model.setRowCount(0);
        
        //show again
        Object[] dataRow = new Object[3];
        for (int i = 0; i < listDish.size(); i++){
            dataRow[0] = listDish.get(i).getId();
            dataRow[1] = listDish.get(i).getTen();
            dataRow[2] = listDish.get(i).getGia();
            model.addRow(dataRow);
        }
    }
    private void btnUpdateClicked(){
        ArrayList<Mon> temp = AddDishFrm.getInstace().getListDishQueue();
        
        //check listDishQueue is null
        if (temp == null)
            return;
        
        //check listDishQueue is empty or not
        if (temp.isEmpty())            
            return;
            
        //add listDishQueue to listDish
        for (int i = 0; i < temp.size(); i++){
            Mon target = temp.get(i);
            if (!listDish.contains(target)){
                listDish.add(target);
                System.out.println(target.getTen());
            }
        }            
        
        //clear the previous data in table
        model.setRowCount(0);
        
        //update table
        Object[] dataRow = new Object[3];
        for (int i = 0; i < listDish.size(); i++){
            dataRow[0] = listDish.get(i).getId();
            dataRow[1] = listDish.get(i).getTen();
            dataRow[2] = listDish.get(i).getGia();
            model.addRow(dataRow);
        }
        
        //calculate the toltal money and show to textField
        double totalMoney = 0;
        for (Mon a : listDish)
            totalMoney += a.getGia();
        String s = (long) totalMoney == totalMoney ? "" + (long) totalMoney : "" + totalMoney;
        txtTotalMoney.setText(s);
    }
   
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMenuName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListDish = new javax.swing.JTable();
        btnAddDish = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtTotalMoney = new javax.swing.JTextField();
        btnSaveMenu = new javax.swing.JButton();
        btnRemoveDish = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Add Menu");
        setLocation(new java.awt.Point(0, 350));
        setResizable(false);

        jLabel1.setText("Nhap ten thuc don");

        jLabel2.setText("Danh sach mon trong thuc don");

        tblListDish.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblListDish);
        if (tblListDish.getColumnModel().getColumnCount() > 0) {
            tblListDish.getColumnModel().getColumn(0).setMinWidth(5);
            tblListDish.getColumnModel().getColumn(0).setMaxWidth(30);
            tblListDish.getColumnModel().getColumn(2).setMinWidth(5);
            tblListDish.getColumnModel().getColumn(2).setMaxWidth(60);
        }

        btnAddDish.setText("Them mon");
        btnAddDish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDishActionPerformed(evt);
            }
        });

        jLabel3.setText("Tong tien");

        btnSaveMenu.setText("Luu thuc don");
        btnSaveMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveMenuActionPerformed(evt);
            }
        });

        btnRemoveDish.setText("Xoa mon");
        btnRemoveDish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveDishActionPerformed(evt);
            }
        });

        btnUpdate.setText("Cap nhat");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(40, 40, 40)
                        .addComponent(txtMenuName, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(txtTotalMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(btnAddDish)
                .addGap(37, 37, 37)
                .addComponent(btnRemoveDish, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnSaveMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUpdate)
                .addGap(38, 38, 38))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMenuName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(txtTotalMoney, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddDish)
                    .addComponent(btnRemoveDish)
                    .addComponent(btnSaveMenu)
                    .addComponent(btnUpdate))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Menu", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddDishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDishActionPerformed
        btnNewDishClicked();
    }//GEN-LAST:event_btnAddDishActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        btnUpdateClicked();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnRemoveDishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveDishActionPerformed
        btnRemoveDishClicked();
    }//GEN-LAST:event_btnRemoveDishActionPerformed

    private void btnSaveMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveMenuActionPerformed
        btnSaveMenuClicked();
    }//GEN-LAST:event_btnSaveMenuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddDish;
    private javax.swing.JButton btnRemoveDish;
    private javax.swing.JButton btnSaveMenu;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable tblListDish;
    private javax.swing.JTextField txtMenuName;
    private javax.swing.JTextField txtTotalMoney;
    // End of variables declaration//GEN-END:variables

    @Override
    public void windowOpened(WindowEvent we) {

    }

    @Override
    public void windowClosing(WindowEvent we) {
        int confirm = JOptionPane.showConfirmDialog(null, "Ban co muon thoat");
        if (confirm == JOptionPane.YES_OPTION){
            if (addDishFrm != null){
                addDishFrm.dispose();
            }
            this.dispose();
            addMenuFrm = null;
        }
    }

    @Override
    public void windowClosed(WindowEvent we) {
        
    }

    @Override
    public void windowIconified(WindowEvent we) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
        
    }

    @Override
    public void windowActivated(WindowEvent we) {
        
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
        
    }
}
