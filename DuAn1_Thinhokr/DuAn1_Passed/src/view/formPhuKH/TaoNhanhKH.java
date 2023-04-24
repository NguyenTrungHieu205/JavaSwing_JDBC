/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.formPhuKH;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import service.DiemService;
import service.KhachHangService;
import service.impl.DiemServiceImpl;
import service.impl.KhachHangServiceImpl;
import viewModel.KhachHangViewModel;

/**
 *
 * @author phamtuyetnga
 */
public class TaoNhanhKH extends javax.swing.JFrame {

    private DiemService diemSer = new DiemServiceImpl();
    private KhachHangService khachHangSevice = new KhachHangServiceImpl();
    public static String maKH = null;
    public static String tenKH = null;
    public static String sdtKH = null;
    public static String diemKH = null;

    public TaoNhanhKH() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loadTable(khachHangSevice.getAllKhachHang());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cbNgaySinh.getSettings().setAllowKeyboardEditing(false);
    }

    private boolean checkSDT() {
        String sdt = txtSoDienThoai.getText().trim();
        String pater = "([0-9]{10,11})";
        if (sdt.matches(pater)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkRong() {
        if (txtMaKH.getText().trim().isEmpty() && txtHoTen.getText().trim().isEmpty() && txtSoDienThoai.getText().trim().isEmpty()
                 && txtDiaChi.getText().trim().isEmpty() && txtGhiChu.getText().trim().isEmpty()
                ) {
            JOptionPane.showMessageDialog(this, "DỮ LIỆU CÁC Ô KHÔNG ĐƯỢC ĐỂ RỖNG!", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtMaKH.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "DỮ LIỆU CÁC Ô KHÔNG ĐƯỢC ĐỂ RỖNG!", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtHoTen.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "DỮ LIỆU Ô HỌ TÊN KHÔNG ĐƯỢC ĐỂ RỖNG!", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtSoDienThoai.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "DỮ LIỆU Ô SĐT KHÔNG ĐƯỢC ĐỂ RỖNG!", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (checkSDT() == false) {
            JOptionPane.showMessageDialog(this, "DỮ LIỆU Ô SĐT SAI ĐỊNH DẠNG!", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtDiaChi.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "DỮ LIỆU Ô ĐỊA CHỈ KHÔNG ĐƯỢC ĐỂ RỖNG!", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (rdThanQuen.isSelected() == false && rdMoi.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "DỮ LIỆU Ô TRẠNG THÁI KHÔNG ĐƯỢC ĐỂ RỖNG!", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtGhiChu.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "DỮ LIỆU Ô GHI CHÚ KHÔNG ĐƯỢC ĐỂ RỖNG!", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pan2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        rdnam = new javax.swing.JRadioButton();
        rdNu = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        btnThem = new javax.swing.JButton();
        rdThanQuen = new javax.swing.JRadioButton();
        rdMoi = new javax.swing.JRadioButton();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jLabel1 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        txtTimKiemKhachHang = new javax.swing.JTextField();
        btnChon = new javax.swing.JButton();
        cbNgaySinh = new com.github.lgooddatepicker.components.DatePicker();
        btnRefesh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pan2.setBackground(new java.awt.Color(158, 195, 192));
        pan2.setForeground(new java.awt.Color(255, 255, 255));
        pan2.setPreferredSize(new java.awt.Dimension(1500, 779));

        jLabel4.setText("Mã Khách Hàng");
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("Họ Tên");
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setText("Số Điện Thoại");
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));

        jLabel7.setText("Ngày Sinh");
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));

        jLabel8.setText("Địa Chỉ");
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));

        jLabel9.setText("Giới Tính");
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));

        jLabel10.setText("Trạng Thái");
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));

        jLabel12.setText("Ghi Chú");
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));

        rdnam.setText("Nam");
        rdnam.setBackground(new java.awt.Color(97, 212, 195));
        rdnam.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdnam.setForeground(new java.awt.Color(255, 255, 255));

        rdNu.setText("Nữ");
        rdNu.setBackground(new java.awt.Color(97, 212, 195));
        rdNu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdNu.setForeground(new java.awt.Color(255, 255, 255));

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane1.setViewportView(txtGhiChu);

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add (2).png"))); // NOI18N
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.setPreferredSize(new java.awt.Dimension(100, 30));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        rdThanQuen.setText("Thân Quen");
        rdThanQuen.setBackground(new java.awt.Color(97, 212, 195));
        rdThanQuen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdThanQuen.setForeground(new java.awt.Color(255, 255, 255));
        rdThanQuen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdThanQuenActionPerformed(evt);
            }
        });

        rdMoi.setText("Mới");
        rdMoi.setBackground(new java.awt.Color(97, 212, 195));
        rdMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdMoi.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("KHÁCH HÀNG");
        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(158, 195, 192));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "THÔNG TIN KHÁCH HÀNG", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 1, 16), new java.awt.Color(255, 0, 0))); // NOI18N

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblKhachHang);

        txtTimKiemKhachHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKhachHangKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(txtTimKiemKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        btnChon.setText("Chọn");
        btnChon.setFont(new java.awt.Font("Lucida Grande", 1, 15)); // NOI18N
        btnChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonActionPerformed(evt);
            }
        });

        btnRefesh.setBackground(new java.awt.Color(255, 255, 102));
        btnRefesh.setText("REFESH");
        btnRefesh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefeshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pan2Layout = new javax.swing.GroupLayout(pan2);
        pan2.setLayout(pan2Layout);
        pan2Layout.setHorizontalGroup(
            pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan2Layout.createSequentialGroup()
                .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pan2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan2Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38))
                            .addGroup(pan2Layout.createSequentialGroup()
                                .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)))
                        .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtMaKH)
                                .addComponent(txtHoTen)
                                .addComponent(txtSoDienThoai)
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbNgaySinh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pan2Layout.createSequentialGroup()
                                .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdnam, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rdMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdNu, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rdThanQuen))))
                        .addGap(91, 91, 91)
                        .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 975, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pan2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel12)
                        .addGap(67, 67, 67)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan2Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRefesh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnChon, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(515, 515, 515))
        );
        pan2Layout.setVerticalGroup(
            pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pan2Layout.createSequentialGroup()
                        .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdnam)
                            .addComponent(rdNu))
                        .addGap(30, 30, 30)
                        .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdMoi)
                            .addComponent(rdThanQuen))
                        .addGap(297, 297, 297)
                        .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89))
                    .addGroup(pan2Layout.createSequentialGroup()
                        .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pan2Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(40, 40, 40))
                            .addGroup(pan2Layout.createSequentialGroup()
                                .addGap(203, 203, 203)
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pan2Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(110, 110, 110)))
                        .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnChon, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                            .addComponent(btnRefesh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pan2, javax.swing.GroupLayout.PREFERRED_SIZE, 1002, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pan2, javax.swing.GroupLayout.PREFERRED_SIZE, 762, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadTable(ArrayList<KhachHangViewModel> list) {
        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (list == null) {
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();
        model.setColumnCount(0);
        model.addColumn("MÃ KH");
        model.addColumn("HỌ TÊN KH");
        model.addColumn("SĐT");
        model.addColumn("NGÀY SINH");
        model.addColumn("ĐỊA CHỈ");
        model.addColumn("GIỚI TÍNH");
        model.addColumn("TRẠNG THÁI");
        model.addColumn("GHI CHÚ");
        model.addColumn("SỐ LẦN MUA");
        model.addColumn("SỐ ĐIỂM");

        model.setRowCount(0);
        for (KhachHangViewModel kh : list) {
            model.addRow(new Object[]{
                kh.getMaKhachHang(),
                kh.getHoTenKhachHang(),
                kh.getSdt(),
                kh.getNgaySinh(),
                kh.getDiaChi(),
                kh.getGioiTinh() == 0 ? "Nam" : "Nữ",
                kh.getTrangThai() == 1 ? "Mới" : "Thân quen",
                kh.getGhiCHu(),
                kh.getSoLanMua(),
                kh.getSoDiem()
            });
        }

    }
    private void rdThanQuenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdThanQuenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdThanQuenActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (checkRong()) {
            try {
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thêm ?", "Xác nhân thêm.", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    model.KhachHang kh = new model.KhachHang();
                    kh.setMaKhachHang(txtMaKH.getText().trim());
                    kh.setHoTenKhachHang(txtHoTen.getText().trim());
                    kh.setSdt(txtSoDienThoai.getText().trim());

                    kh.setNgaySinh(java.sql.Date.valueOf(cbNgaySinh.getDate()));

                    kh.setDiaChi(txtDiaChi.getText().trim());
                    kh.setGioiTinh(rdnam.isSelected() ? 0 : 1);
                    kh.setTrangThai(rdThanQuen.isSelected() ? 0 : 1);
                    kh.setGhiCHu(txtGhiChu.getText().trim());
//                    kh.setSoLanMua(Integer.parseInt(txtSoLuongMua.getText().trim()));

                    diemSer.insertDiem();
                    khachHangSevice.themKhachHang(kh);
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                    loadTable(khachHangSevice.getAllKhachHang());
                } else {
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonActionPerformed
        maKH = tblKhachHang.getValueAt(tblKhachHang.getSelectedRow(), 0).toString();
        tenKH = tblKhachHang.getValueAt(tblKhachHang.getSelectedRow(), 1).toString();
        sdtKH = tblKhachHang.getValueAt(tblKhachHang.getSelectedRow(), 2).toString();
        diemKH = tblKhachHang.getValueAt(tblKhachHang.getSelectedRow(), 9).toString();
        JOptionPane.showMessageDialog(this, "Chọn khách hàng thành công");
        this.dispose();
    }//GEN-LAST:event_btnChonActionPerformed

    private void txtTimKiemKhachHangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKhachHangKeyReleased
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) (tblKhachHang.getModel());
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(model);
        tblKhachHang.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(txtTimKiemKhachHang.getText().trim()));
    }//GEN-LAST:event_txtTimKiemKhachHangKeyReleased

    private void btnRefeshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefeshActionPerformed
        // TODO add your handling code here:
        txtMaKH.setText("");
        txtHoTen.setText("");
        txtDiaChi.setText("");
        txtGhiChu.setText("");
        cbNgaySinh.setText("");
        txtSoDienThoai.setText("");
    }//GEN-LAST:event_btnRefeshActionPerformed

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
            java.util.logging.Logger.getLogger(TaoNhanhKH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TaoNhanhKH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TaoNhanhKH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TaoNhanhKH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TaoNhanhKH().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChon;
    private javax.swing.JButton btnRefesh;
    private javax.swing.JButton btnThem;
    private com.github.lgooddatepicker.components.DatePicker cbNgaySinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JPanel pan2;
    private javax.swing.JRadioButton rdMoi;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JRadioButton rdThanQuen;
    private javax.swing.JRadioButton rdnam;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTimKiemKhachHang;
    // End of variables declaration//GEN-END:variables
}
