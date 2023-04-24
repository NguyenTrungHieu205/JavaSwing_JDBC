/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.ArrayList;
import service.impl.*;
import java.util.List;
import model.GiamGia;
import model.GiamGiaSp;
import viewModel.GiamGiaViewModel;
import viewModel.ViewSanPham;

/**
 *
 * @author Admin
 */
public interface GiamGiaSPService {

    ArrayList<GiamGiaSp> getAll();

    Boolean addVCSP(String maSP, GiamGiaSp vc);

//    Integer xoaVC(GiamGiaSp vc );

    boolean suaCH(GiamGiaSp vc );
    
    Boolean Checktrung(String ma);
    
    public ArrayList<ViewSanPham> getGiaBanVC(String ma);
    
    public ArrayList<GiamGiaViewModel> getMucGiamGiaVC(String ma);
    
    public ArrayList<GiamGiaSp> getAllVoucherOnline() ;

    public boolean suaVoucherHetHan(GiamGiaSp vc);
    
    public boolean suaMucGiamGiaVe0(GiamGiaSp vc);
}
