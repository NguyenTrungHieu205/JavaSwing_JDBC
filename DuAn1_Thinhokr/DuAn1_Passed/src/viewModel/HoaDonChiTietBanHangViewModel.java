/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

import java.math.BigDecimal;

/**
 *
 * @author phamtuyetnga
 */
public class HoaDonChiTietBanHangViewModel {

    private String maSanPham;
    private String tenSanPham;
    private int soLuong;
    private BigDecimal donGia;
    private BigDecimal tongTien;
    private String mucGiamGia;

    public HoaDonChiTietBanHangViewModel() {
    }

    public HoaDonChiTietBanHangViewModel(String maSanPham, String tenSanPham, int soLuong, BigDecimal donGia, BigDecimal tongTien, String mucGiamGia) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.tongTien = tongTien;
        this.mucGiamGia = mucGiamGia;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public String getMucGiamGia() {
        return mucGiamGia;
    }

    public void setMucGiamGia(String mucGiamGia) {
        this.mucGiamGia = mucGiamGia;
    }

    
    public int increaseQuantity(int soLuong) {
        if (soLuong < 0) {
            return -1;
        }
        this.setSoLuong(this.getSoLuong()+ soLuong);
        return this.getSoLuong();
    }
    
    public int increaseQuantity1(int soLuong) {
        if (soLuong < 0) {
            return -1;
        }
        this.setSoLuong(this.getSoLuong()- soLuong);
        return this.getSoLuong();
    }

}
