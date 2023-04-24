/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.util.List;
import model.DungLuong;
import viewModel.DungLuongViewModel;

/**
 *
 * @author phamtuyetnga
 */
public interface DungLuongService {

    ArrayList<DungLuongViewModel> getAll();

    ArrayList<DungLuongViewModel> getAllXoa();

    boolean create(DungLuongViewModel dl);

    boolean update(DungLuongViewModel dl);

    boolean updateTT(DungLuongViewModel dl);

    boolean updateTT3(DungLuongViewModel dl);
    
    Boolean checkTrungMa(String ma);
}
