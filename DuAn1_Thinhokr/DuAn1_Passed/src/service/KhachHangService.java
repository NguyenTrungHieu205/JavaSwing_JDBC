/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.KhachHang;
import viewModel.KhachHangViewModel;

/**
 *
 * @author phamtuyetnga
 */
public interface KhachHangService {

    ArrayList<KhachHangViewModel> getAllKhachHang();

    ArrayList<KhachHangViewModel> getAllKhDaXoa();

    boolean themKhachHang(KhachHang kh);

    boolean suaKhachHang(KhachHang kh);

    boolean xoaKhachHang(String ma);

    ArrayList<KhachHang> timKiemKhachHang(String ma);
    
    ArrayList<KhachHangViewModel> locKH(String ma);
    
    Boolean checkTrungMa(String ma);
    
    Integer updateSoLanMua(String maKH);
    
    Integer lichSuDiem(Float soDiemSD, Float soDiemCong, String maKH, String maHD);
}
