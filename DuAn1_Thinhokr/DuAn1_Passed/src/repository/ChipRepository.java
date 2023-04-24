/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import ultility.DBContext;
import viewModel.ChipViewModel;

/**
 *
 * @author ADMIN
 */
public class ChipRepository {

    public ArrayList<ChipViewModel> getAll() {
        try {
            ArrayList<ChipViewModel> listChip = new ArrayList<>();
            String sql = "SELECT MaChip, TenChip, TrangThai  FROM CHIP WHERE TrangThai = 0 ";
            Connection conn = DBContext.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ChipViewModel chip = new ChipViewModel();
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

    public boolean create(ChipViewModel chip) throws SQLException {
        String sql = "INSERT INTO CHIP (MaChip, TenChip, TrangThai) VALUES (?,?,?)";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
           stm.setString(1, chip.getMa());
           stm.setString(2, chip.getTen());
           stm.setInt(3, chip.getTrangThai());
           stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
     public boolean update(ChipViewModel chip) throws SQLException {
        String sql = "UPDATE CHIP SET TenChip = ?, TrangThai = ? WHERE MaChip =? ";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
           stm.setString(1, chip.getTen());
           stm.setInt(2, chip.getTrangThai());
           stm.setString(3, chip.getMa());
           
           stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
     public ArrayList<ChipViewModel> getAllXoa() throws SQLException {
        ArrayList<ChipViewModel> listChip = new ArrayList<>();
        String sql = "SELECT MaChip, TenChip FROM CHIP WHERE TrangThai = '4' ";
        Connection conn = DBContext.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            ChipViewModel chip = new ChipViewModel();
            chip.setMa(rs.getString(1));
            chip.setTen(rs.getString(2));
            listChip.add(chip);
        }
        return listChip;
    }
     public boolean updateTT(ChipViewModel chip) throws SQLException {
        String sql = "UPDATE CHIP SET TrangThai = '4' WHERE MaChip =? ";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
           //stm.setString(1, chip.getTen());
           stm.setString(1, chip.getMa());
           stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
     public boolean updateTT3(ChipViewModel chip) throws SQLException {
        String sql = "UPDATE CHIP SET TrangThai = '0' WHERE MaChip =? ";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
           stm.setString(1, chip.getMa());
           stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
}
