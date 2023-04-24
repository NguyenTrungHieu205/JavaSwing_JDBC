/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.ArrayList;
import viewModel.HoaDonChiTietBanHangViewModel;

/**
 *
 * @author phamtuyetnga
 */
public interface HoaDonChiTietBanHangService {

    boolean themHDCT(String hd);

    ArrayList<HoaDonChiTietBanHangViewModel> listHDCTBanHang(String idHD);

    boolean themVaoHoaDonChiTiet(HoaDonChiTietBanHangViewModel hd, String maHD);

    boolean kiemTraCheckTrung(HoaDonChiTietBanHangViewModel hd, String maHD);

    boolean suaSoLuongSanPhamTrongHDCT(HoaDonChiTietBanHangViewModel hd, String idHD);

    boolean xoaSanPhamTrongHDCT(String maSP, String maHD);

    boolean xoaAllSanPhamHDCT(String maHD);

    boolean updateTruSoLuongSanPhamHDCT(String maSP, String maHD, int soLuongSanPhamTru);

    boolean capNhatSoLuongChuotPhai(String maSp, String idHD, int soLuong);

    Integer deleteHDCT(String maImei);
}
