/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.ArrayList;
import viewModel.ImeiBanHang;
import viewModel.ImeiViewModel;

/**
 *
 * @author nguyenhongphong
 */
public interface ImeiBanHangService {
    ArrayList<ImeiBanHang> getAllImei(String maSp);
    
    ArrayList<ImeiBanHang> getAllImei1(String maSp);
    
    ArrayList<ImeiBanHang> getAllImei5(String maSp);
    
    Integer updateImeiTT(String maSp);
    
    Integer insertImeiKT();
    
    Integer updateImeiKT(String maImei);
    
    Integer deleteImeiKT(String maImei);
    
    Integer deleteAllImeiKT();
    
    ArrayList<ImeiViewModel> getAllImeiKT(String maHD, String maSP);
    
    ArrayList<ImeiViewModel> getAllImeiKT1(String maHD);
}
