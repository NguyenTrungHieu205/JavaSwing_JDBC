/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.ArrayList;
import viewModel.DungLuongViewModel;
import viewModel.MauSacViewModel;

/**
 *
 * @author Dell
 */
public interface MauSacService {

    ArrayList<MauSacViewModel> getAll();

    ArrayList<MauSacViewModel> getAllXoa();

    boolean create(MauSacViewModel mx);

    boolean update(MauSacViewModel mx);

    boolean updateTT(MauSacViewModel mx);

    boolean updateTT3(MauSacViewModel mx);
    
    public Boolean checkTrungMa(String ma);
}
