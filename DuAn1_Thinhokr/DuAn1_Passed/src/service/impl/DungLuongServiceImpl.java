/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.ArrayList;
import java.util.List;
import model.DungLuong;
import repository.DungLuongRepository;
import service.DungLuongService;
import viewModel.DungLuongViewModel;
import java.sql.SQLException;
/**
 *
 * @author phamtuyetnga
 */
public class DungLuongServiceImpl implements  DungLuongService{
      private DungLuongRepository dungLuongRepo = new DungLuongRepository();

    @Override
    public ArrayList<DungLuongViewModel> getAll() {
        try {
            return dungLuongRepo.getAll();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public ArrayList<DungLuongViewModel> getAllXoa() {
        try {
            return dungLuongRepo.getAllXoa();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public boolean create(DungLuongViewModel dl) {
        try {
            return dungLuongRepo.create(dl);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean update(DungLuongViewModel dl) {
        try {
            return dungLuongRepo.update(dl);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean updateTT(DungLuongViewModel dl) {
        try {
            return dungLuongRepo.updateTT(dl);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean updateTT3(DungLuongViewModel dl) {
        try {
            return dungLuongRepo.updateTT3(dl);
        } catch (Exception ex) {
            return false;
        }
    }
    
    @Override
    public Boolean checkTrungMa(String ma) {
        try {
            ArrayList<DungLuongViewModel> list = dungLuongRepo.getAll();
            for (DungLuongViewModel x : list) {
                if (x.getMa().equals(ma)) {
                    return false;
                }
            }
            return true;
        } catch (SQLException ex) {
            return null;
        }
    }
}
