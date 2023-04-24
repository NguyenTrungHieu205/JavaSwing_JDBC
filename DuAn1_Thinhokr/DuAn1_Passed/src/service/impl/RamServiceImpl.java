/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.ArrayList;
import repository.RamRepository;
import service.RamService;
import viewModel.ManHinhViewModel;
import viewModel.RamViewModel;

/**
 *
 * @author Dell
 */
public class RamServiceImpl implements RamService{

    private RamRepository ramRepo = new RamRepository();
    
    @Override
    public ArrayList<RamViewModel> getAll() {
        try {
            return ramRepo.getAll();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public ArrayList<RamViewModel> getAllXoa() {
        try {
            return ramRepo.getAllXoa();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean create(RamViewModel dl) {
        try {
            return ramRepo.create(dl);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean update(RamViewModel dl) {
        try {
            return ramRepo.update(dl);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean updateTT(RamViewModel dl) {
        try {
            return ramRepo.updateTT(dl);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean updateTT3(RamViewModel dl) {
        try {
            return ramRepo.updateTT3(dl);
        } catch (Exception ex) {
            return false;
        }
    }
    
    @Override
    public Boolean checkTrungMa(String ma) {
        ArrayList<RamViewModel> list = ramRepo.getAll();
        for (RamViewModel x : list) {
            if (x.getMa().equals(ma)) {
                return false;
            }
        }
        return true;
    }
}
