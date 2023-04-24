/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.ArrayList;
import repository.ImeiBanHangRepository;
import service.ImeiBanHangService;
import viewModel.ImeiBanHang;
import viewModel.ImeiViewModel;

/**
 *
 * @author nguyenhongphong
 */
public class ImeiBanHangServiceIplm implements ImeiBanHangService {

    ImeiBanHangRepository imeiBanHangRepository = new ImeiBanHangRepository();

    @Override
    public ArrayList<ImeiBanHang> getAllImei(String maSp) {
        try {
            return imeiBanHangRepository.getAll(maSp);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public ArrayList<ImeiBanHang> getAllImei1(String maSp) {
        try {
            return imeiBanHangRepository.getAll1(maSp);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public ArrayList<ImeiBanHang> getAllImei5(String maSp) {
        try {
            return imeiBanHangRepository.getAll5(maSp);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Integer updateImeiTT(String maSp) {
        try {
            return imeiBanHangRepository.updateImeiTT(maSp);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    
    @Override
    public Integer insertImeiKT() {
        try {
            return imeiBanHangRepository.insertImeiKT();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    
    @Override
    public Integer updateImeiKT(String maImei) {
        try {
            return imeiBanHangRepository.updateImeiKT(maImei);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    
    @Override
    public Integer deleteImeiKT(String maImei) {
        try {
            return imeiBanHangRepository.deleteImeiKT(maImei);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    
    @Override
    public Integer deleteAllImeiKT() {
        try {
            return imeiBanHangRepository.deleteAllImeiKT();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public ArrayList<ImeiViewModel> getAllImeiKT(String maHD, String maSP) {
        try {
            return imeiBanHangRepository.getAllImeiKT(maHD, maSP);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public ArrayList<ImeiViewModel> getAllImeiKT1(String maHD) {
        try {
            return imeiBanHangRepository.getAllImeiKT1(maHD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
