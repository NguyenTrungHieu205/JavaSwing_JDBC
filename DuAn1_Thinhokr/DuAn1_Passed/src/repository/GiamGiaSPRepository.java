/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import model.GiamGiaSp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.GiamGia;
import ultility.DBContext;
import viewModel.GiamGiaViewModel;
import viewModel.ViewSanPham;

/**
 *
 * @author Admin
 */
public class GiamGiaSPRepository {

    public ArrayList<GiamGiaSp> getAll() {
        String sql = "SELECT MaGiamGiaSP, SoTienCL, TrangThai, ID_SanPham, ID_GiamGia FROM  GiamGiaSP";
        try {
            Connection con = DBContext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<GiamGiaSp> listvcsp = new ArrayList<>();
            while (rs.next()) {
                GiamGiaSp vc = new GiamGiaSp();
                vc.setMaGiamGiaSP(rs.getString(1));
                vc.setSoTienCL(rs.getBigDecimal(2));
                vc.setTrangThai(rs.getInt(3));
                vc.setIdSP(rs.getString(4));
                vc.setIdGiamGia(rs.getString(5));

                listvcsp.add(vc);
            }
            return listvcsp;

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean addVCSP(String maSP, GiamGiaSp vc) throws SQLException {
        String sql = "DELETE FROM GiamGiaSP where ID_SanPham = (SELECT Id FROM SANPHAM WHERE MaSP = ? ); insert into GiamGiaSP(SoTienCL,TrangThai,ID_SanPham,ID_GiamGia) values((select (select GiaBan from SANPHAM where MaSP = ?) - (SELECT CONVERT(money, (select MucGiamGia from GiamGia where MaGiamGia = ?)))) ,?, (select Id from SanPham where MaSP = ?),(select Id from GiamGia where MaGiamGia = ?))";
        Connection con = DBContext.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maSP);
        ps.setString(2, vc.getIdSP());
        ps.setString(3, vc.getIdGiamGia());
        ps.setInt(4, vc.getTrangThai());
        ps.setString(5, vc.getIdSP());
        ps.setString(6, vc.getIdGiamGia());

        return ps.executeUpdate() > 0;

    }

//    public Integer xoaVC(GiamGiaSp vc) {
//
//        String sql = " UPDATE  GiamGiaSP SET  TrangThai = '4'  where  MaGiamGiaSP = ? ";
//        try ( Connection conn = DBContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql);) {
//            ps.setString(1, vc.getMaGiamGiaSP());
//
//            Integer ketQua = ps.executeUpdate();
//            return ketQua;
//        } catch (Exception e) {
//            e.printStackTrace();
//            
//        }
//        return null;
//    }
    public boolean suaCH(GiamGiaSp vc) throws SQLException {
        int check = 0;
        String query = " UPDATE GiamGiaSP SET  SoTienCL =?, TrangThai =?, ID_SanPham = (select Id from SanPham where MaSP = ?), ID_GiamGia =(select Id from GiamGia where MaGiamGia = ?) where MaGiamGiaSP = ? ";

        try ( Connection connect = DBContext.getConnection();  PreparedStatement ps = connect.prepareStatement(query)) {
            ps.setBigDecimal(1, vc.getSoTienCL());
            ps.setInt(2, vc.getTrangThai());
            ps.setString(3, vc.getIdSP());
            ps.setString(4, vc.getIdGiamGia());
            ps.setString(5, vc.getMaGiamGiaSP());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    // lấy giá bán trong sản phẩm - hieu
    public ArrayList<ViewSanPham> getGiaBanVC(String ma) {
        try {
            ArrayList<ViewSanPham> listsp = new ArrayList<>();
            String sql = "select GiaBan from SANPHAM where MaSP = ?";
            Connection conn = DBContext.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, ma);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ViewSanPham sp = new ViewSanPham();
                sp.setGiaSp(rs.getBigDecimal(1));
                listsp.add(sp);
            }
            return listsp;
        } catch (SQLException ex) {
            return null;
        }

    }

    //lấy mức giảm giá voucher - hieu
    public ArrayList<GiamGiaViewModel> getMucGiamGiaVC(String ma) {
        ArrayList<GiamGiaViewModel> listsp = new ArrayList<>();
        String sql = "select MucGiamGia from GiamGia where MaGiamGia = ?";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, ma);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                GiamGiaViewModel sp = new GiamGiaViewModel();
                sp.setMucGiamGia(rs.getString(1));
                listsp.add(sp);
            }
            return listsp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //lay voucher trang thai online - hieu
    public ArrayList<GiamGiaSp> getAllVoucherOnline() {
        String sql = "select MaGiamGia from GiamGia where TrangThai = '0'";
        try {
            Connection con = DBContext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<GiamGiaSp> listvcsp = new ArrayList<>();
            while (rs.next()) {
                GiamGiaSp vc = new GiamGiaSp();
                vc.setMaVoucher(rs.getString(1));
                listvcsp.add(vc);
            }
            return listvcsp;

        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }

    }
    //   UPDATE GiamGiaSP SET TrangThai = '3'where MaGiamGiaSP = '?' 
    //   UPDATE GiamGia SET MucGiamGia = '0' where MaGiamGia = '?' 

    //chuyển trạng thái hết hạn - hieu
    public boolean suaVoucherHetHan(GiamGiaSp vc) {
        int check = 0;
        String query = " UPDATE GiamGiaSP SET TrangThai = '4'where MaGiamGiaSP = ? ";

        try ( Connection connect = DBContext.getConnection();  PreparedStatement ps = connect.prepareStatement(query)) {
            ps.setString(1, vc.getMaGiamGiaSP());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    //xóa mức giảm gia về 0 - hieu
    public boolean suaMucGiamGiaVe0(GiamGiaSp vc) {
        int check = 0;
        String query = "UPDATE GiamGiaSP SET ID_GiamGia = (SELECT Id FROM GiamGia WHERE MaGiamGia = 'FREE') WHERE ID_SanPham = (SELECT Id FROM SANPHAM WHERE MaSP = ?)";

        try ( Connection connect = DBContext.getConnection();  PreparedStatement ps = connect.prepareStatement(query)) {
            ps.setString(1, vc.getIdSP());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

}
