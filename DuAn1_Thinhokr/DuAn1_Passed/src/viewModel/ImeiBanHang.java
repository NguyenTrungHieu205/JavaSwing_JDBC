/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

/**
 *
 * @author nguyenhongphong
 */
public class ImeiBanHang {

    private String maImei;
    private String tenSanPham;
    private String maSanPham;

    public ImeiBanHang() {
    }

    public ImeiBanHang(String maImei, String tenSanPham, String maSanPham) {
        this.maImei = maImei;
        this.tenSanPham = tenSanPham;
        this.maSanPham = maSanPham;
    }

    public String getMaImei() {
        return maImei;
    }

    public void setMaImei(String maImei) {
        this.maImei = maImei;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    
}
