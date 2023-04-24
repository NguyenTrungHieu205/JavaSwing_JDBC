/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import model.SanPham;
import viewModel.ViewSanPham;


public interface SanPhamService {

    ArrayList<ViewSanPham> getList();

    List<ViewSanPham> getAllXoa();

    Integer addSP(SanPham sp);
    
    Integer addSlSp(String maSP);

    Integer updateSP(SanPham sp);

    Integer deleteSP(String sp);

    Integer hoanTacSP(SanPham sp);

    ArrayList<SanPham> getThongTin(String maSp);
    
    boolean insertMaGiamGiaFreeAllSanPham();
    
    //gán giảm giá mặc đinh cho sản phẩm (sql là bảng GiamGiaSP)
    boolean insertMaGiamGiaSPFreeSanPham(SanPham sp);
    // check trung mã giam giá sp
    Boolean checkTrungMaGiamGiaSP();
    
    Boolean checkTrungMa(String ma);

}
