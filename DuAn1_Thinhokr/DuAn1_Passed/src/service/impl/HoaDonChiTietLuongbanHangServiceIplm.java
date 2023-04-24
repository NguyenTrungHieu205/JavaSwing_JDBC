/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import service.HoaDonChiTietLuongbanHangService;

import java.sql.SQLException;
import java.util.ArrayList;
import repository.HoaDonChiTietLuongbanHangRepository;
import service.HoaDonChiTietLuongbanHangService;
import viewModel.HoaDonChiTietLuongbanHangViewModel;
import viewModel.ImeiBanHang;
import viewModel.ImeiViewModel;
import viewModel.ViewSanPham;

/**
 *
 * @author Dell
 */
public class HoaDonChiTietLuongbanHangServiceIplm implements HoaDonChiTietLuongbanHangService {

    HoaDonChiTietLuongbanHangRepository hoaDonChiTietLuongbanHangRepos = new HoaDonChiTietLuongbanHangRepository();

    @Override
    public ArrayList<HoaDonChiTietLuongbanHangViewModel> getAllHDCT(String maHD) {
        try {
            return hoaDonChiTietLuongbanHangRepos.getAll(maHD);
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public String selectMucGiamGiaBangGioHang(String maSp) {

        try {
            return hoaDonChiTietLuongbanHangRepos.selectMucGiamGia(maSp);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addImeiKTMacDinh(String maSp, String maHD) {
        try {
            return hoaDonChiTietLuongbanHangRepos.addImeiKTMacDinh(maSp, maHD);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//    @Override
//    public boolean themSanPhamVaoHDCT(HoaDonChiTietLuongbanHangViewModel hd, String maHD) {
//        try {
//            return hoaDonChiTietLuongbanHangRepos.themVaoHDCT(hd, maHD);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
    @Override
    public boolean kiemTraCheckTrung(HoaDonChiTietLuongbanHangViewModel hd, String maHD) {
        try {
            return hoaDonChiTietLuongbanHangRepos.kiemTra(hd, maHD);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean suaSoLuongSanPhamTrongHDCT(HoaDonChiTietLuongbanHangViewModel hd, String idHD) {
        try {
            return hoaDonChiTietLuongbanHangRepos.suaSoLuong(hd, idHD);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateTruSoLuongSanPhamHDCT(String maSP, String maHD, int soLuongSanPhamTru) {

        try {
            return hoaDonChiTietLuongbanHangRepos.updateTruSoLuongSanPham(maSP, maHD, soLuongSanPhamTru);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<ViewSanPham> getAll() {
        try {
            return hoaDonChiTietLuongbanHangRepos.getAll();
        } catch (SQLException ex) {
            ex.printStackTrace();

            return null;
        }
    }

    @Override
    public boolean themHDCT(String hd) {
        try {
            return hoaDonChiTietLuongbanHangRepos.them(hd);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean themVaoHoaDonChiTiet(HoaDonChiTietLuongbanHangViewModel hd, String maHD) {
        try {
            return hoaDonChiTietLuongbanHangRepos.themVaoHDCT(hd, maHD);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean xoaSanPhamTrongHDCT(String maSP, String maHD) {
        try {
            return hoaDonChiTietLuongbanHangRepos.xoaSanPham(maSP, maHD);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean xoaAllSanPhamHDCT(String maHD) {
        try {
            return hoaDonChiTietLuongbanHangRepos.xoaAllSanPham(maHD);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean capNhatSoLuongChuotPhai(String maSp, String idHD, int soLuong) {
        try {
            return hoaDonChiTietLuongbanHangRepos.capNhatSoLuong(maSp, idHD, soLuong);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<ImeiBanHang> getAllListImeiBanHang(String maSp) {
        try {
            return hoaDonChiTietLuongbanHangRepos.getAllListImeiBH(maSp);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<ImeiViewModel> getAllListXoaImeiKt(String maSp, String maHD) {
        try {
            return hoaDonChiTietLuongbanHangRepos.getAllListImeiKtXoa(maSp, maHD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addImeiKT(ImeiViewModel imei, String maHD) {

        try {
            return hoaDonChiTietLuongbanHangRepos.addImeiKT(imei, maHD);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateTrangThaiImeiThemVaoGioHang(String imei) {
        try {
            return hoaDonChiTietLuongbanHangRepos.updateTrangThaiImeiThemVaoGioHang(imei);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteImeiKTMacDinh() {
        try {
            return hoaDonChiTietLuongbanHangRepos.deleteImeiKTMacDinh();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public ArrayList<HoaDonChiTietLuongbanHangViewModel> getAllImeiSanPhamJDialog(String maHD) {
        try {
            return hoaDonChiTietLuongbanHangRepos.getAllImeiSanPhamJDialog(maHD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<HoaDonChiTietLuongbanHangViewModel> getAllCBImeiSanPhamJDialog(String maHD) {
        try {
            return hoaDonChiTietLuongbanHangRepos.getAllCBImeiSanPhamJDialog(maHD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<HoaDonChiTietLuongbanHangViewModel> getAllImeiXoaSanPhamJDialog(String maHD) {
        try {
            return hoaDonChiTietLuongbanHangRepos.getImeiXoaSanPhamJDialog(maHD);
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    @Override
    public ArrayList<HoaDonChiTietLuongbanHangViewModel> getCBImeiXoaSanPhamJDialog(String maHD) {
        try {
            return hoaDonChiTietLuongbanHangRepos.getCBImeiXoaSanPhamJDialog(maHD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateThemSoLuongSanPham(int soLuong, String ma) {
        try {
            return hoaDonChiTietLuongbanHangRepos.updateThemSoLuongSanPham(soLuong, ma);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateXoaSoLuongSanPham(int soLuong, String ma) {
        try {
            return hoaDonChiTietLuongbanHangRepos.updateXoaSoLuongSanPham(soLuong, ma);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int getSoLuongTonSanPham(String maHD) {
        try {
            return hoaDonChiTietLuongbanHangRepos.getSoLuongTonSanPham(maHD);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    @Override
    public ArrayList<HoaDonChiTietLuongbanHangViewModel> getCBXoaSoLuongSanPhamJDialog(String maHD) {
        try {
            return hoaDonChiTietLuongbanHangRepos.getCBXoaSoLuongSanPhamJDialog(maHD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public int getSoLuongSanPhamTrongHDCT(String maSp, String maHD) {
        try {
            return hoaDonChiTietLuongbanHangRepos.getSoLuongSanPhamTrongHDCT(maSp, maHD);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int getSoLuongImeiKTHDCTJDialog(String maSP, String maHD) {
        try {
            return hoaDonChiTietLuongbanHangRepos.getSoLuongImeiKTHDCTJDialog(maSP, maHD);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public ArrayList<HoaDonChiTietLuongbanHangViewModel> getAllListImeiKTHDCTJDialog(String maSP, String maHD) {
        try {
            return hoaDonChiTietLuongbanHangRepos.getAllListImeiKTHDCTJDialog(maSP, maHD);
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
    
    @Override
    public boolean deleteImeiKTGioHang(String maImei) {
        try {
            return hoaDonChiTietLuongbanHangRepos.deleteImeiKTGioHang(maImei);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateTrangThaiImeiXoaTrongGioHangLenBangSanPham(String maImei) {
        try {
            return hoaDonChiTietLuongbanHangRepos.updateTrangThaiImeiXoaTrongGioHangLenBangSanPham(maImei);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public ArrayList<HoaDonChiTietLuongbanHangViewModel> getLocCBImeiSanPhamJDialog(String maSP, String maHD) {
        try {
            return hoaDonChiTietLuongbanHangRepos.getLocCBImeiSanPhamJDialog(maSP, maHD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<HoaDonChiTietLuongbanHangViewModel> getLocCBImeiXoaSanPhamJDialog(String maSP, String maHD) {
        try {
            return hoaDonChiTietLuongbanHangRepos.getLocCBImeiXoaSanPhamJDialog(maSP, maHD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

     @Override
    public ArrayList<HoaDonChiTietLuongbanHangViewModel> listImeiXoaHDCTJDialog(String maHD) {
        try {
            return hoaDonChiTietLuongbanHangRepos.listImeiXoaHDCTJDialog(maHD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteAllImeiKTGioHang(String maSp, String maHD) {
        try {
            return hoaDonChiTietLuongbanHangRepos.deleteAllImeiKTGioHang(maSp, maHD);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<HoaDonChiTietLuongbanHangViewModel> listSanPhamXoaHDCTJDialog(String maHD) {
        try {
            return hoaDonChiTietLuongbanHangRepos.listSanPhamXoaHDCTJDialog(maHD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteAllHDCT(String maHD) {
         try {
            return hoaDonChiTietLuongbanHangRepos.deleteAllHDCT(maHD);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
