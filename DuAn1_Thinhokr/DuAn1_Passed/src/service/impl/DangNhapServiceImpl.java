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
import model.DangNhap;
import repository.DangNhapRepository;

/**
 *
 * @author ADMIN
 */
public class DangNhapServiceImpl implements service.DangNhapService {

    private final DangNhapRepository dangNhapRepository = new DangNhapRepository();

    @Override
    public ArrayList<DangNhap> taiKhoan() {
        return dangNhapRepository.taiKhoan();
    }

    @Override
    public DangNhap getLogin(String name, String password) {
        //DangNhap ta(String name, String password);
        List<DangNhap> list = taiKhoan();
        for (DangNhap o : list) {
            if (name.equals(o.getTaiKhoan()) && password.equals(o.getMatKhau())) {
                return o;
            }
        }
        return null;
    }

    @Override
    public String checkTK(String maCV) {
        try {
            return dangNhapRepository.checkTK(maCV);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}


