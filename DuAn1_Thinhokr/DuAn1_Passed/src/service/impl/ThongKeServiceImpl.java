/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.util.ArrayList;
import repository.ThongKeRepository;
import service.ThongKeService;
import viewModel.ThongKeKhachHangViewModel;
import viewModel.ThongKeNhanVienViewModel;
import viewModel.ThongKeSanPhamViewModel;

/**
 *
 * @author ADMIN
 */
public class ThongKeServiceImpl implements ThongKeService{
    private final ThongKeRepository thongKeRepository = new ThongKeRepository();
    
    @Override
    public ArrayList<ThongKeKhachHangViewModel> getAll(){
        try {
            return thongKeRepository.getAll();
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public ArrayList<ThongKeNhanVienViewModel> getAllNV(){
        try {
            return thongKeRepository.getAll1();
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public ArrayList<ThongKeSanPhamViewModel> getAll2(){
        try {
            return thongKeRepository.getAll2();
        } catch (Exception e) {
            return null;
        }
    }
}
