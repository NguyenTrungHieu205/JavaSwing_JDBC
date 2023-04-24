/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class DungLuong {

    private String id;
    private String maDL;
    private String tenDL;
    private int trangThai;

    public DungLuong() {
    }

    
    public DungLuong(String id, String maDL, String tenDL, int trangThai) {
        this.id = id;
        this.maDL = maDL;
        this.tenDL = tenDL;
        this.trangThai = trangThai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaDL() {
        return maDL;
    }

    public void setMaDL(String maDL) {
        this.maDL = maDL;
    }

    public String getTenDL() {
        return tenDL;
    }

    public void setTenDL(String tenDL) {
        this.tenDL = tenDL;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    
    public Object[] toDataRow() {
        return new Object[]{ maDL, tenDL};
    }
    
    
}
