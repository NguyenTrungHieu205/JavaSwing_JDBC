/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.math.BigDecimal;
import model.HDChiTiet;
import ultility.DBContext;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import viewModel.HoaDonChiTietBanHangViewModel;

/**
 *
 * @author phamtuyetnga
 */
public class HoaDonChiTietBanHangRepository {

    public ArrayList<HoaDonChiTietBanHangViewModel> listHDCT(String idHD) throws SQLException { // lấy ra thông tin bảng giỏ hàng
        ArrayList<HoaDonChiTietBanHangViewModel> list = new ArrayList<>();
        String sql = "SELECT MaSP, TenSP, HDCT.SoLuong, HDCT.DonGia, GiamGia.MucGiamGia , (HDCT.DonGia*HDCT.SoLuong-GiamGia.MucGiamGia*HDCT.SoLuong) FROM HDCT JOIN SANPHAM on HDCT.Id_SanPham = SANPHAM.Id JOIN HoaDon ON HDCT.Id_HoaDon = HoaDon.Id join GiamGiaSP on SANPHAM.Id=GiamGiaSP.ID_SanPham join GiamGia on GiamGia.Id=GiamGiaSP.ID_GiamGia WHERE HDCT.Id_HoaDon = (Select id from HoaDon where MaHD = ?)";
        Connection connection = DBContext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, idHD);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            HoaDonChiTietBanHangViewModel hdct = new HoaDonChiTietBanHangViewModel();
            hdct.setMaSanPham(rs.getString(1));
            hdct.setTenSanPham(rs.getString(2));
            hdct.setSoLuong(rs.getInt(3));
            hdct.setDonGia(rs.getBigDecimal(4));// sửa sql join thêm bảng giamgiasp
            hdct.setMucGiamGia(rs.getString(5));
            hdct.setTongTien(rs.getBigDecimal(6));

            list.add(hdct);
        }
        return list;

    }

    public boolean them(String hd) throws SQLException { // tạo hoá dơn chi tiết
        String sql = "INSERT INTO HDCT (Id_HoaDon) VALUES((SELECT Id From HoaDon WHERE MaHD =?))";
        Connection connection = DBContext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, hd);

        return ps.executeUpdate() > 0;
    }

    public boolean themVaoHDCT(HoaDonChiTietBanHangViewModel hd, String maHD) throws SQLException {// thêm sản phẩm vào giỏ hàng
        String sql = "SET IDENTITY_INSERT hdct ON;INSERT INTO HDCT (SoLuong, DonGia, Id_SanPham, Id_HoaDon, Id_ImeiKT) VALUES(?,?,(SELECT Id FROM SANPHAM WHERE MaSP = ?),(SELECT Id From HoaDon WHERE MaHD =?), (SELECT TOP 1 id asLastID FROM IMEI_KT order by id DESC))";
        Connection connection = DBContext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, hd.getSoLuong());
        ps.setBigDecimal(2, hd.getDonGia());
        ps.setString(3, hd.getMaSanPham());
        ps.setString(4, maHD);

        return ps.executeUpdate() > 0;
    }

    public boolean kiemTra(HoaDonChiTietBanHangViewModel hd, String maHD) throws SQLException { 
        // kiểm tra sản phẩm đã có trong giỏ hàng hay chưa
        // nếu có rồi gọi tới hàm suaSuoLuong() - còn chưa có thì tạo đối tuọng mới gọi tới themVaoHDCT()
        String sql = "SELECT SoLuong, DonGia, Id_SanPham, Id_HoaDon FROM HDCT WHERE Id_SanPham = (SELECT Id FROM SANPHAM WHERE MaSP = ?) and Id_HoaDon = (select Id from HoaDon where MaHD = ?)";
        Connection connection = DBContext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, hd.getMaSanPham());
        ps.setString(2, maHD);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return true;
        }
        return false;

    }

    public boolean suaSoLuong(HoaDonChiTietBanHangViewModel hd, String idHD) throws SQLException { // update số lượng sản phẩm trong giỏ hàng được cộng thêm
        String sql = "UPDATE HDCT set SoLuong = (SoLuong + ?) where  Id_SanPham = (SELECT Id FROM SANPHAM WHERE MaSP = ?) and Id_HoaDon = (select Id from HoaDon where MaHD = ?)";
        Connection connection = DBContext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, hd.getSoLuong());
        ps.setString(2, hd.getMaSanPham());
        ps.setString(3, idHD);

        return ps.executeUpdate() > 0;
    }

    public boolean xoaSanPham(String maSP, String maHD) throws SQLException {// xoá sản phẩm được chọn trong giỏ hàng
        String sql = "delete From HDCT WHERE Id_SanPham = (SELECT Id FROM SANPHAM WHERE MaSP = ?)  and  Id_HoaDon = (SELECT Id From HoaDon WHERE MaHD = ?)";
        Connection connection = DBContext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, maSP);
        ps.setString(2, maHD);

        return ps.executeUpdate() > 0;
    }

    public boolean xoaAllSanPham(String maHD) throws SQLException {// xoá tất cả sản phẩm trong giỏ hàng
        String sql = "delete From HDCT WHERE Id_HoaDon = (SELECT Id From HoaDon WHERE MaHD = ?)";
        Connection connection = DBContext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, maHD);

        return ps.executeUpdate() > 0;
    }

    public boolean updateTruSoLuongSanPham(String maSP, String maHD, int soLuongSanPhamTru) throws SQLException { // update số lượng sản phẩm bị xoá đi trong giỏ hàng
        String sql = "UPDATE HDCT set SoLuong = (SoLuong - ?) where  Id_SanPham = (SELECT Id FROM SANPHAM WHERE MaSP = ?) and Id_HoaDon = (select Id from HoaDon where MaHD = ?)";
        Connection connection = DBContext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, soLuongSanPhamTru);
        ps.setString(2, maSP);
        ps.setString(3, maHD);

        return ps.executeUpdate() > 0;
    }
    
    public boolean capNhatSoLuong(String maSp, String idHD, int soLuong) throws SQLException { // update số lượng sản phẩm trong giỏ hàng được cộng thêm
        String sql = "UPDATE HDCT set SoLuong = (SoLuong + ?) where  Id_SanPham = (SELECT Id FROM SANPHAM WHERE MaSP = ?) and Id_HoaDon = (select Id from HoaDon where MaHD = ?)";
        Connection connection = DBContext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, soLuong);
        ps.setString(2, maSp);
        ps.setString(3, idHD);

        return ps.executeUpdate() > 0;
    }

    public Integer deleteHDCT(String maImei) throws SQLException {
        Connection con = DBContext.getConnection();
        String sql = "delete from HDCT where Id_ImeiKT = (select id from IMEI_KT where MaImei = ?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, maImei);
        
        int affectedRows = ps.executeUpdate();

        ps.close();
        con.close();

        return affectedRows;
    }
}
