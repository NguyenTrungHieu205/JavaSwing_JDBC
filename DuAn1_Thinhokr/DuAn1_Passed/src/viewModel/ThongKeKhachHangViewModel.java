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
public class ThongKeKhachHangViewModel {
    private String maKH;
    private String hoTen;
    private String sdt;
    private Date ngaySinh;
    private String diaChi;
    private Integer gioiTinh;
    private Integer soLanMua;
    private Integer soDiem;

    public ThongKeKhachHangViewModel() {
    }

    public ThongKeKhachHangViewModel(String maKH, String hoTen, String sdt, Date ngaySinh, String diaChi, Integer gioiTinh, Integer soLanMua, Integer soDiem) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.soLanMua = soLanMua;
        this.soDiem = soDiem;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Integer getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Integer gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Integer getSoLanMua() {
        return soLanMua;
    }

    public void setSoLanMua(Integer soLanMua) {
        this.soLanMua = soLanMua;
    }

    public Integer getSoDiem() {
        return soDiem;
    }

    public void setSoDiem(Integer soDiem) {
        this.soDiem = soDiem;
    }
    
    
    
}
