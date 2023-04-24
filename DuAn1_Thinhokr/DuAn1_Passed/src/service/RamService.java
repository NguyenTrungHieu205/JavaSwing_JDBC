/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.ArrayList;
import viewModel.DungLuongViewModel;
import viewModel.RamViewModel;

/**
 *
 * @author Dell
 */
public interface RamService {

    ArrayList<RamViewModel> getAll();

    ArrayList<RamViewModel> getAllXoa();

    boolean create(RamViewModel dl);

    boolean update(RamViewModel dl);

    boolean updateTT(RamViewModel dl);

    boolean updateTT3(RamViewModel dl);

    public Boolean checkTrungMa(String ma);
}
