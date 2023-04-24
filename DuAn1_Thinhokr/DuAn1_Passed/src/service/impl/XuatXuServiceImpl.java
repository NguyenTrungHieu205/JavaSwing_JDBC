/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.ArrayList;
import repository.XuatXuRepository;
import service.XuatXuService;
import viewModel.ManHinhViewModel;
import viewModel.XuatXuViewModel;

/**
 *
 * @author Dell
 */
public class XuatXuServiceImpl implements XuatXuService{

    private XuatXuRepository xuatXuRepo = new XuatXuRepository();
    
    @Override
    public ArrayList<XuatXuViewModel> getAll() {
        try {
            return xuatXuRepo.getAll();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public ArrayList<XuatXuViewModel> getAllXoa() {
        try {
            return xuatXuRepo.getAllXoa();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean create(XuatXuViewModel xx) {
        try {
            return xuatXuRepo.create(xx);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean update(XuatXuViewModel xx) {
        try {
            return xuatXuRepo.update(xx);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean updateTT(XuatXuViewModel xx) {
        try {
            return xuatXuRepo.updateTT(xx);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean updateTT3(XuatXuViewModel xx) {
        try {
            return xuatXuRepo.updateTT3(xx);
        } catch (Exception ex) {
            return false;
        }
    }
    
    @Override
    public Boolean checkTrungMa(String ma) {
        ArrayList<XuatXuViewModel> list = xuatXuRepo.getAll();
        for (XuatXuViewModel x : list) {
            if (x.getMa().equals(ma)) {
                return false;
            }
        }
        return true;
    }
}
