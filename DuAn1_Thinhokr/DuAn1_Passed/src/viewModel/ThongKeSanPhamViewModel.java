/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

import java.math.BigDecimal;

/**
 *
 * @author ADMIN
 */
public class ThongKeSanPhamViewModel {
    private String maSP;
    private String tenSP;
    private BigDecimal giaBan;
    private int soLuongTon;
    private long daBan;
    private int trangThai;
    private BigDecimal giaNhap;
    private BigDecimal tienLai;

    public ThongKeSanPhamViewModel() {
    }

    public ThongKeSanPhamViewModel(String maSP, String tenSP, BigDecimal giaBan, int soLuongTon, long daBan, int trangThai, BigDecimal giaNhap, BigDecimal tienLai) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.giaBan = giaBan;
        this.soLuongTon = soLuongTon;
        this.daBan = daBan;
        this.trangThai = trangThai;
        this.giaNhap = giaNhap;
        this.tienLai = tienLai;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public long getDaBan() {
        return daBan;
    }

    public void setDaBan(long daBan) {
        this.daBan = daBan;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public BigDecimal getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(BigDecimal giaNhap) {
        this.giaNhap = giaNhap;
    }

    public BigDecimal getTienLai() {
        return tienLai;
    }

    public void setTienLai(BigDecimal tienLai) {
        this.tienLai = tienLai;
    }

    
}
