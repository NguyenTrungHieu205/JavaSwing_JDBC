/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ultility.DBContext;
import viewModel.DungLuongViewModel;
import viewModel.MauSacViewModel;

/**
 *
 * @author ADMIN
 */
public class MauSacRepository {
     public ArrayList<MauSacViewModel> getAll() {
         try {
             ArrayList<MauSacViewModel> listMx = new ArrayList<>();
             String sql = "SELECT MaMau, TenMau, TrangThai FROM MauSac WHERE TrangThai = 0 ";
             Connection conn = DBContext.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql);
             ResultSet rs = stm.executeQuery();
             while (rs.next()) {
                 MauSacViewModel mx = new MauSacViewModel();
                 mx.setMa(rs.getString(1));
                 mx.setTen(rs.getString(2));
                 mx.setTrangThai(rs.getInt(3));
                 listMx.add(mx);
             }
             return listMx;
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
         return null;
    }

    public boolean create(MauSacViewModel mx) throws SQLException {
        String sql = "INSERT INTO MauSac (MaMau, TenMau, TrangThai) VALUES (?,?,?)";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
           stm.setString(1, mx.getMa());
           stm.setString(2, mx.getTen());
           stm.setInt(3, mx.getTrangThai());
           stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
     public boolean update(MauSacViewModel mx) throws SQLException {
        String sql = "UPDATE MauSac SET TenMau = ?, TrangThai = ? WHERE MaMau =? ";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
           stm.setString(1, mx.getTen());
           stm.setInt(2, mx.getTrangThai());
           stm.setString(3, mx.getMa());
           stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
     public ArrayList<MauSacViewModel> getAllXoa() throws SQLException {
        ArrayList<MauSacViewModel> listmx = new ArrayList<>();
        String sql = "SELECT MaMau, TenMau FROM MauSac WHERE TrangThai = '4' ";
        Connection conn = DBContext.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            MauSacViewModel mx = new MauSacViewModel();
            mx.setMa(rs.getString(1));
            mx.setTen(rs.getString(2));
            listmx.add(mx);
        }
        return listmx;
    }
     public boolean updateTT(MauSacViewModel mx) throws SQLException {
        String sql = "UPDATE MauSac SET TrangThai = '4' WHERE MaMau =? ";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
           stm.setString(1, mx.getMa());
           stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
     public boolean updateTT3(MauSacViewModel mx) throws SQLException {
        String sql = "UPDATE MauSac SET TrangThai = '0' WHERE MaMau =? ";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
           stm.setString(1, mx.getMa());
           stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
}
