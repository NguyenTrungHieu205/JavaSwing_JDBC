/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.GiamGia;

import ultility.DBContext;
import viewModel.GiamGiaViewModel;
import viewModel.ViewSanPham;

/**
 *
 * @author Admin
 */
public class GiamGiaRepository {

  public ArrayList<GiamGiaViewModel> getAll() {
        try {
            ArrayList<GiamGiaViewModel> listVoucher = new ArrayList<>();
            Connection con = DBContext.getConnection();
            String sql = "SELECT MaGiamGia, TenGiamGia, LoaiGiamGia, NgayBatDau,NgayKetThuc, MucGiamGia ,TrangThai FROM  GiamGia";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GiamGiaViewModel vc = new GiamGiaViewModel();
                vc.setMaGiamGia(rs.getString(1));
                vc.setTenGiamGia(rs.getString(2));
                vc.setLoaiGiamGia(rs.getString(3));
                vc.setNgayBatDau(rs.getDate(4));
                vc.setNgayKetThuc(rs.getDate(5));
                vc.setMucGiamGia(rs.getString(6));
                vc.setTrangThai(rs.getInt(7));

                listVoucher.add(vc);
            }
            return listVoucher;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
  
  public Integer them(GiamGia vc) {
        try {
            Connection con = DBContext.getConnection();
            String sql = "INSERT INTO GiamGia (MaGiamGia, TenGiamGia,NgayBatDau,NgayKetThuc,MucGiamGia, TrangThai) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, vc.getMaGiamGia());
            ps.setString(2, vc.getTenGiamGia());
            ps.setObject(3, vc.getNgayBatDau());
            ps.setObject(4, vc.getNgayKetThuc());
            ps.setString(5, vc.getMucGiamGia());
            ps.setInt(6, vc.getTrangThai());

            Integer ketQua = ps.executeUpdate();
            return ketQua;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Integer sua(GiamGia vc) {

        String sql = "UPDATE GiamGia SET TenGiamGia =?,NgayBatDau =? ,NgayKetThuc=?,MucGiamGia=?, TrangThai =? WHERE MaGiamGia = ?";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setString(1, vc.getTenGiamGia());
            ps.setObject(2, vc.getNgayBatDau());
            ps.setObject(3, vc.getNgayKetThuc());
            ps.setString(4, vc.getMucGiamGia());
            ps.setInt(5, vc.getTrangThai());
            ps.setString(6, vc.getMaGiamGia());

            Integer ketQua = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public Integer updateTrangThai(String ma) {

        try {
            Connection con = DBContext.getConnection();
            String sql = "UPDATE GiamGia  SET TrangThai =3 where MaGiamGia=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            Integer ketQua = ps.executeUpdate();
            return ketQua;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public ArrayList<GiamGia> timKiemVC(String giamGia) throws SQLException {
        ArrayList<GiamGia> list = new ArrayList<>();
        String sql = "select MaGiamGia, TenGiamGia, LoaiGiamGia, NgayBatDau, NgayKetThuc, MucGiamGia, TrangThai from GiamGia where MaGiamGia  =? or NgayBatDau =?";
        Connection connection = DBContext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, giamGia);
        ps.setString(2, giamGia);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            GiamGia vc = new GiamGia();
            vc.setMaGiamGia(giamGia);
            vc.setTenGiamGia(rs.getString(2));
            vc.setLoaiGiamGia(rs.getString(3));
            vc.setNgayBatDau(rs.getDate(4));
            vc.setNgayKetThuc(rs.getDate(5));

            list.add(vc);
        }
        return list;
    }

    public ArrayList<GiamGiaViewModel> layTTGiamGia() {

        try {
            ArrayList<GiamGiaViewModel> listVoucher = new ArrayList<>();
            Connection con = DBContext.getConnection();
            String sql = "SELECT MaGiamGia, TenGiamGia,NgayBatDau,NgayKetThuc, MucGiamGia ,TrangThai FROM  GiamGia";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GiamGiaViewModel vc = new GiamGiaViewModel();
                vc.setMaGiamGia(rs.getString(1));
                vc.setTenGiamGia(rs.getString(2));
                vc.setNgayBatDau(rs.getDate(3));
                vc.setNgayKetThuc(rs.getDate(4));
                vc.setMucGiamGia(rs.getString(5));
                vc.setTrangThai(rs.getInt(6));

                listVoucher.add(vc);
            }
            return listVoucher;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
