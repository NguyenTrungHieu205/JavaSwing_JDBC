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
import model.SanPham;
import ultility.DBContext;
import ultility.JDBCHeper;
import viewModel.PhanLoaiHangViewModel;
import viewModel.SanPhamViewModel;
import viewModel.ViewSanPham;

/**
 *
 * @author phamtuyetnga
 */
public class SPRepository {

    public ArrayList<ViewSanPham> getAll() throws SQLException {
        ArrayList<ViewSanPham> listSp = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "select MaSP, TenSP, TenLH, TenRAM, TenPin, TenDL, TenMH, SoLuongTon, GiaBan, SANPHAM.TrangThai from SANPHAM join PhanLoaiHang on SANPHAM.Id_PhanLoai = PhanLoaiHang.Id join RAM on SANPHAM.Id_Ram = RAM.Id join Pin on SANPHAM.Id_Pin = Pin.Id join DungLuong on SANPHAM.Id_DLuong = DungLuong.Id join ManHinh on SANPHAM.Id_ManHinh = ManHinh.Id where soluongton > 0";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            listSp.add(new ViewSanPham(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getBigDecimal(9), rs.getInt(10)));
        }

        rs.close();
        ps.close();
        con.close();

        return listSp;

    }

    public List<SanPhamViewModel> getAllXoa() throws SQLException {
        ArrayList<SanPhamViewModel> listSp = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "select MaSP, TenSP, TenLH, TenRAM, TenPin, TenDL, TenMH, SoLuongTon, GiaBan,NamBH from SANPHAM join PhanLoaiHang on SANPHAM.Id_PhanLoai = PhanLoaiHang.Id join RAM on SANPHAM.Id_Ram = RAM.Id join Pin on SANPHAM.Id_Pin = Pin.Id join DungLuong on SANPHAM.Id_DLuong = DungLuong.Id join ManHinh on SANPHAM.Id_ManHinh = ManHinh.Id where SANPHAM.TrangThai = 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            listSp.add(new SanPhamViewModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getBigDecimal(9), rs.getInt(10)));

        }

        rs.close();
        ps.close();
        con.close();

        return listSp;
    }

    public Integer addSP(SanPham sp) throws SQLException {
        Connection con = DBContext.getConnection();
        String sql = "insert into SANPHAM values(NEWID(), ?, ?, ?, ?, ?, ?, ?, ?, (select Id from DungLuong where TenDL = ?), (select Id from MauSac where TenMau = ?), (select Id from XuatXu where TenNoiXX = ?), (select Id from PhanLoaiHang where TenLH = ?), (select Id from Pin where TenPin = ?), (select Id from CHIP where TenChip = ?), (select Id from RAM where TenRAM = ?), (select Id from ManHinh where TenMH = ?), (select Id from KichThuoc where TenKT = ?))";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, sp.getMaSP());
        ps.setString(2, sp.getTenSP());
        ps.setBigDecimal(3, sp.getGiaNhap());
        ps.setBigDecimal(4, sp.getGiaBan());
        ps.setInt(5, sp.getSoLuongTon());
        ps.setString(6, sp.getMoTa());
        ps.setInt(7, sp.getTrangThai());
        ps.setInt(8, sp.getNamBH());
        ps.setString(9, sp.getId_DLuong());
        ps.setString(10, sp.getId_MauSac());
        ps.setString(11, sp.getId_XuatXu());
        ps.setString(12, sp.getId_PhanLoai());
        ps.setString(13, sp.getId_Pin());
        ps.setString(14, sp.getId_Chip());
        ps.setString(15, sp.getId_Ram());
        ps.setString(16, sp.getId_ManHinh());
        ps.setString(17, sp.getId_KhichThuoc());

        int affectedRows = ps.executeUpdate();

        ps.close();
        con.close();

        return affectedRows;
    }

    public Integer updateSP(SanPham sp) throws SQLException {
        Connection con = DBContext.getConnection();
        String sql = "update sanpham set tensp = ?, GIANHAP = ?, GIABAN = ?, SOLUONGTON = ?, MOTA = ?, NamBH =  ?, Id_DLuong = (SELECT ID FROM DungLuong WHERE TenDL = ?), Id_MauSac = (SELECT ID FROM MauSac WHERE TenMau = ?), Id_XuatXu = (SELECT ID FROM XuatXu WHERE TenNoiXX = ?), Id_PhanLoai = (SELECT ID FROM PhanLoaiHang WHERE TenLH = ?), Id_Pin = (SELECT ID FROM Pin WHERE TenPin = ?), Id_Chip = (SELECT ID FROM CHIP WHERE TenChip = ?), Id_Ram = (SELECT ID FROM RAM WHERE TenRAM = ?), Id_ManHinh = (SELECT ID FROM ManHinh WHERE TenMH = ?), Id_KichThuoc = (SELECT ID FROM KichThuoc WHERE TenKT = ?) where MaSP = ?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, sp.getTenSP());
        ps.setBigDecimal(2, sp.getGiaNhap());
        ps.setBigDecimal(3, sp.getGiaBan());
        ps.setInt(4, sp.getSoLuongTon());
        ps.setString(5, sp.getMoTa());
        ps.setInt(6, sp.getNamBH());
        ps.setString(7, sp.getId_DLuong());
        ps.setString(8, sp.getId_MauSac());
        ps.setString(9, sp.getId_XuatXu());
        ps.setString(10, sp.getId_PhanLoai());
        ps.setString(11, sp.getId_Pin());
        ps.setString(12, sp.getId_Chip());
        ps.setString(13, sp.getId_Ram());
        ps.setString(14, sp.getId_ManHinh());
        ps.setString(15, sp.getId_KhichThuoc());
        ps.setString(16, sp.getMaSP());

        int affectedRows = ps.executeUpdate();

        ps.close();
        con.close();

        return affectedRows;
    }

    public Integer deleteSP(SanPham sp) throws SQLException {
        Connection con = DBContext.getConnection();
        String sql = "update SANPHAM set TrangThai = 1 where MaSP = ?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, sp.getMaSP());

        int affectedRows = ps.executeUpdate();

        ps.close();
        con.close();

        return affectedRows;
    }

    public Integer hoanTacSP(SanPham sp) throws SQLException {
        Connection con = DBContext.getConnection();
        String sql = "update SANPHAM set TrangThai = 0 where MaSP = ?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, sp.getMaSP());

        int affectedRows = ps.executeUpdate();

        ps.close();
        con.close();

        return affectedRows;
    }

    public List<SanPham> getThongTin(String maSp) throws SQLException {
        ArrayList<SanPham> listSp = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "select GiaNhap, MoTa, NamBH, TenMau, TenNoiXX, TenChip, TenKT from SANPHAM join MauSac on SANPHAM.Id_MauSac = MauSac.Id join XuatXu on SANPHAM.Id_XuatXu = XuatXu.Id join CHIP on SANPHAM.Id_Chip = CHIP.Id join ManHinh on SANPHAM.Id_ManHinh = ManHinh.Id join KichThuoc on SANPHAM.Id_KichThuoc = KichThuoc.Id where MaSP = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maSp);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            listSp.add(new SanPham(null, null, null, rs.getBigDecimal("GiaNhap"), null, null, rs.getString("MoTa"), null, rs.getInt("NamBH"), null, rs.getString("TenMau"), rs.getString("TenNoiXX"), null, null, rs.getString("TenChip"), null, null, rs.getString("TenKT"), null));
        }

        rs.close();
        ps.close();
        con.close();

        return listSp;
    }

    public List<String> selectPLH(String ten) {
        String sql = "SELECT DISTINCT TenLH FROM PhanLoaiHang";
        List<String> list = new ArrayList<>();
        try {
            Connection connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public ArrayList<ViewSanPham> selectLoaiHanh(String tenLH) {
        ArrayList<ViewSanPham> list = new ArrayList<>();
        String sql = "SELECT MaSP, TenSP, SoLuongTon, TenDL, TenLH, GiaBan, SANPHAM.TrangThai FROM SANPHAM JOIN PhanLoaiHang ON SANPHAM.Id_PhanLoai = PhanLoaiHang.Id JOIN DungLuong ON SANPHAM.Id_DLuong = DungLuong.Id WHERE TenLH = ?";
        try {
            Connection connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, tenLH);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ViewSanPham spvm = new ViewSanPham();
                spvm.setMaSp(rs.getString(1));
                spvm.setTenSp(rs.getString(2));
                spvm.setSoLuong(rs.getInt(3));
                spvm.setDungLuong(rs.getString(4));
                spvm.setPhanLoai(rs.getString(5));
                spvm.setGiaSp(rs.getBigDecimal(6));
                 spvm.setTrangThai(rs.getInt(7));

                list.add(spvm);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<ViewSanPham> selectDungLuong(String tenDL) {
        ArrayList<ViewSanPham> list = new ArrayList<>();
        String sql = "SELECT MaSP, TenSP, SoLuongTon, TenDL, TenLH, GiaBan, SANPHAM.TrangThai FROM SANPHAM JOIN PhanLoaiHang ON SANPHAM.Id_PhanLoai = PhanLoaiHang.Id JOIN DungLuong ON SANPHAM.Id_DLuong = DungLuong.Id WHERE TenDL = ?";
        try {
            Connection connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, tenDL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ViewSanPham spvm = new ViewSanPham();
                spvm.setMaSp(rs.getString(1));
                spvm.setTenSp(rs.getString(2));
                spvm.setSoLuong(rs.getInt(3));
                spvm.setDungLuong(rs.getString(4));
                spvm.setPhanLoai(rs.getString(5));
                spvm.setGiaSp(rs.getBigDecimal(6));
                spvm.setTrangThai(rs.getInt(7));

                list.add(spvm);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    
    public Integer updateQuantity(String maSp, Integer quantity) throws SQLException{
        Connection con = DBContext.getConnection();
        String sql = "update SANPHAM set SoLuongTon = (select SoLuongTon from SANPHAM where MaSP = ?) - ? where MaSP = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        
        ps.setString(1, maSp);
        ps.setInt(2, quantity);
        ps.setString(3, maSp);
        
        int affectedRows = ps.executeUpdate();

        ps.close();
        con.close();

        return affectedRows;
    }
    
    public Integer updateQuantity1(String maSp, Integer quantity) throws SQLException{
        Connection con = DBContext.getConnection();
        String sql = "update SANPHAM set SoLuongTon = (select SoLuongTon from SANPHAM where MaSP = ?) + ? where MaSP = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        
        ps.setString(1, maSp);
        ps.setInt(2, quantity);
        ps.setString(3, maSp);
        
        int affectedRows = ps.executeUpdate();

        ps.close();
        con.close();

        return affectedRows;
    }

    public ViewSanPham selectSoLuongSanPham(String maSP) throws SQLException { // update số lượng sản phẩm bị xoá đi trong giỏ hàng
        String sql = "SELECT SoLuongTon FROM SANPHAM where  Id = (SELECT Id FROM SANPHAM WHERE MaSP = ?) ";
        Connection connection = DBContext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, maSP);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            ViewSanPham sp = new ViewSanPham();
            sp.setSoLuong(rs.getInt(1));
            return sp;
        }
        return null;
    }
}
