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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ultility.DBContext;
import viewModel.DungLuongViewModel;
import viewModel.ManHinhViewModel;

/**
 *
 * @author Admin
 */
public class ManHinhRepository {
    public ArrayList<ManHinhViewModel> getAll() {
        try {
            ArrayList<ManHinhViewModel> listMH = new ArrayList<>();
            String sql = "SELECT MaMH, TenMH, TrangThai FROM ManHinh WHERE TrangThai = 0 ";
            Connection conn = DBContext.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ManHinhViewModel chip = new ManHinhViewModel();
                chip.setMa(rs.getString(1));
                chip.setTen(rs.getString(2));
                chip.setTrangThai(rs.getInt(3));
                listMH.add(chip);
            }
            return listMH;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean create(ManHinhViewModel mh) throws SQLException {
        String sql = "INSERT INTO ManHinh (MaMH, TenMH, TrangThai) VALUES (?,?,?)";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
           stm.setString(1, mh.getMa());
           stm.setString(2, mh.getTen());
           stm.setInt(3, mh.getTrangThai());
           stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
     public boolean update(ManHinhViewModel mh) throws SQLException {
        String sql = "UPDATE ManHinh SET TenMH = ?, TrangThai = ? WHERE MaMH =? ";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
           stm.setString(1, mh.getTen());
           stm.setInt(2, mh.getTrangThai());
           stm.setString(3, mh.getMa());
           stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
     public ArrayList<ManHinhViewModel> getAllXoa() throws SQLException {
        ArrayList<ManHinhViewModel> listdl = new ArrayList<>();
        String sql = "SELECT MaMH, TenMH FROM ManHinh WHERE TrangThai = '4' ";
        Connection conn = DBContext.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            ManHinhViewModel mh = new ManHinhViewModel();
            mh.setMa(rs.getString(1));
            mh.setTen(rs.getString(2));
            listdl.add(mh);
        }
        return listdl;
    }
     public boolean updateTT(ManHinhViewModel mh )throws SQLException {
        String sql = "UPDATE ManHinh SET TrangThai = '4' WHERE MaMH =? ";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
           //stm.setString(1, chip.getTen());
           stm.setString(1, mh.getMa());
           stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
     public boolean updateTT3(ManHinhViewModel mh) throws SQLException {
        String sql = "UPDATE ManHinh SET TrangThai = '0' WHERE MaMH =? ";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
           stm.setString(1, mh.getMa());
           stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
}
