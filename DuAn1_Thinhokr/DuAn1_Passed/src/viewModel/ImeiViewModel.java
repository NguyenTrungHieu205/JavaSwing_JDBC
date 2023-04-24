/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

/**
 *
 * @author Admin
 */
public class ImeiViewModel {

    private String maImei;
    private Integer trangThai;
    private String id_Sp;
    private String maSanPham;

    public ImeiViewModel() {
    }

    public ImeiViewModel(String maImei, Integer trangThai, String id_Sp, String maSanPham) {
        this.maImei = maImei;
        this.trangThai = trangThai;
        this.id_Sp = id_Sp;
        this.maSanPham = maSanPham;
    }

    public String getMaImei() {
        return maImei;
    }

    public void setMaImei(String maImei) {
        this.maImei = maImei;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public String getId_Sp() {
        return id_Sp;
    }

    public void setId_Sp(String id_Sp) {
        this.id_Sp = id_Sp;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String TrangThai2() {
        if (trangThai == 0) {
            return "Online";
        } else if (trangThai == 1) {
            return "Offline";
        } else if (trangThai == 2) {
            return "Đang chờ hàng về";
        } else if(trangThai == 3){
            return "Ngừng bán";
        } else{
            return "Đang trong giỏ hàng";
        }
    }
}
