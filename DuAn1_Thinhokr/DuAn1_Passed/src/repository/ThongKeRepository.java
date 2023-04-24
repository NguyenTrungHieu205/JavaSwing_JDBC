/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import ultility.DBContext;
import viewModel.ThongKeKhachHangViewModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import viewModel.ThongKeNhanVienViewModel;
import viewModel.ThongKeSanPhamViewModel;

/**
 *
 * @author ADMIN
 */
public class ThongKeRepository {
    public ArrayList<ThongKeKhachHangViewModel> getAll() {
        try {
            ArrayList<ThongKeKhachHangViewModel> listkh = new ArrayList<>();
            Connection con = DBContext.getConnection();
            String sql = "SELECT MaKH, HoTen, SDT, NgaySinh, DiaChi, GioiTinh, SoLanMua, SoDiem FROM KhachHang join Diem ON KhachHang.Id_Diem = Diem.Id";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThongKeKhachHangViewModel kh = new ThongKeKhachHangViewModel();
                kh.setMaKH(rs.getString(1));
                kh.setHoTen(rs.getString(2));
                kh.setSdt(rs.getString(3));
                kh.setNgaySinh(rs.getDate(4));
                kh.setDiaChi(rs.getString(5));
                kh.setGioiTinh(rs.getInt(6));
                kh.setSoLanMua(rs.getInt(7));
                kh.setSoDiem(rs.getInt(8));
                listkh.add(kh);
            }
            return listkh;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
     public ArrayList<ThongKeNhanVienViewModel> getAll1() {
        try {
            ArrayList<ThongKeNhanVienViewModel> listnv = new ArrayList<>();
            Connection con = DBContext.getConnection();
            String sql = "select MaNV,Ten, Email,SDT,GioiTinh,NgayBDLV,ChucVu.TenCV from NhanVien join ChucVu on NhanVien.Id_ChucVu = ChucVu.Id";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThongKeNhanVienViewModel nv = new ThongKeNhanVienViewModel();
                nv.setMaNV(rs.getString(1));
                nv.setHoVaTen(rs.getString(2));
                nv.setEmail(rs.getString(3));
                nv.setSDT(rs.getString(4));
                nv.setGioiTinh(rs.getInt(5));
                nv.setNgayBatDauLV(rs.getDate(6));
                nv.setChucVu(rs.getString(7));

                listnv.add(nv);
            }
            return listnv;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<ThongKeSanPhamViewModel> getAll2() {
        try {
            ArrayList<ThongKeSanPhamViewModel> listsp = new ArrayList<>();
            Connection con = DBContext.getConnection();
            String sql = "select MaSP, TenSP,GiaBan,SoLuongTon,sanPham.TrangThai,SANPHAM.SoLuongTon,GiaNhap, GiaBan, HDCT.SoLuong, DonGia, (HDCT.DonGia*HDCT.SoLuong) as 'Tổng tiền bán ra', (HDCT.SoLuong*SANPHAM.GiaNhap) as 'Tổng tiền nhập vào', ((HDCT.DonGia*HDCT.SoLuong) -(HDCT.SoLuong*SANPHAM.GiaNhap)) as 'Tiền Lời'  from HDCT join SANPHAM on HDCT.Id_SanPham = SANPHAM.Id JOIN HoaDon on HDCT.Id_HoaDon = HoaDon.Id WHERE HoaDon.TrangThai = '1'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThongKeSanPhamViewModel sp = new ThongKeSanPhamViewModel();
                sp.setMaSP(rs.getString(1));
                sp.setTenSP(rs.getString(2));
                sp.setGiaBan(rs.getBigDecimal(3));
                sp.setSoLuongTon(rs.getInt(4));
                sp.setTrangThai(rs.getInt(5));
                sp.setDaBan(rs.getLong(6));
                sp.setGiaNhap(rs.getBigDecimal(7));
                sp.setTienLai(rs.getBigDecimal(8));

                listsp.add(sp);
            }
            return listsp;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }


}
