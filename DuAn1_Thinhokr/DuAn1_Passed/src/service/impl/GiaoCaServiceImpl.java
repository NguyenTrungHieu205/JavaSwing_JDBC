/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;


import java.util.ArrayList;
import java.util.Date;
import model.GiaoCa;
import model.HoaDon;
import repository.GiaoCaRepository;
import service.GiaoCaService;
import viewModel.GiaoCaViewModel;

/**
 *
 * @author ADMIN
 */
public class GiaoCaServiceImpl implements GiaoCaService{
    private final GiaoCaRepository caRepository = new GiaoCaRepository();
    
    @Override
    public ArrayList<GiaoCa> layTongTien(String thoiGianNhanCa, String thoiGianGiaoCa){
        try {
            return caRepository.layTongTien(thoiGianNhanCa, thoiGianGiaoCa);
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public ArrayList<GiaoCa> layTongTienMat(String thoiGianNhanCa, String thoiGianGiaoCa){
        try {
            return caRepository.layTongTienMat(thoiGianNhanCa, thoiGianGiaoCa);
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public Integer addGiaoCa(GiaoCa gc, String thoiGianNhan, String thoiGianGiaoCa){
        try {
            return caRepository.addGiaoCa(gc, thoiGianNhan,thoiGianGiaoCa);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
    @Override
     public Integer updateNhanVien(GiaoCa gc, String thoiGianNhan){
         try {
            return caRepository.updateNhanVien(gc, thoiGianNhan);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
     }
     
    @Override
     public ArrayList<GiaoCaViewModel> getThongTinGiaoCa(){
         try {
            return caRepository.getThongTinGiaoCa();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
     } 
     
    @Override
     public ArrayList<GiaoCaViewModel> locNgay(String ngayGiao, String ngayNhan){
         try {
             return caRepository.locNgay(ngayGiao, ngayNhan);
         } catch (Exception e) {
             e.printStackTrace();
             return null;
         }
     }
             
}
