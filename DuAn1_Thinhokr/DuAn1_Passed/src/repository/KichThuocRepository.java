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
import viewModel.KichThuocViewModel;


public class KichThuocRepository {
    
    public ArrayList<KichThuocViewModel> getAll() {
        try {
            ArrayList<KichThuocViewModel> listChip = new ArrayList<>();
            String sql = "SELECT MaKT, TenKT, TrangThai FROM KichThuoc WHERE TrangThai = 0 ";
            Connection conn = DBContext.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                KichThuocViewModel chip = new KichThuocViewModel();
                chip.setMa(rs.getString(1));
                chip.setTen(rs.getString(2));
                chip.setTrangThai(rs.getInt(3));
                listChip.add(chip);
            }
            return listChip;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean create(KichThuocViewModel dl) throws SQLException {
        String sql = "INSERT INTO KichThuoc (MaKT, TenKT, TrangThai) VALUES (?,?,?)";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
           stm.setString(1, dl.getMa());
           stm.setString(2, dl.getTen());
           stm.setInt(3, dl.getTrangThai());
           stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
     public boolean update(KichThuocViewModel dl) throws SQLException {
        String sql = "UPDATE KichThuoc SET TenKT = ?, TrangThai = ? WHERE MaKT =? ";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
           stm.setString(1, dl.getTen());
           stm.setInt(2, dl.getTrangThai());
           stm.setString(3, dl.getMa());
           stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
     public ArrayList<KichThuocViewModel> getAllXoa() throws SQLException {
        ArrayList<KichThuocViewModel> listdl = new ArrayList<>();
        String sql = "SELECT MaKT, TenKT FROM KichThuoc WHERE TrangThai = '4' ";
        Connection conn = DBContext.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            KichThuocViewModel dl = new KichThuocViewModel();
            dl.setMa(rs.getString(1));
            dl.setTen(rs.getString(2));
            listdl.add(dl);
        }
        return listdl;
    }
     public boolean updateTT(KichThuocViewModel dl) throws SQLException {
        String sql = "UPDATE KichThuoc SET TrangThai = '4' WHERE MaKT =? ";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
           //stm.setString(1, chip.getTen());
           stm.setString(1, dl.getMa());
           stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
     public boolean updateTT3(KichThuocViewModel dl) throws SQLException {
        String sql = "UPDATE KichThuoc SET TrangThai = '0' WHERE MaKT =? ";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
           stm.setString(1, dl.getMa());
           stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
}
