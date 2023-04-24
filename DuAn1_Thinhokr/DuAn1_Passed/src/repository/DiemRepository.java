/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ultility.DBContext;

/**
 *
 * @author Dell
 */
public class DiemRepository {
    
    public Integer insertDiem() throws SQLException{
        Connection con = DBContext.getConnection();
        String sql = "insert into Diem(SoDiem, TrangThai) values (0,0)";
        PreparedStatement ps = con.prepareStatement(sql);
        
        int affectedRows = ps.executeUpdate();
        
        ps.close();
        con.close();
        
        return affectedRows;
    }
    
    public Integer updateSoDiem(Float soDiem, String maKH) throws SQLException{
        Connection con = DBContext.getConnection();
        String sql = "update Diem set SoDiem = ? where Id = (select Id_Diem from KhachHang where MaKH = ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        
        ps.setFloat(1, soDiem);
        ps.setString(2, maKH);
        
        int affectedRows = ps.executeUpdate();
        
        ps.close();
        con.close();
        
        return affectedRows;
    }
    
}
