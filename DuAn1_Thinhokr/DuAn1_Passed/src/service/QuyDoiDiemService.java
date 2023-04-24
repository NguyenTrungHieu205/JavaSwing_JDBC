/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import viewModel.QuyDoiDiemViewModel;

/**
 *
 * @author Dell
 */
public interface QuyDoiDiemService {
    
    ArrayList<QuyDoiDiemViewModel> getAll();
    Integer updateQuyDoi(BigDecimal tienTichDiem, BigDecimal tienTieuDiem);
}
