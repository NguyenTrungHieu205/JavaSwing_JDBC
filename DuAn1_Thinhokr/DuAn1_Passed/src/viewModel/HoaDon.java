/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

import java.util.Date;

/**
 *
 * @author phamtuyetnga
 */
public class HoaDon {

    private String maHD;
    private Date ngayTao;
    private Integer TrangThai;
    private String hoTenKH;

    public HoaDon() {
    }

    public HoaDon(String maHD, Date ngayTao, Integer TrangThai, String hoTenKH) {
        this.maHD = maHD;
        this.ngayTao = ngayTao;
        this.TrangThai = TrangThai;
        this.hoTenKH = hoTenKH;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Integer getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(Integer TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getHoTenKH() {
        return hoTenKH;
    }

    public void setHoTenKH(String hoTenKH) {
        this.hoTenKH = hoTenKH;
    }
    
    

}
