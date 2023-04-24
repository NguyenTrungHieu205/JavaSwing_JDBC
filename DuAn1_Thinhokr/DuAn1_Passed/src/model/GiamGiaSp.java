/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.net.IDN;

/**
 *
 * @author Admin
 */
public class GiamGiaSp {

    private String id;
    private String maGiamGiaSP;
    private BigDecimal soTienCL;
    private int trangThai;
    private String idSP;
    private String idGiamGia;
    private String maVoucher; //Các mã voucher online - hieu
    
    public GiamGiaSp() {
    }

    public GiamGiaSp(String id, String maGiamGiaSP, BigDecimal soTienCL, int trangThai, String idSP, String idGiamGia, String maVoucher) {
        this.id = id;
        this.maGiamGiaSP = maGiamGiaSP;
        this.soTienCL = soTienCL;
        this.trangThai = trangThai;
        this.idSP = idSP;
        this.idGiamGia = idGiamGia;
        this.maVoucher = maVoucher;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaGiamGiaSP() {
        return maGiamGiaSP;
    }

    public void setMaGiamGiaSP(String maGiamGiaSP) {
        this.maGiamGiaSP = maGiamGiaSP;
    }

    public BigDecimal getSoTienCL() {
        return soTienCL;
    }

    public void setSoTienCL(BigDecimal soTienCL) {
        this.soTienCL = soTienCL;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getIdSP() {
        return idSP;
    }

    public void setIdSP(String idSP) {
        this.idSP = idSP;
    }

    public String getIdGiamGia() {
        return idGiamGia;
    }

    public void setIdGiamGia(String idGiamGia) {
        this.idGiamGia = idGiamGia;
    }

    public String getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

   

    public String TrangThai2() {
        if (trangThai == 0) {
            return "Online";
        } else {
            return "Offline";
        }
//        } else if (trangThai == 2) {
//            return "Sắp tới";
//        } else {
//            return "Hết Hạn";
//        }
    }
    }

