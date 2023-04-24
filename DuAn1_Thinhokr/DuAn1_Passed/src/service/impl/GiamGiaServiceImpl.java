/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.ArrayList;
import model.GiamGia;
import repository.GiamGiaRepository;
import viewModel.GiamGiaViewModel;
import service.GiamGiaService;
import viewModel.ViewSanPham;

/**
 *
 * @author Admin
 */
public class GiamGiaServiceImpl implements GiamGiaService{
    private final GiamGiaRepository voucherRepo = new GiamGiaRepository();
    
    

    // private final GiamGiaRepository voucherRepo = new GiamGiaRepository();
    
    @Override
    public ArrayList<GiamGiaViewModel> getAllVoucher() {
        try {
           return voucherRepo.getAll();
        } catch (Exception e) {      
            e.printStackTrace();
            return null;
        }
    }
   
    @Override
    public Integer them(GiamGia vc) {
            try {
            return voucherRepo.them(vc);
        } catch (Exception e) {
             e.printStackTrace();
            return null;
        }
    }

    

    @Override
    public Integer delete(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

    @Override
    public Integer sua(GiamGia vc) {
         try {
            return voucherRepo.sua(vc);
        } catch (Exception e) {
             e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer updateTrangThai(String ma) {
        try {
            return voucherRepo.updateTrangThai(ma);
        } catch (Exception e) {
             e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public ArrayList<GiamGiaViewModel> layTTGiamGia() {
        try {
            return voucherRepo.layTTGiamGia();
        } catch (Exception e) {
             e.printStackTrace();
            return null;
        }
    }
    
    //
 

}
