/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import model.HoaDon;

/**
 *
 * @author phamtuyetnga
 */
public interface HoaDonService {

    Boolean saveHoaDon(HoaDon hoaDon);

    Boolean saveNgayNhan(HoaDon hoaDon);

    ArrayList<HoaDon> getList();

    Integer updateHD(HoaDon hoaDon);

    public ArrayList<HoaDon> getHistoryHD();

    public ArrayList<HoaDon> getDaThanhToan();

    public ArrayList<HoaDon> getChuaThanhToan();

    public ArrayList<HoaDon> locNgay(Date ngayTao, Date ngayToi);

    Integer updateTenKh(String tenKh, String maHd);

    String updateTrangThai(String maHD);

    int selectTrangThaiHoaDon(String maHD);

    ArrayList<HoaDon> getDoanhThu();

    ArrayList<HoaDon> soDonChuaTT();

    ArrayList<HoaDon> soDonDaTT();

    ArrayList<HoaDon> soLuongHD();

    Integer HDTT(String tenHt, BigDecimal tongTienHttt, String maHd, String maNv, Integer soLuong, BigDecimal tongTien, String maKh);
    
    Integer updateHdttOnl(String tenHt, String maHd, String maNv, Integer soLuong, BigDecimal tongTien, String maKh, Date ngayShip, Date ngayThanhToan, String diaChi, String sdtNguoiNhan, String sdtNguoiShip, String tenNguoiShip);
    
    Integer updateHdttOnl1(String maHD);

    ArrayList<HoaDon> getSoLuongHoaDonThang();
    
    ArrayList<HoaDon> getSoDonDaThanhToanThang();
    
    ArrayList<HoaDon> getSoDonChuaThanhToanThang();
    
    ArrayList<HoaDon> getDoanhThuThang();
    
    ArrayList<HoaDon> getSoLuongHoaDonNam();
    
    ArrayList<HoaDon> getSoDonDaThanhToanNam();
    
    ArrayList<HoaDon> getSoDonChuaThanhToanNam();
    
    ArrayList<HoaDon> getDoanhThuNam();
    
    public String updateTTDangGiao(String maHD);

    String selectMucGiamGiaBangGioHang(String maSp);
    
    public String updateTTDaHoan(String maHD);
    
    Integer updateHdttOnlThinh(String tenHt, BigDecimal tongTien, String maHd);
    //tesst
    Integer updateHdttOnlThinh1(HoaDon hd,String ma);
    // giao Hang
    Integer updateHdttOnl1GiaoHang(HoaDon hd,String ma);
    // xac Nhan
    Integer updateHdttOnl1XacNhan(HoaDon hd,String ma);
    
    
//1512
    // All tất cả hoá đơn to day
     ArrayList<HoaDon> getAllHoaDonHomNay();
     // All tất cả hoá đơn to day theo trạng thái
     ArrayList<HoaDon> getAllHoaDonHomNayLocCB(int trangThai);


}
