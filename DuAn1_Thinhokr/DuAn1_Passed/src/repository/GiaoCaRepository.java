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
import java.util.Date;
import model.GiaoCa;
import model.HoaDon;
import ultility.DBContext;
import viewModel.GiaoCaViewModel;
/**
 *
 * @author ADMIN
 */
public class GiaoCaRepository {
    public ArrayList<GiaoCa> layTongTien(String thoiGianNhanCa, String thoiGianGiaoCa){
        try {
            ArrayList<GiaoCa> list = new ArrayList<>();
            String sql = "select sum(tongtien) from hoadon where NgayThanhToan >= ?  and NgayThanhToan <=  ?";
            Connection conn = DBContext.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1,  thoiGianNhanCa);
            stm.setString(2, thoiGianGiaoCa);
             ResultSet rs = stm.executeQuery();
            while(rs.next()){
                GiaoCa gc = new GiaoCa();
                gc.setTongTienTrongCa(rs.getBigDecimal(1));
                list.add(gc);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
        
    }
    
    public ArrayList<GiaoCa> layTongTienMat(String thoiGianNhanCa, String thoiGianGiaoCa){
        try {
            ArrayList<GiaoCa> list = new ArrayList<>();
            String sql = "select sum(httt.TongTien) from HTTT join HoaDon on httt.Id_HoaDon = HoaDon.Id where TenHT = N'Tiền mặt' and NgayThanhToan >= ? and NgayThanhToan <= ?";
            Connection conn = DBContext.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1,  thoiGianNhanCa);
            stm.setString(2, thoiGianGiaoCa);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                GiaoCa gc = new GiaoCa();
                gc.setTongTienMat(rs.getBigDecimal(1));
                list.add(gc);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
        
    }
    
    public Integer addGiaoCa(GiaoCa gc, String thoiGianNhan, String thoiGianGiaoCa) {
        String sql = "INSERT INTO GiaoCa (ThoiGianNhanCa, ThoiGianGiaoCa, Id_NhanVienTrongCa, TienBanDau, TongTienTrongCa, TongTienMat, TongTienKhac, TienPhatSinh, GhiChuPhatSinh) VALUES (?,?,(select Id from NhanVien where MaNV = ?),?,?,?,?,?,?)";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql)) {
            //stm.setString(1, gc.getMaGiaoCa());
            stm.setString(1, thoiGianNhan);
            stm.setString(2, thoiGianGiaoCa);
            stm.setString(3, gc.getMaNV());
            stm.setBigDecimal(4, gc.getTenBanDau());
            stm.setBigDecimal(5, gc.getTongTienTrongCa());
            stm.setBigDecimal(6, gc.getTongTienMat());
            stm.setBigDecimal(7, gc.getTongTienKhac());
            stm.setBigDecimal(8, gc.getTienPhatSinh());
            stm.setString(9, gc.getGhiChu());
            return stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public Integer updateNhanVien(GiaoCa gc, String thoiGianNhan) {
        String sql = "UPDATE GiaoCa SET Id_NhanVienCaTiepTheo = (select Id from NhanVien where MaNV = ?), TongTienMatCaTruoc = ?, ThoiGianReset = ?, TongTienMatDaRut = ? WHERE MaGiaoCa = (SELECT TOP 1 MaGiaoCa FROM GiaoCa order by MaGiaoCa DESC)";
        try ( Connection conn = DBContext.getConnection();  PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, gc.getMaNvNhanCa());
//            stm.setBigDecimal(2, gc.getTongTienMat());
            stm.setBigDecimal(2, gc.getTongTienMatCaTruoc());
            stm.setString(3, thoiGianNhan);
            stm.setBigDecimal(4, gc.getTongTienMatDaRut());
            //stm.setString(6, gc.getMaGiaoCa());
            return stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<GiaoCaViewModel> getThongTinGiaoCa(){
        try {
            ArrayList<GiaoCaViewModel> list = new ArrayList<>();
            String sql = "select MaGiaoCa, (select manv from NhanVien where Id = Id_NhanVienTrongCa) as 'nv giao ca', (select manv from NhanVien where Id = Id_NhanVienCaTiepTheo) as 'nv nhan ca', ThoiGianNhanCa, ThoiGianGiaoCa, TienBanDau, TienPhatSinh, TongTienTrongCa, GhiChuPhatSinh from giaoca order by MaGiaoCa desc";
            Connection conn = DBContext.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                GiaoCaViewModel gc = new GiaoCaViewModel();
                gc.setMaGiaoCa(rs.getString(1));
                gc.setMaNV(rs.getString(2));
                gc.setMaNvNhanCa(rs.getString(3));
                gc.setThoiGianNhanCa(rs.getString(4));
                gc.setThoiGianGiaoCa(rs.getString(5));
                gc.setTenBanDau(rs.getBigDecimal(6));
                gc.setTienPhatSinh(rs.getBigDecimal(7));
                gc.setTongTienTrongCa(rs.getBigDecimal(8));
                gc.setGhiChu(rs.getString(9));
                list.add(gc);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
        
    }
    
        public ArrayList<GiaoCaViewModel> locNgay(String ngayGiao, String ngayNhan){
        try {
            ArrayList<GiaoCaViewModel> list = new ArrayList<>();
            String sql = "select MaGiaoCa, (select manv from NhanVien where Id = Id_NhanVienTrongCa) "
                    + " as 'nv giao ca', (select manv from NhanVien where Id = Id_NhanVienCaTiepTheo) "
                    + " as 'nv nhan ca', ThoiGianNhanCa, ThoiGianGiaoCa, TienBanDau, TienPhatSinh, TongTienTrongCa, "
                    + " GhiChuPhatSinh from giaoca where ThoiGianGiaoCa >= ? and ThoiGianNhanCa <= ?";
            Connection conn = DBContext.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, ngayGiao);
            stm.setString(2, ngayNhan);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                GiaoCaViewModel gc = new GiaoCaViewModel();
                gc.setMaGiaoCa(rs.getString(1));
                gc.setMaNV(rs.getString(2));
                gc.setMaNvNhanCa(rs.getString(3));
                gc.setThoiGianNhanCa(rs.getString(4));
                gc.setThoiGianGiaoCa(rs.getString(5));
                gc.setTenBanDau(rs.getBigDecimal(6));
                gc.setTienPhatSinh(rs.getBigDecimal(7));
                gc.setTongTienTrongCa(rs.getBigDecimal(8));
                gc.setGhiChu(rs.getString(9));
                list.add(gc);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
        
    }
}
