/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.ArrayList;
import viewModel.DungLuongViewModel;
import viewModel.PinViewModel;

/**
 *
 * @author Dell
 */
public interface PinService {
    
    ArrayList<PinViewModel> getAll();
    ArrayList<PinViewModel> getAllXoa();
    boolean create(PinViewModel p);
    boolean update(PinViewModel p);
    boolean updateTT(PinViewModel p);
    boolean updateTT3(PinViewModel p);
    public Boolean checkTrungMa(String ma);
}
