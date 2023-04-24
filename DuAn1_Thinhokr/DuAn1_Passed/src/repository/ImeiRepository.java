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
import model.SanPham;
import ultility.DBContext;
import viewModel.ImeiViewModel;
import viewModel.SanPhamImeiViewModel;
import viewModel.SanPhamViewModel;

/**
 *
 * @author Admin
 */
public class ImeiRepository {

    public ArrayList<ImeiViewModel> getAllImei() throws SQLException {
        ArrayList<ImeiViewModel> listimei = new ArrayList<>();
        String sql = "SELECT MaImei, TrangThai, Id_Sp FROM IMEI where TrangThai != 3";
        Connection conn = DBContext.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            ImeiViewModel ivm = new ImeiViewModel();
            ivm.setMaImei(rs.getString(1));
            ivm.setTrangThai(rs.getInt(2));
            ivm.setId_Sp(rs.getString(3));
            listimei.add(ivm);
        }
        return listimei;
    }

    public Integer them(ImeiViewModel ii) throws Exception {
        Connection con = DBContext.getConnection();
        String sql = "INSERT INTO IMEI (MaImei, TrangThai, Id_Sp) VALUES (?,0,(select Id from SANPHAM where MaSP=?))";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, ii.getMaImei());
        ps.setString(2, ii.getId_Sp());
        Integer ketQua = ps.executeUpdate();

        return ketQua;
    }

    public String getAllMaSP(String ma) throws SQLException {
        ArrayList<SanPham> listimei = new ArrayList<>();
        String sql = "select MaSP from SANPHAM where Id = ?";
        Connection conn = DBContext.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, ma);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return rs.getString(1);
        }
        return null;

    }

    public boolean addImei(ImeiViewModel imei) throws SQLException {
        String sql = "insert into IMEI(MaImei, TrangThai, Id_Sp) values(?,?, (select Id from SanPham where MaSP = ?))";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
            stm.setString(1, imei.getMaImei());
            stm.setInt(2, imei.getTrangThai());
            stm.setString(3, imei.getId_Sp());
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    public boolean update(ImeiViewModel imei) throws SQLException {
        String sql = "update IMEI set TrangThai = ?, Id_Sp = (SELECT ID FROM SANPHAM WHERE MaSP = ?) where MaImei = ?";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
            stm.setInt(1, imei.getTrangThai());
            stm.setString(2, imei.getId_Sp());
            stm.setString(3, imei.getMaImei());
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateTT1(String maImei) throws SQLException {
        String sql = "update IMEI set TrangThai = 1 where MaImei = ?";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
            stm.setString(1, maImei);
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateTT5(String maImei) throws SQLException {
        String sql = "update IMEI set TrangThai = 5 where MaImei = ?";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
            stm.setString(1, maImei);
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateTT0(String maImei) throws SQLException {
        String sql = "update IMEI set TrangThai = 0 where MaImei = ?";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
            stm.setString(1, maImei);
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateTTXoaAll(String maSp) throws SQLException {
        String sql = "update imei set trangthai = 0 where id_sp = (select id from sanpham where masp = ?) and trangthai = 5";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
            stm.setString(1, maSp);
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<ImeiViewModel> getAllXoa() throws SQLException {
        ArrayList<ImeiViewModel> listivm = new ArrayList<>();
        String sql = "SELECT MaImei, Id_Sp FROM IMEI WHERE TrangThai = '3' ";
        Connection conn = DBContext.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            ImeiViewModel ivm = new ImeiViewModel();
            ivm.setMaImei(rs.getString(1));
//            ivm.setTrangThai(rs.getInt(2));
            ivm.setId_Sp(rs.getString(2));
            listivm.add(ivm);
        }
        return listivm;
    }

//    public boolean updateTT3(ImeiViewModel ivms) throws SQLException {
//        String sql = "UPDATE CHIP SET TrangThai = 0,Id_Sp WHERE MaChip =? ";
//        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql);) {
//            stm.setString(1, ivms.getMaImei());
//            stm.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
    public boolean delete(String imei) throws SQLException {
        Connection con = DBContext.getConnection();
        String sql = "update IMEI set TrangThai = 3 where MaImei = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, imei);

        return ps.executeUpdate() > 0;
    }

    // lấy ra list tìm kiêm stheo mã imei or mã sản phẩm
    public ArrayList<ImeiViewModel> selecttimKiemBangImei(String ma) throws SQLException {
        ArrayList<ImeiViewModel> list = new ArrayList<>();
        String sql = "select MaImei,IMEI.TrangThai, MaSP  from IMEI JOIN SANPHAM on IMEI.Id_Sp = SANPHAM.Id where MaImei =? or MaSP = ?";
        Connection connection = DBContext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, ma);
        ps.setString(2, ma);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ImeiViewModel imei = new ImeiViewModel();
            imei.setMaImei(rs.getString(1));
            imei.setTrangThai(rs.getInt(2));
            imei.setId_Sp(rs.getString(3));

            list.add(imei);
        }
        return list;

    }

    // lấy ra list tìm kiêm stheo mã imei or mã sản phẩm
    public ArrayList<SanPhamImeiViewModel> selecttimKiemBangSanPham(String ma) throws SQLException {
        ArrayList<SanPhamImeiViewModel> list = new ArrayList<>();
        String sql = "select MaSP, TenSP, CHIP.TenChip , DungLuong.TenDL, KichThuoc.TenKT, ManHinh.TenMH, MauSac.TenMau, PhanLoaiHang.TenLH, Pin.TenPin, RAM.TenRAM, XuatXu.TenNoiXX, GiaBan, SoLuongTon, SANPHAM.TrangThai from SANPHAM join PhanLoaiHang on SANPHAM.Id_PhanLoai = PhanLoaiHang.Id join RAM on SANPHAM.Id_Ram = RAM.Id join Pin on SANPHAM.Id_Pin = Pin.Id join DungLuong on SANPHAM.Id_DLuong = DungLuong.Id join ManHinh on SANPHAM.Id_ManHinh = ManHinh.Id JOIN CHIP on SANPHAM.Id_Chip = CHIP.Id JOIN KichThuoc on SANPHAM.Id_KichThuoc = KichThuoc.Id \n"
                + " JOIN MauSac on SANPHAM.Id_MauSac = MauSac.Id JOIN XuatXu ON SANPHAM.Id_XuatXu = XuatXu.Id where SANPHAM.MaSP = ? or SANPHAM.TenSP = ? or CHIP.TenChip =? or DungLuong.TenDL =? or KichThuoc.TenKT = ? or ManHinh.TenMH = ? or MauSac.TenMau =? or PhanLoaiHang.TenLH =? \n"
                + " or Pin.TenPin =? or RAM.TenRAM =? or XuatXu.TenNoiXX =? or SANPHAM.TrangThai = ?";
        Connection connection = DBContext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, ma);
        ps.setString(2, ma);
        ps.setString(3, ma);
        ps.setString(4, ma);
        ps.setString(5, ma);
        ps.setString(6, ma);
        ps.setString(7, ma);
        ps.setString(8, ma);
        ps.setString(9, ma);
        ps.setString(10, ma);
        ps.setString(11, ma);
        ps.setInt(12, Integer.parseInt(ma));

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            SanPhamImeiViewModel sp = new SanPhamImeiViewModel();
            sp.setMaSp(rs.getString(1));
            sp.setTenSp(rs.getString(2));
            sp.setTenChip(rs.getString(3));
            sp.setTenDungLuong(rs.getString(4));
            sp.setTenKichThuoc(rs.getString(5));
            sp.setTenmanHing(rs.getString(6));
            sp.setTenMau(rs.getString(7));
            sp.setTenLoaiHang(rs.getString(8));
            sp.setTenPin(rs.getString(9));
            sp.setTenRam(rs.getString(10));
            sp.setTenNoiSanXuat(rs.getString(11));
            sp.setGiaBan(rs.getBigDecimal(12));
            sp.setSoLuongTon(rs.getInt(13));
            sp.setTrangThai(rs.getInt(14));

            list.add(sp);
        }
        return list;

    }

    //lấy ra thông tin sản phẩm
    public ArrayList<SanPhamImeiViewModel> selectAllSanPham() throws SQLException {
        ArrayList<SanPhamImeiViewModel> list = new ArrayList<>();
        String sql = "select MaSP, TenSP, CHIP.TenChip , DungLuong.TenDL, KichThuoc.TenKT, ManHinh.TenMH, MauSac.TenMau,\n"
                + "            PhanLoaiHang.TenLH, Pin.TenPin, RAM.TenRAM, XuatXu.TenNoiXX, COUNT(IMEI.Id_Sp) as 'Số Lượng', GiaBan, SANPHAM.TrangThai\n"
                + "            from SANPHAM join PhanLoaiHang on SANPHAM.Id_PhanLoai = PhanLoaiHang.Id join RAM on SANPHAM.Id_Ram = RAM.Id \n"
                + "            join Pin on SANPHAM.Id_Pin = Pin.Id join DungLuong on SANPHAM.Id_DLuong = DungLuong.Id join ManHinh \n"
                + "            on SANPHAM.Id_ManHinh = ManHinh.Id JOIN CHIP on SANPHAM.Id_Chip = CHIP.Id JOIN KichThuoc on SANPHAM.Id_KichThuoc = KichThuoc.Id \n"
                + "        JOIN MauSac on SANPHAM.Id_MauSac = MauSac.Id JOIN XuatXu ON SANPHAM.Id_XuatXu = XuatXu.Id join IMEI on SANPHAM.Id = IMEI.Id_Sp where IMEI.TrangThai = 0\n"
                + "        GROUP by MaSP, TenSP, CHIP.TenChip , DungLuong.TenDL, KichThuoc.TenKT, ManHinh.TenMH, MauSac.TenMau,\n"
                + "            PhanLoaiHang.TenLH, Pin.TenPin, RAM.TenRAM, XuatXu.TenNoiXX, GiaBan, SoLuongTon, SANPHAM.TrangThai ";
        Connection connection = DBContext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            SanPhamImeiViewModel sp = new SanPhamImeiViewModel();
            sp.setMaSp(rs.getString(1));
            sp.setTenSp(rs.getString(2));
            sp.setTenChip(rs.getString(3));
            sp.setTenDungLuong(rs.getString(4));
            sp.setTenKichThuoc(rs.getString(5));
            sp.setTenmanHing(rs.getString(6));
            sp.setTenMau(rs.getString(7));
            sp.setTenLoaiHang(rs.getString(8));
            sp.setTenPin(rs.getString(9));
            sp.setTenRam(rs.getString(10));
            sp.setTenNoiSanXuat(rs.getString(11));
            sp.setGiaBan(rs.getBigDecimal(12));
            sp.setSoLuongTon(rs.getInt(13));
            sp.setTrangThai(rs.getInt(14));

            list.add(sp);
        }
        return list;

    }

    public Integer hoanTac(ImeiViewModel imei) throws SQLException {
        Connection con = DBContext.getConnection();
        String sql = "update IMEI set TrangThai = '0' where MaImei = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, imei.getMaImei());
        int affectedRows = ps.executeUpdate();

        return affectedRows;
    }

    // sửa số lượng sản phẩm
    public boolean updateSoLuongSanPham(ImeiViewModel imei) throws SQLException {
        String sql = "update SANPHAM set SoLuongTon = (SoLuongTon + 1) WHERE MaSP = ?";
        Connection con = DBContext.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, imei.getId_Sp());

        return ps.executeUpdate() > 0;
    }

    // Lọc online
    public ArrayList<ImeiViewModel> locOnline() throws SQLException {
        ArrayList<ImeiViewModel> listivm = new ArrayList<>();
        String sql = "SELECT MaImei, TrangThai, Id_Sp FROM IMEI WHERE TrangThai = 0 ";
        Connection conn = DBContext.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            ImeiViewModel ivm = new ImeiViewModel();
            ivm.setMaImei(rs.getString(1));
            ivm.setTrangThai(rs.getInt(2));
            ivm.setId_Sp(rs.getString(3));
            listivm.add(ivm);
        }
        return listivm;
    }

    // loc Đã bán
    public ArrayList<ImeiViewModel> locDaBan() throws SQLException {
        ArrayList<ImeiViewModel> listivm = new ArrayList<>();
        String sql = "SELECT MaImei, TrangThai, Id_Sp FROM IMEI WHERE TrangThai = 1 ";
        Connection conn = DBContext.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            ImeiViewModel ivm = new ImeiViewModel();
            ivm.setMaImei(rs.getString(1));
            ivm.setTrangThai(rs.getInt(2));
            ivm.setId_Sp(rs.getString(3));
            listivm.add(ivm);
        }
        return listivm;
    }

    // loc Đang về
    public ArrayList<ImeiViewModel> locDangVe() throws SQLException {
        ArrayList<ImeiViewModel> listivm = new ArrayList<>();
        String sql = "SELECT MaImei, TrangThai, Id_Sp FROM IMEI WHERE TrangThai = 2 ";
        Connection conn = DBContext.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            ImeiViewModel ivm = new ImeiViewModel();
            ivm.setMaImei(rs.getString(1));
            ivm.setTrangThai(rs.getInt(2));
            ivm.setId_Sp(rs.getString(3));
            listivm.add(ivm);
        }
        return listivm;
    }

    // loc ngừng bán
    public ArrayList<ImeiViewModel> locNgungBan() throws SQLException {
        ArrayList<ImeiViewModel> listivm = new ArrayList<>();
        String sql = "SELECT MaImei, TrangThai, Id_Sp FROM IMEI WHERE TrangThai = 3 ";
        Connection conn = DBContext.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            ImeiViewModel ivm = new ImeiViewModel();
            ivm.setMaImei(rs.getString(1));
            ivm.setTrangThai(rs.getInt(2));
            ivm.setId_Sp(rs.getString(3));
            listivm.add(ivm);
        }
        return listivm;
    }
    
    public Integer trangThaiImei(String maImei) throws SQLException {
        String sql = "select TrangThai from IMEI where MaImei = ?";
        Connection conn = DBContext.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, maImei);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }

    public boolean checkTrungMaImei(String maImei) throws SQLException {
        Connection con = DBContext.getConnection();
        String sql = "select MaImei from IMEI where MaImei = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maImei);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return true;
        }

        rs.close();
        ps.close();
        con.close();

        return false;
    }

}
