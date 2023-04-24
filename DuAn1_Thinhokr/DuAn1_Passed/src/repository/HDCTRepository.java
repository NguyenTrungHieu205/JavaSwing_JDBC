/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.HDChiTiet;
import ultility.DBContext;
import viewModel.HoaDonChiTiet;

/**
 *
 * @author phamtuyetnga
 */
public class HDCTRepository {
     private DBContext dBConnection;

    public Boolean saveHoaDon( HDChiTiet hoaDonChiTiet) {

        int checkInsert = 0;

        String sql = "INSERT INTO HDCT(Id_HoaDon, Id_SanPham, SoLuong, DonGia) VALUES(?,?)";
        try (Connection con = dBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, hoaDonChiTiet.getSoLuong());
            ps.setObject(2, hoaDonChiTiet.getDonGia());
            checkInsert = ps.executeUpdate();
            return checkInsert > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
