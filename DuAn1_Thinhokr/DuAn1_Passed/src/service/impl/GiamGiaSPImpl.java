/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.GiamGia;
import model.GiamGiaSp;
import repository.GiamGiaSPRepository;
import service.GiamGiaSPService;
import viewModel.GiamGiaViewModel;
import viewModel.ViewSanPham;

/**
 *
 * @author Admin
 */
public class GiamGiaSPImpl implements GiamGiaSPService{
    public GiamGiaSPRepository voucherSPRepository = new GiamGiaSPRepository();
   

    @Override
    public Boolean Checktrung(String Ma) {
         ArrayList<GiamGiaSp> listVCSP = voucherSPRepository.getAll();
        for (GiamGiaSp x : listVCSP) {
            if (x.getMaGiamGiaSP().equals(Ma)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ArrayList<GiamGiaSp> getAll() {
        try {
            return voucherSPRepository.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean addVCSP(String maSP, GiamGiaSp vc) {
        try {
            return voucherSPRepository.addVCSP(maSP, vc);
        } catch (SQLException ex) {
           ex.printStackTrace();
            return null;
        }
    }

//    @Override
//    public Integer xoaVC(GiamGiaSp vc) {
//        return voucherSPRepository.xoaVC(vc);
//    }

    @Override
    public boolean suaCH(GiamGiaSp vc) {
        try {
            boolean isUpdate = voucherSPRepository.suaCH(vc);
            return isUpdate;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    //
    @Override
       public ArrayList<ViewSanPham> getGiaBanVC(String ma){
        try {
            return  voucherSPRepository.getGiaBanVC(ma);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
       
    @Override
       public ArrayList<GiamGiaViewModel> getMucGiamGiaVC(String ma){
        try {
            return  voucherSPRepository.getMucGiamGiaVC(ma);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
       
    @Override
    public ArrayList<GiamGiaSp> getAllVoucherOnline() {
        try {
            return voucherSPRepository.getAllVoucherOnline();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public boolean suaVoucherHetHan(GiamGiaSp vc){
        boolean isUpdate = voucherSPRepository.suaVoucherHetHan(vc);
        return isUpdate;
       
    }
    
    @Override
    public boolean suaMucGiamGiaVe0(GiamGiaSp vc){
       
            boolean isUpdate = voucherSPRepository.suaMucGiamGiaVe0(vc);
            return isUpdate;
     
}
    }
