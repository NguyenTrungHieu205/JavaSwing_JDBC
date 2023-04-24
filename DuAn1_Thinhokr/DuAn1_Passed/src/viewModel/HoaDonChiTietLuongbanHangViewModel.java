/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

import java.math.BigDecimal;

/**
 *
 * @author Dell
 */
public class HoaDonChiTietLuongbanHangViewModel {

    private String maSanPham;
    private String tenSanPham;
    private int soLuongSanPham;
    private BigDecimal donGia;
    private String mucGiamGia;
    private int soLuongImeiSanPhamKT;
    private int doiTra;
    private BigDecimal tongTien;
    private String maImei;
    private int trangThai;

    public String getMaImei() {
        return maImei;
    }

    public void setMaImei(String maImei) {
        this.maImei = maImei;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public HoaDonChiTietLuongbanHangViewModel() {
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

    public int getSoLuongSanPham() {
        return soLuongSanPham;
    }

    public void setSoLuongSanPham(int soLuongSanPham) {
        this.soLuongSanPham = soLuongSanPham;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public String getMucGiamGia() {
        return mucGiamGia;
    }

    public void setMucGiamGia(String mucGiamGia) {
        this.mucGiamGia = mucGiamGia;
    }

    public int getSoLuongImeiSanPhamKT() {
        return soLuongImeiSanPhamKT;
    }

    public void setSoLuongImeiSanPhamKT(int soLuongImeiSanPhamKT) {
        this.soLuongImeiSanPhamKT = soLuongImeiSanPhamKT;
    }

    public int getDoiTra() {
        return doiTra;
    }

    public void setDoiTra(int doiTra) {
        this.doiTra = doiTra;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

}
