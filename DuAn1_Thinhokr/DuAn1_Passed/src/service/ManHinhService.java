/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.ArrayList;
import viewModel.DungLuongViewModel;
import viewModel.ManHinhViewModel;

/**
 *
 * @author Dell
 */
public interface ManHinhService {

    ArrayList<ManHinhViewModel> getAll();

    ArrayList<ManHinhViewModel> getAllXoa();

    boolean create(ManHinhViewModel mh);

    boolean update(ManHinhViewModel mh);

    boolean updateTT(ManHinhViewModel mh);

    boolean updateTT3(ManHinhViewModel mh);

    public Boolean checkTrungMa(String ma);
}
