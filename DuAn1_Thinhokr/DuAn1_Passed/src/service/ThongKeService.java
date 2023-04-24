/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.ArrayList;
import viewModel.ThongKeKhachHangViewModel;
import viewModel.ThongKeNhanVienViewModel;
import viewModel.ThongKeSanPhamViewModel;

/**
 *
 * @author ADMIN
 */
public interface ThongKeService {

    ArrayList<ThongKeKhachHangViewModel> getAll();

    public ArrayList<ThongKeNhanVienViewModel> getAllNV();

    public ArrayList<ThongKeSanPhamViewModel> getAll2();
}
