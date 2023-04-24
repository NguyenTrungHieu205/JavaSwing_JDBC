/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.NhanVien;

/**
 *
 * @author phamtuyetnga
 */
public interface QuenMKService {
    ArrayList<NhanVien> ktraTaiKhoan();
    
    NhanVien getLogin(String username, String email);
    
    Integer resetMatKhau(Integer matKhauMoi, String maNv);
}
