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
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.lang.System.Logger.Level;
import view.formPhuKH.TaoNhanhKH;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.HDChiTiet;
import model.HoaDon;
import service.DiemService;
import service.DungLuongService;
import service.HDCTService;
import service.HoaDonChiTietBanHangService;
import service.HoaDonChiTietLuongbanHangService;
import service.HoaDonService;
import service.ImeiBanHangService;
import service.ImeiService;
import service.KhachHangService;
import service.PhanLoaiHangService;
import service.impl.HDCTServiceImpl;
import service.impl.SPServiceImpl;
import viewModel.HoaDonChiTiet;
import viewModel.SanPhamViewModel;
import service.SPService;
import service.SanPhamService;
import service.impl.DiemServiceImpl;
import service.impl.DungLuongServiceImpl;
import service.impl.HoaDonChiTietBanHangServiceIplm;
import service.impl.HoaDonChiTietLuongbanHangServiceIplm;
import service.impl.HoaDonServiceImpl;
import service.impl.ImeiBanHangServiceIplm;
import service.impl.ImeiServiceImpl;
import service.impl.KhachHangServiceImpl;
import service.impl.PhanLoaiHangServiceImpl;
import service.impl.SanPhamServiceImpl;
import view.formPhuSP.ChiTietSP;
import viewModel.DungLuongViewModel;
import viewModel.HoaDonChiTietBanHangViewModel;
import viewModel.HoaDonChiTietLuongbanHangViewModel;
import viewModel.ImeiBanHang;
import viewModel.ImeiViewModel;
import viewModel.PhanLoaiHangViewModel;
import viewModel.PinViewModel;
import viewModel.ViewSanPham;

/**
 *
 * @author phamtuyetnga
 */
public class FormBanHang extends javax.swing.JPanel implements Runnable, ThreadFactory {

    private final HoaDonChiTietLuongbanHangService hoaDonChiTietLuongBanHangServices = new HoaDonChiTietLuongbanHangServiceIplm();
    private ImeiBanHangService imeiBhSer = new ImeiBanHangServiceIplm();
    private ImeiService imeiSer = new ImeiServiceImpl();
    private final DiemService diemService = new DiemServiceImpl();
    private final KhachHangService khachHangService = new KhachHangServiceImpl();
    private final SPService sanPhamService = new SPServiceImpl();
    private final SanPhamService sps = new SanPhamServiceImpl();
    private final HDCTService hoaDonCTService = new HDCTServiceImpl();
    private final HoaDonService hoaDonService = new HoaDonServiceImpl();
    private final DungLuongService dungLuongService = new DungLuongServiceImpl();
    private ArrayList<HoaDonChiTiet> list = new ArrayList<>();
    private ArrayList<ViewSanPham> list2 = new ArrayList<>();
    private ArrayList<HoaDon> list1 = new ArrayList<>();
    private ArrayList<HoaDonChiTietLuongbanHangViewModel> listl = new ArrayList<>();
    public static ArrayList<HoaDonChiTietBanHangViewModel> hdct = new ArrayList<>();
    private final PhanLoaiHangService loaiHangService = new PhanLoaiHangServiceImpl();
    private DefaultTableModel dtm;
    private DefaultTableModel dtmgioHang;
    private DefaultTableModel dtmHoaDon;
    private BigDecimal sum = new BigDecimal(0);
    Integer quantity = 0;
    public static String maSp = null;
    public static String maSp1 = null;
    public static String tenSp = null;
    public static BigDecimal donGia = null;
    public static String maHD = null;
    static int index = 0;
    // tạo đối tượng bảng hoá đơn chi tiết trong form bán hàng
    private final HoaDonChiTietBanHangService hoaDonChiTietBanHangServices = new HoaDonChiTietBanHangServiceIplm();
    private ArrayList<ImeiViewModel> listImeiAdd = new ArrayList<>();
    private ArrayList<ImeiViewModel> listImeiDelete = new ArrayList<>();
    public static boolean checkButton = false;
    public FormBanHang() {
        initComponents();
        loadDL();
        loadLH();
        loadTable(hoaDonChiTietLuongBanHangServices.getAll());
        addTableHoaDon(hoaDonService.getList());
        tblSanPham.setComponentPopupMenu(popMenu);
        tblDonHang.setComponentPopupMenu(delete);
        clok();
        //txtMaNV.setEnabled(false);
        txtMaNV.setText(Login1.maNV);
        txtMaNhanVien.setText(Login1.maNV);
//        txtTienThua.setEnabled(false);
//        txtTongTien.setEnabled(false);
        //txtNgayTao.setEditable(false);
        txtTienCK.setText("0");
        txtTienKD.setText("0");
        txtTienThua.setText("0");
        txtTienCK.setEnabled(false);
        txtTienKD.setEnabled(false);
        rdoKhongSd.setSelected(true);
        txtSDT.setEditable(false);
        txtDiem.setEditable(false);
        txtSoDiemSD.setEnabled(false);
        txtSoDiemSD.setText("0");
//        txtMaHoaDon.setEnabled(false);
//        txtTongTienOl.setEnabled(false);
        jDialogImeiBanHang.setSize(1075, 455);
        jDialogImeiBanHang.setLocationRelativeTo(null);
        txtNgayNhan.getSettings().setAllowKeyboardEditing(false);
        txtNgayThanhToan.getSettings().setAllowKeyboardEditing(false);
        txtNgayTao.getSettings().setAllowKeyboardEditing(false);
        txtNgayShip.getSettings().setAllowKeyboardEditing(false);
        //txtMaNV.setEnabled(false);
        Qrcode.setSize(500, 500);
        
      
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

//    private void loadTableListImeiBanHang(ArrayList<ImeiBanHang> list) {
//        DefaultTableModel model = (DefaultTableModel) tblImei.getModel();
//        model.setRowCount(0);
//        for (ImeiBanHang x : list) {
//            model.addRow(new Object[]{x.getMaImei(), x.getMaSanPham(), false});
//        }
//
//    }
//
//    private void loadTableListImeiGioHang(ArrayList<ImeiViewModel> list) {
//        DefaultTableModel model = (DefaultTableModel) tblImei1.getModel();
//        model.setRowCount(0);
//        for (ImeiViewModel x : list) {
//            model.addRow(new Object[]{x.getMaImei(), false});
//        }
//
//    }
    private boolean checkTrong() {
        try {
            if (Integer.parseInt(txtThemSoLuongSanPhamJDialog.getText().trim()) <= 0 || Integer.parseInt(txtThemSoLuongSanPhamJDialog.getText().trim()) > 10000000) {
                JOptionPane.showMessageDialog(this.jDialogImeiBanHang, "Nhập sai lương!", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.jDialogImeiBanHang, "Nhập sai định dạng lương!", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void loadTbaleGioHangRong() {
        dtmgioHang = (DefaultTableModel) tblDonHang.getModel();
        dtmgioHang.setRowCount(0);
    }

    public void loadDL() {
        cbxLocDL.removeAllItems();
        for (DungLuongViewModel x : dungLuongService.getAll()) {
            cbxLocDL.addItem(x.getTen());
        }
    }

    public void loadLH() {
        cbxPhanLoai.removeAllItems();
        for (PhanLoaiHangViewModel x : loaiHangService.getAll()) {
            cbxPhanLoai.addItem(x.getTen());
        }
    }

    public void loadTable(ArrayList<ViewSanPham> list) {
        dtm = (DefaultTableModel) tblSanPham.getModel();
        dtm.setRowCount(0);
        for (ViewSanPham qLChiTietSanPham : list) {
            dtm.addRow(new Object[]{
                qLChiTietSanPham.getMaSp(),
                qLChiTietSanPham.getTenSp(),
                qLChiTietSanPham.getSoLuong(),
                qLChiTietSanPham.getDungLuong(),
                qLChiTietSanPham.getPhanLoai(),
                qLChiTietSanPham.getGiaSp(),
                qLChiTietSanPham.trangThai()
            });
        }
    }

    public void addTableGioHangBanHang(ArrayList<HoaDonChiTietLuongbanHangViewModel> list) {// loadTable giỏ hàng ở bán hàng - Phong Nguyễn
        BigDecimal sum = new BigDecimal(0);
        dtmgioHang = (DefaultTableModel) tblDonHang.getModel();
        dtmgioHang.setRowCount(0);
//        
        for (HoaDonChiTietLuongbanHangViewModel chiTietHoaDon : list) {

//            BigDecimal tong = chiTietHoaDon.getDonGia().multiply(BigDecimal.valueOf(chiTietHoaDon.getSoLuong()));
            BigDecimal tong = chiTietHoaDon.getTongTien();
            dtmgioHang.addRow(new Object[]{
                chiTietHoaDon.getMaSanPham(),
                chiTietHoaDon.getTenSanPham(),
                chiTietHoaDon.getSoLuongSanPham(),
                chiTietHoaDon.getDonGia(),
                chiTietHoaDon.getMucGiamGia(),
                chiTietHoaDon.getSoLuongImeiSanPhamKT(),
                chiTietHoaDon.getDoiTra() == 10 ? true : false,
                chiTietHoaDon.getTongTien(),});
            sum = sum.add(tong);

//            quantity += chiTietHoaDon.getSoLuongSanPham();
            txtTongTien.setText(String.valueOf(sum));
            String[] splits = txtTongTien.getText().split(".0000$");
            StringBuilder stringBuilder1 = new StringBuilder();
            for (String x : splits) {
                stringBuilder1.append(x);
            }
            txtTongTien.setText(stringBuilder1.toString());
            txtTongTienOl.setText(stringBuilder1.toString());
        }

        try {
            BigDecimal tienKhachDua = BigDecimal.valueOf(Long.parseLong(txtTienKD.getText()));
            BigDecimal tienThua = tienKhachDua.subtract(sum);
            txtTienThua.setText(String.valueOf(tienThua));
        } catch (NumberFormatException e) {
            return;
        }
    }

    private void addTableHoaDon(ArrayList<HoaDon> list1) {
        dtmHoaDon = (DefaultTableModel) tblHoaDon.getModel();
        dtmHoaDon.setRowCount(0);
        List<HoaDon> list = hoaDonService.getList();
        for (HoaDon hoaDon : list1) {
            dtmHoaDon.addRow(new Object[]{
                hoaDon.getMaHD(),
                hoaDon.getNgayTao(),
                //hoaDon.getTrangThai() == 1 ? "Đã thanh toán" : "Chờ thanh toán",
                hoaDon.TrangThai2(),
                hoaDon.getTenKH(),
                hoaDon.getTrangThai() == 4 ? true : false
            });
        }
    }

    public Integer TrangThai() {
        int row = tblHoaDon.getSelectedRow();
        if (tblHoaDon.getValueAt(row, 3).equals(1)) {

        }
        return null;
    }

    public void loadTextField(int i) {
        if (tblHoaDon.getRowCount() > 0) {
            txtMaNV.setText(tblHoaDon.getValueAt(i, 1).toString());
            txtNgayTao.setText(tblHoaDon.getValueAt(i, 2).toString());
        }
    }

    private void clok() {
        Thread th = new Thread() {
            @Override
            public void run() {
                while (true) {
                    SimpleDateFormat clock = new SimpleDateFormat();
                    clock.applyPattern("HH:mm:ss   dd-MM-yyyy");
                    Date date = new Date();
                    lblLock.setText(clock.format(date));
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                }
            }
        };
        th.start();
    }

    private boolean checkRong() {
      
         if (hoaDonChiTietBanHangServices.listHDCTBanHang(txtMaHD.getText().trim()).size() <= 0) {
            JOptionPane.showMessageDialog(this, "Mời thêm sản phẩm vào đơn hàng");
            return false;
        }
         
        for (int i = 0; i < tblDonHang.getRowCount(); i++) {
            String soLg = tblDonHang.getValueAt(i, 2).toString();
            String soLgImei = tblDonHang.getValueAt(i, 5).toString();
            System.out.println("" + soLg);
            System.out.println("" + soLgImei);
            if (Integer.parseInt(soLg) < Integer.parseInt(soLgImei)) {
                JOptionPane.showMessageDialog(this, "Vui Lòng Nhập đủ số lượng máy và imei");
                return false;
            } else if (Integer.parseInt(soLg) > Integer.parseInt(soLgImei)) {
                JOptionPane.showMessageDialog(this, "Vui Lòng Nhập đủ số lượng máy và imei");
                return false;
            } else {
                //JOptionPane.showMessageDialog(this, "ok");
            }
        }
//        }
//         for (int i = 0; i <tblDonHang.getRowCount(); i++) {
//             if(tblDonHang.getValueAt(i,6)==null){
//                 JOptionPane.showMessageDialog(this,"Vui Lòng Chọn Imei ");
//                 return false;
//             }
//         }
        String maHD = txtMaHD.getText().toLowerCase().trim();
        if (maHD.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui Lòng chọn hóa Đơn Hoặc Tạo Hóa Đơn");
            return false;
        }
        if (cbxPTTT.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Vui Lòng chọn hình thức thanh toán");
            return false;
        }
        try {
            if (Double.parseDouble(txtTienKD.getText().trim()) < 0 || Double.parseDouble(txtTienCK.getText().trim()) < 0) {
                JOptionPane.showMessageDialog(this, "Tiền không dược nhập số âm");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Nhập sai định dạng tiền");
            return false;
        }
        if (Double.parseDouble(txtTienThua.getText().trim()) < 0) {
            JOptionPane.showMessageDialog(this, "Nhập thiếu tiền");
            return false;
        }
//       
       
        return true;
    }

    private boolean checkFormOnl() {
        String maNV = txtMaNhanVien.getText().toLowerCase().trim();
        String ngayThanhToan = txtNgayThanhToan.getText().toLowerCase().trim();
        String ngayShip = txtNgayShip.getText().toLowerCase().trim();
//        String ngayNhan = datePicker1.getText().toLowerCase().trim();
        String tenKh = txtTenKH.getText().toLowerCase().trim();
        String diaChi = txtDiaChi.getText().toLowerCase().trim();
        String sdtNguoiNhan = txtSDTNguoiNhan.getText().toLowerCase().trim();
        String sdtNguoiShip = txtSDTNguoiShip.getText().toLowerCase().trim();
        String tenNguoiShip = txtTenNguoiShip.getText().toLowerCase().trim();
//        String soLuong = txtSoLuong.getText().toLowerCase().trim();

        if (hoaDonChiTietBanHangServices.listHDCTBanHang(txtMaHD.getText().trim()).size() <= 0) {
            JOptionPane.showMessageDialog(this, "Mời thêm sản phẩm vào đơn hàng");
            return false;
        }

        for (int i = 0; i < tblDonHang.getRowCount(); i++) {
            String soLg = tblDonHang.getValueAt(i, 2).toString();
            String soLgImei = tblDonHang.getValueAt(i, 5).toString();
            System.out.println("" + soLg);
            System.out.println("" + soLgImei);
//            if (Integer.parseInt(soLg) < Integer.parseInt(soLgImei)) {
//                JOptionPane.showMessageDialog(this, "Vui Long nhap di so luong may");
//                return false;
//            } else if (Integer.parseInt(soLg) > Integer.parseInt(soLgImei)) {
//                JOptionPane.showMessageDialog(this, "Vui Long nhap di so luong may");
//                return false;
//            } else {
//                JOptionPane.showMessageDialog(this, "ok");
//            }
        }

        if (maNV.isEmpty() || tenKh.isEmpty() || diaChi.isEmpty()
                || sdtNguoiNhan.isEmpty() || sdtNguoiShip.isEmpty() || tenNguoiShip.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Dữ Liệu Rỗng");
            return false;
        }
        if (cbxPhuongThucThanhToan.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Vui Lòng chọn hình thức thanh toán");
            return false;
        }
        String chekcSdt = "[0][0-9]{9}";
        if (!sdtNguoiNhan.matches(chekcSdt) || !sdtNguoiShip.matches(chekcSdt)) {
            JOptionPane.showMessageDialog(this, "Nhâp Đúng số Điện thoại "
                    + "VD:0965670192");
            return false;
        }

        return true;
    }

    private void hoanTra() {
        try {
            if (hoaDonService.selectTrangThaiHoaDon(txtMaHD.getText().trim()) == 1) {
//            String maHD = txtMaHD.getText();
                hoaDonService.updateTTDaHoan(txtMaHD.getText());
                addTableHoaDon(hoaDonService.getList());
                JOptionPane.showMessageDialog(this, "Đổi trả thành công");
                for (int i = 0; i < tblHoaDon.getRowCount(); i++) {
                    if (txtMaHD.getText().endsWith(tblHoaDon.getValueAt(i, 0).toString())) {
                        tblHoaDon.setValueAt(true, i, 4);
                        list.removeAll(list);
                    }
                }
                for (int i = 0; i < tblDonHang.getRowCount(); i++) {

                    HoaDonChiTietLuongbanHangViewModel hdctlbh = new HoaDonChiTietLuongbanHangViewModel();
                    hdctlbh.setMaSanPham(tblDonHang.getValueAt(i, 0).toString());
                    tblDonHang.setValueAt(true, i, 6);
                    listl.add(hdctlbh);
                }

            } else if (txtMaHD.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mời chọn hóa đơn hàng cần đổi trả");
                return;

            } else if (hoaDonService.selectTrangThaiHoaDon(txtMaHD.getText().trim()) == 0) {
                JOptionPane.showMessageDialog(this, "*HOÁ ĐƠN CHƯA THANH TOÁN\n-KHÔNG THỂ Hoàn trả", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi");
            return;
        }

    }

    private void hoanTraonline() {
        try {
            if (hoaDonService.selectTrangThaiHoaDon(txtMaHD.getText().trim()) == 1) {
//            String maHD = txtMaHD.getText();
                hoaDonService.updateTTDaHoan(txtMaHD.getText());
                addTableHoaDon(hoaDonService.getList());
                JOptionPane.showMessageDialog(this, "Đổi trả thành công");
                for (int i = 0; i < tblHoaDon.getRowCount(); i++) {
                    if (txtMaHD.getText().endsWith(tblHoaDon.getValueAt(i, 0).toString())) {
                        tblHoaDon.setValueAt(true, i, 4);
                        list.removeAll(list);
                    }
                }
                for (int i = 0; i < tblDonHang.getRowCount(); i++) {

                    HoaDonChiTietLuongbanHangViewModel hdctlbh = new HoaDonChiTietLuongbanHangViewModel();
                    hdctlbh.setMaSanPham(tblDonHang.getValueAt(i, 0).toString());
                    tblDonHang.setValueAt(true, i, 6);
                    listl.add(hdctlbh);
                }

            } else if (txtMaHD.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mời chọn hóa đơn hàng cần đổi trả");
                return;

            } else if (hoaDonService.selectTrangThaiHoaDon(txtMaHD.getText().trim()) == 0) {
                JOptionPane.showMessageDialog(this, "*HOÁ ĐƠN CHƯA THANH TOÁN\n-KHÔNG THỂ Hoàn trả", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (hoaDonService.selectTrangThaiHoaDon(txtMaHD.getText().trim()) == 2) {
                JOptionPane.showMessageDialog(this, "*HOÁ ĐƠN ĐANG GIAO\n-KHÔNG THỂ Hoàn trả", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi");
            return;
        }
    }

    /// JDialog Giỏ hàng
    //
    public void loadCbThemImeiJDialog() {

        cbThemImeiSPVaoGHJDialog.removeAllItems();
        cbThemSoLuongSPJDialog.removeAllItems();

        cbThemImeiSPVaoGHJDialog.addItem("CHỌN MÃ SP");
        cbThemSoLuongSPJDialog.addItem("CHỌN MÃ SP");

        for (HoaDonChiTietLuongbanHangViewModel x : hoaDonChiTietLuongBanHangServices.getAllCBImeiSanPhamJDialog(txtMaHD.getText().trim())) {
            cbThemImeiSPVaoGHJDialog.addItem(x.getMaSanPham());
            cbThemSoLuongSPJDialog.addItem(x.getMaSanPham());
        }
    }

    public void loadCbXoaSanPhamJDialog() {

        cbXoaSoLuongSPJDialog.removeAllItems();

        cbXoaSoLuongSPJDialog.addItem("CHỌN MÃ SP");

        for (HoaDonChiTietLuongbanHangViewModel x : hoaDonChiTietLuongBanHangServices.getCBXoaSoLuongSanPhamJDialog(txtMaHD.getText().trim())) {
            cbXoaSoLuongSPJDialog.addItem(x.getMaSanPham());
        }
    }

    public void loadCbXoaImeiJDialog() {

        cbXoaImeiSPKhoiGHJDialog.removeAllItems();
//        cbXoaSoLuongSPJDialog.removeAllItems();

        cbXoaImeiSPKhoiGHJDialog.addItem("CHỌN MÃ SP");
//        cbXoaSoLuongSPJDialog.addItem("CHỌN MÃ SP");

        for (HoaDonChiTietLuongbanHangViewModel x : hoaDonChiTietLuongBanHangServices.getCBImeiXoaSanPhamJDialog(txtMaHD.getText().trim())) {
            cbXoaImeiSPKhoiGHJDialog.addItem(x.getMaSanPham());
//            cbXoaSoLuongSPJDialog.addItem(x.getMaSanPham());
        }
    }

    // load table imei
    private void loadTableListImeiBanHang(ArrayList<HoaDonChiTietLuongbanHangViewModel> list) {
        DefaultTableModel model = (DefaultTableModel) tblImei.getModel();
        model.setRowCount(0);
        for (HoaDonChiTietLuongbanHangViewModel x : list) {
            model.addRow(new Object[]{x.getMaImei(), x.getMaSanPham(), x.getTenSanPham()});
        }

    }
// load table xoá imei giỏ hàng

    private void loadTableListXoaImeiGioHang(ArrayList<HoaDonChiTietLuongbanHangViewModel> list) {
        DefaultTableModel model = (DefaultTableModel) tblImeiXoa.getModel();

        model.setRowCount(0);
        for (HoaDonChiTietLuongbanHangViewModel x : list) {
            model.addRow(new Object[]{x.getMaImei(), x.getMaSanPham(), x.getTenSanPham()});
        }

    }

    // load table imei giỏ hàng
    private void loadTableListImeiGioHang(ArrayList<HoaDonChiTietLuongbanHangViewModel> list) {
//        DefaultTableModel model = (DefaultTableModel) tblImeiGioHangJDialog.getModel();
//
//        model.setRowCount(0);
//        for (HoaDonChiTietLuongbanHangViewModel x : list) {
//            model.addRow(new Object[]{x.getMaSanPham(), x.getTenSanPham(), x.getMaImei(), 1});
//        }

    }

    // 
    private void loadtableJDialog() {
        loadTableListImeiGioHang(hoaDonChiTietLuongBanHangServices.getAllImeiXoaSanPhamJDialog(txtMaHD.getText().trim()));
        loadTableListXoaImeiGioHang(hoaDonChiTietLuongBanHangServices.getAllImeiXoaSanPhamJDialog(txtMaHD.getText().trim()));
        loadCbXoaSanPhamJDialog();
        loadTableListImeiBanHang(hoaDonChiTietLuongBanHangServices.getAllImeiSanPhamJDialog(txtMaHD.getText().trim()));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        popMenu = new javax.swing.JPopupMenu();
        ghiChu = new javax.swing.JMenuItem();
        thoat = new javax.swing.JMenuItem();
        delete = new javax.swing.JPopupMenu();
        deleteAll = new javax.swing.JMenuItem();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jDialogImeiBanHang = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblImei = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiemJDialogImeiBanHang = new javax.swing.JTextField();
        btnOKJDiaLogImeiBanHang = new javax.swing.JButton();
        cbThemImeiSPVaoGHJDialog = new javax.swing.JComboBox<>();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblImeiXoa = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtTimKiemXoaJDialogImeiBanHang = new javax.swing.JTextField();
        btnOKXoaJDiaLogImeiBanHang = new javax.swing.JButton();
        chkXoaImeiJDialog = new javax.swing.JCheckBox();
        cbXoaImeiSPKhoiGHJDialog = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        txtThemSoLuongSanPhamJDialog = new javax.swing.JTextField();
        btlThemSoLuongSanPhamJDialog = new javax.swing.JButton();
        cbThemSoLuongSPJDialog = new javax.swing.JComboBox<>();
        jPanel16 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        txtGiamSoLuongSanPhamJDialog = new javax.swing.JTextField();
        btlGiamSoLuongSanPhamJDialog = new javax.swing.JButton();
        cbXoaSoLuongSPJDialog = new javax.swing.JComboBox<>();
        jPanel14 = new javax.swing.JPanel();
        btnThoatJDialodImeiBanHang = new javax.swing.JButton();
        jDialogImeiGioHang = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        btnOk1 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblImei1 = new javax.swing.JTable();
        txtTimKiem3 = new javax.swing.JTextField();
        cbChonTatCaXoa = new javax.swing.JCheckBox();
        Qrcode = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        Thoat = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        cbxLocDL = new javax.swing.JComboBox<>();
        cbxPhanLoai = new javax.swing.JComboBox<>();
        tbnQr = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDonHang = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        cbLocHD = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        tab = new javax.swing.JTabbedPane();
        PnOffline = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtTienKD = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        btnChon = new javax.swing.JButton();
        btnThayDoi = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        txtSoDiemSD = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        cbxPTTT = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        btnSuDung = new javax.swing.JButton();
        txtTienCK = new javax.swing.JTextField();
        txtTongTien = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();
        rdoSd = new javax.swing.JRadioButton();
        rdoKhongSd = new javax.swing.JRadioButton();
        btnThanhToan = new javax.swing.JButton();
        btnDoiTra = new javax.swing.JButton();
        txtDiem = new javax.swing.JTextField();
        btnTaoHD = new javax.swing.JButton();
        txtNgayTao = new com.github.lgooddatepicker.components.DatePicker();
        PnOnline = new javax.swing.JPanel();
        txtTenNguoiShip = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtTenkh = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        txtTongTienOl = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSDTNguoiNhan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtSDTNguoiShip = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        txtMaNhanVien = new javax.swing.JTextField();
        btnXacNhan = new javax.swing.JButton();
        btnDoiTraOnl = new javax.swing.JButton();
        btnDatHang = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        cbxPhuongThucThanhToan = new javax.swing.JComboBox<>();
        btnTaoHDOnline = new javax.swing.JButton();
        txtNgayShip = new com.github.lgooddatepicker.components.DatePicker();
        txtNgayThanhToan = new com.github.lgooddatepicker.components.DatePicker();
        txtNgayNhan = new com.github.lgooddatepicker.components.DatePicker();
        btnGiaoHang = new javax.swing.JButton();
        txt_NgayTaoOL = new com.github.lgooddatepicker.components.DatePicker();
        lblLock = new javax.swing.JLabel();

        ghiChu.setText("GhiChu");
        ghiChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ghiChuActionPerformed(evt);
            }
        });
        popMenu.add(ghiChu);

        thoat.setText("jMenuItem1");
        popMenu.add(thoat);

        deleteAll.setText("Xoá tất cả");
        deleteAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAllActionPerformed(evt);
            }
        });
        delete.add(deleteAll);

        jPanel10.setBackground(new java.awt.Color(158, 195, 192));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblImei.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ IMEI", "MÃ SP", "TÊN SẢN PHẨM", "CHỌN"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblImei.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblImeiMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblImei);

        jLabel2.setText("ALL IMEI SẢN PHẨM CÓ TRONG GIỎ HÀNG");

        jLabel1.setText("TÌM KIẾM");

        txtTimKiemJDialogImeiBanHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemJDialogImeiBanHangKeyReleased(evt);
            }
        });

        btnOKJDiaLogImeiBanHang.setText("THÊM IMEI");
        btnOKJDiaLogImeiBanHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKJDiaLogImeiBanHangActionPerformed(evt);
            }
        });

        cbThemImeiSPVaoGHJDialog.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbThemImeiSPVaoGHJDialog.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbThemImeiSPVaoGHJDialogItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTimKiemJDialogImeiBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOKJDiaLogImeiBanHang))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbThemImeiSPVaoGHJDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbThemImeiSPVaoGHJDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemJDialogImeiBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnOKJDiaLogImeiBanHang))
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblImeiXoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ IMEI", "MÃ SP", "TÊN SẢN PHẨM", "CHỌN"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblImeiXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblImeiXoaMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblImeiXoa);

        jLabel29.setText("ALL IMEI ĐƯỢC THÊM VÀO GIỎ HÀNG");

        jLabel35.setText("TÌM KIẾM");

        txtTimKiemXoaJDialogImeiBanHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemXoaJDialogImeiBanHangKeyReleased(evt);
            }
        });

        btnOKXoaJDiaLogImeiBanHang.setText("XOÁ IMEI");
        btnOKXoaJDiaLogImeiBanHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKXoaJDiaLogImeiBanHangActionPerformed(evt);
            }
        });

        chkXoaImeiJDialog.setText("ALL IMEI");
        chkXoaImeiJDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkXoaImeiJDialogMouseClicked(evt);
            }
        });
        chkXoaImeiJDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkXoaImeiJDialogActionPerformed(evt);
            }
        });

        cbXoaImeiSPKhoiGHJDialog.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbXoaImeiSPKhoiGHJDialog.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbXoaImeiSPKhoiGHJDialogItemStateChanged(evt);
            }
        });
        cbXoaImeiSPKhoiGHJDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbXoaImeiSPKhoiGHJDialogActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTimKiemXoaJDialogImeiBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOKXoaJDiaLogImeiBanHang)
                        .addGap(18, 18, 18)
                        .addComponent(chkXoaImeiJDialog))
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addComponent(jLabel29)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbXoaImeiSPKhoiGHJDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(cbXoaImeiSPKhoiGHJDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemXoaJDialogImeiBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(btnOKXoaJDiaLogImeiBanHang)
                    .addComponent(chkXoaImeiJDialog))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel37.setText("CẬP NHẬT SỐ LƯỢNG SẢN PHẨM TRONG GIỎ HÀNG");

        jPanel15.setBackground(new java.awt.Color(204, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel38.setText("THÊM SỐ LƯỢNG SẢN PHẨM");

        btlThemSoLuongSanPhamJDialog.setText("THÊM SỐ LƯỢNG SP");
        btlThemSoLuongSanPhamJDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlThemSoLuongSanPhamJDialogActionPerformed(evt);
            }
        });

        cbThemSoLuongSPJDialog.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbThemSoLuongSPJDialog, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(txtThemSoLuongSanPhamJDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btlThemSoLuongSanPhamJDialog)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(cbThemSoLuongSPJDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtThemSoLuongSanPhamJDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btlThemSoLuongSanPhamJDialog))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(204, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel39.setText("XOÁ SỐ LƯỢNG SẢN PHẨM");

        btlGiamSoLuongSanPhamJDialog.setText("XOÁ SỐ LƯỢNG SP");
        btlGiamSoLuongSanPhamJDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlGiamSoLuongSanPhamJDialogActionPerformed(evt);
            }
        });

        cbXoaSoLuongSPJDialog.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbXoaSoLuongSPJDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbXoaSoLuongSPJDialogActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGiamSoLuongSanPhamJDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btlGiamSoLuongSanPhamJDialog)
                    .addComponent(cbXoaSoLuongSPJDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(cbXoaSoLuongSPJDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiamSoLuongSanPhamJDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btlGiamSoLuongSanPhamJDialog))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 204));
        jPanel14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnThoatJDialodImeiBanHang.setText("THOÁT");
        btnThoatJDialodImeiBanHang.setBackground(new java.awt.Color(255, 51, 0));
        btnThoatJDialodImeiBanHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThoatJDialodImeiBanHang.setForeground(new java.awt.Color(255, 255, 255));
        btnThoatJDialodImeiBanHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatJDialodImeiBanHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThoatJDialodImeiBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(btnThoatJDialodImeiBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jDialogImeiBanHangLayout = new javax.swing.GroupLayout(jDialogImeiBanHang.getContentPane());
        jDialogImeiBanHang.getContentPane().setLayout(jDialogImeiBanHangLayout);
        jDialogImeiBanHangLayout.setHorizontalGroup(
            jDialogImeiBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogImeiBanHangLayout.setVerticalGroup(
            jDialogImeiBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btnOk1.setText("OK");
        btnOk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOk1ActionPerformed(evt);
            }
        });

        tblImei1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "MÃ IMEI", "CHỌN"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblImei1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblImei1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblImei1MousePressed(evt);
            }
        });
        jScrollPane6.setViewportView(tblImei1);

        txtTimKiem3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiem3KeyReleased(evt);
            }
        });

        cbChonTatCaXoa.setText("Chọn tất cả");
        cbChonTatCaXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChonTatCaXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(btnOk1))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtTimKiem3, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81)
                                .addComponent(cbChonTatCaXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbChonTatCaXoa))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(btnOk1)
                .addGap(91, 91, 91))
        );

        javax.swing.GroupLayout jDialogImeiGioHangLayout = new javax.swing.GroupLayout(jDialogImeiGioHang.getContentPane());
        jDialogImeiGioHang.getContentPane().setLayout(jDialogImeiGioHangLayout);
        jDialogImeiGioHangLayout.setHorizontalGroup(
            jDialogImeiGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogImeiGioHangLayout.setVerticalGroup(
            jDialogImeiGioHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Thoat.setText("THOÁT");
        Thoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThoatActionPerformed(evt);
            }
        });

        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(341, Short.MAX_VALUE)
                .addComponent(Thoat)
                .addGap(47, 47, 47))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(Thoat)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout QrcodeLayout = new javax.swing.GroupLayout(Qrcode.getContentPane());
        Qrcode.getContentPane().setLayout(QrcodeLayout);
        QrcodeLayout.setHorizontalGroup(
            QrcodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QrcodeLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        QrcodeLayout.setVerticalGroup(
            QrcodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QrcodeLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(158, 195, 192));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SẢN PHẨM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel3MouseReleased(evt);
            }
        });

        jLabel3.setText("Tìm kiếm");
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));

        txtTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimActionPerformed(evt);
            }
        });
        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKeyReleased(evt);
            }
        });

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Số lượng tồn", "Dung lượng", "Phân loại hàng", "Giá bán", "Trạng thái"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblSanPhamMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        jLabel12.setText("Lọc");
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));

        cbxLocDL.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxLocDLItemStateChanged(evt);
            }
        });
        cbxLocDL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxLocDLMouseClicked(evt);
            }
        });
        cbxLocDL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxLocDLActionPerformed(evt);
            }
        });

        cbxPhanLoai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxPhanLoaiItemStateChanged(evt);
            }
        });

        tbnQr.setIcon(new javax.swing.ImageIcon("F:\\DuAn1\\DuAn1_Thinhokr\\DuAn1_Passed\\src\\icons\\MaVach.png")); // NOI18N
        tbnQr.setBackground(new java.awt.Color(204, 255, 255));
        tbnQr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnQrActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxLocDL, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(tbnQr, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxLocDL)
                            .addComponent(cbxPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(tbnQr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        jPanel4.setBackground(new java.awt.Color(158, 195, 192));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "GIỎ HÀNG", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel4.setPreferredSize(new java.awt.Dimension(573, 226));

        tblDonHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Số lượng", "Giá bán", "Mức giảm giá", "SL Imei", "Đổi", "Tổng tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDonHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDonHangMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblDonHangMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblDonHang);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(158, 195, 192));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HOÁ ĐƠN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel6.setPreferredSize(new java.awt.Dimension(573, 226));

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HĐ", "Ngày tạo", "Trạng thái", "Họ tên KH", "Đổi"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHoaDon);

        jLabel17.setText("Lọc");
        jLabel17.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));

        cbLocHD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Chưa thanh toán ", "Đã thanh toán" }));
        cbLocHD.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbLocHDItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbLocHD, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbLocHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(158, 195, 192));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "THANH TOÁN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N

        tab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabMouseClicked(evt);
            }
        });

        PnOffline.setBackground(new java.awt.Color(255, 255, 255));

        jLabel23.setText("SDT Khách Hàng");

        jLabel16.setText("PTTT");

        jLabel24.setText("Số điểm KH ");

        txtTienKD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKDKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTienKDKeyTyped(evt);
            }
        });

        jLabel25.setText("Mã NV");

        jPanel7.setBackground(new java.awt.Color(158, 195, 192));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel18.setText("Mã KH");

        txtMaKH.setEditable(false);

        jLabel19.setText("Tên KH");

        txtTenKH.setEditable(false);

        btnChon.setText("Thêm & Chọn");
        btnChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonActionPerformed(evt);
            }
        });

        btnThayDoi.setText("Thay đổi");
        btnThayDoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThayDoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnChon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThayDoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChon))
                .addGap(4, 4, 4)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThayDoi))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel26.setText("Số điểm SD");

        txtMaNV.setEditable(false);

        txtSoDiemSD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoDiemSDKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSoDiemSDKeyTyped(evt);
            }
        });

        jLabel27.setText("Mã HĐ");

        txtMaHD.setEditable(false);
        txtMaHD.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtMaHD.setForeground(new java.awt.Color(255, 0, 51));
        txtMaHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMaHDMouseClicked(evt);
            }
        });

        jLabel28.setText("Ngày tạo");

        jLabel30.setText("Tổng tiền");

        jLabel21.setText("Tiền chuyển khoản");

        cbxPTTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hình thức thanh toán", "Tiền mặt", "Chuyển khoản", "Tiền mặt & Chuyển khoản" }));
        cbxPTTT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxPTTTItemStateChanged(evt);
            }
        });
        cbxPTTT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxPTTTMouseClicked(evt);
            }
        });

        jLabel22.setText("Tiền thừa");

        btnSuDung.setText("Sử dụng");
        btnSuDung.setBackground(new java.awt.Color(153, 153, 153));
        btnSuDung.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSuDung.setForeground(new java.awt.Color(255, 255, 255));
        btnSuDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuDungActionPerformed(evt);
            }
        });

        txtTienCK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienCKKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTienCKKeyTyped(evt);
            }
        });

        txtTongTien.setEditable(false);
        txtTongTien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTongTien.setForeground(new java.awt.Color(255, 0, 0));

        jLabel31.setText("Tích điểm");

        jLabel32.setText("Tiền khách đưa");
        jLabel32.setBackground(new java.awt.Color(0, 0, 0));

        txtTienThua.setEditable(false);
        txtTienThua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtTienThua.setForeground(new java.awt.Color(204, 0, 0));
        txtTienThua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienThuaActionPerformed(evt);
            }
        });

        rdoSd.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoSd);
        rdoSd.setText("Sử dụng");
        rdoSd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoSdMouseClicked(evt);
            }
        });
        rdoSd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoSdActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoKhongSd);
        rdoKhongSd.setText("Không sử dụng");
        rdoKhongSd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoKhongSdMouseClicked(evt);
            }
        });

        btnThanhToan.setText("Thanh toán");
        btnThanhToan.setBackground(new java.awt.Color(204, 0, 0));
        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnDoiTra.setText("Đổi");
        btnDoiTra.setBackground(new java.awt.Color(204, 255, 255));
        btnDoiTra.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDoiTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiTraActionPerformed(evt);
            }
        });

        txtDiem.setEditable(false);
        txtDiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtDiem.setForeground(new java.awt.Color(255, 0, 51));

        btnTaoHD.setText("Tạo Hóa Đơn");
        btnTaoHD.setBackground(new java.awt.Color(255, 102, 0));
        btnTaoHD.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnTaoHD.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHDActionPerformed(evt);
            }
        });

        txtNgayTao.setEnabled(false);

        javax.swing.GroupLayout PnOfflineLayout = new javax.swing.GroupLayout(PnOffline);
        PnOffline.setLayout(PnOfflineLayout);
        PnOfflineLayout.setHorizontalGroup(
            PnOfflineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnOfflineLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(PnOfflineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnOfflineLayout.createSequentialGroup()
                        .addGroup(PnOfflineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel24))
                        .addGap(35, 35, 35)
                        .addGroup(PnOfflineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(PnOfflineLayout.createSequentialGroup()
                                .addComponent(txtSoDiemSD, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSuDung, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PnOfflineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnTaoHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PnOfflineLayout.createSequentialGroup()
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PnOfflineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PnOfflineLayout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addGap(49, 49, 49)
                                .addComponent(rdoSd)
                                .addGap(18, 18, 18)
                                .addComponent(rdoKhongSd))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PnOfflineLayout.createSequentialGroup()
                                .addGroup(PnOfflineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(txtTienKD))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PnOfflineLayout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTienCK))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PnOfflineLayout.createSequentialGroup()
                                .addGroup(PnOfflineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(54, 54, 54)
                                .addGroup(PnOfflineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTienThua)
                                    .addComponent(cbxPTTT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PnOfflineLayout.createSequentialGroup()
                                .addComponent(btnDoiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PnOfflineLayout.createSequentialGroup()
                                .addGroup(PnOfflineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(PnOfflineLayout.createSequentialGroup()
                                        .addGap(106, 106, 106)
                                        .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PnOfflineLayout.createSequentialGroup()
                                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(PnOfflineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(PnOfflineLayout.createSequentialGroup()
                                        .addGap(82, 82, 82)
                                        .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PnOfflineLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(PnOfflineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(PnOfflineLayout.createSequentialGroup()
                                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))))))))
                .addGap(350, 350, 350))
        );
        PnOfflineLayout.setVerticalGroup(
            PnOfflineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnOfflineLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTaoHD, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnOfflineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnOfflineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel30)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnOfflineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(txtTienKD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnOfflineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtTienCK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnOfflineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnOfflineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(cbxPTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnOfflineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(rdoSd)
                    .addComponent(rdoKhongSd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnOfflineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnOfflineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnOfflineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtSoDiemSD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuDung))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnOfflineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDoiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(132, 132, 132))
        );

        tab.addTab("OFFLINE", PnOffline);

        PnOnline.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setText("Ngày Ship");

        jLabel9.setText("Ngày Nhận");

        jLabel10.setText("Tên KH");

        jLabel11.setText("Địa Chỉ");

        txtDiaChi.setColumns(20);
        txtDiaChi.setRows(5);
        jScrollPane4.setViewportView(txtDiaChi);

        jLabel13.setText("Tổng Tiền");

        txtTongTienOl.setEditable(false);

        jLabel4.setText("Mã HĐ");

        jLabel14.setText("SĐT Người Nhận");

        jLabel5.setText("Mã NV");

        jLabel6.setText("Ngày Tạo");

        jLabel15.setText("SĐT Người Ship");

        jLabel33.setText("Ngày Thanh Toán");

        jLabel34.setText("Tên Người Ship");

        txtMaHoaDon.setEditable(false);
        txtMaHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHoaDonActionPerformed(evt);
            }
        });

        txtMaNhanVien.setEditable(false);

        btnXacNhan.setText("Xác nhận");
        btnXacNhan.setBackground(new java.awt.Color(255, 204, 102));
        btnXacNhan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXacNhan.setForeground(new java.awt.Color(51, 51, 51));
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        btnDoiTraOnl.setText("Đổi");
        btnDoiTraOnl.setBackground(new java.awt.Color(204, 255, 255));
        btnDoiTraOnl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDoiTraOnl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiTraOnlActionPerformed(evt);
            }
        });

        btnDatHang.setText("ĐẶT HÀNG");
        btnDatHang.setBackground(new java.awt.Color(255, 51, 51));
        btnDatHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDatHang.setForeground(new java.awt.Color(255, 255, 255));
        btnDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatHangActionPerformed(evt);
            }
        });

        jLabel36.setText("HTTT");

        cbxPhuongThucThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hình thức thanh toán", "Tiền mặt", "Chuyển khoản", " " }));

        btnTaoHDOnline.setText("Tạo Hóa Đơn");
        btnTaoHDOnline.setBackground(new java.awt.Color(255, 102, 0));
        btnTaoHDOnline.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnTaoHDOnline.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoHDOnline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHDOnlineActionPerformed(evt);
            }
        });

        txtNgayShip.setEnabled(false);

        txtNgayThanhToan.setEnabled(false);

        txtNgayNhan.setEnabled(false);

        btnGiaoHang.setText("Giao Hàng");
        btnGiaoHang.setBackground(new java.awt.Color(204, 255, 255));
        btnGiaoHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnGiaoHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGiaoHangActionPerformed(evt);
            }
        });

        txt_NgayTaoOL.setEnabled(false);

        javax.swing.GroupLayout PnOnlineLayout = new javax.swing.GroupLayout(PnOnline);
        PnOnline.setLayout(PnOnlineLayout);
        PnOnlineLayout.setHorizontalGroup(
            PnOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnOnlineLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnTaoHDOnline, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PnOnlineLayout.createSequentialGroup()
                        .addGroup(PnOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel9)
                            .addComponent(jLabel4)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel34)
                            .addComponent(jLabel36))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addGroup(PnOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtSDTNguoiNhan)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PnOnlineLayout.createSequentialGroup()
                                .addGroup(PnOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMaHoaDon)
                                    .addGroup(PnOnlineLayout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(txtNgayThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                                    .addComponent(txtNgayNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_NgayTaoOL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(26, 26, 26)
                                .addGroup(PnOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(PnOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8)
                                        .addGroup(PnOnlineLayout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addGap(41, 41, 41)
                                            .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(PnOnlineLayout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(PnOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtNgayShip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtTenkh)))))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTongTienOl, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSDTNguoiShip)
                            .addComponent(txtTenNguoiShip)
                            .addComponent(cbxPhuongThucThanhToan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PnOnlineLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PnOnlineLayout.createSequentialGroup()
                        .addComponent(btnDoiTraOnl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDatHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGiaoHang, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        PnOnlineLayout.setVerticalGroup(
            PnOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnOnlineLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTaoHDOnline, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PnOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnOnlineLayout.createSequentialGroup()
                        .addGroup(PnOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_NgayTaoOL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PnOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(txtNgayThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PnOnlineLayout.createSequentialGroup()
                        .addGroup(PnOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtNgayShip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PnOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtNgayNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PnOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTongTienOl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(PnOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(cbxPhuongThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(PnOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtSDTNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtSDTNguoiShip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(txtTenNguoiShip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PnOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDoiTraOnl, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGiaoHang, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(120, Short.MAX_VALUE))
        );

        tab.addTab("ONLINE", PnOnline);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblLock, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblLock, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private ArrayList<model.SanPham> sps1 = new ArrayList<>();

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
//        if (hoaDonService.selectTrangThaiHoaDon(txtMaHD.getText().trim()) == 0) {
//            HoaDonChiTietBanHangViewModel hd = new HoaDonChiTietBanHangViewModel();// tạo đối tượng hoá đơn chi tiết HDChiTiet - phong nguyễn
//            int row = tblSanPham.getSelectedRow();
//            if (txtMaHD.getText().equals("")) {
//                JOptionPane.showMessageDialog(this, "Mời tạo hoá đơn");
//                int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có muốn tạo HĐ không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
//                if (JOptionPane.YES_OPTION == luaChon) {
//                    taoHD();
//                } else {
//                    JOptionPane.showMessageDialog(this, "Mời tạo hoá đơn");
//                    return;
//                }
//
//            } else {
//////            if () {
////                String soLuong = JOptionPane.showInputDialog(this, "Mời nhập số lượng:", "1");
////
////                int b = Integer.parseInt(soLuong);
////                if (soLuong != null) {
////                    if (Integer.parseInt(soLuong) > Integer.parseInt(tblSanPham.getValueAt(row, 3) + "")) {
////                        JOptionPane.showMessageDialog(this, "Số lượng sản phẩm bạn vừa nhập vượt quá số lượng trong kho!");
////                        return;
////                    } else if (Integer.parseInt(soLuong) <= 0) {
////                        JOptionPane.showMessageDialog(this, "Số lượng sản phẩm không được nhỏ hơn 0");
////                        return;
////                    }
////
////                    // thêm đối tượng sản phâm vào hoá đơn chi tiết
////                    hd.setMaSanPham(tblSanPham.getValueAt(row, 1).toString());
////                    String[] splits = tblSanPham.getValueAt(row, 6).toString().split(".0000$");
////                    StringBuilder stringBuilder1 = new StringBuilder();
////                    for (String x : splits) {
////                        stringBuilder1.append(x);
////                    }
////                    hd.setDonGia(BigDecimal.valueOf(Long.valueOf(stringBuilder1.toString())));
////                    hd.setSoLuong(b);
////
////                    // thêm đối tượng sản phâm vào hoá đơn chi tiết - phong nguyễn
////                    if (hoaDonChiTietBanHangServices.kiemTraCheckTrung(hd, txtMaHD.getText().trim())) {
////                        hoaDonChiTietBanHangServices.suaSoLuongSanPhamTrongHDCT(hd, txtMaHD.getText().trim());
////                        addTableGioHangBanHang(hoaDonChiTietBanHangServices.listHDCTBanHang(txtMaHD.getText().trim()));
////
////                    } else {
////                        hdct.add(hd);
////                        hoaDonChiTietBanHangServices.themVaoHoaDonChiTiet(hd, txtMaHD.getText().trim());
////                        addTableGioHangBanHang(hoaDonChiTietBanHangServices.listHDCTBanHang(txtMaHD.getText().trim()));
////
////                    }
////
////                    // update lại số lượng sản phẩm trong bảng sản phẩm
////                    sanPhamService.updateQuantity(tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 1).toString(), Integer.parseInt(soLuong));
////
////                    // load lại table sản phẩm
////                    loadTable(sps.getList());
////
////                }
//
////                try {
//                String soLuong = JOptionPane.showInputDialog(this, "Mời nhập số lượng:");
//
//                if (soLuong != null && !soLuong.isEmpty()) {
//                    int b = Integer.parseInt(soLuong);
//                    if (Integer.parseInt(soLuong) > Integer.parseInt(tblSanPham.getValueAt(row, 3) + "")) {
//                        JOptionPane.showMessageDialog(this, "Số lượng sản phẩm bạn vừa nhập vượt quá số lượng trong kho!");
//                        return;
//                    } else if (Integer.parseInt(soLuong) <= 0) {
//                        JOptionPane.showMessageDialog(this, "Số lượng sản phẩm không được nhỏ hơn 0");
//                        return;
//                    }
//
//                    // thêm đối tượng sản phâm vào hoá đơn chi tiết
//                    hd.setMaSanPham(tblSanPham.getValueAt(row, 1).toString());
//                    String[] splits = tblSanPham.getValueAt(row, 6).toString().split(".0000$");
//                    StringBuilder stringBuilder1 = new StringBuilder();
//                    for (String x : splits) {
//                        stringBuilder1.append(x);
//                    }
//                    hd.setDonGia(BigDecimal.valueOf(Long.valueOf(stringBuilder1.toString())));
//                    //hd.setDonGia(BigDecimal.valueOf(Double.parseDouble(tblSanPham.getValueAt(row, 6).toString())));
//                    hd.setSoLuong(b);
//
//                    // thêm đối tượng sản phâm vào hoá đơn chi tiết - phong nguyễn
//                    if (hoaDonChiTietBanHangServices.kiemTraCheckTrung(hd, txtMaHD.getText().trim())) {
//                        hoaDonChiTietBanHangServices.suaSoLuongSanPhamTrongHDCT(hd, txtMaHD.getText().trim());
//                        addTableGioHangBanHang(hoaDonChiTietBanHangServices.listHDCTBanHang(txtMaHD.getText().trim()));
//
//                    } else {
//                        hoaDonChiTietBanHangServices.themVaoHoaDonChiTiet(hd, txtMaHD.getText().trim());
//                        addTableGioHangBanHang(hoaDonChiTietBanHangServices.listHDCTBanHang(txtMaHD.getText().trim()));
//
//                    }
//
//                    // update lại số lượng sản phẩm trong bảng sản phẩm
//                    sanPhamService.updateQuantity(tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 1).toString(), Integer.parseInt(soLuong));
//
//                    // load lại table sản phẩm
//                    loadTable(sps.getList());
//
//                } else {
//                    JOptionPane.showMessageDialog(this, "Số luọng không được để rỗng!");
//                    return;
//                }
//            }
//        } else if (hoaDonService.selectTrangThaiHoaDon(txtMaHD.getText().trim()) == 1) {
//            JOptionPane.showMessageDialog(this, "*HOÁ ĐƠN ĐÃ THANH TOÁN\n-KHÔNG THỂ THÊM SẢN PHẨM", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
//            return;
//        } else {
//            JOptionPane.showMessageDialog(this, "Lỗi", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
//            return;
//        }


    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void loadText() {
        int i = tblHoaDon.getSelectedRow();
        txtMaHD.setText(tblHoaDon.getValueAt(i, 0).toString());
        txtNgayTao.setDate(LocalDate.parse(tblHoaDon.getValueAt(i, 1).toString()));
        txtTenKH.setText(tblHoaDon.getValueAt(i, 3).toString());

    }

    private void loadTextOnline() {
        int i = tblHoaDon.getSelectedRow();
        txtMaHoaDon.setText(tblHoaDon.getValueAt(i, 0).toString());
        txt_NgayTaoOL.setDate(LocalDate.parse(tblHoaDon.getValueAt(i, 1).toString()));
        txtTenkh.setText(tblHoaDon.getValueAt(i, 3).toString());
//        for (HoaDonChiTietBanHangViewModel x : hoaDonChiTietBanHangServices.listHDCTBanHang(maHD)) {
//            txtSoLuong.setText(String.valueOf(x.getSoLuong()));
//        }
        //txtNgayNhan.setText(tblHoaDon.getValueAt(i, 5).toString());
    }

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
//        maHD = tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 1).toString();
        int i = tblHoaDon.getSelectedRow();
        loadText();
        loadTextOnline();
        addTableGioHangBanHang(hoaDonChiTietLuongBanHangServices.getAllHDCT(txtMaHD.getText().trim()));
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void txtTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimActionPerformed

    private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyReleased
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(model);
        tblSanPham.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(txtTim.getText().toUpperCase()));
    }//GEN-LAST:event_txtTimKeyReleased

    private void taoHD() {
        HoaDon hoaDon = new HoaDon();
//        
        hoaDon.setNgayTao(String.valueOf(java.time.LocalDate.now()));
        hoaDon.setTrangThai(0);
        hoaDon.setMaNV(txtMaNV.getText().trim());
        hoaDonService.saveHoaDon(hoaDon);
        
        model.KhachHang khachHang = new model.KhachHang();
        txtTenKH.setText("Khách hàng");
        addTableHoaDon(hoaDonService.getList());
        //index=tblHoaDon.getRowCount()-1;
        tblHoaDon.setRowSelectionInterval(index, index);
        txtMaHD.setText(tblHoaDon.getValueAt(index, 0).toString());
    }

    private void taoHDONLINE() {
        HoaDon hoaDon = new HoaDon();
//        double i = Math.random() * 100 + 1;
//        int c = (int) i;
//        hoaDon.setMaHD("HDONL" + c);
//        txtMaHoaDon.setText("HDONL" + c);
        hoaDon.setNgayTao(String.valueOf(java.time.LocalDate.now()));
        hoaDon.setTrangThai(0);
        hoaDon.setMaNV(txtMaHoaDon.getText().trim());
        hoaDonService.saveHoaDon(hoaDon);
        model.KhachHang khachHang = new model.KhachHang();
        txtTenkh.setText("Khách hàng");
        addTableHoaDon(hoaDonService.getList());
        //index=tblHoaDon.getRowCount()-1;
        tblHoaDon.setRowSelectionInterval(index, index);
    }


    private void tblSanPhamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseReleased

    }//GEN-LAST:event_tblSanPhamMouseReleased

    private void jPanel3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseReleased

    }//GEN-LAST:event_jPanel3MouseReleased

    private void tblSanPhamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMousePressed
        if (evt.getClickCount() == 2) {

            if (hoaDonService.selectTrangThaiHoaDon(txtMaHD.getText().trim()) != 1) {
                // tạo đối tượng hoá đơn chi tiết HDChiTiet - phong nguyễn
                HoaDonChiTietLuongbanHangViewModel hd = new HoaDonChiTietLuongbanHangViewModel();
                int row = tblSanPham.getSelectedRow();
                if (txtMaHD.getText().equals("")) {
                    int luaChon = JOptionPane.showConfirmDialog(this, "*Chưa Có Hoá Đơn\n-Bạn Có Muốn Tạo Hoá Đơn Mới không?", "XÁC NHẬN", JOptionPane.YES_NO_OPTION);
                    if (JOptionPane.YES_OPTION == luaChon) {
                        taoHD();
                    } else {
                        JOptionPane.showMessageDialog(this, "*Mời Chọn Hoá Đơn Trước Khi Thêm Sản Phẩm!");
                        return;
                    }

                } else {
                    try {
                        String soLuong = JOptionPane.showInputDialog(this, "Mời nhập số lượng:", "1");

                        int soLuongNhap = Integer.parseInt(soLuong);
                        if (soLuong != null) {
                            if (soLuongNhap > Integer.parseInt(tblSanPham.getValueAt(row, 2).toString())) {
                                JOptionPane.showMessageDialog(this, "Số lượng sản phẩm bạn vừa nhập vượt quá số lượng trong kho!");
                                return;
                            } else if (soLuongNhap <= 0) {
                                JOptionPane.showMessageDialog(this, "Số lượng sản phẩm không được nhỏ hơn 0");
                                return;
                            }

                            hd.setMaSanPham(tblSanPham.getValueAt(row, 0).toString());
                            hd.setSoLuongSanPham(Integer.parseInt(soLuong));

                            String[] splits = tblSanPham.getValueAt(row, 5).toString().split(".0000$");
                            StringBuilder stringBuilder1 = new StringBuilder();
                            for (String x : splits) {
                                stringBuilder1.append(x);
                            }
                            hd.setDonGia(BigDecimal.valueOf(Long.valueOf(stringBuilder1.toString())));

//
                            // thêm đối tượng sản phâm vào hoá đơn chi tiết - phong nguyễn
                            if (hoaDonChiTietLuongBanHangServices.kiemTraCheckTrung(hd, txtMaHD.getText().trim())) {
                                hoaDonChiTietLuongBanHangServices.suaSoLuongSanPhamTrongHDCT(hd, txtMaHD.getText().trim());
                                addTableGioHangBanHang(hoaDonChiTietLuongBanHangServices.getAllHDCT(txtMaHD.getText().trim()));

                            } else {

                                hoaDonChiTietLuongBanHangServices.themVaoHoaDonChiTiet(hd, txtMaHD.getText().trim());
                                hoaDonChiTietLuongBanHangServices.addImeiKTMacDinh(tblSanPham.getValueAt(row, 0).toString(), txtMaHD.getText().trim());
                                addTableGioHangBanHang(hoaDonChiTietLuongBanHangServices.getAllHDCT(txtMaHD.getText().trim()));

                            }

//                       // update lại số lượng sản phẩm trong bảng sản phẩm
                            sanPhamService.updateQuantity(tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 0).toString(), Integer.parseInt(soLuong));

                            // load lại table sản phẩm
                            loadTable(hoaDonChiTietLuongBanHangServices.getAll());

                        } else {
                            return;
                        }
                    } catch (Exception e) {
                        return;
                    }
                }
            } else if (hoaDonService.selectTrangThaiHoaDon(txtMaHD.getText().trim()) == 1) {
                JOptionPane.showMessageDialog(this, "*HOÁ ĐƠN ĐÃ THANH TOÁN\n-KHÔNG THỂ THÊM SẢN PHẨM", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

    }//GEN-LAST:event_tblSanPhamMousePressed

    private void cbxLocDLItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxLocDLItemStateChanged

        ArrayList<ViewSanPham> sc = sanPhamService.dungLuong(cbxLocDL.getSelectedItem().toString());
        loadTable(sc);
    }//GEN-LAST:event_cbxLocDLItemStateChanged

    private void cbxLocDLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxLocDLMouseClicked

    }//GEN-LAST:event_cbxLocDLMouseClicked

    private void cbxLocDLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxLocDLActionPerformed

    }//GEN-LAST:event_cbxLocDLActionPerformed

    private void ghiChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ghiChuActionPerformed
        new ChiTietSP().setVisible(true);
    }//GEN-LAST:event_ghiChuActionPerformed

    public void thanhToan() {

        //    BigDecimal tong = chiTietHoaDon.getDonGia().multiply(BigDecimal.valueOf(chiTietHoaDon.getSoLuong()));
        HoaDon hoaDon = new HoaDon();
        String maHD = txtMaHD.getText();
        if (maHD.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mời tạo hoá đơn để thanh tóan");
            return;
        }
//        if (txtMaKH.getText().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Mời chọn khách hàng để thanh tóan");
//            return;
//        }
        if (hoaDonChiTietBanHangServices.listHDCTBanHang(txtMaHD.getText().trim()).size() <= 0) {
            JOptionPane.showMessageDialog(this, "Mời thêm sản phẩm vào đơn hàng");
            return;
        } else {
            FormQuyDoiDiem qd = new FormQuyDoiDiem();
            try {
                BigDecimal a = BigDecimal.valueOf(Long.valueOf(txtDiem.getText())).subtract(BigDecimal.valueOf(Long.valueOf(txtSoDiemSD.getText())));
                BigDecimal b = BigDecimal.valueOf(Long.valueOf(txtTongTien.getText())).divide(BigDecimal.valueOf(Long.valueOf(qd.getTienTichDiem())));
                BigDecimal soDiem = a.add(b);
                khachHangService.updateSoLanMua(txtMaKH.getText());
                diemService.updateSoDiem(Float.parseFloat(String.valueOf(soDiem)), txtMaKH.getText());
                khachHangService.lichSuDiem(Float.parseFloat(txtSoDiemSD.getText()), Float.parseFloat(String.valueOf(b)), txtMaKH.getText(), txtMaHD.getText());
//                if (Integer.parseInt(txtSoDiemSD.getText().trim()) > Integer.parseInt(txtDiem.getText().trim())) {
//                    JOptionPane.showMessageDialog(this, "Số điểm sử dụng vượt quá số điểm hiện tại");
//                    return;
//                }
//                if (Integer.parseInt(txtSoDiemSD.getText().trim()) < 0) {
//                    JOptionPane.showMessageDialog(this, "Số điểm sử dụng không được âm");
//                    return;
//                }
            } catch (NumberFormatException e) {
                //JOptionPane.showMessageDialog(this, "Nhập sai định dạng");
//                return;
            }
            for (ImeiViewModel x : imeiBhSer.getAllImeiKT1(txtMaHD.getText())) {
                imeiSer.updateTT1(x.getMaImei());
            }
            hoaDonService.HDTT(cbxPTTT.getSelectedItem().toString(), BigDecimal.valueOf(Long.valueOf(txtTienKD.getText())), txtMaHD.getText(), txtMaNV.getText(), quantity, BigDecimal.valueOf(Long.valueOf(txtTongTien.getText())), txtMaKH.getText());
            hoaDonService.updateTrangThai(maHD);
            addTableHoaDon(hoaDonService.getList());

            hoaDon.setNgayThanhToan(String.valueOf(java.time.LocalDate.now()));
            JOptionPane.showMessageDialog(this, "Thanh toán thành công");
            list.removeAll(list);
//             in hóa đơn
            int confirm1 = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn In Hóa Đơn Không ?", "In Hóa Đơn", JOptionPane.YES_NO_OPTION);
            if (confirm1 == JOptionPane.YES_OPTION) {
                String path = "";
                JFileChooser j = new JFileChooser();
                j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int x = j.showSaveDialog(this);
                if (x == JFileChooser.APPROVE_OPTION) {
                    path = j.getSelectedFile().getPath();
                }
                Document doc = new Document();

                try {
                    try {
                        PdfWriter.getInstance(doc, new FileOutputStream(path + "HoaDon.pdf"));
                        doc.open();
                        for (int i = 0; i < tblHoaDon.getRowCount(); i++) {
                            if (txtMaHD.getText().equals(tblHoaDon.getValueAt(i, 0).toString())) {
                                String maHD1 = tblHoaDon.getValueAt(i, 0).toString();
                                String NgayTao = tblHoaDon.getValueAt(i, 1).toString();
                                String Khachhang = tblHoaDon.getValueAt(i, 3).toString();

                                Paragraph paragraph0 = new Paragraph("Ngay tao: " + NgayTao);
                                paragraph0.setIndentationLeft(50);
                                Paragraph paragraph1 = new Paragraph("Ma hoa don: " + maHD1);
                                paragraph1.setIndentationLeft(50);
                                Paragraph paragraph2 = new Paragraph("HOA DON BAN HANG");
                                paragraph2.setIndentationLeft(180);
                                paragraph2.setIndentationRight(80);
                                paragraph2.setSpacingAfter(15);
                                String maNV = txtMaNV.getText();
                                Paragraph paragraph3 = new Paragraph("Nhan vien: " + maNV);
                                paragraph3.setIndentationLeft(50);
                                Paragraph paragraph4 = new Paragraph("Khach hang: " + Khachhang);
                                paragraph4.setIndentationLeft(50);
                                String SDT = txtSDT.getText();
                                Paragraph paragraph5 = new Paragraph("SDT: " + SDT);
                                paragraph5.setIndentationLeft(50);
                                paragraph5.setSpacingAfter(10);
                                doc.add(paragraph0);
                                doc.add(paragraph1);
                                doc.add(paragraph2);
                                doc.add(paragraph3);
                                doc.add(paragraph4);
                                doc.add(paragraph5);

//                            doc.add(paragraph6);
                            }
                        }

                        PdfPTable tbl = new PdfPTable(5);

                        tbl.addCell("Ma San Pham ");
                        tbl.addCell("Ten San Pham");
                        tbl.addCell("So Luong");
                        tbl.addCell("Gia Ban");
                        tbl.addCell("Muc Giam Gia");

                        for (int i = 0; i < tblDonHang.getRowCount(); i++) {
                            String id = tblDonHang.getValueAt(i, 0).toString();
                            String id1 = tblDonHang.getValueAt(i, 1).toString();
                            String id2 = tblDonHang.getValueAt(i, 2).toString();
                            String id3 = tblDonHang.getValueAt(i, 3).toString();
                            String id4 = tblDonHang.getValueAt(i, 4).toString();

                            tbl.addCell(id);
                            tbl.addCell(id1);
                            tbl.addCell(id2);
                            tbl.addCell(id3);
                            tbl.addCell(id4);
                        }

                        doc.add(tbl);
                        String tongTien = txtTongTien.getText();

                        Paragraph paragraph6 = new Paragraph("Tong Tien: " + tongTien + ".000" + "VND");
                        paragraph6.setIndentationLeft(50);

                        Paragraph paragraph7 = new Paragraph("Cam on quy khach da den cua hang dien thoai");
                        paragraph7.setIndentationLeft(120);
                        paragraph7.setIndentationRight(80);
                        doc.add(paragraph6);
                        doc.add(paragraph7);

                    } catch (DocumentException ex) {
                        //java.util.logging.Logger.getLogger(FormBanHang.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (FileNotFoundException ex) {
                    //java.util.logging.Logger.getLogger(FormBanHang.class.getName()).log(Level.SEVERE, null, ex);
                }

                doc.close();
            } else {
                return;
            }
        }

    }

    public void thanhToanOnl() {
        HoaDon hoaDon = new HoaDon();
        String maHD = txtMaHoaDon.getText();
        if (maHD.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mời tạo hoá đơn để thanh tóan");
            return;
        }
//        if (txtTenkh.getText().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Mời chọn khách hàng để thanh tóan");
//            return;
//        }

        if (hoaDonChiTietBanHangServices.listHDCTBanHang(txtMaHoaDon.getText().trim()).size() <= 0) {
            JOptionPane.showMessageDialog(this, "Mời thêm sản phẩm vào đơn hàng");
            return;
        } else {
            FormQuyDoiDiem qd = new FormQuyDoiDiem();
            try {
                BigDecimal b = BigDecimal.valueOf(Long.valueOf(txtTongTien.getText()));
                khachHangService.updateSoLanMua(txtMaHoaDon.getText());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
//            Date ngayShip = java.sql.Date.valueOf(txtNgayShip.getText());
//            Date ngayThanhToan = java.sql.Date.valueOf(txtNgayThanhToan.getText());
//            hoaDonService.updateHdttOnl(cbxPhuongThucThanhToan.getSelectedItem().toString(), txtMaHoaDon.getText(), txtMaNhanVien.getText(), Integer.valueOf(txtSoLuong.getText()), BigDecimal.valueOf(Long.valueOf(txtTongTienOl.getText())), txtTenkh.getText(), ngayShip, ngayThanhToan, txtDiaChi.getText(), txtSDTNguoiNhan.getText(), txtSDTNguoiShip.getText(), txtTenNguoiShip.getText());
//            hoaDonService.updateTTDangGiao(maHD);
            hoaDon.setMaNV(txtMaNV.getText().trim());
            //hoaDon.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
            hoaDon.setTongTien(BigDecimal.valueOf(Double.parseDouble(txtTongTienOl.getText())));
            hoaDon.setTenKH(txtTenkh.getText().trim());
            hoaDon.setDiaChi(txtDiaChi.getText().trim());
            hoaDon.setSdtNguoiNhan(txtSDTNguoiNhan.getText().trim());
            hoaDon.setSdtNguoiiShip(txtSDTNguoiShip.getText().trim());
            hoaDon.setTenNguoiShip(txtTenNguoiShip.getText().trim());

            hoaDonService.updateHdttOnlThinh(cbxPhuongThucThanhToan.getSelectedItem().toString(), BigDecimal.valueOf(Long.parseLong(txtTongTienOl.getText())), txtMaHoaDon.getText());
            //test
            hoaDonService.updateHdttOnlThinh1(hoaDon, txtMaHoaDon.getText());
            addTableHoaDon(hoaDonService.getList());
            JOptionPane.showMessageDialog(this, "Đặt hàng thành công");
            list.removeAll(list);

            int confirm1 = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn In Hóa Đơn Không ?", "In Hóa Đơn", JOptionPane.YES_NO_OPTION);
            if (confirm1 == JOptionPane.YES_OPTION) {
                String path = "";
                JFileChooser j = new JFileChooser();
                j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int x = j.showSaveDialog(this);
                if (x == JFileChooser.APPROVE_OPTION) {
                    path = j.getSelectedFile().getPath();
                }
                Document doc = new Document();

                try {
                    try {
                        PdfWriter.getInstance(doc, new FileOutputStream(path + "HoaDon.pdf"));
                        doc.open();
                        for (int i = 0; i < tblHoaDon.getRowCount(); i++) {
                            if (txtMaHD.getText().equals(tblHoaDon.getValueAt(i, 0).toString())) {
                                String maHD1 = tblHoaDon.getValueAt(i, 0).toString();
                                String NgayTao = tblHoaDon.getValueAt(i, 1).toString();
                                String Khachhang = tblHoaDon.getValueAt(i, 3).toString();

                                Paragraph paragraph0 = new Paragraph("Ngay tao: " + NgayTao);
                                paragraph0.setIndentationLeft(50);
                                Paragraph paragraph1 = new Paragraph("Ma hoa don: " + maHD1);
                                paragraph1.setIndentationLeft(50);
                                Paragraph paragraph2 = new Paragraph("HOA DON BAN HANG");
                                paragraph2.setIndentationLeft(180);
                                paragraph2.setIndentationRight(80);
                                paragraph2.setSpacingAfter(15);
                                String maNV = txtMaNV.getText();
                                Paragraph paragraph3 = new Paragraph("Nhan vien: " + maNV);
                                paragraph3.setIndentationLeft(50);
                                Paragraph paragraph4 = new Paragraph("Khach hang: " + Khachhang);
                                paragraph4.setIndentationLeft(50);
                                String SDT = txtSDT.getText();
                                Paragraph paragraph5 = new Paragraph("SDT: " + SDT);
                                paragraph5.setIndentationLeft(50);
                                paragraph5.setSpacingAfter(10);
                                doc.add(paragraph0);
                                doc.add(paragraph1);
                                doc.add(paragraph2);
                                doc.add(paragraph3);
                                doc.add(paragraph4);
                                doc.add(paragraph5);

//                            doc.add(paragraph6);
                            }
                        }

                        PdfPTable tbl = new PdfPTable(5);

                        tbl.addCell("Ma San Pham ");
                        tbl.addCell("Ten San Pham");
                        tbl.addCell("So Luong");
                        tbl.addCell("Gia Ban");
                        tbl.addCell("Muc Giam Gia");

                        for (int i = 0; i < tblDonHang.getRowCount(); i++) {
                            String id = tblDonHang.getValueAt(i, 0).toString();
                            String id1 = tblDonHang.getValueAt(i, 1).toString();
                            String id2 = tblDonHang.getValueAt(i, 2).toString();
                            String id3 = tblDonHang.getValueAt(i, 3).toString();
                            String id4 = tblDonHang.getValueAt(i, 4).toString();

                            tbl.addCell(id);
                            tbl.addCell(id1);
                            tbl.addCell(id2);
                            tbl.addCell(id3);
                            tbl.addCell(id4);
                        }

                        doc.add(tbl);
                        String tongTien = txtTongTien.getText().trim();

                        Paragraph paragraph6 = new Paragraph("Tong Tien: " + tongTien + ".000" + "VND");
                        paragraph6.setIndentationLeft(50);

                        Paragraph paragraph7 = new Paragraph("Cam on quy khach da den cua hang dien thoai");
                        paragraph7.setIndentationLeft(120);
                        paragraph7.setIndentationRight(80);
                        doc.add(paragraph6);
                        doc.add(paragraph7);

                    } catch (DocumentException ex) {
                        //java.util.logging.Logger.getLogger(FormBanHang.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (FileNotFoundException ex) {
                    //java.util.logging.Logger.getLogger(FormBanHang.class.getName()).log(Level.SEVERE, null, ex);
                }

                doc.close();
            } else {
                return;
            }

        }
    }


    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        for (int i = 0; i < tblHoaDon.getRowCount(); i++) {
            if (txtMaHoaDon.getText().equals(tblHoaDon.getValueAt(i, 0).toString())) {
                if (tblHoaDon.getValueAt(i, 2).toString().equals("Đã thanh toán")) {
                    JOptionPane.showMessageDialog(this, "Đơn Hàng Đã được thanh toán");
                    return;
                }
            }
        }

        if (checkRong()) {
            if (checkRdSD) {
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thanh toán ?", "Xác nhân thanh toán.", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    thanhToan();
                } else {
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui Lòng Nhấn sử dụng điểm");
                return;
            }
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed


    private void cbxPhanLoaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxPhanLoaiItemStateChanged

        ArrayList<ViewSanPham> sc = sanPhamService.loaiHang(cbxPhanLoai.getSelectedItem().toString());
        loadTable(sc);
    }//GEN-LAST:event_cbxPhanLoaiItemStateChanged

    private void deleteAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAllActionPerformed

//        if (hoaDonService.selectTrangThaiHoaDon(txtMaHD.getText().trim()) == 0) {
//            if (JOptionPane.showConfirmDialog(this, "Xác Nhận Xoá", "Thông Báo", JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION) {
//                for (HoaDonChiTietBanHangViewModel hd : hoaDonChiTietBanHangServices.listHDCTBanHang(txtMaHD.getText())) {
//                    for (ImeiViewModel im : imeiBhSer.getAllImeiKT(txtMaHD.getText(), hd.getMaSanPham())) {
//                        imeiBhSer.deleteImeiKT(im.getMaImei());
//                        imeiSer.updateTT0(im.getMaImei());
//                        sps.addSlSp(hd.getMaSanPham());
//                    }
//                }
//                hoaDonChiTietBanHangServices.xoaAllSanPhamHDCT(txtMaHD.getText().trim());
//                hdct.removeAll(hdct);
//                loadTable(sanPhamService.getAll());
//                addTableGioHangBanHang(hoaDonChiTietLuongBanHangServices.getAllHDCT(txtMaHD.getText().trim()));
//                JOptionPane.showMessageDialog(this, "Xoá thàng công");
//            }
//        } else if (hoaDonService.selectTrangThaiHoaDon(txtMaHD.getText().trim()) == 1) {
//            JOptionPane.showMessageDialog(this, "*HOÁ ĐƠN ĐÃ THANH TOÁN\n-KHÔNG THỂ XOÁ SẢN PHẨM", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
//            return;
//        } else {
//            JOptionPane.showMessageDialog(this, "Lỗi", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
        if (hoaDonService.selectTrangThaiHoaDon(txtMaHD.getText().trim()) == 0) {
            if (JOptionPane.showConfirmDialog(this, "Xác Nhận Xoá", "Thông Báo", JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION) {
                if (hoaDonChiTietLuongBanHangServices.
                        listSanPhamXoaHDCTJDialog(txtMaHD.getText().trim()) != null) {
                    // dùng for để xoá all list imei trong bảng Imei_KT của hoá đơn và cập nhật lại trạng thái ỉmei trong bảng imei lại online
                    for (HoaDonChiTietLuongbanHangViewModel hd : hoaDonChiTietLuongBanHangServices.
                            listImeiXoaHDCTJDialog(txtMaHD.getText().trim())) {
                        if (hd.getMaImei() != null) {
                            hoaDonChiTietLuongBanHangServices.updateTrangThaiImeiXoaTrongGioHangLenBangSanPham(hd.getMaImei());
                        }
                        // xoá all imei_KT có chung trong 1 hoá đơn
                        hoaDonChiTietLuongBanHangServices.deleteAllImeiKTGioHang(hd.getMaSanPham(), txtMaHD.getText().trim());
                    }
                    // dùng for để xoá sản phẩm ở trong giỏ  hàng
                    for (HoaDonChiTietLuongbanHangViewModel hd : hoaDonChiTietLuongBanHangServices.
                            listSanPhamXoaHDCTJDialog(txtMaHD.getText().trim())) {
                        // cập nhật số lượng tồn lại sản phẩm
                        System.out.println(hd.getSoLuongSanPham() + " \n");
                        hoaDonChiTietLuongBanHangServices.updateThemSoLuongSanPham(hd.getSoLuongSanPham(), hd.getMaSanPham());
                    }
                    hoaDonChiTietLuongBanHangServices.deleteAllHDCT(txtMaHD.getText().trim());
//                hoaDonChiTietBanHangServices.xoaAllSanPhamHDCT(txtMaHD.getText().trim());
//                hdct.removeAll(hdct);
                    loadTable(sanPhamService.getAll());
                    addTableGioHangBanHang(hoaDonChiTietLuongBanHangServices.getAllHDCT(txtMaHD.getText().trim()));
                    JOptionPane.showMessageDialog(this, "Xoá thàng công", " Thông Báo", JOptionPane.QUESTION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "*GIỎ HÀNG RỖNG!", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        } else if (hoaDonService.selectTrangThaiHoaDon(txtMaHD.getText().trim()) == 1) {
            JOptionPane.showMessageDialog(this, "*HOÁ ĐƠN ĐÃ THANH TOÁN\n-KHÔNG THỂ XOÁ SẢN PHẨM", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            JOptionPane.showMessageDialog(this, "Lỗi", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
            return;
        }

    }//GEN-LAST:event_deleteAllActionPerformed

    private void btnTaoHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHDActionPerformed
        taoHD();
        txtSoDiemSD.setText("0");
        txtNgayTao.setText("");
        txtTongTien.setText("");
        txtTienKD.setText("");
        txtTienThua.setText("");
    }//GEN-LAST:event_btnTaoHDActionPerformed

    private void txtTienKDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKDKeyReleased
        try {
            BigDecimal tienKd = BigDecimal.valueOf(Long.valueOf(txtTienKD.getText().trim()));
            BigDecimal tienCk = BigDecimal.valueOf(Long.valueOf(txtTienCK.getText().trim()));
            BigDecimal tongTien = BigDecimal.valueOf(Long.valueOf(txtTongTien.getText().trim()));
            txtTienThua.setText(String.valueOf((tienKd.add(tienCk)).subtract(tongTien)));
//            if (txtTienKD.getText().equals("")) {
//                txtTienKD.setText("0");
//            }
        } catch (NumberFormatException e) {
            //            e.printStackTrace();
            return;
        }
    }//GEN-LAST:event_txtTienKDKeyReleased

    private void btnChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonActionPerformed
        new TaoNhanhKH().setVisible(true);
    }//GEN-LAST:event_btnChonActionPerformed

    private void btnThayDoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThayDoiActionPerformed
        try {
            txtMaKH.setText(TaoNhanhKH.maKH);
            txtTenKH.setText(TaoNhanhKH.tenKH);
            txtSDT.setText(TaoNhanhKH.sdtKH);
            txtDiem.setText(TaoNhanhKH.diemKH);
            hoaDonService.updateTenKh(txtTenKH.getText(), tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 0).toString());
            addTableHoaDon(hoaDonService.getList());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Mời chọn khách hàng");
            return;
        }

    }//GEN-LAST:event_btnThayDoiActionPerformed

    private void txtSoDiemSDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoDiemSDKeyReleased

    }//GEN-LAST:event_txtSoDiemSDKeyReleased

    private void txtMaHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMaHDMouseClicked

    }//GEN-LAST:event_txtMaHDMouseClicked

    private void cbxPTTTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxPTTTItemStateChanged

        if (cbxPTTT.getSelectedItem().toString().equalsIgnoreCase("Hình thức thanh toán")) {
            txtTienCK.setEnabled(false);
            txtTienCK.setText("0");
            txtTienKD.setEnabled(false);
            txtTienKD.setText("0");
        } else if (cbxPTTT.getSelectedItem().toString().equalsIgnoreCase("Tiền mặt")) {
            txtTienCK.setEnabled(false);
            txtTienCK.setText("0");
            txtTienKD.setEnabled(true);
        } else if (cbxPTTT.getSelectedItem().toString().equalsIgnoreCase("Chuyển khoản")) {
            txtTienKD.setEnabled(false);
            txtTienKD.setText("0");
            txtTienCK.setEnabled(true);
        } else {
            txtTienCK.setEnabled(true);
            txtTienKD.setEnabled(true);
        }
    }//GEN-LAST:event_cbxPTTTItemStateChanged

    private void cbxPTTTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxPTTTMouseClicked

    }//GEN-LAST:event_cbxPTTTMouseClicked
    static boolean checkRdSD = true;
    private void btnSuDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuDungActionPerformed
       try {
            if (txtSoDiemSD.getText().trim().isEmpty()) {
                if (Integer.parseInt(txtSoDiemSD.getText().trim()) > Integer.parseInt(txtDiem.getText().trim())) {
                    JOptionPane.showMessageDialog(this, "Số điểm sử dụng vượt quá số điểm hiện tại");
                    return;
                }
                if (Integer.parseInt(txtSoDiemSD.getText().trim()) < 0) {
                    JOptionPane.showMessageDialog(this, "Số điểm sử dụng không được âm");
                    return;
                }
              
                //                String[] splits = txtTongTien.getText().split(".0000$");
                //                StringBuilder stringBuilder1 = new StringBuilder();
                //                for (String x : splits) {
                //                    stringBuilder1.append(x);
                //                }
                //                txtTongTien.setText(stringBuilder1.toString());

            } else {
                FormQuyDoiDiem qd = new FormQuyDoiDiem();
                BigDecimal c = BigDecimal.valueOf(Long.valueOf(txtTongTien.getText()));
                BigDecimal d = BigDecimal.valueOf(Long.valueOf(txtSoDiemSD.getText())).multiply(BigDecimal.valueOf(Long.valueOf(qd.getTienTieuDiem())));
               
                txtTongTien.setText(String.valueOf(c.subtract(d)));
               
                   try {
            BigDecimal tienKd = BigDecimal.valueOf(Long.valueOf(txtTienKD.getText().trim()));
            BigDecimal tienCk = BigDecimal.valueOf(Long.valueOf(txtTienCK.getText().trim()));
            BigDecimal tongTien = BigDecimal.valueOf(Long.valueOf(txtTongTien.getText()));
            txtTienThua.setText(String.valueOf((tienCk.add(tienKd)).subtract(tongTien)));
            txtSoDiemSD.setEnabled(false);
//            if (txtTienCK.getText().equals("")) {
//                txtTienCK.setText("0");
//            }
        } catch (NumberFormatException e) {
            //            e.printStackTrace();
            return;
        } 
              
                
                //                String[] splits = txtTongTien.getText().split(".0000$");
                //                StringBuilder stringBuilder1 = new StringBuilder();
                //                for (String x : splits) {
                //                    stringBuilder1.append(x);
                //                }
                //                txtTongTien.setText(stringBuilder1.toString());
            }

            checkRdSD = true;
        } catch (NumberFormatException e) {
            return;
        }
    }//GEN-LAST:event_btnSuDungActionPerformed

    private void txtTienCKKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienCKKeyReleased
        try {
            BigDecimal tienKd = BigDecimal.valueOf(Long.valueOf(txtTienKD.getText().trim()));
            BigDecimal tienCk = BigDecimal.valueOf(Long.valueOf(txtTienCK.getText().trim()));
            BigDecimal tongTien = BigDecimal.valueOf(Long.valueOf(txtTongTien.getText()));
            txtTienThua.setText(String.valueOf((tienCk.add(tienKd)).subtract(tongTien)));
//            if (txtTienCK.getText().equals("")) {
//                txtTienCK.setText("0");
//            }
        } catch (NumberFormatException e) {
            //            e.printStackTrace();
            return;
        }

    }//GEN-LAST:event_txtTienCKKeyReleased

    private void txtTienThuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienThuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienThuaActionPerformed

    private void rdoSdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoSdMouseClicked
        // TODO add your handling code here:
        txtSoDiemSD.setEnabled(true);
        checkRdSD = false;
        if(Integer.parseInt(txtDiem.getText())<=0){
            JOptionPane.showMessageDialog(this,"Khách Hàng Không đủ điểm");
            rdoKhongSd.setVisible(true);
            return;
        }
    }//GEN-LAST:event_rdoSdMouseClicked
    
    private void rdoKhongSdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoKhongSdMouseClicked
        // TODO add your handling code here:
        txtSoDiemSD.setEnabled(false);
        checkRdSD = true;
        txtSoDiemSD.setText("0");
        BigDecimal sum = new BigDecimal(0);
        for (HoaDonChiTietLuongbanHangViewModel chiTietHoaDon : hoaDonChiTietLuongBanHangServices.getAllHDCT(txtMaHD.getText().trim())) {
//            BigDecimal tong = chiTietHoaDon.getDonGia().multiply(BigDecimal.valueOf(chiTietHoaDon.getSoLuong()));
            BigDecimal tong = chiTietHoaDon.getTongTien();
            sum = sum.add(tong);
//            quantity += chiTietHoaDon.getSoLuongSanPham();
            txtTongTien.setText(String.valueOf(sum));
            String[] splits = txtTongTien.getText().split(".0000$");
            StringBuilder stringBuilder1 = new StringBuilder();
            for (String x : splits) {
                stringBuilder1.append(x);
            }
            txtTongTien.setText(stringBuilder1.toString());
            BigDecimal tienKd = BigDecimal.valueOf(Long.valueOf(txtTienKD.getText().trim()));
            BigDecimal tienCk = BigDecimal.valueOf(Long.valueOf(txtTienCK.getText().trim()));
            BigDecimal tongTien = BigDecimal.valueOf(Long.valueOf(txtTongTien.getText()));
            txtTienThua.setText(String.valueOf((tienCk.add(tienKd)).subtract(tongTien)));
            //txtTongTienOl.setText(stringBuilder1.toString());
        }
    }//GEN-LAST:event_rdoKhongSdMouseClicked


    private void btnDoiTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiTraActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn đổi trả ?", "Xác nhân đổi trả.", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            hoanTra();
//            if (tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 4).toString().equalsIgnoreCase("false")) {
//                tblHoaDon.setValueAt(true, 0, 4);
        }

    }//GEN-LAST:event_btnDoiTraActionPerformed

    private void txtMaHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHoaDonActionPerformed

    private void rdoSdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoSdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoSdActionPerformed

    private void btnTaoHDOnlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHDOnlineActionPerformed
        // TODO add your handling code here:
        taoHDONLINE();
        txt_NgayTaoOL.setText(" ");
        txtTongTienOl.setText(" ");
        txtNgayThanhToan.setText(" ");
        txtNgayNhan.setText(" ");
        txtNgayShip.setText(" ");
//        txtTienKD.setText("");
//        txtTienThua.setText("");
    }//GEN-LAST:event_btnTaoHDOnlineActionPerformed

    private void tabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabMouseClicked
        // TODO add your handling code here:
        if (tab.getSelectedIndex() == 0) {
            if (btnTaoHD.isSelected()) {
                taoHD();
            }
        } else {
            if (btnTaoHDOnline.isSelected()) {
                taoHDONLINE();
            }
        }
    }//GEN-LAST:event_tabMouseClicked

    private void btnDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatHangActionPerformed
        // TODO add your handling code here:
        for (int i = 0; i < tblHoaDon.getRowCount(); i++) {
            if (txtMaHoaDon.getText().equals(tblHoaDon.getValueAt(i, 0).toString())) {
                if (tblHoaDon.getValueAt(i, 2).toString().equals("Đang giao")) {
                    JOptionPane.showMessageDialog(this, "Đơn Hàng này trong trạng thái đang giao");
                    return;
                }
            }
        }

        for (int i = 0; i < tblHoaDon.getRowCount(); i++) {
            if (txtMaHoaDon.getText().equals(tblHoaDon.getValueAt(i, 0).toString())) {
                if (tblHoaDon.getValueAt(i, 2).toString().equals("Đã thanh toán")) {
                    JOptionPane.showMessageDialog(this, "Đơn Hàng Đã được thanh toán");
                    return;
                }
            }
        }
            for (int i = 0; i < tblHoaDon.getRowCount(); i++) {
            if (txtMaHoaDon.getText().equals(tblHoaDon.getValueAt(i, 0).toString())) {
                if (tblHoaDon.getValueAt(i, 2).toString().equals("Đã hoàn trả")) {
                    JOptionPane.showMessageDialog(this, "Đơn hàng đã đổi");
                    return;
                }
            }
        }
        if (checkFormOnl()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn đặt hàng ?", "Xác nhân đặt hàng.", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                thanhToanOnl();
//                System.out.println(txtSoLuong.getText());
            }
        } else {
            return;
        }
    }//GEN-LAST:event_btnDatHangActionPerformed

    private void tblDonHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDonHangMouseClicked
        // TODO add your handling code here:
//        maSp1 = tblDonHang.getValueAt(tblDonHang.getSelectedRow(), 1).toString();
//        tenSp = tblDonHang.getValueAt(tblDonHang.getSelectedRow(), 2).toString();
    }//GEN-LAST:event_tblDonHangMouseClicked

    private void txtTimKiem3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiem3KeyReleased
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblImei.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(model);
        tblImei.setRowSorter(trs);
        //trs.setRowFilter(RowFilter.regexFilter(txtTimKiem.getText().toUpperCase()));
    }//GEN-LAST:event_txtTimKiem3KeyReleased

    private void tblImei1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblImei1MouseClicked
        // TODO add your handling code here:
        try {
            ImeiViewModel imeiDel = new ImeiViewModel();
            Boolean check = ((Boolean) tblImei1.getValueAt(tblImei1.getSelectedRow(), 1));
            if (check) {
                imeiDel.setMaImei(tblImei1.getValueAt(tblImei1.getSelectedRow(), 0).toString());
                listImeiDelete.add(imeiDel);
            } else {
                for (ImeiViewModel x : listImeiDelete) {
                    if (x.getMaImei().equals(tblImei1.getValueAt(tblImei1.getSelectedRow(), 0))) {
                        listImeiDelete.remove(x);
                    }
                }
            }
        } catch (ConcurrentModificationException e) {
            return;
        }
    }//GEN-LAST:event_tblImei1MouseClicked

    private void tblImei1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblImei1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblImei1MousePressed

    private HoaDonChiTietBanHangService hdctSer = new HoaDonChiTietBanHangServiceIplm();
    private ImeiService imeiSer1 = new ImeiServiceImpl();

    private void btnOk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOk1ActionPerformed
        // TODO add your handling code here:
        if (tblImei1.getRowCount() > 1) {
            if (tblImei1.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(this, "Mời chọn imei");
            } else {
//                hdctSer.deleteHDCT(tblImei1.getValueAt(tblImei1.getSelectedRow(), 0).toString());
//                imeiBhSer.deleteImeiKT(tblImei1.getValueAt(tblImei1.getSelectedRow(), 0).toString());
//                imeiSer1.updateTT0(tblImei1.getValueAt(tblImei1.getSelectedRow(), 0).toString());
//                sps.addSlSp(FormBanHang.maSp1);

                for (ImeiViewModel x : listImeiDelete) {
                    imeiBhSer.deleteImeiKT(x.getMaImei());
                    imeiSer1.updateTT0(x.getMaImei());
                }
                sps.addSlSp(FormBanHang.maSp1);
//                addTableGioHangBanHang(hoaDonChiTietBanHangServices.listHDCTBanHang(maHD));
//                loadTable(sanPhamService.getAll());
                JOptionPane.showMessageDialog(this, "ok");
                jDialogImeiGioHang.dispose();
            }
        } else {
            if (tblImei1.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(this, "Mời chọn imei");
            } else {
//                imeiBhSer.deleteImeiKT(tblImei1.getValueAt(tblImei1.getSelectedRow(), 0).toString());
//                imeiSer1.updateTT0(tblImei1.getValueAt(tblImei1.getSelectedRow(), 0).toString());
                for (ImeiViewModel x : listImeiDelete) {
                    imeiBhSer.deleteImeiKT(x.getMaImei());
                    imeiSer1.updateTT0(x.getMaImei());
                }
                sps.addSlSp(FormBanHang.maSp1);
                hdctSer.xoaSanPhamTrongHDCT(FormBanHang.maSp1, FormBanHang.maHD);
//                addTableGioHangBanHang(hoaDonChiTietBanHangServices.listHDCTBanHang(maHD));
//                loadTableImeiGioHang(imeiBhSer.getAllImeiKT(FormBanHang.maHD, FormBanHang.maSp1));
//                loadTable(sanPhamService.getAllbtnXacNhan());
                JOptionPane.showMessageDialog(this, "ok");
                jDialogImeiGioHang.dispose();
            }
        }
    }//GEN-LAST:event_btnOk1ActionPerformed

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        // TODO add your handling code here:
        for (int i = 0; i < tblHoaDon.getRowCount(); i++) {
            if (txtMaHoaDon.getText().trim().equals(tblHoaDon.getValueAt(i, 0).toString())) {
                if (tblHoaDon.getValueAt(i, 2).toString().equals("Chưa thanh toán")) {
                    JOptionPane.showMessageDialog(this, "Vui Lòng Nhấn Giao Hàng Rồi mới xác Nhận");
                    return;
                }
            }
        }
        for (int i = 0; i < tblHoaDon.getRowCount(); i++) {
            if (txtMaHoaDon.getText().trim().equals(tblHoaDon.getValueAt(i, 0).toString())) {
                if (tblHoaDon.getValueAt(i, 2).toString().equalsIgnoreCase("Chờ lấy hàng")) {
                    JOptionPane.showMessageDialog(this, "Shiper chưa lay hang");
                    return;
                }
            }
        }

        for (int i = 0; i < tblHoaDon.getRowCount(); i++) {
            if (txtMaHoaDon.getText().trim().equals(tblHoaDon.getValueAt(i, 0).toString())) {
                if (tblHoaDon.getValueAt(i, 2).toString().equals("Đã thanh toán")) {
                    JOptionPane.showMessageDialog(this, "Đơn Hàng Đã được thanh toán");
                    return;
                }
            }
        }
        for (int i = 0; i < tblHoaDon.getRowCount(); i++) {
            if (txtMaHoaDon.getText().trim().equals(tblHoaDon.getValueAt(i, 0).toString())) {
                if (tblHoaDon.getValueAt(i, 2).toString().equals("Đã hoàn trả")) {
                    JOptionPane.showMessageDialog(this, "Vui lòng giao hàng rồi mới xác nhận!!!");
                    return;
                }
            }
        }
        
        for (ImeiViewModel x : imeiBhSer.getAllImeiKT1(txtMaHoaDon.getText())) {
            imeiSer.updateTT1(x.getMaImei());
        }
        hoaDonService.updateHdttOnl1(txtMaHoaDon.getText());
        HoaDon hd = new HoaDon();
        hd.setNgayThanhToan(String.valueOf(java.time.LocalDate.now()));
        txtNgayThanhToan.setDate(LocalDate.parse(hd.getNgayThanhToan()));
        txtNgayNhan.setDate(LocalDate.parse(hd.getNgayThanhToan()));
        hoaDonService.updateHdttOnl1XacNhan(hd, txtMaHoaDon.getText());

        addTableHoaDon(hoaDonService.getList());
        JOptionPane.showMessageDialog(this, "Hoàn thành đơn hàng.");


    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void btnDoiTraOnlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiTraOnlActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn đổi trả ?", "Xác nhân đổi trả.", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            hoanTraonline();
        } else {
            return;
        }
    }//GEN-LAST:event_btnDoiTraOnlActionPerformed

    private void tblDonHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDonHangMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {

//            if (hoaDonChiTietLuongBanHangServices.trangThaiHoaDon(txtMaHD.getText().trim()) != 1) {
//                if(Integer.parseInt(tblDonHang.getValueAt(tblDonHang.getSelectedRow(), 2).toString()) > 
//                      Integer.parseInt(tblDonHang.getValueAt(tblDonHang.getSelectedRow(), 5).toString())  ){
//            loadTableListImeiBanHang(hoaDonChiTietLuongBanHangServices.getAllImeiSanPhamJDialog());
            loadtableJDialog();

            loadCbXoaImeiJDialog();
            loadCbThemImeiJDialog();
            jDialogImeiBanHang.setVisible(true);

//            } else {
//                JOptionPane.showMessageDialog(this, "HOÁ ĐƠN ĐÃ THANH TOÁN KHÔNG THỂ CHỈNH SỬA!", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
        } else {
            return;
        }

    }//GEN-LAST:event_tblDonHangMousePressed

    private void btnGiaoHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGiaoHangActionPerformed
        // TODO add your handling code here:
        HoaDon hd = new HoaDon();

        for (int i = 0; i < tblHoaDon.getRowCount(); i++) {
            if (txtMaHoaDon.getText().trim().equals(tblHoaDon.getValueAt(i, 0).toString())) {
                if (tblHoaDon.getValueAt(i, 2).toString().equals("Đang giao")) {
                    JOptionPane.showMessageDialog(this, "Đơn Hàng này trong trạng thái đang giao");
                    return;
                }
            }
        }

        for (int i = 0; i < tblHoaDon.getRowCount(); i++) {
            if (txtMaHoaDon.getText().trim().equals(tblHoaDon.getValueAt(i, 0).toString())) {
                if (tblHoaDon.getValueAt(i, 2).toString().equals("Chưa thanh toán")) {
                    JOptionPane.showMessageDialog(this, "Vui Lòng đặt hàng trước");
                    return;
                }
            }
        }
        for (int i = 0; i < tblHoaDon.getRowCount(); i++) {
            if (txtMaHoaDon.getText().trim().equals(tblHoaDon.getValueAt(i, 0).toString())) {
                if (tblHoaDon.getValueAt(i, 2).toString().equals("Đã thanh toán")) {
                    JOptionPane.showMessageDialog(this, "Đơn Hàng Này  Đã Thanh Toán Thành Công");
                    return;
                }
            }
        }
        
        
      
        String maHD = txtMaHoaDon.getText().trim();
        hoaDonService.updateTTDangGiao(maHD);
        addTableHoaDon(hoaDonService.getList());
        SimpleDateFormat df = new SimpleDateFormat("");

        hd.setNgayShip(String.valueOf(java.time.LocalDate.now()));
        hoaDonService.updateHdttOnl1GiaoHang(hd, txtMaHoaDon.getText().trim());

        txtNgayShip.setDate(LocalDate.parse(hd.getNgayShip()));

    }//GEN-LAST:event_btnGiaoHangActionPerformed

    private void cbChonTatCaXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChonTatCaXoaActionPerformed
        // TODO add your handling code here:
        if (cbChonTatCaXoa.isSelected()) {
            for (int i = 0; i < tblImei1.getRowCount(); i++) {
                tblImei1.setValueAt(true, i, 1);
                ImeiViewModel imeiDel = new ImeiViewModel();
                imeiDel.setMaImei(tblImei1.getValueAt(i, 1).toString());
                listImeiDelete.add(imeiDel);
            }
        } else {
            for (int i = 0; i < tblImei1.getRowCount(); i++) {
                tblImei1.setValueAt(false, i, 1);
                listImeiDelete.removeAll(listImeiDelete);
            }
        }
    }//GEN-LAST:event_cbChonTatCaXoaActionPerformed

    private void txtTimKiemJDialogImeiBanHangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemJDialogImeiBanHangKeyReleased
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblImei.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(model);
        tblImei.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(txtTimKiemJDialogImeiBanHang.getText().trim()));
    }//GEN-LAST:event_txtTimKiemJDialogImeiBanHangKeyReleased

    private void btnOKJDiaLogImeiBanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKJDiaLogImeiBanHangActionPerformed
        // TODO add your handling code here:
        //        loadTableListImeiBanHang(hoaDonChiTietLuongBanHangServices.getAllListImeiBanHang(tblImei.getValueAt(tblImei.getSelectedRow(), 0).toString()));

        //if (tblImei.getSelectedRow() != 0) {
//        for (ImeiViewModel x : listImeiAdd) {
//            listImeiDelete.removeAll(listImeiDelete);
//            hoaDonChiTietLuongBanHangServices.addImeiKT(x, txtMaHD.getText().trim());
//            hoaDonChiTietLuongBanHangServices.updateTrangThaiImeiThemVaoGioHang(x.getMaImei());
//        }
////        ImeiViewModel imei = new ImeiViewModel();
//
////        imei.setMaImei(tblImei.getValueAt(tblImei.getSelectedRow(), 0).toString());
////        imei.setMaSanPham(tblImei.getValueAt(tblImei.getSelectedRow(), 1).toString());
////        imei.setTrangThai(0); // trạng thái imei_KT (0 là đang bán, 2 là đã bán bán)
//        //thêm vào imei_KT
////        hoaDonChiTietLuongBanHangServices.addImeiKT(imei, txtMaHD.getText().trim());
//        //update trạng thái imei đã thêm vào giỏ hàng của bảng imei
//        hoaDonChiTietLuongBanHangServices.updateTrangThaiImeiThemVaoGioHang(tblImei.getValueAt(tblImei.getSelectedRow(), 0).toString());
//        //load lại table imei sản phẩm thêm JDialog
//        //            loadTableListImeiBanHang(hoaDonChiTietLuongBanHangServices.getAllImeiSanPhamJDialog(txtMaHD.getText().trim()));
//
//        // load table lại giỏ hàng
//        addTableGioHangBanHang(hoaDonChiTietLuongBanHangServices.getAllHDCT(txtMaHD.getText().trim()));
//        //load lại table imei xoá JDialog
//        loadtableJDialog();
//        loadCbThemImeiJDialog();
//        loadCbXoaSanPhamJDialog();
//        loadCbXoaImeiJDialog();
//        listImeiAdd.removeAll(listImeiAdd);
        //
//        } else {
//            JOptionPane.showMessageDialog(this, "Mời chọn imei");
//        }
        ArrayList<ImeiViewModel> listImeiBanHangJDialog = new ArrayList<>();

        for (int i = 0; i < tblImei.getRowCount(); i++) {
//                System.out.println(tblImei.getValueAt(i, 3).toString());
            if (((Boolean) tblImei.getValueAt(i, 3)) == null) {
                continue;
            } else if (((Boolean) tblImei.getValueAt(i, 3)) == false) {
                continue;
            } else {
                ImeiViewModel imei = new ImeiViewModel();
                imei.setMaImei(tblImei.getValueAt(i, 0).toString());
                imei.setMaSanPham(tblImei.getValueAt(i, 1).toString());
                imei.setTrangThai(0);

                listImeiBanHangJDialog.add(imei);
            }
        }

        if (listImeiBanHangJDialog.size() > 0) {
            for (ImeiViewModel x : listImeiBanHangJDialog) {
                hoaDonChiTietLuongBanHangServices.addImeiKT(x, txtMaHD.getText().trim());
                hoaDonChiTietLuongBanHangServices.updateTrangThaiImeiThemVaoGioHang(x.getMaImei());
            }
        } else {
            JOptionPane.showMessageDialog(this.jDialogImeiBanHang, "HÃY TÍCH VÀO IMEI TRƯỚC KHI THÊM!", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //load lại table imei sản phẩm thêm JDialog
        loadTableListImeiBanHang(hoaDonChiTietLuongBanHangServices.getAllImeiSanPhamJDialog(txtMaHD.getText().trim()));
        // load table lại giỏ hàng
        addTableGioHangBanHang(hoaDonChiTietLuongBanHangServices.getAllHDCT(txtMaHD.getText().trim()));
        //load lại table imei xoá JDialog
        loadtableJDialog();

        loadCbThemImeiJDialog();
        loadCbXoaSanPhamJDialog();
        loadCbXoaImeiJDialog();

        listImeiBanHangJDialog.removeAll(listImeiBanHangJDialog);

    }//GEN-LAST:event_btnOKJDiaLogImeiBanHangActionPerformed

    private void cbThemImeiSPVaoGHJDialogItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbThemImeiSPVaoGHJDialogItemStateChanged
        if (cbThemImeiSPVaoGHJDialog.getSelectedIndex() == 0) {
            loadTableListImeiBanHang(hoaDonChiTietLuongBanHangServices.getAllImeiSanPhamJDialog(txtMaHD.getText().trim()));

        } else {
            if (cbThemImeiSPVaoGHJDialog.getSelectedItem() == null) {
                loadTableListImeiBanHang(hoaDonChiTietLuongBanHangServices.getAllImeiSanPhamJDialog(txtMaHD.getText().trim()));

            } else {
                loadTableListImeiBanHang(hoaDonChiTietLuongBanHangServices.getLocCBImeiSanPhamJDialog(
                        cbThemImeiSPVaoGHJDialog.getSelectedItem().toString(), txtMaHD.getText().trim()));
            }
        }
    }//GEN-LAST:event_cbThemImeiSPVaoGHJDialogItemStateChanged

    private void txtTimKiemXoaJDialogImeiBanHangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemXoaJDialogImeiBanHangKeyReleased
        DefaultTableModel model = (DefaultTableModel) tblImeiXoa.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(model);
        tblImeiXoa.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(txtTimKiemXoaJDialogImeiBanHang.getText().trim()));
    }//GEN-LAST:event_txtTimKiemXoaJDialogImeiBanHangKeyReleased

    private void btnOKXoaJDiaLogImeiBanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKXoaJDiaLogImeiBanHangActionPerformed
//        if (JOptionPane.showConfirmDialog(this.jDialogImeiBanHang, "XÁC NHẬN XOÁ", "THÔNG BÁO", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
//            for (ImeiViewModel x : listImeiDelete) {
//                listImeiAdd.removeAll(listImeiAdd);
//                hoaDonChiTietLuongBanHangServices.deleteImeiKTGioHang(x.getMaImei());
//                hoaDonChiTietLuongBanHangServices.updateTrangThaiImeiXoaTrongGioHangLenBangSanPham(x.getMaImei());
//            }
////            hoaDonChiTietLuongBanHangServices.deleteImeiKTGioHang(tblImeiXoa.getValueAt(tblImeiXoa.getSelectedRow(), 0).toString());
//
////            hoaDonChiTietLuongBanHangServices.updateTrangThaiImeiXoaTrongGioHangLenBangSanPham(
////                    tblImeiXoa.getValueAt(tblImeiXoa.getSelectedRow(), 0).toString());
//            //load table 3 bảng tring JDialog
//            loadtableJDialog();
//
//            //load bảng giỏ hàng
//            addTableGioHangBanHang(hoaDonChiTietLuongBanHangServices.getAllHDCT(txtMaHD.getText().trim()));
//
//            //loadts bảng sản phẩm
//            loadTable(hoaDonChiTietLuongBanHangServices.getAll());
//            listImeiDelete.removeAll(listImeiDelete);
        //                    hoaDonChiTietLuongBanHangServices.updateSoLuongSanPham(tblImei1.getValueAt(tblImei1.getSelectedRow(), 0).toString());
        //}
        if (!chkXoaImeiJDialog.isSelected()) {

            if (JOptionPane.showConfirmDialog(this.jDialogImeiBanHang, "XÁC NHẬN XOÁ", "THÔNG BÁO", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                ArrayList<ImeiViewModel> listImeiXoaJDialog = new ArrayList<>();
                for (int i = 0; i < tblImeiXoa.getRowCount(); i++) {
//                System.out.println(tblImei.getValueAt(i, 3).toString());
                    if (((Boolean) tblImeiXoa.getValueAt(i, 3)) == null) {
                        continue;
                    } else if (((Boolean) tblImeiXoa.getValueAt(i, 3)) == false) {
                        continue;
                    } else {
                        ImeiViewModel imei = new ImeiViewModel();
                        imei.setMaImei(tblImeiXoa.getValueAt(i, 0).toString());
                        imei.setMaSanPham(tblImeiXoa.getValueAt(i, 1).toString());
                        imei.setTrangThai(0);

                        listImeiXoaJDialog.add(imei);

                    }
                }

                if (listImeiXoaJDialog.size() > 0) {
                    for (ImeiViewModel x : listImeiXoaJDialog) {
                        hoaDonChiTietLuongBanHangServices.deleteImeiKTGioHang(x.getMaImei());
                        hoaDonChiTietLuongBanHangServices.updateTrangThaiImeiXoaTrongGioHangLenBangSanPham(x.getMaImei());
                    }
                } else {
                    JOptionPane.showMessageDialog(this.jDialogImeiBanHang, "HÃY CLICK VÀO IMEI TRƯỚC KHI XOÁ!", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
                    return;
                }

//                for (ImeiViewModel x : listImeiDelete) {
//                    listImeiAdd.removeAll(listImeiAdd);
//                    hoaDonChiTietLuongBanHangServices.deleteImeiKTGioHang(x.getMaImei());
//                    hoaDonChiTietLuongBanHangServices.updateTrangThaiImeiXoaTrongGioHangLenBangSanPham(x.getMaImei());
//                }
//            hoaDonChiTietLuongBanHangServices.deleteImeiKTGioHang(tblImeiXoa.getValueAt(tblImeiXoa.getSelectedRow(), 0).toString());
//            hoaDonChiTietLuongBanHangServices.updateTrangThaiImeiXoaTrongGioHangLenBangSanPham(
//                    tblImeiXoa.getValueAt(tblImeiXoa.getSelectedRow(), 0).toString());
                //load table 3 bảng tring JDialog
                loadtableJDialog();

                //load bảng giỏ hàng
                addTableGioHangBanHang(hoaDonChiTietLuongBanHangServices.getAllHDCT(txtMaHD.getText().trim()));

                //loadts bảng sản phẩm
                loadTable(hoaDonChiTietLuongBanHangServices.getAll());
                listImeiXoaJDialog.removeAll(listImeiXoaJDialog);
                loadCbThemImeiJDialog();
                loadCbXoaSanPhamJDialog();
                loadCbXoaImeiJDialog();

                chkXoaImeiJDialog.setSelected(false);
                //                    hoaDonChiTietLuongBanHangServices.updateSoLuongSanPham(tblImei1.getValueAt(tblImei1.getSelectedRow(), 0).toString());
            }
        } else {
            if (JOptionPane.showConfirmDialog(this.jDialogImeiBanHang, "XÁC NHẬN XOÁ TẤT CẢ IMEI?", "THÔNG BÁO", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                ArrayList<ImeiViewModel> listImeiXoaJDialog = new ArrayList<>();
                for (int i = 0; i < tblImeiXoa.getRowCount(); i++) {
//                System.out.println(tblImei.getValueAt(i, 3).toString());
                    if (((Boolean) tblImeiXoa.getValueAt(i, 3)) == null) {
                        continue;
                    } else if (((Boolean) tblImeiXoa.getValueAt(i, 3)) == false) {
                        continue;
                    } else {
                        ImeiViewModel imei = new ImeiViewModel();
                        imei.setMaImei(tblImeiXoa.getValueAt(i, 0).toString());
                        imei.setMaSanPham(tblImeiXoa.getValueAt(i, 1).toString());
                        imei.setTrangThai(0);

                        listImeiXoaJDialog.add(imei);

                    }
                }

                if (listImeiXoaJDialog.size() > 0) {
                    for (ImeiViewModel x : listImeiXoaJDialog) {
                        hoaDonChiTietLuongBanHangServices.deleteImeiKTGioHang(x.getMaImei());
                        hoaDonChiTietLuongBanHangServices.updateTrangThaiImeiXoaTrongGioHangLenBangSanPham(x.getMaImei());
                    }
                } else {
                    JOptionPane.showMessageDialog(this.jDialogImeiBanHang, "HÃY CLICK VÀO IMEI TRƯỚC KHI XOÁ!", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                //load table 3 bảng tring JDialog
                loadtableJDialog();

                //load bảng giỏ hàng
                addTableGioHangBanHang(hoaDonChiTietLuongBanHangServices.getAllHDCT(txtMaHD.getText().trim()));

                //loadts bảng sản phẩm
                loadTable(hoaDonChiTietLuongBanHangServices.getAll());
                listImeiXoaJDialog.removeAll(listImeiXoaJDialog);
                loadCbThemImeiJDialog();
                loadCbXoaSanPhamJDialog();
                loadCbXoaImeiJDialog();

                chkXoaImeiJDialog.setSelected(false);

            }

        }

    }//GEN-LAST:event_btnOKXoaJDiaLogImeiBanHangActionPerformed

    private void btlThemSoLuongSanPhamJDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlThemSoLuongSanPhamJDialogActionPerformed
        if (cbThemSoLuongSPJDialog.getSelectedItem() != cbThemSoLuongSPJDialog.getItemAt(0)) {
            if (checkTrong()) {

                if (Integer.parseInt(txtThemSoLuongSanPhamJDialog.getText().trim())
                        - hoaDonChiTietLuongBanHangServices.getSoLuongTonSanPham(cbThemSoLuongSPJDialog.getSelectedItem().toString()) < 0) {

                    hoaDonChiTietLuongBanHangServices.capNhatSoLuongChuotPhai(
                            cbThemSoLuongSPJDialog.getSelectedItem().toString(),
                            txtMaHD.getText().trim(), Integer.parseInt(txtThemSoLuongSanPhamJDialog.getText().trim()));

                    // cập nhật lại số lượng tồn ở sản phẩm
                    hoaDonChiTietLuongBanHangServices.updateXoaSoLuongSanPham(
                            Integer.parseInt(txtThemSoLuongSanPhamJDialog.getText().trim()), cbThemSoLuongSPJDialog.getSelectedItem().toString());

                    JOptionPane.showMessageDialog(this.jDialogImeiBanHang, "CẬP NHẬT SỐ LƯỢNG THÀNH CÔNG", "THÔNG BÁO!", JOptionPane.QUESTION_MESSAGE);

                    loadtableJDialog();
                    //load bảng giỏ hàng
                    addTableGioHangBanHang(hoaDonChiTietLuongBanHangServices.getAllHDCT(txtMaHD.getText().trim()));

                    //loadts bảng sản phẩm
                    loadTable(hoaDonChiTietLuongBanHangServices.getAll());
                } else if (Integer.parseInt(txtThemSoLuongSanPhamJDialog.getText().trim())
                        - hoaDonChiTietLuongBanHangServices.getSoLuongTonSanPham(cbThemSoLuongSPJDialog.getSelectedItem().toString()) == 0) {
                    hoaDonChiTietLuongBanHangServices.capNhatSoLuongChuotPhai(
                            cbThemSoLuongSPJDialog.getSelectedItem().toString(),
                            txtMaHD.getText().trim(), Integer.parseInt(txtThemSoLuongSanPhamJDialog.getText().trim()));

                    // cập nhật lại số lượng tồn ở sản phẩm
                    hoaDonChiTietLuongBanHangServices.updateXoaSoLuongSanPham(
                            Integer.parseInt(txtThemSoLuongSanPhamJDialog.getText().trim()), cbThemSoLuongSPJDialog.getSelectedItem().toString());

                    JOptionPane.showMessageDialog(this.jDialogImeiBanHang, "CẬP NHẬT SỐ LƯỢNG THÀNH CÔNG", "THÔNG BÁO!", JOptionPane.QUESTION_MESSAGE);

                    loadtableJDialog();
                    //load bảng giỏ hàng
                    addTableGioHangBanHang(hoaDonChiTietLuongBanHangServices.getAllHDCT(txtMaHD.getText().trim()));

                    //loadts bảng sản phẩm
                    loadTable(hoaDonChiTietLuongBanHangServices.getAll());
                } else {
                    JOptionPane.showMessageDialog(this.jDialogImeiBanHang, "SỐ LƯỢNG THÊM VƯỢT QUÁ SỐ LƯỢNG TRONG KHO!", "THÔNG BÁO!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this.jDialogImeiBanHang, "MỜI CHỌN MÃ SẢN PHẨM", "THÔNG BÁO!", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_btlThemSoLuongSanPhamJDialogActionPerformed
    private boolean checkTrong2() {
        try {
            if (Integer.parseInt(txtGiamSoLuongSanPhamJDialog.getText().trim()) <= 0
                    || Integer.parseInt(txtGiamSoLuongSanPhamJDialog.getText().trim()) > 10000000) {
                JOptionPane.showMessageDialog(this.jDialogImeiBanHang, "Nhập sai lương!", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.jDialogImeiBanHang, "Nhập sai định dạng lương!", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    private void btlGiamSoLuongSanPhamJDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlGiamSoLuongSanPhamJDialogActionPerformed
        if (cbXoaSoLuongSPJDialog.getSelectedItem() != cbXoaSoLuongSPJDialog.getItemAt(0)) {
            if (checkTrong2()) {

                if (Integer.parseInt(txtGiamSoLuongSanPhamJDialog.getText().trim())
                        - hoaDonChiTietLuongBanHangServices.getSoLuongSanPhamTrongHDCT(cbXoaSoLuongSPJDialog.getSelectedItem().toString(),
                                txtMaHD.getText().trim()) < 0) {

                    hoaDonChiTietLuongBanHangServices.updateTruSoLuongSanPhamHDCT(
                            cbXoaSoLuongSPJDialog.getSelectedItem().toString(),
                            txtMaHD.getText().trim(), Integer.parseInt(txtGiamSoLuongSanPhamJDialog.getText().trim()));

                    // cập nhật số lượng tồn lại sản phẩm
                    hoaDonChiTietLuongBanHangServices.updateThemSoLuongSanPham(
                            Integer.parseInt(txtGiamSoLuongSanPhamJDialog.getText().trim()), cbXoaSoLuongSPJDialog.getSelectedItem().toString());

                    loadtableJDialog();
                    //load bảng giỏ hàng
                    addTableGioHangBanHang(hoaDonChiTietLuongBanHangServices.getAllHDCT(txtMaHD.getText().trim()));

                    //loadts bảng sản phẩm
                    loadTable(hoaDonChiTietLuongBanHangServices.getAll());

                    JOptionPane.showMessageDialog(this.jDialogImeiBanHang, "CẬP NHẬT SỐ LƯỢNG THÀNH CÔNG", "THÔNG BÁO!", JOptionPane.QUESTION_MESSAGE);
                } else if (Integer.parseInt(txtGiamSoLuongSanPhamJDialog.getText().trim())
                        - hoaDonChiTietLuongBanHangServices.getSoLuongSanPhamTrongHDCT(cbXoaSoLuongSPJDialog.getSelectedItem().toString(),
                                txtMaHD.getText().trim()) == 0) {
                    if (hoaDonChiTietLuongBanHangServices.getSoLuongImeiKTHDCTJDialog(cbXoaSoLuongSPJDialog.getSelectedItem().toString(),
                            txtMaHD.getText().trim()) > 0) {

                        if (JOptionPane.showConfirmDialog(this.jDialogImeiBanHang, "BẠN CÓ CHẮC XOÁ HẾT SẢN PHẨM NÀY\n*VÀ XOÁ HẾT IMEI CỦA SẢN PHẨM TRONG GIỎ HÀNG?",
                                "XÁC NHẬN", JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION) {

                            //                        hoaDonChiTietLuongBanHangServices.deleteImeiKTGioHang(tblImeiXoa.getValueAt(tblImeiXoa.getSelectedRow(), 0).toString());
                            //                hoaDonChiTietLuongBanHangServices.updateTrangThaiImeiXoaTrongGioHangLenBangSanPham(
                            //                        tblImeiXoa.getValueAt(tblImeiXoa.getSelectedRow(), 0).toString());
                            // dùng for để xoá imei_KT có trong giỏ hàng, và update lại trọng thái imei
                            for (HoaDonChiTietLuongbanHangViewModel hd
                                    : hoaDonChiTietLuongBanHangServices.getAllListImeiKTHDCTJDialog(cbXoaSoLuongSPJDialog.getSelectedItem().toString(),
                                            txtMaHD.getText().trim())) {
                                hoaDonChiTietLuongBanHangServices.deleteImeiKTGioHang(hd.getMaImei());
                                hoaDonChiTietLuongBanHangServices.updateTrangThaiImeiXoaTrongGioHangLenBangSanPham(hd.getMaImei());

                            }

                            hoaDonChiTietLuongBanHangServices.updateTruSoLuongSanPhamHDCT(
                                    cbXoaSoLuongSPJDialog.getSelectedItem().toString(),
                                    txtMaHD.getText().trim(), Integer.parseInt(txtGiamSoLuongSanPhamJDialog.getText().trim()));

                            // cập nhật số lượng tồn lại sản phẩm
                            hoaDonChiTietLuongBanHangServices.updateThemSoLuongSanPham(
                                    Integer.parseInt(txtGiamSoLuongSanPhamJDialog.getText().trim()), cbXoaSoLuongSPJDialog.getSelectedItem().toString());

                            loadtableJDialog();
                            loadCbThemImeiJDialog();
                            loadCbXoaSanPhamJDialog();
                            loadCbXoaImeiJDialog();
                            //load bảng giỏ hàng
                            addTableGioHangBanHang(hoaDonChiTietLuongBanHangServices.getAllHDCT(txtMaHD.getText().trim()));

                            //loadts bảng sản phẩm
                            loadTable(hoaDonChiTietLuongBanHangServices.getAll());

                            JOptionPane.showMessageDialog(this.jDialogImeiBanHang, "XOÁ SẢN PHẨM THÀNH CÔNG KHỎI GIỎ HÀNG", "THÔNG BÁO!", JOptionPane.QUESTION_MESSAGE);
                        } else {
                            return;
                        }
                    } else {

                        hoaDonChiTietLuongBanHangServices.updateTruSoLuongSanPhamHDCT(
                                cbXoaSoLuongSPJDialog.getSelectedItem().toString(),
                                txtMaHD.getText().trim(), Integer.parseInt(txtGiamSoLuongSanPhamJDialog.getText().trim()));

                        // cập nhật số lượng tồn lại sản phẩm
                        hoaDonChiTietLuongBanHangServices.updateThemSoLuongSanPham(
                                Integer.parseInt(txtGiamSoLuongSanPhamJDialog.getText().trim()), cbXoaSoLuongSPJDialog.getSelectedItem().toString());

                        loadtableJDialog();
                        //load bảng giỏ hàng
                        addTableGioHangBanHang(hoaDonChiTietLuongBanHangServices.getAllHDCT(txtMaHD.getText().trim()));

                        //loadts bảng sản phẩm
                        loadTable(hoaDonChiTietLuongBanHangServices.getAll());

                        JOptionPane.showMessageDialog(this.jDialogImeiBanHang, "XOÁ SẢN PHẨM THÀNH CÔNG KHỎI GIỎ HÀNG", "THÔNG BÁO!", JOptionPane.QUESTION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this.jDialogImeiBanHang, "SỐ LƯỢNG XOÁ LỚN HƠN SỐ LƯỢNG TRONG HOÁ ĐƠN CHI TIẾT!", "THÔNG BÁO!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this.jDialogImeiBanHang, "MỜI CHỌN MÃ SẢN PHẨM", "THÔNG BÁO!", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_btlGiamSoLuongSanPhamJDialogActionPerformed

    private void cbXoaSoLuongSPJDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbXoaSoLuongSPJDialogActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbXoaSoLuongSPJDialogActionPerformed

    private void btnThoatJDialodImeiBanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatJDialodImeiBanHangActionPerformed
        // TODO add your handling code here:
        jDialogImeiBanHang.dispose();
    }//GEN-LAST:event_btnThoatJDialodImeiBanHangActionPerformed

    private void tblImeiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblImeiMouseClicked
        // TODO add your handling code here:
//        try {
//            ImeiViewModel imeiAdd = new ImeiViewModel();
//            Boolean check = ((Boolean) tblImei.getValueAt(tblImei.getSelectedRow(), 3));
//            if (check) {
//                imeiAdd.setMaImei(tblImei.getValueAt(tblImei.getSelectedRow(), 0).toString());
//                imeiAdd.setTrangThai(0);
//                imeiAdd.setMaSanPham(tblImei.getValueAt(tblImei.getSelectedRow(), 1).toString());
//                listImeiAdd.add(imeiAdd);
//            } else {
//                for (ImeiViewModel x : listImeiAdd) {
//                    if (x.getMaImei().equals(tblImei.getValueAt(tblImei.getSelectedRow(), 0))) {
//                        listImeiAdd.remove(x);
//                    }
//                }
//            }
//        } catch (ConcurrentModificationException e) {
//            return;
//        }
        if (((Boolean) tblImei.getValueAt(tblImei.getSelectedRow(), 3)) == null) {
            tblImei.setValueAt(true, tblImei.getSelectedRow(), 3);
        } else if (((Boolean) tblImei.getValueAt(tblImei.getSelectedRow(), 3)) == false) {
            tblImei.setValueAt(true, tblImei.getSelectedRow(), 3);

        } else {
            tblImei.setValueAt(false, tblImei.getSelectedRow(), 3);

        }
    }//GEN-LAST:event_tblImeiMouseClicked

    private void tblImeiXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblImeiXoaMouseClicked
        // TODO add your handling code here:
//        try {
//            ImeiViewModel imeiDel = new ImeiViewModel();
//            Boolean check = ((Boolean) tblImeiXoa.getValueAt(tblImeiXoa.getSelectedRow(), 3));
//            if (check) {
//                imeiDel.setMaImei(tblImeiXoa.getValueAt(tblImeiXoa.getSelectedRow(), 0).toString());
//                listImeiDelete.add(imeiDel);
//            } else {
//                for (ImeiViewModel x : listImeiDelete) {
//                    if (x.getMaImei().equals(tblImeiXoa.getValueAt(tblImeiXoa.getSelectedRow(), 0))) {
//                        listImeiDelete.remove(x);
//                    }
//                }
//            }
//        } catch (ConcurrentModificationException e) {
//            return;
//        }
        try {
            if (((Boolean) tblImeiXoa.getValueAt(tblImeiXoa.getSelectedRow(), 3)) == null) {
                tblImeiXoa.setValueAt(true, tblImeiXoa.getSelectedRow(), 3);
            } else if (((Boolean) tblImeiXoa.getValueAt(tblImeiXoa.getSelectedRow(), 3)) == false) {
                tblImeiXoa.setValueAt(true, tblImeiXoa.getSelectedRow(), 3);

            } else {
                tblImeiXoa.setValueAt(false, tblImeiXoa.getSelectedRow(), 3);

            }
        } catch (ConcurrentModificationException e) {
            return;
        }
    }//GEN-LAST:event_tblImeiXoaMouseClicked

    private void chkXoaImeiJDialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkXoaImeiJDialogMouseClicked
        // TODO add your handling code here:
        if (chkXoaImeiJDialog.isSelected()) {
            for (int i = 0; i < tblImeiXoa.getRowCount(); i++) {
                tblImeiXoa.setValueAt(true, i, 3);
//                ImeiViewModel im = new ImeiViewModel();
//                im.setMaImei(tblImeiXoa.getValueAt(i, 0).toString());
//                listImeiDelete.add(im);
            }
        } else {
            for (int i = 0; i < tblImeiXoa.getRowCount(); i++) {
                tblImeiXoa.setValueAt(false, i, 3);
//                listImeiDelete.removeAll(listImeiDelete);
            }
        }
    }//GEN-LAST:event_chkXoaImeiJDialogMouseClicked

    private void chkXoaImeiJDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkXoaImeiJDialogActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkXoaImeiJDialogActionPerformed

    private void cbXoaImeiSPKhoiGHJDialogItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbXoaImeiSPKhoiGHJDialogItemStateChanged
        // TODO add your handling code here:
        if (cbXoaImeiSPKhoiGHJDialog.getSelectedIndex() == 0) {
            loadTableListXoaImeiGioHang(hoaDonChiTietLuongBanHangServices.getAllImeiXoaSanPhamJDialog(txtMaHD.getText().trim()));
        } else {
            if (cbXoaImeiSPKhoiGHJDialog.getSelectedItem() == null) {
                loadTableListXoaImeiGioHang(hoaDonChiTietLuongBanHangServices.getAllImeiXoaSanPhamJDialog(txtMaHD.getText().trim()));

            } else {
                loadTableListXoaImeiGioHang(hoaDonChiTietLuongBanHangServices.getLocCBImeiXoaSanPhamJDialog(
                        cbXoaImeiSPKhoiGHJDialog.getSelectedItem().toString(), txtMaHD.getText().trim()));
            }
        }
    }//GEN-LAST:event_cbXoaImeiSPKhoiGHJDialogItemStateChanged

    private void cbXoaImeiSPKhoiGHJDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbXoaImeiSPKhoiGHJDialogActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbXoaImeiSPKhoiGHJDialogActionPerformed
    private void loadHD(ArrayList<HoaDon> list) {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        for (HoaDon hoaDonn : list) {
            model.addRow(new Object[]{
                hoaDonn.getMaHD(),
                hoaDonn.getNgayTao(),
                hoaDonn.TrangThai2(),
                hoaDonn.getTenKH(),});
        }
    }

    private void cbLocHDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbLocHDItemStateChanged
        // TODO add your handling code here:
        if (cbLocHD.getSelectedIndex() == 0) {
            loadHD(hoaDonService.getList());
        } else if (cbLocHD.getSelectedIndex() == 1) {
            loadHD(hoaDonService.getChuaThanhToan());
        } else if (cbLocHD.getSelectedIndex() == 2) {
            loadHD(hoaDonService.getDaThanhToan());
        }
    }//GEN-LAST:event_cbLocHDItemStateChanged

    private void txtTienKDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKDKeyTyped
        // TODO add your handling code here:
        if (txtTienKD.getText().trim().equals("")) {
            txtTienKD.setText("0");
        }
    }//GEN-LAST:event_txtTienKDKeyTyped

    private void txtTienCKKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienCKKeyTyped
        // TODO add your handling code here:
        if (txtTienCK.getText().trim().equals("")) {
            txtTienCK.setText("0");
        }
    }//GEN-LAST:event_txtTienCKKeyTyped

    private void txtSoDiemSDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoDiemSDKeyTyped
        // TODO add your handling code here:
        if (txtSoDiemSD.getText().trim().equals("")) {
            txtSoDiemSD.setText("0");
        }
    }//GEN-LAST:event_txtSoDiemSDKeyTyped

    private void tbnQrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnQrActionPerformed
         initWebCam();
        Qrcode.setVisible(true);
        
    }//GEN-LAST:event_tbnQrActionPerformed

    private void ThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThoatActionPerformed
        webcam.close();
        Qrcode.setVisible(false);
    }//GEN-LAST:event_ThoatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PnOffline;
    private javax.swing.JPanel PnOnline;
    private javax.swing.JDialog Qrcode;
    private javax.swing.JButton Thoat;
    private javax.swing.JButton btlGiamSoLuongSanPhamJDialog;
    private javax.swing.JButton btlThemSoLuongSanPhamJDialog;
    private javax.swing.JButton btnChon;
    private javax.swing.JButton btnDatHang;
    private javax.swing.JButton btnDoiTra;
    private javax.swing.JButton btnDoiTraOnl;
    private javax.swing.JButton btnGiaoHang;
    private javax.swing.JButton btnOKJDiaLogImeiBanHang;
    private javax.swing.JButton btnOKXoaJDiaLogImeiBanHang;
    private javax.swing.JButton btnOk1;
    private javax.swing.JButton btnSuDung;
    private javax.swing.JButton btnTaoHD;
    private javax.swing.JButton btnTaoHDOnline;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThayDoi;
    private javax.swing.JButton btnThoatJDialodImeiBanHang;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JCheckBox cbChonTatCaXoa;
    private javax.swing.JComboBox<String> cbLocHD;
    private javax.swing.JComboBox<String> cbThemImeiSPVaoGHJDialog;
    private javax.swing.JComboBox<String> cbThemSoLuongSPJDialog;
    private javax.swing.JComboBox<String> cbXoaImeiSPKhoiGHJDialog;
    private javax.swing.JComboBox<String> cbXoaSoLuongSPJDialog;
    private javax.swing.JComboBox<String> cbxLocDL;
    private javax.swing.JComboBox<String> cbxPTTT;
    private javax.swing.JComboBox<String> cbxPhanLoai;
    private javax.swing.JComboBox<String> cbxPhuongThucThanhToan;
    private javax.swing.JCheckBox chkXoaImeiJDialog;
    private javax.swing.JPopupMenu delete;
    private javax.swing.JMenuItem deleteAll;
    private javax.swing.JMenuItem ghiChu;
    private javax.swing.JDialog jDialogImeiBanHang;
    private javax.swing.JDialog jDialogImeiGioHang;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel lblLock;
    private javax.swing.JPopupMenu popMenu;
    private javax.swing.JRadioButton rdoKhongSd;
    private javax.swing.JRadioButton rdoSd;
    private javax.swing.JTabbedPane tab;
    public javax.swing.JTable tblDonHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblImei;
    private javax.swing.JTable tblImei1;
    private javax.swing.JTable tblImeiXoa;
    public static javax.swing.JTable tblSanPham;
    private javax.swing.JButton tbnQr;
    private javax.swing.JMenuItem thoat;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtDiem;
    private javax.swing.JTextField txtGiamSoLuongSanPhamJDialog;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaNhanVien;
    private com.github.lgooddatepicker.components.DatePicker txtNgayNhan;
    private com.github.lgooddatepicker.components.DatePicker txtNgayShip;
    private com.github.lgooddatepicker.components.DatePicker txtNgayTao;
    private com.github.lgooddatepicker.components.DatePicker txtNgayThanhToan;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSDTNguoiNhan;
    private javax.swing.JTextField txtSDTNguoiShip;
    private javax.swing.JTextField txtSoDiemSD;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenNguoiShip;
    private javax.swing.JTextField txtTenkh;
    private javax.swing.JTextField txtThemSoLuongSanPhamJDialog;
    private javax.swing.JTextField txtTienCK;
    private javax.swing.JTextField txtTienKD;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTim;
    private javax.swing.JTextField txtTimKiem3;
    private javax.swing.JTextField txtTimKiemJDialogImeiBanHang;
    private javax.swing.JTextField txtTimKiemXoaJDialogImeiBanHang;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txtTongTienOl;
    private com.github.lgooddatepicker.components.DatePicker txt_NgayTaoOL;
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
               
                    String chuoi = result.getText().trim();
                    txtTim.setText(chuoi);
//                  
                     boolean checkQR=true;
                    for (int i = 0; i < tblSanPham.getRowCount(); i++) {
                        if (txtTim.getText().trim().equals(tblSanPham.getValueAt(i, 0).toString())) {
//                             checkQR=false;
                            if (hoaDonService.selectTrangThaiHoaDon(txtMaHD.getText().trim()) != 1) {
                                // tạo đối tượng hoá đơn chi tiết HDChiTiet - phong nguyễn
                                HoaDonChiTietLuongbanHangViewModel hd = new HoaDonChiTietLuongbanHangViewModel();
                                int row = i;
                                if (txtMaHD.getText().trim().equals("")) {
                                 
                                    Qrcode.setVisible(false);
                                      webcam.close();
                                    int luaChon = JOptionPane.showConfirmDialog(this, "*Chưa Có Hoá Đơn\n-Bạn Có Muốn Tạo Hoá Đơn Mới không?", "XÁC NHẬN", JOptionPane.YES_NO_OPTION);

                                    if (JOptionPane.YES_OPTION == luaChon) {

                                        taoHD();

                                        try {
                                            String soLuong = JOptionPane.showInputDialog(this, "Mời nhập số lượng:", "1");

                                            int soLuongNhap = Integer.parseInt(soLuong);
                                            if (soLuong != null) {
                                                if (soLuongNhap > Integer.parseInt(tblSanPham.getValueAt(row, 2).toString())) {
                                                    JOptionPane.showMessageDialog(this, "Số lượng sản phẩm bạn vừa nhập vượt quá số lượng trong kho!");
                                                    return;
                                                } else if (soLuongNhap <= 0) {
                                                    JOptionPane.showMessageDialog(this, "Số lượng sản phẩm không được nhỏ hơn 0");
                                                    return;
                                                }

                                                hd.setMaSanPham(tblSanPham.getValueAt(row, 0).toString());
                                                hd.setSoLuongSanPham(Integer.parseInt(soLuong));

                                                String[] splits = tblSanPham.getValueAt(row, 5).toString().split(".0000$");
                                                StringBuilder stringBuilder1 = new StringBuilder();
                                                for (String x : splits) {
                                                    stringBuilder1.append(x);
                                                }
                                                hd.setDonGia(BigDecimal.valueOf(Long.valueOf(stringBuilder1.toString())));

//
                                                // thêm đối tượng sản phâm vào hoá đơn chi tiết - phong nguyễn
                                                if (hoaDonChiTietLuongBanHangServices.kiemTraCheckTrung(hd, txtMaHD.getText().trim())) {
                                                    hoaDonChiTietLuongBanHangServices.suaSoLuongSanPhamTrongHDCT(hd, txtMaHD.getText().trim());
                                                    addTableGioHangBanHang(hoaDonChiTietLuongBanHangServices.getAllHDCT(txtMaHD.getText().trim()));

                                                } else {

                                                    hoaDonChiTietLuongBanHangServices.themVaoHoaDonChiTiet(hd, txtMaHD.getText().trim());
                                                    hoaDonChiTietLuongBanHangServices.addImeiKTMacDinh(tblSanPham.getValueAt(row, 0).toString(), txtMaHD.getText().trim());
                                                    addTableGioHangBanHang(hoaDonChiTietLuongBanHangServices.getAllHDCT(txtMaHD.getText().trim()));

                                                }

//                       // update lại số lượng sản phẩm trong bảng sản phẩm
                                                sanPhamService.updateQuantity(tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 0).toString(), Integer.parseInt(soLuong));

                                                // load lại table sản phẩm
                                                loadTable(hoaDonChiTietLuongBanHangServices.getAll());

                                            } else {
                                                return;
                                            }
                                        } catch (Exception e) {
                                            return;
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(this, "*Mời Chọn Hoá Đơn Trước Khi Thêm Sản Phẩm!");
                                        return;
                                    }

                                } else {
                                    // dong cam
                                     webcam.close();
                                    Qrcode.setVisible(false);
                                    try {
                                        String soLuong = JOptionPane.showInputDialog(this, "Mời nhập số lượng:", "1");

                                        int soLuongNhap = Integer.parseInt(soLuong);
                                        if (soLuong != null) {
                                            if (soLuongNhap > Integer.parseInt(tblSanPham.getValueAt(row, 2).toString())) {
                                                JOptionPane.showMessageDialog(this, "Số lượng sản phẩm bạn vừa nhập vượt quá số lượng trong kho!");
                                                return;
                                            } else if (soLuongNhap <= 0) {
                                                JOptionPane.showMessageDialog(this, "Số lượng sản phẩm không được nhỏ hơn 0");
                                                return;
                                            }

                                            hd.setMaSanPham(tblSanPham.getValueAt(row, 0).toString());
                                            hd.setSoLuongSanPham(Integer.parseInt(soLuong));

                                            String[] splits = tblSanPham.getValueAt(row, 5).toString().split(".0000$");
                                            StringBuilder stringBuilder1 = new StringBuilder();
                                            for (String x : splits) {
                                                stringBuilder1.append(x);
                                            }
                                            hd.setDonGia(BigDecimal.valueOf(Long.valueOf(stringBuilder1.toString())));

//
                                            // thêm đối tượng sản phâm vào hoá đơn chi tiết - phong nguyễn
                                            if (hoaDonChiTietLuongBanHangServices.kiemTraCheckTrung(hd, txtMaHD.getText().trim())) {
                                                hoaDonChiTietLuongBanHangServices.suaSoLuongSanPhamTrongHDCT(hd, txtMaHD.getText().trim());
                                                addTableGioHangBanHang(hoaDonChiTietLuongBanHangServices.getAllHDCT(txtMaHD.getText().trim()));

                                            } else {

                                                hoaDonChiTietLuongBanHangServices.themVaoHoaDonChiTiet(hd, txtMaHD.getText().trim());
                                                hoaDonChiTietLuongBanHangServices.addImeiKTMacDinh(tblSanPham.getValueAt(row, 0).toString(), txtMaHD.getText().trim());
                                                addTableGioHangBanHang(hoaDonChiTietLuongBanHangServices.getAllHDCT(txtMaHD.getText().trim()));

                                            }

//                       // update lại số lượng sản phẩm trong bảng sản phẩm
                                            sanPhamService.updateQuantity(tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 0).toString(), Integer.parseInt(soLuong));

                                            // load lại table sản phẩm
                                            loadTable(hoaDonChiTietLuongBanHangServices.getAll());

                                        } else {
                                            return;
                                        }
                                    } catch (Exception e) {
                                        return;
                                    }
                                }
                            } else if (hoaDonService.selectTrangThaiHoaDon(txtMaHD.getText().trim()) == 1) {
                                JOptionPane.showMessageDialog(this, "*HOÁ ĐƠN ĐÃ THANH TOÁN\n-KHÔNG THỂ THÊM SẢN PHẨM", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
                                return;
                            } else {
                                JOptionPane.showMessageDialog(this, "Lỗi", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                             JOptionPane.showMessageDialog(this, "Quét Thành Công");
                    webcam.close();
                        }
                        
                    }
                    if(checkQR){
                        JOptionPane.showMessageDialog(this, "Mã Không tồn Tại");
                    }
                    
                   
                    
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
