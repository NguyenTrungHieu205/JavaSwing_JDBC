package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ChucVu;
import model.NhanVien;

import ultility.DBContext;
import ultility.DBContext;
import viewModel.ViewModeChucVu;
import viewModel.ViewModeNhanVien;

public class NhanVienRepository {

   
          public ArrayList<ViewModeNhanVien> tatCaNhanVien() {
        try {
            ArrayList<ViewModeNhanVien> listNhanVien = new ArrayList<>();
            Connection con = DBContext.getConnection();
            String sql = "SELECT MaNV,Ten,TenDem,Ho, GioiTinh,NgaySinh,DiaChi,SDT,Email, MatKhau,GhiChu,ChucVu.Id as IdCv,ChucVu.TenCV as TenCv\n"
                    + "                                 FROM    NhanVien left join ChucVu on NhanVien.Id_ChucVu=ChucVu.Id ";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ViewModeNhanVien nv = new ViewModeNhanVien();
                nv.setMaNv(rs.getString(1));
                nv.setTenNv(rs.getString(2));
                nv.setTenDem(rs.getString(3));
                nv.setHo(rs.getString(4));
                nv.setGioiTinh(rs.getInt(5));
                nv.setNgaySinh(rs.getString(6));
                nv.setDiaChi(rs.getString(7));
                nv.setSdt(rs.getString(8));
                nv.setEmail(rs.getString(9));
                nv.setMatKhau(rs.getString(10));
//                nv.setNgayDiLam(rs.getString(11));
                nv.setGhiChu(rs.getString(11));
                ChucVu cv = new ChucVu();
                cv.setId(rs.getString("IdCv"));
                cv.setTenCv(rs.getString("TenCv"));
                nv.setChucVu(cv);
                listNhanVien.add(nv);
            }
            rs.close();
            ps.close();
            con.close();
            return listNhanVien;
        } catch (SQLException ex) {
            return null;
        }
    }

    public ArrayList<ViewModeNhanVien> getAll() throws Exception {
        ArrayList<ViewModeNhanVien> listNhanVien = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "SELECT MaNV,Ten,TenDem,Ho, GioiTinh,NgaySinh,DiaChi,SDT,Email, MatKhau,GhiChu,ChucVu.Id as IdCv,ChucVu.TenCV as TenCv\n"
                + "                                 FROM    NhanVien left join ChucVu on NhanVien.Id_ChucVu=ChucVu.Id   \n"
                + "				where NhanVien.TrangThai=0";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ViewModeNhanVien nv = new ViewModeNhanVien();
            nv.setMaNv(rs.getString(1));
            nv.setTenNv(rs.getString(2));
            nv.setTenDem(rs.getString(3));
            nv.setHo(rs.getString(4));
            nv.setGioiTinh(rs.getInt(5));
            nv.setNgaySinh(rs.getString(6));
            nv.setDiaChi(rs.getString(7));
            nv.setSdt(rs.getString(8));
            nv.setEmail(rs.getString(9));
            nv.setMatKhau(rs.getString(10));
//            nv.setNgayDiLam(rs.getString(11));
            nv.setGhiChu(rs.getString(11));
            ChucVu cv = new ChucVu();
            cv.setId(rs.getString("IdCv"));
            cv.setTenCv(rs.getString("TenCv"));
            nv.setChucVu(cv);
            listNhanVien.add(nv);
        }
        rs.close();
        ps.close();
        con.close();
        return listNhanVien;
    }

    public ArrayList<ViewModeNhanVien> getAll1() throws Exception {
        ArrayList<ViewModeNhanVien> listNhanVien = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "SELECT MaNV,Ten,TenDem,Ho, GioiTinh,NgaySinh,DiaChi,SDT,Email, MatKhau,GhiChu,ChucVu.Id as IdCv,ChucVu.TenCV as TenCv\n"
                + "                                 FROM    NhanVien left join ChucVu on NhanVien.Id_ChucVu=ChucVu.Id   \n"
                + "				where NhanVien.TrangThai=1";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ViewModeNhanVien nv = new ViewModeNhanVien();
            nv.setMaNv(rs.getString(1));
            nv.setTenNv(rs.getString(2));
            nv.setTenDem(rs.getString(3));
            nv.setHo(rs.getString(4));
            nv.setGioiTinh(rs.getInt(5));
            nv.setNgaySinh(rs.getString(6));
            nv.setDiaChi(rs.getString(7));
            nv.setSdt(rs.getString(8));
            nv.setEmail(rs.getString(9));
            nv.setMatKhau(rs.getString(10));
//            nv.setNgayDiLam(rs.getString(11));
            nv.setGhiChu(rs.getString(11));
            ChucVu cv = new ChucVu();
            cv.setId(rs.getString("IdCv"));
            cv.setTenCv(rs.getString("TenCv"));
            nv.setChucVu(cv);
            listNhanVien.add(nv);
        }
        rs.close();
        ps.close();
        con.close();
        return listNhanVien;
    }

    public Integer them(NhanVien nv) throws Exception {
        Connection con = DBContext.getConnection();
        String sql = "INSERT INTO NhanVien\n"
                + "                  (MaNV, Ten, TenDem, Ho, GioiTinh, NgaySinh, DiaChi, SDT, Email, MatKhau, GhiChu, Id_ChucVu, TrangThai)\n"
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,0)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, nv.getMaNv());
        ps.setString(2, nv.getTenNv());
        ps.setString(3, nv.getTenDem());
        ps.setString(4, nv.getHo());
        ps.setInt(5, nv.getGioiTinh());
        ps.setString(6, nv.getNgaySinh());
        ps.setString(7, nv.getDiaChi());
        ps.setString(8, nv.getSdt());
        ps.setString(9, nv.getEmail());
        ps.setString(10, nv.getMatKhau());
//        ps.setString(11, nv.getNgayDiLam());
        ps.setString(11, nv.getGhiChu());

        String IdCv = null;
        if (nv.getChucvu() != null) {
            IdCv = nv.getChucvu().getId();
        }
        ps.setString(12, IdCv);

        Integer ketQua = ps.executeUpdate();
        ps.close();
        con.close();
        return ketQua;
    }

    public Integer sua(NhanVien nv, String ma) throws Exception {
        Connection con = DBContext.getConnection();
//        String sql = "UPDATE NhanVien\n"
//                + "SET    Ten =?, TenDem =?, Ho =?, GioiTinh =?, NgaySinh =?, DiaChi =?, SDT =?,Email=?, MatKhau =?, GhiChu =?, Id_ChucVu =?, ngaybdlv = ?\n"
//                + "where MaNV=?";
        String sql = "update nhanvien set ten = ?, tendem = ?, ho = ?, gioitinh = ?, ngaysinh = ?, diachi = ?, sdt = ?, email = ?, matkhau = ?, ghichu = ?, id_chucvu = ? where manv = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(12, nv.getMaNv());
        ps.setString(1, nv.getTenNv());
        ps.setString(2, nv.getTenDem());
        ps.setString(3, nv.getHo());
        ps.setInt(4, nv.getGioiTinh());
        ps.setString(5, nv.getNgaySinh());
        ps.setString(6, nv.getDiaChi());
        ps.setString(7, nv.getSdt());
        ps.setString(8, nv.getEmail());
        ps.setString(9, nv.getMatKhau());

        ps.setString(10, nv.getGhiChu());
        String IdCv = null;

        if (nv.getChucvu() != null) {
            IdCv = nv.getChucvu().getId();
        }
        ps.setString(11, IdCv);
//        ps.setString(12, nv.getNgayDiLam());

        Integer ketQua = ps.executeUpdate();
        ps.close();
        con.close();
        return ketQua;
    }

    public List<ChucVu> getAllcv() {
        try {
            ArrayList<ChucVu> listChucVu = new ArrayList<>();
            Connection con = DBContext.getConnection();
            String sql = "SELECT  Id,MaCV, TenCV\n"
                    + "FROM     ChucVu WHERE TrangThai = '0'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChucVu cv = new ChucVu();
                cv.setId(rs.getString(1));
                cv.setMaCv(rs.getString(2));
                cv.setTenCv(rs.getString(3));
                listChucVu.add(cv);
            }
            rs.close();
            ps.close();
            con.close();
            return listChucVu;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Integer delete(String ma) throws Exception {
        Connection con = DBContext.getConnection();
        String sql = "UPDATE NhanVien\n"
                + "SET          TrangThai =1 \n"
                + "where MaNV=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, ma);
        Integer ketQua = ps.executeUpdate();
        ps.close();
        con.close();
        return ketQua;
    }

    public Integer delete1(String ma) throws Exception {
        Connection con = DBContext.getConnection();
        String sql = "UPDATE NhanVien\n"
                + "SET          TrangThai =0 \n"
                + "where MaNV=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, ma);
        Integer ketQua = ps.executeUpdate();
        ps.close();
        con.close();
        return ketQua;
    }

    public ArrayList<ViewModeNhanVien> timKiem(String ma) throws Exception {
        ArrayList<ViewModeNhanVien> listNhanVien = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "SELECT MaNV,Ten,TenDem,Ho, GioiTinh,NgaySinh,DiaChi,SDT,Email, MatKhau,GhiChu,ChucVu.Id as IdCv,ChucVu.TenCV as TenCv\n"
                + "                                 FROM    NhanVien left join ChucVu on NhanVien.Id_ChucVu=ChucVu.Id   \n"
                + "				where NhanVien.TrangThai=0 and MaNV like N'%'+?+'%'";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, ma);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ViewModeNhanVien nv = new ViewModeNhanVien();
            nv.setMaNv(rs.getString(1));
            nv.setTenNv(rs.getString(2));
            nv.setTenDem(rs.getString(3));
            nv.setHo(rs.getString(4));
            nv.setGioiTinh(rs.getInt(5));
            nv.setNgaySinh(rs.getString(6));
            nv.setDiaChi(rs.getString(7));
            nv.setSdt(rs.getString(8));
            nv.setEmail(rs.getString(9));
            nv.setMatKhau(rs.getString(10));
//            nv.setNgayDiLam(rs.getString(11));
            nv.setGhiChu(rs.getString(11));
            ChucVu cv = new ChucVu();
            cv.setId(rs.getString("IdCv"));
            cv.setTenCv(rs.getString("TenCv"));
            nv.setChucVu(cv);
            listNhanVien.add(nv);
        }
        rs.close();
        ps.close();
        con.close();
        return listNhanVien;
    }

    public ArrayList<ViewModeNhanVien> timKiemNhanVienNghiViec(String ma) throws Exception {
        ArrayList<ViewModeNhanVien> listNhanVien = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "SELECT MaNV,Ten,TenDem,Ho, GioiTinh,NgaySinh,DiaChi,SDT,Email, MatKhau,GhiChu,ChucVu.Id as IdCv,ChucVu.TenCV as TenCv\n"
                + "                                 FROM    NhanVien left join ChucVu on NhanVien.Id_ChucVu=ChucVu.Id   \n"
                + "				where NhanVien.TrangThai=1 and MaNV like N'%'+?+'%'";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, ma);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ViewModeNhanVien nv = new ViewModeNhanVien();
            nv.setMaNv(rs.getString(1));
            nv.setTenNv(rs.getString(2));
            nv.setTenDem(rs.getString(3));
            nv.setHo(rs.getString(4));
            nv.setGioiTinh(rs.getInt(5));
            nv.setNgaySinh(rs.getString(6));
            nv.setDiaChi(rs.getString(7));
            nv.setSdt(rs.getString(8));
            nv.setEmail(rs.getString(9));
            nv.setMatKhau(rs.getString(10));
//            nv.setNgayDiLam(rs.getString(11));
            nv.setGhiChu(rs.getString(11));
            ChucVu cv = new ChucVu();
            cv.setId(rs.getString("IdCv"));
            cv.setTenCv(rs.getString("TenCv"));
            nv.setChucVu(cv);
            listNhanVien.add(nv);
        }
        rs.close();
        ps.close();
        con.close();
        return listNhanVien;
    }
    

}
