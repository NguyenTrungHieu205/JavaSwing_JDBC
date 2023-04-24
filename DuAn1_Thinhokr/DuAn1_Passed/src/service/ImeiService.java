/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;
import java.util.ArrayList;
import java.util.List;

import viewModel.ImeiViewModel;
import viewModel.SanPhamImeiViewModel;

/**
 *
 * @author Admin
 */
public interface ImeiService {
    ArrayList<ImeiViewModel> getAll();

    ArrayList<ImeiViewModel> getAllXoa();

    boolean addImei(ImeiViewModel imei );

    boolean update(ImeiViewModel imei);
    
    boolean updateTT1(String maImei);
    
    boolean updateTT5(String maImei);
    
    boolean updateTT0(String maImei);
    
    boolean updateTTXoaAll(String maSp);

    boolean deleteImei(String imei);

    Integer hoanTac(ImeiViewModel imei);
    
    // lấy ra all sản phẩm
    ArrayList<SanPhamImeiViewModel> selectAllSanPhamBangSanPham();
    
    // lấy ra list tìm kiêm stheo mã imei or mã sản phẩm
     ArrayList<SanPhamImeiViewModel> selecttimKiemSanPhamBangSanPham(String ma);
     
     // lấy ra list tìm kiêm stheo mã imei or mã sản phẩm
    ArrayList<ImeiViewModel> selecttimKiemImeiBangImei(String ma);
    
    //
    String getAllMaSP(String ma);
    
    //update số lượng sản phẩm
    boolean updateSoLuongSanPhamTrongImei(ImeiViewModel imei);
     // loc ngừng bán
     ArrayList<ImeiViewModel> locNgungBan();
     // loc Đang về
     ArrayList<ImeiViewModel> locDangVe();
      // loc Đã bán
      ArrayList<ImeiViewModel> locDaBan();
      // Lọc online
     ArrayList<ImeiViewModel> locOnline();
     
     Integer them(ImeiViewModel ii);
     
     Integer trangThaiImei(String maImei);
     
     boolean checkTrungMaImei(String maImei);

}
