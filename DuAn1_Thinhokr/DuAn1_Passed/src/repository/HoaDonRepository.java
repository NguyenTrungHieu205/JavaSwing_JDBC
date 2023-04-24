/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import model.GiamGia;
import model.HoaDon;
import ultility.DBContext;

/**
 *
 * @author phamtuyetnga
 */
public class HoaDonRepository {

    private DBContext dBContext;

    public Boolean saveHoaDon(HoaDon hoaDon) {

        int checkInsert = 0;

        String sql = "INSERT INTO HoaDon (NgayTao, NgayThanhToan, TrangThai, TenKH,NgayNhan, MaNV) VALUES (?,?,?,N'Khách Hàng',?,?)";
        try ( Connection con = dBContext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            //ps.setObject(1, hoaDon.getMaHD());
            ps.setObject(1, hoaDon.getNgayTao());
            ps.setObject(2, hoaDon.getNgayThanhToan());
            ps.setObject(3, hoaDon.getTrangThai());
            ps.setObject(4, hoaDon.getNgayNhan());
            ps.setObject(5, hoaDon.getMaNV());
            checkInsert = ps.executeUpdate();
            return checkInsert > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public Boolean saveNgayNhan(HoaDon hd) {
        int checkInsert = 0;

        String sql = "INSERT INTO HoaDon(NgayNhan) VALUES(?)";
        try ( Connection con = dBContext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(4, hd.getNgayNhan());
            checkInsert = ps.executeUpdate();
            return checkInsert > 0;

        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public ArrayList<HoaDon> getListHoaDon() {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "select * from HoaDon ORDER BY MaHD desc";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHD(rs.getString(2));
                hoaDon.setNgayTao(rs.getString(4));
                hoaDon.setTrangThai(rs.getInt(12));
                hoaDon.setTenKH(rs.getString(9));
                hoaDon.setNgayNhan(rs.getString(8));
                list.add(hoaDon);
            }
        } catch (Exception e) {
            e.getMessage();
        }

        return list;
    }

    public Integer updateHoaDon(HoaDon hoaDon) throws SQLException {

        Connection con = DBContext.getConnection();
        String sql = "update HoaDon set TrangThai = 1, ngaythanhtoan = getdate() where MaHD = ?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setObject(1, hoaDon.getMaHD());

        int affectedRows = ps.executeUpdate();

        ps.close();
        con.close();

        return affectedRows;
    }

    public ArrayList<HoaDon> getHisroryHD() {
        String sql = "SELECT HoaDon.MaHD, MaNV, HoaDon.NgayTao, SoLuong, NgayThanhToan, HTTT.TenHT, TenKH, HoaDon.TongTien, HoaDon.TrangThai FROM HoaDon left join HTTT on HoaDon.Id=HTTT.Id_HoaDon";
        ArrayList<HoaDon> list = new ArrayList<>();
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                HoaDon h1 = new HoaDon();
                h1.setMaHD(rs.getString(1));
                h1.setMaNV(rs.getString(2));
                h1.setNgayTao(rs.getString(3));
                h1.setSoLuong(rs.getInt(4));
                h1.setNgayThanhToan(rs.getString(5));
                h1.setTenHTTT(rs.getString(6));
                h1.setTenKH(rs.getString(7));
                h1.setTongTien(rs.getBigDecimal(8));
                h1.setTrangThai(rs.getInt(9));
                list.add(h1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<HoaDon> getDaThanhToan() {
        String sql = "SELECT HoaDon.MaHD, MaNV, HoaDon.NgayTao, SoLuong, NgayThanhToan, HTTT.TenHT, TenKH, HoaDon.TongTien, HoaDon.TrangThai\n"
                + "FROM HoaDon LEFT join HTTT on HoaDon.Id=HTTT.Id_HoaDon WHERE HoaDon.TrangThai = '1'";
        ArrayList<HoaDon> list = new ArrayList<>();
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                HoaDon h1 = new HoaDon();
                h1.setMaHD(rs.getString(1));
                h1.setMaNV(rs.getString(2));
                h1.setNgayTao(rs.getString(3));
                h1.setSoLuong(rs.getInt(4));
                h1.setNgayThanhToan(rs.getString(5));
                h1.setTenHTTT(rs.getString(6));
                h1.setTenKH(rs.getString(7));
                h1.setTongTien(rs.getBigDecimal(8));
                h1.setTrangThai(rs.getInt(9));
                list.add(h1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<HoaDon> getChuaThanhToan() {
        String sql = "SELECT HoaDon.MaHD, MaNV, HoaDon.NgayTao, SoLuong, NgayThanhToan, HTTT.TenHT, TenKH, HoaDon.TongTien, HoaDon.TrangThai\n"
                + "FROM HoaDon LEFT join HTTT on HoaDon.Id=HTTT.Id_HoaDon WHERE HoaDon.TrangThai = '0'";
        ArrayList<HoaDon> list = new ArrayList<>();
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                HoaDon h1 = new HoaDon();
                h1.setMaHD(rs.getString(1));
                h1.setMaNV(rs.getString(2));
                h1.setNgayTao(rs.getString(3));
                h1.setSoLuong(rs.getInt(4));
                h1.setNgayThanhToan(rs.getString(5));
                h1.setTenHTTT(rs.getString(6));
                h1.setTenKH(rs.getString(7));
                h1.setTongTien(rs.getBigDecimal(8));
                h1.setTrangThai(rs.getInt(9));
                list.add(h1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<HoaDon> locNgay(Date ngayTao, Date denNgay) {
        String sql = "SELECT HoaDon.MaHD, MaNV, HoaDon.NgayTao, SoLuong, NgayThanhToan, HTTT.TenHT, TenKH, HoaDon.TongTien, HoaDon.TrangThai\n"
                + "FROM HoaDon left join HTTT on HoaDon.Id=HTTT.Id_HoaDon WHERE HoaDon.NgayTao >= ? and HoaDon.NgayTao <= ?";
        ArrayList<HoaDon> list = new ArrayList<>();
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
            stm.setDate(1, (java.sql.Date) ngayTao);
            stm.setDate(2, (java.sql.Date) denNgay);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                HoaDon h1 = new HoaDon();
                h1.setMaHD(rs.getString(1));
                h1.setMaNV(rs.getString(2));
                h1.setNgayTao(rs.getString(3));
                h1.setSoLuong(rs.getInt(4));
                h1.setNgayThanhToan(rs.getString(5));
                h1.setTenHTTT(rs.getString(6));
                h1.setTenKH(rs.getString(7));
                h1.setTongTien(rs.getBigDecimal(8));
                h1.setTrangThai(rs.getInt(9));
                list.add(h1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    public Integer updateTenKh(String tenKh, String maHd) throws SQLException {
        Connection con = DBContext.getConnection();
        String sql = "update HoaDon set TenKH = ? where MaHD = ?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, tenKh);
        ps.setString(2, maHd);

        int affectedRows = ps.executeUpdate();

        ps.close();
        con.close();

        return affectedRows;
    }

    public String updateTinhTrang(String maHD) {
        String query = "UPDATE [dbo].[HoaDon]\n"
                + "   SET \n"
                + "      [TrangThai] = 1\n"
                + " WHERE MaHD = ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, maHD);

            if (ps.executeUpdate() > 0) {
                return "sửa thành công";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Sửa thất bại";
    }

    public String updateTTDangGiao(String maHD) {
        String query = "UPDATE [dbo].[HoaDon]\n"
                + "   SET \n"
                + "      [TrangThai] = 2\n"
                + " WHERE MaHD = ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, maHD);

            if (ps.executeUpdate() > 0) {
                return "sửa thành công";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Sửa thất bại";
    }

    public int trangThaiHoaDon(String maHD) throws SQLException { // select ra trạng thái hoá đơn 
        String sql = "select TrangThai from HoaDon where MaHD = ?";

        Connection con = DBContext.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, maHD);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            HoaDon hd = new HoaDon();
            hd.setTrangThai(rs.getInt(1));

            return hd.getTrangThai();
        }

        ps.close();
        con.close();

        return -1;
    }

    public Integer updateHdtt(String tenHt, BigDecimal tongTienHttt, String maHd, String maNv, Integer soLuong, BigDecimal tongTien, String maKh) throws SQLException {
        Connection con = DBContext.getConnection();
        String sql = "insert into HTTT(TenHT, TongTien, Id_HoaDon) values(?, ?, (select id from HoaDon where MaHD = ?));update HoaDon set MaNV = ?, SoLuong = ?, NgayThanhToan = GETDATE(), TongTien = ?, id_khachhang = (select id from khachhang where makh = ?) where MaHD = ?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, tenHt);
        ps.setBigDecimal(2, tongTienHttt);
        ps.setString(3, maHd);
        ps.setString(4, maNv);
        ps.setInt(5, soLuong);
        ps.setBigDecimal(6, tongTien);
        ps.setString(7, maKh);
        ps.setString(8, maHd);

        int affectedRows = ps.executeUpdate();

        ps.close();
        con.close();

        return affectedRows;
    }

    public Integer updateHdttOnl(String tenHt, String maHd, String maNv, Integer soLuong, BigDecimal tongTien, String maKh, Date ngayShip, Date ngayThanhToan, String diaChi, String sdtNguoiNhan, String sdtNguoiShip, String tenNguoiShip) throws SQLException {
        Connection con = DBContext.getConnection();
        String sql = "insert into HTTT(TenHT, Id_HoaDon) values(?, (select id from HoaDon where MaHD = ?));update HoaDon set MaNV = ?, SoLuong = ?, TongTien = ?, id_khachhang = (select id from khachhang where makh = ?), ngayship = ?, ngaythanhtoan = ?, diachi = ?, sdtnguoinhan = ?, sdtnguoiship = ?, tenNguoiShip = ? where MaHD = ?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, tenHt);
        ps.setString(2, maHd);
        ps.setString(3, maNv);
        ps.setInt(4, soLuong);
        ps.setBigDecimal(5, tongTien);
        ps.setString(6, maKh);
        ps.setDate(7, (java.sql.Date) ngayShip);
        ps.setDate(8, (java.sql.Date) ngayThanhToan);
        ps.setString(9, diaChi);
        ps.setString(10, sdtNguoiNhan);
        ps.setString(11, sdtNguoiShip);
        ps.setString(12, tenNguoiShip);
        ps.setString(13, maHd);

        int affectedRows = ps.executeUpdate();

        ps.close();
        con.close();

        return affectedRows;
    }

    public Integer updateHdttOnl1(String maHD) throws SQLException {
        Connection con = DBContext.getConnection();
        String sql = "update hoadon set trangthai = 1, ngaynhan = getdate() where mahd = ?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, maHD);

        int affectedRows = ps.executeUpdate();

        ps.close();
        con.close();

        return affectedRows;
    }

//    public ArrayList<HoaDon> getSoLuongHoaDon() throws SQLException {
//        ArrayList<HoaDon> list = new ArrayList<>();
//        Connection con = DBContext.getConnection();
//        String sql = "select COUNT(MaHD) from HoaDon where DAY(NgayTao) = DAY(GETDATE())";
//        PreparedStatement ps = con.prepareStatement(sql);
//        ResultSet rs = ps.executeQuery();
//
//        while (rs.next()) {
//            HoaDon hd = new HoaDon();
//            hd.setMaHD(rs.getString(1));
//
//            list.add(hd);
//        }
//
//        rs.close();
//        ps.close();
//        con.close();
//
//        return list;
//    }
//
//    public ArrayList<HoaDon> getSoDonDaThanhToan() throws SQLException {
//        ArrayList<HoaDon> list = new ArrayList<>();
//        Connection con = DBContext.getConnection();
//        String sql = "select COUNT(MaHD) from HoaDon where DAY(NgayTao) = DAY(GETDATE()) and TrangThai = 1";
//        PreparedStatement ps = con.prepareStatement(sql);
//        ResultSet rs = ps.executeQuery();
//
//        while (rs.next()) {
//            HoaDon hd = new HoaDon();
//            hd.setMaHD(rs.getString(1));
//
//            list.add(hd);
//        }
//
//        rs.close();
//        ps.close();
//        con.close();
//
//        return list;
//    }
//
//    public ArrayList<HoaDon> getSoDonChuaThanhToan() throws SQLException {
//        ArrayList<HoaDon> list = new ArrayList<>();
//        Connection con = DBContext.getConnection();
//        String sql = "select COUNT(MaHD) from HoaDon where DAY(NgayTao) = DAY(GETDATE()) and TrangThai = 0";
//        PreparedStatement ps = con.prepareStatement(sql);
//        ResultSet rs = ps.executeQuery();
//
//        while (rs.next()) {
//            HoaDon hd = new HoaDon();
//            hd.setMaHD(rs.getString(1));
//
//            list.add(hd);
//        }
//
//        rs.close();
//        ps.close();
//        con.close();
//
//        return list;
//    }
//
//    public ArrayList<HoaDon> getDoanhThuHomNay() throws SQLException {
//        ArrayList<HoaDon> list = new ArrayList<>();
//        Connection con = DBContext.getConnection();
//        String sql = "SELECT SUM(TongTien) FROM HOADON where DAY(NgayTao) = DAY(GETDATE())";
//        PreparedStatement ps = con.prepareStatement(sql);
//        ResultSet rs = ps.executeQuery();
//
//        while (rs.next()) {
//            HoaDon hd = new HoaDon();
//            hd.setTongTien(rs.getBigDecimal(1));
//
//            list.add(hd);
//        }
//
//        rs.close();
//        ps.close();
//        con.close();
//
//        return list;
//    }
    public ArrayList<HoaDon> getSoLuongHoaDonHomNay() throws SQLException {
        ArrayList<HoaDon> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "select COUNT(MaHD) from HoaDon where DAY(NgayTao) = DAY(GETDATE())";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            HoaDon hd = new HoaDon();
            hd.setMaHD(rs.getString(1));

            list.add(hd);
        }

        rs.close();
        ps.close();
        con.close();

        return list;
    }

    public ArrayList<HoaDon> getSoDonDaThanhToanHomNay() throws SQLException {
        ArrayList<HoaDon> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "select COUNT(MaHD) from HoaDon where DAY(NgayTao) = DAY(GETDATE()) and TrangThai = 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            HoaDon hd = new HoaDon();
            hd.setMaHD(rs.getString(1));

            list.add(hd);
        }

        rs.close();
        ps.close();
        con.close();

        return list;
    }

    public ArrayList<HoaDon> getSoDonChuaThanhToanHomNay() throws SQLException {
        ArrayList<HoaDon> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "select COUNT(MaHD) from HoaDon where DAY(NgayTao) = DAY(GETDATE()) and TrangThai = 0";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            HoaDon hd = new HoaDon();
            hd.setMaHD(rs.getString(1));

            list.add(hd);
        }

        rs.close();
        ps.close();
        con.close();

        return list;
    }

    public ArrayList<HoaDon> getDoanhThuHomNay() throws SQLException {
        ArrayList<HoaDon> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "SELECT SUM(TongTien) FROM HOADON where DAY(NgayTao) = DAY(GETDATE())";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            HoaDon hd = new HoaDon();
            hd.setTongTien(rs.getBigDecimal(1));

            list.add(hd);
        }

        rs.close();
        ps.close();
        con.close();

        return list;
    }

    public ArrayList<HoaDon> getSoLuongHoaDonThang() throws SQLException {
        ArrayList<HoaDon> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "select COUNT(MaHD) from HoaDon where MONTH(NgayTao) = MONTH(GETDATE())";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            HoaDon hd = new HoaDon();
            hd.setMaHD(rs.getString(1));

            list.add(hd);
        }

        rs.close();
        ps.close();
        con.close();

        return list;
    }

    public ArrayList<HoaDon> getSoDonDaThanhToanThang() throws SQLException {
        ArrayList<HoaDon> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "select COUNT(MaHD) from HoaDon where MONTH(NgayTao) = MONTH(GETDATE()) and TrangThai = 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            HoaDon hd = new HoaDon();
            hd.setMaHD(rs.getString(1));

            list.add(hd);
        }

        rs.close();
        ps.close();
        con.close();

        return list;
    }

    public ArrayList<HoaDon> getSoDonChuaThanhToanThang() throws SQLException {
        ArrayList<HoaDon> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "select COUNT(MaHD) from HoaDon where MONTH(NgayTao) = MONTH(GETDATE()) and TrangThai = 0";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            HoaDon hd = new HoaDon();
            hd.setMaHD(rs.getString(1));

            list.add(hd);
        }

        rs.close();
        ps.close();
        con.close();

        return list;
    }

    public ArrayList<HoaDon> getDoanhThuThang() throws SQLException {
        ArrayList<HoaDon> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "SELECT SUM(TongTien) FROM HOADON where MONTH(NgayTao) = MONTH(GETDATE())";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            HoaDon hd = new HoaDon();
            hd.setTongTien(rs.getBigDecimal(1));

            list.add(hd);
        }

        rs.close();
        ps.close();
        con.close();

        return list;
    }

    public ArrayList<HoaDon> getSoLuongHoaDonNam() throws SQLException {
        ArrayList<HoaDon> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "select COUNT(MaHD) from HoaDon where YEAR(NgayTao) = YEAR(GETDATE())";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            HoaDon hd = new HoaDon();
            hd.setMaHD(rs.getString(1));

            list.add(hd);
        }

        rs.close();
        ps.close();
        con.close();

        return list;
    }

    public ArrayList<HoaDon> getSoDonDaThanhToanNam() throws SQLException {
        ArrayList<HoaDon> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "select COUNT(MaHD) from HoaDon where YEAR(NgayTao) = YEAR(GETDATE()) and TrangThai = 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            HoaDon hd = new HoaDon();
            hd.setMaHD(rs.getString(1));

            list.add(hd);
        }

        rs.close();
        ps.close();
        con.close();

        return list;
    }

    public ArrayList<HoaDon> getSoDonChuaThanhToanNam() throws SQLException {
        ArrayList<HoaDon> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "select COUNT(MaHD) from HoaDon where YEAR(NgayTao) = YEAR(GETDATE()) and TrangThai = 0";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            HoaDon hd = new HoaDon();
            hd.setMaHD(rs.getString(1));

            list.add(hd);
        }

        rs.close();
        ps.close();
        con.close();

        return list;
    }

    public ArrayList<HoaDon> getDoanhThuNam() throws SQLException {
        ArrayList<HoaDon> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "SELECT SUM(TongTien) FROM HOADON where YEAR(NgayTao) = YEAR(GETDATE())";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            HoaDon hd = new HoaDon();
            hd.setTongTien(rs.getBigDecimal(1));

            list.add(hd);
        }

        rs.close();
        ps.close();
        con.close();

        return list;
    }

    public String selectMucGiamGia(String maSp) throws SQLException {
        Connection con = DBContext.getConnection();
        String sql = "select MucGiamGia from GiamGia JOIN GiamGiaSP on GiamGiaSP.ID_GiamGia = GiamGia.Id JOIN SANPHAM on GiamGiaSP.ID_SanPham = SANPHAM.Id WHERE MaSP = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maSp);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            GiamGia hd = new GiamGia();
            hd.setMucGiamGia(rs.getString(1));

            return hd.getMucGiamGia();
        }

        rs.close();
        ps.close();
        con.close();

        return null;
    }

    public String updateTTDaHoan(String maHD) {
        String query = "UPDATE [dbo].[HoaDon]\n"
                + "   SET \n"
                + "      [TrangThai] = 4\n"
                + " WHERE MaHD = ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, maHD);

            if (ps.executeUpdate() > 0) {
                return "sửa thành công";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Sửa thất bại";
    }

    public Integer updateHdttOnlThinh(String tenHt, BigDecimal tongTien, String maHd) throws SQLException {
        Connection con = DBContext.getConnection();
        String sql = "insert into HTTT(TenHT, TongTien, Id_HoaDon) values(?,?,(select id from HoaDon where MaHD = ?))";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, tenHt);
        ps.setBigDecimal(2, tongTien);
        ps.setString(3, maHd);
//        ps.setString(3, hoaDon.getMaNV());
//        ps.setInt(4, hoaDon.getSoLuong());
//        ps.setBigDecimal(5,hoaDon.getTongTien());
//        ps.setString(6,hoaDon.getIdKH());
//        ps.setDate(7, (java.sql.Date) hoaDon.getNgayShip());
//        ps.setDate(8, (java.sql.Date) hoaDon.getNgayThanhToan());
//        ps.setString(9, hoaDon.getDiaChi());
//        ps.setString(10, hoaDon.getSdtNguoiNhan());
//        ps.setString(11, hoaDon.getSdtNguoiiShip());
//        ps.setString(12, hoaDon.getTenNguoiShip());
//        ps.setString(13, maHd);

        int affectedRows = ps.executeUpdate();

        ps.close();
        con.close();

        return affectedRows;
    }
    // thinh

    public Integer updateHdttOnlThinh1(HoaDon hoaDon, String ma) throws SQLException {
        Connection con = DBContext.getConnection();
        String sql = "update HoaDon set MaNV = ?, TongTien = ?, TenKH = ?, diachi = ?, sdtnguoinhan = ?, sdtnguoiship = ?, tenNguoiShip = ?,TrangThai=5  where MaHD = ?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, hoaDon.getMaNV());
        //ps.setInt(2, hoaDon.getSoLuong());
        ps.setBigDecimal(2, hoaDon.getTongTien());
        ps.setString(3, hoaDon.getTenKH());
        ps.setString(4, hoaDon.getDiaChi());
        ps.setString(5, hoaDon.getSdtNguoiNhan());
        ps.setString(6, hoaDon.getSdtNguoiiShip());
        ps.setString(7, hoaDon.getTenNguoiShip());
        ps.setString(8, ma);

        int affectedRows = ps.executeUpdate();

        ps.close();
        con.close();

        return affectedRows;
    }

    // ngay Giao Hang
    public Integer updateHdttOnl1GiaoHang(HoaDon hoaDon, String ma) throws SQLException {
        Connection con = DBContext.getConnection();
        String sql = "update HoaDon set  ngayship = ? where MaHD = ?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, hoaDon.getNgayShip());

        ps.setString(2, ma);

        int affectedRows = ps.executeUpdate();

        ps.close();
        con.close();

        return affectedRows;
    }
    // ngay Thanh Toan

    public Integer updateHdttOnl1XacNhan(HoaDon hoaDon, String ma) throws SQLException {
        Connection con = DBContext.getConnection();
        String sql = "update HoaDon set  NgayThanhToan = ? where MaHD = ?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, hoaDon.getNgayThanhToan());

        ps.setString(2, ma);
        int affectedRows = ps.executeUpdate();

        ps.close();
        con.close();

        return affectedRows;
    }
    
    
 //1512
    // All tất cả hoá đơn to day
    public ArrayList<HoaDon> getAllHoaDonHomNay() throws SQLException {
        ArrayList<HoaDon> list = new ArrayList<>();

        String sql = "SELECT HoaDon.MaHD, MaNV, HoaDon.NgayTao, SoLuong, NgayThanhToan,"
                + " HTTT.TenHT, TenKH, HoaDon.TongTien, HoaDon.TrangThai\n"
                + " FROM HoaDon left join HTTT on HoaDon.Id=HTTT.Id_HoaDon\n"
                + "  where HoaDon.NgayTao = ?";

        Connection con = DBContext.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            HoaDon hd = new HoaDon();
            hd.setMaHD(rs.getString(1));
            hd.setMaNV(rs.getString(2));
            hd.setNgayTao(rs.getString(3));
            hd.setSoLuong(rs.getInt(4));
            hd.setNgayThanhToan(rs.getString(5));
            hd.setTenHTTT(rs.getString(6));
            hd.setTenKH(rs.getString(7));
            hd.setTongTien(rs.getBigDecimal(8));
            hd.setTrangThai(rs.getInt(9));
            list.add(hd);
        }
        rs.close();
        ps.close();
        con.close();

        return list;
    }

    // All tất cả hoá đơn to day theo trạng thái
    public ArrayList<HoaDon> getAllHoaDonHomNayLocCB(int trangThai) throws SQLException {
        ArrayList<HoaDon> list = new ArrayList<>();

        String sql = "SELECT HoaDon.MaHD, MaNV, HoaDon.NgayTao, SoLuong, "
                + " NgayThanhToan, HTTT.TenHT, TenKH, HoaDon.TongTien, HoaDon.TrangThai\n"
                + " FROM HoaDon left join HTTT on HoaDon.Id=HTTT.Id_HoaDon\n"
                + "  where HoaDon.NgayTao = ? AND HoaDon.TrangThai = ?";

        Connection con = DBContext.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
        ps.setInt(2, trangThai);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            HoaDon hd = new HoaDon();
            hd.setMaHD(rs.getString(1));
            hd.setMaNV(rs.getString(2));
            hd.setNgayTao(rs.getString(3));
            hd.setSoLuong(rs.getInt(4));
            hd.setNgayThanhToan(rs.getString(5));
            hd.setTenHTTT(rs.getString(6));
            hd.setTenKH(rs.getString(7));
            hd.setTongTien(rs.getBigDecimal(8));
            hd.setTrangThai(rs.getInt(9));
            list.add(hd);
        }
        rs.close();
        ps.close();
        con.close();

        return list;
    }




}
