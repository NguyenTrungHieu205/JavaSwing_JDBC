/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository.SPRepository;
import viewModel.SanPhamViewModel;
import service.SPService;
import viewModel.ViewSanPham;

/**
 *
 * @author phamtuyetnga
 */
public class SPServiceImpl implements SPService {

    private final SPRepository sanPhamRepo = new SPRepository();

    @Override
    public ArrayList<ViewSanPham> getAll() {
        try {
            return sanPhamRepo.getAll();
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public SanPhamViewModel timSP(String loaiHang) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<String> selectSP(String ten) {
        return sanPhamRepo.selectPLH(ten);
    }

    @Override
    public ArrayList<SanPhamViewModel> locLoaiHang(String tenLH) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ViewSanPham> loaiHang(String tenLH) {
        return sanPhamRepo.selectLoaiHanh(tenLH);
    }

    @Override
    public ArrayList<ViewSanPham> dungLuong(String tenDL) {
        return sanPhamRepo.selectDungLuong(tenDL);
    }

    @Override
    public Integer updateQuantity(String maSp, Integer quantity) {
        try {
            return sanPhamRepo.updateQuantity(maSp, quantity);
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public Integer updateQuantity1(String maSp, Integer quantity) {
        try {
            return sanPhamRepo.updateQuantity1(maSp, quantity);
        } catch (SQLException ex) {
            return null;
        }
    }
    
    @Override
    public ViewSanPham selectSoLuongSanPhamBangSanPham(String maSP) {
        try {
            return sanPhamRepo.selectSoLuongSanPham(maSP);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
