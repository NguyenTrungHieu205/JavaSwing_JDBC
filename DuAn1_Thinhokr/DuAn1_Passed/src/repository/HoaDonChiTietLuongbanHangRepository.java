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
import model.GiamGia;
import ultility.DBContext;
import viewModel.HoaDonChiTietLuongbanHangViewModel;
import viewModel.ImeiBanHang;
import viewModel.ImeiViewModel;
import viewModel.ViewSanPham;

/**
 *
 * @author Dell
 */
public class HoaDonChiTietLuongbanHangRepository {

    public ArrayList<HoaDonChiTietLuongbanHangViewModel> getAll(String maHD) throws SQLException {
        ArrayList<HoaDonChiTietLuongbanHangViewModel> list = new ArrayList<>();
        String sql = "SELECT MaSP, TenSP,HDCT.SoLuong,  HDCT.DonGia, GiamGia.MucGiamGia, \n"
                + " (HDCT.DonGia*HDCT.SoLuong-GiamGia.MucGiamGia*HDCT.SoLuong), SANPHAM.TrangThai,\n"
                + " COUNT(IMEI_KT.MaImei)\n"
                + " FROM HDCT JOIN SANPHAM on \n"
                + " HDCT.Id_SanPham = SANPHAM.Id JOIN HoaDon ON HDCT.Id_HoaDon = HoaDon.Id JOIN\n"
                + " IMEI_KT on HDCT.Id = IMEI_KT.ID_HDCT\n"
                + " join GiamGiaSP on SANPHAM.Id=GiamGiaSP.ID_SanPham join GiamGia on \n"
                + " GiamGia.Id=GiamGiaSP.ID_GiamGia\n"
                + " WHERE HDCT.Id_HoaDon = (Select id from HoaDon where MaHD = ?) and HDCT.SoLuong >0\n"
                + " group by MaSP, TenSP,HDCT.SoLuong,  HDCT.DonGia,  GiamGia.MucGiamGia,\n"
                + " (HDCT.DonGia*HDCT.SoLuong-GiamGia.MucGiamGia*HDCT.SoLuong), SANPHAM.TrangThai";
        Connection con = DBContext.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maHD);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            HoaDonChiTietLuongbanHangViewModel hdct = new HoaDonChiTietLuongbanHangViewModel();
            hdct.setMaSanPham(rs.getString(1));
            hdct.setTenSanPham(rs.getString(2));
            hdct.setSoLuongSanPham(rs.getInt(3));
            hdct.setDonGia(rs.getBigDecimal(4));
            hdct.setMucGiamGia(rs.getString(5));
            hdct.setTongTien(rs.getBigDecimal(6));
            hdct.setDoiTra(rs.getInt(7));
            hdct.setSoLuongImeiSanPhamKT(rs.getInt(8));

            list.add(hdct);
        }
        return list;
    }

    // lấy ra mucứ giamr giá
    public String selectMucGiamGia(String maSp) throws SQLException {
        Connection con = DBContext.getConnection();
        String sql = "select MucGiamGia from GiamGia JOIN GiamGiaSP on "
                + " GiamGiaSP.ID_GiamGia = GiamGia.Id JOIN SANPHAM on"
                + " GiamGiaSP.ID_SanPham = SANPHAM.Id WHERE MaSP = ?";
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

    // add imei_KT mặc định
    public boolean addImeiKTMacDinh(String maSp, String maHD) throws SQLException {

        String sql = "Insert into IMEI_KT(MaImei, TrangThai, ID_HDCT) VALUES(null, 1, "
                + " (Select ID from HDCT where Id_SanPham =(Select Id from SANPHAM where MaSP = ?) "
                + " and Id_HoaDon =(Select Id from HoaDon where MaHD = ?)))\n";
        Connection con = DBContext.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maSp);
        ps.setString(2, maHD);

        return ps.executeUpdate() > 0;
    }

    public boolean themVaoHDCT(HoaDonChiTietLuongbanHangViewModel hd, String maHD) throws SQLException {// thêm sản phẩm vào giỏ hàng
        String sql = "INSERT INTO HDCT (SoLuong, DonGia, Id_SanPham, Id_HoaDon) VALUES(?,?,(SELECT Id FROM SANPHAM WHERE MaSP = ?),(SELECT Id From HoaDon WHERE MaHD =?))";
        Connection connection = DBContext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, hd.getSoLuongSanPham());
        ps.setBigDecimal(2, hd.getDonGia());
        ps.setString(3, hd.getMaSanPham());
        ps.setString(4, maHD);

        return ps.executeUpdate() > 0;
    }
//    public boolean themVaoHDCT(HoaDonChiTietBanHangViewModel hd, String maHD) throws SQLException {// thêm sản phẩm vào giỏ hàng
//        String sql = "INSERT INTO HDCT (SoLuong, DonGia, Id_SanPham, Id_HoaDon) VALUES(?,?,(SELECT Id FROM SANPHAM WHERE MaSP = ?),(SELECT Id From HoaDon WHERE MaHD =?))";
//        Connection connection = DBContext.getConnection();
//        PreparedStatement ps = connection.prepareStatement(sql);
//
//        ps.setInt(1, hd.getSoLuong());
//        ps.setBigDecimal(2, hd.getDonGia());
//        ps.setString(3, hd.getMaSanPham());
//        ps.setString(4, maHD);
//
//        return ps.executeUpdate() > 0;
//    }

    //check kiểm tra trùng mã hay k ở giỏ hàng chi tiết
    public boolean kiemTra(HoaDonChiTietLuongbanHangViewModel hd, String maHD) throws SQLException {
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
//     public boolean kiemTra(HoaDonChiTietBanHangViewModel hd, String maHD) throws SQLException { 
//        // kiểm tra sản phẩm đã có trong giỏ hàng hay chưa
//        // nếu có rồi gọi tới hàm suaSuoLuong() - còn chưa có thì tạo đối tuọng mới gọi tới themVaoHDCT()
//        String sql = "SELECT SoLuong, DonGia, Id_SanPham, Id_HoaDon FROM HDCT WHERE Id_SanPham = (SELECT Id FROM SANPHAM WHERE MaSP = ?) and Id_HoaDon = (select Id from HoaDon where MaHD = ?)";
//        Connection connection = DBContext.getConnection();
//        PreparedStatement ps = connection.prepareStatement(sql);
//        ps.setString(1, hd.getMaSanPham());
//        ps.setString(2, maHD);
//        ResultSet rs = ps.executeQuery();
//        if (rs.next()) {
//            return true;
//        }
//        return false;
//
//    }

    //check kiểm tra trùng mã hay k ở giỏ hàng chi tiết nếu trùng thì update số lượng (+ thêm số lượng)
    public boolean suaSoLuong(HoaDonChiTietLuongbanHangViewModel hd, String idHD) throws SQLException { // update số lượng sản phẩm trong giỏ hàng được cộng thêm
        String sql = "UPDATE HDCT set SoLuong = (SoLuong + ?) where  Id_SanPham = (SELECT Id FROM SANPHAM WHERE MaSP = ?) and Id_HoaDon = (select Id from HoaDon where MaHD = ?)";
        Connection connection = DBContext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, hd.getSoLuongSanPham());
        ps.setString(2, hd.getMaSanPham());
        ps.setString(3, idHD);

        return ps.executeUpdate() > 0;
    }
//     public boolean suaSoLuong(HoaDonChiTietLuongbanHangViewModel hd, String idHD) throws SQLException { // update số lượng sản phẩm trong giỏ hàng được cộng thêm
//        String sql = "UPDATE HDCT set SoLuong = (SoLuong + ?) where  Id_SanPham = (SELECT Id FROM SANPHAM WHERE MaSP = ?) and Id_HoaDon = (select Id from HoaDon where MaHD = ?)";
//        Connection connection = DBContext.getConnection();
//        PreparedStatement ps = connection.prepareStatement(sql);
//        ps.setInt(1, hd.getSoLuong());
//        ps.setString(2, hd.getMaSanPham());
//        ps.setString(3, idHD);
//
//        return ps.executeUpdate() > 0;
//    }

    // cập nhật lại ssos lượng sản phấm trong bảng sản phẩm sau khi thêm xuống giỏ hàng
    public boolean updateTruSoLuongSanPham(String maSP, String maHD, int soLuongSanPhamTru) throws SQLException { // update số lượng sản phẩm bị xoá đi trong giỏ hàng
        String sql = "UPDATE HDCT set SoLuong = (SoLuong - ?) where  Id_SanPham = (SELECT Id FROM SANPHAM WHERE MaSP = ?) and Id_HoaDon = (select Id from HoaDon where MaHD = ?)";
        Connection connection = DBContext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, soLuongSanPhamTru);
        ps.setString(2, maSP);
        ps.setString(3, maHD);

        return ps.executeUpdate() > 0;
    }
//     public boolean updateTruSoLuongSanPham(String maSP, String maHD, int soLuongSanPhamTru) throws SQLException { // update số lượng sản phẩm bị xoá đi trong giỏ hàng
//        String sql = "UPDATE HDCT set SoLuong = (SoLuong - ?) where  Id_SanPham = (SELECT Id FROM SANPHAM WHERE MaSP = ?) and Id_HoaDon = (select Id from HoaDon where MaHD = ?)";
//        Connection connection = DBContext.getConnection();
//        PreparedStatement ps = connection.prepareStatement(sql);
//        ps.setInt(1, soLuongSanPhamTru);
//        ps.setString(2, maSP);
//        ps.setString(3, maHD);
//
//        return ps.executeUpdate() > 0;
//    }

    // lấy ra all sản phẩm bảng sản phẩm bán hàng
    public ArrayList<ViewSanPham> getAll() throws SQLException {
        ArrayList<ViewSanPham> listSp = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "select MaSP, TenSP, TenLH, TenRAM, TenPin, TenDL, TenMH, SoLuongTon, "
                + " GiaBan, SANPHAM.TrangThai from SANPHAM join PhanLoaiHang on SANPHAM.Id_PhanLoai = "
                + " PhanLoaiHang.Id join RAM on SANPHAM.Id_Ram = RAM.Id join Pin on SANPHAM.Id_Pin = Pin.Id "
                + " join DungLuong on SANPHAM.Id_DLuong = DungLuong.Id join ManHinh on SANPHAM.Id_ManHinh = ManHinh.Id "
                + " WHERE SANPHAM.TrangThai = 0 AND SANPHAM.SoLuongTon>0";
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

    public boolean them(String hd) throws SQLException { // tạo hoá dơn chi tiết
        String sql = "INSERT INTO HDCT (Id_HoaDon) VALUES((SELECT Id From HoaDon WHERE MaHD =?))";
        Connection connection = DBContext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, hd);

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

    public boolean capNhatSoLuong(String maSp, String idHD, int soLuong) throws SQLException { // update số lượng sản phẩm trong giỏ hàng được cộng thêm
        String sql = "UPDATE HDCT set SoLuong = (SoLuong + ?) where  Id_SanPham = (SELECT Id FROM SANPHAM WHERE MaSP = ?) and Id_HoaDon = (select Id from HoaDon where MaHD = ?)";
        Connection connection = DBContext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, soLuong);
        ps.setString(2, maSp);
        ps.setString(3, idHD);

        return ps.executeUpdate() > 0;
    }

    // list imei bán hàng
    public ArrayList<ImeiBanHang> getAllListImeiBH(String maSp) throws SQLException {
        ArrayList<ImeiBanHang> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "SELECT MaImei, MaSp from IMEI JOIN SANPHAM on IMEI.Id_Sp = SANPHAM.Id WHERE MaSP = ? and IMEI.TrangThai = 0";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maSp);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            ImeiBanHang imei = new ImeiBanHang();
            imei.setMaImei(rs.getString(1));
            imei.setMaSanPham(rs.getString(2));

            list.add(imei);
        }

        rs.close();
        ps.close();
        con.close();

        return list;

    }

    // list imei xoá Imei_KT
    public ArrayList<ImeiViewModel> getAllListImeiKtXoa(String maSp, String maHD) throws SQLException {
        ArrayList<ImeiViewModel> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "SELECT MaImei, MaSp from IMEI_KT JOIN HDCT on IMEI_KT.ID_HDCT = HDCT.Id JOIN HoaDon "
                + "     on HDCT.Id_HoaDon = HoaDon.Id\n"
                + " jOIN SANPHAM on HDCT.Id_SanPham = SANPHAM.Id WHERE  MaSP = ? and MaHD = ? \n"
                + " GROUP BY MaImei, MaSp\n"
                + " HAVING COUNT(MaImei) > 0";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maSp);
        ps.setString(2, maHD);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            ImeiViewModel imei = new ImeiViewModel();
            imei.setMaImei(rs.getString(1));
            imei.setMaSanPham(rs.getString(2));

            list.add(imei);
        }

        rs.close();
        ps.close();
        con.close();

        return list;

    }

    //Insert into IMEI_KT(MaImei, TrangThai, ID_HDCT) VALUES('imei 103', 1, (Select ID from HDCT where Id_SanPham =(Select Id from SANPHAM where MaSP = 'SP1') and Id_HoaDon =(Select Id from HoaDon where MaHD = 'HDOFF41')))
    public boolean addImeiKT(ImeiViewModel imei, String maHD) throws SQLException {// xoá tất cả sản phẩm trong giỏ hàng
        String sql = "Insert into IMEI_KT(MaImei, TrangThai, ID_HDCT) VALUES(?, ?, "
                + "(Select ID from HDCT where  Id_SanPham =(Select Id from SANPHAM where MaSP = ?) and"
                + " Id_HoaDon =(Select Id from HoaDon where MaHD = ?)))\n";
        Connection connection = DBContext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, imei.getMaImei());
        ps.setInt(2, imei.getTrangThai());
        ps.setString(3, imei.getMaSanPham());
        ps.setString(4, maHD);

        return ps.executeUpdate() > 0;
    }

    //update trang thái imei
    public boolean updateTrangThaiImeiThemVaoGioHang(String maImei) throws SQLException {
        String sql = "UPDATE IMEI set TrangThai = 5 WHERE MaImei = ?";
        Connection connection = DBContext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, maImei);

        return ps.executeUpdate() > 0;
    }

    public boolean deleteImeiKTMacDinh() throws SQLException {

        String sql = "delete from IMEI_KT where TrangThai = 1";
        Connection con = DBContext.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        return ps.executeUpdate() > 0;
    }
    //load table thêm ime + load cb thêm imei

    public ArrayList<HoaDonChiTietLuongbanHangViewModel> getAllImeiSanPhamJDialog(String maHD) throws SQLException {
        ArrayList<HoaDonChiTietLuongbanHangViewModel> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "select IMEI.MaImei, MaSP, TenSP from HDCT JOIN HoaDon on"
                + " HDCT.Id_HoaDon = HoaDon.Id JOIN SANPHAM on HDCT.Id_SanPham = SANPHAM.Id JOIN IMEI on SANPHAM.Id = IMEI.Id_Sp\n"
                + " WHERE HDCT.Id_HoaDon = (Select ID from HoaDon where MaHD = ?) and IMEI.TrangThai = 0";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maHD);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            HoaDonChiTietLuongbanHangViewModel hd = new HoaDonChiTietLuongbanHangViewModel();
            hd.setMaImei(rs.getString(1));
            hd.setMaSanPham(rs.getString(2));
            hd.setTenSanPham(rs.getString(3));
            list.add(hd);
        }

        rs.close();
        ps.close();
        con.close();

        return list;

    }

    public ArrayList<HoaDonChiTietLuongbanHangViewModel> getAllCBImeiSanPhamJDialog(String maHD) throws SQLException {
        ArrayList<HoaDonChiTietLuongbanHangViewModel> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "select MaSP from HDCT JOIN HoaDon on HDCT.Id_HoaDon = HoaDon.Id JOIN SANPHAM on "
                + " HDCT.Id_SanPham = SANPHAM.Id JOIN IMEI on SANPHAM.Id = IMEI.Id_Sp\n"
                + " WHERE HDCT.Id_HoaDon = (Select ID from HoaDon where MaHD = ?) and IMEI.TrangThai = 0 \n"
                + " GROUP by  MaSP";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maHD);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            HoaDonChiTietLuongbanHangViewModel hd = new HoaDonChiTietLuongbanHangViewModel();
            hd.setMaSanPham(rs.getString(1));
            list.add(hd);
        }

        rs.close();
        ps.close();
        con.close();

        return list;

    }

    //load table Xoá ime + load cb thêm imei
    public ArrayList<HoaDonChiTietLuongbanHangViewModel> getImeiXoaSanPhamJDialog(String maHD) throws SQLException {
        ArrayList<HoaDonChiTietLuongbanHangViewModel> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "SELECT MaImei, MaSp, TenSP from IMEI_KT JOIN HDCT on IMEI_KT.ID_HDCT = HDCT.Id JOIN HoaDon\n"
                + "                 on HDCT.Id_HoaDon = HoaDon.Id\n"
                + "                 jOIN SANPHAM on HDCT.Id_SanPham = SANPHAM.Id WHERE  MaHD = ?\n"
                + "                 GROUP BY MaImei, MaSp, TenSP\n"
                + "                 HAVING COUNT(MaImei) > 0";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maHD);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            HoaDonChiTietLuongbanHangViewModel hd = new HoaDonChiTietLuongbanHangViewModel();
            hd.setMaImei(rs.getString(1));
            hd.setMaSanPham(rs.getString(2));
            hd.setTenSanPham(rs.getString(3));
            list.add(hd);
        }

        rs.close();
        ps.close();
        con.close();

        return list;

    }

    public ArrayList<HoaDonChiTietLuongbanHangViewModel> getCBImeiXoaSanPhamJDialog(String maHD) throws SQLException {
        ArrayList<HoaDonChiTietLuongbanHangViewModel> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "SELECT  MaSp from IMEI_KT JOIN HDCT on IMEI_KT.ID_HDCT = HDCT.Id JOIN HoaDon\n"
                + "                 on HDCT.Id_HoaDon = HoaDon.Id\n"
                + "                 jOIN SANPHAM on HDCT.Id_SanPham = SANPHAM.Id WHERE  MaHD = ?\n"
                + "                 GROUP BY MaSp\n"
                + "                 HAVING COUNT(MaImei) > 0";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maHD);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            HoaDonChiTietLuongbanHangViewModel hd = new HoaDonChiTietLuongbanHangViewModel();
            hd.setMaSanPham(rs.getString(1));
            list.add(hd);
        }

        rs.close();
        ps.close();
        con.close();

        return list;

    }

//    lấy ra list mã sp trong hdct
    public ArrayList<HoaDonChiTietLuongbanHangViewModel> getCBXoaSoLuongSanPhamJDialog(String maHD) throws SQLException {
        ArrayList<HoaDonChiTietLuongbanHangViewModel> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "SELECT MaSP from HDCT JOIN SANPHAM ON HDCT.Id_SanPham = SANPHAM.Id\n"
                + "WHERE HDCT.Id_HoaDon = (select ID from HoaDon where MaHD = ?) GROUP BY MaSP";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maHD);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            HoaDonChiTietLuongbanHangViewModel hd = new HoaDonChiTietLuongbanHangViewModel();
            hd.setMaSanPham(rs.getString(1));
            list.add(hd);
        }

        rs.close();
        ps.close();
        con.close();

        return list;

    }

    // lấy ra số lượng sản phẩm trong kho
    public int getSoLuongTonSanPham(String maHD) throws SQLException {
        String sql = "SELECT SoLuongTon from SANPHAM WHERE MaSP = ?";
        Connection con = DBContext.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maHD);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {

            return rs.getInt(1);
        }
        return -1;
    }

    // lấy ra số lượng sản phẩm trong kho
    public int getSoLuongSanPhamTrongHDCT(String maSp, String maHD) throws SQLException {
        String sql = "SELECT HDCT.SoLuong from HDCT JOIN SANPHAM on HDCT.Id_SanPham "
                + " = SANPHAM.Id WHERE MaSP = ? and HDCT.Id_HoaDon = (select ID from HoaDon where MaHD = ?)";
        Connection con = DBContext.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maSp);
        ps.setString(2, maHD);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {

            return rs.getInt(1);
        }
        return -1;
    }

    //    lấy ra số lượng imei_KT trong HDCT
    public int getSoLuongImeiKTHDCTJDialog(String maSP, String maHD) throws SQLException {
//        ArrayList<HoaDonChiTietLuongbanHangViewModel> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "SELECT  \n"
                + "          COUNT(IMEI_KT.MaImei) \n"
                + "          FROM HDCT JOIN SANPHAM on \n"
                + "          HDCT.Id_SanPham = SANPHAM.Id JOIN HoaDon ON HDCT.Id_HoaDon = HoaDon.Id JOIN \n"
                + "          IMEI_KT on HDCT.Id = IMEI_KT.ID_HDCT \n"
                + "          join GiamGiaSP on SANPHAM.Id=GiamGiaSP.ID_SanPham join GiamGia on \n"
                + "          GiamGia.Id=GiamGiaSP.ID_GiamGia \n"
                + "          WHERE       HDCT.Id_SanPham = (Select id from SANPHAM where MaSP = ?) and "
                + "          HDCT.Id_HoaDon = (Select id from HoaDon where MaHD = ?)  and HDCT.SoLuong >0 ";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maSP);
        ps.setString(2, maHD);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt(1);
        }

        rs.close();
        ps.close();
        con.close();

        return -1;

    }

    //    lấy ra list imei_KT trong HDCT
    public ArrayList<HoaDonChiTietLuongbanHangViewModel> getAllListImeiKTHDCTJDialog(String maSP, String maHD) throws SQLException {
        ArrayList<HoaDonChiTietLuongbanHangViewModel> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "SELECT  IMEI_KT.MaImei\n"
                + "      FROM HDCT JOIN SANPHAM on \n"
                + "      HDCT.Id_SanPham = SANPHAM.Id JOIN HoaDon ON HDCT.Id_HoaDon = HoaDon.Id JOIN \n"
                + "      IMEI_KT on HDCT.Id = IMEI_KT.ID_HDCT \n"
                + "      join GiamGiaSP on SANPHAM.Id=GiamGiaSP.ID_SanPham join GiamGia on \n"
                + "      GiamGia.Id=GiamGiaSP.ID_GiamGia \n"
                + "      WHERE       HDCT.Id_SanPham = (Select id from SANPHAM where MaSP = ?) and \n"
                + "      HDCT.Id_HoaDon = (Select id from HoaDon where MaHD = ?)   and IMEI_KT.TrangThai =0 ";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maSP);
        ps.setString(2, maHD);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            HoaDonChiTietLuongbanHangViewModel hd = new HoaDonChiTietLuongbanHangViewModel();
            hd.setMaImei(rs.getString(1));

            list.add(hd);
        }

        rs.close();
        ps.close();
        con.close();

        return list;

    }

    //load table Xoá ime + load cb thêm imei
    public ArrayList<HoaDonChiTietLuongbanHangViewModel> getAllCBImeiXoaSanPhamJDialog(String maSP, String maHD) throws SQLException {
        ArrayList<HoaDonChiTietLuongbanHangViewModel> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maHD);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            HoaDonChiTietLuongbanHangViewModel hd = new HoaDonChiTietLuongbanHangViewModel();
            hd.setMaImei(rs.getString(1));
            hd.setMaSanPham(rs.getString(2));
            hd.setTenSanPham(rs.getString(3));
            list.add(hd);
        }

        rs.close();
        ps.close();
        con.close();

        return list;

    }

    // update table sản phẩm trong luồng bán hàng khi xoá nhiều số luộng sản phẩm
    public boolean updateThemSoLuongSanPham(int soLuong, String ma) throws SQLException {
        String sql = "update SANPHAM set SoLuongTon = (SoLuongTon + ?) WHERE MaSP = ?";
        Connection con = DBContext.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, soLuong);
        ps.setString(2, ma);

        return ps.executeUpdate() > 0;
    }

    // update table sản phẩm trong luồng bán hàng khi xoá nhiều số luộng sản phẩm
    public boolean updateXoaSoLuongSanPham(int soLuong, String ma) throws SQLException {
        String sql = "update SANPHAM set SoLuongTon = (SoLuongTon - ?) WHERE MaSP = ?";
        Connection con = DBContext.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, soLuong);
        ps.setString(2, ma);

        return ps.executeUpdate() > 0;
    }

    // xoá imei_KT trong giỏ hàng
    public boolean deleteImeiKTGioHang(String maImei) throws SQLException {
        String sql = "DELETE from IMEI_KT WHERE MaImei = ?";
        Connection connection = DBContext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, maImei);

        return ps.executeUpdate() > 0;
    }

    // update imei trong giỏ hàng lên giỏ hàng
    public boolean updateTrangThaiImeiXoaTrongGioHangLenBangSanPham(String maImei) throws SQLException {
        String sql = "UPDATE IMEI set TrangThai = 0 WHERE MaImei = ?";
        Connection connection = DBContext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, maImei);

        return ps.executeUpdate() > 0;
    }
    
    // 1312
    // lọc cb imei bảng imei_KT
    public ArrayList<HoaDonChiTietLuongbanHangViewModel> getLocCBImeiXoaSanPhamJDialog(String maSP, String maHD) throws SQLException {
        ArrayList<HoaDonChiTietLuongbanHangViewModel> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "SELECT MaImei, MaSp, TenSP from IMEI_KT JOIN HDCT on IMEI_KT.ID_HDCT = HDCT.Id JOIN HoaDon\n"
                + "                     on HDCT.Id_HoaDon = HoaDon.Id\n"
                + "                     jOIN SANPHAM on HDCT.Id_SanPham = SANPHAM.Id WHERE  MaSP = ? and  MaHD = ?\n"
                + "                     GROUP BY MaImei, MaSp, TenSP\n"
                + "                     HAVING COUNT(MaImei) > 0";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maSP);
        ps.setString(2, maHD);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            HoaDonChiTietLuongbanHangViewModel hd = new HoaDonChiTietLuongbanHangViewModel();
            hd.setMaImei(rs.getString(1));
            hd.setMaSanPham(rs.getString(2));
            hd.setTenSanPham(rs.getString(3));
            list.add(hd);
        }

        rs.close();
        ps.close();
        con.close();

        return list;

    }

    // lọc cb imei bảng imei bán hàng
    public ArrayList<HoaDonChiTietLuongbanHangViewModel> getLocCBImeiSanPhamJDialog(String maSP ,String maHD) throws SQLException {
        ArrayList<HoaDonChiTietLuongbanHangViewModel> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "select IMEI.MaImei, MaSP, TenSP from HDCT JOIN HoaDon on\n"
                + "           HDCT.Id_HoaDon = HoaDon.Id JOIN SANPHAM on HDCT.Id_SanPham = SANPHAM.Id JOIN IMEI on SANPHAM.Id = IMEI.Id_Sp\n"
                + "           WHERE HDCT.Id_SanPham = (Select ID from SANPHAM where MaSP = ?)\n"
                + "            and  HDCT.Id_HoaDon = (Select ID from HoaDon where MaHD = ?) and  IMEI.TrangThai = 0";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maSP);
        ps.setString(2, maHD);


        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            HoaDonChiTietLuongbanHangViewModel hd = new HoaDonChiTietLuongbanHangViewModel();
            hd.setMaImei(rs.getString(1));
            hd.setMaSanPham(rs.getString(2));
            hd.setTenSanPham(rs.getString(3));
            list.add(hd);
        }

        rs.close();
        ps.close();
        con.close();

        return list;

    }
    // lấy ra list imei ở bảng imei_KT để xoá 
    public ArrayList<HoaDonChiTietLuongbanHangViewModel> listImeiXoaHDCTJDialog(String maHD) throws SQLException {
        ArrayList<HoaDonChiTietLuongbanHangViewModel> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = " SELECT MaImei, MaSp, TenSP from IMEI_KT JOIN HDCT on IMEI_KT.ID_HDCT = HDCT.Id JOIN HoaDon\n"
                + "            on HDCT.Id_HoaDon = HoaDon.Id\n"
                + "            jOIN SANPHAM on HDCT.Id_SanPham = SANPHAM.Id WHERE  MaHD = ?"
                + " GROUP BY MaImei, MaSp, TenSP";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maHD);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            HoaDonChiTietLuongbanHangViewModel hd = new HoaDonChiTietLuongbanHangViewModel();
            hd.setMaImei(rs.getString(1));
            hd.setMaSanPham(rs.getString(2));
            hd.setTenSanPham(rs.getString(3));
            list.add(hd);
        }

        rs.close();
        ps.close();
        con.close();

        return list;

    }

    // xoá tất cả imei trong hoá đơn chi tiết
    public boolean deleteAllImeiKTGioHang(String maSp, String maHD) throws SQLException {
        String sql = "DELETE from IMEI_KT WHERE IMEI_KT.ID_HDCT = "
                + " (select ID from HDCT where Id_SanPham = (select ID from SANPHAM where MaSP = ?) \n"
                + " and Id_HoaDon = (Select ID from HoaDon where MaHD = ?) )";
        Connection connection = DBContext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, maSp);
        ps.setString(2, maHD);

        return ps.executeUpdate() > 0;
    }

    // lấy ra list sản phẩm và số lượng trong giỏ hàng
    public ArrayList<HoaDonChiTietLuongbanHangViewModel> listSanPhamXoaHDCTJDialog(String maHD) throws SQLException {
        ArrayList<HoaDonChiTietLuongbanHangViewModel> list = new ArrayList<>();
        Connection con = DBContext.getConnection();
        String sql = "SELECT MaSP, TenSP, HDCT.SoLuong\n"
                + " FROM HDCT JOIN SANPHAM on HDCT.Id_SanPham = SANPHAM.Id JOIN HoaDon ON HDCT.Id_HoaDon = HoaDon.Id join GiamGiaSP \n"
                + " on SANPHAM.Id=GiamGiaSP.ID_SanPham join GiamGia on GiamGia.Id=GiamGiaSP.ID_GiamGia \n"
                + " WHERE HDCT.Id_HoaDon = (Select id from HoaDon where MaHD = ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maHD);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            HoaDonChiTietLuongbanHangViewModel hd = new HoaDonChiTietLuongbanHangViewModel();
            hd.setMaSanPham(rs.getString(1));
            hd.setTenSanPham(rs.getString(2));
            hd.setSoLuongSanPham(rs.getInt(3));
            list.add(hd);
        }

        rs.close();
        ps.close();
        con.close();

        return list;

    }
    
    // delete all HDCT
    public boolean deleteAllHDCT(String maHD) throws SQLException {
        String sql = "DELETE from HDCT where Id_HoaDon = (select Id from HoaDon where MaHD = ?)";
        Connection connection = DBContext.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, maHD);

        return ps.executeUpdate() > 0;
    }
    
    

}
