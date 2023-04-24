/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.ArrayList;
import repository.MauSacRepository;
import service.MauSacService;
import viewModel.ManHinhViewModel;
import viewModel.MauSacViewModel;

/**
 *
 * @author Dell
 */
public class MauSacServiceImpl implements MauSacService {

    private MauSacRepository mauSacRepo = new MauSacRepository();

    @Override
    public ArrayList<MauSacViewModel> getAll() {
        try {
            return mauSacRepo.getAll();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public ArrayList<MauSacViewModel> getAllXoa() {
        try {
            return mauSacRepo.getAllXoa();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean create(MauSacViewModel mx) {
        try {
            return mauSacRepo.create(mx);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean update(MauSacViewModel mx) {
        try {
            return mauSacRepo.update(mx);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean updateTT(MauSacViewModel mx) {
        try {
            return mauSacRepo.updateTT(mx);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean updateTT3(MauSacViewModel mx) {
        try {
            return mauSacRepo.updateTT3(mx);
        } catch (Exception ex) {
            return false;
        }
    }
    
    @Override
    public Boolean checkTrungMa(String ma) {
        ArrayList<MauSacViewModel> list = mauSacRepo.getAll();
        for (MauSacViewModel x : list) {
            if (x.getMa().equals(ma)) {
                return false;
            }
        }
        return true;
    }

}
