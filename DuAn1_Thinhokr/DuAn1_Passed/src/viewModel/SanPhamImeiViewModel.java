/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

import java.math.BigDecimal;

/**
 *
 * @author nguyenhongphong
 */
public class SanPhamImeiViewModel {

    private String maSp;
    private String tenSp;
    private String tenChip;
    private String tenDungLuong;
    private String tenKichThuoc;
    private String tenmanHing;
    private String tenMau;
    private String tenLoaiHang;
    private String tenPin;
    private String tenRam;
    private String tenNoiSanXuat;
    private BigDecimal giaBan;
    private int soLuongTon;

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
    private Integer trangThai;

    public SanPhamImeiViewModel() {
    }

    public String getMaSp() {
        return maSp;
    }

    public void setMaSp(String maSp) {
        this.maSp = maSp;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public String getTenChip() {
        return tenChip;
    }

    public void setTenChip(String tenChip) {
        this.tenChip = tenChip;
    }

    public String getTenDungLuong() {
        return tenDungLuong;
    }

    public void setTenDungLuong(String tenDungLuong) {
        this.tenDungLuong = tenDungLuong;
    }

    public String getTenKichThuoc() {
        return tenKichThuoc;
    }

    public void setTenKichThuoc(String tenKichThuoc) {
        this.tenKichThuoc = tenKichThuoc;
    }

    public String getTenmanHing() {
        return tenmanHing;
    }

    public void setTenmanHing(String tenmanHing) {
        this.tenmanHing = tenmanHing;
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    public String getTenLoaiHang() {
        return tenLoaiHang;
    }

    public void setTenLoaiHang(String tenLoaiHang) {
        this.tenLoaiHang = tenLoaiHang;
    }

    public String getTenPin() {
        return tenPin;
    }

    public void setTenPin(String tenPin) {
        this.tenPin = tenPin;
    }

    public String getTenRam() {
        return tenRam;
    }

    public void setTenRam(String tenRam) {
        this.tenRam = tenRam;
    }

    public String getTenNoiSanXuat() {
        return tenNoiSanXuat;
    }

    public void setTenNoiSanXuat(String tenNoiSanXuat) {
        this.tenNoiSanXuat = tenNoiSanXuat;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public String trangThaiSanPham() {
        if (trangThai == 0) {
            return "Online";
        } else if (trangThai == 1) {
            return "Offline";
        } else if (trangThai == 2) {
            return "Đang chờ hàng về";
        } else {
            return "Hết hàng";
        }
    }
}
