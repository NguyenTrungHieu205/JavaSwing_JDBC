/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.ArrayList;
import repository.ManHinhRepository;
import service.ManHinhService;
import viewModel.ChipViewModel;
import viewModel.ManHinhViewModel;

/**
 *
 * @author Dell
 */
public class ManHinhServiceImpl implements ManHinhService {

    private ManHinhRepository manHinhRepo = new ManHinhRepository();

    @Override
    public ArrayList<ManHinhViewModel> getAll() {
        try {
            return manHinhRepo.getAll();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public ArrayList<ManHinhViewModel> getAllXoa() {
        try {
            return manHinhRepo.getAllXoa();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean create(ManHinhViewModel mh) {
        try {
            return manHinhRepo.create(mh);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean update(ManHinhViewModel mh) {
        try {
            return manHinhRepo.update(mh);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean updateTT(ManHinhViewModel mh) {
        try {
            return manHinhRepo.updateTT(mh);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean updateTT3(ManHinhViewModel mh) {
        try {
            return manHinhRepo.updateTT3(mh);
        } catch (Exception ex) {
            return false;
        }
    }
    @Override
    public Boolean checkTrungMa(String ma) {
        ArrayList<ManHinhViewModel> list = manHinhRepo.getAll();
        for (ManHinhViewModel x : list) {
            if (x.getMa().equals(ma)) {
                return false;
            }
        }
        return true;
    }

}
