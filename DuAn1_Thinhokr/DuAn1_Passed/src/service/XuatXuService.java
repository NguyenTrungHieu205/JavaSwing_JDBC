/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.ArrayList;
import viewModel.DungLuongViewModel;
import viewModel.XuatXuViewModel;

/**
 *
 * @author Dell
 */
public interface XuatXuService {

    ArrayList<XuatXuViewModel> getAll();

    ArrayList<XuatXuViewModel> getAllXoa();

    boolean create(XuatXuViewModel xx);

    boolean update(XuatXuViewModel xx);

    boolean updateTT(XuatXuViewModel xx);

    boolean updateTT3(XuatXuViewModel xx);

    public Boolean checkTrungMa(String ma);
}
