/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import service.DiemService;
import service.KhachHangService;
import service.impl.DiemServiceImpl;
import service.impl.KhachHangServiceImpl;
import viewModel.KhachHangViewModel;

/**
 *
 * @author phamtuyetnga
 */
public class KhachHang extends javax.swing.JPanel implements Runnable, ThreadFactory {

    public KhachHang() {
        initComponents();
        loadTable(khachHangSevice.getAllKhachHang());
        loadTableDaXoa(khachHangSevice.getAllKhDaXoa());
        QrQuet.setSize(500, 500);
        txtNgaySinh.getSettings().setAllowKeyboardEditing(false);
    }
      private WebcamPanel panel = null;
    private Webcam webcam = null;

    private static final long serial = 6441489157408381878L;
    private Executor ex = Executors.newSingleThreadExecutor(this);
    public static String ma = null;

    public void initWebCam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0);
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        jPanel9.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 230));

        ex.execute(this);
    }

    private KhachHangService khachHangSevice = new KhachHangServiceImpl();
    private DiemService diemSer = new DiemServiceImpl();

    private void loadThongTin() {
        try {
            txtHoTen.setText(QuetMaKhachHang.hoTen);
            txtDiaChi.setText(QuetMaKhachHang.diaChi);
            txtNgaySinh.setText(QuetMaKhachHang.ngaySinh);
            String gioiTinh = QuetMaKhachHang.gioiTinh;
            if (gioiTinh.equals("Nam")) {
                rdnam.setSelected(true);
            } else {
                rdNu.setSelected(true);
            }
        } catch (Exception e) {
        }

    }

    private void loadTable(ArrayList<KhachHangViewModel> list) {
        DefaultTableModel model = (DefaultTableModel) tblThongTin.getModel();
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
                kh.getGioiTinh() == 1 ? "Nam" : "Nữ",
                kh.getTrangThai() == 1 ? "Mới" : "Thân quen",
                kh.getGhiCHu(),
                kh.getSoLanMua(),
                kh.getSoDiem()
            });
        }

    }

    private void loadTableDaXoa(ArrayList<KhachHangViewModel> list) {

        DefaultTableModel model = (DefaultTableModel) tblKhachHangDaXoa.getModel();
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
                kh.getMaKhachHang(), kh.getHoTenKhachHang(), kh.getSdt(), kh.getNgaySinh(),
                kh.getDiaChi(), kh.getGioiTinh(), kh.getTrangThai(), kh.getGhiCHu(), kh.getSoLanMua(), kh.getSoDiem()
            });
        }

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
        if (txtMaKhachHang.getText().trim().isEmpty() && txtHoTen.getText().trim().isEmpty() && txtSoDienThoai.getText().trim().isEmpty()
                && txtNgaySinh.getText().trim().isEmpty() && txtDiaChi.getText().trim().isEmpty() && txtGhiChu.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "DỮ LIỆU CÁC Ô KHÔNG ĐƯỢC ĐỂ RỖNG!", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtMaKhachHang.getText().trim().isEmpty()) {
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
        if (txtNgaySinh.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "DỮ LIỆU Ô NGÀY SINH KHÔNG ĐƯỢC ĐỂ RỖNG!", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
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
//        if (txtSoLuongMua.getText().trim().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "DỮ LIỆU Ô SỐ LƯỢNG MUA KHÔNG ĐƯỢC ĐỂ RỖNG!", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        QrQuet = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
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
        tbnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        rdThanQuen = new javax.swing.JRadioButton();
        rdMoi = new javax.swing.JRadioButton();
        txtMaKhachHang = new javax.swing.JTextField();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblThongTin = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhachHangDaXoa = new javax.swing.JTable();
        btnHoanTac = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnQuet = new javax.swing.JButton();
        btnRefesh = new javax.swing.JButton();
        txtNgaySinh = new com.github.lgooddatepicker.components.DatePicker();

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Thoát");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(310, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(27, 27, 27))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 214, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout QrQuetLayout = new javax.swing.GroupLayout(QrQuet.getContentPane());
        QrQuet.getContentPane().setLayout(QrQuetLayout);
        QrQuetLayout.setHorizontalGroup(
            QrQuetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        QrQuetLayout.setVerticalGroup(
            QrQuetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(158, 195, 192));
        setPreferredSize(new java.awt.Dimension(1200, 800));
        setVerifyInputWhenFocusTarget(false);

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

        rdnam.setBackground(new java.awt.Color(97, 212, 195));
        buttonGroup1.add(rdnam);
        rdnam.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdnam.setForeground(new java.awt.Color(255, 255, 255));
        rdnam.setText("Nam");

        rdNu.setBackground(new java.awt.Color(97, 212, 195));
        buttonGroup1.add(rdNu);
        rdNu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdNu.setForeground(new java.awt.Color(255, 255, 255));
        rdNu.setText("Nữ");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane1.setViewportView(txtGhiChu);

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add (2).png"))); // NOI18N
        btnThem.setBackground(new java.awt.Color(153, 255, 255));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.setPreferredSize(new java.awt.Dimension(100, 30));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        tbnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/update (2).png"))); // NOI18N
        tbnSua.setBackground(new java.awt.Color(102, 204, 255));
        tbnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tbnSua.setPreferredSize(new java.awt.Dimension(100, 30));
        tbnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnSuaActionPerformed(evt);
            }
        });

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delele.png"))); // NOI18N
        btnXoa.setBackground(new java.awt.Color(255, 51, 51));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.setPreferredSize(new java.awt.Dimension(100, 30));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        rdThanQuen.setBackground(new java.awt.Color(97, 212, 195));
        buttonGroup2.add(rdThanQuen);
        rdThanQuen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdThanQuen.setForeground(new java.awt.Color(255, 255, 255));
        rdThanQuen.setText("Thân Quen");
        rdThanQuen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdThanQuenActionPerformed(evt);
            }
        });

        rdMoi.setBackground(new java.awt.Color(97, 212, 195));
        buttonGroup2.add(rdMoi);
        rdMoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdMoi.setForeground(new java.awt.Color(255, 255, 255));
        rdMoi.setText("Mới");

        tblThongTin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblThongTin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThongTinMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblThongTin);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 736, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
        );

        jTabbedPane4.addTab("Thông Tin Khách Hàng", jPanel3);

        tblKhachHangDaXoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblKhachHangDaXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangDaXoaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKhachHangDaXoa);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
        );

        jTabbedPane4.addTab("Khách hàng Đã Xóa", jPanel4);

        btnHoanTac.setText("Hoàn tác");
        btnHoanTac.setBackground(new java.awt.Color(153, 255, 153));
        btnHoanTac.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHoanTac.setPreferredSize(new java.awt.Dimension(100, 30));
        btnHoanTac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoanTacActionPerformed(evt);
            }
        });

        jLabel1.setText("KHÁCH HÀNG");
        jLabel1.setFont(new java.awt.Font("Lucida Grande", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        btnQuet.setIcon(new javax.swing.ImageIcon("F:\\DuAn1\\DuAn1_Thinhokr\\DuAn1_Passed\\src\\icons\\qrcode_scan_icon_136286.png")); // NOI18N
        btnQuet.setBackground(new java.awt.Color(204, 255, 255));
        btnQuet.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnQuet.setPreferredSize(new java.awt.Dimension(100, 30));
        btnQuet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuetActionPerformed(evt);
            }
        });

        btnRefesh.setText("REFESH");
        btnRefesh.setBackground(new java.awt.Color(255, 255, 102));
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
                        .addContainerGap()
                        .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pan2Layout.createSequentialGroup()
                                .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(46, 46, 46)
                                .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pan2Layout.createSequentialGroup()
                                        .addComponent(rdnam, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(rdNu, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pan2Layout.createSequentialGroup()
                                        .addComponent(rdMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)
                                        .addComponent(rdThanQuen))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtDiaChi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))))
                            .addComponent(jLabel12)))
                    .addGroup(pan2Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(61, 61, 61)
                .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pan2Layout.createSequentialGroup()
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQuet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pan2Layout.createSequentialGroup()
                .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pan2Layout.createSequentialGroup()
                        .addGap(292, 292, 292)
                        .addComponent(tbnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(btnHoanTac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pan2Layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRefesh)
                .addGap(359, 359, 359))
        );
        pan2Layout.setVerticalGroup(
            pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan2Layout.createSequentialGroup()
                .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pan2Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pan2Layout.createSequentialGroup()
                        .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pan2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))
                            .addGroup(pan2Layout.createSequentialGroup()
                                .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnQuet, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHoanTac, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(btnRefesh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pan2, javax.swing.GroupLayout.PREFERRED_SIZE, 1171, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pan2, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 110, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed

        if (!khachHangSevice.checkTrungMa(txtMaKhachHang.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Trùng Mã");
            return;
        } else if (checkRong()) {
            try {
//                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thêm ?", "Xác nhân thêm.", JOptionPane.YES_NO_OPTION);
//                if (confirm == JOptionPane.YES_OPTION) {
                model.KhachHang kh = new model.KhachHang();
                kh.setMaKhachHang(txtMaKhachHang.getText().trim());
                kh.setHoTenKhachHang(txtHoTen.getText().trim());
                kh.setSdt(txtSoDienThoai.getText().trim());
                //SimpleDateFormat ngaySinh = new SimpleDateFormat("yyyy-MM-dd");

                kh.setNgaySinh( java.sql.Date.valueOf(txtNgaySinh.getDate()));

                kh.setDiaChi(txtDiaChi.getText().trim());
                kh.setGioiTinh(rdnam.isSelected() ? 1 : 0);
                kh.setTrangThai(rdThanQuen.isSelected() ? 1 : 0);
                kh.setGhiCHu(txtGhiChu.getText().trim());
                //kh.setSoLanMua(Integer.parseInt(txtSoLuongMua.getText().trim()));
                diemSer.insertDiem();

                khachHangSevice.themKhachHang(kh);
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                loadTable(khachHangSevice.getAllKhachHang());
//                } else {
//                    return;
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Xác nhận xoá", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
//            if (khachHangSevice.xoaKhachHang(txtMaKhachHang.getText().trim())) {
//                JOptionPane.showMessageDialog(this, "XOÁ THÀNH CÔNG");
//                loadTable(khachHangSevice.getAllKhachHang());
//                loadTableDaXoa(khachHangSevice.getAllKhDaXoa());
//            } else {
//                return;
//            }
            if (txtMaKhachHang.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã KHÁCH HÀNG RỖNG! XOÁ THẤT BẠI!", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
            } else {
                if (khachHangSevice.xoaKhachHang(txtMaKhachHang.getText().trim())) {
                    JOptionPane.showMessageDialog(this, "XOÁ THÔNG TIN KHÁCH HÀNG THÀNH CÔNG", "THÔNG BÁO", JOptionPane.QUESTION_MESSAGE);
                    loadTable(khachHangSevice.getAllKhachHang());
                    loadTableDaXoa(khachHangSevice.getAllKhDaXoa());
                } else {
                    JOptionPane.showMessageDialog(this, "XOÁ THÔNG TIN KHÁCH HÀNG THẤT BẠI!", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void rdThanQuenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdThanQuenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdThanQuenActionPerformed

    private void tbnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnSuaActionPerformed
        if (checkRong()) {
            try {
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa ?", "Xác nhân sửa.", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    model.KhachHang kh = new model.KhachHang();
                    kh.setMaKhachHang(txtMaKhachHang.getText().trim());
                    kh.setHoTenKhachHang(txtHoTen.getText().trim());
                    kh.setSdt(txtSoDienThoai.getText().trim());
                    //SimpleDateFormat ngaySinh = new SimpleDateFormat("yyyy-MM-dd");

                    kh.setNgaySinh( java.sql.Date.valueOf(txtNgaySinh.getDate()));

                    kh.setDiaChi(txtDiaChi.getText().trim());
                    kh.setGioiTinh(rdnam.isSelected() ? 1 : 0);
                    kh.setTrangThai(rdThanQuen.isSelected() ? 0 : 1);
                    kh.setGhiCHu(txtGhiChu.getText().trim());
//                    kh.setSoLanMua(Integer.parseInt(txtSoLuongMua.getText().trim()));

                    khachHangSevice.suaKhachHang(kh);
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                    loadTable(khachHangSevice.getAllKhachHang());
                } else {
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_tbnSuaActionPerformed

    private void btnHoanTacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoanTacActionPerformed

    }//GEN-LAST:event_btnHoanTacActionPerformed

    private void clickTableDaXoa(int row, JTable table) {
        txtMaKhachHang.setText(table.getValueAt(row, 0).toString());
        txtHoTen.setText(table.getValueAt(row, 1).toString());
        txtSoDienThoai.setText(table.getValueAt(row, 2).toString());
        txtNgaySinh.setText(table.getValueAt(row, 3).toString());
        txtDiaChi.setText(table.getValueAt(row, 4).toString());
        if (table.getValueAt(row, 5).equals(0)) {
            rdnam.setSelected(true);
        } else if (table.getValueAt(row, 5).equals(1)) {
            rdNu.setSelected(true);
        }
        if (table.getValueAt(row, 5).equals(0) == false && table.getValueAt(row, 5).equals(1) == false) {
            rdnam.setSelected(false);
            rdNu.setSelected(false);
        }
        if (table.getValueAt(row, 6).equals(0)) {
            rdThanQuen.setSelected(true);
        } else if (table.getValueAt(row, 6).equals(1)) {
            rdMoi.setSelected(true);
        } else {
            rdThanQuen.setSelected(false);
            rdMoi.setSelected(false);
        }
        txtGhiChu.setText(table.getValueAt(row, 7).toString());
//        txtSoLuongMua.setText(table.getValueAt(row, 8).toString());
    }
    private void tblKhachHangDaXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangDaXoaMouseClicked
        int row = tblKhachHangDaXoa.getSelectedRow();
        clickTableDaXoa(row, tblKhachHangDaXoa);
    }//GEN-LAST:event_tblKhachHangDaXoaMouseClicked

    private void tblThongTinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThongTinMouseClicked
        int index = tblThongTin.getSelectedRow();
        txtMaKhachHang.setText(tblThongTin.getValueAt(index, 0).toString());
        txtHoTen.setText(tblThongTin.getValueAt(index, 1).toString());
        txtSoDienThoai.setText(tblThongTin.getValueAt(index, 2).toString());
        txtNgaySinh.setDate(LocalDate.parse(tblThongTin.getValueAt(index, 3).toString()));
        txtDiaChi.setText(tblThongTin.getValueAt(index, 4).toString());
        if (tblThongTin.getValueAt(index, 5).toString().equals("Nam")) {
            rdnam.setSelected(true);
        } else {
            rdNu.setSelected(true);
        }

        if (tblThongTin.getValueAt(index, 6).toString().equals("Mới")) {
            rdMoi.setSelected(true);
        } else {
            rdThanQuen.setSelected(true);
        }
        txtGhiChu.setText(tblThongTin.getValueAt(index, 7).toString());
//        txtSoLuongMua.setText(tblThongTin.getValueAt(index, 8).toString());
    }//GEN-LAST:event_tblThongTinMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        String timKiem = txtTimKiem.getText().trim();
        ArrayList<KhachHangViewModel> listKhachHang = khachHangSevice.locKH(timKiem);
        ArrayList<KhachHangViewModel> listKhachHangTimTheoTen = new ArrayList<>();
        for (KhachHangViewModel khachHangViewModel : khachHangSevice.getAllKhachHang()) {
            if (khachHangViewModel.getHoTenKhachHang().contains(timKiem) || khachHangViewModel.getSdt().contains(timKiem)) {
                listKhachHangTimTheoTen.add(khachHangViewModel);
            }
        }
        if (listKhachHangTimTheoTen != null) {
            loadTable(listKhachHangTimTheoTen);

        }

        if (txtTimKiem.getText().trim().equals("")) {
            loadTable(khachHangSevice.getAllKhachHang());
        }
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void btnQuetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuetActionPerformed
        initWebCam();
        QrQuet.setVisible(true);

    }//GEN-LAST:event_btnQuetActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         QrQuet.setVisible(false);
         webcam.close();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnRefeshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefeshActionPerformed
        // TODO add your handling code here:
        txtMaKhachHang.setText("");
        txtHoTen.setText("");
        txtDiaChi.setText("");
        txtGhiChu.setText("");
        txtNgaySinh.setText("");
        txtSoDienThoai.setText("");
    }//GEN-LAST:event_btnRefeshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog QrQuet;
    private javax.swing.JButton btnHoanTac;
    private javax.swing.JButton btnQuet;
    private javax.swing.JButton btnRefesh;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JPanel pan2;
    private javax.swing.JRadioButton rdMoi;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JRadioButton rdThanQuen;
    private javax.swing.JRadioButton rdnam;
    private javax.swing.JTable tblKhachHangDaXoa;
    private javax.swing.JTable tblThongTin;
    private javax.swing.JButton tbnSua;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaKhachHang;
    private com.github.lgooddatepicker.components.DatePicker txtNgaySinh;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
         do {

            try {
                Thread.sleep(100);

            } catch (Exception e) {
                System.out.println(e);
            }

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (Exception e) {
            }
            if (result != null) {

                boolean check = true;
                if (check) {
                    txtGhiChu.setText(result.getText());
                    String chuoi = txtGhiChu.getText();
                    String nganCach = "|";

                    int soCmt = chuoi.indexOf(nganCach); // lấy ra vị trí
                    String soCanCuocCongDan = chuoi.substring(0, soCmt);
//        txtSoCmt.setText(soCanCuocCongDan);

                    int soCMTCu = chuoi.indexOf(nganCach, soCmt + 1);// lấy ra vị trí
                    String sochungMinhThu = chuoi.substring(soCmt + 1, soCMTCu);
                    System.out.println(sochungMinhThu);
//     

                    int hoTen = chuoi.indexOf(nganCach, soCMTCu + 1);// lấy ra vị trí
                    String hoVaTen = chuoi.substring(soCMTCu + 1, hoTen);

                    String khoangTrang = " ";

                   txtHoTen.setText(hoVaTen);

                    int ngaySinh1 = chuoi.indexOf(nganCach, hoTen + 1);// lấy ra vị trí
                    String ngaySinh = chuoi.substring(hoTen + 1, ngaySinh1);
                    SimpleDateFormat simDate = new SimpleDateFormat("dd-MM-yyyy");
                    String catNgaySinh = ngaySinh.substring(0, 2);
                    String catThangSinh = ngaySinh.substring(2, 4);
                    String catNamSinh = ngaySinh.substring(4, 8);
                    String layNgaySinh = catNamSinh + "-" + catThangSinh + "-" + catNgaySinh;
                    txtNgaySinh.setText(layNgaySinh);
//        System.out.println(catNgaySinh);
//        System.out.println(catThangSinh);
//        System.out.println(catNamSinh);
//        
//        txtNgaySinh.setText(gepNgaySing);
//
//                try {
//                    System.out.println(simDate.parse(gepNgaySing));
////       
//                } catch (ParseException ex) {
//                   
//                }
                    int i = chuoi.indexOf(nganCach, ngaySinh1 + 1);// lấy ra vị trí
                    String gioiTinh = chuoi.substring(ngaySinh1 + 1, i);
                    System.out.println(gioiTinh);

                    if (gioiTinh.equals("Nam")) {
                        rdnam.setSelected(true);
                    } else {
                        rdNu.setSelected(true);
                    }

                    int diaChi1 = chuoi.indexOf(nganCach, i + 1);// lấy ra vị trí
                    String diaChi = chuoi.substring(i + 1, diaChi1);
                    System.out.println(diaChi);
                    txtDiaChi.setText(diaChi);
//                    txtMaNhanVien.setText("");
                    txtGhiChu.setText("");
                    webcam.close();
                    QrQuet.setVisible(false);
                    JOptionPane.showMessageDialog(this, "Quét Mã Thành Công");

                } else {
                    JOptionPane.showMessageDialog(this, "Quét Thất Bại");
                }

//
//         int b = chuoi.lastIndexOf(nganCach, chuoi.length()) + 1;// lấy ra vị trí
//        String ngayCap = chuoi.substring(b, chuoi.length());
//        String catNgayCap = ngayCap.substring(0, 2);
//        String catThangCap = ngayCap.substring(2, 4);
//        String catNamCap = ngayCap.substring(4, 8);
//               
//        String cap=catNgayCap+"-"+ catThangCap+"-"+catNamCap;
//        
//        
//        txtNgayCap.setText(cap);
//        
//        
//        System.out.println(cap);
//        049202000284||Khuất Lưu Huy Nhật|24022002|Nam|Cẩm Bào, Cẩm Yên, Thạch Thất, Hà Nội|31052021
//         int f = chuoi.indexOf(nganCach, e + 1);// lấy ra vị trí
//        String ngaySinh = chuoi.substring(e + 1, f);
//        SimpleDateFormat simDate = new SimpleDateFormat("dd-MM-yyyy");
//        String catNgaySinh = ngaySinh.substring(0, 2);
//        String catThangSinh = ngaySinh.substring(2, 4);
//        String catNamSinh = ngaySinh.substring(4, 8);
//        String gepNgaySing =   catNgaySinh + "-" + catThangSinh + "-" + catNamSinh;
            }

        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
       Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }
}
