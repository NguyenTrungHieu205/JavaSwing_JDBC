/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.util.List;
import viewModel.SanPhamViewModel;
import viewModel.ViewSanPham;

/**
 *
 * @author phamtuyetnga
 */
public interface SPService {

    ArrayList<ViewSanPham> getAll();

    SanPhamViewModel timSP(String loaiHang);

    List<String> selectSP(String ten);

    ArrayList<SanPhamViewModel> locLoaiHang(String tenLH);

    ArrayList<ViewSanPham> loaiHang(String tenLH);

    ArrayList<ViewSanPham> dungLuong(String tenDL);

    Integer updateQuantity(String maSp, Integer quantity);

    Integer updateQuantity1(String maSp, Integer quantity);
    
    ViewSanPham selectSoLuongSanPhamBangSanPham(String maSP);
}
