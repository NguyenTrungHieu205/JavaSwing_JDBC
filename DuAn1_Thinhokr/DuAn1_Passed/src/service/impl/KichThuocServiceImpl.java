/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.ArrayList;
import repository.KichThuocRepository;
import service.KichThuocService;
import viewModel.ChipViewModel;
import viewModel.KichThuocViewModel;

/**
 *
 * @author Dell
 */
public class KichThuocServiceImpl implements KichThuocService {

    private KichThuocRepository kichThuocRepo = new KichThuocRepository();

    @Override
    public ArrayList<KichThuocViewModel> getAll() {
        try {
            return kichThuocRepo.getAll();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public ArrayList<KichThuocViewModel> getAllXoa() {
        try {
            return kichThuocRepo.getAllXoa();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean create(KichThuocViewModel dl) {
        try {
            return kichThuocRepo.create(dl);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean update(KichThuocViewModel dl) {
        try {
            return kichThuocRepo.update(dl);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean updateTT(KichThuocViewModel dl) {
        try {
            return kichThuocRepo.updateTT(dl);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean updateTT3(KichThuocViewModel dl) {
        try {
            return kichThuocRepo.updateTT3(dl);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Boolean checkTrungMa(String ma) {
        ArrayList<KichThuocViewModel> listNhanVien = kichThuocRepo.getAll();
        for (KichThuocViewModel x : listNhanVien) {
            if (x.getMa().equals(ma)) {
                return false;
            }
        }
        return true;
    }
}
