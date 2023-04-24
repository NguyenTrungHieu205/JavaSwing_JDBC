/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.ArrayList;
import viewModel.DungLuongViewModel;
import viewModel.PhanLoaiHangViewModel;

/**
 *
 * @author Dell
 */
public interface PhanLoaiHangService {

    ArrayList<PhanLoaiHangViewModel> getAll();

    ArrayList<PhanLoaiHangViewModel> getAllXoa();

    boolean create(PhanLoaiHangViewModel dl);

    boolean update(PhanLoaiHangViewModel dl);

    boolean updateTT(PhanLoaiHangViewModel dl);

    boolean updateTT3(PhanLoaiHangViewModel dl);

    public Boolean checkTrungMa(String ma);
}
