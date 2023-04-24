/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.ChucVu;
import model.GiamGia;
import viewModel.MauSacViewModel;
import viewModel.ViewModeChucVu;
import viewModel.GiamGiaViewModel;

/**
 *
 * @author Admin
 */
public interface GiamGiaService {

    ArrayList<GiamGiaViewModel> getAllVoucher();

    Integer them(GiamGia vc);

    Integer sua(GiamGia vc);

    Integer delete(String ma);

    Integer updateTrangThai(String ma);

    ArrayList<GiamGiaViewModel> layTTGiamGia();
}
