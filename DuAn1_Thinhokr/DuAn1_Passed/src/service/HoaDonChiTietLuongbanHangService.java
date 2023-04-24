/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.ArrayList;
import viewModel.HoaDonChiTietLuongbanHangViewModel;
import viewModel.ImeiBanHang;
import viewModel.ImeiViewModel;
import viewModel.ViewSanPham;

/**
 *
 * @author Dell
 */
public interface HoaDonChiTietLuongbanHangService {

    // lấy ra list sản phẩm HDCT cùng 1 hoá đơn
    ArrayList<HoaDonChiTietLuongbanHangViewModel> getAllHDCT(String maHD);
    // lấy ra mức giảm giá của tường sanr phẩm

    String selectMucGiamGiaBangGioHang(String maSp);

//thêm imei kt mặc định
    boolean addImeiKTMacDinh(String maSp, String maHD);
// thêm vào hdct

//    boolean themSanPhamVaoHDCT(HoaDonChiTietLuongbanHangViewModel hd, String maHD);
//check trùng mã sp
    boolean kiemTraCheckTrung(HoaDonChiTietLuongbanHangViewModel hd, String maHD);

// + thêm số lượng sản phẩm trong giỏ hàng chi tiết nếu trunggf mã
    boolean suaSoLuongSanPhamTrongHDCT(HoaDonChiTietLuongbanHangViewModel hd, String idHD);

    // cập nhật lại ssos lượng sản phấm trong bảng sản phẩm sau khi thêm xuống giỏ hàng
    boolean updateTruSoLuongSanPhamHDCT(String maSP, String maHD, int soLuongSanPhamTru);

    ArrayList<ViewSanPham> getAll();

    boolean themHDCT(String hd);

//    ArrayList<HoaDonChiTietLuongbanHangViewModel> listHDCTBanHang(String idHD);
    boolean themVaoHoaDonChiTiet(HoaDonChiTietLuongbanHangViewModel hd, String maHD);

//    boolean kiemTraCheckTrung(HoaDonChiTietLuongbanHangViewModel hd, String maHD);
//    boolean suaSoLuongSanPhamTrongHDCT(HoaDonChiTietLuongbanHangViewModel hd, String idHD);
    boolean xoaSanPhamTrongHDCT(String maSP, String maHD);

    boolean xoaAllSanPhamHDCT(String maHD);

//    boolean updateTruSoLuongSanPhamHDCT(String maSP, String maHD, int soLuongSanPhamTru);
    boolean capNhatSoLuongChuotPhai(String maSp, String idHD, int soLuong);

    //    // list imei bán hàng
    ArrayList<ImeiBanHang> getAllListImeiBanHang(String maSp);

    //
    // list imei xoá Imei_KT
    ArrayList<ImeiViewModel> getAllListXoaImeiKt(String maSp, String maHD);

    //add imei_KT
    boolean addImeiKT(ImeiViewModel imei, String maHD);

    //update trạng thái imei bảng imei bán hàng
    boolean updateTrangThaiImeiThemVaoGioHang(String imei);

    boolean deleteImeiKTMacDinh();
    
    //load table thêm ime + load cb thêm imei
    ArrayList<HoaDonChiTietLuongbanHangViewModel> getAllImeiSanPhamJDialog(String maHD);
    ArrayList<HoaDonChiTietLuongbanHangViewModel> getAllCBImeiSanPhamJDialog(String maHD);
    
    //load table Xoá ime + load cb thêm imei
    ArrayList<HoaDonChiTietLuongbanHangViewModel> getAllImeiXoaSanPhamJDialog(String maHD);
    ArrayList<HoaDonChiTietLuongbanHangViewModel> getCBImeiXoaSanPhamJDialog(String maHD);
    
    //thêm số lượng sản phẩm 
    boolean updateThemSoLuongSanPham(int soLuong, String ma);
    
    //xoá số lượng sản phẩm 
    boolean updateXoaSoLuongSanPham(int soLuong, String ma);
    
    // lấy ra số lượng sản phẩm trong kho
     int getSoLuongTonSanPham(String maHD);
     
     //    lấy ra list mã sp trong hdct
    ArrayList<HoaDonChiTietLuongbanHangViewModel> getCBXoaSoLuongSanPhamJDialog(String maHD);
     // lấy ra số lượng sản phẩm trong HDCT
    int getSoLuongSanPhamTrongHDCT(String maSp ,String maHD);
    
    //    lấy ra số lượng imei_KT trong HDCT
    int getSoLuongImeiKTHDCTJDialog(String maSP, String maHD);
    
    //    lấy ra list imei_KT trong HDCT
     ArrayList<HoaDonChiTietLuongbanHangViewModel> getAllListImeiKTHDCTJDialog(String maSP, String maHD);
     
      boolean deleteImeiKTGioHang(String maImei);
     
     
     // update imei trong giỏ hàng lên giỏ hàng
      boolean updateTrangThaiImeiXoaTrongGioHangLenBangSanPham(String maImei);

      // lọc cb imei bảng imei bán hàng
    ArrayList<HoaDonChiTietLuongbanHangViewModel> getLocCBImeiSanPhamJDialog(String maSP ,String maHD);
    
    // lọc cb imei bảng imei_KT
     ArrayList<HoaDonChiTietLuongbanHangViewModel> getLocCBImeiXoaSanPhamJDialog(String maSP, String maHD);
     
     
     // 1412
     // lấy ra list imei ở bảng imei_KT để xoá 
     ArrayList<HoaDonChiTietLuongbanHangViewModel> listImeiXoaHDCTJDialog(String maHD);
     
     // xoá tất cả imei trong hoá đơn chi tiết
     boolean deleteAllImeiKTGioHang(String maSp, String maHD);
     
     // lấy ra list sản phẩm và số lượng trong giỏ hàng
     ArrayList<HoaDonChiTietLuongbanHangViewModel> listSanPhamXoaHDCTJDialog(String maHD);
     
      // delete all HDCT
    boolean deleteAllHDCT(String maHD);



}
