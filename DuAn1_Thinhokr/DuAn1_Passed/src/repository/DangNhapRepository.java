/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;


import model.DangNhap;
import ultility.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.*;
        
/**
 *
 * @author ADMIN
 */
public class DangNhapRepository {
        
    public ArrayList<DangNhap> taiKhoan(){
        try {
            ArrayList<DangNhap> list = new ArrayList<>();
            Connection con = DBContext.getConnection();
            String sql = "select MaNV, MatKhau from NhanVien";
            PreparedStatement pre = con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                DangNhap tk = new DangNhap();
                tk.setTaiKhoan(rs.getString(1));
                tk.setMatKhau(rs.getString(2));
                list.add(tk);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
             return null;
        }
    }
    public String checkTK(String maCV) throws SQLException {

        ArrayList<DangNhap> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "SELECT TenCV  FROM ChucVu JOIN NhanVien ON  NhanVien.Id_ChucVu = ChucVu.Id WHERE MaNV = ?";
        PreparedStatement pre = con.prepareStatement(sql);

        pre.setString(1, maCV);

        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            return rs.getString(1);
        }

        return null;
    }
}
