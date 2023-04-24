/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.ArrayList;
import repository.PhanLoaiHangRepository;
import service.PhanLoaiHangService;
import viewModel.ManHinhViewModel;
import viewModel.PhanLoaiHangViewModel;

/**
 *
 * @author Dell
 */
public class PhanLoaiHangServiceImpl implements PhanLoaiHangService{

    private PhanLoaiHangRepository phanLoaiHangRepo = new PhanLoaiHangRepository();
    
    @Override
    public ArrayList<PhanLoaiHangViewModel> getAll() {
        try {
            return phanLoaiHangRepo.getAll();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public ArrayList<PhanLoaiHangViewModel> getAllXoa() {
        try {
            return phanLoaiHangRepo.getAllXoa();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean create(PhanLoaiHangViewModel dl) {
        try {
            return phanLoaiHangRepo.create(dl);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean update(PhanLoaiHangViewModel dl) {
        try {
            return phanLoaiHangRepo.update(dl);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean updateTT(PhanLoaiHangViewModel dl) {
        try {
            return phanLoaiHangRepo.updateTT(dl);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean updateTT3(PhanLoaiHangViewModel dl) {
        try {
            return phanLoaiHangRepo.updateTT3(dl);
        } catch (Exception ex) {
            return false;
        }
    }
    
    @Override
    public Boolean checkTrungMa(String ma) {
        ArrayList<PhanLoaiHangViewModel> list = phanLoaiHangRepo.getAll();
        for (PhanLoaiHangViewModel x : list) {
            if (x.getMa().equals(ma)) {
                return false;
            }
        }
        return true;
    }
}
