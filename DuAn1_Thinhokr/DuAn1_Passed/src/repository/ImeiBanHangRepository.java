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
import ultility.DBContext;
import viewModel.ImeiBanHang;
import viewModel.ImeiViewModel;

/**
 *
 * @author nguyenhongphong
 */
public class ImeiBanHangRepository {

    // lấy ra list mã imei và tên sản phẩm
    public ArrayList<ImeiBanHang> getAll(String maSp) throws SQLException {
        ArrayList<ImeiBanHang> list = new ArrayList<>();
        String sql = "select MaImei from IMEI JOIN SANPHAM on IMEI.Id_Sp = SANPHAM.Id where masp = ? and imei.trangthai = 0";
        Connection connection = DBContext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, maSp);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ImeiBanHang imei = new ImeiBanHang();
            imei.setMaImei(rs.getString(1));
            list.add(imei);

        }
        return list;
    }

    public ArrayList<ImeiBanHang> getAll1(String maSp) throws SQLException {
        ArrayList<ImeiBanHang> list = new ArrayList<>();
        String sql = "select MaImei from IMEI JOIN SANPHAM on IMEI.Id_Sp = SANPHAM.Id where masp = ? and imei.trangthai = 1";
        Connection connection = DBContext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, maSp);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ImeiBanHang imei = new ImeiBanHang();
            imei.setMaImei(rs.getString(1));
            list.add(imei);

        }
        return list;
    }

    public ArrayList<ImeiBanHang> getAll5(String maSp) throws SQLException {
        ArrayList<ImeiBanHang> list = new ArrayList<>();
        String sql = "select MaImei from IMEI JOIN SANPHAM on IMEI.Id_Sp = SANPHAM.Id where masp = ? and imei.trangthai = 5";
        Connection connection = DBContext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, maSp);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ImeiBanHang imei = new ImeiBanHang();
            imei.setMaImei(rs.getString(1));
            list.add(imei);

        }
        return list;
    }

    //cập nhật trạng thái imei và thêm dữ liệu vào bảng imeiKT khi thanh toán thành công
    public Integer updateImeiTT(String maSp) throws SQLException {
        Connection con = DBContext.getConnection();
        String sql = "update IMEI set TrangThai = 1 where Id_Sp = (select id from SANPHAM where MaSP = ?) and imei.TrangThai = 5";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, maSp);

        int affectedRows = ps.executeUpdate();

        ps.close();
        con.close();

        return affectedRows;
    }

    public Integer insertImeiKT() throws SQLException {
        Connection con = DBContext.getConnection();
        String sql = "insert into IMEI_KT(trangthai) values(0)";
        PreparedStatement ps = con.prepareStatement(sql);

        int affectedRows = ps.executeUpdate();

        ps.close();
        con.close();

        return affectedRows;
    }
    
    public Integer updateImeiKT(String maImei) throws SQLException {
        Connection con = DBContext.getConnection();
        String sql = "update IMEI_KT set MaImei = ? where Id = (SELECT TOP 1 id asLastID FROM IMEI_KT order by id DESC)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, maImei);
        
        int affectedRows = ps.executeUpdate();

        ps.close();
        con.close();

        return affectedRows;
    }
    
    public Integer deleteImeiKT(String maImei) throws SQLException {
        Connection con = DBContext.getConnection();
        String sql = "delete from IMEI_KT where MaImei = ?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, maImei);
        
        int affectedRows = ps.executeUpdate();

        ps.close();
        con.close();

        return affectedRows;
    }
    
    public Integer deleteAllImeiKT() throws SQLException {
        Connection con = DBContext.getConnection();
        String sql = "delete from IMEI_KT";
        PreparedStatement ps = con.prepareStatement(sql);
        
        int affectedRows = ps.executeUpdate();

        ps.close();
        con.close();

        return affectedRows;
    }

    public ArrayList<ImeiViewModel> getAllImeiKT(String maHD, String maSP) throws SQLException {
        ArrayList<ImeiViewModel> listimei = new ArrayList<>();
        String sql = "select MaImei from IMEI_KT join HDCT on Imei_KT.id_hdct = HDCT.Id join HoaDon on HDCT.Id_HoaDon = HoaDon.Id where MaHD = ? and Id_SanPham = (select id from SANPHAM where MaSP = ?) and IMEI_KT.TrangThai = 0";
        Connection conn = DBContext.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, maHD);
        stm.setString(2, maSP);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            ImeiViewModel ivm = new ImeiViewModel();
            ivm.setMaImei(rs.getString(1));
            listimei.add(ivm);
        }
        return listimei;
    }
    
    public ArrayList<ImeiViewModel> getAllImeiKT1(String maHD) throws SQLException {
        ArrayList<ImeiViewModel> listimei = new ArrayList<>();
        String sql = "select MaImei from IMEI_KT join HDCT on HDCT.Id = IMEI_KT.ID_HDCT join HoaDon on HDCT.Id_HoaDon = HoaDon.Id where MaHD = ?";
        Connection conn = DBContext.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, maHD);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            ImeiViewModel ivm = new ImeiViewModel();
            ivm.setMaImei(rs.getString(1));
            listimei.add(ivm);
        }
        return listimei;
    }
    
    public ArrayList<ImeiViewModel> CheckImeiOffline(String trangThai) throws SQLException{
        ArrayList<ImeiViewModel> listimei = new ArrayList<>();
        String sql = "";
        Connection conn = DBContext.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        
        return listimei;
        
    }
}
