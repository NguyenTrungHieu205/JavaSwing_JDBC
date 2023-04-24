/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

/**
 *
 * @author ADMIN
 */
public class ChipViewModel {
    private String ma;
    private String ten;
    private Integer trangThai;

    public ChipViewModel() {
    }

    public ChipViewModel(String ma, String ten, Integer trangThai) {
        this.ma = ma;
        this.ten = ten;
        this.trangThai = trangThai;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public String TrangThai2(){
        if(trangThai == 0){
            return "Online";
        }else if(trangThai == 1){
            return "Offline";
        }else if(trangThai == 2){
            return "Đang chờ hàng về";
        }else{
            return "Ngừng bán";
        }
    }
    
    
}
