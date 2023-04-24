/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import repository.QuyDoiDiemRepository;
import service.QuyDoiDiemService;
import viewModel.QuyDoiDiemViewModel;

/**
 *
 * @author Dell
 */
public class QuyDoiDiemServiceImpl implements QuyDoiDiemService {

    private QuyDoiDiemRepository quyDoiRepo = new QuyDoiDiemRepository();
    
    @Override
    public ArrayList<QuyDoiDiemViewModel> getAll() {
        try {
            return quyDoiRepo.getAll();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Integer updateQuyDoi(BigDecimal tienTichDiem, BigDecimal tienTieuDiem) {
        try {
            return quyDoiRepo.updateQuyDoi(tienTichDiem, tienTieuDiem);
        } catch (Exception e) {
            return null;
        }
    }

}
