/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewModel;

import java.math.BigDecimal;

/**
 *
 * @author Dell
 */
public class QuyDoiDiemViewModel {

    private BigDecimal tienTichDiem;
    private BigDecimal tienTieuDiem;

    public QuyDoiDiemViewModel() {
    }

    public QuyDoiDiemViewModel(BigDecimal tienTichDiem, BigDecimal tienTieuDiem) {
        this.tienTichDiem = tienTichDiem;
        this.tienTieuDiem = tienTieuDiem;
    }

    public BigDecimal getTienTichDiem() {
        return tienTichDiem;
    }

    public void setTienTichDiem(BigDecimal tienTichDiem) {
        this.tienTichDiem = tienTichDiem;
    }

    public BigDecimal getTienTieuDiem() {
        return tienTieuDiem;
    }

    public void setTienTieuDiem(BigDecimal tienTieuDiem) {
        this.tienTieuDiem = tienTieuDiem;
    }
    
    
}
