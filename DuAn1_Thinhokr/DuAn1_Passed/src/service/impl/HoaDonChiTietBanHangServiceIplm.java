/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;
import java.util.ArrayList;
import model.HDChiTiet;
import repository.HoaDonChiTietBanHangRepository;
import service.HoaDonChiTietBanHangService;
import viewModel.HoaDonChiTietBanHangViewModel;
/**
 *
 * @author phamtuyetnga
 */
public class HoaDonChiTietBanHangServiceIplm implements HoaDonChiTietBanHangService{
    
    private HoaDonChiTietBanHangRepository hoaDonChiTietBanHangRepos = new HoaDonChiTietBanHangRepository();

    @Override
    public ArrayList<HoaDonChiTietBanHangViewModel> listHDCTBanHang(String idHD) {
        try {
            return hoaDonChiTietBanHangRepos.listHDCT(idHD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean themHDCT(String hd) {
        try {
            return hoaDonChiTietBanHangRepos.them(hd);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean themVaoHoaDonChiTiet(HoaDonChiTietBanHangViewModel hd, String maHD) {
        try {
            return hoaDonChiTietBanHangRepos.themVaoHDCT(hd, maHD);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean kiemTraCheckTrung(HoaDonChiTietBanHangViewModel hd, String maHD) {
        try {
            return hoaDonChiTietBanHangRepos.kiemTra(hd, maHD);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean suaSoLuongSanPhamTrongHDCT(HoaDonChiTietBanHangViewModel hd, String idHD) {
        try {
            return hoaDonChiTietBanHangRepos.suaSoLuong(hd, idHD);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean xoaSanPhamTrongHDCT(String maSP, String maHD) {
        try {
            return hoaDonChiTietBanHangRepos.xoaSanPham(maSP, maHD);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean xoaAllSanPhamHDCT(String maHD) {
        try {
            return hoaDonChiTietBanHangRepos.xoaAllSanPham(maHD);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateTruSoLuongSanPhamHDCT(String maSP, String maHD, int soLuongSanPhamTru) {
        try {
            return hoaDonChiTietBanHangRepos.updateTruSoLuongSanPham(maSP, maHD, soLuongSanPhamTru);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean capNhatSoLuongChuotPhai(String maSp, String idHD, int soLuong) {
         try {
            return hoaDonChiTietBanHangRepos.capNhatSoLuong(maSp, idHD, soLuong);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public Integer deleteHDCT(String maImei) {
         try {
            return hoaDonChiTietBanHangRepos.deleteHDCT(maImei);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
