/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;

/**
 *
 * @author phamtuyetnga
 */
public class HDChiTiet {
    private String id;
    private Integer soLuong;
    private BigDecimal donGia;
    private BigDecimal tienThua;
    private BigDecimal tiemGiamGia;
    private Integer trangThai;
    private String idSP;
    private String hoaDon;
    private String idImei;

    public HDChiTiet() {
    }

    public HDChiTiet(String id, Integer soLuong, BigDecimal donGia, BigDecimal tienThua, BigDecimal tiemGiamGia, Integer trangThai, String idSP, String hoaDon, String idImei) {
        this.id = id;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.tienThua = tienThua;
        this.tiemGiamGia = tiemGiamGia;
        this.trangThai = trangThai;
        this.idSP = idSP;
        this.hoaDon = hoaDon;
        this.idImei = idImei;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public BigDecimal getTienThua() {
        return tienThua;
    }

    public void setTienThua(BigDecimal tienThua) {
        this.tienThua = tienThua;
    }

    public BigDecimal getTiemGiamGia() {
        return tiemGiamGia;
    }

    public void setTiemGiamGia(BigDecimal tiemGiamGia) {
        this.tiemGiamGia = tiemGiamGia;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public String getIdSP() {
        return idSP;
    }

    public void setIdSP(String idSP) {
        this.idSP = idSP;
    }

    public String getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(String hoaDon) {
        this.hoaDon = hoaDon;
    }

    public String getIdImei() {
        return idImei;
    }

    public void setIdImei(String idImei) {
        this.idImei = idImei;
    }

    @Override
    public String toString() {
        return "HDChiTiet{" + "id=" + id + ", soLuong=" + soLuong + ", donGia=" + donGia + ", tienThua=" + tienThua + ", tiemGiamGia=" + tiemGiamGia + ", trangThai=" + trangThai + ", idSP=" + idSP + ", hoaDon=" + hoaDon + ", idImei=" + idImei + '}';
    }
    
    
    
}
