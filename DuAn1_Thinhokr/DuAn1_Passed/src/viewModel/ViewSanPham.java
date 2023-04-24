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
public class ViewSanPham {

    private String maSp;
    private String tenSp;
    private String phanLoai;
    private String ram;
    private String pin;
    private String dungLuong;
    private String manHinh;
    private Integer soLuong;
    private BigDecimal giaSp;
    private Integer trangThai;
    private BigDecimal mucGiamGia;
  

    public ViewSanPham() {
    }

    public ViewSanPham(String maSp, String tenSp, String phanLoai, String ram, String pin, String dungLuong, String manHinh, Integer soLuong, BigDecimal giaSp, Integer trangThai) {
        this.maSp = maSp;
        this.tenSp = tenSp;
        this.phanLoai = phanLoai;
        this.ram = ram;
        this.pin = pin;
        this.dungLuong = dungLuong;
        this.manHinh = manHinh;
        this.soLuong = soLuong;
        this.giaSp = giaSp;
        this.trangThai = trangThai;
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

    public String getPhanLoai() {
        return phanLoai;
    }

    public void setPhanLoai(String phanLoai) {
        this.phanLoai = phanLoai;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getDungLuong() {
        return dungLuong;
    }

    public void setDungLuong(String dungLuong) {
        this.dungLuong = dungLuong;
    }

    public String getManHinh() {
        return manHinh;
    }

    public void setManHinh(String manHinh) {
        this.manHinh = manHinh;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getGiaSp() {
        return giaSp;
    }

    public void setGiaSp(BigDecimal giaSp) {
        this.giaSp = giaSp;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public BigDecimal getMucGiamGia() {
        return mucGiamGia;
    }

    public void setMucGiamGia(BigDecimal mucGiamGia) {
        this.mucGiamGia = mucGiamGia;
    }

    @Override
    public String toString() {
        return "ViewSanPham{" + "maSp=" + maSp + ", tenSp=" + tenSp + ", phanLoai=" + phanLoai + ", ram=" + ram + ", pin=" + pin + ", dungLuong=" + dungLuong + ", manHinh=" + manHinh + ", soLuong=" + soLuong + ", giaSp=" + giaSp + ", trangThai=" + trangThai + ", mucGiamGia=" + mucGiamGia + '}';
    }

   

    public String trangThai() {
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
