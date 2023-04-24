/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.KhachHang;
import repository.KhachHangRepository;
import service.KhachHangService;
import viewModel.KhachHangViewModel;

/**
 *
 * @author phamtuyetnga
 */
public class KhachHangServiceImpl implements KhachHangService {

    private KhachHangRepository khachHnagRepository = new KhachHangRepository();

    @Override
    public ArrayList<KhachHangViewModel> getAllKhachHang() {
        try {
            return khachHnagRepository.getAll();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean themKhachHang(KhachHang kh) {
        try {
            return khachHnagRepository.addKH(kh);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean suaKhachHang(KhachHang kh) {
        try {
            return khachHnagRepository.suaKH(kh);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean xoaKhachHang(String ma) {
        try {
            return khachHnagRepository.xoaKH(ma);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<KhachHang> timKiemKhachHang(String ma) {
        try {
            return khachHnagRepository.timKiemKH(ma);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public ArrayList<KhachHangViewModel> getAllKhDaXoa() {
        try {
            return khachHnagRepository.getAllKhXoa();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<KhachHangViewModel> locKH(String ma) {
        try {
            return khachHnagRepository.loc(ma);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean checkTrungMa(String ma) {
        

            ArrayList<KhachHangViewModel> listKH = khachHnagRepository.getAll();
            for (KhachHangViewModel x : listKH) {
                if (x.getMaKhachHang().equals(ma)) {
                    return false;
                }
            }
            return true;
        }

    @Override
    public Integer updateSoLanMua(String maKH) {
        try {
            return khachHnagRepository.updateSoLanMua(maKH);
        } catch (SQLException ex) {
           return null;
        }
    }
    
    @Override
    public Integer lichSuDiem(Float soDiemSD, Float soDiemCong, String maKH, String maHD) {
        try {
            return khachHnagRepository.lichSuDiem(soDiemSD, soDiemCong, maKH, maHD);
        } catch (SQLException ex) {
            ex.printStackTrace();
           return null;
        }
    }

}
