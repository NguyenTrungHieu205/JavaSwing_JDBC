/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.DungLuong;
import repository.DungLuongRepository;
import service.DungLuongService;
import service.impl.DungLuongServiceImpl;
import viewModel.DungLuongViewModel;

/**
 *
 * @author Admin
 */
public class FormDungLuong extends javax.swing.JFrame {

    private final DungLuongService dungLuongSer = new DungLuongServiceImpl();

    public FormDungLuong() {
        initComponents();
        setLocationRelativeTo(null);
        loadTb();
        loadTbXoa();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        rdOnline.setSelected(true);
        setTitle("Dung lượng");
    }

    public void loadTb() {
        ArrayList<DungLuongViewModel> list = dungLuongSer.getAll();
        DefaultTableModel model = (DefaultTableModel) tblDungLuong.getModel();
        model.setRowCount(0);
        for (DungLuongViewModel x : list) {
            model.addRow(new Object[]{x.getMa(), x.getTen(), x.TrangThai2()});
        }
    }

    public void loadTbXoa() {
        ArrayList<DungLuongViewModel> list = dungLuongSer.getAllXoa();
        DefaultTableModel model = (DefaultTableModel) tblDungLuong1.getModel();
        model.setRowCount(0);
        for (DungLuongViewModel x : list) {
            model.addRow(new Object[]{x.getMa(), x.getTen()});
        }
    }

    private boolean validateForm() {
        if (txtMaDungLuong.getText().trim().equals("") || txtTenDungLuong.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Dữ liệu không được để trống");
            return false;
        }
        try {
            if (Integer.parseInt(txtTenDungLuong.getText().trim()) <= 0) {
                JOptionPane.showMessageDialog(this, "Dung lượng không được âm hoặc bằng 0");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Dung lượng sai định dạng");
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDungLuong = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaDungLuong = new javax.swing.JTextField();
        lblDL = new javax.swing.JLabel();
        txtTenDungLuong = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        txtMaDungLuong1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        rdOnline = new javax.swing.JRadioButton();
        rdOffline = new javax.swing.JRadioButton();
        rdNgungban = new javax.swing.JRadioButton();
        rdDangchohangve = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDungLuong1 = new javax.swing.JTable();
        btnUpdate1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(750, 400));

        jPanel2.setBackground(new java.awt.Color(158, 195, 192));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblDungLuong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã dung lượng", "Dung lượng", "Trạng Thái"
            }
        ));
        tblDungLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDungLuongMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDungLuong);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 27, 323, 210));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel1.setText("Mã dung lượng :");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, -1, -1));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel2.setText("Dung lượng :");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, -1, -1));
        jPanel2.add(txtMaDungLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 202, -1));

        lblDL.setText("GB");
        jPanel2.add(lblDL, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 110, 30, 20));
        jPanel2.add(txtTenDungLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, 202, -1));

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add (2).png"))); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel2.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, 50, -1));

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/update (2).png"))); // NOI18N
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel2.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 220, 50, -1));

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delele.png"))); // NOI18N
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel2.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 220, 50, -1));
        jPanel2.add(txtMaDungLuong1, new org.netbeans.lib.awtextra.AbsoluteConstraints(358, 27, 202, 1));

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel3.setText("Trạng thái:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 140, -1, -1));

        buttonGroup1.add(rdOnline);
        rdOnline.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdOnline.setText("Online");
        rdOnline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdOnlineActionPerformed(evt);
            }
        });
        jPanel2.add(rdOnline, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, -1, -1));

        buttonGroup1.add(rdOffline);
        rdOffline.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdOffline.setText("Offline");
        rdOffline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdOfflineActionPerformed(evt);
            }
        });
        jPanel2.add(rdOffline, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, -1, -1));

        buttonGroup1.add(rdNgungban);
        rdNgungban.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdNgungban.setText("Ngừng bán");
        rdNgungban.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNgungbanActionPerformed(evt);
            }
        });
        jPanel2.add(rdNgungban, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 190, -1, -1));

        buttonGroup1.add(rdDangchohangve);
        rdDangchohangve.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdDangchohangve.setText("Đang chờ hàng về");
        rdDangchohangve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdDangchohangveActionPerformed(evt);
            }
        });
        jPanel2.add(rdDangchohangve, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 190, -1, -1));

        jTabbedPane1.addTab("Danh sách", jPanel2);

        jPanel3.setBackground(new java.awt.Color(158, 195, 192));

        tblDungLuong1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã dung lượng", "Dung lượng"
            }
        ));
        tblDungLuong1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDungLuong1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDungLuong1);

        btnUpdate1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/update (2).png"))); // NOI18N
        btnUpdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnUpdate1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 85, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnUpdate1, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Đã Xóa", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblDungLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDungLuongMouseClicked
        int row = tblDungLuong.getSelectedRow();
        txtMaDungLuong.setText(tblDungLuong.getValueAt(row, 0).toString());
        String tenDL = tblDungLuong.getValueAt(row, 1).toString();
        String[] splitss = tenDL.split("GB$");
        StringBuilder stringBuilder = new StringBuilder();
        for (String x : splitss) {
            stringBuilder.append(x);
        }
        txtTenDungLuong.setText(stringBuilder.toString());
        if (tblDungLuong.getValueAt(row, 2).toString().equals("Online")) {
            rdOnline.setSelected(true);
        } else if (tblDungLuong.getValueAt(row, 2).toString().equals("Offline")) {
            rdOffline.setSelected(true);
        } else if (tblDungLuong.getValueAt(row, 2).toString().equals("Đang chờ hàng về")) {
            rdDangchohangve.setSelected(true);
        } else {
            rdNgungban.setSelected(true);
        }
    }//GEN-LAST:event_tblDungLuongMouseClicked
    private Integer trangThai() {
        if (rdOnline.isSelected()) {
            return 0;
        } else if (rdOffline.isSelected()) {
            return 1;
        } else if (rdDangchohangve.isSelected()) {
            return 2;
        } else {
            return 3;
        }

    }
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (validateForm()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thêm ?", "Xác nhân thêm.", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (dungLuongSer.checkTrungMa(txtMaDungLuong.getText().trim())) {
                    DungLuongViewModel dl = new DungLuongViewModel();
                    dl.setMa(txtMaDungLuong.getText().trim());
                    dl.setTen(txtTenDungLuong.getText().trim() + lblDL.getText());
                    dl.setTrangThai(this.trangThai());
                    dungLuongSer.create(dl);
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                    loadTb();
                } else {
                    JOptionPane.showMessageDialog(this, "Trùng mã dung lượng");
                }
            } else {
                return;
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (validateForm()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa ?", "Xác nhân sửa.", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                DungLuongViewModel dl = new DungLuongViewModel();
                dl.setMa(txtMaDungLuong.getText().trim());
                dl.setTen(txtTenDungLuong.getText().trim() + lblDL.getText());
                dl.setTrangThai(this.trangThai());
                dungLuongSer.update(dl);
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                loadTb();
            } else {
                return;
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (txtMaDungLuong.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Mời chọn mã dung lượng");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa ?", "Xác nhân xóa.", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            DungLuongViewModel dl = new DungLuongViewModel();
            dl.setMa(txtMaDungLuong.getText().trim());
            dungLuongSer.updateTT(dl);
            JOptionPane.showMessageDialog(this, "Xóa thành công");
            loadTb();
            loadTbXoa();
        } else {
            return;
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate1ActionPerformed
        // TODO add your handling code here:

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn hoàn tác ?", "Xác nhân hoàn tác.", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            DungLuongViewModel dl = new DungLuongViewModel();
            dl.setMa(tblDungLuong1.getValueAt(tblDungLuong1.getSelectedRow(), 0).toString());
            dungLuongSer.updateTT3(dl);
            JOptionPane.showMessageDialog(this, "Hoàn tác thành công");
            loadTb();
            loadTbXoa();
        } else {
            return;
        }
    }//GEN-LAST:event_btnUpdate1ActionPerformed

    private void tblDungLuong1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDungLuong1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDungLuong1MouseClicked

    private void rdOnlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdOnlineActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdOnlineActionPerformed

    private void rdOfflineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdOfflineActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdOfflineActionPerformed

    private void rdNgungbanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNgungbanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNgungbanActionPerformed

    private void rdDangchohangveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdDangchohangveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdDangchohangveActionPerformed

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
            java.util.logging.Logger.getLogger(FormDungLuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormDungLuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormDungLuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormDungLuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new FormDungLuong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdate1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblDL;
    private javax.swing.JRadioButton rdDangchohangve;
    private javax.swing.JRadioButton rdNgungban;
    private javax.swing.JRadioButton rdOffline;
    private javax.swing.JRadioButton rdOnline;
    private javax.swing.JTable tblDungLuong;
    private javax.swing.JTable tblDungLuong1;
    private javax.swing.JTextField txtMaDungLuong;
    private javax.swing.JTextField txtMaDungLuong1;
    private javax.swing.JTextField txtTenDungLuong;
    // End of variables declaration//GEN-END:variables
}
