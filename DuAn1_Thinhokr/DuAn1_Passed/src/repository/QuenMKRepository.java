/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import model.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import ultility.DBContext;
/**
 *
 * @author phamtuyetnga
 */
public class QuenMKRepository {
    public ArrayList<NhanVien> checkTaiKhoan() throws SQLException {
        ArrayList<NhanVien> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "select Email, MaNV from NhanVien";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            NhanVien nv = new NhanVien();
            nv.setEmail(rs.getString("Email"));
            nv.setMaNv(rs.getString("MaNV"));
            list.add(nv);
        }
         
        return list;
    }

    public Integer resetMatKhau(Integer matKhauMoi, String maNv) throws SQLException{
        Connection con = DBContext.getConnection();
        String sql = "update NhanVien set MatKhau = ? where MaNV = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        
        ps.setInt(1, matKhauMoi);
        ps.setString(2, maNv);
        
        int affectedRows = ps.executeUpdate();
        
        ps.close();
        con.close();
        
        return affectedRows;
    }

}
