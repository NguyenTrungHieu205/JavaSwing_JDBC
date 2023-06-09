/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.math.BigDecimal;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import service.QuyDoiDiemService;
import service.impl.QuyDoiDiemServiceImpl;
import viewModel.QuyDoiDiemViewModel;

/**
 *
 * @author Dell
 */
public class FormQuyDoiDiem extends javax.swing.JFrame {

    private QuyDoiDiemService quyDoiSer = new QuyDoiDiemServiceImpl();

    public static BigDecimal tichDiem = null;
    public static BigDecimal tieuDiem = null;

    public FormQuyDoiDiem() {
        initComponents();
        setLocationRelativeTo(null);
        loadTf();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public String getTienTichDiem() {

        return txtTienTichDiem.getText().trim();
    }

    public String getTienTieuDiem() {

        return txtTienTieuDiem.getText().trim();
    }

    public void loadTf() {
        for (QuyDoiDiemViewModel x : quyDoiSer.getAll()) {
            txtTienTichDiem.setText(String.valueOf(x.getTienTichDiem()));
            String[] splits = txtTienTichDiem.getText().split(".0000$");
            StringBuilder stringBuilder1 = new StringBuilder();
            for (String x1 : splits) {
                stringBuilder1.append(x1);
            }
            txtTienTichDiem.setText(stringBuilder1.toString());

            txtTienTieuDiem.setText(String.valueOf(x.getTienTieuDiem()));
            String[] splitss = txtTienTieuDiem.getText().split(".0000$");
            StringBuilder stringBuilder11 = new StringBuilder();
            for (String x2 : splitss) {
                stringBuilder11.append(x2);
            }
            txtTienTieuDiem.setText(stringBuilder11.toString());
        }
    }

    private boolean validateForm() {
        try {
            if (txtTienTichDiem.getText().trim().equals("") || txtTienTieuDiem.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Dữ liệu không được để trống");
                return false;
            }
            if (Double.parseDouble(txtTienTichDiem.getText().trim()) <= 0) {
                JOptionPane.showMessageDialog(this, "Tiền tích điểm phải lớn hơn 0");
                return false;
            }
            if (Double.parseDouble(txtTienTieuDiem.getText().trim()) <= 0) {
                JOptionPane.showMessageDialog(this, "Tiền tiêu điểm phải lớn hơn 0");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sai định dạng");
            return false;
        }
        return true;
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTienTichDiem = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTienTieuDiem = new javax.swing.JTextField();
        btnQuyDoi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(158, 195, 192));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(158, 195, 192));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUY ĐỔI ĐIỂM");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 21, 448, -1));

        jLabel2.setText("Tiền tích điểm :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 74, 87, -1));

        jLabel3.setText("Tiền tiêu điểm :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 87, 30));

        jLabel4.setText("VNĐ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 56, 37, 40));
        jPanel1.add(txtTienTichDiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 63, 150, 30));

        jLabel5.setText(" 1 điểm =");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 60, 30));

        jLabel6.setText("= 1 điểm");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 60, -1));

        jLabel7.setText("VNĐ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 37, 30));
        jPanel1.add(txtTienTieuDiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 150, 30));

        btnQuyDoi.setText("Quy Đổi");
        btnQuyDoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuyDoiActionPerformed(evt);
            }
        });
        jPanel1.add(btnQuyDoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 172, 130, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnQuyDoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuyDoiActionPerformed
        // TODO add your handling code here:
        if (validateForm()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn quy đổi ?", "Xác nhân quy đổi.", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                BigDecimal tienTichDiem = BigDecimal.valueOf(Long.valueOf(txtTienTichDiem.getText().trim()));
                BigDecimal tienTieuDiem = BigDecimal.valueOf(Long.valueOf(txtTienTieuDiem.getText().trim()));
                quyDoiSer.updateQuyDoi(tienTichDiem, tienTieuDiem);
                tichDiem = BigDecimal.valueOf(Long.valueOf(txtTienTichDiem.getText().trim()));
                tieuDiem = BigDecimal.valueOf(Long.valueOf(txtTienTieuDiem.getText().trim()));
                JOptionPane.showMessageDialog(this, "Quy đổi thành công");
            } else {
                return;
            }
        }
    }//GEN-LAST:event_btnQuyDoiActionPerformed

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
            java.util.logging.Logger.getLogger(FormQuyDoiDiem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormQuyDoiDiem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormQuyDoiDiem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormQuyDoiDiem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormQuyDoiDiem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnQuyDoi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtTienTichDiem;
    private javax.swing.JTextField txtTienTieuDiem;
    // End of variables declaration//GEN-END:variables
}
