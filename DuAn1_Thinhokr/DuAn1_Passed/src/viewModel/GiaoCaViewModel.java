/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Timer;

/**
 *
 * @author ADMIN
 */
public class GiaoCaViewModel {
    private String maGiaoCa;
    private String thoiGianNhanCa;
    private String thoiGianGiaoCa;
    private String maNV;
    private String maNvNhanCa;
    private BigDecimal tenBanDau;
    private BigDecimal tongTienTrongCa;
    private BigDecimal tongTienMat;
    private BigDecimal tongTienKhac;
    private BigDecimal tienPhatSinh;
    private String ghiChu;
    private BigDecimal tongTienMatCaTruoc;
    private Timer thoiGianReset;
    private BigDecimal tongTienMatDaRut;
    private Integer trangThai;

    public GiaoCaViewModel() {
    }

    public GiaoCaViewModel(String maGiaoCa, String thoiGianNhanCa, String thoiGianGiaoCa, String maNV, String maNvNhanCa, BigDecimal tenBanDau, BigDecimal tongTienTrongCa, BigDecimal tongTienMat, BigDecimal tongTienKhac, BigDecimal tienPhatSinh, String ghiChu, BigDecimal tongTienMatCaTruoc, Timer thoiGianReset, BigDecimal tongTienMatDaRut, Integer trangThai) {
        this.maGiaoCa = maGiaoCa;
        this.thoiGianNhanCa = thoiGianNhanCa;
        this.thoiGianGiaoCa = thoiGianGiaoCa;
        this.maNV = maNV;
        this.maNvNhanCa = maNvNhanCa;
        this.tenBanDau = tenBanDau;
        this.tongTienTrongCa = tongTienTrongCa;
        this.tongTienMat = tongTienMat;
        this.tongTienKhac = tongTienKhac;
        this.tienPhatSinh = tienPhatSinh;
        this.ghiChu = ghiChu;
        this.tongTienMatCaTruoc = tongTienMatCaTruoc;
        this.thoiGianReset = thoiGianReset;
        this.tongTienMatDaRut = tongTienMatDaRut;
        this.trangThai = trangThai;
    }

    public String getMaGiaoCa() {
        return maGiaoCa;
    }

    public void setMaGiaoCa(String maGiaoCa) {
        this.maGiaoCa = maGiaoCa;
    }

    public String getThoiGianNhanCa() {
        return thoiGianNhanCa;
    }

    public void setThoiGianNhanCa(String thoiGianNhanCa) {
        this.thoiGianNhanCa = thoiGianNhanCa;
    }

    public String getThoiGianGiaoCa() {
        return thoiGianGiaoCa;
    }

    public void setThoiGianGiaoCa(String thoiGianGiaoCa) {
        this.thoiGianGiaoCa = thoiGianGiaoCa;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaNvNhanCa() {
        return maNvNhanCa;
    }

    public void setMaNvNhanCa(String maNvNhanCa) {
        this.maNvNhanCa = maNvNhanCa;
    }

    public BigDecimal getTenBanDau() {
        return tenBanDau;
    }

    public void setTenBanDau(BigDecimal tenBanDau) {
        this.tenBanDau = tenBanDau;
    }

    public BigDecimal getTongTienTrongCa() {
        return tongTienTrongCa;
    }

    public void setTongTienTrongCa(BigDecimal tongTienTrongCa) {
        this.tongTienTrongCa = tongTienTrongCa;
    }

    public BigDecimal getTongTienMat() {
        return tongTienMat;
    }

    public void setTongTienMat(BigDecimal tongTienMat) {
        this.tongTienMat = tongTienMat;
    }

    public BigDecimal getTongTienKhac() {
        return tongTienKhac;
    }

    public void setTongTienKhac(BigDecimal tongTienKhac) {
        this.tongTienKhac = tongTienKhac;
    }

    public BigDecimal getTienPhatSinh() {
        return tienPhatSinh;
    }

    public void setTienPhatSinh(BigDecimal tienPhatSinh) {
        this.tienPhatSinh = tienPhatSinh;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public BigDecimal getTongTienMatCaTruoc() {
        return tongTienMatCaTruoc;
    }

    public void setTongTienMatCaTruoc(BigDecimal tongTienMatCaTruoc) {
        this.tongTienMatCaTruoc = tongTienMatCaTruoc;
    }

    public Timer getThoiGianReset() {
        return thoiGianReset;
    }

    public void setThoiGianReset(Timer thoiGianReset) {
        this.thoiGianReset = thoiGianReset;
    }

    public BigDecimal getTongTienMatDaRut() {
        return tongTienMatDaRut;
    }

    public void setTongTienMatDaRut(BigDecimal tongTienMatDaRut) {
        this.tongTienMatDaRut = tongTienMatDaRut;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
