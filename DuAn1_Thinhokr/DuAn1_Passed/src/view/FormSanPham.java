/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.DungLuong;
import service.ChipService;
import service.DungLuongService;
import service.ImeiService;
import service.KichThuocService;
import service.ManHinhService;
import service.MauSacService;
import service.PhanLoaiHangService;
import service.PinService;
import service.RamService;
import service.SanPhamService;
import service.XuatXuService;
import service.impl.ChipServiceImpl;
import service.impl.DungLuongServiceImpl;
import service.impl.ImeiServiceImpl;
import service.impl.KichThuocServiceImpl;
import service.impl.ManHinhServiceImpl;
import service.impl.MauSacServiceImpl;
import service.impl.PhanLoaiHangServiceImpl;
import service.impl.PinServiceImpl;
import service.impl.RamServiceImpl;
import service.impl.SanPhamServiceImpl;
import service.impl.GiamGiaServiceImpl;
import service.impl.XuatXuServiceImpl;

import viewModel.ChipViewModel;
import viewModel.DungLuongViewModel;
import viewModel.ImeiViewModel;
import viewModel.KichThuocViewModel;
import viewModel.ManHinhViewModel;
import viewModel.MauSacViewModel;
import viewModel.PhanLoaiHangViewModel;
import viewModel.PinViewModel;
import viewModel.RamViewModel;
import viewModel.ViewSanPham;
import viewModel.GiamGiaViewModel;
import viewModel.XuatXuViewModel;
import service.GiamGiaService;
import service.ImeiBanHangService;
import service.impl.ImeiBanHangServiceIplm;
import viewModel.ImeiBanHang;

/**
 *
 * @author phamtuyetnga
 */
public class FormSanPham extends javax.swing.JPanel {

    public FormSanPham() {
        initComponents();
        loadTable();
        loadTbXoa();
        loadCbChip();
        loadCbDl();
        loadCbKt();
        loadCbMh();
        loadCbMs();
        loadCbPl();
        loadCbPin();
        loadCbRam();
        loadCbXx();
        txtTonKho.setText("0");
    }

    private final SanPhamService sanPhamSer = new SanPhamServiceImpl();
    private final ChipService chipSer = new ChipServiceImpl();
    private final DungLuongService dungLuongSer = new DungLuongServiceImpl();
    private final KichThuocService kichThuocSer = new KichThuocServiceImpl();
    private final ManHinhService manHinhSer = new ManHinhServiceImpl();
    private final MauSacService mauSacSer = new MauSacServiceImpl();
    private final PhanLoaiHangService phanLoaiHangSer = new PhanLoaiHangServiceImpl();
    private final PinService pinSer = new PinServiceImpl();
    private final RamService ramSer = new RamServiceImpl();
    private final ImeiBanHangService imeiService = new ImeiBanHangServiceIplm();
    private final XuatXuService xuatXuSer = new XuatXuServiceImpl();

    public void loadTable() {
        List<ViewSanPham> list = (List<ViewSanPham>) sanPhamSer.getList();
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        for (ViewSanPham x : list) {
            model.addRow(new Object[]{
                x.getMaSp(),
                x.getTenSp(),
                x.getPhanLoai(),
                x.getRam(),
                x.getPin(),
                x.getDungLuong(),
                x.getManHinh(),
                x.getSoLuong(),
                x.getGiaSp(),
                x.trangThai(), //                x.getMucGiamGia(),
            //                x.getSoTienCL()
            });
        }
    }

    public void loadTbXoa() {
        List<ViewSanPham> list = (List<ViewSanPham>) sanPhamSer.getAllXoa();
        DefaultTableModel model = (DefaultTableModel) tblSp1.getModel();
        model.setRowCount(0);
        for (ViewSanPham x : list) {
            model.addRow(new Object[]{x.getMaSp(), x.getTenSp(), x.getPhanLoai(), x.getRam(),
                x.getPin(), x.getDungLuong(), x.getManHinh(), x.getSoLuong(), x.getGiaSp()});
        }
    }

    public void loadCbChip() {
        cbChip.removeAllItems();
        for (ChipViewModel x : chipSer.getAll()) {
            cbChip.addItem(x.getTen());
        }
    }

    public void loadImei() {
        cbxImei.removeAllItems();
        for (ImeiBanHang x : imeiService.getAllImei(tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 0).toString())) {
            cbxImei.addItem(x.getMaImei());
        }
    }

    public void loadCbDl() {
        cbDungLuong.removeAllItems();
        for (DungLuongViewModel x : dungLuongSer.getAll()) {
            cbDungLuong.addItem(x.getTen());
        }
    }

    public void loadCbKt() {
        cbKichThuoc.removeAllItems();
        for (KichThuocViewModel x : kichThuocSer.getAll()) {
            cbKichThuoc.addItem(x.getTen());
        }
    }

    public void loadCbMh() {
        cbxManHinh.removeAllItems();
        for (ManHinhViewModel x : manHinhSer.getAll()) {
            cbxManHinh.addItem(x.getTen());
        }
    }

    public void loadCbMs() {
        cbxMauSac.removeAllItems();
        for (MauSacViewModel x : mauSacSer.getAll()) {
            cbxMauSac.addItem(x.getTen());
        }
    }

    public void loadCbPl() {
        cbxPhanLoai.removeAllItems();
        for (PhanLoaiHangViewModel x : phanLoaiHangSer.getAll()) {
            cbxPhanLoai.addItem(x.getTen());
        }
    }

    public void loadCbPin() {
        cbxPin.removeAllItems();
        for (PinViewModel x : pinSer.getAll()) {
            cbxPin.addItem(x.getTen());
        }
    }

    public void loadCbRam() {
        cbxRam.removeAllItems();
        for (RamViewModel x : ramSer.getAll()) {
            cbxRam.addItem(x.getTen());
        }
    }

    public void loadCbXx() {
        cbxXuatXu1.removeAllItems();
        for (XuatXuViewModel x : xuatXuSer.getAll()) {
            cbxXuatXu1.addItem(x.getTen());
        }
    }

    public void loadTf(int i) {
        List<model.SanPham> list = sanPhamSer.getThongTin(tblSanPham.getValueAt(i, 0).toString());
        if (tblSanPham.getRowCount() > 0) {
            txtMaSp.setText(tblSanPham.getValueAt(i, 0).toString());
            txtTenSP.setText(tblSanPham.getValueAt(i, 1).toString());
            String giaBan = tblSanPham.getValueAt(i, 8).toString();
            String[] splitss = giaBan.split(".0000$");
            StringBuilder stringBuilder = new StringBuilder();
            for (String x : splitss) {
                stringBuilder.append(x);
            }
            txtGiaBna.setText(stringBuilder.toString());
            txtTonKho.setText(tblSanPham.getValueAt(i, 7).toString());
            for (model.SanPham sanPham : list) {
                String giaNhap = sanPham.getGiaNhap().toString();
                String[] splits = giaNhap.split(".0000$");
                StringBuilder stringBuilder1 = new StringBuilder();
                for (String x : splits) {
                    stringBuilder1.append(x);
                }
                txtGiaNhap.setText(stringBuilder1.toString());
                txtNamBH.setText(sanPham.getNamBH().toString());
                txtMoTa.setText(sanPham.getMoTa());
                cbChip.setSelectedItem(sanPham.getId_Chip());
                cbxMauSac.setSelectedItem(sanPham.getId_MauSac());
                cbKichThuoc.setSelectedItem(sanPham.getId_KhichThuoc());
                cbxXuatXu1.setSelectedItem(sanPham.getId_XuatXu());
            }
            cbDungLuong.setSelectedItem(tblSanPham.getValueAt(i, 5).toString());
            cbxManHinh.setSelectedItem(tblSanPham.getValueAt(i, 6).toString());
            cbxPhanLoai.setSelectedItem(tblSanPham.getValueAt(i, 2).toString());
            cbxPin.setSelectedItem(tblSanPham.getValueAt(i, 4).toString());
            cbxRam.setSelectedItem(tblSanPham.getValueAt(i, 3).toString());

            if (tblSanPham.getValueAt(i, 9).toString().equals("Online")) {
                rdOnline.setSelected(true);
            } else if (tblSanPham.getValueAt(i, 9).toString().equals("Offline")) {
                rdOffline.setSelected(true);
            } else if (tblSanPham.getValueAt(i, 9).toString().equals("Đang chờ hàng về")) {
                rdDangchohangve.setSelected(true);
            } else {
                rdHethang.setSelected(true);
            }
        }
    }

    private boolean validateForm() {
        String ma = txtMaSp.getText().toUpperCase().trim();
        String ten = txtTenSP.getText().toUpperCase().trim();
        String giaNhap = txtGiaNhap.getText().toUpperCase().trim();
        String giaBan = txtGiaBna.getText().toUpperCase().trim();
        String namBH = txtNamBH.getText().toUpperCase().trim();
        String MoTa = txtMoTa.getText().toUpperCase().trim();
        if (ma.isEmpty() || ten.isEmpty() || giaNhap.isEmpty() || giaBan.isEmpty() || namBH.isEmpty() || MoTa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống dữ liệu");
            return false;
        }
        if (cbChip.getItemCount() == 0 || cbDungLuong.getItemCount() == 0 || cbKichThuoc.getItemCount() == 0 || cbxManHinh.getItemCount() == 0 || cbxMauSac.getItemCount() == 0 || cbxPhanLoai.getItemCount() == 0 || cbxPin.getItemCount() == 0 || cbxRam.getItemCount() == 0 || cbxXuatXu1.getItemCount() == 0) {
            JOptionPane.showMessageDialog(this, "Hãy thêm đầy đủ thông tin cho sản phẩm");
            return false;
        }
        try {
            if (BigDecimal.valueOf(Long.parseLong(giaNhap)).compareTo(BigDecimal.valueOf(0)) == -1) {
                JOptionPane.showMessageDialog(this, "Giá nhập không được âm");
                return false;
            }
            if (BigDecimal.valueOf(Long.parseLong(giaBan)).compareTo(BigDecimal.valueOf(0)) == -1) {
                JOptionPane.showMessageDialog(this, "Giá bán không được âm");
                return false;
            }
            if (BigDecimal.valueOf(Long.parseLong(namBH)).compareTo(BigDecimal.valueOf(0)) == -1) {
                JOptionPane.showMessageDialog(this, "Năm bảo hành không được âm");
                return false;
            }
        } catch (Exception e) {
        }
        String format = "^[a-z A-Z 0-9]*$";
        String format1 = "^[a-zA-Z0-9]*$";
        if (!ten.matches(format)) {
            JOptionPane.showMessageDialog(this, "Tên không được chứa kí tự đặc biệt");
            return false;
        }
        if (!ma.matches(format1)) {
            JOptionPane.showMessageDialog(this, "Mã không được chứa kí tự đặc biệt");
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtTimKiem1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        cbChip = new javax.swing.JComboBox<>();
        btnChip = new javax.swing.JButton();
        btnPhanLoaiHang = new javax.swing.JButton();
        cbxPhanLoai = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cbDungLuong = new javax.swing.JComboBox<>();
        btnDungLuong = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        cbKichThuoc = new javax.swing.JComboBox<>();
        btnKichThuoc = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        cbxManHinh = new javax.swing.JComboBox<>();
        btnManHinh = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        cbxPin = new javax.swing.JComboBox<>();
        btnPin = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        cbxRam = new javax.swing.JComboBox<>();
        btnRam = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        cbxImei = new javax.swing.JComboBox<>();
        btnXuatXu = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        cbxMauSac = new javax.swing.JComboBox<>();
        btnMauSac = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtGiaNhap = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtGiaBna = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtTonKho = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtNamBH = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        btnAdd = new javax.swing.JButton();
        btnXoA = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        txtMaSp = new javax.swing.JTextField();
        btnLoad = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        rdOnline = new javax.swing.JRadioButton();
        rdOffline = new javax.swing.JRadioButton();
        rdDangchohangve = new javax.swing.JRadioButton();
        rdHethang = new javax.swing.JRadioButton();
        jLabel29 = new javax.swing.JLabel();
        cbxXuatXu1 = new javax.swing.JComboBox<>();
        btnImei = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiem2 = new javax.swing.JTextField();
        btnHoanTac = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSp1 = new javax.swing.JTable();

        jPanel3.setBackground(new java.awt.Color(158, 195, 192));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("    SẢN PHẨM");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 1035, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(22, 22, 22))
        );

        jTabbedPane1.setBackground(new java.awt.Color(158, 195, 192));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setText("Tìm kiếm :");

        txtTimKiem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiem1ActionPerformed(evt);
            }
        });
        txtTimKiem1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiem1KeyReleased(evt);
            }
        });

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Loại", "RAM", "Pin", "Dung Lượng", "Màn hình", "Số lượng", "Giá bán", "Trạng Thái"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        jLabel13.setText("Chip");

        cbChip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChipActionPerformed(evt);
            }
        });

        btnChip.setIcon(new javax.swing.ImageIcon("F:\\DuAn1\\DuAn1_Thinhokr\\DuAn1_Passed\\src\\icons\\share_icon.png")); // NOI18N
        btnChip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChipActionPerformed(evt);
            }
        });

        btnPhanLoaiHang.setIcon(new javax.swing.ImageIcon("F:\\DuAn1\\DuAn1_Thinhokr\\DuAn1_Passed\\src\\icons\\share_icon.png")); // NOI18N
        btnPhanLoaiHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhanLoaiHangActionPerformed(evt);
            }
        });

        jLabel16.setText("Phân loại hàng");

        jLabel14.setText("Dung lượng");

        btnDungLuong.setIcon(new javax.swing.ImageIcon("F:\\DuAn1\\DuAn1_Thinhokr\\DuAn1_Passed\\src\\icons\\share_icon.png")); // NOI18N
        btnDungLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDungLuongActionPerformed(evt);
            }
        });

        jLabel15.setText("Kích thước");

        btnKichThuoc.setIcon(new javax.swing.ImageIcon("F:\\DuAn1\\DuAn1_Thinhokr\\DuAn1_Passed\\src\\icons\\share_icon.png")); // NOI18N
        btnKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKichThuocActionPerformed(evt);
            }
        });

        jLabel17.setText("Màn hình");

        btnManHinh.setIcon(new javax.swing.ImageIcon("F:\\DuAn1\\DuAn1_Thinhokr\\DuAn1_Passed\\src\\icons\\share_icon.png")); // NOI18N
        btnManHinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManHinhActionPerformed(evt);
            }
        });

        jLabel18.setText("Pin");

        btnPin.setIcon(new javax.swing.ImageIcon("F:\\DuAn1\\DuAn1_Thinhokr\\DuAn1_Passed\\src\\icons\\share_icon.png")); // NOI18N
        btnPin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPinActionPerformed(evt);
            }
        });

        jLabel19.setText("Ram");

        btnRam.setIcon(new javax.swing.ImageIcon("F:\\DuAn1\\DuAn1_Thinhokr\\DuAn1_Passed\\src\\icons\\share_icon.png")); // NOI18N
        btnRam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRamActionPerformed(evt);
            }
        });

        jLabel20.setText("Xuất xứ");

        cbxImei.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxImeiActionPerformed(evt);
            }
        });

        btnXuatXu.setIcon(new javax.swing.ImageIcon("F:\\DuAn1\\DuAn1_Thinhokr\\DuAn1_Passed\\src\\icons\\share_icon.png")); // NOI18N
        btnXuatXu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatXuActionPerformed(evt);
            }
        });

        jLabel21.setText("Màu sắc");

        btnMauSac.setIcon(new javax.swing.ImageIcon("F:\\DuAn1\\DuAn1_Thinhokr\\DuAn1_Passed\\src\\icons\\share_icon.png")); // NOI18N
        btnMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMauSacActionPerformed(evt);
            }
        });

        jLabel12.setText("Tên sản phẩm");

        jLabel22.setText("Giá nhập");

        jLabel23.setText("Giá bán");

        jLabel24.setText("Tồn kho");

        txtTonKho.setEditable(false);

        jLabel25.setText("Năm bảo hành");

        txtNamBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamBHActionPerformed(evt);
            }
        });

        jLabel26.setText("Mô tả");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane2.setViewportView(txtMoTa);

        btnAdd.setBackground(new java.awt.Color(153, 255, 255));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add (2).png"))); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnXoA.setBackground(new java.awt.Color(255, 51, 51));
        btnXoA.setForeground(new java.awt.Color(255, 102, 102));
        btnXoA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delele.png"))); // NOI18N
        btnXoA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoAActionPerformed(evt);
            }
        });

        btnCapNhat.setBackground(new java.awt.Color(102, 204, 255));
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/update (2).png"))); // NOI18N
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        jLabel27.setText("Mã sản phẩm");

        btnLoad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/update (2).png"))); // NOI18N
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        jLabel28.setText("Trạng thái");

        buttonGroup1.add(rdOnline);
        rdOnline.setSelected(true);
        rdOnline.setText("Online");

        buttonGroup1.add(rdOffline);
        rdOffline.setText("Offline");

        buttonGroup1.add(rdDangchohangve);
        rdDangchohangve.setText("Đang chờ hàng về");

        buttonGroup1.add(rdHethang);
        rdHethang.setText("Hết hàng");

        jLabel29.setText("Imei của máy");

        btnImei.setBackground(new java.awt.Color(51, 255, 51));
        btnImei.setIcon(new javax.swing.ImageIcon("F:\\DuAn1\\DuAn1_Thinhokr\\DuAn1_Passed\\src\\icons\\share_icon.png")); // NOI18N
        btnImei.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImeiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 104, Short.MAX_VALUE))
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(cbChip, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(btnChip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(12, 12, 12)))
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnDungLuong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbDungLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(jLabel16)
                                                .addGap(0, 88, Short.MAX_VALUE))
                                            .addComponent(cbxPhanLoai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnPhanLoaiHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(12, 12, 12)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbxPin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnPin, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbxRam, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnRam, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(cbxXuatXu1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnXuatXu, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(12, 12, 12)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(btnImei, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cbxImei, 0, 144, Short.MAX_VALUE))))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbKichThuoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbxManHinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnManHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbxMauSac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(12, 12, 12)
                                .addComponent(btnLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 877, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtGiaBna, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGiaNhap, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txtNamBH))
                            .addComponent(jLabel25)))
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(txtTonKho, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaSp, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(rdOnline)
                        .addGap(18, 18, 18)
                        .addComponent(rdOffline))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(rdDangchohangve)
                        .addGap(18, 18, 18)
                        .addComponent(rdHethang))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoA, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGiaBna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTonKho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNamBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdOffline)
                            .addComponent(rdOnline))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdDangchohangve)
                            .addComponent(rdHethang))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAdd)
                            .addComponent(btnXoA)
                            .addComponent(btnCapNhat))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtTimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbChip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnChip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnDungLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                            .addComponent(jLabel17)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(cbxManHinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel15)
                                                .addComponent(jLabel14))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(cbKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cbDungLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbxMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnMauSac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnManHinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnKichThuoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                            .addComponent(jLabel18)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(cbxPin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(jLabel16)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(cbxPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel19)
                                            .addComponent(jLabel20)
                                            .addComponent(jLabel29))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbxRam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnRam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnPin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnPhanLoaiHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnLoad)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbxXuatXu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbxImei, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnImei, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnXuatXu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(119, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Sản phẩm", jPanel4);

        jPanel5.setBackground(new java.awt.Color(158, 195, 192));

        jPanel6.setBackground(new java.awt.Color(158, 195, 192));

        jLabel1.setText("Tìm kiếm ");

        txtTimKiem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiem2ActionPerformed(evt);
            }
        });
        txtTimKiem2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiem2KeyReleased(evt);
            }
        });

        btnHoanTac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/update (2).png"))); // NOI18N
        btnHoanTac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoanTacActionPerformed(evt);
            }
        });

        tblSp1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Loại", "Ram", "Pin", "Dung lượng", "Màn hình", "Số lượng", "Giá SP"
            }
        ));
        tblSp1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSp1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSp1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHoanTac, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtTimKiem2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnHoanTac)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(216, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1174, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 707, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Đã xóa", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnChipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChipActionPerformed
        // TODO add your handling code here:
        new FormChip().setVisible(true);
    }//GEN-LAST:event_btnChipActionPerformed

    private void btnPhanLoaiHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhanLoaiHangActionPerformed
        // TODO add your handling code here:
        new FormPhanLoaiHang().setVisible(true);
    }//GEN-LAST:event_btnPhanLoaiHangActionPerformed

    private void btnDungLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDungLuongActionPerformed
        // TODO add your handling code here:
        new FormDungLuong().setVisible(true);
    }//GEN-LAST:event_btnDungLuongActionPerformed

    private void btnKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKichThuocActionPerformed
        // TODO add your handling code here:
        new FormKichThuoc().setVisible(true);
    }//GEN-LAST:event_btnKichThuocActionPerformed

    private void btnManHinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManHinhActionPerformed
        // TODO add your handling code here:
        new FormManHinh().setVisible(true);
    }//GEN-LAST:event_btnManHinhActionPerformed

    private void btnPinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPinActionPerformed
        // TODO add your handling code here:
        new FormPin().setVisible(true);
    }//GEN-LAST:event_btnPinActionPerformed

    private void btnRamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRamActionPerformed
        // TODO add your handling code here:
        new FormRam().setVisible(true);
    }//GEN-LAST:event_btnRamActionPerformed

    private void btnXuatXuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatXuActionPerformed
        // TODO add your handling code here:
        new FormXuatXu().setVisible(true);
    }//GEN-LAST:event_btnXuatXuActionPerformed

    private void btnMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMauSacActionPerformed
        // TODO add your handling code here:
        new FormMauSac().setVisible(true);
    }//GEN-LAST:event_btnMauSacActionPerformed

    private void txtNamBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamBHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamBHActionPerformed
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
    private void btnXoAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoAActionPerformed
        int row = tblSanPham.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Mời chọn sản phẩm cần xoá");
            return;
        }
        String sp = tblSanPham.getValueAt(row, 0).toString();
        sanPhamSer.deleteSP(sp);
        int chon = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xoá?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Xoá thành công");
            loadTable();
            loadTbXoa();
        } else {
            return;
        }
    }//GEN-LAST:event_btnXoAActionPerformed

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        loadCbChip();
        loadCbDl();
        loadCbKt();
        loadCbMh();
        loadCbMs();
        loadCbPl();
        loadCbPin();
        loadCbRam();
        loadCbXx();
        loadTable();
        txtGiaBna.setText("");
        txtGiaNhap.setText("");
        txtMaSp.setText("");
        txtMoTa.setText("");
        txtNamBH.setText("");
        txtTenSP.setText("");
        txtTonKho.setText("");
    }//GEN-LAST:event_btnLoadActionPerformed

    private void txtTimKiem2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiem2KeyReleased

        DefaultTableModel model = (DefaultTableModel) tblSp1.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(model);
        tblSp1.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(txtTimKiem2.getText().toUpperCase()));
    }//GEN-LAST:event_txtTimKiem2KeyReleased

    private void btnHoanTacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoanTacActionPerformed

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn hoàn tác ?", "Xác nhân hoàn tác.", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            model.SanPham sp = new model.SanPham();
            sp.setMaSP(tblSp1.getValueAt(tblSp1.getSelectedRow(), 0).toString());
            sanPhamSer.hoanTacSP(sp);
            JOptionPane.showMessageDialog(this, "Hoàn tác thành công");
            loadTable();
            loadTbXoa();
        } else {
            return;
        }

    }//GEN-LAST:event_btnHoanTacActionPerformed

//    private boolean checkMaSP() {
//        for (ViewSanPham x : sanPhamSer.getList()) {
//            if (txtMaSp.getText().equals(x.getMaSp())) {
//                JOptionPane.showMessageDialog(this, "Mã sản phẩm đã tồn tại");
//                return false;
//            } else {
//                return true;
//            }
//        }
//        return true;
//    }
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (validateForm()) {
            if (sanPhamSer.checkTrungMa(txtMaSp.getText().trim())) {
                try {
                    model.SanPham sp = new model.SanPham();
                    sp.setMaSP(txtMaSp.getText().trim());
                    sp.setTenSP(txtTenSP.getText().trim());
                    sp.setGiaNhap(BigDecimal.valueOf(Long.parseLong(txtGiaNhap.getText().trim())));
                    sp.setGiaBan(BigDecimal.valueOf(Long.parseLong(txtGiaBna.getText().trim())));
                    sp.setSoLuongTon(Integer.parseInt(txtTonKho.getText().trim()));
                    sp.setMoTa(txtMoTa.getText().trim());
                    sp.setTrangThai(this.trangThai());
                    sp.setNamBH(Integer.parseInt(txtNamBH.getText().trim()));
                    sp.setId_DLuong(cbDungLuong.getSelectedItem().toString());
                    sp.setId_MauSac(cbxMauSac.getSelectedItem().toString());
                    sp.setId_XuatXu(cbxXuatXu1.getSelectedItem().toString());
                    sp.setId_PhanLoai(cbxPhanLoai.getSelectedItem().toString());
                    sp.setId_Pin(cbxPin.getSelectedItem().toString());
                    sp.setId_Chip(cbChip.getSelectedItem().toString());
                    sp.setId_Ram(cbxRam.getSelectedItem().toString());
                    sp.setId_ManHinh(cbxManHinh.getSelectedItem().toString());
                    sp.setId_KhichThuoc(cbKichThuoc.getSelectedItem().toString());

                    if (sanPhamSer.addSP(sp) == 1) {
                        if (sanPhamSer.checkTrungMaGiamGiaSP()) {
                            sanPhamSer.insertMaGiamGiaSPFreeSanPham(sp);
//                JOptionPane.showMessageDialog(this, "ĐÃ có max FREE");
                        } else {
                            if (sanPhamSer.insertMaGiamGiaFreeAllSanPham()) {
//                    sanPhamSer.insertMaGiamGiaFreeAllSanPham();

                                sanPhamSer.insertMaGiamGiaSPFreeSanPham(sp);
//                    JOptionPane.showMessageDialog(this, "chưa có mã FREE");
                            }
                        }
//            JOptionPane.showMessageDialog(this, "OK");
                    }
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                    loadTable();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Giá, năm phải là số");
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Trùng mã sản phẩm");
                return;
            }

        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        if (validateForm()) {
            if (!sanPhamSer.checkTrungMa(txtMaSp.getText().trim())) {
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa ?", "Xác nhân sửa.", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        model.SanPham sp = new model.SanPham();
                        sp.setMaSP(tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 0).toString());
                        sp.setTenSP(txtTenSP.getText().trim());
                        sp.setGiaNhap(BigDecimal.valueOf(Long.parseLong(txtGiaNhap.getText().trim())));
                        sp.setGiaBan(BigDecimal.valueOf(Long.parseLong(txtGiaBna.getText().trim())));
                        sp.setSoLuongTon(Integer.parseInt(txtTonKho.getText().trim()));
                        sp.setMoTa(txtMoTa.getText().trim());
                        sp.setTrangThai(this.trangThai());
                        sp.setNamBH(Integer.parseInt(txtNamBH.getText().trim()));
                        sp.setId_DLuong(cbDungLuong.getSelectedItem().toString());
                        sp.setId_MauSac(cbxMauSac.getSelectedItem().toString());
                        sp.setId_XuatXu(cbxXuatXu1.getSelectedItem().toString());
                        sp.setId_PhanLoai(cbxPhanLoai.getSelectedItem().toString());
                        sp.setId_Pin(cbxPin.getSelectedItem().toString());
                        sp.setId_Chip(cbChip.getSelectedItem().toString());
                        sp.setId_Ram(cbxRam.getSelectedItem().toString());
                        sp.setId_ManHinh(cbxManHinh.getSelectedItem().toString());
                        sp.setId_KhichThuoc(cbKichThuoc.getSelectedItem().toString());
                        sp.setMaSPCT("hi");
                        sanPhamSer.updateSP(sp);
                        JOptionPane.showMessageDialog(this, "Sửa thành công");
                        loadTable();
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Giá, năm phải là số");
                        return;
                    }
                } else {
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Mã sản phẩm không tồn tại");
                return;
            }
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void txtTimKiem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiem1ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(model);
        tblSanPham.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(txtTimKiem1.getText().toUpperCase()));
    }//GEN-LAST:event_txtTimKiem1ActionPerformed

    private void txtTimKiem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiem2ActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblSp1.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(model);
        tblSp1.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(txtTimKiem2.getText().toUpperCase()));
    }//GEN-LAST:event_txtTimKiem2ActionPerformed

    private void tblSp1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSp1MouseClicked
        int i = tblSp1.getSelectedRow();
        loadTf(i);
    }//GEN-LAST:event_tblSp1MouseClicked

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int i = tblSanPham.getSelectedRow();
        loadImei();
        loadTf(i);
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void cbChipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbChipActionPerformed

    private void txtTimKiem1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiem1KeyReleased
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(model);
        tblSanPham.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(txtTimKiem1.getText().toUpperCase()));
    }//GEN-LAST:event_txtTimKiem1KeyReleased

    private void btnImeiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImeiActionPerformed
        new FormImei().setVisible(true);
    }//GEN-LAST:event_btnImeiActionPerformed

    private void cbxImeiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxImeiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxImeiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnChip;
    private javax.swing.JButton btnDungLuong;
    private javax.swing.JButton btnHoanTac;
    private javax.swing.JButton btnImei;
    private javax.swing.JButton btnKichThuoc;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnManHinh;
    private javax.swing.JButton btnMauSac;
    private javax.swing.JButton btnPhanLoaiHang;
    private javax.swing.JButton btnPin;
    private javax.swing.JButton btnRam;
    private javax.swing.JButton btnXoA;
    private javax.swing.JButton btnXuatXu;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbChip;
    private javax.swing.JComboBox<String> cbDungLuong;
    private javax.swing.JComboBox<String> cbKichThuoc;
    private javax.swing.JComboBox<String> cbxImei;
    private javax.swing.JComboBox<String> cbxManHinh;
    private javax.swing.JComboBox<String> cbxMauSac;
    private javax.swing.JComboBox<String> cbxPhanLoai;
    private javax.swing.JComboBox<String> cbxPin;
    private javax.swing.JComboBox<String> cbxRam;
    private javax.swing.JComboBox<String> cbxXuatXu1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdDangchohangve;
    private javax.swing.JRadioButton rdHethang;
    private javax.swing.JRadioButton rdOffline;
    private javax.swing.JRadioButton rdOnline;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblSp1;
    private javax.swing.JTextField txtGiaBna;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtMaSp;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtNamBH;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTimKiem1;
    private javax.swing.JTextField txtTimKiem2;
    private javax.swing.JTextField txtTonKho;
    // End of variables declaration//GEN-END:variables
}
