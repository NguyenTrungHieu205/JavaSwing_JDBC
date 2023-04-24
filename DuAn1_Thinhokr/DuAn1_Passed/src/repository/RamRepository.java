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
import viewModel.KichThuocViewModel;
import viewModel.RamViewModel;

/**
 *
 * @author Admin
 */
public class RamRepository {
    
    public ArrayList<RamViewModel> getAll(){
        try {
            ArrayList<RamViewModel> listChip = new ArrayList<>();
            String sql = "SELECT MaRam, TenRAM FROM ram WHERE TrangThai = '0'";
            Connection conn = DBContext.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                RamViewModel chip = new RamViewModel();
                chip.setMa(rs.getString(1));
                chip.setTen(rs.getString(2));
                listChip.add(chip);
            }
            return listChip;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean create(RamViewModel dl) throws SQLException {
        String sql = "INSERT INTO ram (MaRam, TenRAM, TrangThai) VALUES (?,?,?)";
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
     public boolean update(RamViewModel dl) throws SQLException {
        String sql = "UPDATE ram SET TenRAM = ?, trangthai = ? WHERE MaRam =? ";
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
     public ArrayList<RamViewModel> getAllXoa() throws SQLException {
        ArrayList<RamViewModel> listdl = new ArrayList<>();
        String sql = "SELECT MaRam, TenRAM FROM ram WHERE TrangThai = '1' ";
        Connection conn = DBContext.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            RamViewModel dl = new RamViewModel();
            dl.setMa(rs.getString(1));
            dl.setTen(rs.getString(2));
            listdl.add(dl);
        }
        return listdl;
    }
     public boolean updateTT(RamViewModel dl) throws SQLException {
        String sql = "UPDATE ram SET TrangThai = '1' WHERE MaRam =? ";
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
     public boolean updateTT3(RamViewModel dl) throws SQLException {
        String sql = "UPDATE ram SET TrangThai = '0' WHERE MaRam =? ";
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
