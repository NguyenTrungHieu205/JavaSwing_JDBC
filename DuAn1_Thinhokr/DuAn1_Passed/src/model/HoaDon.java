/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author phamtuyetnga
 */
public class HoaDon {

    private String id;
    private String maHD;
    private String maNV;
    private String ngayTao;
    private Integer SoLuong;
    private String ngayThanhToan;
    private String ngayShip;
    private String ngayNhan;
    private String tenKH;
    private String diaChi;
    private BigDecimal tongTien;
    private Integer trangThai;
    private String sdtNguoiNhan;
    private String sdtNguoiiShip;
    private String tenNguoiShip;
    private String phanTramGiam;
    private Integer soDiemSD;
    private BigDecimal soTienQuyDoi;
    private String idKH;
    private String idNhanVien;
    private String tenHTTT;

    public HoaDon() {
    }

    public HoaDon(String id, String maHD, String maNV, String ngayTao, Integer SoLuong, String ngayThanhToan, String ngayShip, String ngayNhan, String tenKH, String diaChi, BigDecimal tongTien, Integer trangThai, String sdtNguoiNhan, String sdtNguoiiShip, String tenNguoiShip, String phanTramGiam, Integer soDiemSD, BigDecimal soTienQuyDoi, String idKH, String idNhanVien, String tenHTTT) {
        this.id = id;
        this.maHD = maHD;
        this.maNV = maNV;
        this.ngayTao = ngayTao;
        this.SoLuong = SoLuong;
        this.ngayThanhToan = ngayThanhToan;
        this.ngayShip = ngayShip;
        this.ngayNhan = ngayNhan;
        this.tenKH = tenKH;
        this.diaChi = diaChi;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
        this.sdtNguoiNhan = sdtNguoiNhan;
        this.sdtNguoiiShip = sdtNguoiiShip;
        this.tenNguoiShip = tenNguoiShip;
        this.phanTramGiam = phanTramGiam;
        this.soDiemSD = soDiemSD;
        this.soTienQuyDoi = soTienQuyDoi;
        this.idKH = idKH;
        this.idNhanVien = idNhanVien;
        this.tenHTTT = tenHTTT;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Integer getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(Integer SoLuong) {
        this.SoLuong = SoLuong;
    }

    public String getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(String ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public String getNgayShip() {
        return ngayShip;
    }

    public void setNgayShip(String ngayShip) {
        this.ngayShip = ngayShip;
    }

    public String getNgayNhan() {
        return ngayNhan;
    }

    public void setNgayNhan(String ngayNhan) {
        this.ngayNhan = ngayNhan;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public String getSdtNguoiNhan() {
        return sdtNguoiNhan;
    }

    public void setSdtNguoiNhan(String sdtNguoiNhan) {
        this.sdtNguoiNhan = sdtNguoiNhan;
    }

    public String getSdtNguoiiShip() {
        return sdtNguoiiShip;
    }

    public void setSdtNguoiiShip(String sdtNguoiiShip) {
        this.sdtNguoiiShip = sdtNguoiiShip;
    }

    public String getTenNguoiShip() {
        return tenNguoiShip;
    }

    public void setTenNguoiShip(String tenNguoiShip) {
        this.tenNguoiShip = tenNguoiShip;
    }

    public String getPhanTramGiam() {
        return phanTramGiam;
    }

    public void setPhanTramGiam(String phanTramGiam) {
        this.phanTramGiam = phanTramGiam;
    }

    public Integer getSoDiemSD() {
        return soDiemSD;
    }

    public void setSoDiemSD(Integer soDiemSD) {
        this.soDiemSD = soDiemSD;
    }

    public BigDecimal getSoTienQuyDoi() {
        return soTienQuyDoi;
    }

    public void setSoTienQuyDoi(BigDecimal soTienQuyDoi) {
        this.soTienQuyDoi = soTienQuyDoi;
    }

    public String getIdKH() {
        return idKH;
    }

    public void setIdKH(String idKH) {
        this.idKH = idKH;
    }

    public String getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(String idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getTenHTTT() {
        return tenHTTT;
    }

    public void setTenHTTT(String tenHTTT) {
        this.tenHTTT = tenHTTT;
    }

    

     public String TrangThai2() {
        if (trangThai == 1) {
            return "Đã thanh toán";
        }else if (trangThai == 0){
            return "Chưa thanh toán";
        }else if(trangThai == 2){
            return "Đang giao";
        }else if(trangThai ==3){
            return "Đã giao";
        }else if(trangThai == 4){
            return "Đã hoàn trả";
        }else if(trangThai == 5){
            return "Chờ lấy hàng";
        }else{
            return "Chưa hoàn trả";
        }
        
    }

}
