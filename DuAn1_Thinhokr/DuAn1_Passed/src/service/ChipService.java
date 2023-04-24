/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.ArrayList;
import viewModel.ChipViewModel;

/**
 *
 * @author Dell
 */
public interface ChipService {

    ArrayList<ChipViewModel> getAll();

    ArrayList<ChipViewModel> getAllXoa();

    boolean create(ChipViewModel chip);

    boolean update(ChipViewModel chip);

    boolean updateTT(ChipViewModel chip);

    boolean updateTT3(ChipViewModel chip);
    
    public Boolean checkTrungMa(String ma);
}
