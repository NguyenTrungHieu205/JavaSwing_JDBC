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
import viewModel.ManHinhViewModel;
import viewModel.PinViewModel;

/**
 *
 * @author ADMIN
 */
public class PinRepository {
    public ArrayList<PinViewModel> getAll(){
        try {
            ArrayList<PinViewModel> listMH = new ArrayList<>();
            String sql = "SELECT MaPin, TenPin, TrangThai FROM Pin WHERE TrangThai = 0 ";
            Connection conn = DBContext.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                PinViewModel pin = new PinViewModel();
                pin.setMa(rs.getString(1));
                pin.setTen(rs.getString(2));
                pin.setTrangThai(rs.getInt(3));
                listMH.add(pin);
            }
            return listMH;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean create(PinViewModel p) throws SQLException {
        String sql = "INSERT INTO Pin (MaPin, TenPin, TrangThai) VALUES (?,?,?)";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
           stm.setString(1, p.getMa());
           stm.setString(2, p.getTen());
           stm.setInt(3, p.getTrangThai());
           stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
     public boolean update(PinViewModel p) throws SQLException {
        String sql = "UPDATE Pin SET TenPin = ? , TrangThai = ? WHERE MaPin =? ";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
           stm.setString(1, p.getTen());
           stm.setInt(2, p.getTrangThai());
           stm.setString(3, p.getMa());
           stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
     public ArrayList<PinViewModel> getAllXoa() throws SQLException {
        ArrayList<PinViewModel> listdl = new ArrayList<>();
        String sql = "SELECT MaPin, TenPin FROM Pin WHERE TrangThai = '4' ";
        Connection conn = DBContext.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            PinViewModel p = new PinViewModel();
            p.setMa(rs.getString(1));
            p.setTen(rs.getString(2));
            listdl.add(p);
        }
        return listdl;
    }
     public boolean updateTT(PinViewModel p )throws SQLException {
        String sql = "UPDATE Pin SET TrangThai = '4' WHERE MaPin =? ";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
           //stm.setString(1, chip.getTen());
           stm.setString(1, p.getMa());
           stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
     public boolean updateTT3(PinViewModel p) throws SQLException {
        String sql = "UPDATE Pin SET TrangThai = '0' WHERE MaPin =? ";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
           stm.setString(1, p.getMa());
           stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
}
