/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.ArrayList;
import java.util.List;

import repository.ImeiRepository;
import service.ImeiService;
import viewModel.ImeiViewModel;
import viewModel.SanPhamImeiViewModel;

/**
 *
 * @author Admin
 */
public class ImeiServiceImpl implements ImeiService {

    private final ImeiRepository imeiRepository = new ImeiRepository();

    @Override
    public ArrayList<ImeiViewModel> getAll() {
        try {
            return imeiRepository.getAllImei();
        } catch (Exception ex) {
            ex.printStackTrace();

            return null;
        }
    }

    @Override
    public ArrayList<ImeiViewModel> getAllXoa() {
        try {
            return imeiRepository.getAllXoa();
        } catch (Exception ex) {
            ex.printStackTrace();

            return null;
        }
    }

    @Override
    public boolean addImei(ImeiViewModel imei) {
        try {
            return imeiRepository.addImei(imei);
        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }
    }

    @Override
    public boolean update(ImeiViewModel imei) {
        try {
            return imeiRepository.update(imei);
        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }
    }

    @Override
    public boolean updateTT1(String maImei) {
        try {
            return imeiRepository.updateTT1(maImei);
        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }
    }

    @Override
    public boolean updateTT5(String maImei) {
        try {
            return imeiRepository.updateTT5(maImei);
        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }
    }

    @Override
    public boolean updateTT0(String maImei) {
        try {
            return imeiRepository.updateTT0(maImei);
        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }
    }

    @Override
    public boolean updateTTXoaAll(String maSp) {
        try {
            return imeiRepository.updateTTXoaAll(maSp);
        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }
    }

    @Override
    public boolean deleteImei(String imei) {
        try {
            return imeiRepository.delete(imei);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Integer hoanTac(ImeiViewModel imei) {
        try {
            return imeiRepository.hoanTac(imei);
        } catch (Exception ex) {
            ex.printStackTrace();

            return null;
        }
    }

    @Override
    public ArrayList<SanPhamImeiViewModel> selectAllSanPhamBangSanPham() {
        try {
            return imeiRepository.selectAllSanPham();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<SanPhamImeiViewModel> selecttimKiemSanPhamBangSanPham(String ma) {
        try {
            return imeiRepository.selecttimKiemBangSanPham(ma);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<ImeiViewModel> selecttimKiemImeiBangImei(String ma) {
        try {
            return imeiRepository.selecttimKiemBangImei(ma);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getAllMaSP(String ma) {
        try {
            return imeiRepository.getAllMaSP(ma);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateSoLuongSanPhamTrongImei(ImeiViewModel imei) {
        try {
            return imeiRepository.updateSoLuongSanPham(imei);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public ArrayList<ImeiViewModel> locNgungBan() {
        try {
            return imeiRepository.locNgungBan();
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<ImeiViewModel> locDangVe() {
        try {
            return imeiRepository.locDangVe();
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<ImeiViewModel> locDaBan() {
        try {
            return imeiRepository.locDaBan();
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<ImeiViewModel> locOnline() {
        try {
            return imeiRepository.locOnline();
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer them(ImeiViewModel ii) {
        try {
            return imeiRepository.them(ii);
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer trangThaiImei(String maImei) {
        try {
            return imeiRepository.trangThaiImei(maImei);
        } catch (Exception e) {

            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public boolean checkTrungMaImei(String maImei) {
        try {
            return imeiRepository.checkTrungMaImei(maImei);
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

}
