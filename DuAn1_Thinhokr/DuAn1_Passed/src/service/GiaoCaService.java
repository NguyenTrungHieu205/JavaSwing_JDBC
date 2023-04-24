/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.util.Date;
import model.GiaoCa;
import model.HoaDon;
import viewModel.GiaoCaViewModel;

/**
 *
 * @author ADMIN
 */
public interface GiaoCaService {

    ArrayList<GiaoCa> layTongTien(String thoiGianNhanCa, String thoiGianGiaoCa);
    
    ArrayList<GiaoCa> layTongTienMat(String thoiGianNhanCa, String thoiGianGiaoCa);
    
    public Integer addGiaoCa(GiaoCa gc, String thoiGianNhan, String thoiGianGiaoCa);
    
    public Integer updateNhanVien(GiaoCa gc, String thoiGianNhan);
    
    ArrayList<GiaoCaViewModel> getThongTinGiaoCa();
    
    public ArrayList<GiaoCaViewModel> locNgay(String ngayGiao, String ngayNhan);
}
