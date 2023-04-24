/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.ArrayList;
import viewModel.DungLuongViewModel;
import viewModel.KichThuocViewModel;

/**
 *
 * @author Dell
 */
public interface KichThuocService {

    ArrayList<KichThuocViewModel> getAll();

    ArrayList<KichThuocViewModel> getAllXoa();

    boolean create(KichThuocViewModel dl);

    boolean update(KichThuocViewModel dl);

    boolean updateTT(KichThuocViewModel dl);

    boolean updateTT3(KichThuocViewModel dl);
    
     public Boolean checkTrungMa(String ma);
}
