/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.ArrayList;
import repository.PinRepository;
import service.PinService;
import viewModel.ManHinhViewModel;
import viewModel.PinViewModel;

/**
 *
 * @author Dell
 */
public class PinServiceImpl implements PinService{

    private PinRepository pinRepo = new PinRepository();
    
    @Override
    public ArrayList<PinViewModel> getAll() {
        try {
            return pinRepo.getAll();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public ArrayList<PinViewModel> getAllXoa() {
        try {
            return pinRepo.getAllXoa();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean create(PinViewModel p) {
        try {
            return pinRepo.create(p);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean update(PinViewModel p) {
        try {
            return pinRepo.update(p);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean updateTT(PinViewModel p) {
        try {
            return pinRepo.updateTT(p);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean updateTT3(PinViewModel p) {
        try {
            return pinRepo.updateTT3(p);
        } catch (Exception ex) {
            return false;
        }
    }
    
    @Override
    public Boolean checkTrungMa(String ma) {
        ArrayList<PinViewModel> list = pinRepo.getAll();
        for (PinViewModel x : list) {
            if (x.getMa().equals(ma)) {
                return false;
            }
        }
        return true;
    }
}
