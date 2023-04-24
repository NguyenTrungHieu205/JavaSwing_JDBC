/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.HoaDon;
import repository.HoaDonRepository;
import service.HoaDonService;
import service.HoaDonService;

/**
 *
 * @author phamtuyetnga
 */
public class HoaDonServiceImpl implements HoaDonService {

    private final HoaDonRepository hoaDonRepo = new HoaDonRepository();

    @Override
    public Boolean saveHoaDon(HoaDon hoaDon) {
        return hoaDonRepo.saveHoaDon(hoaDon);
    }

    @Override
    public ArrayList<HoaDon> getList() {
        return hoaDonRepo.getListHoaDon();
    }

    @Override
    public Integer updateHD(HoaDon hoaDon) {
        try {
            return hoaDonRepo.updateHoaDon(hoaDon);
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public ArrayList<HoaDon> getHistoryHD() {
        return hoaDonRepo.getHisroryHD();
    }

    @Override
    public ArrayList<HoaDon> getDaThanhToan() {
        return hoaDonRepo.getDaThanhToan();
    }

    @Override
    public ArrayList<HoaDon> getChuaThanhToan() {
        return hoaDonRepo.getChuaThanhToan();
    }

    @Override
    public Integer updateTenKh(String tenKh, String maHd) {
        try {
            return hoaDonRepo.updateTenKh(tenKh, maHd);
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public String updateTrangThai(String maHD) {
        return hoaDonRepo.updateTinhTrang(maHD);
    }

    @Override
    public String updateTTDangGiao(String maHD) {
        return hoaDonRepo.updateTTDangGiao(maHD);
    }

    @Override
    public int selectTrangThaiHoaDon(String maHD) {
        try {
            return hoaDonRepo.trangThaiHoaDon(maHD);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    @Override
    public Boolean saveNgayNhan(HoaDon hoaDon) {
        try {
            return hoaDonRepo.saveNgayNhan(hoaDon);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ArrayList<HoaDon> getDoanhThu() {
        try {
            return hoaDonRepo.getDoanhThuHomNay();
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public ArrayList<HoaDon> soDonChuaTT() {
        try {
            return hoaDonRepo.getSoDonChuaThanhToanHomNay();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ArrayList<HoaDon> soDonDaTT() {
        try {
            return hoaDonRepo.getSoDonDaThanhToanHomNay();
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public ArrayList<HoaDon> soLuongHD() {
        try {
            return hoaDonRepo.getSoLuongHoaDonHomNay();
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public Integer HDTT(String tenHt, BigDecimal tongTienHttt, String maHd, String maNv, Integer soLuong, BigDecimal tongTien, String maKh) {
        try {
            return hoaDonRepo.updateHdtt(tenHt, tongTienHttt, maHd, maNv, soLuong, tongTien, maKh);
        } catch (SQLException ex) {
            return null;
        }
    }
    
    @Override
    public Integer updateHdttOnl(String tenHt, String maHd, String maNv, Integer soLuong, BigDecimal tongTien, String maKh, Date ngayShip, Date ngayThanhToan, String diaChi, String sdtNguoiNhan, String sdtNguoiShip, String tenNguoiShip) {
        try {
            return hoaDonRepo.updateHdttOnl(tenHt, maHd, maNv, soLuong, tongTien, maKh, ngayShip, ngayThanhToan, diaChi, sdtNguoiNhan, sdtNguoiShip, tenNguoiShip);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Integer updateHdttOnl1(String maHD) {
        try {
            return hoaDonRepo.updateHdttOnl1(maHD);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<HoaDon> locNgay(Date ngayTao, Date ngayToi) {
        return hoaDonRepo.locNgay(ngayTao, ngayToi);
    }

    @Override
    public ArrayList<HoaDon> getSoLuongHoaDonThang() {
        try {
            return hoaDonRepo.getSoLuongHoaDonThang();
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public ArrayList<HoaDon> getSoDonDaThanhToanThang() {
        try {
            return hoaDonRepo.getSoDonDaThanhToanThang();
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public ArrayList<HoaDon> getSoDonChuaThanhToanThang() {
        try {
            return hoaDonRepo.getSoDonChuaThanhToanThang();
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public ArrayList<HoaDon> getDoanhThuThang() {
        try {
            return hoaDonRepo.getDoanhThuThang();
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public ArrayList<HoaDon> getSoLuongHoaDonNam() {
        try {
            return hoaDonRepo.getSoLuongHoaDonNam();
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public ArrayList<HoaDon> getSoDonDaThanhToanNam() {
        try {
            return hoaDonRepo.getSoDonDaThanhToanNam();
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public ArrayList<HoaDon> getSoDonChuaThanhToanNam() {
        try {
            return hoaDonRepo.getSoDonChuaThanhToanNam();
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public ArrayList<HoaDon> getDoanhThuNam() {
        try {
            return hoaDonRepo.getDoanhThuNam();
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public String selectMucGiamGiaBangGioHang(String maSp) {
        try {
            return hoaDonRepo.selectMucGiamGia(maSp);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String updateTTDaHoan(String maHD) {
        try {
            return hoaDonRepo.updateTTDaHoan(maHD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Integer updateHdttOnlThinh(String tenHt, BigDecimal tongTien, String maHd) {
         try {
            return hoaDonRepo.updateHdttOnlThinh(tenHt, tongTien, maHd);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer updateHdttOnlThinh1(HoaDon hd,String ma) {
        try {
           return hoaDonRepo.updateHdttOnlThinh1(hd,ma);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer updateHdttOnl1GiaoHang(HoaDon hd,String ma) {
        try {
           return hoaDonRepo.updateHdttOnl1GiaoHang(hd, ma);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer updateHdttOnl1XacNhan(HoaDon hd,String ma) {
       try {
           return hoaDonRepo.updateHdttOnl1XacNhan(hd,ma);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
 @Override
    public ArrayList<HoaDon> getAllHoaDonHomNay() {
        try {
            return hoaDonRepo.getAllHoaDonHomNay();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<HoaDon> getAllHoaDonHomNayLocCB(int trangThai) {
        try {
            return hoaDonRepo.getAllHoaDonHomNayLocCB(trangThai);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
