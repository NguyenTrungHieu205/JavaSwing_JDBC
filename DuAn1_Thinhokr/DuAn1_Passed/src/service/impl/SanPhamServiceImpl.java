/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SanPham;
import repository.SanPhamRepository;
import service.SanPhamService;
import viewModel.ViewSanPham;

/**
 *
 * @author Dell
 */
public class SanPhamServiceImpl implements SanPhamService {

    private SanPhamRepository sanPhanRepo = new SanPhamRepository();

    @Override
    public ArrayList<ViewSanPham> getList() {
        try {
            return sanPhanRepo.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ViewSanPham> getAllXoa() {
        try {
            return sanPhanRepo.getAllXoa();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer addSP(SanPham sp) {
        try {
            return sanPhanRepo.addSP(sp);
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    @Override
    public Integer addSlSp(String maSP) {
        try {
            return sanPhanRepo.addSlSp(maSP);
        } catch (Exception ex) {
           ex.printStackTrace();
            return -1;
        }
    }

    @Override
    public Integer updateSP(SanPham sp) {
        try {
            return sanPhanRepo.updateSP(sp);
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    @Override
    public Integer deleteSP(String sp) {
        try {
            return sanPhanRepo.deleteSP(sp);
        } catch (Exception ex) {
           ex.printStackTrace();
            return -1;
        }
    }

    @Override
    public Integer hoanTacSP(SanPham sp) {
        try {
            return sanPhanRepo.hoanTacSP(sp);
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    @Override
    public ArrayList<SanPham> getThongTin(String maSp) {
        try {
            return (ArrayList<SanPham>) sanPhanRepo.getThongTin(maSp);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

//    @Override
//    public Integer updateSL(SanPham sanPham) {
//        try {
//            return sanPhanRepo.updateSoLuong(sanPham);
//        } catch (SQLException ex) {
//            return null;
//        }
//    }
    @Override
    public boolean insertMaGiamGiaFreeAllSanPham() {
        try {
            return sanPhanRepo.insertMaGiamGiaFree();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insertMaGiamGiaSPFreeSanPham(SanPham sp) {
        try {
            return sanPhanRepo.insertMaGiamGiaSPFree(sp);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean checkTrungMaGiamGiaSP() {

        try {
            return sanPhanRepo.checkMaGiamGia();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean checkTrungMa(String ma) {
        try {
            ArrayList<ViewSanPham> listNhanVien = sanPhanRepo.getAll();
            for (ViewSanPham x : listNhanVien) {
                if (x.getMaSp().equals(ma)) {
                    return false;
                }
            }
            return true;
        } catch (SQLException ex) {
            return null;
        }
    }

}
