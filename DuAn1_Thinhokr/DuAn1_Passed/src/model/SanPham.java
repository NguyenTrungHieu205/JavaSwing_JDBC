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
public class SanPham {

    private String id;
    private String maSP;
    private String tenSP;
    private BigDecimal giaNhap;
    private BigDecimal giaBan;
    private Integer soLuongTon;
    private String moTa;
    private Integer trangThai;
    private Integer namBH;
    private String id_DLuong;
    private String id_MauSac;
    private String id_XuatXu;
    private String id_PhanLoai;
    private String id_Pin;
    private String id_Chip;
    private String id_Ram;
    private String id_ManHinh;
    private String id_KhichThuoc;
    private String maSPCT;

    public SanPham() {
    }

    public SanPham(String id, String maSP, String tenSP, BigDecimal giaNhap, BigDecimal giaBan, Integer soLuongTon, String moTa, Integer trangThai, Integer namBH, String id_DLuong, String id_MauSac, String id_XuatXu, String id_PhanLoai, String id_Pin, String id_Chip, String id_Ram, String id_ManHinh, String id_KhichThuoc, String maSPCT) {
        this.id = id;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.soLuongTon = soLuongTon;
        this.moTa = moTa;
        this.trangThai = trangThai;
        this.namBH = namBH;
        this.id_DLuong = id_DLuong;
        this.id_MauSac = id_MauSac;
        this.id_XuatXu = id_XuatXu;
        this.id_PhanLoai = id_PhanLoai;
        this.id_Pin = id_Pin;
        this.id_Chip = id_Chip;
        this.id_Ram = id_Ram;
        this.id_ManHinh = id_ManHinh;
        this.id_KhichThuoc = id_KhichThuoc;
        this.maSPCT = maSPCT;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public BigDecimal getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(BigDecimal giaNhap) {
        this.giaNhap = giaNhap;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public Integer getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(Integer soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public Integer getNamBH() {
        return namBH;
    }

    public void setNamBH(Integer namBH) {
        this.namBH = namBH;
    }

    public String getId_DLuong() {
        return id_DLuong;
    }

    public void setId_DLuong(String id_DLuong) {
        this.id_DLuong = id_DLuong;
    }

    public String getId_MauSac() {
        return id_MauSac;
    }

    public void setId_MauSac(String id_MauSac) {
        this.id_MauSac = id_MauSac;
    }

    public String getId_XuatXu() {
        return id_XuatXu;
    }

    public void setId_XuatXu(String id_XuatXu) {
        this.id_XuatXu = id_XuatXu;
    }

    public String getId_PhanLoai() {
        return id_PhanLoai;
    }

    public void setId_PhanLoai(String id_PhanLoai) {
        this.id_PhanLoai = id_PhanLoai;
    }

    public String getId_Pin() {
        return id_Pin;
    }

    public void setId_Pin(String id_Pin) {
        this.id_Pin = id_Pin;
    }

    public String getId_Chip() {
        return id_Chip;
    }

    public void setId_Chip(String id_Chip) {
        this.id_Chip = id_Chip;
    }

    public String getId_Ram() {
        return id_Ram;
    }

    public void setId_Ram(String id_Ram) {
        this.id_Ram = id_Ram;
    }

    public String getId_ManHinh() {
        return id_ManHinh;
    }

    public void setId_ManHinh(String id_ManHinh) {
        this.id_ManHinh = id_ManHinh;
    }

    public String getId_KhichThuoc() {
        return id_KhichThuoc;
    }

    public void setId_KhichThuoc(String id_KhichThuoc) {
        this.id_KhichThuoc = id_KhichThuoc;
    }

    public String getMaSPCT() {
        return maSPCT;
    }

    public void setMaSPCT(String maSPCT) {
        this.maSPCT = maSPCT;
    }

}
