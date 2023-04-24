/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.GiamGia;
import model.GiamGiaSp;
import service.GiamGiaSPService;
import service.GiamGiaService;
import service.SanPhamService;
import service.impl.GiamGiaSPImpl;
import service.impl.GiamGiaServiceImpl;
import service.impl.SanPhamServiceImpl;
import viewModel.GiamGiaViewModel;
import viewModel.ViewSanPham;

/**
 *
 * @author phamtuyetnga
 */
public class FormGiamGia extends javax.swing.JPanel {

    public FormGiamGia() {
        initComponents();
        loadTable();
        loadtableKM();
        rdoOnline.setSelected(true);
        cbNgayBD.getSettings().setAllowKeyboardEditing(false);
        cbNgayKT.getSettings().setAllowKeyboardEditing(false);
    }

    private final GiamGiaSPService ggsp = new GiamGiaSPImpl();
    private final GiamGiaService gg = new GiamGiaServiceImpl();
    private final SanPhamService sps = new SanPhamServiceImpl();

//    @SuppressWarnings("unchecked");
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTaoCTKM = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMaKM = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        txtMucGiamGia = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLoadLuaChon = new javax.swing.JTable();
        cbxChonAll = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbNgayKT = new com.github.lgooddatepicker.components.DatePicker();
        btnLuu = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        rdoOnline = new javax.swing.JRadioButton();
        rdoOffline = new javax.swing.JRadioButton();
        btnThemVaoSp = new javax.swing.JButton();
        cbNgayBD = new com.github.lgooddatepicker.components.DatePicker();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDanhSachKM = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(158, 195, 192));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("GIẢM GIÁ");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chương trình giảm giá", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 1, 17))); // NOI18N

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("Tạo mã KM");
        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N

        jLabel3.setText("Tạo chương trình KM");
        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N

        jLabel4.setText("Hình thức giảm giá");
        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N

        jLabel5.setText("Mức giảm giá");
        jLabel5.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giảm giá theo tiền" }));

        tblLoadLuaChon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Lựa chọn", "Mã sản phẩm", "Tên sản phẩm"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblLoadLuaChon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLoadLuaChonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblLoadLuaChon);

        cbxChonAll.setText("Chọn tất cả");
        cbxChonAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxChonAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(227, 227, 227)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaKM)
                                    .addComponent(txtTaoCTKM)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(110, 110, 110)
                                .addComponent(txtMucGiamGia)))
                        .addContainerGap())))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(cbxChonAll))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTaoCTKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMucGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbxChonAll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("THỜI GIAN SỬ DỤNG");
        jLabel6.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N

        jLabel7.setText("Thời gian  BĐ Giảm giá");
        jLabel7.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N

        jLabel8.setText("Thời gian  KT Giảm giá");
        jLabel8.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N

        btnLuu.setText("LƯU");
        btnLuu.setBackground(new java.awt.Color(255, 255, 0));
        btnLuu.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnSua.setText("SỬA");
        btnSua.setBackground(new java.awt.Color(255, 255, 0));
        btnSua.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnLamMoi.setText("LÀM MỚI");
        btnLamMoi.setBackground(new java.awt.Color(255, 255, 0));
        btnLamMoi.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        jLabel9.setText("Trạng thái");
        jLabel9.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N

        buttonGroup1.add(rdoOnline);
        rdoOnline.setText("Online");

        buttonGroup1.add(rdoOffline);
        rdoOffline.setText("Offline");

        btnThemVaoSp.setText("THÊM VÀO SP");
        btnThemVaoSp.setBackground(new java.awt.Color(255, 255, 0));
        btnThemVaoSp.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        btnThemVaoSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemVaoSpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnLuu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThemVaoSp, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(rdoOnline)
                                .addGap(29, 29, 29)
                                .addComponent(rdoOffline, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 307, Short.MAX_VALUE))
                    .addComponent(cbNgayBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbNgayKT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(cbNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(cbNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoOnline)
                    .addComponent(rdoOffline))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemVaoSp, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách Khuyến mại", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 1, 17))); // NOI18N

        tblDanhSachKM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KM", "Tên chương trình", "Ngày bắt đầu ", "Ngày kết thúc", "Mức giảm giá", "Trạng Thái"
            }
        ));
        tblDanhSachKM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachKMMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDanhSachKM);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1159, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents
    private boolean validateForm() {
        if (txtMaKM.getText().trim().equals("") || txtTaoCTKM.getText().trim().equals("") || txtMucGiamGia.getText().trim().equals("") || cbNgayBD.getDate() == null || cbNgayKT.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Dữ liệu không được để trống");
            return false;
        }
        if (cbNgayBD.getDate().compareTo(cbNgayKT.getDate()) > 0) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc không thể nhỏ hơn ngày bắt đầu");
            return false;
        }
        try {
            if (Double.parseDouble(txtMucGiamGia.getText().trim()) < 0) {
                JOptionPane.showMessageDialog(this, "Mức giảm giá không được âm");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Mức giảm giá sai định dạng");
            return false;
        }
        return true;
    }

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if (validateForm()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thêm ?", "Xác nhân thêm.", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                GiamGia vc = new GiamGia();
                vc.setMaGiamGia(txtMaKM.getText().trim());
                vc.setTenGiamGia(txtTaoCTKM.getText().trim());
                Date dateNgayBD = java.sql.Date.valueOf(cbNgayBD.getDate());
                vc.setNgayBatDau(dateNgayBD);
                Date dateNgayKT = java.sql.Date.valueOf(cbNgayKT.getDate());
                vc.setNgayKetThuc(dateNgayKT);
                vc.setTrangThai(this.trangThai());
                vc.setMucGiamGia(txtMucGiamGia.getText().trim());
                gg.them(vc);
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                loadtableKM();
            } else {
                return;
            }
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    ArrayList<ViewSanPham> list = new ArrayList<>();
    private void tblLoadLuaChonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLoadLuaChonMouseClicked
        // TODO add your handling code here:
        try {
            ViewSanPham sp = new ViewSanPham();
            Boolean check = ((Boolean) tblLoadLuaChon.getValueAt(tblLoadLuaChon.getSelectedRow(), 0));
            if (check) {
                sp.setMaSp(tblLoadLuaChon.getValueAt(tblLoadLuaChon.getSelectedRow(), 1).toString());
                list.add(sp);
            } else {
                for (ViewSanPham viewSanPham : list) {
                    if (viewSanPham.getMaSp().equals(tblLoadLuaChon.getValueAt(tblLoadLuaChon.getSelectedRow(), 1))) {
                        list.remove(viewSanPham);
                    }
                }
            }
        } catch (ConcurrentModificationException e) {
            return;
        }
    }//GEN-LAST:event_tblLoadLuaChonMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        txtMaKM.setText("");
        txtTaoCTKM.setText("");
        txtMucGiamGia.setText("");
        cbNgayBD.setText("");
        cbNgayKT.setText("");
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (validateForm()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa ?", "Xác nhân sửa.", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                GiamGia vc = new GiamGia();
                vc.setMaGiamGia(txtMaKM.getText().trim());
                vc.setTenGiamGia(txtTaoCTKM.getText().trim());
                Date dateNgayBD = java.sql.Date.valueOf(cbNgayBD.getDate());
                vc.setNgayBatDau(dateNgayBD);
                Date dateNgayKT = java.sql.Date.valueOf(cbNgayKT.getDate());
                vc.setNgayKetThuc(dateNgayKT);
                vc.setTrangThai(this.trangThai());
                vc.setMucGiamGia(txtMucGiamGia.getText().trim());
                gg.sua(vc);
                JOptionPane.showMessageDialog(this, "Sửa thành công");
                loadtableKM();
            } else {
                return;
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void cbxChonAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxChonAllActionPerformed
        // TODO add your handling code here:
        if (cbxChonAll.isSelected()) {
            for (int i = 0; i < tblLoadLuaChon.getRowCount(); i++) {
                tblLoadLuaChon.setValueAt(true, i, 0);
                ViewSanPham sp = new ViewSanPham();
                sp.setMaSp(tblLoadLuaChon.getValueAt(i, 1).toString());
                list.add(sp);
            }
        } else {
            for (int i = 0; i < tblLoadLuaChon.getRowCount(); i++) {
                tblLoadLuaChon.setValueAt(false, i, 0);
                list.removeAll(list);
            }
        }
    }//GEN-LAST:event_cbxChonAllActionPerformed

    private void btnThemVaoSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemVaoSpActionPerformed
        // TODO add your handling code here:
        if (tblDanhSachKM.getSelectedRow() < 0 || tblLoadLuaChon.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Hãy chọn sản phẩm hoặc mã giảm giá");
            return;
        } else {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thêm ?", "Xác nhân thêm.", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                for (ViewSanPham viewSanPham : list) {

                    GiamGiaSp spgg = new GiamGiaSp();
                    spgg.setIdSP(viewSanPham.getMaSp());
                    spgg.setIdGiamGia(tblDanhSachKM.getValueAt(tblDanhSachKM.getSelectedRow(), 0).toString());
                    spgg.setTrangThai(0);
                    ggsp.addVCSP(viewSanPham.getMaSp(), spgg);
                }
                JOptionPane.showMessageDialog(this, "Thêm giảm giá cho sản phẩm thành công.");
            } else {
                return;
            }
        }
    }//GEN-LAST:event_btnThemVaoSpActionPerformed

    public void loadTf(int i) {
        if (tblLoadLuaChon.getRowCount() > 0) {
            txtMaKM.setText(tblDanhSachKM.getValueAt(i, 0).toString());
            txtTaoCTKM.setText(tblDanhSachKM.getValueAt(i, 1).toString());
            cbNgayBD.setDate(LocalDate.parse(tblDanhSachKM.getValueAt(i, 2).toString()));
            cbNgayKT.setDate(LocalDate.parse(tblDanhSachKM.getValueAt(i, 3).toString()));
            txtMucGiamGia.setText(tblDanhSachKM.getValueAt(i, 4).toString());
            if (tblDanhSachKM.getValueAt(i, 5).toString().equals("Online")) {
                rdoOnline.setSelected(true);
            } else if (tblDanhSachKM.getValueAt(i, 5).toString().equals("Offline")) {
                rdoOffline.setSelected(true);
            }
        }
    }

    private void tblDanhSachKMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachKMMouseClicked
        // TODO add your handling code here:
        try {
            int i = tblDanhSachKM.getSelectedRow();
            loadTf(i);
        } catch (NullPointerException e) {
            return;
        }
    }//GEN-LAST:event_tblDanhSachKMMouseClicked

    public void loadTable() {
        List<ViewSanPham> list = (List<ViewSanPham>) sps.getList();
        DefaultTableModel model = (DefaultTableModel) tblLoadLuaChon.getModel();
        model.setRowCount(0);
        for (ViewSanPham x : list) {
            model.addRow(new Object[]{
                false,
                x.getMaSp(),
                x.getTenSp()
            });
        }
    }

    private void loadtableKM() {
        ArrayList<GiamGiaViewModel> listVoucher = gg.layTTGiamGia();
        DefaultTableModel model = (DefaultTableModel) tblDanhSachKM.getModel();

        model.setRowCount(0);
        for (GiamGiaViewModel x : listVoucher) {
            model.addRow(new Object[]{x.getMaGiamGia(), x.getTenGiamGia(), x.getNgayBatDau(), x.getNgayKetThuc(),
                x.getMucGiamGia(), x.trangThai()});
        }
    }

    private Integer trangThai() {
        if (rdoOnline.isSelected()) {
            return 0;
        } else {
            return 1;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThemVaoSp;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.github.lgooddatepicker.components.DatePicker cbNgayBD;
    private com.github.lgooddatepicker.components.DatePicker cbNgayKT;
    private javax.swing.JCheckBox cbxChonAll;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton rdoOffline;
    private javax.swing.JRadioButton rdoOnline;
    private javax.swing.JTable tblDanhSachKM;
    private javax.swing.JTable tblLoadLuaChon;
    private javax.swing.JTextField txtMaKM;
    private javax.swing.JTextField txtMucGiamGia;
    private javax.swing.JTextField txtTaoCTKM;
    // End of variables declaration//GEN-END:variables
}
