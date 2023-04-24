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
import model.NhanVien;
import repository.QuenMKRepository;
import service.QuenMKService;

/**
 *
 * @author phamtuyetnga
 */
public class QuenMKServiceImpl implements QuenMKService {

    private final QuenMKRepository quenRepo = new QuenMKRepository();

    @Override
    public ArrayList<NhanVien> ktraTaiKhoan() {
        try {
            return quenRepo.checkTaiKhoan();
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public Integer resetMatKhau(Integer matKhauMoi, String maNv) {
        try {
            return quenRepo.resetMatKhau(matKhauMoi, maNv);
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public NhanVien getLogin(String username, String email) {
        List<NhanVien> list = ktraTaiKhoan();
        for (NhanVien o : list) {
            if (username.equals(o.getMaNv()) && email.equals(o.getEmail())) {
                return o;
            }
        }
        return null;
    }

}
