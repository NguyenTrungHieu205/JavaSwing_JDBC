/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.ArrayList;
import repository.ChipRepository;
import service.ChipService;
import viewModel.ChipViewModel;
import viewModel.ViewModeNhanVien;

/**
 *
 * @author Dell
 */
public class ChipServiceImpl implements ChipService {

    private ChipRepository chipRepo = new ChipRepository();

    @Override
    public ArrayList<ChipViewModel> getAll() {
        try {
            return chipRepo.getAll();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public ArrayList<ChipViewModel> getAllXoa() {
        try {
            return chipRepo.getAllXoa();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean create(ChipViewModel chip) {
        try {
            return chipRepo.create(chip);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean update(ChipViewModel chip) {
        try {
            return chipRepo.update(chip);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean updateTT(ChipViewModel chip) {
        try {
            return chipRepo.updateTT(chip);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean updateTT3(ChipViewModel chip) {
        try {
            return chipRepo.updateTT3(chip);
        } catch (Exception ex) {
            return false;
        }
    }
    @Override
    public Boolean checkTrungMa(String ma) {
        ArrayList<ChipViewModel> listNhanVien = chipRepo.getAll();
        for (ChipViewModel x : listNhanVien) {
            if (x.getMa().equals(ma)) {
                return false;
            }
        }
        return true;
    }

}
