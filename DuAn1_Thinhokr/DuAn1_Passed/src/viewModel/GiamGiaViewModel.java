/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class GiamGiaViewModel {

   private String maGiamGia;
    private String tenGiamGia;
    private String loaiGiamGia;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private String mucGiamGia;
    private int trangThai;

    public GiamGiaViewModel() {
    }

    public GiamGiaViewModel(String maGiamGia, String tenGiamGia, String loaiGiamGia, Date ngayBatDau, Date ngayKetThuc, String mucGiamGia, int trangThai) {
        this.maGiamGia = maGiamGia;
        this.tenGiamGia = tenGiamGia;
        this.loaiGiamGia = loaiGiamGia;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.mucGiamGia = mucGiamGia;
        this.trangThai = trangThai;
    }

    public String getMaGiamGia() {
        return maGiamGia;
    }

    public void setMaGiamGia(String maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public String getTenGiamGia() {
        return tenGiamGia;
    }

    public void setTenGiamGia(String tenGiamGia) {
        this.tenGiamGia = tenGiamGia;
    }

    public String getLoaiGiamGia() {
        return loaiGiamGia;
    }

    public void setLoaiGiamGia(String loaiGiamGia) {
        this.loaiGiamGia = loaiGiamGia;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getMucGiamGia() {
        return mucGiamGia;
    }

    public void setMucGiamGia(String mucGiamGia) {
        this.mucGiamGia = mucGiamGia;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }


   

    public String trangThai() {
        if (trangThai == 0) {
            return "Online";
        } else if (trangThai == 1) {
            return "Offline";
        } else if (trangThai == 2) {
            return "Sắp tới";
        } else{
            return "Hết sử dụng";
        }
    }

}
