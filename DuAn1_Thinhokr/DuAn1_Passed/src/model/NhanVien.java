/*
............/´¯/)........... (\¯'\
............/....//........... ...\\....\
.........../....//............ ....\\....\
...../´¯/..../´¯\.........../¯ '\....\¯'\
.././.../..../..../.|_......_| .\....\....\...\.\..
(.(....(....(..../.)..)..(..(. \....)....)....).)
.\................\/.../....\. ..\/................/
..\................. /........\..................../
....\..............(.......... ..)................/           


 */
package model;

import java.util.Date;
import viewModel.ViewModeChucVu;

/**
 *
 * @author Admin
 */
public class NhanVien {
    private String id;
    private String maNv;
    private String tenNv;
    private String tenDem;
    private String ho;
    private String ngaySinh;
    private int gioiTinh;
    private ChucVu chucvu;
    private String diaChi;
    private String email;
    private String sdt;
    private String matKhau;
    private int trangThai;
    private String ngayDiLam;
    private String ghiChu;

    public NhanVien() {
    }

    public NhanVien(String id, String maNv, String tenNv, String tenDem, String ho, String ngaySinh, int gioiTinh, ChucVu chucvu, String diaChi, String email, String sdt, String matKhau, int trangThai, String ngayDiLam, String ghiChu) {
        this.id = id;
        this.maNv = maNv;
        this.tenNv = tenNv;
        this.tenDem = tenDem;
        this.ho = ho;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.chucvu = chucvu;
        this.diaChi = diaChi;
        this.email = email;
        this.sdt = sdt;
        this.matKhau = matKhau;
        this.trangThai = trangThai;
        this.ngayDiLam = ngayDiLam;
        this.ghiChu = ghiChu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

    public String getTenNv() {
        return tenNv;
    }

    public void setTenNv(String tenNv) {
        this.tenNv = tenNv;
    }

    public String getTenDem() {
        return tenDem;
    }

    public void setTenDem(String tenDem) {
        this.tenDem = tenDem;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public ChucVu getChucvu() {
        return chucvu;
    }

    public void setChucvu(ChucVu chucvu) {
        this.chucvu = chucvu;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getNgayDiLam() {
        return ngayDiLam;
    }

    public void setNgayDiLam(String ngayDiLam) {
        this.ngayDiLam = ngayDiLam;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    
    
    
}
