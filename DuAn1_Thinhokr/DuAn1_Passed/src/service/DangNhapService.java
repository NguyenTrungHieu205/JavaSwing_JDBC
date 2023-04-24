/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.DangNhap;

/**
 *
 * @author ADMIN
 */
public interface DangNhapService {
    public ArrayList<DangNhap> taiKhoan();
    
    DangNhap getLogin(String name, String password);
    
    String checkTK(String maCV);

}
