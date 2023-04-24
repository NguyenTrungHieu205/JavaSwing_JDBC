/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class ThongKeNhanVienViewModel {
    private String maNV;
    private String hoVaTen;
    private String email;
    private String SDT;
    private int gioiTinh;
    private Date ngayBatDauLV;
    private String chucVu;

    public ThongKeNhanVienViewModel() {
    }

    public ThongKeNhanVienViewModel(String maNV, String hoVaTen, String email, String SDT, int gioiTinh, Date ngayBatDauLV, String chucVu) {
        this.maNV = maNV;
        this.hoVaTen = hoVaTen;
        this.email = email;
        this.SDT = SDT;
        this.gioiTinh = gioiTinh;
        this.ngayBatDauLV = ngayBatDauLV;
        this.chucVu = chucVu;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgayBatDauLV() {
        return ngayBatDauLV;
    }

    public void setNgayBatDauLV(Date ngayBatDauLV) {
        this.ngayBatDauLV = ngayBatDauLV;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
    
    
}
